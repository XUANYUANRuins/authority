package cn.ffcs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_circle_info")
public class TbCircle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer reportid;
    
    private double centerx_left1;
    
    private double centery_left1;
    
    private double radius_left1;
    
    private double centerx_right1;
    
    private double centery_right1;
    
    private double radius_right1;
    
    private double centerx_left2;
    
    private double centery_left2;
    
    private double radius_left2;
    
    private double centerx_right2;
    
    private double centery_right2;
    
    private double radius_right2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReportid() {
		return reportid;
	}

	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}

	public double getCenterx_left1() {
		return centerx_left1;
	}

	public void setCenterx_left1(double centerx_left1) {
		this.centerx_left1 = centerx_left1;
	}

	public double getCentery_left1() {
		return centery_left1;
	}

	public void setCentery_left1(double centery_left1) {
		this.centery_left1 = centery_left1;
	}

	public double getRadius_left1() {
		return radius_left1;
	}

	public void setRadius_left1(double radius_left1) {
		this.radius_left1 = radius_left1;
	}

	public double getCenterx_right1() {
		return centerx_right1;
	}

	public void setCenterx_right1(double centerx_right1) {
		this.centerx_right1 = centerx_right1;
	}

	public double getCentery_right1() {
		return centery_right1;
	}

	public void setCentery_right1(double centery_right1) {
		this.centery_right1 = centery_right1;
	}

	public double getRadius_right1() {
		return radius_right1;
	}

	public void setRadius_right1(double radius_right1) {
		this.radius_right1 = radius_right1;
	}

	public double getCenterx_left2() {
		return centerx_left2;
	}

	public void setCenterx_left2(double centerx_left2) {
		this.centerx_left2 = centerx_left2;
	}

	public double getCentery_left2() {
		return centery_left2;
	}

	public void setCentery_left2(double centery_left2) {
		this.centery_left2 = centery_left2;
	}

	public double getRadius_left2() {
		return radius_left2;
	}

	public void setRadius_left2(double radius_left2) {
		this.radius_left2 = radius_left2;
	}

	public double getCenterx_right2() {
		return centerx_right2;
	}

	public void setCenterx_right2(double centerx_right2) {
		this.centerx_right2 = centerx_right2;
	}

	public double getCentery_right2() {
		return centery_right2;
	}

	public void setCentery_right2(double centery_right2) {
		this.centery_right2 = centery_right2;
	}

	public double getRadius_right2() {
		return radius_right2;
	}

	public void setRadius_right2(double radius_right2) {
		this.radius_right2 = radius_right2;
	}
}