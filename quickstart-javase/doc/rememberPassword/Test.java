/**
 * 项目名称：quickstart-javaweb 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年11月20日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package rememberPassword;

import javax.servlet.http.HttpServlet;

/**
 * Test
 * 
 * @author：yangzl@asiainfo.com
 * @2017年11月20日 下午5:43:18
 * @since 1.0
 */
public class Test extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 用户名
        String name = request.getParameter("userName");
        // 密码
        String passWord = request.getParameter("pwd");

        // 记住用户名、密码功能(注意：cookie存放密码会存在安全隐患)
        String remFlag = request.getParameter("remFlag");
        if ("1".equals(remFlag)) { // "1"表示用户勾选记住密码
            /*String cookieUserName = Utils.encrypt(name);
            String cookiePwd = Utils.encrypt(passWord);
            String loginInfo = cookieUserName+","+cookiePwd;*/
            String loginInfo = name + "," + passWord;
            Cookie userCookie = new Cookie("loginInfo", loginInfo);

            userCookie.setMaxAge(30 * 24 * 60 * 60); // 存活期为一个月 30*24*60*60
            userCookie.setPath("/");
            response.addCookie(userCookie);
        }

    }

}
