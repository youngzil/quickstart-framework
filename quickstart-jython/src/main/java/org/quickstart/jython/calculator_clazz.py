# coding=utf-8
import math

# 面向对象编程
class Calculator(object):
    
    # 计算x的y次方
    def power(self, x, y):
        return math.pow(x,y)