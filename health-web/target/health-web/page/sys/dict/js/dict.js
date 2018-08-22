var oTable;

$(function () {
	
	// 获取树
	getTree();
	
	oTable = $('#listDataTable').DataTable({
        ajax: {
            url: ctx+'/dict/getPage',
            type: 'POST',
            data: buildSearchCriteria
        },
        columns: [
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
            	title: $.i18n.prop("dict.name"),	//名称
            	class:"center",
            	data: "name"
            },
            {
            	title: $.i18n.prop("dict.code"),	//代码
            	data: "code", 
            	class:"center",
            	defaultContent: ""
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
	
	//表单验证
	validate();
});

//搜索参数
function buildSearchCriteria(search) {  
    search['name'] = $("#search_name").val();  
    search['code'] = $("#search_code").val();
    search['status'] = $("#search_status").val();
    search['parentid'] = $("#search_parentid").val();
    return search;  
}
//搜索
function search(){
    oTable.ajax.reload( null, false ); // 刷新表格数据，分页信息不会重置
}

//添加页面
function add() {
	
	// 验证重置
	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
	// 父节点
	var parentid = $("#search_parentid").val();
	
	$.ajax({
		url : ctx + '/dict/add',
		method : "POST",
		dataType : "json",
		data : {
			parentid : parentid
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
        error: function(err) {
            alertMsg($.i18n.prop("message.error"));
        }
	});
}
//编辑页面
function edit(id) {
	
	// 验证重置
	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
	$.ajax({
		url : ctx + '/dict/edit',
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
		// form表单数据转化
		// jquery自带的serializeArray方法转为数组格式
		// jquery.serialize-object插件的serializeObject转为对象格式，serializeJSON转为json
		var formdata = $("#editfm").serializeObject();
		$.ajax({
	        url: ctx+'/dict/update',
	        type: 'POST',
	        dataType: 'json',
	        data: formdata,
	        success : function(result) {
	        	// 模态框隐藏
	        	$("#editModal").modal("hide");
	        	//刷新树
	        	reloadTree();
	        	// 重新加载
	        	search();
	        	// 消息提示
	        	//autoCloseMsg(result.msg);
	        }
	    });
	}
}


//数据删除
function del(id) {
	
	var url = ctx+'/dict/delete';
	var params = {
			id: id
		};
    delBootbox(
    		url, 
    		params, 
    		function (){
		    	//刷新树
		    	reloadTree();
		    	// 重新加载
		    	search();
		    	// 消息提示
		    	//autoCloseMsg(result.msg);
    		}
    );
	
}

//刷新树
function reloadTree(){
	//选中的节点
	var selectedArr =  $('#treeview').treeview('getSelected');
	// 存在选中的
	if( selectedArr.length>0){
		var id = selectedArr[0].id;
		var nodeId;
		// 所有可用的节点
		var nodes = $('#treeview').treeview('getEnabled');
		for(var i=0; i<nodes.length; i++){
			if(nodes[i].id==id){
				nodeId = nodes[i].nodeId;
				break;
			}
		}
		// 重新加载树
		getTree();
		//等树加载完后再，展开并选中
		setTimeout(function(){expSelected(nodeId);},1000);

	} else {
		// 重新加载树
		getTree();
	}
}

//获取树数据
function getTree(){
	$.ajax({
        type: 'POST',
        url: ctx+'/dict/getTree',
        data:{},
        dataType: 'json',
        async: false,
        success: function (data) {
             var treeData = eval(data);
             $('#treeview').treeview({
         	    data: treeData,//数据
         	    showIcon: false,
         	    levels: 1,	//设置继承树默认展开的级别，默认2
         	    onNodeSelected: function(event, node) { //一个节点被选择
         	    	$("#search_parentid").val(node.id);
         	    	search();
         	    },
         	    onNodeUnselected: function (event, node) { //取消选择一个节点
         	    	$("#search_parentid").val('');
         	    	search();
        	    }
         	});
        },
        error: function(err) {
            alertMsg($.i18n.prop("message.error"));
        }
	});
}

//展开并选中
function expSelected(nodeId){
	$('#treeview').treeview('toggleNodeExpanded', [ nodeId, { silent: true } ]);
	$('#treeview').treeview('toggleNodeSelected', [ nodeId, { silent: true } ]);
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
            },
            code: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    },
                    remote: {
                    	url: ctx+'/dict/valid',
                    	/**自定义提交数据，默认值提交当前input value */
                    	data: function(validator) {
                    		return {
                    			code: $('#code').val(),
                                id: $('#id').val()
                            };
                    	},
                    	delay:2000,
                    	message: $.i18n.prop("message.codeExist")
                    }
                }
            }
	
        }
    });
}

