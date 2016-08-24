function query(){
	var selectValue = trim(document.getElementById("selectValue").value);
	if(selectValue.length==1){
		alert("不能输入一个字进行查询，请重新输入!");
		return;
	}
	if(selectValue=="公司"){
		alert("不能输入公司进行查询，请重新输入!");
		return;
	}
	if(selectValue=="有限责任公司"){
		alert("不能输入有限责任公司进行查询，请重新输入!");
		return;
	}
	if(selectValue=="食品"){
		alert("不能输入食品进行查询，请重新输入!");
		return;
	}
	if(selectValue=="江西"){
		alert("不能输入江西进行查询，请重新输入!");
		return;
	}
	if(selectValue=="湖北"){
		alert("不能输入湖北进行查询，请重新输入!");
		return;
	}
	if(selectValue=="宁夏"){
		alert("不能输入宁夏进行查询，请重新输入!");
		return;
	}
	if(selectValue=="南昌"){
		alert("不能输入南昌进行查询，请重新输入!");
		return;
	}
	if(selectValue=="武汉"){
		alert("不能输入武汉进行查询，请重新输入!");
		return;
	}
	if(selectValue=="银川"){
		alert("不能输入银川进行查询，请重新输入!");
		return;
	}
	
	if(selectValue=="请输入企业名称、统一社会信用代码或注册号或个体经营者姓名"||selectValue==''){
		alert("企业名称、统一社会信用代码或注册号或个体经营者姓名，请输入!");
		return;
	}
	alert_win.style.display='block';
	annlid.style.display='none';
	zmdid.style.display='block';
	refreshVerificationCode1();
}
function refreshVerificationCode1(){
	document.getElementById("verificationCode1").src="/ECPS/verificationCode.jsp?_=" + new Date().getTime();
}


function submitQuery() {
	var password = $("input[name='password']").val();
	$.ajax({
		   type: "POST",
		   dataType: "json",
		   url: "qyxxgsAction_checkVerificationCode.action",
		   data: {"password" : password },
		   success: function(data){
			   if (data.message == "ok") {
				    alert_win.style.display='none';
				   	document.getElementById("queryXyxx").action = "qyxxgsAction_queryXyxx.action";
					document.forms.queryXyxx.target="_self";
					document.forms.queryXyxx.submit();
			   } else {
				   alert(data.message);
				   refreshVerificationCode1();
			   }
		   },
		   error: function(data) {
			   alert("服务器异常");
			   refreshVerificationCode1();
		   }
		});
}

function showJbxx(nbxh,qylx,qymc,zch,qylxFlag){
	window.open("qyxxgsAction_initQyxyxxMain.action?qylx=" + qylx +"&nbxh=" + nbxh + "&qylxFlag=" + qylxFlag + "&zch=" + zch);
}	
