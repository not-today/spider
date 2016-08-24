function PunishResult(type){
    if(type!=1){
      var name = document.getElementById("name").value;
      if(name==''){
          name=nameH;
      }
      //rDate(4);
//      var url = getPath() + '/infoQueryServlet.json?query_info=true&type='+sj;
      var paramObj = {};
      //paramObj["name"] = name; 
      paramObj["verifyCode"] =  vc;  
      paramObj["name"] =  nameH;  
      var datas = jQuery.param(paramObj);
      $("#mask").mask("数据加载中，请稍候...");
      jQuery.ajax({
          url: getPath() + '/infoQueryServlet.json?queryCinfo=true',
          data: datas,
          type: 'POST',
          dataType:'json',
          async:true,
          success: function(data){
              $("#mask").unmask();//解除遮罩
            if (data[0] != null) {
              var mark = data[0];
              if(mark.TIPS != ''){   
                  jQuery.dialog.alert_Prompt(mark.TIPS,function(){ 
                });
                return false;
              }
              //验证码和查询使用同一个请求。所以 改用ajax请求，因为在查询时判断验证码
              $('#punishResult').html("<dl class=\"list\" >"+mark.INFO+"<br/><font color=\"red\">"+mark.COUNT+"&nbsp;&nbsp;&nbsp;&nbsp;</font></dl>");   
            }   
          },
          error: function(jqXHR, textStatus, errorThrown){
              $("#mask").unmask();//解除遮罩
          	document.getElementById("name").value='';    
              jQuery.dialog.alert_nocb('您输入的内容可能存在非法字符，请重新输入');
          }
        });
    }else{
	/**
	 * 查询结果页面直接查询的调用方法
	 */
        var name = document.getElementById("name").value;     //账号
        var verifyCode = document.getElementById("verifyCode").value;  //验证码
        if (name == ''||name == '请输入企业名称或注册号、统一社会信用代码') {
        	jQuery.dialog.alert_nocb('请输入企业名称或注册号、统一社会信用代码！');
            return;
          }
        if (!verifyChar(name)) {
        	jQuery.dialog.alert_nocb('您输入的内容可能存在非法字符，请重新输入');
            recordLog('34',name);
            resetSomeInfo();
            return;
          }
        if(!passIdentifyingCode){
        	if(verifyCode == ''||verifyCode=='请在查询框中正确输入你所看到的字符'){
        		jQuery.dialog.alert_nocb('请填写验证码！');
                return;
            }
        }
        rDate(4);
        var paramsObj = {};
        paramsObj["name"] = name;
        paramsObj["verifyCode"] = verifyCode; 
//        paramsObj["name"] =  nameH;  
        var datas = jQuery.param(paramsObj);
        $("#mask").mask("数据加载中，请稍候...");
        jQuery.ajax({
            url: getPath() + '/infoQueryServlet.json?query_info=true',
            data: datas,
            type: 'POST',
            dataType:'json',
            async:true,
            success: function(data){
        	$("#mask").unmask();//解除遮罩
              if (data[0] != null) { 
                var mark = data[0];
                if(mark.TIPS != ''){   
                  if(mark.TIPS != '验证码填写错误，请重新填写！'){
                	  recordLog('32');
                  }
                  jQuery.dialog.alert_Prompt(mark.TIPS,function(){
                	  document.getElementById("verifyCode").value='';
                      resetSomeInfo();
                  });
                  return false;
                }
                alert_win.style.display = 'none';
                //验证码和查询使用同一个请求。所以 改用ajax请求，因为在查询时判断验证码
                $('#punishResult').html("<dl class=\"list\" >"+mark.INFO+"<br/><font color=\"red\">"+mark.COUNT+"&nbsp;&nbsp;&nbsp;&nbsp;</font></dl>");   
                resetSomeInfo();  
              } 
            },
            error: function(jqXHR, textStatus, errorThrown){
            	recordLog('32');
        	$("#mask").unmask();//解除遮罩
            	document.getElementById("name").value='';    
                resetSomeInfo();  
                jQuery.dialog.alert_nocb('您输入的内容可能存在非法字符，请重新输入');
              //jQuery.dialog.alert_nocb('HTTP请求服务器失败');
            }
          }); 
    }  
}
function zdm() {
    var name = document.getElementById("name").value; // 账号
    document.getElementById("verifyCode").value='';
    if (name == '' || name == '请输入企业名称或注册号、统一社会信用代码') {
      alert('请输入企业名称或注册号、统一社会信用代码！');
      return;
    }
    alert_win.style.display = 'block';
    resetSomeInfo();
  }
