package cn.ffcs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_report")
public class TbReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 左眼带标记图片
     */
    private String leftsignpic;

    /**
     * 右眼带标记图片
     */
    private String rightsignpic;

    /**
     * 机器诊断状态
     */
    private Integer initstatus;

    /**
     * 诊断状态
     */
    private Integer status;

    /**
     * 筛查结果
     */
    private Integer normal;

    /**
     * 左眼特征
     */
    private String leftfeature;

    /**
     * 右眼特征
     */
    private String rightfeature;

    /**
     * 左眼诊断结果（选项）
     */
    private String leftresultopt;

    /**
     * 左眼诊断结果（其他）
     */
    private String leftresult;

    /**
     * 右眼诊断结果（选项）
     */
    private String rightresultopt;

    /**
     * 右眼诊断结果（其他）
     */
    private String rightresult;

    /**
     * 医生诊断结果（选项）
     */
    private String doctorresultopt;

    /**
     * 医生诊断结果（其他）
     */
    private String doctorresult;

    /**
     * 医生id
     */
    @Column(name = "doctorId")
    private Integer doctorid;

    /**
     * 生成时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    private Integer healthinfoid;

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
     * 获取左眼带标记图片
     *
     * @return leftsignpic - 左眼带标记图片
     */
    public String getLeftsignpic() {
        return leftsignpic;
    }

    /**
     * 设置左眼带标记图片
     *
     * @param leftsignpic 左眼带标记图片
     */
    public void setLeftsignpic(String leftsignpic) {
        this.leftsignpic = leftsignpic;
    }

    /**
     * 获取右眼带标记图片
     *
     * @return rightsignpic - 右眼带标记图片
     */
    public String getRightsignpic() {
        return rightsignpic;
    }

    /**
     * 设置右眼带标记图片
     *
     * @param rightsignpic 右眼带标记图片
     */
    public void setRightsignpic(String rightsignpic) {
        this.rightsignpic = rightsignpic;
    }

    /**
     * 获取机器诊断状态
     *
     * @return initstatus - 机器诊断状态
     */
    public Integer getInitstatus() {
        return initstatus;
    }

    /**
     * 设置机器诊断状态
     *
     * @param initstatus 机器诊断状态
     */
    public void setInitstatus(Integer initstatus) {
        this.initstatus = initstatus;
    }

    /**
     * 获取诊断状态
     *
     * @return status - 诊断状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置诊断状态
     *
     * @param status 诊断状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取筛查结果
     *
     * @return normal - 筛查结果
     */
    public Integer getNormal() {
        return normal;
    }

    /**
     * 设置筛查结果
     *
     * @param normal 筛查结果
     */
    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    /**
     * 获取左眼特征
     *
     * @return leftfeature - 左眼特征
     */
    public String getLeftfeature() {
        return leftfeature;
    }

    /**
     * 设置左眼特征
     *
     * @param leftfeature 左眼特征
     */
    public void setLeftfeature(String leftfeature) {
        this.leftfeature = leftfeature;
    }

    /**
     * 获取右眼特征
     *
     * @return rightfeature - 右眼特征
     */
    public String getRightfeature() {
        return rightfeature;
    }

    /**
     * 设置右眼特征
     *
     * @param rightfeature 右眼特征
     */
    public void setRightfeature(String rightfeature) {
        this.rightfeature = rightfeature;
    }

    /**
     * 获取左眼诊断结果（选项）
     *
     * @return leftresultopt - 左眼诊断结果（选项）
     */
    public String getLeftresultopt() {
        return leftresultopt;
    }

    /**
     * 设置左眼诊断结果（选项）
     *
     * @param leftresultopt 左眼诊断结果（选项）
     */
    public void setLeftresultopt(String leftresultopt) {
        this.leftresultopt = leftresultopt;
    }

    /**
     * 获取左眼诊断结果（其他）
     *
     * @return leftresult - 左眼诊断结果（其他）
     */
    public String getLeftresult() {
        return leftresult;
    }

    /**
     * 设置左眼诊断结果（其他）
     *
     * @param leftresult 左眼诊断结果（其他）
     */
    public void setLeftresult(String leftresult) {
        this.leftresult = leftresult;
    }

    /**
     * 获取右眼诊断结果（选项）
     *
     * @return rightresultopt - 右眼诊断结果（选项）
     */
    public String getRightresultopt() {
        return rightresultopt;
    }

    /**
     * 设置右眼诊断结果（选项）
     *
     * @param rightresultopt 右眼诊断结果（选项）
     */
    public void setRightresultopt(String rightresultopt) {
        this.rightresultopt = rightresultopt;
    }

    /**
     * 获取右眼诊断结果（其他）
     *
     * @return rightresult - 右眼诊断结果（其他）
     */
    public String getRightresult() {
        return rightresult;
    }

    /**
     * 设置右眼诊断结果（其他）
     *
     * @param rightresult 右眼诊断结果（其他）
     */
    public void setRightresult(String rightresult) {
        this.rightresult = rightresult;
    }

    /**
     * 获取医生诊断结果（选项）
     *
     * @return doctorresultopt - 医生诊断结果（选项）
     */
    public String getDoctorresultopt() {
        return doctorresultopt;
    }

    /**
     * 设置医生诊断结果（选项）
     *
     * @param doctorresultopt 医生诊断结果（选项）
     */
    public void setDoctorresultopt(String doctorresultopt) {
        this.doctorresultopt = doctorresultopt;
    }

    /**
     * 获取医生诊断结果（其他）
     *
     * @return doctorresult - 医生诊断结果（其他）
     */
    public String getDoctorresult() {
        return doctorresult;
    }

    /**
     * 设置医生诊断结果（其他）
     *
     * @param doctorresult 医生诊断结果（其他）
     */
    public void setDoctorresult(String doctorresult) {
        this.doctorresult = doctorresult;
    }

    /**
     * 获取医生id
     *
     * @return doctorId - 医生id
     */
    public Integer getDoctorid() {
        return doctorid;
    }

    /**
     * 设置医生id
     *
     * @param doctorid 医生id
     */
    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    /**
     * 获取生成时间
     *
     * @return createtime - 生成时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置生成时间
     *
     * @param createtime 生成时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取修改时间
     *
     * @return updatetime - 修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updatetime 修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return healthinfoid
     */
    public Integer getHealthinfoid() {
        return healthinfoid;
    }

    /**
     * @param healthinfoid
     */
    public void setHealthinfoid(Integer healthinfoid) {
        this.healthinfoid = healthinfoid;
    }
}