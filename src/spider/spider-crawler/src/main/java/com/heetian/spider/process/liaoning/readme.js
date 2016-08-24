function tzr_paging(obj, count, pripid, flag) {
	if (count == 0) {
		$("#tzr_holderDiv").text("");
		$("#tzr_holderDiv").css("text-align", "center");
	} else {
		for (var i = 0, id = pripid; i < count; i++) {
			var tr = $("<tr></tr>");

			var td = $("<td style=\"text-align:left;\">" + obj[i].invtypeName
					+ "</td>");
			td.appendTo(tr);

			var td = $("<td style=\"text-align:left;\">" + obj[i].inv + "</td>");
			td.appendTo(tr);

			var td = $("<td style=\"text-align:left;\">" + obj[i].blictypeName
					+ "</td>");
			td.appendTo(tr);

			var td = $("<td style=\"text-align:left;\">" + obj[i].blicno
					+ "</td>");
			td.appendTo(tr);

			if (flag == "false" || flag == "") {
				var td = $("<td style=\"text-align:center;\"></td>");
				td.appendTo(tr);
			} else {
				var a = $("<a href='/saicpub/entPublicitySC/entPublicityDC/tzrDetailAction.action?pripid="
						+ pripid + "' target='_blank'>详情</a>");
				var td = $("<td style=\"text-align:left;\"></td>");
				a.appendTo(td);
				td.appendTo(tr);
			}

			tr.appendTo($("#tzr_itemContainer"));
		}

		$("#tzr_holderDiv").jPages({
			containerID : "tzr_itemContainer",
			first : "《",
			last : "》",
			previous : false,
			next : false,
			perPage : 5,
			delay : 10
		});

	}
}
var webAppName = "";
var pripid = "";

function setParamTzrxx(contextPath,id){
	webAppName = contextPath;
	pripid = id;
}

/**
 * 查询股权变更列表信息
 * @return
 */
function getTzrxxList(path){
	$.ajax({
        type: "POST",
        url: webAppName+'/entPublicitySC/entPublicityDC/getGsgsTzrxxPojoList.action',
        data:{"pripid":pripid},
        async: true,
        error: function(request) {
            alert("信息查询出错！");
        },
        success: function(jsonArray) {
        	if(jsonArray == ""||jsonArray==null){
        		var t_html='<tr style="width:95%;"><th colspan="9" style="text-align:center;"></th></tr>';
        		$('#gdjczTab').append(t_html);
        	}else{
            	$(jsonArray).each(function(){
            		var t_html=getTzrxxHtmlStr(this);
            		$('#gdjczTab').append(t_html);
            	});
        	}

        }
    });
}
/**
 * 由TRegTzrxxPojo对象，绘制某个投资人信息的表格
 */
