//修改
$(function() {
	//获取出生日期
	$("#idcard").change(function() {
		var idCard = $(this).val();
		var birthday = "";
		if(idCard != null && idCard != "") {
			if(idCard.length == 15) {
				birthday = "19" + idCard.substr(6, 6);
			} else if(idCard.length == 18) {
				birthday = idCard.substr(6, 8);
			}

			birthday = birthday.replace(/(.{4})(.{2})/, "$1-$2-");
		}
		$("#birthday").val(birthday);
	});

	//向导
	$('#fuelux-wizard-container')
		.ace_wizard({
			//step: 2 //optional argument. wizard will jump to step "2" at first
			//buttons: '.wizard-actions:eq(0)'
		})
		.on('actionclicked.fu.wizard', function(e, info) {

			// 触发表单验证
			var stepform1Validator = $("#stepform1").data('bootstrapValidator');
			stepform1Validator.validate();

			var stepform2Validator = $("#stepform2").data('bootstrapValidator');
			stepform2Validator.validate();

			if(info.step == 1) {
				var temp = document.getElementsByName("sex");
				var sex = 0;
				for(var i = 0; i < temp.length; i++) {
					if(temp[i].checked)
						sex = temp[i].value;
				}

				if(sex == 0) {
					$("#judgeMenopause").hide();
				} else if(sex == 1) {
					$("#judgeMenopause").show();
					$("input[name='menopause'][value=1]").attr("checked", true);
				}
			}

			if(info.step == 1 && !stepform1Validator.isValid()) {
				e.preventDefault();
			}
			if(info.step == 2 && !stepform2Validator.isValid()) {
				e.preventDefault();
			}
		})
		.on('changed.fu.wizard', function(e, info) {

		})
		.on('finished.fu.wizard', function(e) {

			// 验证图片是否上传
			var file1 = document.getElementById("file1");
			var fileList1 = file1.files; //获取的图片文件
			var file2 = document.getElementById("file2");
			var fileList2 = file2.files; //获取的图片文件
			if(fileList1.length < 1) {
				alert("请上传左眼图像");
				e.preventDefault();
			} else if(fileList2.length < 1) {
				alert("请上传右眼图像");
				e.preventDefault();
			} else {
				// form表单数据转化
				// jquery自带的serializeArray方法转为数组格式
				// jquery.serialize-object插件的serializeObject转为对象格式，serializeJSON转为json
				var formdata1 = $("#stepform1").serializeObject();
				var formdata2 = $("#stepform2").serializeObject();
				//form1和form2的值复制到uploadform中
				$('#uploadform').autofill(formdata1);
				$('#uploadform').autofill(formdata2);
				
				//获取是否有远程会诊中心在线
				getUsersP06();

				// 显示加载
				//$('#loading').modal('show');
				
				// 完成后操作
				//$('#uploadform').submit();
			}

		}).on('stepclick.fu.wizard', function(e, info) {
			//e.preventDefault();//this will prevent clicking and selecting steps
		});

	//表单验证
	validate();
});

function setbmi() {
	var w = $('#weight').val();
	var h = $('#height').val();
	if(w != 0 && h != 0) {
		h = h / 100;
		var bmi = w / (h * h);
		bmi = bmi.toFixed(2);
		var bmiText = "";
		if(bmi <= 18.4) {
			bmiText = "偏瘦";
		} else if(bmi >= 18.5 && bmi <= 23.9) {
			bmiText = "正常";
		} else if(bmi >= 24.0 && bmi <= 27.9) {
			bmiText = "过重";
		} else if(bmi >= 28.0) {
			bmiText = "肥胖";
		}

		$('#bmi').val(bmi + "（" + bmiText + "）")

		$('#bmivar').val(bmi);
		if(bmi > 26) {
			$('#bmi').css("color", "red");
		} else {
			$('#bmi').css("color", "");
		}
	}
}

//表单验证
function validate() {
	$('#stepform1').bootstrapValidator({
		excluded: [':disabled'],
		fields: { /*验证*/
			idcard: { /*键名和input name值对应*/
				validators: {
					notEmpty: { /*非空提示*/
						message: '不能为空'
					},
					regexp: { /* 只需加此键值对，包含正则表达式，和提示 */
						regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
						message: '请输入正确的身份证号'
					}
				}
			},
			name: { /*键名和input name值对应*/
				validators: {
					notEmpty: { /*非空提示*/
						message: '不能为空'
					},
					regexp: { /* 只需加此键值对，包含正则表达式，和提示 */
						regexp: /^[\u4e00-\u9fa5]{2,4}$/,
						message: '请输入真实姓名'
					}
				}
			},
			tel: { /*键名和input name值对应*/
				validators: {
					notEmpty: { /*非空提示*/
						message: '不能为空'
					},
					regexp: { /* 只需加此键值对，包含正则表达式，和提示 */
						regexp: /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/,
						message: '请输入正确手机号码'
					}
				}
			},
			guardian: { /*键名和input name值对应*/
				validators: {
					regexp: { /* 只需加此键值对，包含正则表达式，和提示 */
						regexp: /^[\u4e00-\u9fa5]{2,4}$/,
						message: '请输入真实姓名'
					}
				}
			},
			guardianphone: { /*键名和input name值对应*/
				validators: {
					regexp: { /* 只需加此键值对，包含正则表达式，和提示 */
						regexp: /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/,
						message: '请输入正确手机号码'
					}
				}
			}

		}
	});
	$('#stepform2').bootstrapValidator({
		excluded: [':disabled'],
		fields: { /*验证*/
			height: { /*键名和input name值对应*/
				validators: {
					notEmpty: { /*非空提示*/
						message: '不能为空'
					},
					regexp: {
						regexp: /^\d{0,8}\.{0,1}(\d{1,3})?$/,
						message: '请输入整数或3位小数'
					}
				}
			},
			weight: { /*键名和input name值对应*/
				validators: {
					notEmpty: { /*非空提示*/
						message: '不能为空'
					},
					regexp: {
						regexp: /^\d{0,8}\.{0,1}(\d{1,3})?$/,
						message: '请输入整数或3位小数'
					}
				}
			}

		}
	});
}

function getUsersP06() {
	$.ajax({
		type: "POST",
		url: ctx + "/consultation/getUsersP06",
		async: false,
		success: function(data) {
			json = JSON.parse(data);
			if(json.UsersP06List.length==0) {
				$('#noneModal').modal('show');
			} else {
				// 显示加载
				$('#loading').modal('show');
				
				// 完成后操作
				$('#uploadform').submit();
			}
		}
	});
}


function continueSubmit() {
	$("input[id='isNone']")[0].value = 1;
	$('#noneModal').modal('hide');
	//远程会诊中心无人在线时
	// 显示加载
	$('#loading').modal('show');
	// 完成后操作
	$('#uploadform').submit();
}
