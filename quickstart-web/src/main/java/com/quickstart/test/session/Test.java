package com.quickstart.test.session;

public class Test {

    public static void main(String[] args) {
		//
		登录
		synchronized(信号量)
		{
		 //验证隐藏的验证码，同时删除随机验证码，避免重复登录
		}
		//１、此处先得到cookie中已有的会话ID，以便登录后清除memached中的会话信息;
		//２、清除前一个登录的会话信息

		String sid = java.util.UUID.randomUUID().toString()+serverName+"_"+userId;
		//加入serverName，是为了区分多台服务器产生的randomUUID可能相同
		  RequestUtils.setCookie(request, response, "sid", sid, 30*60); 
		  
		  SessionUtil.setAttribute(sid, "user_session_flag", userInfo);
		 
		//退出
		//删除会话cookie同时删除memcached中的会话信息
		SessionUtil.invalidate(request);
	}

}
