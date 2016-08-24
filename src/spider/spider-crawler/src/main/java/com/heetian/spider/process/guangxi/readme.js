//form id-form; input:id=name=checkNo;input:id=name=entName;input:null
function queryCheck() {
	$("#checkNo").val($("#checkNoShow").val());
	var pass = false;
	var entName = trim($("#entName").val());
	//校验entName
	$.ajax({
		url : "/checkFilterKey.jspx",
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

function changeVal() {
	var type=(Math.floor(Math.random()*10))%3;
	if(type==0)
		$('#valCodeTip').html("请根据下图中的算术题，在查询框中输入计算结果。");
	else if(type==1)
		$('#valCodeTip').html("请根据下图中的汉字，在查询框中输入汉字。");
	else
		$('#valCodeTip').html("请根据下图中的汉字，在查询框中输入首字母。");
	$('#valCode').prop('src', '/validateCode.jspx?type='+type+'&id=' + Math.random());
}
function showCaptcha() {
    if ($('#entName').val() != '' && $('#entName').val() != '请输入企业名称或注册号') {
        zdm();
        changeVal();
    } else {
        alert("请输入企业名称或注册号!");
    }
}
function zdm() {
    alert_win.style.display = 'block';
    annlid.style.display = 'none';
    zmdid.style.display = 'block';
    $("#checkNoShow").val("");  //验证码框框
}

function doQuery() {
    $("#checkNo").val($("#checkNoShow").val());
    var pass = false;
   //校验验证码
    $.ajax({url:"/checkCheckNo.jspx",
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
    if (!pass)return pass;
    $("#zmdid").hide();
    $("#alert_win").hide();
    $("#form").submit();
}
//以下为Ajax工具方法
var ajax = new Object();
ajax.Request = function () {
    this.request = getXMLHttpRequest();
};

ajax.Request.prototype = {
    loadTextByGet:function (url, callback) {
        var request = this.request;
        request.open("get", url, true);
        var outer = this;
        request.onreadystatechange = function () {
            var requests = outer.request;
            if (request.readyState == 4) {
                if(callback) callback.call(callback, request.responseText);
            }
        };
        request.send(null);
    },
    loadTextByPost:function (url, contents, callback) {
        var request = this.request;
        request.open("post", url, true);
        var outer = this;
        request.onreadystatechange = function () {
            var requests = outer.request;
            if (request.readyState == 4) {
                if(callback)callback.call(callback, request.responseText);
            }
        };
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        request.send(contents);
    }};
function getXMLHttpRequest() {
    var xmlHttp = false;
    try {
        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (exception) {
        try {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (failedMS) {
        }
    }
    if (!xmlHttp && typeof XMLHttpRequest != "undefined") {
        xmlHttp = new XMLHttpRequest();
    }
    return xmlHttp;
}