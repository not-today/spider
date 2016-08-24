function showDailog() {
	if (!$.trim($("#queryName1").val())) {//queryName1 关键字表单id
		alert("请输入查询条件");
		return false;
	}
	$(".mask").show();
	$('.login-box').show();
	$('#queryName').val($('#queryName1').val());//queryName  表单input的id   name=searchContent
	$('#vcodeimg').click();//vcodeimg验证码id 下载验证码   /verifycode?date='+(new Date()).getTime()
}

function validate(){
	if($("#queryName").val()==null || $("#queryName").val()==""){
		alert("请输入查询条件");
		return false;
	}
	if($('#vcode').val()==null || $('#vcode').val() == ""){  //vcode验证码id name   http://tjcredit.gov.cn/platform/saic/search.ftl
		alert("请输入验证码");
		return false;
	} 
}
