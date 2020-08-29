package org.quickstart.example.design.responsibility.chain;
import org.quickstart.example.design.responsibility.chain.impl.Mt2101ReceiptHandler;
import org.quickstart.example.design.responsibility.chain.impl.Mt8104ReceiptHandler;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>描述: [功能描述] </p >
 * 处理者容器
 * @author yangzl
 * @date 2020/8/29 10:35
 * @version v1.0
 */
public class ReceiptHandlerContainer2 {

    private ReceiptHandlerContainer2(){}

    public static List<IReceiptHandler> getReceiptHandlerList(){
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();
        receiptHandlerList.add(new Mt2101ReceiptHandler());
        receiptHandlerList.add(new Mt8104ReceiptHandler());
        return receiptHandlerList;
    }

}