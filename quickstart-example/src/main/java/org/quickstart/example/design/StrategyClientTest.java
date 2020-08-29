package org.quickstart.example.design;

import org.quickstart.example.design.strategy.IReceiptHandleStrategy;
import org.quickstart.example.design.entity.ReceiptBuilder;
import org.quickstart.example.design.entity.Receipt;
import org.quickstart.example.design.strategy.ReceiptHandleStrategyFactory;
import org.quickstart.example.design.strategy.ReceiptStrategyContext;

import java.util.List;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:26
 * @version v1.0
 */
public class StrategyClientTest {
    public static void main(String[] args) {
        //模拟回执
        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        //策略上下文
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();
        for (Receipt receipt : receiptList) {
            //获取并设置策略
            IReceiptHandleStrategy receiptHandleStrategy = new ReceiptHandleStrategyFactory().getReceiptHandleStrategy(receipt.getType());
            receiptStrategyContext.setReceiptHandleStrategy(receiptHandleStrategy);
            //执行策略
            receiptStrategyContext.handleReceipt(receipt);
        }
    }

//    经过对策略模式+简单工厂方案的改造，我们已经消除了if-else的结构，
//    每当新来了一种回执，只需要添加新的回执处理策略，并修改ReceiptHandleStrategyFactory中的Map集合。
//    如果要使得程序符合开闭原则，则需要调整ReceiptHandleStrategyFactory中处理策略的获取方式，通过反射的方式，获取指定包下的所有IReceiptHandleStrategy实现类，然后放到字典Map中去。
}
