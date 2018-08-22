package cn.ffcs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_base_info")
public class TbBaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 身份证号（必填）
     */
    private String idcard;

    /**
     * 姓名（必填）
     */
    private String name;

    /**
     * 性别（选择，默认为男）
     */
    private Integer sex;

    /**
     * 出生日期（身份证号填入后会自动生成）
     */
    private Date birthday;

    /**
     * 手机号（必填）
     */
    private String tel;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 监护人/联系人
     */
    private String guardian;

    /**
     * 监护人手机号
     */
    private String guardianphone;

    /**
     * 所来自医院（选择，或根据登录的帐号自动关联）
     */
    private String hospital;

    /**
     * 主诊医生（根据登录帐号自动关联）
     */
    private String doctor;

    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 创建人id
     */
    private String createid;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取身份证号（必填）
     *
     * @return idcard - 身份证号（必填）
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证号（必填）
     *
     * @param idcard 身份证号（必填）
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * 获取姓名（必填）
     *
     * @return name - 姓名（必填）
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名（必填）
     *
     * @param name 姓名（必填）
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别（选择，默认为男）
     *
     * @return sex - 性别（选择，默认为男）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别（选择，默认为男）
     *
     * @param sex 性别（选择，默认为男）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取出生日期（身份证号填入后会自动生成）
     *
     * @return birthday - 出生日期（身份证号填入后会自动生成）
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期（身份证号填入后会自动生成）
     *
     * @param birthday 出生日期（身份证号填入后会自动生成）
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取手机号（必填）
     *
     * @return tel - 手机号（必填）
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置手机号（必填）
     *
     * @param tel 手机号（必填）
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取家庭住址
     *
     * @return address - 家庭住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置家庭住址
     *
     * @param address 家庭住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取监护人/联系人
     *
     * @return guardian - 监护人/联系人
     */
    public String getGuardian() {
        return guardian;
    }

    /**
     * 设置监护人/联系人
     *
     * @param guardian 监护人/联系人
     */
    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    /**
     * 获取监护人手机号
     *
     * @return guardianphone - 监护人手机号
     */
    public String getGuardianphone() {
        return guardianphone;
    }

    /**
     * 设置监护人手机号
     *
     * @param guardianphone 监护人手机号
     */
    public void setGuardianphone(String guardianphone) {
        this.guardianphone = guardianphone;
    }

    /**
     * 获取所来自医院（选择，或根据登录的帐号自动关联）
     *
     * @return hospital - 所来自医院（选择，或根据登录的帐号自动关联）
     */
    public String getHospital() {
        return hospital;
    }

    /**
     * 设置所来自医院（选择，或根据登录的帐号自动关联）
     *
     * @param hospital 所来自医院（选择，或根据登录的帐号自动关联）
     */
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    /**
     * 获取主诊医生（根据登录帐号自动关联）
     *
     * @return doctor - 主诊医生（根据登录帐号自动关联）
     */
    public String getDoctor() {
        return doctor;
    }

    /**
     * 设置主诊医生（根据登录帐号自动关联）
     *
     * @param doctor 主诊医生（根据登录帐号自动关联）
     */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateid() {
		return createid;
	}

	public void setCreateid(String createid) {
		this.createid = createid;
	}
   
}