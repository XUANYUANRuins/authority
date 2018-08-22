function updateReport(obj) {
	var Id = obj.id;
	if(Id == 'submit') { //判断提交还是退回
		$("input[id='isSubmit']")[0].value = 1;
	} else if(Id == 'return') {
		$("input[id='isSubmit']")[0].value = 0;
	} else if(Id == 'save') {
		$("input[id='isSubmit']")[0].value = 2;
	}
	
	// 显示加载
	$('#myModal').modal('show');

	$('#editfm').submit();
}

function backHome() {
	location.href = ctx + "/page/health/homepage/index.jsp";
}

/**
 * 左右眼诊断编辑
 * @returns
 */
function initResultEdit() {
	initEdit('left_edit', 'left_ul');
	initEdit('right_edit', 'right_ul');
}

function initEdit(editId, viewId) {
	var viewLi = "";
	$('#' + editId + ' li').each(function() {
		var checkbox = $(this).find("input[type=radio]:checked");
		if(checkbox.length > 0) {
			viewLi += "<li>" + $(this).find("label").text() + "</li>";
		}

		// 文本域
		var textarea = $(this).find("textarea").val();
		if(textarea) {
			viewLi += "<li>" + textarea + "</li>";
		}

	});

	//先清空后添加
	$('#' + viewId + ' li').remove();
	$('#' + viewId).append(viewLi);
}

/**
 * 医生建议编辑
 * @returns
 */
function doctorEdit() {
	var viewLi = "";
	$('#suggest_edit li').each(function() {
		var checkbox = $(this).find("input[type=checkbox]:checked");
		if(checkbox.length > 0) {
			viewLi += "<li>" + $(this).find("label").text() + "</li>";
		}

		// 文本域
		var textarea = $(this).find("textarea").val();
		if(textarea) {
			viewLi += "<li>" + textarea + "</li>";
		}

	});

	//先清空后添加
	$('#suggest_ul li').remove();
	$('#suggest_ul').append(viewLi);
}

$(function() {
	var birthday = document.getElementById('birthday').innerText;
	var age = jsGetAge(birthday);
	$('#age').html(age);
});

/*根据出生日期算出年龄*/
function jsGetAge(strBirthday) {
	var returnAge;
	var strBirthdayArr = strBirthday.split("-");
	var birthYear = strBirthdayArr[0];
	var birthMonth = strBirthdayArr[1];
	var birthDay = strBirthdayArr[2];

	d = new Date();
	var nowYear = d.getFullYear();
	var nowMonth = d.getMonth() + 1;
	var nowDay = d.getDate();

	if(nowYear == birthYear) {
		returnAge = 0; //同年 则为0岁  
	} else {
		var ageDiff = nowYear - birthYear; //年之差  
		if(ageDiff > 0) {
			if(nowMonth == birthMonth) {
				var dayDiff = nowDay - birthDay; //日之差  
				if(dayDiff < 0) {
					returnAge = ageDiff - 1;
				} else {
					returnAge = ageDiff;
				}
			} else {
				var monthDiff = nowMonth - birthMonth; //月之差  
				if(monthDiff < 0) {
					returnAge = ageDiff - 1;
				} else {
					returnAge = ageDiff;
				}
			}
		} else {
			returnAge = -1; //返回-1 表示出生日期输入错误 晚于今天  
		}
	}

	return returnAge; //返回周岁年龄    
}
//获取屏幕宽高
var winWid = window.screen.width;
var winHei = window.screen.height;

