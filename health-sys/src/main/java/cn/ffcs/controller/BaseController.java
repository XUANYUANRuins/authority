package cn.ffcs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.WebDataBinder;  
import org.springframework.web.bind.annotation.InitBinder;

import cn.ffcs.model.TbUser;
import cn.ffcs.util.StringEscapeEditor;  
  
@Controller  
public class BaseController {
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    // 保存当前登录的所有用户  
    public static Map<String, HttpSession> loginUser = new HashMap<String, HttpSession>();  
  
    // 用这个作为session中的key  
    public static String SESSION_LOGIN_NAME = "user";
    
    @Autowired  
    protected HttpServletRequest request;  
    @Autowired  
    protected HttpServletResponse response;  
    @Autowired  
    protected HttpSession session;  
    @Autowired  
    protected ServletContext application;
    
    // 日期、时间格式
    public static String dataFormat = "yyyy-MM-dd";
    public static String dataTimeFormat = "yyyy-MM-dd HH:mm";
    public static String timeFormat = "yyyy-MM-dd HH:mm:ss";
  
    /**
     * 解决出现下面的问题:界面上传过来的是字符串类型,当用对象接受时并且是Date类型的数据则会发生以下的错误 Cannot convert value
     * of type [java.lang.String] to required type [java.util.Date]
     * 
     * @param binder
     */
    @InitBinder  
    protected void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat(dataTimeFormat);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
        
        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }
    
}  
