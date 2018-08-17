/**
 * 项目名称：quickstart-proxy 
 * 文件名：ReloadTask.java
 * 版本信息：
 * 日期：2018年8月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.hot.deploy.example2;

import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.TimerTask;

/**
 * ReloadTask
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月17日 下午10:36:10
 * @since 1.0
 */
public class ReloadTask extends TimerTask {
    private Instrumentation inst;

    protected ReloadTask(Instrumentation inst) {
        this.inst = inst;
    }

    @Override
    public void run() {
        try {
            ClassDefinition[] cd = new ClassDefinition[1];
            Class[] classes = inst.getAllLoadedClasses();
            for (Class cls : classes) {
                if (cls.getClassLoader() == null || !cls.getClassLoader().getClass().getName().equals("sun.misc.Launcher$AppClassLoader"))
                    continue;
                String name = cls.getName().replaceAll("\\.", "/");
                cd[0] = new ClassDefinition(cls, loadClassBytes(cls, name + ".class"));
                inst.redefineClasses(cd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private byte[] loadClassBytes(Class cls, String clsname) throws Exception {
        System.out.println(clsname + ":" + cls);
        InputStream is = cls.getClassLoader().getSystemClassLoader().getResourceAsStream(clsname);
        if (is == null)
            return null;
        byte[] bt = new byte[is.available()];
        is.read(bt);
        is.close();
        return bt;
    }
}
