function zdm() {
	if (($.trim($("#entname").val()) == '请输入企业名称或注册号')|| ($.trim($("#entname").val()) == '')) {
		$.dialog('请输入企业名称或注册号！', function() {});
		return;
	}
	zmdid.style.display = 'block';
}

function checkInput() {
	if (this.document.form1.currentPageNo) {
		this.document.form1.currentPageNo.value = '1';
	}
	zmdid.style.display = 'none';
	if (($.trim($("#entname").val()) == '')) {
		$.dialog('请输入企业名称或注册号！', function() {
		});
		return;
	}
	/* 企业名称长度限制 */
	var entname = document.getElementById("entname").value;
	var realLength = 0, len = entname.length, charCode = -1;
	for (var i = 0; i < len; i++) {
		charCode = entname.charCodeAt(i);
		if (charCode >= 0 && charCode <= 128)
			realLength += 1;
		else
			realLength += 2;
	}
	if (realLength > 100 || realLength < 4) {
		$.dialog('请输入更为详细的查询条件！', function() { });
		var aa = Math.random() * 3;
		var bb = Math.floor(aa);
		var title = document.getElementById("yzmTitle");
		title.innerHTML = "请根据下图中的算术题，在查询框中输入计算结果。";
		$("#img").attr( "src", "ztxy.do?method=createYzm&dt=" + new Date().getTime() + "&random=" + new Date().getTime());
		document.getElementById("yzm").value = "";
		return false;
	}
	/* 企业名称验证 */
	var entname = document.getElementById("entname").value;
	var url = "qymc=" + encodeURI(encodeURI(entname));
	$.ajax({
		type : "POST",
		url : "keyword.do?method=keywordFilter&random=" + new Date().getTime(),
		data : url,
		dataType : "text",
		success : function(data) {
			if (data == '1') {
				_czc.push([ "_trackEvent", "市场主体", "搜索" ]);
				doQuery('1');
				// _loading("信息查询",'300','200');
			} else {
				$.dialog('请输入更为详细的查询条件！', function() {});
				$("#img").attr("src","ztxy.do?method=createYzm&dt=" + new Date().getTime()+ "&random=" + new Date().getTime());
				document.getElementById("yzm").value = "";
				return false;
			}
		}
	});
}
function doQuery(index){
	form1.action = "ztxy.do?method=list&djjg=&random="+new Date().getTime();
	form1.submit();
}



function _load(){
    var eles = document.getElementById('noIe');
    var help = document.getElementById("help");
	if(/msie/i.test(navigator.userAgent)){//ie游览器 
		if(document.documentMode!=7 && document.documentMode!=6){
			eles.style.marginTop = '45px';
			help.style.marginLeft = '-280px';
		}else{
			help.style.marginLeft = '-280px';
		}
	}else{//非ie浏览器
         eles.style.marginTop = '45px';
			help.style.marginLeft = '-280px';
	}
	var flag = '';
	if(flag == 'fail'){
		$.dialog('验证码错误！', function () {zdm();});
	}else if(flag == 'outtime'){
		$.dialog('验证码已过期，请重新输入。', function () {zdm();});
	}
	
	if(flag == "not"){
		$.dialog('您搜索的条件无查询结果！', function () {});
	}
	if(flag == "tooMore"){
		$.dialog('该企业存在多个登记机关注册登记的情况，请到发证机关进行核实！', function () {});
	}
	if(flag=="No"){
		$.dialog('您输入的信息有误，未找到相关企业！', function () {});
		//annl();
	}
	/**
		xd.liu 2014-09-18 16:07 验证码随机生成
	*/
	var aa = Math.random()*3;
    var bb = Math.floor(aa);
    var title = document.getElementById("yzmTitle");
	title.innerHTML = "请根据下图中的算术题，在查询框中输入计算结果。";
	$("#img").attr("src","ztxy.do?method=createYzm&dt="+new Date().getTime()+"&random="+new Date().getTime());
}

function openView(pripid,enttype,zt){ 
    var src="ztxy.do?method=qyInfo&djjg=&maent.pripid="+pripid+"&maent.entbigtype="+enttype+"&random="+new Date().getTime();
	_openPage(src);
	       
}
function _openPage(url) {
    var dynamicForm = document.createElement("form");   
    document.body.appendChild(dynamicForm);
    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
    var actionString = url.substring(0,url.indexOf("?"));   
	var paraObj = {}   
	for (i=0; j=paraString[i]; i++){   
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf 
		("=")+1,j.length);   
	}
    dynamicForm.method = 'post';
    dynamicForm.action = actionString
    dynamicForm.target = '_blank';
    dynamicForm.innerHTML="";
	for ( var p in paraObj ){
	  	var newElement = document.createElement("input");
	    newElement.setAttribute("name",p);
	    newElement.setAttribute("type","hidden");
	    newElement.setAttribute("value",paraObj[p]);
	    dynamicForm.appendChild(newElement);
	}
	dynamicForm.submit();
}



function _openPageForSelf(url) {
    var dynamicForm = document.createElement("form");   
    document.body.appendChild(dynamicForm);
    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
    var actionString = url.substring(0,url.indexOf("?"));   
	var paraObj = {}   
	for (i=0; j=paraString[i]; i++){   
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf 
		("=")+1,j.length);   
	}
    dynamicForm.method = 'post';
    dynamicForm.action = actionString
    dynamicForm.target = '_self';
    dynamicForm.innerHTML="";
	for ( var p in paraObj ){
	  	var newElement = document.createElement("input");
	    newElement.setAttribute("name",p);
	    newElement.setAttribute("type","hidden");
	    newElement.setAttribute("value",paraObj[p]);
	    dynamicForm.appendChild(newElement);
	}
	dynamicForm.submit();
}