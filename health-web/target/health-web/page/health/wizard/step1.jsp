<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function () {
        //判断是否宽屏
        var winWide = window.screen.width;
        var wideScreen = false;
        var html = "";
        if (winWide < 1000) {//1000及以下分辨率
			$("#identity_div").hide();
		}else{
        	$("#identity_div").show();
            wideScreen = true; //是宽屏
        }
	})
	
	function getVerSion()
	{
		clearForm();
		
		var CertCtl = document.getElementById("CertCtl");
		try 
		{
			var result = CertCtl.getVersion();
			document.getElementById("result").value = result;
	    } catch (e) 
		{
		}
	}
	function toJson(str)
	{
		//var obj = JSON.parse(str);
		//return obj;
		return eval('('+str+')');  	
	}
	function clearForm()
	{
		document.getElementById("timeElapsed").value = "";
		document.getElementById("partyName").value = "";
		document.getElementById("gender").value = "";
		document.getElementById("nation").value = "";
		document.getElementById("bornDay").value = "";
		document.getElementById("certAddress").value = "";
		document.getElementById("certNumber").value = "";
		document.getElementById("certOrg").value = "";
		document.getElementById("effDate").value = "";
		document.getElementById("expDate").value = "";
		document.getElementById("result").value = "";
	}
	function connect()
	{
		clearForm();
		
		var CertCtl = document.getElementById("CertCtl");
		try {
			var result = CertCtl.connect();
			document.getElementById("result").value = result;
			$("#message").text("已连接");
	    } catch (e) 
		{
		}
	}
	function disconnect()
	{
		clearForm();
		
		var CertCtl = document.getElementById("CertCtl");
		try 
		{
			var result = CertCtl.disconnect();
			document.getElementById("result").value = result;
			$("#message").text("已断开");
	    } catch (e) 
		{
		}
	}
	function getStatus()
	{
		clearForm();
		
		var CertCtl = document.getElementById("CertCtl");
		try {
			var result = CertCtl.getStatus();
			document.getElementById("result").value = result;
		} catch(e) {
		}
	}
	function readCert() 
	{
		clearForm();
		
		var CertCtl = document.getElementById("CertCtl");
		
		try {
			var startDt = new Date();
			var result = CertCtl.readCert();
			var endDt = new Date();
			
			document.getElementById("timeElapsed").value = (endDt.getTime() - startDt.getTime()) + "毫秒";
			document.getElementById("result").value = result;
			
			var resultObj = toJson(result);
			if (resultObj.resultFlag == 0) {
				document.getElementById("partyName").value = resultObj.resultContent.partyName;
				document.getElementById("gender").value = resultObj.resultContent.gender;
				document.getElementById("nation").value = resultObj.resultContent.nation;
				document.getElementById("bornDay").value = resultObj.resultContent.bornDay;
				document.getElementById("certAddress").value = resultObj.resultContent.certAddress;
				document.getElementById("certNumber").value = resultObj.resultContent.certNumber;
				document.getElementById("certOrg").value = resultObj.resultContent.certOrg;
				document.getElementById("effDate").value = resultObj.resultContent.effDate;
				document.getElementById("expDate").value = resultObj.resultContent.expDate;
				
				$("#name").val(resultObj.resultContent.partyName);
				$("#idcard").val(resultObj.resultContent.certNumber);
				var birthday = resultObj.resultContent.bornDay;
				birthday = birthday.substring(0,4)+"-"+birthday.substring(4,6)+"-"+birthday.substring(6);
				$("#birthday").val(birthday);
				$("#address").val(resultObj.resultContent.certAddress);
				var gender = resultObj.resultContent.gender;
				if(gender == "1"){
					$("#sex_m").attr("checked",true);
	        		$("#sex_w").attr("checked",false);
				}else{
					$("#sex_m").attr("checked",false);
	        		$("#sex_w").attr("checked",true);
				}
			}
		} catch(e) {
		}
	}
	function conv2base64() 
	{
	    var CertCtl = document.getElementById("CertCtl");
	    try 
	    {
	        var jpgPath = document.getElementById("inputJpgPath").value;
	        var result;
	        result = CertCtl.ConvJpgToBase64File(jpgPath);
	        document.getElementById("outputBase64File").value = result;
	    } catch (e) 
	    {
	    }
	}
	
	function convBase64ToJpg() 
	{
	    var CertCtl = document.getElementById("CertCtl");
	    try 
	    {
	        var jpgPath = document.getElementById("decodeJpgPath").value;
	        var base64Data = document.getElementById("base64Input").value;
	        var result;
	        result = CertCtl.ConvBase64ToJpg(base64Data, jpgPath);
	        alert(result);
	    } catch (e) {
	    }
	}
