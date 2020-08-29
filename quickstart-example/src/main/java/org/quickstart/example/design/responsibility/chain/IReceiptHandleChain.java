package org.quickstart.example.design.responsibility.chain;

import org.quickstart.example.design.entity.Receipt;
/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:31
 * @version v1.0
 */
/**
 * @Description: 责任链接口
 * @Auther: wuzhazha
 */
public interface IReceiptHandleChain {

    void handleReceipt(Receipt receipt);
}
