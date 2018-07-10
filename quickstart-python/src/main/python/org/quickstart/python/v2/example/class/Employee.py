#!/usr/bin/python
# -*- coding: UTF-8 -*-


# empCount 变量是一个类变量，它的值将在这个类的所有实例之间共享。你可以在内部类或外部类使用 Employee.empCount 访问。
# 第一种方法__init__()方法是一种特殊的方法，被称为类的构造函数或初始化方法，当创建了这个类的实例时就会调用该方法
# self 代表类的实例，self 在定义类的方法时是必须有的，虽然在调用时不必传入相应的参数。
class Employee:
    '所有员工的基类'
    empCount = 0

    def __init__(self, name, salary):
        self.name = name
        self.salary = salary
        Employee.empCount += 1


    def displayCount(self):
        print "Total Employee %d" % Employee.empCount


    def displayEmployee(self):
        print "Name : ", self.name, ", Salary: ", self.salary


# self代表类的实例，而非类
# 类的方法与普通的函数只有一个特别的区别——它们必须有一个额外的第一个参数名称, 按照惯例它的名称是 self。

class Test:
    def prt(self):
        print(self)
        print(self.__class__)


t = Test()
t.prt()


# 从执行结果可以很明显的看出，self 代表的是类的实例，代表当前对象的地址，而 self.class 则指向类。
# self 不是 python 关键字，我们把他换成 runoob 也是可以正常执行的:

class Test2:
    def prt(runoob):
        print(runoob)
        print(runoob.__class__)

t = Test2()
t.prt()


# 创建实例对象
# 实例化类其他编程语言中一般用关键字 new，但是在 Python 中并没有这个关键字，类的实例化类似函数调用方式。
# 以下使用类的名称 Employee 来实例化，并通过 __init__ 方法接受参数。

# "创建 Employee 类的第一个对象"
emp1 = Employee("Zara", 2000)
# "创建 Employee 类的第二个对象"
emp2 = Employee("Manni", 5000)

# 访问属性
# 您可以使用点(.)来访问对象的属性。使用如下类的名称访问类变量:
emp1.displayEmployee()
emp2.displayEmployee()
print "Total Employee %d" % Employee.empCount

print emp1
print emp2


"""
Python内置类属性
__dict__ : 类的属性（包含一个字典，由类的数据属性组成）
__doc__ :类的文档字符串
__name__: 类名
__module__: 类定义所在的模块（类的全名是'__main__.className'，如果类位于一个导入模块mymod中，那么className.__module__ 等于 mymod）
__bases__ : 类的所有父类构成元素（包含了一个由所有父类组成的元组）
"""

print "Employee.__doc__:", Employee.__doc__
print "Employee.__name__:", Employee.__name__
print "Employee.__module__:", Employee.__module__
print "Employee.__bases__:", Employee.__bases__
print "Employee.__dict__:", Employee.__dict__

# 你可以添加，删除，修改类的属性，如下所示：

emp1.age = 7  # 添加一个 'age' 属性
print emp1.age

emp1.age = 8  # 修改 'age' 属性
print emp1.age

del emp1.age  # 删除 'age' 属性
# print emp1.age #报错，AttributeError: Employee instance has no attribute 'age'


# 你也可以使用以下函数的方式来访问属性：
# getattr(obj, name[, default]) : 访问对象的属性。
# hasattr(obj,name) : 检查是否存在一个属性。
# setattr(obj,name,value) : 设置一个属性。如果属性不存在，会创建一个新属性。
# delattr(obj, name) : 删除属性。

print hasattr(emp1, 'age')    # 如果存在 'age' 属性返回 True。
# print getattr(emp1, 'age')    # 返回 'age' 属性的值，报错，AttributeError: Employee instance has no attribute 'age'
setattr(emp1, 'age', 8) # 添加属性 'age' 值为 8
print getattr(emp1, 'age')    # 返回 'age' 属性的值
delattr(emp1, 'age')    # 删除属性 'age'
# print getattr(emp1, 'age')    # 返回 'age' 属性的值，报错，AttributeError: Employee instance has no attribute 'age'
















