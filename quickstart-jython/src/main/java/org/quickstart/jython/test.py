#!/usr/bin/python
# -*- coding: utf-8 -*-
def first():
    print("first ...........")

first()

def second():
    a=100
    b=50
    return a+b

def third(a,b):
    c=sub(a,b)
    d=sub(b,a)
    return c*d

def sub(a,b):
    return a-b

def word_process(a):
    if a=="你好":
        print(True)
    else:
        print(False)
    print(a)
    return a