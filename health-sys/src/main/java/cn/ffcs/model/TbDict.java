package cn.ffcs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_dict")
public class TbDict {
    /**
     * 字典主键 无逻辑意义
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父id
     */
    @Column(name = "parentId")
    private Long parentid;

    /**
     * 字典代码
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 状态：0-启用；1-停用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    @Column(name = "updateTime")
    private Date updatetime;

    /**
     * 获取字典主键 无逻辑意义
     *
     * @return id - 字典主键 无逻辑意义
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置字典主键 无逻辑意义
     *
     * @param id 字典主键 无逻辑意义
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取父id
     *
     * @return parentId - 父id
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * 设置父id
     *
     * @param parentid 父id
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取字典代码
     *
     * @return code - 字典代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置字典代码
     *
     * @param code 字典代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取字典名称
     *
     * @return name - 字典名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字典名称
     *
     * @param name 字典名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取排序
     *
     * @return sequence - 排序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置排序
     *
     * @param sequence 排序
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
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
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