$(document).ready(function() {
	//两个图的宽度
	var w1_left = 0;
	var w2_left = 0;
	var w3_left = 0;
	var w1_right = 0;
	var w2_right = 0;
	var w3_right = 0;
	
	// 圆  
	var $circle_left = null;
	var $img1_circle = null;
	var $circle_right = null;
	var $img2_circle = null;
	var $easyzoom1_circle = null;
	var $easyzoom2_circle = null;
	
	// 画布  
	var $drawing_left = $(".drawing_left");
	var $img1_drawing = $("#img1").find(".drawing");
	var $drawing_right = $(".drawing_right");
	var $img2_drawing = $("#img2").find(".drawing");
	var $easyzoom1 = $("#easyzoom1");
	var $easyzoom2 = $("#easyzoom2");

	// 圆心位置  
	var centerX_left = 0;
	var centerY_left = 0;
	var radius_left = 0;
	var centerX_right = 0;
	var centerY_right = 0;
	var radius_right = 0;
	
	// 是否正在画圆  
	var isDrawing_left = false;
	var isDrawing_right = false;
	
	// 按下鼠标开始画圆  
	$drawing_left.mousedown(function(event) {
		var num1 = $drawing_left.find(".circle").size();
		if(num1<2) {
			$circle_left = $('<div class="circle"></div>');
			$easyzoom1_circle = $('<div class="circle"></div>');
			$img1_circle = $('<div class="circle"></div>');
			centerX_left = event.pageX - $drawing_left.offset().left;
			centerY_left = event.pageY - $drawing_left.offset().top;
			$drawing_left.append($circle_left);
			
			isDrawing_left = true;
			event.preventDefault();
			
			//两个图的宽度
			w1_left = document.getElementById("drawing_left").offsetWidth;
			w2_left = document.getElementById("avatar1").offsetWidth;
			w3_left = (w2_left/w1_left).toFixed(6);
		}
	});
	
	$drawing_right.mousedown(function(event) {
		var num2 = $drawing_right.find(".circle").size();
		if(num2<2) {
			$circle_right = $('<div class="circle"></div>');
			$easyzoom2_circle = $('<div class="circle"></div>');
			$img2_circle = $('<div class="circle"></div>');
			centerX_right = event.pageX - $drawing_right.offset().left;
			centerY_right = event.pageY - $drawing_right.offset().top;
			$drawing_right.append($circle_right);
			isDrawing_right = true;
			event.preventDefault();
			
			//两个图的宽度
			w1_right = document.getElementById("drawing_right").offsetWidth;
			w2_right = document.getElementById("avatar2").offsetWidth;
			w3_right = (w2_right/w1_right).toFixed(6);
		}
	});

	// 鼠标拖动  
	$drawing_left.mousemove(function(event) {
		var num1 = $drawing_left.find(".circle").size();
		if(isDrawing_left && num1 <= 2) {
			var radiusX = Math.abs(event.pageX - $drawing_left.offset().left - centerX_left);
			var radiusY = Math.abs(event.pageY - $drawing_left.offset().top - centerY_left);
			var radius = Math.sqrt(radiusX * radiusX + radiusY * radiusY); // 半径，勾股定理  

			// 下面四个条件判断是限制圆不能超出画布区域，如果不需要这个限制可以去掉这段代码  
			if(centerX_left - radius < 0) {
				radius = centerX_left;
			}
			if(centerY_left - radius < 0) {
				radius = centerY_left;
			}
			if(centerX_left + radius > $drawing_left.width()) {
				radius = $drawing_left.width() - centerX_left;
			}
			if(centerY_left + radius > $drawing_left.height()) {
				radius = $drawing_left.height() - centerY_left;
			}

			// 设置圆的大小和位置  
			$circle_left.css("left", centerX_left - radius + "px");
			$circle_left.css("top", centerY_left - radius + "px");
			$circle_left.css("width", 2 * radius + "px");
			$circle_left.css("height", 2 * radius + "px");
			$circle_left.css("border-radius", radius + "px");
			
			$easyzoom1_circle.css("left", centerX_left - radius + "px");
			$easyzoom1_circle.css("top", centerY_left - radius + "px");
			$easyzoom1_circle.css("width", 2 * radius + "px");
			$easyzoom1_circle.css("height", 2 * radius + "px");
			$easyzoom1_circle.css("border-radius", radius + "px");
			
			$img1_circle.css("left", (centerX_left - radius)*w3_left + "px");
			$img1_circle.css("top", (centerY_left - radius)*w3_left + "px");
			$img1_circle.css("width", 2 * radius*w3_left + "px");
			$img1_circle.css("height", 2 * radius*w3_left + "px");
			$img1_circle.css("border-radius", radius*w3_left + "px");

			radius_left = radius;
		}
	});
	
	$drawing_right.mousemove(function(event) {
		var num2 = $drawing_right.find(".circle").size();
		if(isDrawing_right && num2 <= 2) {
			var radiusX = Math.abs(event.pageX - $drawing_right.offset().left - centerX_right);
			var radiusY = Math.abs(event.pageY - $drawing_right.offset().top - centerY_right);
			var radius = Math.sqrt(radiusX * radiusX + radiusY * radiusY); // 半径，勾股定理  

			// 下面四个条件判断是限制圆不能超出画布区域，如果不需要这个限制可以去掉这段代码  
			if(centerX_right - radius < 0) {
				radius = centerX_right;
			}
			if(centerY_right - radius < 0) {
				radius = centerY_right;
			}
			if(centerX_right + radius > $drawing_right.width()) {
				radius = $drawing_right.width() - centerX_right;
			}
			if(centerY_right + radius > $drawing_right.height()) {
				radius = $drawing_right.height() - centerY_right;
			}

			// 设置圆的大小和位置  
			$circle_right.css("left", centerX_right - radius + "px");
			$circle_right.css("top", centerY_right - radius + "px");
			$circle_right.css("width", 2 * radius + "px");
			$circle_right.css("height", 2 * radius + "px");
			$circle_right.css("border-radius", radius + "px");
			
			$img2_circle.css("left", (centerX_right - radius)*w3_right + "px");
			$img2_circle.css("top", (centerY_right - radius)*w3_right + "px");
			$img2_circle.css("width", 2 * radius*w3_right + "px");
			$img2_circle.css("height", 2 * radius*w3_right + "px");
			$img2_circle.css("border-radius", radius*w3_right + "px");
			
			$easyzoom2_circle.css("left", centerX_right - radius + "px");
			$easyzoom2_circle.css("top", centerY_right - radius + "px");
			$easyzoom2_circle.css("width", 2 * radius + "px");
			$easyzoom2_circle.css("height", 2 * radius + "px");
			$easyzoom2_circle.css("border-radius", radius + "px");
				
			radius_right = radius;
		}
	});

	// 鼠标松开停止画圆  
	$drawing_left.mouseup(function(event) {
		var num1 = $(".drawing_left").find(".circle").size();
		isDrawing_left = false;
		if(num1<=2) {
			$img1_drawing.append($img1_circle);
			$easyzoom1.append($easyzoom1_circle);
		}
		
		if(num1==1) {
			$("input[id='centerX_left1']")[0].value = centerX_left/winWid*w3_left;
			$("input[id='centerY_left1']")[0].value = centerY_left/winHei*w3_left;
			$("input[id='radius_left1']")[0].value = radius_left/winWid*w3_left;
		} else if(num1==2) {
			$("input[id='centerX_left2']")[0].value = centerX_left/winWid*w3_left;
			$("input[id='centerY_left2']")[0].value = centerY_left/winHei*w3_left;
			$("input[id='radius_left2']")[0].value = radius_left/winWid*w3_left;
		}
	});
	
	$drawing_right.mouseup(function(event) {
		var num2 = $(".drawing_right").find(".circle").size();
		isDrawing_right = false;
		if(num2<=2) {
			$img2_drawing.append($img2_circle);
			$easyzoom2.append($easyzoom2_circle);
		}
		
		if(num2==1) {
			$("input[id='centerX_right1']")[0].value = centerX_right/winWid*w3_right;
			$("input[id='centerY_right1']")[0].value = centerY_right/winHei*w3_right;
			$("input[id='radius_right1']")[0].value = radius_right/winWid*w3_right;
		} else if(num2==2) {
			$("input[id='centerX_right2']")[0].value = centerX_right/winWid*w3_right;
			$("input[id='centerY_right2']")[0].value = centerY_right/winHei*w3_right;
			$("input[id='radius_right2']")[0].value = radius_right/winWid*w3_right;
		}
	});
	
	//画图标志保存后显示在原图上
	if(document.getElementById("radius_left1").value>0) {
		var centerX = document.getElementById("centerX_left1").value * winWid;
		var centerY = document.getElementById("centerY_left1").value * winHei;
		var radius = document.getElementById("radius_left1").value * winWid;
		var $circle_left_img1 = $('<div class="circle"></div>');
		$circle_left_img1.css("left", centerX - radius + "px");
		$circle_left_img1.css("top", centerY - radius + "px");
		$circle_left_img1.css("width", 2 * radius + "px");
		$circle_left_img1.css("height", 2 * radius + "px");
		$circle_left_img1.css("border-radius", radius + "px");
		$img1_drawing.append($circle_left_img1);
		
		//两个图的宽度
		var w1 = document.getElementById("easyzoom1").offsetWidth;
		var w2 = document.getElementById("avatar1").offsetWidth;
		var w3 = (w2/w1).toFixed(6);
			
		var $circle_index1 = $('<div class="circle"></div>');
		$circle_index1.css("left", (centerX - radius)/w3 + "px");
		$circle_index1.css("top", (centerY - radius)/w3 + "px");
		$circle_index1.css("width", 2 * radius/w3 + "px");
		$circle_index1.css("height", 2 * radius/w3 + "px");
		$circle_index1.css("border-radius", radius/w3 + "px");
		$easyzoom1.append($circle_index1);
	}
	if(document.getElementById("radius_left2").value>0) {
		var centerX = document.getElementById("centerX_left2").value * winWid;
		var centerY = document.getElementById("centerY_left2").value * winHei;
		var radius = document.getElementById("radius_left2").value * winWid;
		
		var $circle_left_img2 = $('<div class="circle"></div>');
		$circle_left_img2.css("left", centerX - radius + "px");
		$circle_left_img2.css("top", centerY - radius + "px");
		$circle_left_img2.css("width", 2 * radius + "px");
		$circle_left_img2.css("height", 2 * radius + "px");
		$circle_left_img2.css("border-radius", radius + "px");
		$img1_drawing.append($circle_left_img2);
		
		//两个图的宽度
		var w1 = document.getElementById("easyzoom1").offsetWidth;
		var w2 = document.getElementById("avatar1").offsetWidth;
		var w3 = (w2/w1).toFixed(6);
			
		var $circle_index1 = $('<div class="circle"></div>');
		$circle_index1.css("left", (centerX - radius)/w3 + "px");
		$circle_index1.css("top", (centerY - radius)/w3 + "px");
		$circle_index1.css("width", 2 * radius/w3 + "px");
		$circle_index1.css("height", 2 * radius/w3 + "px");
		$circle_index1.css("border-radius", radius/w3 + "px");
		$easyzoom1.append($circle_index1);
	}
	if(document.getElementById("radius_right1").value>0) {
		var centerX = document.getElementById("centerX_right1").value * winWid;
		var centerY = document.getElementById("centerY_right1").value * winHei;
		var radius = document.getElementById("radius_right1").value * winWid;
		
		var $circle_right_img1 = $('<div class="circle"></div>');
		$circle_right_img1.css("left", centerX - radius + "px");
		$circle_right_img1.css("top", centerY - radius + "px");
		$circle_right_img1.css("width", 2 * radius + "px");
		$circle_right_img1.css("height", 2 * radius + "px");
		$circle_right_img1.css("border-radius", radius + "px");
		$img2_drawing.append($circle_right_img1);
		
		//两个图的宽度发大图标识
		var w1 = document.getElementById("easyzoom2").offsetWidth;
		var w2 = document.getElementById("avatar2").offsetWidth;
		var w3 = (w2/w1).toFixed(6);
		var $circle_index2 = $('<div class="circle"></div>');
		$circle_index2.css("left", (centerX - radius)/w3 + "px");
		$circle_index2.css("top", (centerY - radius)/w3 + "px");
		$circle_index2.css("width", 2 * radius/w3 + "px");
		$circle_index2.css("height", 2 * radius/w3 + "px");
		$circle_index2.css("border-radius", radius + "px");
		$easyzoom2.append($circle_index2);
	}
	if(document.getElementById("radius_right2").value>0) {
		
		var centerX = document.getElementById("centerX_right2").value * winWid;
		var centerY = document.getElementById("centerY_right2").value * winHei;
		var radius = document.getElementById("radius_right2").value * winWid;
		var $circle_right_img2 = $('<div class="circle"></div>');
		$circle_right_img2.css("left", centerX - radius + "px");
		$circle_right_img2.css("top", centerY - radius + "px");
		$circle_right_img2.css("width", 2 * radius + "px");
		$circle_right_img2.css("height", 2 * radius + "px");
		$circle_right_img2.css("border-radius", radius + "px");
		$img2_drawing.append($circle_right_img2);
		
		//两个图的宽度 发大图标识
		var w1 = document.getElementById("easyzoom2").offsetWidth;
		var w2 = document.getElementById("avatar2").offsetWidth;
		var w3 = (w2/w1).toFixed(6);
		var $circle_index2 = $('<div class="circle"></div>');
		$circle_index2.css("left", (centerX - radius)/w3 + "px");
		$circle_index2.css("top", (centerY - radius)/w3 + "px");
		$circle_index2.css("width", 2 * radius/w3 + "px");
		$circle_index2.css("height", 2 * radius/w3 + "px");
		$circle_index2.css("border-radius", radius + "px");
		$easyzoom2.append($circle_index2);
	}
});