function rDate(num){
	if(num){
		var url = getPath()+ '/commonServlet.json?commonEnter=true';
		var paramObj = {};
		paramObj["showRecordLine"] = '0';
		paramObj["specificQuery"] = 'commonQuery';
		paramObj["propertiesName"] = 'recordTime';
		paramObj["n"] = num;
		paramObj["tmp"] = new Date();
		
		var datas = jQuery.param(paramObj);
		
		jQuery
		.ajax({
			url : url,
			data : datas,
			type : 'POST',
			dataType : 'json',
			async : false,
			success : function(data) {
			},
			error : function(jqXHR, textStatus, errorThrown) {}
		});
	}
	
}
function resetSomeInfo(){
//  var nameDom = document.getElementById("name");        //企业名称或注册号
//  var verifyCodeDom = document.getElementById("verifyCode");  //验证码
  //document.getElementById('verifyCode').style.color='#BDBDBD';//将验证码输入框调整为灰色
  //verifyCodeDom.value = '请输入右侧验证码';//重置验证码输入框时复制成提示语
  if (document.all) { //IE
    updateVerifyCode();
  } else if (document.createEvent) {
    updateVerifyCode();
  }
}
function resetSomeInfo(){
//  var nameDom = document.getElementById("name");        //企业名称或注册号
//  var verifyCodeDom = document.getElementById("verifyCode");  //验证码
  //document.getElementById('verifyCode').style.color='#BDBDBD';//将验证码输入框调整为灰色
  //verifyCodeDom.value = '请输入右侧验证码';//重置验证码输入框时复制成提示语
  if (document.all) { //IE
    updateVerifyCode();
  } else if (document.createEvent) {
    updateVerifyCode();
  }
}
function updateVerifyCode(){
    var $updateVerifyCode = jQuery('#updateVerifyCode1');
    
    $updateVerifyCode.html('');
    $updateVerifyCode.html("<img class='inp_s_index1' border=\"0\" src=\""+getPath()+"/rand_img.jsp?type=8&temp="+new Date()+"\"  width=\"200px\" height=\"50px\"/>");
    tmp++;
 }

function searchCorp() {
    var name = document.getElementById("name").value; // 账号
    var verifyCode = document.getElementById("verifyCode").value; // 验证码

    if (verifyCode == '') {
      alert('提示：验证码输入错误！');
      return;
    }
    var paramsObj = {};
    if (!verifyChar(name)) {
      alert('您输入的内容可能存在非法字符，请重新输入');
      recordLog('34',name);
      resetSomeInfo();
      return;
    }
    paramsObj["verifyCode"] = verifyCode;
    var datas = jQuery.param(paramsObj);

    jQuery.ajax({
      url : getPath() + '/infoQueryServlet.json?checkCode=true',
      data : datas,
      type : 'POST',
      dataType : 'json',
      async : true,
      success : function(data) {
        if (data.bean != null) {
      	var tips = data.bean.tips;
          var mark = data.bean.mark;

          if(tips){
          	alert(tips);
          	return false;
          }else{
          	if (mark == '0') {
                  alert('提示：验证码输入错误！');
                  document.getElementById("verifyCode").value='';
                  resetSomeInfo();
                  recordLog('33');
                  return false;
                } else {
                  var $form = jQuery('#frmBasicInfo');
                  jQuery('#typeName').val(name);
                  $form.attr('action', getPath()
                      + '/queryResultList.jsp');
                  $form.submit();
                  alert_win.style.display='none';
                }
          }
        }
      },
      error : function(jqXHR, textStatus, errorThrown) {
        document.getElementById("name").value = '';
        recordLog('32');
        resetSomeInfo();
      }
    });
    
  }