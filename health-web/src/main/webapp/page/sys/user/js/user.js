var oTable;
var tabTreeView;
var tabTreeView1;
$(function () {
	
	tabTreeView = $('#treeview');
	tabTreeView1 = $('#treeview1');
	
	oTable = $('#listDataTable').DataTable({
        ajax: {
            url: ctx+'/user/getPage',
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
        		title: '<input type="checkbox" class="checkall" /> '+$.i18n.prop("common.checkall"),
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
            	title: "登录名",	//登录名
            	class:"center",
            	data: "loginname",
            	defaultContent: ""
            },
            {	
            	title: "用户ID",	//用户ID
            	class:"center",
            	data: "loginname",
            	defaultContent: ""
            },
            {
            	title: "真实姓名",	//真实姓名
            	data: "realname",
            	class:"center",
            	defaultContent: ""
            },
//          {
//          	title: $.i18n.prop("user.position"),	//职位
//          	data: "position",
//          	defaultContent: "",
//          	class:"center",
//          	render: function (data,type,full) {
//          		// 列表中根据code显示名称,第二个参数为parentCode
//	                return fmtDictByCode(data, "p00");
//              }
//          },

            {
                title: "更新时间",	//更新时间
            	data: "updatetime",
            	class:"center",
                render: function (data,type,full) {
                    return fmtDatebox(data, "yyyy-MM-dd hh:mm:ss");
                },
                defaultContent: ""
            },
            {
                title: "状态",	//状态
            	data: "status", 
            	class:"center",
                render: function (data,type,full) {
	                return fmtStatus(data);
                },
                defaultContent: ""
            },
            {
            	title: "手机号",	//手机号
            	data: "phone",
            	class:"center",
            	defaultContent: ""
            },
            {
            	title: "邮箱",	//邮箱
            	data: "email",
            	class:"center",
            	defaultContent: ""
            },
            {
            	title: "机构",	//机构
            	data: "orgId",
            	class:"center",
            	defaultContent: ""
            },
            {
            	title: $.i18n.prop("common.operate"),	//操作
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
	
	//填充下拉框 
	$("#search_orgId").orgIdOptionByStatus("1");	//查询框
	$("#orgId").orgIdOptionByStatus("1");	//表单
	
	//刷新操作设备
	//refleshTerminalList();
	
	//表单验证
	validate();
});

//搜索参数
function buildSearchCriteria(search) {
	search['loginname'] = $("#search_loginname").val();
    search['realname'] = $("#search_realname").val();
    search['phone'] = $("#search_phone").val();
    search['status'] = $("#search_status").val();
    search['orgId'] = $("#search_orgId").val();
    
    search['startDate'] = $("#search_start").val();
    search['endDate'] = $("#search_end").val();  
    return search;  
}
//搜索
function search(){
    oTable.ajax.reload( null, false ); // 刷新表格数据，分页信息不会重置
	$.ajax({
		url : ctx+'/user/getPage',
        method : 'post',
        dataType : 'json',
        cache : 'false',
        data:{
        	"loginname":$("#search_loginname").val(),
        	"realname":$("#search_realname").val(),
        	"phone":$("#search_phone").val(),
        	"status":$("#search_status").val(),
        	"orgId":$("#search_orgId").val(),
        	"startDate":$("#search_start").val(),
        	"endDate":$("#search_end").val()
        },
        success : function(data){
        },
        error : function(data){
        }
	});	
}

//重置密码
function resetPassword() {
	//alertMsg("确定要重置密码吗?");
	if(confirm("确定要重置密码吗?")){
		$("[name='password']").val("224036cb2907e4eb840f307c11360580");
	}else{
		return false;
	}
}

//添加查看页面
function view(id) {
	$("#editfm input").attr("disabled","disabled");
	$("#editfm select").attr("disabled","disabled");
	$("#resetPwd").attr("disabled","disabled");
	$("#saveBtn").hide();
	$("#cancleBtn").hide();
	$("#returnBtn").show();
	// 验证重置
	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
	if(id == null){
		$("[name='password']").closest("div.form-group").hide();
	}else{
		$("[name='password']").closest("div.form-group").show();
	}
	
	$.ajax({
		url : ctx + '/user/edit',
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
				getRoleTree(data.roleids);
				getTerminalTree(data.terminalids,data.orgId);
			} else {
				// 重置表单
				$('#editfm').resetForm();
				getRoleTree(null);
				
				var orgId = $("#orgId").val();
				if(orgId == ""){
					$.getJSON(
						ctx + '/user/getLoginOrgId', 
						function(d){
							if(d!=null){
								getTerminalTree(null,d);
							}else{
								getTerminalTree(null,null);
							}
						}
					);
				}else{
					getTerminalTree(null,orgId);
				}
				//getTerminalTree(null,null);
			}
		},
        error: function(err) {
            alertMsg($.i18n.prop("message.error"));
        }
	});
}

//添加编辑页面
function edit(id) {
	$("#editfm input").removeAttr("disabled");
	$("#editfm select").removeAttr("disabled");
	$("#resetPwd").removeAttr("disabled");
	$("#saveBtn").show();
	$("#cancleBtn").show();
	$("#returnBtn").hide();
	// 验证重置
	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
	if(id == null){
		$("[name='password']").closest("div.form-group").hide();
	}else{
		$("[name='password']").closest("div.form-group").show();
	}
	
	$.ajax({
		url : ctx + '/user/edit',
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
				getRoleTree(data.roleids);
				getTerminalTree(data.terminalids,data.orgId);
			} else {
				// 重置表单
				$('#editfm').resetForm();
				getRoleTree(null);
				
				var orgId = $("#orgId").val();
				if(orgId == ""){
					$.getJSON(
						ctx + '/user/getLoginOrgId', 
						function(d){
							if(d!=null){
								getTerminalTree(null,d);
							}else{
								getTerminalTree(null,null);
							}
						}
					);
				}else{
					getTerminalTree(null,orgId);
				}
				//getTerminalTree(null,null);
			}
		},
        error: function(err) {
            alertMsg($.i18n.prop("message.error"));
        }
	});
}
//数据更新
function update() {
	// 触发表单验证
	var bootstrapValidator = $("#editfm").data('bootstrapValidator');
	bootstrapValidator.validate();
	
	if(bootstrapValidator.isValid()){
		
		//选中的节点
		//角色
		var checkedArr =  $('#treeview').treeview('getChecked');
		var roleids = '';
		for( var i=0; i<checkedArr.length; i++){
			roleids += checkedArr[i].id+",";
		}
		roleids = roleids.substr(0, roleids.length - 1);//去点最后一个逗号
		$('#roleids').val(roleids);
		
		//设备
		checkedArr =  $('#treeview1').treeview('getChecked');
		var terminalids = '';
		for( var i=0; i<checkedArr.length; i++){
			terminalids += checkedArr[i].id+",";
		}
		terminalids = terminalids.substr(0, terminalids.length - 1);//去点最后一个逗号
		$('#terminalids').val(terminalids);
		
		// form表单数据转化
		// jquery自带的serializeArray方法转为数组格式
		// jquery.serialize-object插件的serializeObject转为对象格式，serializeJSON转为json
		var formdata = $("#editfm").serializeObject();
		var loginname = document.getElementById('loginname').value;
		var userid = document.getElementById('userid').value;
		
			$.ajax({
	        url: ctx+'/user/update',
	        type: 'POST',
	        dataType: 'json',
        	data: formdata,
	        success : function(result) {
	        	var isRepeat = parseInt(result.isRepeat);
	        	if(isRepeat==0) {
	        		// 模态框隐藏
		        	$("#editModal").modal("hide");
		        	// 重新加载
		        	search();
		        	// 消息提示
		        	//autoCloseMsg(result.msg);
	        	} else if(isRepeat==1) {
	        		alert("用户名重复，请重新输入！");
	        	}
		        	
	        },
	        error: function(err) {
	            alertMsg($.i18n.prop("message.error"));
	        }
	   });
		
	}
}

