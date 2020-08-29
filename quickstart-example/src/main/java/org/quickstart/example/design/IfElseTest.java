package org.quickstart.example.design;

import org.apache.commons.codec.binary.StringUtils;
import org.quickstart.example.design.entity.ReceiptBuilder;
import org.quickstart.example.design.entity.Receipt;

import java.util.List;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:20
 * @version v1.0
 */
public class IfElseTest {

    public static void main(String[] args) {

        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        //循环处理
        for (Receipt receipt : receiptList) {
            if (StringUtils.equals("MT2101",receipt.getType())) {
                System.out.println("接收到MT2101回执");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑");
            } else if (StringUtils.equals("MT1101",receipt.getType())) {
                System.out.println("接收到MT1101回执");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑");
            } else if (StringUtils.equals("MT8104",receipt.getType())) {
                System.out.println("接收到MT8104回执");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑");
            } else if (StringUtils.equals("MT9999",receipt.getType())) {
                System.out.println("接收到MT9999回执");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑");
                System.out.println("推送邮件");
            }
            // ......未来可能还有好多个else if
        }
    }
}
