package org.quickstart.example.design.strategy;

import org.quickstart.example.design.strategy.impl.Mt2101ReceiptHandleStrategy;
import org.quickstart.example.design.strategy.impl.Mt8104ReceiptHandleStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>策略工厂 </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:25
 * @version v1.0
 */
public class ReceiptHandleStrategyFactory {

    private static Map<String,IReceiptHandleStrategy> receiptHandleStrategyMap;

    public ReceiptHandleStrategyFactory(){
        this.receiptHandleStrategyMap = new HashMap<>();
        this.receiptHandleStrategyMap.put("MT2101",new Mt2101ReceiptHandleStrategy());
        this.receiptHandleStrategyMap.put("MT8104",new Mt8104ReceiptHandleStrategy());
    }

    public IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType){
        return receiptHandleStrategyMap.get(receiptType);
    }
}
