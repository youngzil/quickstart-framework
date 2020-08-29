package org.quickstart.example.design.entity;

import org.quickstart.example.design.entity.Receipt;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:19
 * @version v1.0
 */
public class ReceiptBuilder {

    public static List<Receipt> generateReceiptList(){
        //直接模拟一堆回执对象
        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(new Receipt("我是MT2101回执喔","MT2101"));
        receiptList.add(new Receipt("我是MT1101回执喔","MT1101"));
        receiptList.add(new Receipt("我是MT8104回执喔","MT8104"));
        receiptList.add(new Receipt("我是MT9999回执喔","MT9999"));
        //......
        return receiptList;
    }
}

