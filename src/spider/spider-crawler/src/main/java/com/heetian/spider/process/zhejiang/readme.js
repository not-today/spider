//分支机构
function goPageBranchInfo(pageNo){
	var submitForm = document.getElementById('branchInfoPaginationForm');
	submitForm.elements[0].value = pageNo;
	submitForm.submit();
}
//主要人员
function goPageEntMember(pageNo){
		var submitForm = document.getElementById('entMemberPaginationForm');
		submitForm.elements[0].value = pageNo;
		submitForm.submit();
}
//股东
function goPageEntInvestor(pageNo){
		var submitForm = document.getElementById('entInvestorPaginationForm');
		submitForm.elements[0].value = pageNo;
		submitForm.submit();
}
// 变更
function goPageCheckAlter(pageNo){
		var submitForm = document.getElementById('checkAlterPaginationForm');
		submitForm.elements[0].value = pageNo;
		submitForm.submit();
}
//点击搜索，显示验证码界面
function zdm(type) {//啥都没干
	var name = $.trim($("#name").val());
	if((name ==""||isLess(name, 2)||name =="请输入企业名称或注册号")&&type=="1"){
		$("#name").focus();
		$("#message").html("请输入企业名称或注册号,名称不少于两位");
		return false;
	}else if((name ==""||isLess(name, 2)||name =="请输入企业名称或注册号")&&type=="2"){
		$("#name").val("");
	}
	$("#message").html("");	
	$("#codeValidator").html("");
    alert_win.style.display = 'block';
    zmdid.style.display = 'block';
	$("#clickType").val(type);
	$("#verifyCode").focus();
}
function doProgressBarSearch() {
	//没输入验证码提示
    if($.trim($("#verifyCode").val()) ==""){
    	$("#verifyCode").focus();
    	$("#codeValidator").html("请输入验证码");
    	return false;
	}
	//验证验证码
    $.HxSyncPost("/search/doValidatorVerifyCode.do",{ "verifyCode": $("#verifyCode").val(), "name": $("#name").val()
    },function(data){
    	//通过提交表单
    	if(data["nameResponse"].message == "true"){
    		//验证码放入表单
    	    $("#codeValue").val($.trim($("#verifyCode").val()));
    		$(".da").HxProgressOn({
    			"fun" : search,
    			"arg" : [],
    			"time" : 500
    		});
    	}else{
    		//验证不通过 提示信息
    		changeVerifyCode($("#kaptchaImg").get(0));
    		var nameResponse = data["nameResponse"];
			$("#codeValidator").html(nameResponse.message);
    	}
		});
}
$("#kaptchaText").click(function() {//下载验证码
	$("#kaptchaImg").get(0).src = "/common/captcha/doReadKaptcha.do?" + Math.floor(Math.random() * 100);
});