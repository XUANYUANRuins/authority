package cn.ffcs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_organization")
public class TbOrganization implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 机构编号  全0表示会诊中心
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 机构名称
     */
    @Column(name = "org_name")
    private String orgName;
    
    
    /**
     * 状态 1有效、2无效
     */
    private Integer status;

    /**
     * 机构详细地址
     */
    @Column(name = "org_address")
    private String orgAddress;
    
    /**
     * 固定电话
     */
    @Column(name = "fixed_telephone")
    private String fixedTelephone;
    
    /**
     * 机构电话
     */
    @Column(name = "org_phone")
    private String orgPhone;
    
    /**
     * 联系人
     */
    private String linkman;

    /**
     * 登记时间
     */
    @Column(name = "insert_date")
    private Date insertDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

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
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	 * @return the orgAddress
	 */
	public String getOrgAddress() {
		return orgAddress;
	}

	/**
	 * @param orgAddress the orgAddress to set
	 */
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	/**
	 * @return the fixedTelephone
	 */
	public String getFixedTelephone() {
		return fixedTelephone;
	}

	/**
	 * @param fixedTelephone the fixedTelephone to set
	 */
	public void setFixedTelephone(String fixedTelephone) {
		this.fixedTelephone = fixedTelephone;
	}

	/**
	 * @return the orgPhone
	 */
	public String getOrgPhone() {
		return orgPhone;
	}

	/**
	 * @param orgPhone the orgPhone to set
	 */
	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	/**
	 * @return the linkman
	 */
	public String getLinkman() {
		return linkman;
	}

	/**
	 * @param linkman the linkman to set
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
		return "TbOrganization [id=" + id + ", orgId=" + orgId + ", orgName=" + orgName + ", status=" + status
				+ ", orgAddress=" + orgAddress + ", fixedTelephone=" + fixedTelephone + ", orgPhone=" + orgPhone
				+ ", linkman=" + linkman + ", insertDate=" + insertDate + ", updateDate=" + updateDate + "]";
	}
}