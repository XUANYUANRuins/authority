var oTable;
var tabTreeView;
$(function () {
	
	tabTreeView = $('#treeview');
	
	oTable = $('#listDataTable').DataTable({
        ajax: {
            url: ctx+'/terminal/getPage',
            type: 'POST',
            data: buildSearchCriteria
        },
		
//		ajax:{
//			url : ctx+'/user/getPage',
//	        method : 'post',
//	        dataType : 'json',
//	        cache : 'false',
//	        data:{
//	        	"loginname":$("#search_loginname").val(),
//	        	"realname":$("#search_realname").val(),
//	        	"startDate":$("#search_start").val(),
//	        	"endDate":$("#search_end").val(),
//	        	"status":$("#search_status").val()
//	        }
//		},
        columns: [
        	{
        		title: '<input type="checkbox" class="checkall" />',
        		data: "id", 
        		render: function (data, type, full, meta) {
                    return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                },
                class:"center",
                bSortable: false
        	},
//      	{
//      		title: $.i18n.prop("common.serial"),	//序号
//      		data: null,
//      		orderable: false,
//      		class:"center",
//      		render: function (data,type,full, meta) {
//          		// 行数索引+每页起始索引+1
//          		return meta.row + meta.settings._iDisplayStart +1;
//          	}
//      	},
            {	
            	title: '相机品牌',	//相机品牌
            	class:"center",
            	data: "cameraBrand",
            	defaultContent: ""
            },
            {
            	title: '相机型号',	//相机型号
            	class:"center",
            	data: "cameraModel",
            	defaultContent: ""
            },
            {	
            	title: '所属机构',	//所属机构
            	class:"center",
            	data: "orgId",
            	defaultContent: ""
            },
            {
            	title: '生产地',	//生产地
            	data: "cameraProducer",
            	class:"center",
            	defaultContent: ""
            },
            {	
            	title: '生产时间',	//生产时间
            	class:"center",
            	data: "cameraProduceTime",
            	render: function (data,type,full) {
                    return fmtDatebox(data, "yyyy-MM-dd");
                },
                defaultContent: ""
            },
            {	
            	title: '购置时间',	//购置时间
            	class:"center",
				data: "cameraBuyTime",
            	render: function (data,type,full) {
                    return fmtDatebox(data, "yyyy-MM-dd");
                },
                defaultContent: ""
            },
            {
            	title: '中心分辨率',	//中心分辨率
            	data: "centralResolution",
            	class:"center",
            	defaultContent: ""
            },
            {	
            	title: '视角',	//视角
            	class:"center",
            	data: "cameraAngle",
            	defaultContent: ""
            },           
            {	
            	title: 'CCD分辨率',	//CCD分辨率
            	class:"center",
            	data: "ccdResolution",
            	defaultContent: ""
            },          
            {	
            	title: '终端IP',	//终端IP
            	class:"center",
            	data: "trmlIp",
            	defaultContent: ""
            },
            {	
            	title: '终端端口',	//终端端口
            	class:"center",
            	data: "trmlPort",
            	defaultContent: ""
            },         
            {
            	title: '操作',	//操作
            	data: "id", 
            	class:"center",
            	render: function (data,type,full) {
            		// 操作按钮
            		return fmtfullBtn(data);
            	}
            }
        ]
    });
	
	// 列表全选
	$(".checkall").click(function () {
	      var check = $(this).prop("checked");
	      $(".checkchild").prop("checked", check);
	});
	
	//时间控件
	$(".form_datetime").each(function (){
		$(this).fdatepicker({format: 'yyyy-mm-dd'});
		// 需要开启时间选择器，如下所示：
		/*
		$('.form_datetime').fdatepicker({
			format: 'yyyy-mm-dd hh:ii',
			pickTime: true
		});
		*/
	});
	//填充下拉框 
	$("#search_orgId").orgIdOptionByStatus("1");	//查询框
	$("#orgId").orgIdOptionByStatus("1");	//表单

	//表单验证
	//validate();
});

