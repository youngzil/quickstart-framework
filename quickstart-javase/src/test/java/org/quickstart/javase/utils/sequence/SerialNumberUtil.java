package org.quickstart.javase.utils.sequence;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 通过移位算法 生成流水号
 * <p>
 * --> 通用版本（其实各位可以针对具体场景 给出定制化版本  没关系的）
 * (最直接的方式就是日期+主机Id+随机字符串来拼接一个流水号)
 * <p>
 * 免责声明：经本人压力测试，并发量几千是没有任何问题的。但并不保证完全百分百的唯一性（99.99999999%是可以的）
 * 备注：UUID计算HashCode的方式，也并不能保证唯一性
 *
 * @author yourbatman
 * @date 2020/7/27 7:53
 */
public abstract class SerialNumberUtil {

    //采用long值存储 一共63位
    private static final int BIT_COUNT = 63;
    //各个部分占的最大位数（为了减轻负担，时分秒都放到前面去  不要占用long的位数了  但是毫秒我隐藏起来,方便查问题）
    //毫秒值最大为999(1111100111)占10位
    private static final int SHIFTS_FOR_MILLS = 10;
    //下面是各部分的业务位数（各位根据自己不同的业务需求  自己定制）
    //serviceType占位
    private static final int SHIFTS_FOR_SERVICETYPE = 5;
    //shortParam占位
    private static final int SHIFTS_FOR_SHORTPARAM = 5;
    private static final int SHIFTS_FOR_LONGPARAM = 30;

    ///////////////////////////////////
    //最后的随机数 占满剩余位数
    private static final int SHIFTS_FOR_RANDOMNUM =
        BIT_COUNT - SHIFTS_FOR_MILLS - SHIFTS_FOR_SERVICETYPE - SHIFTS_FOR_SHORTPARAM - SHIFTS_FOR_LONGPARAM;

    //掩码（自定义的一种掩码） 做按位与操作，可以去除无用的，还原真生的作用  用于辅助萃取出数据  （此技巧特别巧妙，效率极高）
    private static final long MASK_FOR_MILLS = (1 << SHIFTS_FOR_MILLS) - 1;
    private static final long MASK_FOR_SERVICETYPE = (1 << SHIFTS_FOR_SERVICETYPE) - 1;
    private static final long MASK_FOR_SHORTPARAM = (1 << SHIFTS_FOR_SHORTPARAM) - 1;
    private static final long MASK_FOR_LONGPARAM = (1 << SHIFTS_FOR_LONGPARAM) - 1;

    //时间模版
    private static final String DATE_PATTERN = "yyyyMMddHHmmss";

