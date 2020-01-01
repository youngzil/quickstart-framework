
1、加载web资源的文件
方式一：采用ServletContext读取，读取配置文件的realpath，然后通过文件流读取出来。


2、只能加载类classes下面的资源文件
采用ResourceBundle类读取配置信息：只能读取.properties文件
采用ClassLoader方式进行读取配置信息，根目录为相对目录
class的getResouceAsStream：class文件目录为相对目录


3、文件IO
FileInputStream(new File(config))，根目录为相对目录






