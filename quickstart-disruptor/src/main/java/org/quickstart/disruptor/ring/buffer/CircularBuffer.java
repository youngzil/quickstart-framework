/**
 * 项目名称：quickstart-disruptor 
 * 文件名：CircularBuffer.java
 * 版本信息：
 * 日期：2018年9月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.disruptor.ring.buffer;

/**
 * CircularBuffer
 * 
 * 环形缓冲器。逻辑上首尾衔接.
 * 
 * @author：youngzil@163.com
 * @2018年9月25日 下午8:34:58
 * @since 1.0
 */
public class CircularBuffer<T> {
    /***************************************
     * 为保证buffer的put和 get有序进行,用两个索引 putIndex�待填入元素空位的索引 getIndex�待取出元素的索引
     ***************************************/
    private int putIndex = 0;
    private int getIndex = 0;
    private Object[] buffer = null;
    private int capability = 0; // buffer容量

    /**
     * jsut for test~
     * 
     * @param helloBaby
     */
    public static void main(String[] helloBaby) {
        // CircularBuffer<String> a = new CircularBuffer<String>(1024);
        // System.out.println(a.putElement(null));
        // System.out.println(a.putElement("能存进去吗？"));
        // System.out.println(a.getElement());
        // System.out.println(a.getElement());
        // System.out.println(a.getElementWithBlock());
        // System.out.println("如果控制台木有这条信息，那么上一句代码造成阻塞了~");

        CircularBuffer<String> test = new CircularBuffer<String>(128);
        int i = 0;
        for (boolean canPut = true; canPut;) {
            canPut = test.putElement("第" + (++i) + "个元素~~");
            // System.out.println(canPut);
        }

        for (boolean canGet = true; canGet;) {
            String echoString = test.getElement();
            canGet = (null != echoString);
            if (canGet)
                System.out.println(echoString);
        }
    }

    /**
     * 
     * @param capability 缓冲区大小
     */
    public CircularBuffer(int capability) {
        this.capability = capability;
        initialize();
    }

    /**
     * @param capability
     */
    private void initialize() {
        this.buffer = new Object[this.capability];
    }

    /**
     * 填入元素：注意此函数会造成阻塞
     * 
     * @param element- 带填入元素
     */
    public void putElementWithBlock(T element) {

        while (!putElement(element)) {
            try {
                Thread.sleep(50);

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("put�߳��ж�");
            }
        }
    }

    /**
     * 取出元素，注意此函数会造成阻塞
     */
    public T getElementWithBlock() {
        T temp = null;
        while (null == (temp = getElement())) {
            try {
                Thread.sleep(50);

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("get�߳��ж�");
            }
        }
        return temp;
    }

    /**
     * 填入元素,此函数会立即返回
     * 
     * @param element
     * @return true-操作成功 false-操作失败（待填入数据的元素位还有数据）
     */
    public boolean putElement(T element) {
        if (element == null)
            throw new NullPointerException("非要往里边填null么？");

        /* 不要覆盖已有数据 */
        if (this.buffer[this.putIndex] != null) {
            return false;
        }

        this.buffer[this.putIndex] = element;
        this.putIndex++;
        this.putIndex %= this.capability;
        return true;
    }

    /**
     * 取出元素,此函数会立即返回
     * 
     * @return null-待取数据的元素位为null ,非null-成功取出数据
     */
    @SuppressWarnings("unchecked")
    public T getElement() {
        if (this.buffer[this.getIndex] == null) {
            return null;
        }

        // 拿到引用，并将元素位置空
        Object temp = this.buffer[this.getIndex];
        this.buffer[this.getIndex] = null;
        this.getIndex++;
        this.getIndex %= this.capability;
        return (T) temp;
    }

    public void clear() {
        for (int i = 0, length = buffer.length; i < length; i++) {
            buffer[i] = null;
        }
        this.putIndex = 0;
        this.getIndex = 0;
    }

    /****************************************************
     * \ --Setter and Getter-- * \
     ****************************************************/
    public int getCapability() {
        return capability;
    }

    // 新元素是以 索引0 向 索引length 的顺序 put入
    // 有鉴于此，这里倒过来枚举，防止出现“同向追赶”导致落空的的囧事；
    public boolean isEmputy() {
        for (int i = this.buffer.length - 1; i > 0; i--) {
            if (this.buffer[i] != null) {
                return false;
            }
        }
        return true;
    }

}
