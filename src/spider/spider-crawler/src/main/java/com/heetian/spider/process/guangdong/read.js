$("#searchBtn").click(function() {
	var textfield = $("#textfield");
	var zmdid = $("#zmdid");
	var alert_win = $("#alert_win");
	var code = $("#code");
	if ($.trim(textfield.val()) == "请输入企业名称、注册号或统一社会信用代码") {
		// 关键字为空时的处理
	} else {
		code.val("");
		alert_win.attr("style", "display:black;");
		zmdid.attr("style", "display:black;");
		$("#see").trigger("click");// 请求依次验证码
		// 主动触发换一张的点击事件
		// $("#vimg").trigger("click");
	}
});

$("#checkBtn").click(function(){
	// 单击搜一下按钮时表单较验
	var msg = "";
	// document.checkForm.submit();
	// $("#textfield").submit();
	var textfield = $("#textfield");
	var code = $("#code");
	// 判断输入框是否为空
	if ($.trim(code.val()) == "请输入验证码") {
		msg = "请输入验证码！";
		code.focus();
	}
	for (var i = 0; i < noSel.length; i++) {
		if ($.trim(textfield.val()) == noSel[i]) {
			msg = "请勿使用此类词语进行查询！";
			textfield.focus();
			break;
		}
	}
	if (msg != "") {
		//不允许查询的关键字处理
	} else {
		showLoading();
		$.post("http://gsxt.gdgs.gov.cn/aiccips/CheckEntContext/checkCode.html",$("#checkForm").serialize(),function(data, status) {
			if (status == "success") {
				_czc.push([ "_trackEvent","公示系统", "搜索" ]);
				if (data.flag == "1") {
					if ($.trim(textfield.val()) != "") {
						var code = $("#code").val();
						var text = data.textfield;
						$("#textfield").val(text);
						var url = "http://gsxt.gdgs.gov.cn/aiccips/CheckEntContext/showInfo.html";
						$("#checkForm").serialize();
						$('#checkForm').attr('action', url);
						$("#checkForm").submit();
					}
				}
				if (data.flag == "3") {
					alert(data.tip);
					window.location.reload();
				}
				if (data.flag == "0") {
					hidenLoading();
					alert(data.tip);
					$("#see").trigger("click");
					code.focus();
				}
			} else {
				hidenLoading();
				alert("数据加载出错！")
			}
		}, "json");
		// if(url.length>0){
		// window.open(url);
		// window.location.reload();
		// }
	}
});