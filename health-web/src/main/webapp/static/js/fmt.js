
/**
 * 格式化时间
 * @param value
 * @param format
 * @returns
 */
function fmtDatebox(value, format) {  
	if (value == null || value == '') {  
	   return '';  
	}  
	var dt;  
	if (value instanceof Date) {  
	   dt = value;  
	} else {  
	   dt = new Date(value);  
	}  
	return dt.format(format); //扩展的Date的format方法(上述插件实现)  
} 

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//例子：
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function (format) {
var o = {  
      "M+": this.getMonth() + 1, // month  
      "d+": this.getDate(), // day  
      "h+": this.getHours(), // hour  
      "m+": this.getMinutes(), // minute  
      "s+": this.getSeconds(), // second  
      "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
      "S": this.getMilliseconds()  
      // millisecond  
  }  
  if (/(y+)/.test(format))  
      format = format.replace(RegExp.$1, (this.getFullYear() + "")  
          .substr(4 - RegExp.$1.length));  
  for (var k in o)  
      if (new RegExp("(" + k + ")").test(format))  
          format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
  return format;  
}


/**
 * 格式化是否
 * @param value
 * @returns {String}
 */
function fmtIsNot(value){  
    if (value == 0){  
        return '否';  
    } else if (value == 1) {  
        return '是';  
    } else {
    	return '未知';
    }
}

/**
 * 格式化状态
 * @param value
 * @returns {String}
 */
function fmtStatus(value){
	if (value == 0){  
        return $.i18n.prop("common.on");  
    } else if (value == 1) {  
        return $.i18n.prop("common.off");  
    } else {
    	return '';
    }
}


/**
 * 格式化代码值
 * @param value
 * @param parentCode
 * @returns
 */
var dictCache = {};
function fmtDictByCode(value, parentCode){

	if(!dictCache[value]) {
		$.ajax({
	        type: 'POST',
	        url: ctx+'/dict/getListByParentCode',
	        data:{parentCode: parentCode},
	        dataType: 'json',
	        async: false,
	        success: function (data) {
	        	for(var i in data){
	        		// 赋值
	        		dictCache[data[i].code]=data[i].name;
	        	}
	        }
		});
	}
	
	return dictCache[value];
	
}


/**
 * 格式化按钮
 * @param value
 * @returns {String}
 */
function fmtBtn(value){
	var str = '';
	str += '<button class="gridbtnedit  "  type="button" data-toggle="modal" data-target="#editModal" ';
	str += ' onclick="edit(\''+value+'\')">';
	str += '<i class="fa fa-pencil-square-o"></i>编辑</button>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '<button class="gridbtndel" type="button" onclick="del(\''+value+'\')">';
	str += '<i class="fa fa-trash-o"></i>删除</button>';	
	return str;
	
//	var str = '';
//	str += '<button class="btn btn-sm btn-warning" type="button" ';
//	str += 'data-toggle="modal" data-target="#editModal" style="background-color: #00c9ef!important;border-color: #00c9ef!important;border-radius: 4px;padding:4px 8px;" onclick="edit(\''+value+'\')">';
//	str += '<i class="fa fa-edit"></i> '+$.i18n.prop("button.edit")+'</button>';
//	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
//	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
//	str += '<button class="btn btn-sm btn-danger" type="button" onclick="del(\''+value+'\')">'
//	str += '<i class="fa fa-trash-o"></i> '+$.i18n.prop("button.delete")+'</button>';
//	return str;
}

function fmtViewBtn(value){
	var str = '';
	str += '<button class="gridbtnsea"  type="button" data-toggle="modal" data-target="#editModal" ';
	str += ' onclick="edit(\''+value+'\')">';
	str += '<i class="fa fa-eye"></i>查看</button>';
	return str;
}


