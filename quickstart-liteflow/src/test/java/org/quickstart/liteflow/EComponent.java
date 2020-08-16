/**
 * <p>Title: liteflow</p>
 * <p>Description: 轻量级的组件式流程框架</p>
 * @author Bryan.Zhang
 * @email weenyc31@163.com
 * @Date 2020/4/1
 */
package org.quickstart.liteflow;

import com.yomahub.liteflow.core.NodeComponent;

public class EComponent extends NodeComponent {

	@Override
	public void process() {
		try {
			Thread.sleep(120L);
			System.out.println("Eomponent，E:" + this.getSlot().getOutput("a"));
			this.getSlot().setOutput(this.getNodeId(), "E component output");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Eomponent executed!");

	}

}
