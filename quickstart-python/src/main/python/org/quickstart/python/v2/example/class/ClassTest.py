# !/usr/bin/python
# -*- coding: UTF-8 -*-

# 析构函数__del__ ，__del__在对象销毁的时候被调用，当对象不再被使用时，__del__方法运行：
class Point:
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y

    def __del__(self):
        class_name = self.__class__.__name__
        print class_name, "销毁"

pt1 = Point()
pt2 = pt1
pt3 = pt1
print id(pt1), id(pt2), id(pt3)  # 打印对象的id
del pt1
print("after delete pt1")
del pt2
print("after delete pt2")
del pt3
print("after delete pt3")