function fmtfullBtn(value){
	var str = '';
	str += '<button class="gridbtnsea"  type="button" data-toggle="modal" data-target="#editModal" ';
	str += ' onclick="view(\''+value+'\')">';
	str += '<i class="fa fa-eye"></i>查看</button>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '<button class="gridbtnedit"  type="button" data-toggle="modal" data-target="#editModal" ';
	str += ' onclick="edit(\''+value+'\')">';
	str += '<i class="fa fa-edit"></i>编辑</button>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '<button class="gridbtndel" type="button" onclick="del(\''+value+'\')">';
	str += '<i class="fa fa-trash-o"></i>删除</button>';	
	return str;
}

/**
 * 操作按钮
 * @param value
 * @returns {String}
 */
function fullOperateBtn(value){
	var str = '';
	str += '<button class="btn btn-sm btn-success" type="button" ';
	str += 'data-toggle="modal" data-target="#editModal" onclick="add(\''+value+'\')">';
	str += '<i class="fa fa-plus"></i> '+$.i18n.prop("button.add")+'</button>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '<button class="btn btn-sm btn-warning" type="button" ';
	str += 'data-toggle="modal" data-target="#editModal" onclick="edit(\''+value+'\')">';
	str += '<i class="fa fa-edit"></i> '+$.i18n.prop("button.edit")+'</button>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '<button class="btn btn-sm btn-danger" type="button" onclick="del(\''+value+'\')">';
	str += '<i class="fa fa-trash-o"></i> '+$.i18n.prop("button.delete")+'</button>';
	return str;
}

/**
 * 操作按钮
 * @param value
 * @returns {String}
 */
function operateBtn(value){
	var str = '';
	str += '<button class="btn btn-sm btn-success" type="button" ';
	str += 'data-toggle="modal" data-target="#editModal" onclick="add(\''+value+'\')">';
	str += '<i class="fa fa-plus"></i> '+$.i18n.prop("button.add")+'</button>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '<button class="btn btn-sm btn-warning" type="button" ';
	str += 'data-toggle="modal" data-target="#editModal" onclick="edit(\''+value+'\')">';
	str += '<i class="fa fa-edit"></i> '+$.i18n.prop("button.edit")+'</button>';
	return str;
}


/**
 * 查看报告按钮
 * @param value
 * @returns {String}
 */
function reportBtn(value,status){
	var str = '';
	str += '<button class="btn btn-sm btn-warning" style="background-color: #00c9ef!important;border-color: #00c9ef!important;border-radius: 4px;padding:4px 8px;" type="button" ';
	str += ' onclick="report(\''+value+'\','+status+')">';
	str += '<i class="fa fa-eye"></i>查看</button>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	return str;
}

function reportEditBtn(value,status){
	var str = '';
	str += '<button class="gridbtnsea"  type="button" ';
	str += ' onclick="report(\''+value+'\','+status+')">';
	str += '<i class="fa fa-eye"></i>查看</button>';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '<button class="gridbtnedit"  type="button" ';
	str += ' onclick="edit(\''+value+'\','+status+')">';
	str += '<i class="fa fa-pencil-square-o"></i>编辑</button>';
	return str;
}

/**
 * 重置form
 * @param form
 * @returns
 */
$.fn.resetForm = function() {
	
	// 调用 DOM中的reset方法重置表单
	$(this)[0].reset();
	
	// 重置隐藏控件
	$(':input', $(this)).each(function() {
		var type = this.type;
	    if (type == 'hidden')
	      this.value = "";
	});
	
}

/**
 * 获取数据字典下拉列表
 */
$.fn.dictOptionByCode = function(parentCode){
	var thisSelect = $(this);
	$.getJSON(
			ctx + '/dict/getListByParentCode', 
			{parentCode: parentCode}, 
			function(d){
				// 先清空 后追加
				thisSelect.empty();
	        	//请选择
				thisSelect.append("<option value=''>"+ $.i18n.prop("common.select") +"</option>")
	        	for(var i in d){
	        		//填充下拉框数据
	        		thisSelect.append("<option value='"+ d[i].code +"'>"+d[i].name +"</option>"); 
	        	}
				
				// 可搜索下拉框
//				thisSelect.selectpicker({
//					liveSearch: true
//				});
			}
	);
	
}
