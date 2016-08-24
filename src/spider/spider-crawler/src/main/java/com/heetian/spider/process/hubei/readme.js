function queryCheck() {
	$("#checkNo").val($("#checkNoShow").val());
	var pass = false;
	var entName = trim($("#entName").val()); //关键字
	$("#entName").val(entName);
	$.ajax({
		url : "/ECPS_HB/checkFilterKey.jspx",
		data : "entName=" + encodeURI(encodeURI($("#entName").val())),
		success : function(data) {
			data = eval('(' + data + ')');
			if (data.success != undefined && data.success == true) {
				changeVal();
				alert("不能以该关键字作为条件进行查询，请重新输入更明确的条件!");
				pass = false;
			} else {
				pass = true;
			}
		},
		async : false,
		dataType : "json"
	});
	if (!pass)
		return pass;
	showCaptcha();
}
function showCaptcha() {
	if ($('#entName').val() != '' && $('#entName').val() != '请输入企业名称或统一社会信用代码或注册号或个体经营者姓名') {
        zdm();
        changeVal();
    } else {
        alert("请输入企业名称或统一社会信用代码或注册号或个体经营者姓名!");
    }
}
function zdm() {
    alert_win.style.display = 'block';
    annlid.style.display = 'none';
    zmdid.style.display = 'block';
    $("#checkNoShow").val("");
}
function changeVal() {
	removeVerificationCode();
		refreshVerificationCode1();
		return;
		if(type==0)
			$('#valCodeTip').html("请根据下图中的算术题，在查询框中输入计算结果。");
		else if(type==1)
			$('#valCodeTip').html("请根据下图中的汉字，在查询框中输入汉字。");
		else
			$('#valCodeTip').html("请根据下图中的汉字，在查询框中输入首字母。");
    	$('#valCode').prop('src', '/ECPS_HB/validateCode.jspx?type='+type+'&id=' + Math.random());
}

function doQuery() {
	if(yzm.length<4){
		alert("请先选择验证码");
		return;
	}else{
		$("#checkNo").val(yzm);
		var checkNo = yzm;
	}
var pass = false;
if (checkNo == "请输入验证码" || checkNo == "") {
    alert("请输入验证码");
    return false;
}
$.ajax({url:"/ECPS_HB/checkCheckNo.jspx",
    data: "checkNo=" + encodeURI(encodeURI($("#checkNo").val())),
    success: function(data) {
        data = eval('(' + data + ')');
        if (data.success != undefined && data.success == false) {
            changeVal();
            alert("验证码不正确，请重新输入!");
        } else {
            pass = true;
        }
    },async:false,
    dataType:"json"})
if (!pass) return pass;
$("#form").attr("target", "_blank");
$("#zmdid").hide();
$("#alert_win").hide();
$("#form").submit();
}

var yzm ="";
function chooseCode(n){
	if(yzm.length<4){
		yzm+=n;
	}
	$("input[name='yzmpassword']").each(function(i){
		$(this).val(yzm.charAt(i));
	});
}