</script>


<div class="step-pane active" data-step="1">
	<div style="text-align: center;" id="identity_div">
		<div style="margin-bottom: 20px;">
			<div class="col-md-offset-1 col-md-10">
				<button class="btn btn-sm " type="button" onclick="connect()">
					<i class="ace-icon fa fa-undo bigger-110"></i>
					连接
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-sm btn-info" type="button" onclick="readCert()">
					<i class="ace-icon fa fa-check bigger-110"></i>
					读卡
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-sm " type="button" onclick="disconnect()">
					<i class="ace-icon fa fa-undo bigger-110"></i>
					断开
				</button>
				&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
				<span id="message">已断开</span>
			</div>
		</div>
		<br/>
		
		<table border="0" width="100%" cellpadding="0" cellspacing="10" style="display: none;">
			<tr>
				<td align="right">读卡时间：</td>
				<td><input type="text" id="timeElapsed" size="49" style="width:400px;" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td align="right">姓名：</td>
				<td><input type="text" id="partyName" size="49" style="width:400px;" readonly="readonly">(要求中间，两头都没有空格)</td>
			</tr>
			<tr>
				<td align="right">性别：</td>
				<td><input type="text" id="gender" size="49" style="width:400px;" readonly="readonly">(取值为“1”（表示“男”）或“0”（表示“女”）)</td>
			</tr>
			<tr>
				<td align="right">民族：</td>
				<td><input type="text" id="nation" size="49" style="width:400px;" readonly="readonly">(汉字即可)</td>
			</tr>
			<tr>
				<td align="right">出生日期：</td>
				<td><input type="text" id="bornDay" size="49" style="width:400px;" readonly="readonly">(要求格式为:yyyyMMdd，长度为8)</td>
			</tr>
			<tr>
				<td align="right">住址：</td>
				<td><input type="text" id="certAddress" size="49" style="width:400px;" readonly="readonly"></td>
			</tr>
			<tr>
				<td align="right">身份证号：</td>
				<td><input type="text" id="certNumber" size="49" style="color:#FF0000;width:400px;" readonly="readonly">(居民身份号码，长度18位)</td>
			</tr>
			<tr>
				<td align="right">签发机关：</td>
				<td><input type="text" id="certOrg" size="49" style="width:400px;" readonly="readonly"></td>
			</tr>
			<tr>
				<td align="right">开始期限：</td>
				<td><input type="text" id="effDate" size="49" style="width:400px;" readonly="readonly">(要求格式为:yyyyMMdd，长度为8)</td>
			</tr>
			<tr>
				<td align="right">结束期限：</td>
				<td><input type="text" id="expDate" size="49" style="width:400px;" readonly="readonly">(要求格式为:yyyyMMdd，长度为8，或者是“长期”)</td>
			</tr>
			<tr>
				<td align="right">结果：</td>
				<td><textarea id="result" rows="8" cols="47" style="width:400px;" readonly="readonly"></textarea></td>
			</tr>
			
			<tr>
			<td align="right">jpg路径：</td>
			<td><input type="text" id="inputJpgPath" size="49" style="width:400px;"></td>
			</tr>
			
			<tr>
			<td align="right">base64数据：</td>
			<td><textarea id="outputBase64File" rows="8" cols="47" style="width:400px;"></textarea><input type="button" value="转码" onclick="conv2base64()"></td>
			</tr>
			
			<td align="right">base64数据：</td>
			<td><textarea id="base64Input" rows="8" cols="47" style="width:400px;"></textarea></td>
			</tr>
			
			<tr>
			<td align="right">解码jpg路径：</td>
			<td><input type="text" id="decodeJpgPath" size="49" style="width:400px;"><input type="button" value="解码" onclick="convBase64ToJpg()"></td>
			</tr>
		</table>
		<div class="col-md-offset-1 col-md-10">
			<object id="CertCtl" type="application/cert-reader" width="0" height="0" style="clear: both;">
			<p style="color:#FF0000;">控件不可用，可能未正确安装控件及驱动，或者控件未启用。</p>
			</object>		
		</div>	
	</div>
	
	<form id="stepform1" style="width: 70%;" class="center-block">
		<table class="table step_table">
			<tbody>
				<tr>
					<td class="width-15 active text-right">
						<label>姓名<font color="red">*</font></label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="text" id="name" name="name" class="form-control" maxlength="20" />
						</div>
					</td>
					<td class="width-15 active text-right">
						<label>身份证<font color="red">*</font></label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="number" min="0" id="idcard" name="idcard" class="form-control" maxlength="20" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right">
						<label>手机<font color="red">*</font></label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="number" min="0" name="tel" class="form-control" maxlength="20" />
						</div>
					</td>
					<td class="width-15 active text-right">
						<label>出生日期</label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="text" id="birthday" name="birthday" class="form-control" readonly="readonly" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right">
						<label>性别<font color="red">*</font></label>
					</td>
					<td class="width-35">
						<div class="form-group">
						    <label>
								<input name="sex" value="0" class="ace" type="radio" id="sex_m" checked>
								<span class="lbl">男</span>
							</label>
							<label>
								<input name="sex" value="1" class="ace" type="radio" id="sex_w">
								<span class="lbl">女</span>
							</label>
						</div>
					</td>
					<td class="width-15 active text-right">
						<label>家庭住址</label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="text" name="address" id="address" class="form-control" maxlength="100" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right">
						<label>监护人/联系人</label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="text" name="guardian" class="form-control" maxlength="20" />
						</div>
					</td>
					<td class="width-15 active text-right">
						<label>监护人手机</label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="number" min="0" name="guardianphone" class="form-control" maxlength="20" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right">
						<label>所来自医院</label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="text" name="hospital" class="form-control" maxlength="50" />
						</div>
					</td>
					<td class="width-15 active text-right">
						<label>主诊医生</label>
					</td>
					<td class="width-35">
						<div class=" form-group">
							<input type="text" name="doctor" class="form-control" maxlength="50" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right">
						<label>备注</label>
					</td>
					<td class="width-35" colspan="3">
						<div class=" form-group">
							<textarea name="remark" class="form-control" maxlength="150"></textarea>
						</div>
					</td>

				</tr>
			</tbody>
		</table>
		<!--<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				姓名<span class="colorred">*</span>
			</label>
			<div class="col-sm-2">
				<input type="text" name="name" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="20"/>
			</div>

			<label class="col-sm-1 control-label no-padding-right">
				身份证<span class="colorred">*</span>
			</label>
			<div class="col-sm-4">
				<input type="number" min="0" id="idcard" name="idcard" class="width-100" style="margin-top: 7px;margin-left: 10px;"  maxlength="20"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				手机<span class="colorred">*</span>
			</label>
			<div class="col-sm-2">
				<input type="number" min="0" name="tel" class="width-100" style="margin-top: 7px;margin-left: 10px;"/>
			</div>

			<label class="col-sm-1 control-label no-padding-right">
				出生日期
			</label>
			<div class="col-sm-2">
				<input type="text" id="birthday" name="birthday" class="width-100" style="margin-top: 7px;margin-left: 10px;" readonly="readonly" />
			</div>
			
			<label class="col-sm-1 control-label no-padding-right" style="margin-left: -20px;">
				性别 <span class="colorred">*</span>
			</label>
			
			<div class="col-sm-2 radio">
				<label>
					<input name="sex" value="0" class="ace" type="radio" checked>
					<span class="lbl">男</span>
				</label>
				<label>
					<input name="sex" value="1" class="ace" type="radio">
					<span class="lbl">女</span>
				</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				家庭住址
			</label>
			<div class="col-sm-7">
				<input type="text" name="address" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="100"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				监护人/联系人
			</label>
			<div class="col-sm-2">
				<input type="text" name="guardian" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="20"/>
			</div>

			<label class="col-sm-1 control-label no-padding-right">
				监护人手机
			</label>
			<div class="col-sm-2">
				<input type="number" min="0" name="guardianphone" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="20" style="-moz-appearance: textfield;"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				所来自医院
			</label>
			<div class="col-sm-2">
				<input type="text" name="hospital" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="50"/>
			</div>

			<label class="col-sm-1 control-label no-padding-right">
				主诊医生
			</label>
			<div class="col-sm-2">
				<input type="text" name="doctor" class="width-100" style="margin-top: 7px;margin-left: 10px;" maxlength="50"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">
				备注
			</label>
			<div class="col-sm-7">
				<textarea name="remark"  class="form-control"  style="margin-top: 7px;margin-left: 10px;" maxlength="150"></textarea>
			</div>
		</div>-->

	</form>

</div>