function getTzrxxHtmlStr(tzrxxPojo){
	var tzrxx=tzrxxPojo.tRegTzrxx;
	var rowspan;
	var isRjxxLong=true;//是否认缴信息条数比实缴多
	var muchMoreCount=0;//多几条
	if(tzrxxPojo.tRegTzrrjxxList.length>tzrxxPojo.tRegTzrsjxxList.length){
		rowspan=tzrxxPojo.tRegTzrrjxxList.length;
		muchMoreCount=tzrxxPojo.tRegTzrrjxxList.length-tzrxxPojo.tRegTzrsjxxList.length;
	}else{
		rowspan=tzrxxPojo.tRegTzrsjxxList.length;
		isRjxxLong=false;
		muchMoreCount=tzrxxPojo.tRegTzrsjxxList.length-tzrxxPojo.tRegTzrrjxxList.length;
	}
	var t_html="";
	if(rowspan==0){//没有认缴和实缴信息
		t_html = t_html + 
		'<tr >'+
        '<td style="text-align:left;" rowspan="'+rowspan+'" height="42">' + tzrxxPojo.tRegTzrxx.inv + '</td>'+
        '<td  style=\"display:none\">'+tzrxxPojo.tRegTzrxx.id+ '</td>'+
        '<td style="text-align:right;" rowspan="'+rowspan+'">' + tzrxxPojo.tRegTzrxx.lisubconam + '</td>'+
        '<td style="text-align:right;" rowspan="'+rowspan+'">' + tzrxxPojo.tRegTzrxx.liacconam + '</td>'+
        
        '<td style="text-align:left;" height="42" name="rjfs">' + (tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].conformName:'') + '</td>'+
        '<td style=\"display:none\" >' +(tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].conform:'') + '</td>'+
        '<td style="text-align:right;" >' +(tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].subconam:'') + '</td>'+
        '<td style="text-align:center;" >' + (tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].condateStr:'') + '</td>'+
        '<td  style=\"display:none\">'+(tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].id:'')+ '</td>'+
        '<td style="text-align:left;"  name="sjfs">' + (tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].conformName:'') + '</td>'+
        '<td style=\"display:none\" >' +(tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].conform:'') + '</td>'+
        '<td style="text-align:right;" >' +(tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].acconam:'') + '</td>'+
        '<td style="text-align:center;" >' + (tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].condateStr:'') + '</td>'+
        '</td><td  style=\"display:none\">'+(tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].id:'')+ '</td>'
	}
	for(var n = 0 ; n < rowspan ; n++ ){
		if(n==0){
			t_html = t_html + 
				'<tr >'+
                '<td style="text-align:left;" rowspan="'+rowspan+'" height="42">' + tzrxxPojo.tRegTzrxx.inv +  '</td>'+  
                '<td  style=\"display:none\">'+tzrxxPojo.tRegTzrxx.id+ '</td>'+   
                '<td style="text-align:right;" rowspan="'+rowspan+'">' + tzrxxPojo.tRegTzrxx.lisubconam + '</td>'+ 
                '<td style="text-align:right;" rowspan="'+rowspan+'">' + tzrxxPojo.tRegTzrxx.liacconam + '</td>'+ 
                
                
                '<td style="text-align:left;" height="42" name="rjfs">' + (tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].conformName:'') + '</td>'+//货币
                '<td style=\"display:none\" >' +(tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].conform:'') + '</td>'+   //conform
                '<td style="text-align:right;" >' +(tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].subconam:'') + '</td>'+    //subconam
                '<td style="text-align:center;" >' + (tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].condateStr:'') + '</td>'+ //condateStr
                '<td  style=\"display:none\">'+(tzrxxPojo.tRegTzrrjxxList[0]?tzrxxPojo.tRegTzrrjxxList[0].id:'')+ '</td>'+   //id
                
                
                '<td style="text-align:left;"  name="sjfs">' + (tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].conformName:'') + '</td>'+   //货币
                '<td style=\"display:none\" >' +(tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].conform:'') + '</td>'+   //conform
                '<td style="text-align:right;" >' +(tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].acconam:'') + '</td>'+     //acconam
                '<td style="text-align:center;" >' + (tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].condateStr:'') + '</td>'+    //condateStr
                '</td><td  style=\"display:none\">'+(tzrxxPojo.tRegTzrsjxxList[0]?tzrxxPojo.tRegTzrsjxxList[0].id:'')+ '</td>'     //id
	   }else{
    	 t_html = t_html + 
				'<tr >'+
				 '<td style="text-align:left;" height="42" name="rjfs">' + (tzrxxPojo.tRegTzrrjxxList[n]?tzrxxPojo.tRegTzrrjxxList[n].conformName:'') + '</td>'+
				 	'<td style=\"display:none\" >' +(tzrxxPojo.tRegTzrrjxxList[n]?tzrxxPojo.tRegTzrrjxxList[n].conform:'') + '</td>'+
	                '<td style="text-align:right;" >' +(tzrxxPojo.tRegTzrrjxxList[n]?tzrxxPojo.tRegTzrrjxxList[n].subconam:'') + '</td>'+
	                '<td style="text-align:center;" >' + (tzrxxPojo.tRegTzrrjxxList[n]?tzrxxPojo.tRegTzrrjxxList[n].condateStr:'') + '</td>'+
	                '<td  style=\"display:none\">'+(tzrxxPojo.tRegTzrrjxxList[n]?tzrxxPojo.tRegTzrrjxxList[n].id:'')+ '</td>'+
	                
	                
	                '<td style="text-align:left;"  name="sjfs">' + (tzrxxPojo.tRegTzrsjxxList[n]?tzrxxPojo.tRegTzrsjxxList[n].conformName:'') + '</td>'+
	                '<td style=\"display:none\" >' +(tzrxxPojo.tRegTzrsjxxList[n]?tzrxxPojo.tRegTzrsjxxList[n].conform:'') + '</td>'+
	                '<td style="text-align:right;" >' +(tzrxxPojo.tRegTzrsjxxList[n]?tzrxxPojo.tRegTzrsjxxList[n].acconam:'') + '</td>'+
	                '<td style="text-align:center;" >' + (tzrxxPojo.tRegTzrsjxxList[n]?tzrxxPojo.tRegTzrsjxxList[n].condateStr:'') + '</td>'+
	                '<td  style=\"display:none\">'+(tzrxxPojo.tRegTzrsjxxList[n]?tzrxxPojo.tRegTzrsjxxList[n].id:'')+ '</td>'+
                '</tr>';
    
     	}		
	}
	return t_html;
}

function getNowDateStr(){
	var nowDate=new Date();
	return nowDate.getFullYear()+"-"+(nowDate.getMonth()+1)+"-"+nowDate.getDate();
}


/**
 * 字符串转日期 2014年03月12日
 */
function strToDate_tzrxx(str){
	var vstr = str.replace("年",'-').replace("月",'-').replace("日",'');
	return vstr ;
}