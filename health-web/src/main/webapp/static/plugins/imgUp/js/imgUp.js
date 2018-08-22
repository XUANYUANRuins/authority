//修改
var oldW = "100%",
	oldH = "100%";
$(function() {
	var delParent;
	var defaults = {
		fileType: ["jpg", "png", "bmp", "jpeg", "JPG", "PNG", "BMP", "JPEG"], // 上传文件的类型
		fileSize: 1024 * 1024 * 10 // 上传文件的大小 10M
	};
	/*点击图片的文本框*/
	$(".file").change(function() {
		var idFile = $(this).attr("id");
		var file = document.getElementById(idFile);
		var imgNum = 1; // 上传的图片数
		var imgContainer = $(this).parents(".z_photo"); //存放图片的父亲元素
		var fileList = file.files; //获取的图片文件

		var input = $(this).parent(); //文本框的父亲元素
		var imgArr = [];
		//遍历得到的图片文件
		var numUp = imgContainer.find(".up-section").length;
		var totalNum = numUp + fileList.length; //总的数量
		if(fileList.length > imgNum || totalNum > imgNum) {
			alert("上传图片数目不可以超过" + imgNum + "个，请重新选择"); //一次选择上传超过 或者是已经上传和这次上传的到的总数也不可以超过设定的个数
		} else if(numUp < imgNum) {

			fileList = validateUp(fileList);
			for(var i = 0; i < fileList.length; i++) {
				var imgUrl = window.URL.createObjectURL(fileList[i]);
				imgArr.push(imgUrl);
				var $section = $("<section class='up-section fl loading'>");
				imgContainer.prepend($section);
				var $span = $("<span class='up-span'>");
				$span.appendTo($section);

				var $img0 = $("<img class='close-upimg'>").on("click", function(event) {
					event.preventDefault();
					event.stopPropagation();
					$(".works-mask").show();
					delParent = $(this).parent();
				});
				$img0.attr("src", impUpPath + "/img/a7.png").appendTo($section);

				var $img = $("<img class='up-img up-opcity'>");
				
				$img.attr("src", imgArr[i]);
				$img.appendTo($section);
				var $p = $("<p class='img-name-p'>");
				$p.html(fileList[i].name).appendTo($section);
				var $input = $("<input id='taglocation' name='taglocation' value='' type='hidden'>");
				$input.appendTo($section);
				var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
				$input2.appendTo($section);
				
				//等比例上传
			var f = file.files[0];
			var reader = new FileReader();
			reader.onload = function(e) {
				var data = e.target.result;
				//加载图片获取图片真实宽度和高度  
				var image = new Image();
				image.onload = function() {
					var w = image.width;
					var h = image.height;
					if(w > h) {
						var i = Math.round(h * 100) / w;
						oldH = i.toString() + "%";
						oldW = "100%"
					} else {
						var i = Math.round(w * 100) / h;
						oldW = i.toString() + "%";
						oldH = "100%"
					}
					$img.attr("style", "width:"+oldW+";height:"+oldH);
				};
				image.src = data;
			};
			reader.readAsDataURL(f);
			}
		}
		setTimeout(function() {
			$(".up-section").removeClass("loading");
			$(".up-img").removeClass("up-opcity");
		}, 450);
		numUp = imgContainer.find(".up-section").length;
		if(numUp >= imgNum) {
			$(this).parent().hide();
		}
	});

	$(".z_photo").delegate(".close-upimg", "click", function() {
		$(".works-mask").show();
		delParent = $(this).parent();
	});

	$(".wsdel-ok").click(function() {
		$(".works-mask").hide();
		var numUp = delParent.siblings().length;
		if(numUp < 6) {
			delParent.parent().find(".z_file").show();
		}
		delParent.parent().find(".z_file .file").val("");
		delParent.remove();
	});

	$(".wsdel-no").click(function() {
		$(".works-mask").hide();
	});

	function validateUp(files) {
		var arrFiles = []; //替换的文件数组
		for(var i = 0, file; file = files[i]; i++) {
			//获取文件上传的后缀名
			var newStr = file.name.split("").reverse().join("");
			if(newStr.split(".")[0] != null) {
				var type = newStr.split(".")[0].split("").reverse().join("");
				//console.log(type + "===type===");
				if(jQuery.inArray(type, defaults.fileType) > -1) {
					// 类型符合，可以上传
					if(file.size >= defaults.fileSize) {
						alert(file.size);
						alert('您这个"' + file.name + '"文件大小过大');
					} else {
						// 在这里需要判断当前所有文件中
						arrFiles.push(file);
					}
				} else {
					alert('您这个"' + file.name + '"上传类型不符合');
				}
			} else {
				alert('您这个"' + file.name + '"没有类型, 无法识别');
			}
		}
		return arrFiles;
	}

});

createReader = function(file, whenReady) {
	var reader = new FileReader;
	reader.readAsDataURL(file);
	reader.onload = function(evt) {
		var image = new Image();
		image.onload = function() {
			var width = this.width;
			var height = this.height;
			if(whenReady) whenReady(width, height);
		};
		image.src = evt.target.result;
	};
}