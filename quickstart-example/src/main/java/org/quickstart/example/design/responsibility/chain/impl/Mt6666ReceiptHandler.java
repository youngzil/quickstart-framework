package org.quickstart.example.design.responsibility.chain.impl;

import org.apache.commons.lang3.StringUtils;
import org.quickstart.example.design.entity.Receipt;
import org.quickstart.example.design.responsibility.chain.IReceiptHandleChain;
import org.quickstart.example.design.responsibility.chain.IReceiptHandler;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:50
 * @version v1.0
 */
public class Mt6666ReceiptHandler implements IReceiptHandler {

    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if (StringUtils.equals("MT6666",receipt.getType())) {
            System.out.println("解析报文MT6666:" + receipt.getMessage());
        }
        //处理不了该回执就往下传递
        else {
            handleChain.handleReceipt(receipt);
        }
    }
}
