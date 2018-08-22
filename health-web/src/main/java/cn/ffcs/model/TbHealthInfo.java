package cn.ffcs.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_health_info")
public class TbHealthInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 身高（cm为单位，录入 ，必填）
     */
    private Integer height;

    /**
     * 体重（kg为单位，录入，必填）
     */
    private Integer weight;

    /**
     * 体重/身高(米平方)
     */
    private String bmi;

    /**
     * 腰围（cm，录入）
     */
    private String waist;

    /**
     * 性别（若为女性，需选择是否绝经）
     */
    private Integer menopause;

    /**
     * 血压（高压/低压格式输入）
     */
    private Integer bloodpressure;
    
    /**
     * 血压值
     */
    private String pressure;

    /**
     * 目前健康状况（选择）：良好、一般、较差
     */
    private Integer state;

    /**
     * 体力劳动或体育运动锻炼（选择）：基本不运动，每月1-2次，一周1-2次，每天运动
     */
    private Integer labour;

    /**
     * 水果蔬菜摄入情况（选择）：无、偶尔吃、经常吃、每天都吃
     */
    private Integer fruit;

    /**
     * 是否经常性饮酒（选择）：从不、偶尔、一周1-2次、每天饮超过1瓶啤酒
     */
    private Integer drink;

    /**
     * 糖尿病史：默认为空
     */
    private Integer diabetes;

    /**
     * 若选择有，则可以选择确诊年份（可选）
     */
    private Date diagnose1;

    /**
     * 若选择有，则选择直系亲属或亲戚是否有糖尿病（必选）
     */
    private Integer relatives;

    /**
     * 青光眼：默认为空
     */
    private Integer glaucoma;

    /**
     * 若选择有，可选择确诊年份
     */
    private Date diagnose2;

    /**
     * 高血压病史：默认为空
     */
    private Integer hypertension;

    /**
     * 若选择有，可选择确诊年份
     */
    private Date diagnose3;

    /**
     * 若选择有，可输入总胆固醇数值
     */
    private Integer cholesterol;

    /**
     * 若选择有，可选择否曾经定期服用过抗高血压药物
     */
    private Integer medicine;

    /**
     * 主诉症状：默认为空，可录入
     */
    private String symptom;

    /**
     * 左眼视力
     */
    private String leftvision;

    /**
     * 右眼视力
     */
    private String rightvision;

    /**
     * 左眼图片
     */
    private String leftpic;

    /**
     * 右眼图片
     */
    private String rightpic;

    private String leftaid;

    private String rightaid;

    private Integer baseinfoid;

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
     * 获取身高（cm为单位，录入 ，必填）
     *
     * @return height - 身高（cm为单位，录入 ，必填）
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 设置身高（cm为单位，录入 ，必填）
     *
     * @param height 身高（cm为单位，录入 ，必填）
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 获取体重（kg为单位，录入，必填）
     *
     * @return weight - 体重（kg为单位，录入，必填）
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置体重（kg为单位，录入，必填）
     *
     * @param weight 体重（kg为单位，录入，必填）
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取体重/身高(米平方)
     *
     * @return bmi - 体重/身高(米平方)
     */
    public String getBmi() {
        return bmi;
    }

    /**
     * 设置体重/身高(米平方)
     *
     * @param bmi 体重/身高(米平方)
     */
    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    /**
     * 获取腰围（cm，录入）
     *
     * @return waist - 腰围（cm，录入）
     */
    public String getWaist() {
        return waist;
    }

    /**
     * 设置腰围（cm，录入）
     *
     * @param waist 腰围（cm，录入）
     */
    public void setWaist(String waist) {
        this.waist = waist;
    }

    /**
     * 获取性别（若为女性，需选择是否绝经）
     *
     * @return menopause - 性别（若为女性，需选择是否绝经）
     */
    public Integer getMenopause() {
        return menopause;
    }

    /**
     * 设置性别（若为女性，需选择是否绝经）
     *
     * @param menopause 性别（若为女性，需选择是否绝经）
     */
    public void setMenopause(Integer menopause) {
        this.menopause = menopause;
    }

    /**
     * 获取血压（高压/低压格式输入）
     *
     * @return bloodpressure - 血压（高压/低压格式输入）
     */
    public Integer getBloodpressure() {
        return bloodpressure;
    }

    /**
     * 设置血压（高压/低压格式输入）
     *
     * @param bloodpressure 血压（高压/低压格式输入）
     */
    public void setBloodpressure(Integer bloodpressure) {
        this.bloodpressure = bloodpressure;
    }
    
    /**
     * 获取血压值
     *
     * @return pressure - 血压值
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * 设置血压值
     *
     * @param pressure 血压值
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    /**
     * 获取目前健康状况（选择）：良好、一般、较差
     *
     * @return state - 目前健康状况（选择）：良好、一般、较差
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置目前健康状况（选择）：良好、一般、较差
     *
     * @param state 目前健康状况（选择）：良好、一般、较差
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取体力劳动或体育运动锻炼（选择）：基本不运动，每月1-2次，一周1-2次，每天运动
     *
     * @return labour - 体力劳动或体育运动锻炼（选择）：基本不运动，每月1-2次，一周1-2次，每天运动
     */
    public Integer getLabour() {
        return labour;
    }

    /**
     * 设置体力劳动或体育运动锻炼（选择）：基本不运动，每月1-2次，一周1-2次，每天运动
     *
     * @param labour 体力劳动或体育运动锻炼（选择）：基本不运动，每月1-2次，一周1-2次，每天运动
     */
    public void setLabour(Integer labour) {
        this.labour = labour;
    }

    /**
     * 获取水果蔬菜摄入情况（选择）：无、偶尔吃、经常吃、每天都吃
     *
     * @return fruit - 水果蔬菜摄入情况（选择）：无、偶尔吃、经常吃、每天都吃
     */
    public Integer getFruit() {
        return fruit;
    }

    /**
     * 设置水果蔬菜摄入情况（选择）：无、偶尔吃、经常吃、每天都吃
     *
     * @param fruit 水果蔬菜摄入情况（选择）：无、偶尔吃、经常吃、每天都吃
     */
    public void setFruit(Integer fruit) {
        this.fruit = fruit;
    }

    /**
     * 获取是否经常性饮酒（选择）：从不、偶尔、一周1-2次、每天饮超过1瓶啤酒
     *
     * @return drink - 是否经常性饮酒（选择）：从不、偶尔、一周1-2次、每天饮超过1瓶啤酒
     */
    public Integer getDrink() {
        return drink;
    }

    /**
     * 设置是否经常性饮酒（选择）：从不、偶尔、一周1-2次、每天饮超过1瓶啤酒
     *
     * @param drink 是否经常性饮酒（选择）：从不、偶尔、一周1-2次、每天饮超过1瓶啤酒
     */
    public void setDrink(Integer drink) {
        this.drink = drink;
    }

    /**
     * 获取糖尿病史：默认为空
     *
     * @return diabetes - 糖尿病史：默认为空
     */
    public Integer getDiabetes() {
        return diabetes;
    }

    /**
     * 设置糖尿病史：默认为空
     *
     * @param diabetes 糖尿病史：默认为空
     */
    public void setDiabetes(Integer diabetes) {
        this.diabetes = diabetes;
    }

    /**
     * 获取若选择有，则可以选择确诊年份（可选）
     *
     * @return diagnose1 - 若选择有，则可以选择确诊年份（可选）
     */
    public Date getDiagnose1() {
        return diagnose1;
    }

    /**
     * 设置若选择有，则可以选择确诊年份（可选）
     *
     * @param diagnose1 若选择有，则可以选择确诊年份（可选）
     */
    public void setDiagnose1(Date diagnose1) {
        this.diagnose1 = diagnose1;
    }

    /**
     * 获取若选择有，则选择直系亲属或亲戚是否有糖尿病（必选）
     *
     * @return relatives - 若选择有，则选择直系亲属或亲戚是否有糖尿病（必选）
     */
    public Integer getRelatives() {
        return relatives;
    }

    /**
     * 设置若选择有，则选择直系亲属或亲戚是否有糖尿病（必选）
     *
     * @param relatives 若选择有，则选择直系亲属或亲戚是否有糖尿病（必选）
     */
    public void setRelatives(Integer relatives) {
        this.relatives = relatives;
    }

    /**
     * 获取青光眼：默认为空
     *
     * @return glaucoma - 青光眼：默认为空
     */
    public Integer getGlaucoma() {
        return glaucoma;
    }

    /**
     * 设置青光眼：默认为空
     *
     * @param glaucoma 青光眼：默认为空
     */
    public void setGlaucoma(Integer glaucoma) {
        this.glaucoma = glaucoma;
    }

    /**
     * 获取若选择有，可选择确诊年份
     *
     * @return diagnose2 - 若选择有，可选择确诊年份
     */
    public Date getDiagnose2() {
        return diagnose2;
    }

    /**
     * 设置若选择有，可选择确诊年份
     *
     * @param diagnose2 若选择有，可选择确诊年份
     */
    public void setDiagnose2(Date diagnose2) {
        this.diagnose2 = diagnose2;
    }

    /**
     * 获取高血压病史：默认为空
     *
     * @return hypertension - 高血压病史：默认为空
     */
    public Integer getHypertension() {
        return hypertension;
    }

    /**
     * 设置高血压病史：默认为空
     *
     * @param hypertension 高血压病史：默认为空
     */
    public void setHypertension(Integer hypertension) {
        this.hypertension = hypertension;
    }

    /**
     * 获取若选择有，可选择确诊年份
     *
     * @return diagnose3 - 若选择有，可选择确诊年份
     */
    public Date getDiagnose3() {
        return diagnose3;
    }

    /**
     * 设置若选择有，可选择确诊年份
     *
     * @param diagnose3 若选择有，可选择确诊年份
     */
    public void setDiagnose3(Date diagnose3) {
        this.diagnose3 = diagnose3;
    }

    /**
     * 获取若选择有，可输入总胆固醇数值
     *
     * @return cholesterol - 若选择有，可输入总胆固醇数值
     */
    public Integer getCholesterol() {
        return cholesterol;
    }

    /**
     * 设置若选择有，可输入总胆固醇数值
     *
     * @param cholesterol 若选择有，可输入总胆固醇数值
     */
    public void setCholesterol(Integer cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     * 获取若选择有，可选择否曾经定期服用过抗高血压药物
     *
     * @return medicine - 若选择有，可选择否曾经定期服用过抗高血压药物
     */
    public Integer getMedicine() {
        return medicine;
    }

    /**
     * 设置若选择有，可选择否曾经定期服用过抗高血压药物
     *
     * @param medicine 若选择有，可选择否曾经定期服用过抗高血压药物
     */
    public void setMedicine(Integer medicine) {
        this.medicine = medicine;
    }

    /**
     * 获取主诉症状：默认为空，可录入
     *
     * @return symptom - 主诉症状：默认为空，可录入
     */
    public String getSymptom() {
        return symptom;
    }

    /**
     * 设置主诉症状：默认为空，可录入
     *
     * @param symptom 主诉症状：默认为空，可录入
     */
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    /**
     * 获取左眼视力
     *
     * @return leftvision - 左眼视力
     */
    public String getLeftvision() {
        return leftvision;
    }

    /**
     * 设置左眼视力
     *
     * @param leftvision 左眼视力
     */
    public void setLeftvision(String leftvision) {
        this.leftvision = leftvision;
    }

    /**
     * 获取右眼视力
     *
     * @return rightvision - 右眼视力
     */
    public String getRightvision() {
        return rightvision;
    }

    /**
     * 设置右眼视力
     *
     * @param rightvision 右眼视力
     */
    public void setRightvision(String rightvision) {
        this.rightvision = rightvision;
    }

    /**
     * 获取左眼图片
     *
     * @return leftpic - 左眼图片
     */
    public String getLeftpic() {
        return leftpic;
    }

    /**
     * 设置左眼图片
     *
     * @param leftpic 左眼图片
     */
    public void setLeftpic(String leftpic) {
        this.leftpic = leftpic;
    }

    /**
     * 获取右眼图片
     *
     * @return rightpic - 右眼图片
     */
    public String getRightpic() {
        return rightpic;
    }

    /**
     * 设置右眼图片
     *
     * @param rightpic 右眼图片
     */
    public void setRightpic(String rightpic) {
        this.rightpic = rightpic;
    }

    /**
     * @return leftaid
     */
    public String getLeftaid() {
        return leftaid;
    }

    /**
     * @param leftaid
     */
    public void setLeftaid(String leftaid) {
        this.leftaid = leftaid;
    }

    /**
     * @return rightaid
     */
    public String getRightaid() {
        return rightaid;
    }

    /**
     * @param rightaid
     */
    public void setRightaid(String rightaid) {
        this.rightaid = rightaid;
    }

    /**
     * @return baseinfoid
     */
    public Integer getBaseinfoid() {
        return baseinfoid;
    }

    /**
     * @param baseinfoid
     */
    public void setBaseinfoid(Integer baseinfoid) {
        this.baseinfoid = baseinfoid;
    }
}