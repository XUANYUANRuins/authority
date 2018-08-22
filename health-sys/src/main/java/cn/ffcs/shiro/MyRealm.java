package cn.ffcs.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import cn.ffcs.model.TbUser;
import cn.ffcs.service.TbUserService;
import cn.ffcs.util.SysConstants;

public class MyRealm extends AuthorizingRealm {
	
	@Resource
	protected TbUserService tbUserService;
	
	public MyRealm() {
		super();
	}
	
	/**
     * 为当前登陆的用户授予角色和权限
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
     * 对前登陆的用户进行身份认证
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	    // 获取前台的登录名   
		String loginname = (String) token.getPrincipal(); 	
        
		// 根据登录名从数据库中查询出信息    
		TbUser tbUser = tbUserService.selectByLoginname(loginname);
		
		if( tbUser == null ) {
		    //账号或者密码输入错误
           throw new UnknownAccountException();
		}
		
		if( tbUser.getStatus() == SysConstants.OFF ) {
		    // 账号已被停用
            throw new LockedAccountException();
		}
		
		Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        
        
        //设置User到Session
        session.setAttribute(SysConstants.SESSION_USER, tbUser);
		
		//把从数据库中查询出来的信息放到authcInfo中返回给Shiro
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
        		tbUser, tbUser.getPassword(), loginname);
        return authcInfo;
	}


	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

}
