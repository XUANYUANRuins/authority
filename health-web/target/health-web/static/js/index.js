// Disable search and ordering by default
$.extend( $.fn.dataTable.defaults, {
	processing: true,
    serverSide: true,
    paging: true,//是否分页
    pagingType: "full_numbers",//除首页、上一页、下一页、末页四个按钮还有页数按钮
    /* scrollX: true,//允许水平滚动
     scrollY: "200px",
     scrollCollapse: true, */
    searching: true,//是否开始本地搜索
    stateSave: false,//刷新时是否保存状态
    autoWidth: true,//自动计算宽度
    //deferRender : true,//延迟渲染
    bPaginate: true,//是否显示（使用）分页器
    bLengthChange: true,//是否显示一个每页长度的选择条（需要分页器支持）
    iDisplayLength: 25,//设置默认显示条数
    bAutoWidth: true,//是否自适应宽度
    lengthMenu: [10, 25, 50, 100],//每页显示的条数选项
    language: {
        "lengthMenu": "每页_MENU_条",
        "zeroRecords": " ",
        "info": "共  _TOTAL_ 条记录",
        "infoEmpty": "空信息",
        "infoFiltered": "(从 _MAX_ 条数据中检索)",
        "processing": "加载中",
        "emptyTable": "无记录",
        "paginate": {
            "first": "首页",
            "previous": "上一页",
            "next": "下一页",
            "last": "尾页"
        }
    },
    dom: 'rt<"bottom"ilp><"clear">'	//页码位置
} );

/**
 * 统一的删除模态框
 * @param url
 * @param dataParams
 * @param successFunc
 * @returns
 */
function delBootbox(url, dataParams, successFunc){
	bootbox.dialog({
        message: '<h4 class="text-warning">'+$.i18n.prop("message.delete")+'</h4>',
        buttons: {
        	"danger" :
			{
				"label" : $.i18n.prop("button.ok"),
				"className" : "btn btn-danger",
				"callback": function() {
					$.ajax({
	                    url: url,
	                    type: 'POST',
	                    dataType: 'json',
	                    data: dataParams,
	                    success : function(result) {
	                    	if(result.success){
	                    		successFunc();
	                    	} else {
	                    		alertMsg($.i18n.prop("message.fail"));
	                    	}
	                    },
	                    error : function(err){
	                    	alertMsg($.i18n.prop("message.error"));
	                    }
	                });
				}
			},
			"button" :
			{
				"label" : $.i18n.prop("button.cancel"),
				"className" : "btn"
			}
        }
    });
}

/**
 * 操作模态框
 * @param msg
 * @param url
 * @param dataParams
 * @param successFunc
 * @returns
 */
function msgBootbox(msg, url, dataParams, successFunc){
	bootbox.dialog({
        message: '<h4 class="text-warning">'+msg+'</h4>',
        buttons: {
        	"danger" :
			{
				"label" : $.i18n.prop("button.ok"),
				"className" : "btn btn-danger",
				"callback": function() {
					$.ajax({
	                    url: url,
	                    type: 'POST',
	                    dataType: 'json',
	                    data: dataParams,
	                    success : function(result) {
	                    	if(result.success){
	                    		successFunc();
	                    	} else {
	                    		alertMsg($.i18n.prop("message.fail"));
	                    	}
	                    },
	                    error : function(err){
	                    	alertMsg($.i18n.prop("message.error"));
	                    }
	                });
				}
			},
			"button" :
			{
				"label" : $.i18n.prop("button.cancel"),
				"className" : "btn"
			}
        }
    });
}

