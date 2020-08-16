/**
 * <p>Title: liteflow</p>
 * <p>Description: 轻量级的组件式流程框架</p>
 * @author Bryan.Zhang
 * @email weenyc31@163.com
 * @Date 2020/4/1
 */
package org.quickstart.liteflow;

import com.yomahub.liteflow.core.NodeComponent;

public class BComponent extends NodeComponent {

	@Override
	public void process() {
		try {
			Thread.sleep(400L);
			String[] temp = new String[1000];
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Bcomponent executed!");

	}

}
