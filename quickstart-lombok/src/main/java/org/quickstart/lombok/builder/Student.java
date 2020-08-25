package org.quickstart.lombok.builder;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/25 14:58
 * @version v1.0
 */
public class Student {
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private String name;
        private int age;
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder age(int age){
            this.age = age;
            return this;
        }
        public Student build(){
            Student student = new Student();
            student.setAge(age);
            student.setName(name);
            return student;
        }
    }

    public static void main(String[] args) {
        Student student = Student.builder().name("zs").age(24).build();
    }
}