//数据删除
function del(id) {
	var msg = "确认要删除这个用户吗?";
	var url = ctx+'/user/delete';
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
		alertMsg("请至少选择一个用户！");
		return;
	} else {
		checked.each(function(){
			ids.push($(this).val())
		});
	}
    
	//var msg = $.i18n.prop("message.batchOff");
	var msg = "确认要批量删除这些用户吗?";
	var url = ctx+'/user/batchDelete';
	var params = {
			ids: ids
		};
	msgBootbox(msg, url, params, search);	
}

// 批量停用
function batchOff(){
	var checked = $(".checkchild:checked");
	var ids = [];
	
	if (checked.length < 1){
		alertMsg($.i18n.prop("message.leastOne"));
		return;
	} else {
		checked.each(function(){
			ids.push($(this).val())
		});
	}
    
	var msg = $.i18n.prop("message.batchOff");
	var url = ctx+'/user/batchOff';
	var params = {
			ids: ids
		};
	msgBootbox(msg, url, params, search);
	
}

// 获取树数据
function getRoleTree(ids){
	$.ajax({
        type: 'POST',
        url: ctx+'/user/getRoleTree',
        data:{ids:ids},
        dataType: 'json',
        async: false,
        success: function (data) {
             var treeData = eval(data);
             // 只有一级的树结构
             tabTreeView.treeview({
         	    data: treeData,//数据
         	    showIcon: false,
         	    showCheckbox: true
         	});
        },
        error: function(err) {
            alertMsg($.i18n.prop("message.error"));
        }
	});
}

function getTerminalTree(ids,orgId){
	//alert("ids:"+ids+",orgId:"+orgId);
	$.ajax({
        type: 'POST',
        url: ctx+'/user/getTerminalTree',
        data:{ids:ids,orgId:orgId},
        dataType: 'json',
        async: false,
        success: function (data) {
             var treeData = eval(data);
             // 只有一级的树结构
             tabTreeView1.treeview({
         	    data: treeData,//数据
         	    showIcon: false,
         	    showCheckbox: true
         	});
        },
        error: function(err) {
            alertMsg($.i18n.prop("message.error"));
        }
	});
}

function refleshTerminalList(){
	var orgId = $("#orgId").val();	
	if(orgId == ""){
		$.getJSON(
			ctx + '/user/getLoginOrgId', 
			function(d){
				if(data!=null){
					getTerminalTree(null,d);
				}else{
					getTerminalTree(null,null);
				}
			}
		);
	}else{
		getTerminalTree(null,orgId);
	}	
}

//表单验证
function validate(){
	$('#editfm').bootstrapValidator({
		excluded: [':disabled'],
		fields: {/*验证*/
        	
			loginname: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    },
                    regexp:{/* 数字、字母、下划线 */
                    	regexp: /^[a-zA-Z0-9_]+$/,
                    	message: $.i18n.prop("message.letterNumber")
                    }
                }
            },
            
            realname: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    }
                }
            }
            
        }
    });
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