//清除画
function removeCircle1() {
	$(".drawing_left").find(".circle").remove();
	$("#img1").find(".drawing").find(".circle").remove();
	$("#easyzoom1").find(".circle").remove();
	
	$("input[id='centerX_left1']")[0].value = 0;
	$("input[id='centerY_left1']")[0].value = 0;
	$("input[id='radius_left1']")[0].value = 0;
	$("input[id='centerX_left2']")[0].value = 0;
	$("input[id='centerY_left2']")[0].value = 0;
	$("input[id='radius_left2']")[0].value = 0;
}

//清除画
function removeCircle2() {
	$(".drawing_right").find(".circle").remove();
	$("#img2").find(".drawing").find(".circle").remove();
	$("#easyzoom2").find(".circle").remove();
	
	$("input[id='centerX_right1']")[0].value = 0;
	$("input[id='centerY_right1']")[0].value = 0;
	$("input[id='radius_right1']")[0].value = 0;
	$("input[id='centerX_right2']")[0].value = 0;
	$("input[id='centerY_right2']")[0].value = 0;
	$("input[id='radius_right2']")[0].value = 0;
}

function getUsersP06() {
	$.ajax({
		type: "POST",
		url: ctx + "/consultation/getUsersP06",
		async: false,
		success: function(data) {
			json = JSON.parse(data);
			if(json.UsersP06List.length==0) {
				$("input[id='isSubmit']")[0].value = 1;
				$('#noneModal').modal('show');
			} else {
				$("input[id='isSubmit']")[0].value = 1;
				// 显示加载
				$('#myModal').modal('show');

				$('#editfm').submit();
			}
		}
	});
}

