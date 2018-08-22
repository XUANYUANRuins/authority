package cn.ffcs.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.ffcs.model.TbUser;



/**
 * shiro工具类
 */
public class ShiroUtils {
	
    public static TbUser getCurUser() {
        Subject subject = SecurityUtils.getSubject();
        Object obj = subject.getSession().getAttribute(SysConstants.SESSION_USER);
        if(obj==null){
            return new TbUser();
        } else {
        	TbUser tbUser = (TbUser)obj;
            return tbUser;
        }
    }
}

