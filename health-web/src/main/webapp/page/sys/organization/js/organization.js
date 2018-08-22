var oTable;
var tabTreeView;
$(function () {
	
	tabTreeView = $('#treeview');
	
	oTable = $('#listDataTable').DataTable({
        ajax: {
            url: ctx+'/organization/getPage',
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
            	title: '机构编号',	//机构编号
            	class:"center",
            	data: "orgId",
            },
            {	
            	title: '机构名称',	//机构名称
            	class:"center",
            	data: "orgName",
            },
            {
                title: '状态',	//状态
            	data: "status", 
            	class:"center",
                render: function (data,type,full) {
                	if(data == 1){
                		return "有效";
                	}else{
                		return "无效";
                	}
                }
            },
            {
            	title: '地址',	//地址
            	data: "orgAddress",
            	class:"center",
            	defaultContent: ""
            },
            {	
            	title: '机构电话',	//机构电话
            	class:"center",
            	data: "orgPhone"
            },           
            {	
            	title: '固定电话',	//固定电话
            	class:"center",
            	data: "fixedTelephone"
            },          
            {	
            	title: '联系人',	//联系人
            	class:"center",
            	data: "linkman"
            },
            {	
            	title: '登记时间',	//登记时间
            	class:"center",
            	data: "insertDate",
            	render: function (data,type,full) {
                    return fmtDatebox(data, "yyyy-MM-dd");
                }
            },
            {	
            	title: '更新时间',	//更新时间
				data: "updateDate",
				class:"center",
            	render: function (data,type,full) {
                    return fmtDatebox(data, "yyyy-MM-dd");
                }
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
    
//	//填充下拉框 
//	//dictOption中的参数为parentCode
//	$("#search_position").dictOptionByCode("p00");	//查询框
//	$("#position").dictOptionByCode("p00");	//表单
	
	
	//表单验证
	//validate();
});

//搜索参数
function buildSearchCriteria(search) {
	search['orgName'] = $("#search_orgName").val();
    search['orgId'] = $("#search_orgId").val();
    search['orgPhone'] = $("#search_orgPhone").val();
    search['linkman'] = $("#search_linkman").val();
    
    search['startDate'] = $("#search_start").val();
    search['endDate'] = $("#search_end").val();
    search['status'] = $("#search_status").val();
    return search;  
}
//搜索
function search(){
    oTable.ajax.reload( null, false ); // 刷新表格数据，分页信息不会重置
	$.ajax({
		url : ctx+'/organization/getPage',
        method : 'post',
        dataType : 'json',
        cache : 'false',
        data:{
        	"orgName":$("#search_orgName").val(),
        	"orgId":$("#search_orgId").val(),
        	"orgPhone":$("#search_orgPhone").val(),
        	"linkman":$("#linkman").val(),
        	"startDate":$("#search_start").val(),
        	"endDate":$("#search_end").val(),
        	"status":$("#search_status").val()
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
		url : ctx + '/organization/edit',
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
		url : ctx + '/organization/edit',
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

	if(!check()){
		return false;
	}

	var formdata = $("#editfm").serializeObject();
			$.ajax({
	        url: ctx+'/organization/update',
	        type: 'POST',
	        dataType: 'json',
        	data: formdata,
	        success : function(result) {
	        	var operation = result.operation;
				if(operation == "add"){
					autoCloseMsg("新增机构成功！");
				}else{
					autoCloseMsg("编辑机构成功！");
				}
				
				// 模态框隐藏
		        $("#editModal").modal("hide");
		        // 重新加载
		        search(); 	
	        },
	        error: function(err) {
	        	if(operation == "add"){
					alert("新增机构失败！");
				}else{
					alert("编辑机构失败！");
				}
	            //alertMsg($.i18n.prop("message.error"));
	        }
	 });
}

//数据删除
function del(id) {
	var msg = "确认要删除这个机构吗？";
	var url = ctx+'/organization/delete';
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
		alertMsg("请至少选择一个机构！");
		return;
	} else {
		checked.each(function(){
			ids.push($(this).val())
		});
	}
    
	//var msg = $.i18n.prop("message.batchOff");
	var msg = "确认要批量删除这些机构吗?";
	var url = ctx+'/organization/batchDelete';
	var params = {
			ids: ids
		};
	msgBootbox(msg, url, params, search);	
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
	var orgName = $("#orgName").val();
	if(orgName.trim() == ""){
		alert("机构名称为必填项");
		$("#orgName").focus();
		return false;
	}
	return true;
}