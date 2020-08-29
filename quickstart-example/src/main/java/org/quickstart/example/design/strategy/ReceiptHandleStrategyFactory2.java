package org.quickstart.example.design.strategy;

import org.apache.commons.lang3.StringUtils;
import org.quickstart.example.design.strategy.impl.Mt2101ReceiptHandleStrategy;
import org.quickstart.example.design.strategy.impl.Mt8104ReceiptHandleStrategy;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:25
 * @version v1.0
 */

/**
 * @Description: 策略工厂
 * @Auther: wuzhazha
 */
public class ReceiptHandleStrategyFactory2 {

    private ReceiptHandleStrategyFactory2() {
    }

    public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType) {
        IReceiptHandleStrategy receiptHandleStrategy = null;
        if (StringUtils.equals("MT2101", receiptType)) {
            receiptHandleStrategy = new Mt2101ReceiptHandleStrategy();
        } else if (StringUtils.equals("MT8104", receiptType)) {
            receiptHandleStrategy = new Mt8104ReceiptHandleStrategy();
        }
        return receiptHandleStrategy;
    }

}
