在Eclipse中使用PyDev发现无法进行代码格式化，使用通常的"Ctrl+Shift+F"快捷键，没有任何变化，难道不能对python代码格式化了吗？

通过下面设置，就可以每次保存文件时候自动格式化了，如果当前文件没有变化，你也想格式化一下，那么你随意修改一下文件任意地方，随后保存一下，就会自动格式化！

1，设置PyDev的Editor的code style的Code Formatter。选中右面的use autopep8.py for code formatting
2，设置PyDev的Editor的Save Actions。Auto-format editor contents before saving

设置完上面的，保存代码自动格式化


