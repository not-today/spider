function showPageData(pageNo){
    $("#list dt[id!=more]").remove();
    $("#list dd").remove();
    var line = "<dt>";
    line += "<a href='###'";
    line += " onclick='javascript:showDetail(\"#nbxh#\",\"#qymc#\",\"#zch#\",\"#ztlx#\",\"#qylx#\");'>";
    line += "#qymc#</a>";
    line += "</dt>";
    line += "<dd>";
    line += "统一社会信用代码/注册号:<span>#zch#</span>";
    line += "&nbsp;#fddbr_name#:<span>#fddbr#</span>";
    line += "&nbsp;登记机关:<span>#djjgmc#</span>";
    line += "&nbsp;成立日期:<span>#clrq#</span>";
    line += "</dd>";
    var total = _sczt == null ? 0 : _sczt.length;
    var start = (pageNo - 1) * _pageSize;
    var end = pageNo * _pageSize - 1;
    end = end > total - 1 ? total - 1 : end;
    var qyxx, row;
    for (i = start; i <= end; i ++) {
        qyxx = _sczt[i];
        row = line;
        row = row.replace(/#nbxh#/gm, qyxx.nbxh);
        row = row.replace(/#ztlx#/gm, qyxx.ztlx);
        row = row.replace(/#qylx#/gm, qyxx.qylx);
        if ((qyxx.qymc != null) && ($.trim(qyxx.qymc) != ""))
            row = row.replace(/#qymc#/gm, qyxx.qymc);
        else
            row = row.replace(/#qymc#/gm, qyxx.fddbr);
        row = row.replace(/#zch#/gm, qyxx.zch);
        row = row.replace(/#fddbr#/gm, qyxx.fddbr);
        row = row.replace(/#djjgmc#/gm, qyxx.djjgmc);
        if (qyxx.clrq == null)
            row = row.replace(/#clrq#/gm, "");
        else if (qyxx.clrq.indexOf("-") > 0)
            row = row.replace(/#clrq#/gm, qyxx.clrq.substr(0, 10));
        else
            row = row.replace(/#clrq#/gm, qyxx.clrq);
        row = row.replace(/#fddbr_name#/gm, getFddbrName(qyxx.ztlx, qyxx.qylx));
        $("#more").before(row);
    }
    generatePagination(pageNo);
}

function showDetail(nbxh, qymc, zch, ztlx, qylx){
    $("#nbxh").val(nbxh);
    $("#qymc").val(qymc);
    $("#zch").val(zch);
    var path = getScztPath(ztlx, qylx);
    if (path == ""){
        alert("未知企业类型！");
        return;
    }
    $("#showForm").attr("action", path + "/index.jsp");
    $("#showForm").submit();
}
function getScztPath(ztlx, qylx){
    var path = "";
    if (ztlx == "1"){
        if (qylx.indexOf("12") == 0){
            path = "gfgs";
        }else if (qylx.indexOf("1") == 0){
            path = "nzgs";
        }else if (qylx.indexOf("2") == 0){
            path = "nzgsfgs";
        }else if (qylx.indexOf("3") == 0){
            path = "nzqyfr";
        }else if (qylx == "4310"){
            path = "nzyyfz";
        }else if ((qylx == "4000") || (qylx.indexOf("41") == 0)
                || (qylx.indexOf("42") == 0) || (qylx.indexOf("43") == 0)
                || (qylx.indexOf("44") == 0) || (qylx.indexOf("46") == 0)
                || (qylx.indexOf("47") == 0)){
            path = "nzyy";
        }else if (qylx.indexOf("453") == 0){
            path = "nzhh";
        }else if (qylx == "4540"){
            path = "grdzgs";
        }else if (qylx.indexOf("455") == 0){
            path = "nzhhfz";
        }else if (qylx == "4560"){
            path = "grdzfzjg";
        }else if ((qylx.indexOf("50") == 0) || (qylx.indexOf("51") == 0)
                || (qylx.indexOf("52") == 0) || (qylx.indexOf("53") == 0)
                || (qylx.indexOf("60") == 0) || (qylx.indexOf("61") == 0)
                || (qylx.indexOf("62") == 0) || (qylx.indexOf("63") == 0)){
            path = "wstz";
        }else if (((qylx.indexOf("58") == 0) || (qylx.indexOf("68") == 0)
                || (qylx.indexOf("70") == 0) || (qylx.indexOf("71") == 0)
                || (qylx == '7310') || (qylx == "7390"))
                && (qylx != "5840") && (qylx != "6840")){
            path = "wstzfz";
        }else if ((qylx.indexOf("54") == 0) || (qylx.indexOf("64") == 0)){
            path = "wzhh";
        }else if ((qylx.indexOf("5840") == 0) || (qylx.indexOf("6840") == 0)){
            path = "wzhhfz";
        }else if (qylx == "7200"){
            path = "czdbjg";
        }else if (qylx == "7300"){
            path = "wgqycsjyhd";
        }else if (qylx == "9100"){
            path = "nmzyhzs";
        }else if (qylx == "9200"){
            path = "nmzyhzsfz";
        }
    }else if (ztlx == "2"){
        path = "gtgsh";
    }
    return path;
}
function showCodeWindow() {
	if ($("#codeWindow").is(":visible"))
		return;
	var q = $.trim($("#q").val());
	if (q == "") {
		alert("请输入企业名称或注册号！");
		$("#q").focus();
		return false;
	}
	if (q == "请输入企业名称或注册号") {
		alert("请输入企业名称或注册号！");
		$("#q").focus();
		return false;
	}
	$("#codeWindow").window({
		title : "验证码",
		top : 100,
		width : 400,
		minimizable : false,
		maximizable : false,
		draggable : true,
		resizable : false,
		shadow : false,
		modal : true
	});
	$("#codeWindow").show();
	refreshSearchCode();
}
function refreshSearchCode() {
	var url = "search!generateCode.shtml?validTag=searchImageCode&" + new Date().getTime();
	$("#imgCode").attr("src", url);
}
function jumpSearch() {
	var q = $.trim($("#q").val());
	var validCode = $.trim($("#validCode").val());
	if (q == "") {
		alert("请输入企业名称或注册号！");
		$("#q").focus();
		return false;
	}
	if (q == "请输入企业名称或注册号") {
		alert("请输入企业名称或注册号！");
		$("#q").focus();
		return false;
	}
	if (validCode == "") {
		alert("请输入验证码！");
		showCodeWindow();
		$("#validCode").focus();
		return false;
	}
	$("#searchForm input[name=validCode]").val(validCode);
	$("#searchForm").submit();
}




//通过initList方法的500毫秒自动执行调用此方法获得数据的
function searchSczt(){
    var q = $.trim($("#q").val());
    var validCode = $.trim($("#validCode").val());
    if (q == "") {
        alert("请输入企业名称或注册号！");
        $("#q").focus();
        return;
    }
    if (validCode == "") {
        alert("请输入验证码！");
        $("#validCode").focus();
        return;
    }
    if ($("#codeWindow").is(":visible"))
        closeCodeWindow();
    $("#list dt[id!=more]").remove();
    $("#list dd").remove();
    $("#more").hide();
    $ .ajax( {
        url : 'search!searchSczt.shtml',
        type : 'POST',
        data : {
            q : q,
            validCode : validCode
        },
        dataType : 'json',
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend : function() {
            $("#button").attr("disabled", "disabled");
            $("#more").show();
            $("#more").html("正在查询，请稍等……");
        },
        complete : function() {
            $("#button").removeAttr("disabled");
        },
        success : function(result) {
            if ((result.successed == null) || (!result.successed)) {
                if (result.message != null)
                    alert(result.message);
                else
                    alert("查询失败，请重试！");
                if (result.refresh)
                    refreshSearchCode();
                $("#more").hide();
                return;
            }
            if (result.refresh)
                refreshSearchCode();
            _sczt = result.data;
            if ((_sczt == null) || (_sczt.length == 0)) {
                $("#more") .html( "&gt;&gt;&nbsp;&nbsp;您输入的查询条件未查询到相关记录，请重新查询！&nbsp;&nbsp;&lt;&lt;");
                $("#more").show();
                return;
            }
            showPageData(1);
            if (result.count > 50) {
                var hint = "&gt;&gt;&nbsp;&nbsp;";
                hint += "您查询的信息多于50条记录，请输入更精确的查询条件！";
                hint += "&nbsp;&nbsp;&lt;&lt;";
                $("#more").html(hint);
                $("#more").show();
            } else  {
                $("#more").hide();
            }
        },
        error : function(x, msg, e) {
            alert("查询失败，请重试！");
            $("#more").hide();
        }
    });
}


/*<li id="jbxx" class="current" 
	 onclick="changeStyle('tabs','jbxx');
	 changeDiv('detailsCon','jibenxinxi');
	 searchOne(0, 5, '0b48d5e97d1312fee246f85f41498b76145ee36336ab3fa2a54dc5e8a7816503', '1_');
	 searchList(0, 3, '0b48d5e97d1312fee246f85f41498b76145ee36336ab3fa2a54dc5e8a7816503', 'bgxxTable', 3);
	 searchTzrList(2, 3, '0b48d5e97d1312fee246f85f41498b76145ee36336ab3fa2a54dc5e8a7816503', 'tzxxTable', 5);">登记信息</li>
<li id="baxx" 
	onclick="changeStyle('tabs','baxx');
	changeDiv('detailsCon','beian');
	searchQyZyryList(0, 8, '0b48d5e97d1312fee246f85f41498b76145ee36336ab3fa2a54dc5e8a7816503', 'zyryTable', 3);
	searchList(0, 9, '0b48d5e97d1312fee246f85f41498b76145ee36336ab3fa2a54dc5e8a7816503', 'fzjgTable', 5);
	searchOne(0, 36, '0b48d5e97d1312fee246f85f41498b76145ee36336ab3fa2a54dc5e8a7816503', '2_');">备案信息</li>*/

function searchOne(c, t, nbxh, prefix){
    if (_data[c + "|" + t] != null)
        return;
    $.ajax({
        url : 'search!searchData.shtml',
        type : 'POST',
        data :{
            c : c,
            t : t,
            nbxh : nbxh
        },
        dataType : 'json',
        success : function(result){
            if ((result.successed == null) || (!result.successed)){
                if (result.message != null)
                    alert(result.message);
                else
                    alert("查询失败，请重试！");
                return;
            }
            _data[c + "|" + t] = result.data;
            if ((result.data == null) || (result.data.length == 0))
                return;
            var data = result.data[0];
            var divKey = new Array();
            $.each(data, function(key, value){
                if (key == "nbxh")
                    return;
                if (key.indexOf("__") > 0){
                    divKey.push(key);
                    return;
                }
                if (value != null){
                    $("#" + prefix + key).html(value);
                }else{
                    $("#" + prefix + key).html("&nbsp;");
                }
            });
            var link = "<a href='###' onclick='javascript:showDesc(\"说明\", \"#sm#\");'>#text#</a>";
            $.each(divKey, function(i, row){
                var keys = row.split("__");
                if (keys.length < 2)
                    return;
                var text = $("#" + prefix + keys[0]).html();
                if ((text == null) || (text == " "))
                    return;
                if ((data[keys[1]] == null) || (data[keys[1]] == ""))
                    return;
                var sm = data[keys[1]].replace(/\r\n/gm, "<br/>").replace(/\n/gm, "<br/>").replace(/\r/gm,"<br/>");
                var html = link.replace(/#sm#/gm, sm).replace(/#text#/gm, text);
                $("#" + prefix + keys[0]).html(html);
            });
        },
        error : function(x, msg, e){
            alert("查询失败，请重试！");
        }
    });
}
function searchList(c, t, nbxh, tid, pageSize){
    $("#" + tid).show();
    if (_data[c + "|" + t] != null)
        return;
    var listField = _listFieldOrder[c + "|" + t];
    $("#" + tid + " tr:gt(1)").remove();
    if (listField == null)
        return;
    $.ajax({
        url : 'search!searchData.shtml',
        type : 'POST',
        data :{
            c : c,
            t : t,
            nbxh : nbxh
        },
        dataType : 'json',
        success : function(result){
            if ((result.successed == null) || (!result.successed)){
                if (result.message != null)
                    alert(result.message);
                else
                    alert("查询失败，请重试！");
                return;
            }
            _data[c + "|" + t] = result.data;
            if ((result.data == null) || (result.data.length == 0)){
                return;
            }
            var data = result.data;
            showPageData(tid, c, t, 1, pageSize);
        },
        error : function(x, msg, e){
            alert("查询失败，请重试！");
        }
    });
}
					 //2, 3, 'xxxx','tzxxTable', 5
function searchTzrList(c, t, nbxh, tid, pageSize){
    $("#" + tid).show();
    if (_data[c + "|" + t] != null)
        return;
    var listField = _listFieldOrder[c + "|" + t];
    $("#" + tid + " tr:gt(1)").remove();
    if (listField == null)
        return;
    $.ajax({
        url : 'search!searchData.shtml',
        type : 'POST',
        data :{
            c : c,
            t : t,
            nbxh : nbxh
        },
        dataType : 'json',
        success : function(result){
            if ((result.successed == null) || (!result.successed)){
                if (result.message != null)
                    appendTableMessage(tid, listField.length, result.message);
                else
                    appendTableMessage(tid, listField.length, "查询失败，请重试！");
                return;
            }
            _data[c + "|" + t] = result.data;
            if ((result.data == null) || (result.data.length == 0)){
                return;
            }
            var data = result.data;
            showTzrPageData(tid, c, t, 1, pageSize);
        },
        error : function(x, msg, e){
            appendTableMessage(tid, listField.length, "查询失败，请重试！");
        }
    });
}
_listFieldOrder["2|3"] = [ "tzrlxmc", "czmc", "zzlxmc", "zzbh", "1" ];
function showTzrPageData(tid, c, t, pageNo, pageSize){
    $("#" + tid + " tr:gt(1)").remove();
    var data = _data[c + "|" + t];
    if ((data == null) || (data.length == 0))
        return;
    var listField = _listFieldOrder[c + "|" + t];
    if (listField == null)
        return;
    var start = (pageNo - 1) * pageSize;
    var end = pageNo * pageSize - 1;
    end = end > data.length - 1 ? data.length - 1 : end;
    var row, line;
    var detailUrl = "<a href='###' onclick='javascript:showTzrDetail(\"#nbxh#\",\"#czmc#\");'>";
    detailUrl += "查看详情";
    detailUrl += "</a>";
    for (i = start; i <= end; i ++){
        row = data[i];
        line = "<tr>";
        //_listFieldOrder["2|3"] = [ "tzrlxmc", "czmc", "zzlxmc", "zzbh", "1" ];
        $.each(listField, function(j, field){
            line += "<td " + _breakWordStyle + ">";
            if (field != "1"){
                if (row[field] != null)
                    line += row[field];
                else
                    line += "&nbsp;";
            }else{
                if ((row.clcnt > 0) || (row.bgcnt > 0))
                    line += "&nbsp;";
                else
                	line += detailUrl.replace(/#nbxh#/gm, row.nbxh).replace(/#czmc#/gm, row.czmc);
            }
            line += "</td>";
        });
        line += "</tr>";
        $("#" + tid).append(line);
    }
    generatePagination(tid, listField.length, c, t, data, pageNo, pageSize, 1,"showTzrPageData");
}
function searchQyZyryList(c, t, nbxh, tid, pageSize){
    $("#" + tid).show();
    if (_data[c + "|" + t] != null)
        return;
    var listField = _listFieldOrder[c + "|" + t];
    $("#" + tid + " tr:gt(1)").remove();
    if (listField == null)
        return;
    $.ajax({
        url : 'search!searchData.shtml',
        type : 'POST',
        data :{
            c : c,
            t : t,
            nbxh : nbxh
        },
        dataType : 'json',
        success : function(result){
            if ((result.successed == null) || (!result.successed)){
                if (result.message != null)
                    appendTableMessage(tid, listField.length, result.message);
                else
                    appendTableMessage(tid, listField.length, "查询失败，请重试！");
                return;
            }
            _data[c + "|" + t] = result.data;
            if ((result.data == null) || (result.data.length == 0)){
                return;
            }
            var data = result.data;
            showQyZyryPageData(tid, c, t, 1, pageSize);
        },
        error : function(x, msg, e){
            appendTableMessage(tid, listField.length, "查询失败，请重试！");
        }
    });
}
function showDetail(nbxh, qymc, zch, ztlx, qylx){
    $("#nbxh").val(nbxh);
    $("#qymc").val(qymc);
    $("#zch").val(zch);
    if (ztlx == "1"){
        if (qylx.indexOf("1") == 0){
            $("#showForm").attr("action", "nzgs/index.jsp");
        }else if (qylx.indexOf("2") == 0){
            $("#showForm").attr("action", "nzgsfgs/index.jsp");
        }else if (qylx.indexOf("3") == 0){
            $("#showForm").attr("action", "nzqyfr/index.jsp");
        }else if ((qylx == "4000") || (qylx.indexOf("41") == 0)
                || (qylx.indexOf("42") == 0) || (qylx.indexOf("43") == 0)
                || (qylx.indexOf("44") == 0) || (qylx.indexOf("46") == 0)
                || (qylx.indexOf("47") == 0)){
            $("#showForm").attr("action", "nzyy/index.jsp");
        }else if (qylx.indexOf("453") == 0){
            $("#showForm").attr("action", "nzhh/index.jsp");
        }else if (qylx == "4540"){
            $("#showForm").attr("action", "grdzgs/index.jsp");
        }else if (qylx.indexOf("455") == 0){
            $("#showForm").attr("action", "nzhhfz/index.jsp");
        }else if (qylx == "4560"){
            $("#showForm").attr("action", "grdzfzjg/index.jsp");
        }else if ((qylx.indexOf("50") == 0) || (qylx.indexOf("51") == 0)
                || (qylx.indexOf("52") == 0) || (qylx.indexOf("53") == 0)
                || (qylx.indexOf("60") == 0) || (qylx.indexOf("61") == 0)
                || (qylx.indexOf("62") == 0) || (qylx.indexOf("63") == 0)){
            $("#showForm").attr("action", "wstz/index.jsp");
        }else if (((qylx.indexOf("58") == 0) || (qylx.indexOf("68") == 0)
                || (qylx.indexOf("70") == 0) || (qylx.indexOf("71") == 0)
                || (qylx == '7310') || (qylx == "7390"))
                && (qylx != "5840") && (qylx != "6840")){
            $("#showForm").attr("action", "wstzfz/index.jsp");
        }else if ((qylx.indexOf("54") == 0) || (qylx.indexOf("64") == 0)){
            $("#showForm").attr("action", "wzhh/index.jsp");
        }else if ((qylx.indexOf("5840") == 0) || (qylx.indexOf("6840") == 0)){
            $("#showForm").attr("action", "wzhhfz/index.jsp");
        }else if (qylx == "7200"){
            $("#showForm").attr("action", "czdbjg/index.jsp");
        }else if (qylx == "7300"){
            $("#showForm").attr("action", "wgqycsjyhd/index.jsp");
        }else if (qylx == "9100"){
            $("#showForm").attr("action", "nmzyhzs/index.jsp");
        }else if (qylx == "9200"){
            $("#showForm").attr("action", "nmzyhzsfz/index.jsp");
        }else{
            alert("未知企业类型：" + qylx);
            return;
        }
    }else if (ztlx == "2") {
        $("#showForm").attr("action", "gtgsh/index.jsp");
    }else {
        alert("未知主体类型：" + ztlx);
        return;
    }
    $("#showForm").submit();
}

function showTzrDetail(nbxh, czmc){
    $("#tzr_czmc").val(czmc);
    $("#tzrForm").submit();
}