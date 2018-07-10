#!/usr/bin/python
import demjson

"""
JSON 函数
函数	描述
encode	将 Python 对象编码成 JSON 字符串
decode	将已编码的 JSON 字符串解码为 Python 对象
"""

data = [{'a': 1, 'b': 2, 'c': 3, 'd': 4, 'e': 5}]

jsonStr = demjson.encode(data)
print jsonStr

text = demjson.decode(jsonStr)
print  text
