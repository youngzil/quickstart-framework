package org.quickstart.example.design.responsibility.chain;

import org.quickstart.example.design.entity.Receipt;

import java.util.List;

/**
 * <p>描述: [功能描述] </p >
 * 责任链实现类
 * @author yangzl
 * @date 2020/8/29 10:32
 * @version v1.0
 */
public class ReceiptHandleChain implements IReceiptHandleChain {
    //记录当前处理者位置
    private int index = 0;
    //处理者集合
    private static List<IReceiptHandler> receiptHandlerList;

    static {
        //从容器中获取处理器对象
        receiptHandlerList = ReceiptHandlerContainer.getReceiptHandlerList();
        //        receiptHandlerList = ReceiptHandlerContainer2.getReceiptHandlerList();
    }

    @Override
    public void handleReceipt(Receipt receipt) {
        if (receiptHandlerList != null && receiptHandlerList.size() > 0) {
            if (index != receiptHandlerList.size()) {
                IReceiptHandler receiptHandler = receiptHandlerList.get(index++);
                receiptHandler.handleReceipt(receipt, this);
            }
        }
    }
}