//搜索参数
function buildSearchCriteria(search) {
	search['cameraBrand'] = $("#search_cameraBrand").val();
    search['cameraModel'] = $("#search_cameraModel").val();
    search['cameraProducer'] = $("#search_cameraProducer").val();
    search['orgId'] = $("#search_orgId").val();
    search['centralResolution'] = $("#search_centralResolution").val();
    search['ccdResolution'] = $("#search_ccdResolution").val();
    
    search['produceStartDate'] = $("#search_produce_start").val();
    search['produceEndDate'] = $("#search_produce_end").val();
    search['buyStartDate'] = $("#search_buy_start").val();
    search['buyEndDate'] = $("#search_buy_end").val();
    return search;  
}
//搜索
function search(){
    oTable.ajax.reload( null, false ); // 刷新表格数据，分页信息不会重置
	$.ajax({
		url : ctx+'/terminal/getPage',
        method : 'post',
        dataType : 'json',
        cache : 'false',
        data:{
        	"cameraBrand":$("#search_cameraBrand").val(),
        	"cameraModel":$("#search_cameraModel").val(),
        	"cameraProducer":$("#search_cameraProducer").val(),
        	"orgId":$("#search_orgId").val(),
        	"centralResolution":$("#search_centralResolution").val(),
        	"ccdResolution":$("#search_ccdResolution").val(),
        	"produceStartDate":$("#search_produce_start").val(),
        	"produceEndDate":$("#search_produce_end").val(),
        	"buyStartDate":$("#search_buy_start").val(),
        	"buyEndDate":$("#search_buy_end").val()
        },
        success : function(data){
        },
        error : function(data){
        }
	});	
}

////重置密码
//function resetPassword() {
//	//alertMsg("确定要重置密码吗?");
//	if(confirm("确定要重置密码吗?")){
//		$("[name='password']").val("224036cb2907e4eb840f307c11360580");
//	}else{
//		return false;
//	}
//}

//添加查看页面
function view(id) {
	$("#editfm input").attr("disabled","disabled");
	$("#editfm select").attr("disabled","disabled");
	$("#saveBtn").hide();
	$("#cancleBtn").hide();
	$("#returnBtn").show();
	// 验证重置
//	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
//	if(id == null){
//		$("[name='password']").closest("div.form-group").hide();
//	}else{
//		$("[name='password']").closest("div.form-group").show();
//	}
	
	$.ajax({
		url : ctx + '/terminal/edit',
		method : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if(data!=null){
				// 填充表单
				$('#editfm').autofill(data);
				// 给下拉框赋值
//				$("#position").selectpicker('val', data.position);
//				getTree(data.roleids);
			} else {
				// 重置表单
				$('#editfm').resetForm();
//				getTree(null);
			}
		},
        error: function(err) {
            //alertMsg($.i18n.prop("message.error"));
        }
	});
}

//添加编辑页面
function edit(id) {
	$("#editfm input").removeAttr("disabled");
	$("#editfm select").removeAttr("disabled");
	$("#saveBtn").show();
	$("#cancleBtn").show();
	$("#returnBtn").hide();
	// 验证重置
//	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
//	if(id == null){
//		$("[name='password']").closest("div.form-group").hide();
//	}else{
//		$("[name='password']").closest("div.form-group").show();
//	}
	
	$.ajax({
		url : ctx + '/terminal/edit',
		method : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if(data!=null){
				// 填充表单
				$('#editfm').autofill(data);
				// 给下拉框赋值
//				$("#position").selectpicker('val', data.position);
//				getTree(data.roleids);
			} else {
				// 重置表单
				$('#editfm').resetForm();
//				getTree(null);
			}
		},
        error: function(err) {
            //alertMsg($.i18n.prop("message.error"));
        }
	});
}
//数据更新
function update() {
	// 触发表单验证
//	var bootstrapValidator = $("#editfm").data('bootstrapValidator');
//	bootstrapValidator.validate();
//	
//	if(bootstrapValidator.isValid()){
//		
//		//选中的节点
//		var checkedArr =  $('#treeview').treeview('getChecked');
//		var roleids = '';
//		for( var i=0; i<checkedArr.length; i++){
//			roleids += checkedArr[i].id+",";
//		}
//		roleids = roleids.substr(0, roleids.length - 1);//去点最后一个逗号
//		$('#roleids').val(roleids);
//		
//		// form表单数据转化
//		// jquery自带的serializeArray方法转为数组格式
//		// jquery.serialize-object插件的serializeObject转为对象格式，serializeJSON转为json
//		var formdata = $("#editfm").serializeObject();
//		var loginname = document.getElementById('loginname').value;
//		var userid = document.getElementById('userid').value;
//		
//			$.ajax({
//	        url: ctx+'/user/update',
//	        type: 'POST',
//	        dataType: 'json',
//      	data: formdata,
//	        success : function(result) {
//	        	var isRepeat = parseInt(result.isRepeat);
//	        	if(isRepeat==0) {
//	        		// 模态框隐藏
//		        	$("#editModal").modal("hide");
//		        	// 重新加载
//		        	search();
//		        	// 消息提示
//		        	//autoCloseMsg(result.msg);
//	        	} else if(isRepeat==1) {
//	        		alert("用户名重复，请重新输入！");
//	        	}
//		        	
//	        },
//	        error: function(err) {
//	            alertMsg($.i18n.prop("message.error"));
//	        }
//	   });
//		
//	} 

//	if(!check()){
//		return;
//	}
	
	var formdata = $("#editfm").serializeObject();
			$.ajax({
	        url: ctx+'/terminal/update',
	        type: 'POST',
	        dataType: 'json',
        	data: formdata,
	        success : function(result) {
	        	var operation = result.operation;
				if(operation == "add"){
					autoCloseMsg("新增设备成功！");
				}else{
					autoCloseMsg("编辑设备成功！");
				}
				
				// 模态框隐藏
		        $("#editModal").modal("hide");
		        // 重新加载
		        search(); 	
	        },
	        error: function(err) {
	        	if(operation == "add"){
					alert("新增设备失败！");
				}else{
					alert("编辑设备失败！");
				}
	            //alertMsg($.i18n.prop("message.error"));
	        }
	 });
}

