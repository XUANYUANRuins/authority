$(function () {
	$.ajax({
		type: "POST",
		url: ctx + "/consultation/main",
		async: false,
		success: function(data) {
			json = JSON.parse(data);
			
			var all = 0;
			var noall = 0;
			for(var i=0;i<json.allNum.length;i++) {
				all ++;
				if(json.allNum[i].num>1) {
					noall ++;
				}
				
			}
//			console.log(all);
//			console.log(noall);
			$('#allNum').html(all);
			$('#noall').html(noall);
			$('#treated').html(json.treated);
			$('#completed').html(json.completed);
		}
	});
});