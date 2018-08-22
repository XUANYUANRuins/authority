$(function(){
	
	//表单验证
	validate();
	
});

//更新密码
function updatePassword() {
	// 触发表单验证
	var bootstrapValidator = $("#editfm").data('bootstrapValidator');
	bootstrapValidator.validate();
	
	if(bootstrapValidator.isValid()){
		
		// form表单数据转化
		// jquery自带的serializeArray方法转为数组格式
		// jquery.serialize-object插件的serializeObject转为对象格式，serializeJSON转为json
		var formdata = $("#editfm").serializeObject();
		$.ajax({
	        url: ctx+'/user/updatePassword',
	        type: 'POST',
	        dataType: 'json',
	        data: formdata,
	        success : function(result) {
	        	if(result.success){
	        		
	        		bootbox.alert({
	        			message:'<h3 class="text-danger">' + $.i18n.prop("message.pwSuccess") + '</h3>',
	        			buttons: {
	        				ok: {
	        					label: $.i18n.prop("button.ok"),
	        		            className: 'btn-success'
	        				}
	        			},
	        			callback: function() {  
	        				// 退出，重新登录
    		        		location.href = ctx + "/logout";
	                    }
	        		});
	        		
	        	} else {
	        		alertMsg($.i18n.prop("message.fail"));
	        	}
	        },
	        error : function(err){
	        	alertMsg($.i18n.prop("message.error"));
	        }
	    });
	}
}

//表单验证
function validate(){
	$('#editfm').bootstrapValidator({
        excluded: [':disabled'],
        fields: {/*验证*/
        	
        	password: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    }
                }
            },
            newPassword: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: $.i18n.prop("message.length",6,30)
                    },
                    different: {//不能和用户名相同
                        field: 'password',//需要进行比较的input name值
                        message: $.i18n.prop("message.same",'password')
                    }
                }
            },
            newPasswordRepeat: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    },
                    identical: {//相同
                        field: 'newPassword',
                        message: $.i18n.prop("message.same",'newPassword')
                    }
                }
            }
            
        }
    });
}