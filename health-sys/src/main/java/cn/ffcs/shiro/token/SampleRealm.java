package cn.ffcs.shiro.token;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ffcs.model.TbUser;
import cn.ffcs.service.TbRoleService;
import cn.ffcs.service.TbUserService;
import cn.ffcs.shiro.token.manager.TokenManager;
import cn.ffcs.util.SysConstants;


/**
 * 
 * 开发公司：SOJSON在线工具 <p>
 * 版权所有：© www.sojson.com<p>
 * 博客地址：http://www.sojson.com/blog/  <p>
 * <p>
 * 
 * shiro 认证 + 授权   重写
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年6月2日 　<br/>
 *
 * @author zhou-baicheng
 * @email  so@sojson.com
 * @version 1.0,2016年6月2日 <br/>
 * 
 */
public class SampleRealm extends AuthorizingRealm {

	@Resource
	protected TbUserService tbUserService;
	
	public SampleRealm() {
		super();
	}
	/**
	 *  认证信息，主要针对用户登录， 
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
        		tbUser,tbUser.getPassword(), loginname);
        return authcInfo;
    }

	 /** 
     * 授权 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
    	
    	// TODO Auto-generated method stub
    	return null;
    }  
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
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
