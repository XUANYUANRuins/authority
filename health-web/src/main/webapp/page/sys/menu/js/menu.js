var oTable;
$(function () {
	
	oTable = $('#listDataTable').DataTable({
        ajax: {
            url: ctx+'/menu/getPage',
            type: 'POST',
            data: buildSearchCriteria
        },
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
//      		title: $.i18n.prop("common.serial"),
//      		data: null,
//      		orderable: false,
//      		class:"center",      		
//      		render: function (data,type,full, meta) {
//          		// 行数索引+每页起始索引+1
//          		return meta.row + meta.settings._iDisplayStart +1;
//          	}
//      	},
            {
            	title: $.i18n.prop("menu.name"),	//名称
            	class:"center",
            	data: "name",
                defaultContent: ""
            },
            {
            	title: $.i18n.prop("menu.url"),	//路径
            	class:"center",
            	data: "url",
                defaultContent: ""
            },
            {
            	title: $.i18n.prop("menu.icon"),	//图标
            	data: "icon", 
            	class:"center",
            	defaultContent: ""
            },
            {
            	title: $.i18n.prop("common.status"),	//状态
            	data: "status", 
            	class:"center",
            	render: function (data,type,full) {
	                return fmtStatus(data);
                },
                defaultContent: ""
            },
            {
                
            	title: $.i18n.prop("common.updatetime"),	//更新时间
            	data: "updatetime",
            	class:"center",
                render: function (data,type,full) {
                    return fmtDatebox(data, "yyyy-MM-dd hh:mm:ss");
                },
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
	
	//表单验证
	validate();
	//父菜单下拉框的填充
	pushMenus();
    
});



//搜索参数
function buildSearchCriteria(search) {  
    search['name'] = $("#search_name").val();  
    search['url'] = $("#search_url").val();
    search['status'] = $("#search_status").val();
    return search;  
}
//搜索
function search(){
	pushMenus();
    oTable.ajax.reload( null, false ); // 刷新表格数据，分页信息不会重置
}

//父菜单下拉框的填充
function pushMenus(){
	$.ajax({
    	type: "POST",
    	dataType:"json",
        url: ctx + '/menu/getParentMenus',
        success: function(data) {
        	// 先清空 后追加
        	$("#parentid").empty();
        	$("#parentid").append("<option value='0'>"+ $.i18n.prop("common.root") +"</option>");
        	for(var i=0;i<data.length;i++){
        		//填充下拉框
        		$("#parentid").append("<option value='"+ data[i].id +"'>"+data[i].name +"</option>"); 
        	}
        },
        error: function(err) {
            alertMsg($.i18n.prop("message.error"));
        }
	}); 
}

//添加查看页面
function view(id) {
	$("#editfm input").attr("disabled","disabled");
	$("#editfm select").attr("disabled","disabled");
	$("#saveBtn").hide();
	$("#cancleBtn").hide();
	$("#returnBtn").show();
	
	// 验证重置
	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
	$.ajax({
		url : ctx + '/menu/edit',
		method : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if(data!=null){
				// 填充表单
				$('#editfm').autofill(data);
			} else {
				// 重置表单
				$('#editfm').resetForm();
			}
		},
        error : function(err){
        	alertMsg($.i18n.prop("message.error"));
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
	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
	$.ajax({
		url : ctx + '/menu/edit',
		method : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if(data!=null){
				// 填充表单
				$('#editfm').autofill(data);
			} else {
				// 重置表单
				$('#editfm').resetForm();
			}
		},
        error : function(err){
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
		// form表单数据转化
		// jquery自带的serializeArray方法转为数组格式
		// jquery.serialize-object插件的serializeObject转为对象格式，serializeJSON转为json
		var formdata = $("#editfm").serializeObject();
		$.ajax({
	        url: ctx+'/menu/update',
	        type: 'POST',
	        dataType: 'json',
	        data: formdata,
	        success : function(result) {
	        	// 模态框隐藏
	        	$("#editModal").modal("hide");
	        	// 重新加载
	        	search();
	        	// 消息提示
	        	//autoCloseMsg(result.msg);
	        },
	        error : function(err){
	        	alertMsg($.i18n.prop("message.error"));
	        }
	    });
	}
}


//数据删除
function del(id) {
	var msg = "确认要删除这个菜单吗?";
	var url = ctx+'/menu/delete';
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
		alertMsg("请至少选择一个菜单！");
		return;
	} else {
		checked.each(function(){
			ids.push($(this).val())
		});
	}
    
	//var msg = $.i18n.prop("message.batchOff");
	var msg = "确认要批量删除这些菜单吗?";
	var url = ctx+'/menu/batchDelete';
	var params = {
			ids: ids
		};
	msgBootbox(msg, url, params, search);	
}

// 表单验证
function validate(){
	$('#editfm').bootstrapValidator({
        excluded: [':disabled'],
        fields: {/*验证*/
            
        	name: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    }
                }
            }
	
        }
    });
}
