<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="step-pane" data-step="2">

	<form id="stepform2" style="width: 70%;" class="center-block">
		<table class="table step_table">
			<tbody>
				<tr>
					<td class="width-10 active text-right">
						<label>身高（cm）<font color="red">*</font></label>
					</td>
					<td class="width-20">
						<div class="form-group">
							<input type="number" step="0.1" min="1" max="500" name="height" id="height" class="form-control" onchange="setbmi();" maxlength="20" />
						</div>
					</td>
					<td class="width-10 active text-right">
						<label>体重（kg）<font color="red">*</font></label>
					</td>
					<td class="width-20">
						<div class=" form-group">
							<input type="number" step="0.1" min="1" max="500" name="weight" id="weight" class="form-control" onchange="setbmi();" maxlength="20" />
						</div>
					</td>
					<td class="width-10 active text-right">
						<label>BMI</label>
					</td>
					<td class="width-20">
						<div class=" form-group">
							<input type="text" id="bmi" class="form-control" maxlength="20" readonly="readonly" />
						</div>
						<input type="hidden" name="bmi" id="bmivar" />
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right">
						<label>腰围</label>
					</td>
					<td class="width-20">
						<div class=" form-group">
							<input type="number" min="0" step="0.1" name="waist" class="form-control" maxlength="20" />
						</div>

					</td>
					<td class="width-10 active text-right">
						<label>左眼视力</label>
					</td>
					<td class="width-20">
						<div class=" form-group">
							<input type="number" min="0.1" max="6" step="0.1" name="leftvision" class="form-control" maxlength="20" />
						</div>
					</td>
					<td class="width-10 active text-right">
						<label>右眼视力</label>
					</td>
					<td class="width-20">
						<div class=" form-group">
							<input type="number" min="0.1" max="6" step="0.1" name="rightvision" class="form-control" maxlength="20" />
						</div>
					</td>
				</tr>
				<tr id="judgeMenopause">
					<td class="width-20 active text-right" colspan="2">
						<label>若为女性，需选择是否绝经</label>
					</td>
					<td class="width-20" colspan="4">
						<div class=" form-group">
							<label>
								<input name="menopause" value="0" class="ace" type="radio">
								<span class="lbl">是</span>
							</label>
							
							<label>
								<input name="menopause" value="1" class="ace" type="radio">
								<span class="lbl">否</span>
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>血压</label>
					</td>
					<td class="width-20" colspan="2">
						<div class="form-group">
							<label>
								<input name="bloodpressure" value="2" class="ace" type="radio" checked="checked">
								<span class="lbl">正常</span>
							</label>
							
							<label>
								<input name="bloodpressure" value="0" class="ace" type="radio">
								<span class="lbl">高血压</span>
							</label>
							
							<label>
								<input name="bloodpressure" value="1" class="ace" type="radio">
								<span class="lbl">低血压</span>
							</label>
						</div>
					</td>
					<td class="width-10 active text-right">
						<label>血压值</label>
					</td>
					<td class="width-20">
						<input type="number" min="0" step="0.1" name="pressure" class="form-control" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>目前健康状况</label>
					</td>
					<td class="width-20" colspan="4">
						<div class=" form-group">
							<label>
							<input name="state" value="0" class="ace" type="radio">
							<span class="lbl">良好</span>
						</label>
						
							<label>
							<input name="state" value="1" class="ace" type="radio" checked="checked">
							<span class="lbl">一般</span>
						</label>
						
							<label>
							<input name="state" value="2" class="ace" type="radio">
							<span class="lbl">较差</span>
						</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>体力劳动或体育运动锻炼</label>
					</td>
					<td class="width-20" colspan="4">
						<div class=" form-group">
							<label>
							<input name="labour" value="0" class="ace" type="radio">
							<span class="lbl">基本不运动</span>
						</label>
						
							<label>
							<input name="labour" value="1" class="ace" type="radio" checked="checked">
							<span class="lbl">每月1-2次</span>
						</label>
							<label>
							<input name="labour" value="2" class="ace" type="radio">
							<span class="lbl">一周1-2次</span>
						</label>
						
							<label>
							<input name="labour" value="3" class="ace" type="radio">
							<span class="lbl">每天运动</span>
						</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>水果蔬菜摄入情况</label>
					</td>
					<td class="width-20" colspan="4">
						<div class=" form-group">
						<label>
							<input name="fruit" value="0" class="ace" type="radio">
							<span class="lbl">无</span>
						</label>
						<label>
							<input name="fruit" value="1" class="ace" type="radio" checked="checked">
							<span class="lbl">偶尔吃</span>
						</label>
						<label>
							<input name="fruit" value="2" class="ace" type="radio">
							<span class="lbl">经常吃</span>
						</label>
						<label>
							<input name="fruit" value="3" class="ace" type="radio">
							<span class="lbl">每天都吃</span>
						</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>是否经常性饮酒</label>
					</td>
					<td class="width-20" colspan="4">
						<div class=" form-group">
							<label>
							<input name="drink" value="0" class="ace" type="radio">
							<span class="lbl">从不</span>
						</label>
							<label>
							<input name="drink" value="1" class="ace" type="radio" checked="checked">
							<span class="lbl">偶尔</span>
						</label>
							<label>
							<input name="drink" value="2" class="ace" type="radio">
							<span class="lbl">一周1-2次</span>
						</label>
							<label>
							<input name="drink" value="3" class="ace" type="radio">
							<span class="lbl">每天饮超过1瓶啤酒</span>
						</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>糖尿病史</label>
					</td>
					<td class="width-20" colspan="4">
						<div class=" form-group">
							<label>
					<input name="diabetes" value="0" class="ace" type="radio" checked="checked">
					<span class="lbl">无</span>
				</label>
							<label>
					<input name="diabetes" value="1" class="ace" type="radio">
					<span class="lbl">有</span>
				</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>高血压病史</label>
					</td>
					<td class="width-20" colspan="4">
						<div class=" form-group">
							<label>
					<input name="hypertension" value="0" class="ace" type="radio" checked="checked">
					<span class="lbl">无</span>
				</label>
							<label>
					<input name="hypertension" value="1" class="ace" type="radio">
					<span class="lbl">有</span>
				</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>青光眼</label>
					</td>
					<td class="width-20" colspan="4">
						<div class=" form-group">
							<label>
					<input name="glaucoma" value="0" class="ace" type="radio" checked="checked">
					<span class="lbl">无</span>
				</label>
							<label>
					<input name="glaucoma" value="1" class="ace" type="radio">
					<span class="lbl">有</span>
				</label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-10 active text-right" colspan="2">
						<label>主诉症状</label>
					</td>
					<td class="width-20" colspan="3">
						<div class="form-group">
							<textarea name="symptom" class="form-control" maxlength="150"></textarea>
						</div>
					</td>
				</tr>

			</tbody>
		</table>
		<!--<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				身高（cm）<span class="colorred">*</span>
			</label>
			<div class="col-sm-1">
				<input type="number" step="0.01" min="0" max="500" name="height" id="height" class="width-100" onchange="setbmi();" style="margin-top: 7px;margin-left: 10px;" maxlength="20"/>
			</div>
			<label class="col-sm-1 control-label no-padding-right">
				体重（kg）<span class="colorred">*</span>
			</label>
			<div class="col-sm-1">
				<input type="number" step="0.01" min="0" max="500" name="weight" id="weight" class="width-100" onchange="setbmi();" style="margin-top: 7px;margin-left: 10px;" maxlength="20"/>
			</div>
			
			<label class="col-sm-1 control-label no-padding-right">
				BMI：
			</label>
			<div class="col-sm-1" style="width:auto;">		
				<input type="text" id="bmi" class="width-100" style="margin-top: 7px;" maxlength="20" style="border:hidden" readonly="readonly"/>
			</div>
			<div class="col-sm-2">		
				<input type="hidden" name="bmi" id="bmivar" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				腰围
			</label>
			<div class="col-sm-1">
				<input type="number" min="0" step="0.1" name="waist" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="20"/>
			</div>
			<label class="col-sm-1 control-label no-padding-right">
				左眼视力
			</label>
			<div class="col-sm-1">
				<input type="number" min="0" step="0.01" name="leftvision" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="20"/>
			</div>
			<label class="col-sm-1 control-label no-padding-right">
				右眼视力
			</label>
			<div class="col-sm-1">
				<input type="number" min="0" step="0.01" name="rightvision" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="20"/>
			</div>
		</div>

		<div class="form-group" id="judgeMenopause" >
			<label class="col-sm-4 control-label no-padding-right">
				若为女性，需选择是否绝经:
			</label>
			<div class="col-sm-2 radio">
				<label>
					<input name="menopause" value="0" class="ace" type="radio">
					<span class="lbl">是</span>
				</label>
				<label>
					<input name="menopause" value="1" class="ace" type="radio">
					<span class="lbl">否</span>
				</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right" style="margin-top:7px">
				血压:
			</label>
			<div class="col-sm-6 radio">
				<label>
					<input name="bloodpressure" value="2" class="ace" type="radio" checked="checked">
					<span class="lbl">正常</span>
				</label>
				<label>
					<input name="bloodpressure" value="0" class="ace" type="radio">
					<span class="lbl">高压</span>
				</label>
				<label>
					<input name="bloodpressure" value="1" class="ace" type="radio">
					<span class="lbl">低压</span>
				</label>
				<label>
					<span class="lbl">血压值：</span>
					<input type="number" min="0" name="pressure" style="margin-top: 7px;" maxlength="20" />
				</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right">
				目前健康状况:
			</label>
			<div class="col-sm-3 radio">
				<label>
					<input name="state" value="0" class="ace" type="radio">
					<span class="lbl">良好</span>
				</label>
				<label>
					<input name="state" value="1" class="ace" type="radio" checked="checked">
					<span class="lbl">一般</span>
				</label>
				<label>
					<input name="state" value="2" class="ace" type="radio">
					<span class="lbl">较差</span>
				</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right">
				体力劳动或体育运动锻炼:
			</label>
			<div class="col-sm-5 radio">
				<label>
					<input name="labour" value="0" class="ace" type="radio">
					<span class="lbl">基本不运动</span>
				</label>
				<label>
					<input name="labour" value="1" class="ace" type="radio" checked="checked">
					<span class="lbl">每月1-2次</span>
				</label>
				<label>
					<input name="labour" value="2" class="ace" type="radio">
					<span class="lbl">一周1-2次</span>
				</label>
				<label>
					<input name="labour" value="3" class="ace" type="radio">
					<span class="lbl">每天运动</span>
				</label>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right">
				水果蔬菜摄入情况:
			</label>
			<div class="col-sm-4 radio">
				<label>
					<input name="fruit" value="0" class="ace" type="radio">
					<span class="lbl">无</span>
				</label>
				<label>
					<input name="fruit" value="1" class="ace" type="radio" checked="checked">
					<span class="lbl">偶尔吃</span>
				</label>
				<label>
					<input name="fruit" value="2" class="ace" type="radio">
					<span class="lbl">经常吃</span>
				</label>
				<label>
					<input name="fruit" value="3" class="ace" type="radio">
					<span class="lbl">每天都吃</span>
				</label>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right">
				是否经常性饮酒:
			</label>
			<div class="col-sm-5 radio">
				<label>
					<input name="drink" value="0" class="ace" type="radio">
					<span class="lbl">从不</span>
				</label>
				<label>
					<input name="drink" value="1" class="ace" type="radio" checked="checked">
					<span class="lbl">偶尔</span>
				</label>
				<label>
					<input name="drink" value="2" class="ace" type="radio">
					<span class="lbl">一周1-2次</span>
				</label>
				<label>
					<input name="drink" value="3" class="ace" type="radio">
					<span class="lbl">每天饮超过1瓶啤酒</span>
				</label>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right">
				糖尿病史:
			</label>
			<div class="col-sm-2 radio">
				<label>
					<input name="diabetes" value="0" class="ace" type="radio" checked="checked">
					<span class="lbl">无</span>
				</label>
				<label>
					<input name="diabetes" value="1" class="ace" type="radio">
					<span class="lbl">有</span>
				</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right">
				高血压病史:
			</label>
			<div class="col-sm-2 radio">
				<label>
					<input name="hypertension" value="0" class="ace" type="radio" checked="checked">
					<span class="lbl">无</span>
				</label>
				<label>
					<input name="hypertension" value="1" class="ace" type="radio">
					<span class="lbl">有</span>
				</label>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right">
				青光眼:
			</label>
			<div class="col-sm-2 radio">
				<label>
					<input name="glaucoma" value="0" class="ace" type="radio" checked="checked">
					<span class="lbl">无</span>
				</label>
				<label>
					<input name="glaucoma" value="1" class="ace" type="radio">
					<span class="lbl">有</span>
				</label>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-right">
				主诉症状
			</label>
			<div class="col-sm-5">
				<textarea name="symptom"  class="form-control" style="margin-top: 7px;margin-left: 10px;" maxlength="150"></textarea>
			</div>
		</div>-->
	</form>
</div>