    /**
     * 生成流水号  若需要隐藏跟多的参数进来，可以加传参。如订单类型（订单id就没啥必要了）等等
     *
     * @param serviceType 业务类型，比如订单号、消费流水号、操作流水号等等  请保持一个公司内不要重复
     *                    最大值：30(11110) 占5位
     * @param shortParam  短参数 不具体定义什么  一般用于表示类型。如这表示订单流水号，这里可以表示订单类型
     *                    最大值：30(11110) 占5位
     * @param longParam   长参数，一般用于记录id参数什么的，比如是订单的话，这里可以表示商户ID（商户一般不会非常多吧）
     *                    最大值：999999999（101111101011110000011111111） 占30位  表示9.999亿的数据  相信作为id的话，一般都超不过这个数值吧
     * @return 流水号 年月日时分秒+long类型的数字 = string串
     */
    public static String genSerialNum(long serviceType, long shortParam, long longParam) {
        if (serviceType > 30) {
            throw new RuntimeException("the max value of 'serviceType' is 30");
        }
        if (shortParam > 30) {
            throw new RuntimeException("the max value of 'shortParam' is 30");
        }
        if (longParam > 99999999) {
            throw new RuntimeException("the max value of 'longParam' is 99999999");
        }

        //放置毫秒值
        long mills = LocalTime.now().getNano() / 1000000; //备注 此处一定要是long类型 否则会按照int的32位去移位
        long millsShift = mills << (BIT_COUNT - SHIFTS_FOR_MILLS);

        //放置serviceType
        long serviceTypeShift = serviceType << (BIT_COUNT - SHIFTS_FOR_MILLS - SHIFTS_FOR_SERVICETYPE);

        //放置shortParam
        long shortParamShift =
            shortParam << (BIT_COUNT - SHIFTS_FOR_MILLS - SHIFTS_FOR_SERVICETYPE - SHIFTS_FOR_SHORTPARAM);

        //放置longParam
        long longParamShift =
            longParam << (BIT_COUNT - SHIFTS_FOR_MILLS - SHIFTS_FOR_SERVICETYPE - SHIFTS_FOR_SHORTPARAM
                - SHIFTS_FOR_LONGPARAM);

        //生成一个指定位数（二进制位数）的随机数  最后一个 不需要左移了 因为长度就是自己
        long randomShift = getBinaryRandom(SHIFTS_FOR_RANDOMNUM);

        //拼接各个部分
        long finalNum = millsShift | serviceTypeShift | shortParamShift | longParamShift | randomShift;

        //最后前面拼接上年月日时分秒 返回出去
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)) + finalNum;
    }

    /**
     * 拿到指定位数的 首位数字不为0的位数，最终以十进制数返回出来
     *
     * @param count 需要的总位数 总位数不允许超过63
     * @return binary random
     */
    private static long getBinaryRandom(int count) {
        StringBuffer sb = new StringBuffer();
        String str = "01";

        //采用ThreadLocalRandom 生成随机数 避免多线程问题
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            char c = str.charAt(num);
            while (c == '0') { //确保第一个是不为0数字 否则一直循环去获取
                if (i != 0) {
                    break;
                } else {
                    num = r.nextInt(str.length());
                    c = str.charAt(num);
                }
            }
            sb.append(c);
        }
        return Long.valueOf(sb.toString(), 2);
    }

    //===============================提供便捷获取各个部分的工具方法===================================

    /**
     * 从序列号拿到日期 并且格式化为LocalDateTime格式
     *
     * @param serialNumber 流水号
     * @return 日期时间
     */
    public static LocalDateTime getDate(String serialNumber) {
        String dateStr = serialNumber.substring(0, DATE_PATTERN.length());
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    /**
     * 拿到毫秒数：是多少毫秒
     *
     * @param serialNumber 流水号
     * @return 毫秒数
     */
    public static long getMills(String serialNumber) {
        return getLongSerialNumber(serialNumber) >> (BIT_COUNT - SHIFTS_FOR_MILLS) & MASK_FOR_MILLS;
    }

    /**
     * 拿到 serviceType
     *
     * @param serialNumber 流水号
     * @return serviceType
     */
    public static long getServiceType(String serialNumber) {
        return getLongSerialNumber(serialNumber) >> (BIT_COUNT - SHIFTS_FOR_MILLS - SHIFTS_FOR_SERVICETYPE)
            & MASK_FOR_SERVICETYPE;
    }

    /**
     * 拿到 shortParam
     *
     * @param serialNumber 流水号
     * @return shortParam
     */
    public static long getShortParam(String serialNumber) {
        return getLongSerialNumber(serialNumber) >> (BIT_COUNT - SHIFTS_FOR_MILLS - SHIFTS_FOR_SERVICETYPE
            - SHIFTS_FOR_SHORTPARAM) & MASK_FOR_SHORTPARAM;
    }

    /**
     * 拿到 longParam
     *
     * @param serialNumber 流水号
     * @return longParam
     */
    public static long getLongParam(String serialNumber) {
        return getLongSerialNumber(serialNumber) >> (BIT_COUNT - SHIFTS_FOR_MILLS - SHIFTS_FOR_SERVICETYPE
            - SHIFTS_FOR_SHORTPARAM - SHIFTS_FOR_LONGPARAM) & MASK_FOR_LONGPARAM;
    }

    //把日期前缀去掉
    private static long getLongSerialNumber(String serialNumber) {
        return Long.parseLong(serialNumber.substring(DATE_PATTERN.length()));
    }

    //==================================================================

    /**
     * 提供测试的Main方法
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String serialNum = genSerialNum(1, 2, 300);
        System.out.println(serialNum); //20181121173040299068801480344

        //拿long型的值
        System.out.println(getLongSerialNumber(serialNum)); //299068801480344
        System.out.println(Long.toBinaryString(getLongSerialNumber(serialNum)));

        //拿到日期时间
        System.out.println(getDate(serialNum)); //2018-11-21T17:30:40

        //拿毫秒值
        System.out.println((LocalTime.now().getNano() / 1000000));
        System.out.println(getMills(serialNum));

        //拿到serviceType
        System.out.println(getServiceType(serialNum)); //1

        //拿到shortParam
        System.out.println(getShortParam(serialNum)); //2

        //拿到longParam
        System.out.println(getLongParam(serialNum)); //300

        // IdGenerator参考https://developer.aliyun.com/article/771042

    }
}
