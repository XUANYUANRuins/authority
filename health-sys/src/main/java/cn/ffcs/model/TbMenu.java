package cn.ffcs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_menu")
public class TbMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parentId")
    private Long parentid;

    private String name;

    private String url;

    private String icon;

    /**
     * 序列号
     */
    private Integer sequence;

    /**
     * 状态：0-启用；1-停用
     */
    private Integer status;

    @Column(name = "createTime")
    private Date createtime;

    @Column(name = "updateTime")
    private Date updatetime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return parentId
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取序列号
     *
     * @return sequence - 序列号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置序列号
     *
     * @param sequence 序列号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取状态：0-启用；1-停用
     *
     * @return status - 状态：0-启用；1-停用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0-启用；1-停用
     *
     * @param status 状态：0-启用；1-停用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return createTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return updateTime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}