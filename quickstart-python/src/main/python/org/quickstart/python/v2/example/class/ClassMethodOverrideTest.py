#!/usr/bin/python
# -*- coding: UTF-8 -*-

class Parent:  # 定义父类
    def myMethod(self):
        print '调用父类方法'


class Child(Parent):  # 定义子类
    def myMethod(self):
        print '调用子类方法'


c = Child()  # 子类实例
c.myMethod()  # 子类调用重写方法


# 运算符重载
# Python同样支持运算符重载，实例如下：

class Vector:
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def __str__(self):
        return 'Vector (%d, %d)' % (self.a, self.b)

    def __add__(self, other):
        return Vector(self.a + other.a, self.b + other.b)


v1 = Vector(2, 10)
v2 = Vector(5, -2)
print v1 + v2


# 类属性与方法:类的私有属性、类的方法、类的私有方法

class JustCounter:
    __secretCount = 0  # 私有变量
    publicCount = 0  # 公开变量

    def count(self):
        self.__secretCount += 1
        self.publicCount += 1
        print self.__secretCount
        self.__privateMethod()

    def count2(self):
         print self.__secretCount

    def __privateMethod(self):
        print "private method"

counter = JustCounter()
# 在类的对象生成后,调用含有类私有属性的函数时就可以使用到私有属性.
counter.count()
#第二次同样可以.
counter.count()
print counter.publicCount
# print counter.__secretCount  # 报错，实例不能访问私有变量，AttributeError: JustCounter instance has no attribute '__secretCount'
# Python不允许实例化的类访问私有数据，但你可以使用 object._className__attrName 访问属性，将如下代码替换以上代码的最后一行代码：
print counter._JustCounter__secretCount

try:
    counter.count2()
except IOError:
    print "不能调用非公有属性!"
else:
    print "ok!" #现在呢!证明是滴!




# 新式类和经典类的区别：
class A:
   def foo(self):
      print('called A.foo()')
class B(A):
   pass
class C(A):
   def foo(self):
      print('called C.foo()')
class D(B, C,object):
# class D(B, C):
   pass

if __name__ == '__main__':
   d = D()
   d.foo()
# D 继承了 object 和不继承程序输出不一样，继承 object 会调用 C 类的 foo，否则会调用 Ａ 的。
# 使用 super 进行父类构造调用那么必须使用 object 继承的新式类，否则报错。




