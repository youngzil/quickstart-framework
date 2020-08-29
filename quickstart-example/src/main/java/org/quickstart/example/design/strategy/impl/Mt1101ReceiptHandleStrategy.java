package org.quickstart.example.design.strategy.impl;

import org.quickstart.example.design.strategy.IReceiptHandleStrategy;
import org.quickstart.example.design.entity.Receipt;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:23
 * @version v1.0
 */
public class Mt1101ReceiptHandleStrategy implements IReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT1101:" + receipt.getMessage());
    }

}
