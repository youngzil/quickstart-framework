package org.quickstart.liteflow;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.entity.data.Slot;

import java.util.Arrays;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/16 13:37
 * @version v1.0
 */
public class LiteflowTest {

    public static void main(String[] args) throws Exception {
        FlowExecutor executor = new FlowExecutor();
        executor.setRulePath(Arrays.asList(new String[] {"config/flow.xml"}));
        executor.init();
        Slot slot = executor.execute("demoChain", "arg");
    }
}
