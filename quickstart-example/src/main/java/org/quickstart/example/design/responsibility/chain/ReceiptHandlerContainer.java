package org.quickstart.example.design.responsibility.chain;

import org.quickstart.example.design.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/29 10:50
 * @version v1.0
 */
public class ReceiptHandlerContainer {

    private ReceiptHandlerContainer(){}

    public static List<IReceiptHandler> getReceiptHandlerList(){
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();
        //获取IReceiptHandler接口的实现类
        Set<Class<?>> classList = ReflectionUtil.getClassSetBySuper(IReceiptHandler.class);
        if (classList != null && classList.size() > 0) {
            for (Class<?> clazz : classList) {
                try {
                    receiptHandlerList.add((IReceiptHandler)clazz.newInstance());
                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return receiptHandlerList;
    }

}

