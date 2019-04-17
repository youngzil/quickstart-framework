#!/usr/bin/python
# -*- coding: utf-8 -*-

"""
Function:
【记录】使用Python的IDE：Eclipse+PyDev
http://www.crifan.com/try_with_python_ide_eclipse_pydev
Author:     Crifan Li
Version:    2012-12-29
Contact:    admin at crifan dot com
"""
import platform;
import sys

print "Current platform.uname() in Ecplise+PyDev=",platform.uname();

print sys.argv

#执行./test.py hello
#sys.argv[0] 代表文件本身路径，所带参数从 sys.argv[1] 开始。
