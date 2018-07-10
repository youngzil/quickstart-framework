#!/usr/bin/python
import json

data = [{'a': 1, 'b': 2, 'c': 3, 'd': 4, 'e': 5}]

jsonStr = json.dumps(data, sort_keys=True, indent=4, separators=(',', ': '))
# jsonStr = json.dumps(data)
print jsonStr

text = json.loads(jsonStr)
print text
