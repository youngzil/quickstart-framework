package org.quickstart.liteflow;

import com.yomahub.liteflow.core.NodeComponent;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/16 13:36
 * @version v1.0
 */
public class AComponent extends NodeComponent {

    @Override
    public void process() {
        String str = this.getSlot().getRequestData();
        System.out.println("Acomponent：Str=" + str);
        System.out.println("Acomponent executed!");
    }

}
