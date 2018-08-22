package cn.ffcs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_user")
public class TbUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 帐号
     */
    @Column(name = "loginName")
    private String loginname;

    /**
     * 密码
     */
    private String password;

    /**
     * 正式名称
     */
    @Column(name = "realName")
    private String realname;

    /**
     * 职位
     */
    private String position;
    
    /**
     * 角色ID
     */
    @Column(name = "roleIds")
    private String roleids;
    
    /**
     * 状态：0-启用；1-停用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 用户密码修改时间
     */
    @Column(name = "updateTime")
    private Date updatetime;
    
    /**
     * 登陆状态（0：不在线 1：在线）
     */
    private Integer islogin;
    
    /**
     * 上一次登录时间
     */
    @Column(name = "last_login_time")
    private String lastLoginTime;
    
    /**
     * 角色ID
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;
    
    /**
     * 登陆状态（0：不在线 1：在线）
     */
    @Column(name = "login_qty")
    private Integer loginQty;
    
    /**
     * 状态，0：冻结、1：正常
     */
    private Integer enable;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 机构
     */
    @Column(name = "org_id")
    private String orgId;
    
    /**
     * 操作设备ID
     */
    @Column(name = "terminalIds")
    private String terminalids;
    
    
    public TbUser() {}
    public TbUser(TbUser user) {
		this.id = user.getId();
		this.loginname = user.getLoginname();
		this.realname = user.getRealname();
		this.password = user.getPassword();
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the loginname
	 */
	public String getLoginname() {
		return loginname;
	}
	/**
	 * @param loginname the loginname to set
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the roleids
	 */
	public String getRoleids() {
		return roleids;
	}
	/**
	 * @param roleids the roleids to set
	 */
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the updatetime
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * @return the islogin
	 */
	public Integer getIslogin() {
		return islogin;
	}
	/**
	 * @param islogin the islogin to set
	 */
	public void setIslogin(Integer islogin) {
		this.islogin = islogin;
	}
	/**
	 * @return the lastLoginTime
	 */
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * @return the lastLoginIp
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 * @param lastLoginIp the lastLoginIp to set
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 * @return the loginQty
	 */
	public Integer getLoginQty() {
		return loginQty;
	}
	/**
	 * @param loginQty the loginQty to set
	 */
	public void setLoginQty(Integer loginQty) {
		this.loginQty = loginQty;
	}
	/**
	 * @return the enable
	 */
	public Integer getEnable() {
		return enable;
	}
	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the terminalids
	 */
	public String getTerminalids() {
		return terminalids;
	}
	/**
	 * @param terminalids the terminalids to set
	 */
	public void setTerminalids(String terminalids) {
		this.terminalids = terminalids;
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
		return "TbUser [id=" + id + ", loginname=" + loginname + ", password=" + password + ", realname=" + realname
				+ ", position=" + position + ", roleids=" + roleids + ", status=" + status + ", createtime="
				+ createtime + ", updatetime=" + updatetime + ", islogin=" + islogin + ", lastLoginTime="
				+ lastLoginTime + ", lastLoginIp=" + lastLoginIp + ", loginQty=" + loginQty + ", enable=" + enable
				+ ", phone=" + phone + ", email=" + email + ", orgId=" + orgId + ", terminalids=" + terminalids + "]";
	}
	
}