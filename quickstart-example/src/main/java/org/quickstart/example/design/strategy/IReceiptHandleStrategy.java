package org.quickstart.example.design.strategy;

import org.quickstart.example.design.entity.Receipt;
/**
 * <p>回执处理策略接口 </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:22
 * @version v1.0
 */
public interface IReceiptHandleStrategy {

    void handleReceipt(Receipt receipt);

}
