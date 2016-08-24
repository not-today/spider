网站相关的js代码以及资料：
//form :id searchform
 key:id searchtxt   name:kw     name:_csrf     id:secode   name:secode
function zdm() {
	alert_win.style.display = 'block';
	annlid.style.display = 'none';
	zmdid.style.display = 'block';
	onkeydownstate = true;
	newimg();
	$('#yzminput').trigger("focus");
}

function newimg() {
		$("#secimg").attr("src", "/aiccips/securitycode?"+Math.random() );
	};
	
function searching() {
		if(''==$("#yzminput").val()){
			alert("请输入验证码中算式计算结果（阿拉伯数字）");
			return;
		}
		if(''==$("#searchtxt").val()||'请输入企业名称或注册号'==$("#searchtxt").val()){
			alert("请输入企业名称或注册号");
			return;
		}
		$("#secode").val(toMD5Str($("#yzminput").val()));
		
		var validate = $("#searchform").valid();
		if(!validate)return false; 
		$("#searchform").submit();
	}	
	








	var encrpripid = '0b34ec377693fdb91a105483133e9698a50edd0d9b4b65df73ee91e35149cbb258d24d0f2bb5317e8c106618cc294ccf';
	var webroot ='/aiccips/';
	var enttype='1140';

	function r1() {
		$("#jibenxinxi").css("display", "block");
		$("#beian").css("display", "none");
		$("#guquanchuzhi").css("display", "none");
		$("#dongchandiya").css("display", "none");
		$("#jingyingyichangminglu").css("display", "none");
		$("#yanzhongweifaqiye").css("display", "none");
		$("#xingzhengchufa").css("display", "none");
		$("#chouchaxinxi").css("display", "none");
	}
	function r2() {
		$("#jibenxinxi").css("display", "none");
		$("#beian").css("display", "block");
		$("#guquanchuzhi").css("display", "none");
		$("#dongchandiya").css("display", "none");
		$("#jingyingyichangminglu").css("display", "none");
		$("#yanzhongweifaqiye").css("display", "none");
		$("#xingzhengchufa").css("display", "none");
		$("#chouchaxinxi").css("display", "none"); 
		getRyxx();	
		getFzjg();
		}
	function r3() {
		$("#jibenxinxi").css("display", "none");
		$("#beian").css("display", "none");
		$("#guquanchuzhi").css("display", "block");
		$("#dongchandiya").css("display", "none");
		$("#jingyingyichangminglu").css("display", "none");
		$("#yanzhongweifaqiye").css("display", "none");
		$("#xingzhengchufa").css("display", "none");
		$("#chouchaxinxi").css("display", "none"); 
		getGqcz();
	}
	function r4() {
		$("#jibenxinxi").css("display", "none");
		$("#beian").css("display", "none");
		$("#guquanchuzhi").css("display", "none");
		$("#dongchandiya").css("display", "block");
		$("#jingyingyichangminglu").css("display", "none");
		$("#yanzhongweifaqiye").css("display", "none");
		$("#xingzhengchufa").css("display", "none");
		$("#chouchaxinxi").css("display", "none"); 
		getDcdy();
	}
	function r5() {
		$("#jibenxinxi").css("display", "none");
		$("#beian").css("display", "none");
		$("#guquanchuzhi").css("display", "none");
		$("#dongchandiya").css("display", "none");
		$("#jingyingyichangminglu").css("display", "block");
		$("#yanzhongweifaqiye").css("display", "none");
		$("#xingzhengchufa").css("display", "none");
		$("#chouchaxinxi").css("display", "none"); 
		getJyyc();
	}
	function r6() {
		$("#jibenxinxi").css("display", "none");
		$("#beian").css("display", "none");
		$("#guquanchuzhi").css("display", "none");
		$("#dongchandiya").css("display", "none");
		$("#jingyingyichangminglu").css("display", "none");
		$("#yanzhongweifaqiye").css("display", "block");
		$("#xingzhengchufa").css("display", "none");
		$("#chouchaxinxi").css("display", "none"); 
		getYzwfqy();
	}
	function r7() {
		$("#jibenxinxi").css("display", "none");
		$("#beian").css("display", "none");
		$("#guquanchuzhi").css("display", "none");
		$("#dongchandiya").css("display", "none");
		$("#jingyingyichangminglu").css("display", "none");
		$("#yanzhongweifaqiye").css("display", "none");
		$("#xingzhengchufa").css("display", "block");
		$("#chouchaxinxi").css("display", "none"); 
		getXzcf();
	}
	function r8() {
		$("#jibenxinxi").css("display", "none");
		$("#beian").css("display", "none");
		$("#guquanchuzhi").css("display", "none");
		$("#dongchandiya").css("display", "none");
		$("#jingyingyichangminglu").css("display", "none");
		$("#yanzhongweifaqiye").css("display", "none");
		$("#xingzhengchufa").css("display", "none");
		$("#chouchaxinxi").css("display", "block"); 
		 getCcjc();
	}

	function togo(str) {
		if (str == '1') {
			window.location = '../../gsgsdetail/1140/0b34ec377693fdb91a105483133e9698a50edd0d9b4b65df73ee91e35149cbb258d24d0f2bb5317e8c106618cc294ccf';
		} else if (str == '2') {
			window.location = '../../qygsdetail/1140/0b34ec377693fdb91a105483133e9698a50edd0d9b4b65df73ee91e35149cbb258d24d0f2bb5317e8c106618cc294ccf';
		} else if (str == '3') {
			window.location = '../../qtgsdetail/1140/0b34ec377693fdb91a105483133e9698a50edd0d9b4b65df73ee91e35149cbb258d24d0f2bb5317e8c106618cc294ccf';
		} else if (str == '4') {
			window.location = '../../sfgsdetail/1140/0b34ec377693fdb91a105483133e9698a50edd0d9b4b65df73ee91e35149cbb258d24d0f2bb5317e8c106618cc294ccf';
		}
	}

	var czxxliststr ='[{"blicno":"2200001009764","blictype":"内资企业法人","inv":"吉林省国有资产经营管理有限责任公司","invtype":"企业法人","liacconam":3006,"lisubconam":3006,"pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","recid":"b46f72f5d70a8b5a498c7518b654d535a32b0a294e3f0c7c30c18b27c98b4d1072117ea8dd17789538f1c9b763978879","xzqh":"2200"},{"blicno":"2201151002252","blictype":"内资企业法人","inv":"吉林通钢矿业有限责任公司","invtype":"企业法人","liacconam":11780,"lisubconam":11780,"pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","recid":"948ddf6ba970a894a8920a5f1f8fd5154d8620520cd80a04aa1ce0189b3682bf72117ea8dd17789538f1c9b763978879","xzqh":"2200"}]';
    var bgsxliststr ='[{"altaf":"铁矿石开采；炼铁；汽车运输；钢材销售；水泥砖；水泥瓦；涂料销售；石灰石开采、加工、销售；生石灰、材料销售；门窗维修（分支机构经营）；餐饮、住宿（分支机构经营）；烧结；自有土地、房屋、设施（构筑物）、设备租赁；技术咨询、工程勘察勘探服务","altbe":"铁矿石开采；炼铁；汽车运输；钢材销售；水泥砖；水泥瓦；涂料销售；石灰石开采、加工、销售；生石灰、材料销售；门窗维修（分支机构经营）；餐饮、住宿（分支机构经营）；烧结***","altdate":{"date":14,"day":4,"hours":0,"minutes":0,"month":7,"seconds":0,"time":1407945600000,"timezoneOffset":-480,"year":114},"altitem":"经营范围变更","openo":"d1aa4d5d-0147-1000-e000-c0cfc0a80102","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"陈豁祥,董事长,经理；华金生,董事；李朝霞,监事会主席；马茹宏,监事（职工代表）；王帧,监事；赵洪来,董事","altbe":"刘福,监事；吴波,董事长,经理；张国成,副董事长；赵洪来,董事","altdate":{"date":11,"day":5,"hours":0,"minutes":0,"month":9,"seconds":0,"time":1381420800000,"timezoneOffset":-480,"year":113},"altitem":"董事备案","openo":"a47fba9a-0141-1000-e001-9ad2c0a80102","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"股东名称：吉林省国有资产经营管理有限责任公司出资额：3006万元；,股东名称：吉林通钢矿业有限责任公司出资额：11780万元；","altbe":"股东名称：吉林省国有资产经营管理有限责任公司出资额：3006万元；,股东名称：通化钢铁集团有限责任公司出资额：11780万元；","altdate":{"date":11,"day":5,"hours":0,"minutes":0,"month":9,"seconds":0,"time":1381420800000,"timezoneOffset":-480,"year":113},"altitem":"投资人(股权)变更","openo":"a47fba9a-0141-1000-e001-9ad4c0a80102","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"刘福,监事；吴波,董事长,经理；张国成,副董事长；赵洪来,董事","altbe":"刘福,监事；刘福,监事会主席；明兆奎 ,董事长；张国成,副董事长","altdate":{"date":14,"day":2,"hours":0,"minutes":0,"month":11,"seconds":0,"time":1292256000000,"timezoneOffset":-480,"year":110},"altitem":"董事备案","openo":"68aaddd6-012f-1000-e010-6347c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"股东名称：吉林省国有资产经营管理有限责任公司出资额：3006万元；,股东名称：通化钢铁集团有限责任公司出资额：11780万元；","altbe":"股东名称：吉林省国有资产经营管理有限责任公司出资额：3006万元；,股东名称：通化钢铁集团有限责任公司出资额：11780万元；","altdate":{"date":14,"day":3,"hours":0,"minutes":0,"month":9,"seconds":0,"time":1255449600000,"timezoneOffset":-480,"year":109},"altitem":"投资人(股权)变更","openo":"68aaddd6-012f-1000-e010-6341c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"14786.0000","altbe":"0.0000","altdate":{"date":11,"day":5,"hours":0,"minutes":0,"month":3,"seconds":0,"time":1207843200000,"timezoneOffset":-480,"year":108},"altitem":"","openo":"68aaddd6-012f-1000-e010-6327c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"2009-10-31","altbe":"2007-12-19","altdate":{"date":11,"day":5,"hours":0,"minutes":0,"month":3,"seconds":0,"time":1207843200000,"timezoneOffset":-480,"year":108},"altitem":"经营期限(营业期限)变更","openo":"68aaddd6-012f-1000-e010-632ac0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"刘福,监事；刘福,监事会主席；明兆奎 ,董事长；张国成,副董事长","altbe":"","altdate":{"date":11,"day":5,"hours":0,"minutes":0,"month":3,"seconds":0,"time":1207843200000,"timezoneOffset":-480,"year":108},"altitem":"董事备案","openo":"68aaddd6-012f-1000-e010-632cc0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"股东名称：吉林省国有资产经营管理有限责任公司出资额：3006万元；,股东名称：通化钢铁集团有限责任公司出资额：11780万元；","altbe":"","altdate":{"date":11,"day":5,"hours":0,"minutes":0,"month":3,"seconds":0,"time":1207843200000,"timezoneOffset":-480,"year":108},"altitem":"投资人(股权)变更","openo":"68aaddd6-012f-1000-e010-632fc0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"铁矿石开采；炼铁；汽车运输；钢材销售；水泥砖；水泥瓦；涂料销售；石灰石开采、加工、销售；生石灰、材料销售；门窗维修（分支机构经营）；餐饮、住宿（分支机构经营）；烧结***","altbe":"铁矿石开采；铅锌开采；炼铁；汽车运输；钢材销售；水泥砖；水泥瓦；涂料销售；石灰石开采、加工、销售；生石灰、材料销售；门窗维修（分支机构经营）；餐饮、住宿（分支机构经营）；烧结***","altdate":{"date":11,"day":5,"hours":0,"minutes":0,"month":3,"seconds":0,"time":1207843200000,"timezoneOffset":-480,"year":108},"altitem":"经营范围变更","openo":"68aaddd6-012f-1000-e010-6329c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"明兆奎","altbe":"NULL","altdate":{"date":27,"day":4,"hours":0,"minutes":0,"month":8,"seconds":0,"time":1190822400000,"timezoneOffset":-480,"year":107},"altitem":"法定代表人变更","openo":"68aaddd6-012f-1000-e010-6325c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"通化钢铁集团大栗子矿业有限责任公司","altbe":"通化钢铁集团大栗子矿业有限责任公司","altdate":{"date":28,"day":4,"hours":0,"minutes":0,"month":5,"seconds":0,"time":1182960000000,"timezoneOffset":-480,"year":107},"altitem":"名称变更","openo":"68aaddd6-012f-1000-e010-6323c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"14786.0000","altbe":"6186.0000","altdate":{"date":27,"day":3,"hours":0,"minutes":0,"month":5,"seconds":0,"time":1182873600000,"timezoneOffset":-480,"year":107},"altitem":"注册资本(金)变更","openo":"68aaddd6-012f-1000-e010-6321c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"NULL","altbe":"明兆奎","altdate":{"date":16,"day":5,"hours":0,"minutes":0,"month":5,"seconds":0,"time":1150387200000,"timezoneOffset":-480,"year":106},"altitem":"法定代表人变更","openo":"68aaddd6-012f-1000-e010-630fc0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"0.0000","altbe":"5957.0000","altdate":{"date":16,"day":5,"hours":0,"minutes":0,"month":5,"seconds":0,"time":1150387200000,"timezoneOffset":-480,"year":106},"altitem":"","openo":"68aaddd6-012f-1000-e010-6311c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"","altbe":"明兆奎,董事长","altdate":{"date":16,"day":5,"hours":0,"minutes":0,"month":5,"seconds":0,"time":1150387200000,"timezoneOffset":-480,"year":106},"altitem":"董事备案","openo":"68aaddd6-012f-1000-e010-6317c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"铁矿石开采；铅锌开采；炼铁；汽车运输；钢材销售；水泥砖；水泥瓦；涂料销售；石灰石开采、加工、销售；生石灰、材料销售；门窗维修（分支机构经营）；餐饮、住宿（分支机构经营）；烧结***","altbe":"铁矿石开采、铅锌开采、炼铁、汽车运输、钢材销售、水泥砖、水泥瓦、涂料、门窗维修","altdate":{"date":16,"day":5,"hours":0,"minutes":0,"month":5,"seconds":0,"time":1150387200000,"timezoneOffset":-480,"year":106},"altitem":"经营范围变更","openo":"68aaddd6-012f-1000-e010-6314c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"},{"altaf":"6186.0000","altbe":"5957.0000","altdate":{"date":16,"day":5,"hours":0,"minutes":0,"month":5,"seconds":0,"time":1150387200000,"timezoneOffset":-480,"year":106},"altitem":"注册资本(金)变更","openo":"68aaddd6-012f-1000-e010-6310c0a80101","pripid":"ed670d27-7e7e-41c1-af6c-438eb3857252","xzqh":"3700"}]';
    var czxxlist=null;
    var bgsxlist=null;
	$(document)
			.ready(
					function() {
						var isjyyc=false;
						var hasccjc=false;
						if(isjyyc){
							r5();
							changeStyle('tabs',$("#5"));
						}else if(hasccjc){
								r8();
								changeStyle('tabs',$("#8"));
						 }
						//出资信息
						if(''!=czxxliststr){
							czxxlist =eval('('+czxxliststr.replace(/\r/ig, "").replace(/\n/ig, "")+')');
						    setczxxpagenumbers(czxxlist.length);//出资信息分页
						    czxxpage(1);
						}
						//变更事项
						if(''!=bgsxliststr){
							bgsxlist =eval('('+bgsxliststr.replace(/\r/ig, "").replace(/\n/ig, "")+')');
						    setbgsxpagenumbers(bgsxlist.length);//出资信息分页
						    bgsxpage(1);
						}
					});
	//出资信息分页
	function setczxxpagenumbers(size){
		if(size==0) return;
		var   count=Math.ceil(size/5);
		$("#tzrpages").append("<span><a href='javascript:czxxpage(1);'>&lt;&lt;</a></span>&nbsp;&nbsp;");
		for (var i = 0; i <count; i++) {
			$("#tzrpages").append("<a href='javascript:czxxpage("+(i+1)+");'>"+(i+1)+"</a>&nbsp;&nbsp;");
		};
		$("#tzrpages").append("</span><span><a href='javascript:czxxpage("+count+");'>&gt;&gt;</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	}
	//出资信息分页
	function czxxpage(page){
		$("#czxxtable").children().remove();
		for (var i= 5*(page-1); i < 5*page; i++) {
			if(i<czxxlist.length){
				var czxx=czxxlist[i];
				if(czxx.xzqh=="1"){
					$("#czxxtable")
					.append(
							"<tr>"
									+ "<td>"
									+ czxx.invtype
									+ "</td>"
									+ "<td>"
									+ czxx.inv
									+ "</td>"
									+ "<td>" 
									+ czxx.blictype
									+ "</td>"
									+ "<td>"
									+ czxx.blicno
									+ "</td>"
									+ "<td>"
									+ '<a href="'+webroot+'pub/gsnzczxxdetail/'+encrpripid.toString()+'/'+czxx.recid+'" target="_blank">详情</a>'
									+ "</td>"
									+ "</tr>");	
				}else{
					$("#czxxtable")
					.append(
							"<tr>"
									+ "<td>"
									+ czxx.invtype
									+ "</td>"
									+ "<td>"
									+ czxx.inv
									+ "</td>"
									+ "<td>"
									+ czxx.blictype
									+ "</td>"
									+ "<td>"
									+ czxx.blicno
									+ "</td>"
									+ "<td>"
									+ "</td>"
									+ "</tr>");	
				}
			}
		}
	}
	//变更信息分页
	function setbgsxpagenumbers(size){
		if(size==0) return;
		var  count=Math.ceil(size/5);
		$("#bgsxpages").append("<span><a href='javascript:bgsxpage(1);'>&lt;&lt;</a></span>&nbsp;&nbsp;"); 
		for (var i = 0; i <count; i++) {
			$("#bgsxpages").append("<a href='javascript:bgsxpage("+(i+1)+");'>"+(i+1)+"</a>&nbsp;&nbsp;");
		};
		$("#bgsxpages").append("</span><span><a href='javas	function changeStyle(divId, ele) {
		var liAry = document.getElementById(divId).getElementsByTagName("li");
		var liLen = liAry.length;
		var liID = ele.id;
		for (var i = 0; i <liLen; i++) 
		{
			if (liAry[i].id == liID) 
			{
				
				liAry[i].className = "current";
 } else 
			{
				liAry[i].className = "";
			}
			;
		}
		;
	}

	function ShowSpan(obj, n) {
		var span = obj.parentNode.getElementsByTagName("tabs");
		for (var i = 0; i <span.length; i++) {
			span[i].className = "current";
		}
		span[n].className = "";
		var li = obj.parentNode.getElementsByTagName("li");
		li[n].className = "current";
		for (var i = 0; i <li.length; i++) {
			if (i != n) {
				li[i].className = "";
			}
			li[i].onmouseout = function() {
				this.className = "current";
			};
		}
		;
	}
	function v1h2(first,second){
		$("#"+first+"").css("display", "block");
		$("#"+second+"").css("display", "none");
	}
</script>cript:bgsxpage("+count+");'>&gt;&gt;</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	}
	//变更事项分页
	function bgsxpage(page){
		$("#bgsxtable").children().remove();
		var recordHtml="";
		var altbe="";
		var altaf="";
		for (var i= 5*(page-1); i < 5*page; i++) {
			if(i<bgsxlist.length){
				var bgsx=bgsxlist[i];
				altbe=(null!=bgsx.altbe&&undefined !=bgsx.altbe?bgsx.altbe:"");
				altaf=(null!=bgsx.altaf&&undefined !=bgsx.altaf?bgsx.altaf:"");
				recordHtml="<tr><td>"+ bgsx.altitem + "</td><td>";
				if(altbe.length>40){
					recordHtml+='<span id="beless'+i+'" style="display:block">';
					recordHtml+=altbe.substring(0,40);
					recordHtml+='<a href="###" onclick="v1h2(\'bemore'+i+'\',\'beless'+i+'\')">更多</a></span>';
					recordHtml+='<span id="bemore'+i+'" style="display:none">';
					recordHtml+=altbe;
					recordHtml+='<a href="###" onclick="v1h2(\'beless'+i+'\',\'bemore'+i+'\')">收起更多</a></span>';
				}else{
					recordHtml+=altbe;
				}
				recordHtml+="</td><td>";
				 if(altaf.length>40){
					 recordHtml+='<span id="afless'+i+'" style="display:block">';	
					 recordHtml+=altaf.substring(0,40);
					 recordHtml+='<a href="###" onclick="v1h2(\'afmore'+i+'\',\'afless'+i+'\')">更多</a></span>';
					 recordHtml+='<span id="afmore'+i+'" style="display:none">';
					 recordHtml+=altaf;
					 recordHtml+='<a href="###" onclick="v1h2(\'afless'+i+'\',\'afmore'+i+'\')">收起更多</a></span>';
				 }else{
					 recordHtml+=altaf;
				 }
				 recordHtml+="</td><td>"+ (JsonSetTime(bgsx.altdate))+"</td></tr>";
	
				$("#bgsxtable").append(recordHtml);
			}
		}
	}