function continueSubmit() {
	$("input[id='isSubmit']")[0].value = 3;
	$('#noneModal').modal('hide');
	//远程会诊中心无人在线时
	// 显示加载
	$('#myModal').modal('show');

	$('#editfm').submit();
}

$('#enlarge1').on('shown.bs.modal',function () {
	//两个图的宽度
	var w1 = document.getElementById("easyzoom1").offsetWidth;
	var w2 = document.getElementById("avatar1").offsetWidth;
	var w3 = (w2/w1).toFixed(6);
		
    if(document.getElementById("radius_left1").value>0) {
		var centerX = document.getElementById("centerX_left1").value * winWid;
		var centerY = document.getElementById("centerY_left1").value * winHei;
		var radius = document.getElementById("radius_left1").value * winWid;
		
		var $circle_index1 = $('<div class="circle"></div>');
		$circle_index1.css("left", (centerX - radius)/w3 + "px");
		$circle_index1.css("top", (centerY - radius)/w3 + "px");
		$circle_index1.css("width", 2 * radius/w3 + "px");
		$circle_index1.css("height", 2 * radius/w3 + "px");
		$circle_index1.css("border-radius", radius/w3 + "px");
		$("#easyzoom1").append($circle_index1);
	}
	if(document.getElementById("radius_left2").value>0) {
		var centerX = document.getElementById("centerX_left2").value * winWid;
		var centerY = document.getElementById("centerY_left2").value * winHei;
		var radius = document.getElementById("radius_left2").value * winWid;
			
		var $circle_index1 = $('<div class="circle"></div>');
		$circle_index1.css("left", (centerX - radius)/w3 + "px");
		$circle_index1.css("top", (centerY - radius)/w3 + "px");
		$circle_index1.css("width", 2 * radius/w3 + "px");
		$circle_index1.css("height", 2 * radius/w3 + "px");
		$circle_index1.css("border-radius", radius/w3 + "px");
		$("#easyzoom1").append($circle_index1);
	}
});

