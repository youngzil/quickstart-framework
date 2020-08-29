package org.quickstart.example.design.strategy;

import org.quickstart.example.design.entity.Receipt;
/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:24
 * @version v1.0
 */
/**
 * @Description: 上下文类，持有策略接口
 * @Auther: wuzhazha
 */
public class ReceiptStrategyContext {

    private IReceiptHandleStrategy receiptHandleStrategy;

    /**
     * 设置策略接口
     * @param receiptHandleStrategy
     */
    public void setReceiptHandleStrategy(IReceiptHandleStrategy receiptHandleStrategy) {
        this.receiptHandleStrategy = receiptHandleStrategy;
    }

    public void handleReceipt(Receipt receipt) {
        if (receiptHandleStrategy != null) {
            receiptHandleStrategy.handleReceipt(receipt);
        }
    }
}

