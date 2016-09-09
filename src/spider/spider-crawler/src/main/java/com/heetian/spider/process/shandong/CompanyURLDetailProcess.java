package com.heetian.spider.process.shandong;

import java.util.Iterator;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.process.abstractclass.ShanDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;
/**
 * 
 * @author tst
 *
 */

/*
 var webroot ='/';
var enckeyword='640cc7c16f23e739b925086dbad9e3a3'; 
var searchres=[];
var pages=1;
 $(document).ready( function() { 
	 loadres();
 });

 function loadres(){
	 var url = webroot+"pub/search"; 
		var token = $("meta[name='_csrf']").attr("content");
			$.ajax({
				url : url,
				async : true,
				data:{ 
					param:enckeyword
				},
				type: "POST",
				headers:{
					"X-CSRF-TOKEN":token
				}, 
				dataType: "json",
				success : function(data) {
					searchres=data;
					if(searchres.length<50)$("#hasmore").hide();
					pages=Math.ceil(data.length/10);
					pageResult(1);
				},
				error : function() {
					return false;
				}
			});
 }
 function pageResult(page){
    $("#resultlist").children().remove();
	for (var i = (page-1)*10; i <10*page; i++) {
		 var resu=searchres[i];
		 if(resu!=undefined){
			 var tmphtml="";
			 tmphtml+="<div class=\"list\" >";
			 tmphtml+="<ul>";
			 tmphtml+="<li class=\"font16\"> ";
			 tmphtml+="<a href=\"gsgsdetail/"+resu.enttype+"/"+resu.encrptpripid+"\" target=\"_blank\">"+resu.entname;
			 tmphtml+="</a></li>";
			 tmphtml+="<li class=\"font14\">注册号/统一社会信用代码:<span>"+resu.regno+"</span> &nbsp;";
			 tmphtml+="<span>"+resu.enttypename+"</span> &nbsp;<span>"+resu.lerep+"</span> &nbsp;";
			 tmphtml+="登记机关:<span>"+resu.regorgname+"</span> &nbsp; 成立日期:<span>"+resu.estdate+"</span></li>";
			 tmphtml+="</ul>";
			 tmphtml+="</div>";
			 $("#resultlist").append(tmphtml);
		 }
	}
	setpage(page);
}
 */
public class CompanyURLDetailProcess extends ShanDongProcessHandlePrepare{
	public CompanyURLDetailProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/pub/search");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		List<CompanyBean> companys = AnalysisForJson.jsonToObject(page.getRawText(),new TypeToken<List<CompanyBean>>() {});
		if(companys==null||companys.size()<=0)
			return;
		Iterator<CompanyBean> iter = companys.iterator();
		while(iter.hasNext()){
			CompanyBean bean = iter.next();
			String regnumber = bean.getRegno();
			String entname = bean.getEntname();
			String  enttype= bean.getEnttype();
			String encrpripid = bean.getEncrptpripid();
			String url = "/pub/gsgsdetail/"+enttype+"/"+encrpripid;
			if(!TSTUtils.checkIsExitForEnter(task, regnumber,entname )){
				iter.remove();
				continue;
			}
			Request request = builderRequest(builderURL(url,task.getSite()),Method.GET, regnumber,entname, null);
			request.putExtra("enttype", enttype);
			request.putExtra("encrpripid", encrpripid);
			page.addTargetRequest(request);
		}
	}
}
