package cn.ffcs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_terminal_info")
public class TbTerminal implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    /**
     * 终端编号
     */
    @Column(name = "trml_id")
    private String trmlId;

    /**
     * 所属机构编号  全0表示会诊中心
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 相机品牌
     */
    @Column(name = "camera_brand")
    private String cameraBrand;

    /**
     * 相机型号
     */
    @Column(name = "camera_model")
    private String cameraModel;
    
    /**
     * 生产地
     */
    @Column(name = "camera_producer")
    private String cameraProducer;
    
    /**
     * 生产时间
     */
    @Column(name = "camera_produce_time")
    private String cameraProduceTime;

    /**
     * 购置时间
     */
    @Column(name = "camera_buy_time")
    private String cameraBuyTime;
    
    /**
     * 中心分辨率
     */
    @Column(name = "central_resolution")
    private Integer centralResolution;
    
    /**
     * 视角
     */
    @Column(name = "camera_angle")
    private Integer cameraAngle;
    
    /**
     * CCD分辨率
     */
    @Column(name = "ccd_resolution")
    private Integer ccdResolution;
    
    /**
     * 终端IP
     */
    @Column(name = "trml_ip")
    private String trmlIp;
    
    /**
     * 终端端口
     */
    @Column(name = "trml_port")
    private Integer trmlPort;

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
	 * @return the trmlId
	 */
	public String getTrmlId() {
		return trmlId;
	}

	/**
	 * @param trmlId the trmlId to set
	 */
	public void setTrmlId(String trmlId) {
		this.trmlId = trmlId;
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
	 * @return the cameraBrand
	 */
	public String getCameraBrand() {
		return cameraBrand;
	}

	/**
	 * @param cameraBrand the cameraBrand to set
	 */
	public void setCameraBrand(String cameraBrand) {
		this.cameraBrand = cameraBrand;
	}

	/**
	 * @return the cameraModel
	 */
	public String getCameraModel() {
		return cameraModel;
	}

	/**
	 * @param cameraModel the cameraModel to set
	 */
	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}

	/**
	 * @return the cameraProducer
	 */
	public String getCameraProducer() {
		return cameraProducer;
	}

	/**
	 * @param cameraProducer the cameraProducer to set
	 */
	public void setCameraProducer(String cameraProducer) {
		this.cameraProducer = cameraProducer;
	}

	/**
	 * @return the cameraProduceTime
	 */
	public String getCameraProduceTime() {
		return cameraProduceTime;
	}

	/**
	 * @param cameraProduceTime the cameraProduceTime to set
	 */
	public void setCameraProduceTime(String cameraProduceTime) {
		this.cameraProduceTime = cameraProduceTime;
	}

	/**
	 * @return the cameraBuyTime
	 */
	public String getCameraBuyTime() {
		return cameraBuyTime;
	}

	/**
	 * @param cameraBuyTime the cameraBuyTime to set
	 */
	public void setCameraBuyTime(String cameraBuyTime) {
		this.cameraBuyTime = cameraBuyTime;
	}

	/**
	 * @return the centralResolution
	 */
	public Integer getCentralResolution() {
		return centralResolution;
	}

	/**
	 * @param centralResolution the centralResolution to set
	 */
	public void setCentralResolution(Integer centralResolution) {
		this.centralResolution = centralResolution;
	}

	/**
	 * @return the cameraAngle
	 */
	public Integer getCameraAngle() {
		return cameraAngle;
	}

	/**
	 * @param cameraAngle the cameraAngle to set
	 */
	public void setCameraAngle(Integer cameraAngle) {
		this.cameraAngle = cameraAngle;
	}

	/**
	 * @return the ccdResolution
	 */
	public Integer getCcdResolution() {
		return ccdResolution;
	}

	/**
	 * @param ccdResolution the ccdResolution to set
	 */
	public void setCcdResolution(Integer ccdResolution) {
		this.ccdResolution = ccdResolution;
	}

	/**
	 * @return the trmlIp
	 */
	public String getTrmlIp() {
		return trmlIp;
	}

	/**
	 * @param trmlIp the trmlIp to set
	 */
	public void setTrmlIp(String trmlIp) {
		this.trmlIp = trmlIp;
	}

	/**
	 * @return the trmlPort
	 */
	public Integer getTrmlPort() {
		return trmlPort;
	}

	/**
	 * @param trmlPort the trmlPort to set
	 */
	public void setTrmlPort(Integer trmlPort) {
		this.trmlPort = trmlPort;
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
		return "TbTerminal [id=" + id + ", trmlId=" + trmlId + ", orgId=" + orgId + ", cameraBrand=" + cameraBrand
				+ ", cameraModel=" + cameraModel + ", cameraProducer=" + cameraProducer + ", cameraProduceTime="
				+ cameraProduceTime + ", cameraBuyTime=" + cameraBuyTime + ", centralResolution=" + centralResolution
				+ ", cameraAngle=" + cameraAngle + ", ccdResolution=" + ccdResolution + ", trmlIp=" + trmlIp
				+ ", trmlPort=" + trmlPort + "]";
	}
}