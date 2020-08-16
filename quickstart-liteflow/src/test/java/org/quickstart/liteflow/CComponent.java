/**
 * <p>Title: liteflow</p>
 * <p>Description: 轻量级的组件式流程框架</p>
 * @author Bryan.Zhang
 * @email weenyc31@163.com
 * @Date 2020/4/1
 */
package org.quickstart.liteflow;

import com.yomahub.liteflow.core.NodeComponent;

public class CComponent extends NodeComponent {

	@Override
	public void process() {
		try {
			String[] temp = new String[4000];
			Thread.sleep(300L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.getSlot().setData("m_flag",4);
		System.out.println("Ccomponent executed!");

	}

}
