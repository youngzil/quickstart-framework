https://www.lua.org/
https://www.lua.org/download.html
https://github.com/lua/lua


Linux 系统上安装
curl -R -O http://www.lua.org/ftp/lua-5.3.5.tar.gz
tar zxf lua-5.3.5.tar.gz
cd lua-5.3.5
make linux test
make install


Mac OS X 系统上安装
curl -R -O http://www.lua.org/ftp/lua-5.3.5.tar.gz
tar zxf lua-5.3.5.tar.gz
cd lua-5.3.5
make macosx test
make install

或者
brew update
brew install lua


lua -v



参考
https://www.runoob.com/lua/lua-environment.html

