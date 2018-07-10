#!/usr/bin/python
# -*- coding: UTF-8 -*-

import os

print "环境变量";
for key in os.environ.keys():
    print key, "=", os.environ[key]
