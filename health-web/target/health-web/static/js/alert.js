
/**
 * 弹出消息提示
 * @param msg
 * @returns
 */
function alertMsg(msg){
	bootbox.alert({
		message:'<h3 class="text-danger">' + msg + '</h3>',
		buttons: {
			ok: {
				label: $.i18n.prop("button.ok"),
	            className: 'btn-success'
			}
		}
	});
}

/**
 * 自动关闭消息提示
 * @param msg
 * @returns
 */
function autoCloseMsg(msg){
    var dialog = bootbox.dialog({
        message: '<h3 class="text-info">' +msg+ '</h3>'
        		+'<p class="text-right">（<span id="SecondsRemaining">3</span>秒后自动关闭）</p>'
    });
    
	var remaining = 3; // Number of seconds
    var obj = document.getElementById("SecondsRemaining");
    var timeout = window.setInterval(function(){
        remaining--;
        if(remaining==0) {
            // Time is up, stop the timer and hide the bootbox
        	window.clearInterval(timeout);
        	dialog.modal('hide');
            return;
        }
        obj.innerHTML = remaining; // Update the value displayed
    }, 1000); // Decrease counter every second
	
}
