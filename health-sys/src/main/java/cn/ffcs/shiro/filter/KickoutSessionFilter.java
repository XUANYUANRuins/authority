package cn.ffcs.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ffcs.model.TbUser;
import cn.ffcs.shiro.cache.VCache;
import cn.ffcs.shiro.session.ShiroSessionRepository;
import cn.ffcs.shiro.token.manager.TokenManager;
import cn.ffcs.util.LoggerUtils;
import cn.ffcs.util.StringUtils;
import cn.ffcs.util.SysConstants;

/**
 * 
 * 开发公司：SOJSON在线工具 <p>
 * 版权所有：© www.sojson.com<p>
 * 博客地址：http://www.sojson.com/blog/  <p>
 * <p>
 * 
 * 相同帐号登录控制
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
@SuppressWarnings({"unchecked","static-access"})
public class KickoutSessionFilter extends AccessControlFilter {
	@Autowired
    private SessionDAO sessionDAO;
	
	//静态注入
	static String kickoutUrl;
	//在线用户
	final static String ONLINE_USER = KickoutSessionFilter.class.getCanonicalName()+ "_online_user";
	//踢出状态，true标示踢出
	final static String KICKOUT_STATUS = KickoutSessionFilter.class.getCanonicalName()+ "_kickout_status";
	static VCache cache;
	
	//session获取
	static ShiroSessionRepository shiroSessionRepository;
	
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session currSession = subject.getSession();
		Boolean marker = (Boolean)currSession.getAttribute(SysConstants.SESSION_STATUS);
		if (null != marker && !marker ) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		subject.logout();
		HttpServletResponse httpResponse = (HttpServletResponse) (response);
		httpResponse.sendRedirect("/health-web/login.jsp?kickout=1");
        return false;
	}

	private void out(ServletResponse hresponse, Map<String, String> resultMap)
			throws IOException {
		try {
			hresponse.setCharacterEncoding("UTF-8");
			PrintWriter out = hresponse.getWriter();
			out.println(JSONObject.fromObject(resultMap).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			LoggerUtils.error(getClass(), "KickoutSessionFilter.class 输出JSON异常，可以忽略。");
		}
	}
	
	public static void setShiroSessionRepository(
			ShiroSessionRepository shiroSessionRepository) {
		KickoutSessionFilter.shiroSessionRepository = shiroSessionRepository;
	}

	public static String getKickoutUrl() {
		return kickoutUrl;
	}

	public static void setKickoutUrl(String kickoutUrl) {
		KickoutSessionFilter.kickoutUrl = kickoutUrl;
	}
}
