package cn.ffcs.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_role")
public class TbRole implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名
     */
    private String name;
    
    /**
     * 角色编号
     */
    @Column(name = "role_code")
    private String roleCode;
    
    /**
     * 权限ID
     */
    @Column(name = "menuIds")
    private String menuids;

    /**
     * 状态：0-启用；1-停用
     */
    private Integer status;
    
    /**
     * 角色描述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime")
    private Date updatetime;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return the menuids
	 */
	public String getMenuids() {
		return menuids;
	}

	/**
	 * @param menuids the menuids to set
	 */
	public void setMenuids(String menuids) {
		this.menuids = menuids;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
		return "TbRole [id=" + id + ", name=" + name + ", roleCode=" + roleCode + ", menuids=" + menuids + ", status="
				+ status + ", description=" + description + ", createtime=" + createtime + ", updatetime=" + updatetime
				+ "]";
	}
}