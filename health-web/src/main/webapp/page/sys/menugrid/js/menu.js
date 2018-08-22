
$(function () {
	
	$('#treegrid').jqGrid({
		treeGrid: true,  
        treeGridModel: 'adjacency', //treeGrid模式，跟json元数据有关 ,adjacency/nested  
        scroll: "true",  
        url: ctx + '/menu/getTreeGrid',  
        datatype: 'json',  
        //rownumbers: true,	//开启序号列
        ExpandColumn : 'name',  
        colModel:[  
            /*{
            	label: $.i18n.prop("common.serial"),
            	name:"id",
            	index:'id',
            	sorttype:"int" 
            },*/
            {
            	label: $.i18n.prop("menu.name"),	//名称
            	name:'name',
            	index:'name',
            	class:"center",
            	width:300
            },
            {
            	label: $.i18n.prop("menu.url"),	//路径
            	name:'url',
            	index:'url',
            	class:"center",
            	width:300
            },
            {
            	label: $.i18n.prop("menu.icon"),	//图标
            	name:'icon',
            	class:"center",
            	index:'icon'
            },
            {
            	label: $.i18n.prop("common.status"),	//状态
            	name:'status',
            	index:'status',
            	class:"center",
            	formatter: function(cellvalue, options, rowObject) {  
                    return fmtStatus(cellvalue);
                }
            },
            {
            	label: $.i18n.prop("common.updatetime"),	//更新时间
            	name:'updatetime',
            	index:'updatetime',
            	class:"center",
            	formatter: function(cellvalue, options, rowObject) {  
                    return fmtDatebox(cellvalue, "yyyy-MM-dd hh:mm:ss");
                }
            },
            {
            	label: $.i18n.prop("common.operate"),	//操作
            	name:'id',
            	index:'id',
            	class:"center",
            	formatter:function (cellvalue, options, rowObject) {
            		// 操作按钮
            		return fmtBtn(cellvalue);
            	}
            }
        ],  
        pager: "false",  
//        sortname: 'id',  
//        sortorder: "desc",  
        jsonReader: {  
            root: "dataRows",  
            repeatitems : false  
        },  
        treeReader : {  
            level_field: "level",  
            parent_id_field: "parent",  
            leaf_field: "isLeaf",  
            expanded_field: "expanded"  
        },  
        mtype: "POST", 
        autowidth: true, 	//自动列宽
        height: "auto",    // 设为具体数值则会根据实际记录数出现垂直滚动条  
        rowNum : "-1",     // 显示全部记录  
        shrinkToFit:false  // 控制水平滚动条  
    });
	
	//表单验证
	validate();
	//父菜单下拉框的填充
	pushMenus();
    
});

//搜索
function search(){
	//搜索参数
	var queryData={
		"name":$("#search_name").val(),
		"url":$("#search_url").val(),
		"status":$("#search_status").val()
	};
	
	pushMenus();
	// 刷新表格
	$('#treegrid').jqGrid("setGridParam", {postData:queryData}).trigger("reloadGrid");
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

//添加编辑页面
function edit(id) {
	
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
	
	var url = ctx+'/menu/delete';
	var params = {
			id: id
		};
    delBootbox(url, params, search);
	
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
