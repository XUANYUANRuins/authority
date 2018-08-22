var oTable;

$(function() {
	//判断新建诊断按钮是否显示
	var position = $('#position').val();
	if(position == "p05" || position == "p02") {
		$("#butnewly").show();
	} else if(position == "p06" || position == "p01" ) {
		$("#butnewly").hide();
	}
	
	oTable = $('#listDataTable').DataTable({
		ajax: {
			url: ctx + '/consultation/getPage',
			type: 'POST',
			data: buildSearchCriteria
		},
		columns: [{
				title: "序号",
				data: null,
				orderable: false,
				class:"center",
				render: function(data, type, full, meta) {
					// 行数索引+每页起始索引+1
					return meta.row + meta.settings._iDisplayStart + 1;
				}
			},
			{
                title: "诊断时间",
            	data: "createtime",
            	class:"center",
                render: function (data,type,full) {
                    return fmtDatebox(data, "yyyy-MM-dd hh:mm:ss");
                }
            },
			{
				title: "姓名",
				data: "name",
				class:"center",
				defaultContent: ""
			},
			{ //修改
				title: "性别",
				data: "sex",
				class:"center",
				defaultContent: "",
				render: function(data, type, full) {
					if(data == "0") {
						return "男";
					} else if(data == "1") {
						return "女";
					} else {
						return "";
					}

				}
			},
			{
				title: "身份证",
				data: "idcard",
				class:"center",
				defaultContent: ""
			},
			{
				title: "手机",
				data: "tel",
				class:"center",
				defaultContent: ""
			},
			{ 
				title: "糖尿病史",
				data: "diabetes",
				class:"center",
				defaultContent: "",
				render: function(data, type, full) {
					if(data == "0") {
						return "无";
					} else if(data == "1") {
						return "有";
					} else {
						return "";
					}

				}
			},
			{
				title: "状态",
				data: "status",
				class:"center",
				render: function(data, type, full) {
					if(data == "-2") {
						return "报告被退回";
					}else if(data == "-1") {
						return "远程会诊中";
					}else if(data == "0") {
						return "机器自动诊断中";
					} else if(data == "1") {
						return "待远程医生确认";
					} else if(data == "2") {
						return "诊断完成";
					} else {
						return "未知";
					}

				}
			},
			{
				title: "操作",
				data: "id",
				class:"center",
				render: function(data, type, full) {
					// 操作按钮 --p01 医生
					if(position =="p01"||position =="p05"||position =="p06") {
						return reportEditBtn(data, full.status);
					} else {
						return reportBtn(data, full.status);
					}

				}
			},
			{
				title: "创建人id",
				data: "createid",
				class:"center",
				defaultContent: "",
			},
			{
				title: "状态",
				data: "status",
				class:"center",
				defaultContent: "",
			}
		],

		"fnInfoCallback": function(oSettings, iStart, iEnd, iMax, iTotal, sPre) {
			var value = $("#userid").val();
			var tr_arr = new Array();
			tr_arr = $("#listDataTable tbody tr");
			for(var i = 0; i < tr_arr.length; i++) {
				var tr = tr_arr[i];
				var status = $(tr).find("td").eq(10).text();

				if((position =="p05"&&status!=-2)||(position =="p06"&&status==-2)) {
					//基层处于退回状态则不能编辑，会诊中心在退回状态不能编辑
					var td = $(tr).find("td").eq(8);
					//$(td).find(".gridbtnedit").attr("disabled", "disabled");
					$(td).find(".gridbtnedit").hide();
				}
				
				//设置报告被退回时字体为红色
				if(status==-2) {
					$(tr).find("td").eq(7).css('color','red');
				}
			}
			
			var tr_arr1 = $("#listDataTable tr");
			for(var i = 0; i < tr_arr1.length; i++) {
				var tr = tr_arr[i];
				$(tr).find("td").eq(9).hide();
				$(tr).find("td").eq(10).hide();
			}
			$("#listDataTable thead tr th").eq(9).hide();
			$("#listDataTable thead tr th").eq(10).hide();
		}
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

	//表单验证
	//validate();
});

//搜索参数
function buildSearchCriteria(search) {
	search['startDate'] = $("#search_start").val();
    search['endDate'] = $("#search_end").val();
	search['name'] = $("#search_name").val();
	search['diabetes'] = $("#search_diabetes").val();
	search['status'] = $("#search_status").val();
	return search;
}
//搜索
function search() {
	oTable.ajax.reload(null, false); // 刷新表格数据，分页信息不会重置
}
//新建诊断
function newWizard(){
	location.href = ctx + "/page/health/wizard/index.jsp";
}

//报告页面
function report(baseInfoid, status) {
	if(status == 0) {
		var msg = "该报告正在机器智能诊断中，请稍后再判读！";

		bootbox.confirm({
			message: '<h3 class="text-danger">' + msg + '</h3>',
			buttons: {
				confirm: {
					label: $.i18n.prop("button.ok"),
					className: 'btn-success'
				},
				cancel: {
					label: "取消"
				}
			},
			callback: function(result) {
				if(result) {
					location.reload();
				} else {
					return;
				}
			}
		});

	} else {
		location.href = ctx + "/consultation/report?baseInfoid=" + baseInfoid;
	}

}

// 诊断编辑
function edit(baseInfoid, status) {
	if(status == 0) {
		var msg = "该报告正在机器智能诊断中，请稍后再判读！";

		bootbox.confirm({
			message: '<h3 class="text-danger">' + msg + '</h3>',
			buttons: {
				confirm: {
					label: $.i18n.prop("button.ok"),
					className: 'btn-success'
				},
				cancel: {
					label: "取消"
				}
			},
			callback: function(result) {
				if(result) {
					location.reload();
				} else {
					return;
				}
			}
		});

	} else {
		location.href = ctx + "/consultation/edit?baseInfoid=" + baseInfoid;
	}
}