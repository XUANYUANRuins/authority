package cn.ffcs.controller;

import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import cn.ffcs.model.TbUser;

public class UserOnlineListener implements HttpSessionListener {
	private Logger logger = Logger.getLogger(this.getClass()); 

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		//在session销毁的时候 把loginUserMap中保存的键值对清除 
		TbUser tbUser = (TbUser)event.getSession().getAttribute("loginUser"); 
		if(tbUser!=null){ 
			Map<String, String> loginUserMap = (Map<String, String>)event.getSession().getServletContext().getAttribute("loginUserMap"); 
			loginUserMap.remove(tbUser.getLoginname()); 
			event.getSession().getServletContext().setAttribute("loginUserMap",loginUserMap); 
		}
		
	}
}

