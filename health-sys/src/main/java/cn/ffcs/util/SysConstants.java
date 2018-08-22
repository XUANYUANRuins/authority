package cn.ffcs.util;

public class SysConstants {
	
    /** Session相关 */
    public final static String SESSION_USER = "currentUser";
    public final static String SESSION_MENUS = "menus";
    public final static String SESSION_STATUS = "status";
    
	
	/** 状态*/
	public final static int ON = 0;    //启用
	public final static int OFF = 1;   //停用
	
	/** 是否 */
	public final static int TRUE = 1;  //是
	public final static int FALSE = 0; //否
	
	/** 分页相关 */
	public final static int PAGE_START = 1;	//起止位置
	public final static int PAGE_LENGTH = 10;	//数据长度
	public final static int PAGE_NUM = 1;	//当前页码
	public final static int PAGE_SIZE = 10;	//每页数据
	
	/** 诊断状态相关 */
	public final static int STATUS_RETURN = -2;	//图片被退回
	public final static int STATUS_SUBMIT = -1;	//图片远程会诊中
	public final static int STATUS_MACHINE = 0;	//机器自动诊断中
	public final static int STATUS_DOCTOR_WAIT = 1;	//待远程医生确认
	public final static int STATUS_DOCTOR_ON = 2;	//诊断完成
	
	/** 报告状态相关 */
	public final static int PORT_STATUS0 = 0;	//0 报告初始
	public final static int PORT_STATUS1 = 1;	//1基层上传给远程
	public final static int PORT_STATUS2 = 2;	//2远程上传给AI
	public final static int PORT_STATUS3 = 3;	//3AI返回
	public final static int PORT_STATUS4 = -1;	//-1远程返回给基层
	
}
