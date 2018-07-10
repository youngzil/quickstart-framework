#!/usr/bin/python
# -*- coding: UTF-8 -*-

for i in range(1,5):
    for j in range(1,5):
        for k in  range(1,5):
            if(i != j)  and (i != k) and (j != k):
                print  i,j,k

d=[]
for a in range(1,5):
    for b in range(1,5):
        for c in range(1,5):
            if (a!=b) and (a!=c) and (c!=b):
                d.append([a,b,c])
print "总数量：", len(d)
print d


list_num = [1,2,3,4]
list  = [i*100 + j*10 + k for i in list_num for j in list_num for k in list_num if (j != i and k != j and k != i)]
print (list)
print "count=",len(list)



