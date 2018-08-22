$(function() {
	/*点击图片刷新验证码*/
	$('#kaptchaImage').click(function () {
    	$(this).hide().attr('src', ctx+'/code/captcha-image').fadeIn();
	});
	
	//验证
	validate();
	geterror();
	
})

//获取后台返回的错误信息
function geterror() {
	var errorInfo = $("#error").val();
	if (!(errorInfo == null || errorInfo == '')) {
		alertMsg(errorInfo);
	}
}

//表单验证
function validate(){
	$('#formId').bootstrapValidator({
        fields: {/*验证*/
        	
        	loginname: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    },
                    regexp:{
                    	regexp: /^[a-zA-Z0-9_]+$/,
                    	message: $.i18n.prop("message.letterNumber")
                    }
                }
            },
            password: {/*键名和input name值对应*/
                validators: {
                    notEmpty: {/*非空提示*/
                        message: $.i18n.prop("message.notempty")
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: $.i18n.prop("message.length",6,30)
                    }
                }
            }
            
        }
    });
}