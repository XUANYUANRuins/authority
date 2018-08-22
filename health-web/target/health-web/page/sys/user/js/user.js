var oTable;
var tabTreeView;
$(function () {
	
	tabTreeView = $('#treeview');
	
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
        	{
        		title: $.i18n.prop("common.serial"),	//序号
        		data: null,
        		orderable: false,
        		class:"center",
        		render: function (data,type,full, meta) {
            		// 行数索引+每页起始索引+1
            		return meta.row + meta.settings._iDisplayStart +1;
            	}
        	},
            {	
            	title: $.i18n.prop("user.loginname"),	//用户名
            	class:"center",
            	data: "loginname",
            },
            {
            	title: $.i18n.prop("user.realname"),	//昵称
            	data: "realname",
            	class:"center",
            	defaultContent: ""
            },
            {
            	title: $.i18n.prop("user.position"),	//职位
            	data: "position",
            	defaultContent: "",
            	class:"center",
            	render: function (data,type,full) {
            		// 列表中根据code显示名称,第二个参数为parentCode
	                return fmtDictByCode(data, "p00");
                }
            },
            {
                title: $.i18n.prop("common.status"),	//状态
            	data: "status", 
            	class:"center",
                render: function (data,type,full) {
	                return fmtStatus(data);
                }
            },
            {
                title: $.i18n.prop("common.updatetime"),	//更新时间
            	data: "updatetime",
            	class:"center",
                render: function (data,type,full) {
                    return fmtDatebox(data, "yyyy-MM-dd hh:mm:ss");
                }
            },
            {
            	title: $.i18n.prop("common.operate"),	//操作
            	data: "id", 
            	class:"center",
            	render: function (data,type,full) {
            		// 操作按钮
            		return fmtBtn(data);
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
	//dictOption中的参数为parentCode
	$("#search_position").dictOptionByCode("p00");	//查询框
	$("#position").dictOptionByCode("p00");	//表单
	
	
	//表单验证
	validate();
});

//搜索参数
function buildSearchCriteria(search) {
	search['loginname'] = $("#search_loginname").val();
    search['realname'] = $("#search_realname").val();
    search['position'] = $("#search_position").val();
    search['startDate'] = $("#search_start").val();
    search['endDate'] = $("#search_end").val();
    search['status'] = $("#search_status").val();
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

//重置密码
function resetPassword() {
	//alertMsg("确定要重置密码吗?");
	if(confirm("确定要重置密码吗?")){
		$("[name='password']").val("224036cb2907e4eb840f307c11360580");
	}else{
		return false;
	}
}

//添加编辑页面
function edit(id) {
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
				getTree(data.roleids);
			} else {
				// 重置表单
				$('#editfm').resetForm();
				getTree(null);
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
		var checkedArr =  $('#treeview').treeview('getChecked');
		var roleids = '';
		for( var i=0; i<checkedArr.length; i++){
			roleids += checkedArr[i].id+",";
		}
		roleids = roleids.substr(0, roleids.length - 1);//去点最后一个逗号
		$('#roleids').val(roleids);
		
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
	
	var url = ctx+'/user/delete';
	var params = {
			id: id
		};
	delBootbox(url, params, search);
    	
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
function getTree(ids){
	$.ajax({
        type: 'POST',
        url: ctx+'/user/getTree',
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