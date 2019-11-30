#!/usr/local/bin/lua

print("Hello World！")
print("www.runoob.com")

-- 两个减号是单行注释:

--[[
 多行注释
 多行注释
 --]]

--全局变量b，在默认情况下，变量总是认为是全局的。

print(b)
b=10
print(b)

--如果你想删除一个全局变量，只需要将变量赋值为nil。
b = nil
print(b)

--[[Lua 是动态类型语言，变量不要类型定义,只需要为变量赋值。 值可以存储在变量中，作为参数传递或结果返回。
    Lua 中有 8 个基本类型分别为：nil、boolean、number、string、userdata、function、thread 和 table。--]]

print(type("Hello world"))      --> string
print(type(10.4*3))             --> number
print(type(print))              --> function
print(type(type))               --> function
print(type(true))               --> boolean
print(type(nil))                --> nil
print(type(type(X)))            --> string


--对于全局变量和 table，nil 还有一个"删除"作用，给全局变量或者 table 表里的变量赋一个 nil 值，等同于把它们删掉，执行下面代码就知：

tab1 = { key1 = "val1", key2 = "val2", "val3" }
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end

tab1.key1 = nil
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end



















