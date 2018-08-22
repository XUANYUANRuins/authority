var oTable;
var tabTreeView;
$(function () {
	
	tabTreeView = $('#treeview');
	
	oTable = $('#listDataTable').DataTable({
        ajax: {
            url: ctx+'/role/getPage',
            type: 'POST',
            data: buildSearchCriteria
        },
        columns: [
        	{
        		title: $.i18n.prop("common.serial"),
        		data: null,
        		orderable: false,
        		class:"center",
        		render: function (data,type,full, meta) {
            		// 行数索引+每页起始索引+1
            		return meta.row + meta.settings._iDisplayStart +1;
            	}
        	},
            {
            	title: $.i18n.prop("role.name"),	//名称
            	class:"center",
            	data: "name"
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
    search['status'] = $("#search_status").val();
    return search;  
}
//搜索
function search(){
    oTable.ajax.reload( null, false ); // 刷新表格数据，分页信息不会重置
}

//添加编辑页面
function edit(id) {
	
	// 验证重置
	$('#editfm').data('bootstrapValidator').resetForm();
	// 重置表单
	$('#editfm').resetForm();
	
	$.ajax({
		url : ctx + '/role/edit',
		method : "POST",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if(data!=null){
				// 填充表单
				$('#editfm').autofill(data);
				getTree(data.menuids);
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
		var checkedArr =  tabTreeView.treeview('getChecked');
		var menuids = '';
		for( var i=0; i<checkedArr.length; i++){
			menuids += checkedArr[i].id+",";
		}
		menuids = menuids.substr(0, menuids.length - 1);//去点最后一个逗号
		$('#menuids').val(menuids);
		
		// form表单数据转化
		// jquery自带的serializeArray方法转为数组格式
		// jquery.serialize-object插件的serializeObject转为对象格式，serializeJSON转为json
		var formdata = $("#editfm").serializeObject();
		$.ajax({
	        url: ctx+'/role/update',
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
	        error: function(err) {
	            alertMsg($.i18n.prop("message.error"));
	        }
		});
	} 
}

//数据删除
function del(id) {
	
	var url = ctx+'/role/delete';
	var params = {
			id: id
		};
    delBootbox(url, params, search);
	
}

// 获取树数据
function getTree(ids){
	$.ajax({
        type: 'POST',
        url: ctx+'/role/getTree',
        data:{ids:ids},
        dataType: 'json',
        async: false,
        success: function (data) {
             var treeData = eval(data);
             tabTreeView.treeview({
         	    data: treeData,//数据
         	    showIcon: false,
         	    showCheckbox: true,
         	    onNodeChecked: function(event, node) { //选中节点
         	        //选中该节点下的所有子节点
         	    	checkNode(tabTreeView,node);  			
       			     //选中子节点，父节点也选中
       			    cascade(tabTreeView,node);
         	    },
         	    onNodeUnchecked: function (event, node) { //取消选中节点
         	    	//取消选中该节点下的所有子节点
         	    	uncheckNode(tabTreeView,node);        			
        			 //子节点为空时，父节点也为空
        			uncheckCascade(tabTreeView,node);
         	    }
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