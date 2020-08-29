package org.quickstart.example.design;

import org.quickstart.example.design.entity.ReceiptBuilder;
import org.quickstart.example.design.entity.Receipt;
import org.quickstart.example.design.responsibility.chain.ReceiptHandleChain;

import java.util.List;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:36
 * @version v1.0
 */
public class ResponsibilityChainTest {

    public static void main(String[] args) {
        //模拟回执
        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        for (Receipt receipt : receiptList) {
            //回执处理链对象
            ReceiptHandleChain receiptHandleChain = new ReceiptHandleChain();
            receiptHandleChain.handleReceipt(receipt);
        }
    }

    //    通过责任链的处理方式，if-else结构也被我们消除了，
    //    每当新来了一种回执，只需要添加IReceiptHandler实现类并修改ReceiptHandlerContainer处理者容器即可，
    //    如果要使得程序符合开闭原则，则需要调整ReceiptHandlerContainer中处理者的获取方式，通过反射的方式，获取指定包下的所有IReceiptHandler实现类

}
