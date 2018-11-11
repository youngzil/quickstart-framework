/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：DeepCopy.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.prototype.clone;

/**
 * DeepCopy
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:30:47
 * @since 1.0
 */
public class Body implements Cloneable {

    public Head head;

    public Body() {}

    public Body(Head head) {
        this.head = head;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Body newBody = (Body) super.clone();
        newBody.head = (Head) head.clone();
        return newBody;
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Body body = new Body(new Head(new Face()));

        Body body1 = (Body) body.clone();

        System.out.println("body == body1 : " + (body == body1));

        System.out.println("body.head == body1.head : " + (body.head == body1.head));

        System.out.println("body.head.face == body1.head.face : " + (body.head.face == body1.head.face));

    }

}


class Head implements Cloneable {
    public Face face;

    public Head() {}

    public Head(Face face) {
        this.face = face;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // return super.clone();
        Head newHead = (Head) super.clone();
        newHead.face = (Face) this.face.clone();
        return newHead;
    }
}


class Face implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