$('#enlarge2').on('shown.bs.modal',function () {
	//两个图的宽度
	var w1 = document.getElementById("easyzoom2").offsetWidth;
	var w2 = document.getElementById("avatar2").offsetWidth;
	var w3 = (w2/w1).toFixed(6);
		
    if(document.getElementById("radius_right1").value>0) {
		var centerX = document.getElementById("centerX_right1").value * winWid;
		var centerY = document.getElementById("centerY_right1").value * winHei;
		var radius = document.getElementById("radius_right1").value * winWid;
		
		var $circle_index2 = $('<div class="circle"></div>');
		$circle_index2.css("left", (centerX - radius)/w3 + "px");
		$circle_index2.css("top", (centerY - radius)/w3 + "px");
		$circle_index2.css("width", 2 * radius/w3 + "px");
		$circle_index2.css("height", 2 * radius/w3 + "px");
		$circle_index2.css("border-radius", radius/w3 + "px");
		$("#easyzoom2").append($circle_index2);
	}
	if(document.getElementById("radius_right2").value>0) {
		var centerX = document.getElementById("centerX_right2").value * winWid;
		var centerY = document.getElementById("centerY_right2").value * winHei;
		var radius = document.getElementById("radius_right2").value * winWid;
			
		var $circle_index2 = $('<div class="circle"></div>');
		$circle_index2.css("left", (centerX - radius)/w3 + "px");
		$circle_index2.css("top", (centerY - radius)/w3 + "px");
		$circle_index2.css("width", 2 * radius/w3 + "px");
		$circle_index2.css("height", 2 * radius/w3 + "px");
		$circle_index2.css("border-radius", radius/w3 + "px");
		$("#easyzoom2").append($circle_index2);
	}
});
