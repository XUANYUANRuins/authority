package cn.ffcs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_operate_log")
public class TbOperateLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
    private Long logId;
	
    /**
     * 登录名
     */
    @Column(name = "user_name")
    private String userName;
    
    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 登录IP
     */
    private String ip;

    /**
     * 登录结果:0成功、1密码错误、2用户不存在、3用户状态异常
     */
    private String result;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private String loginTime;
    
    /**
     * 操作内容
     */
    @Column(name = "operate_content")
    private String operateContent;
    
    /**
     * 操作时间
     */
    @Column(name = "operate_time")
    private String operateTime;

	/**
	 * @return the logId
	 */
	public Long getLogId() {
		return logId;
	}

	/**
	 * @param logId the logId to set
	 */
	public void setLogId(Long logId) {
		this.logId = logId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the loginTime
	 */
	public String getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the operateContent
	 */
	public String getOperateContent() {
		return operateContent;
	}

	/**
	 * @param operateContent the operateContent to set
	 */
	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	/**
	 * @return the operateTime
	 */
	public String getOperateTime() {
		return operateTime;
	}

	/**
	 * @param operateTime the operateTime to set
	 */
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TbOperateLog [logId=" + logId + ", userName=" + userName + ", realName=" + realName + ", ip=" + ip
				+ ", result=" + result + ", loginTime=" + loginTime + ", operateContent=" + operateContent
				+ ", operateTime=" + operateTime + "]";
	}
}