//数据删除
function del(id) {
	var msg = "确认要删除这台设备吗？";
	var url = ctx+'/terminal/delete';
	var params = {
			id: id
		};
	msgBootbox(msg, url, params, search);
}

// 批量删除
function batchDelete(){
	var checked = $(".checkchild:checked");
	var ids = [];
	
	if (checked.length < 1){
		alertMsg("请至少选择一台设备！");
		return;
	} else {
		checked.each(function(){
			ids.push($(this).val())
		});
	}
    
	//var msg = $.i18n.prop("message.batchOff");
	var msg = "确认要批量删除这些设备吗?";
	var url = ctx+'/terminal/batchDelete';
	var params = {
			ids: ids
		};
	msgBootbox(msg, url, params, search);	
}


/**
 * 获取所属机构下拉列表
 */
$.fn.orgIdOptionByStatus = function(status){
	var thisSelect = $(this);
	$.getJSON(
			ctx + '/organization/getListByStatus', 
			{status: status}, 
			function(d){
				// 先清空 后追加
				thisSelect.empty();
	        	//请选择
				thisSelect.append("<option value=''>请选择</option>")
	        	for(var i in d){
	        		//填充下拉框数据
	        		thisSelect.append("<option value='"+ d[i].orgId +"'>"+d[i].orgName +"</option>"); 
	        	}
				
				// 可搜索下拉框
//				thisSelect.selectpicker({
//					liveSearch: true
//				});
			}
	);
	
}

//
//// 获取树数据
//function getTree(ids){
//	$.ajax({
//      type: 'POST',
//      url: ctx+'/user/getTree',
//      data:{ids:ids},
//      dataType: 'json',
//      async: false,
//      success: function (data) {
//           var treeData = eval(data);
//           // 只有一级的树结构
//           tabTreeView.treeview({
//       	    data: treeData,//数据
//       	    showIcon: false,
//       	    showCheckbox: true
//       	});
//      },
//      error: function(err) {
//          alertMsg($.i18n.prop("message.error"));
//      }
//	});
//}


//表单验证
function validate(){
	$('#editfm').bootstrapValidator({
		excluded: [':disabled'],
		fields: {/*验证*/
        	
			orgId: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: "机构编号为必填项"
                    }
                }
            },
            
            orgName: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: "机构名称为必填项"
                    }
                }
            },
            
            orgPhone: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: "机构电话为必填项"
                    }
                }
            }           
        }
    });
}

function check(){
	var cameraBrand = $("#cameraBrand").val();
	if(cameraBrand.trim() == ""){
		alert("相机品牌为必填项");
		$("#cameraBrand").focus();
		return false;
	}
	return true;
}
