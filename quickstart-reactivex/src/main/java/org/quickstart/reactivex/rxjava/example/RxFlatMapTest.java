package org.quickstart.reactivex.rxjava.example;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxFlatMapTest {

    private static SchoolClass[] mSchoolClasses = new SchoolClass[2];

    static {
        initData();
    }

    private static void initData() {
        Student[] student = new Student[5];
        for (int i = 0; i < 5; i++) {
            Student s = new Student("二狗" + i, "17");
            student[i] = s;
        }
        mSchoolClasses[0] = new SchoolClass(student);

        Student[] student2 = new Student[5];
        for (int i = 0; i < 5; i++) {
            Student s = new Student("小明" + i, "27");
            student2[i] = s;
        }
        mSchoolClasses[1] = new SchoolClass(student2);

    }

    public static SchoolClass[] getSchoolClass() {
        return mSchoolClasses;
    }

    public static void main(String[] args) {
        Observable.from(getSchoolClass())//
        .flatMap(new Func1<SchoolClass, Observable<Student>>() {
            @Override
            public Observable<Student> call(SchoolClass schoolClass) {
                // 将Student列表使用from方法一个一个发出去
                return Observable.from(schoolClass.getStudents());
            }
        }).subscribe(new Action1<Student>() {
            @Override
            public void call(Student student) {
                System.out.println("打印单个学生信息：\n");
                System.out.println("name:" + student.name + "    age: " + student.age + "\n");
            }
        });
    }
}


class SchoolClass {
    Student[] stud;

    public SchoolClass(Student[] s) {
        this.stud = s;
    }

    public Student[] getStudents() {
        return stud;
    }
}


class Student {
    String name;
    String age;

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
