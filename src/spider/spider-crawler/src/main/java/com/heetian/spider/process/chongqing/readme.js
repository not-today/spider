/*form id:search-from
	input id:search-keyword   name:key    当点击搜索调用了  $(".search-bar a").add("#research-button").click(function()
 */
//search.js
var blockCssTop = '20%';
if (window.screen.height < 800)
	blockCssTop = '3%';
var blockCss = {
	width : 'auto',
	top : blockCssTop,
	left : '32%',
	border : 'none',
	backgroundColor : 'none',
	cursor : 'default'
};
var messageblockCss = {
	border : 'none',
	padding : '15px',
	backgroundColor : '#000',
	'-webkit-border-radius' : '10px',
	'-moz-border-radius' : '10px',
	'border-radius' : '10px',
	opacity : .8,
	color : 'white'
};
var overlayCSS = {
	cursor : 'default',
	backgroundColor : '#333'
};
$.blockUI.defaults.fadeIn = 0;
$.blockUI.defaults.fadeOut = 0;
// 关键字数组
var key = new Array("重庆", "重庆市", "万州", "万州区", "涪陵", "涪陵区", "渝中", "渝中区", "大渡口",
		"大渡口区", "江北", "江北区", "沙坪坝", "沙坪坝区", "九龙坡", "九龙坡区", "南岸", "南岸区", "北碚",
		"北碚区", "万盛", "万盛区", "双桥", "双桥区", "渝北", "渝北区", "巴南", "巴南区", "长寿", "长寿区",
		"綦江", "綦江县", "潼南", "潼南县", "铜梁", "铜梁县", "大足", "大足县", "荣昌", "荣昌县", "璧山",
		"璧山县", "梁平", "梁平县", "城口", "城口县", "丰都", "丰都县", "垫江", "垫江县", "武隆", "武隆县",
		"忠县", "开县", "云阳", "云阳县", "奉节", "奉节县", "巫山", "巫山县", "巫溪", "巫溪县", "石柱",
		"石柱县", "秀山", "秀山县", "酉阳", "酉阳县", "彭水", "彭水县", "江津", "江津区", "合川", "合川区",
		"永川", "永川区", "南川", "南川区", "黔江", "黔江区", "高新", "高新区", "经济技术开发区",
		"经济技术开发区", "北部新区高新园", "北部新区经开园", "公司", "有限公司", "分公司", "门市", "集团",
		"股份有限公司");
// 查找关键字
function findkey(keyWord) {
	for (var i = 0; i < key.length; i++) {
		if (key[i] == keyWord)
			return true;
	}
	return false;
}
var validityPattern = /^[\w\u4e00-\u9fa5\uff08\uff09\u0028\u0029-]+$/i;
function checkValidity(keyWord) {

	keyWord = $.trim(keyWord);

	if (keyWord != null && keyWord != "" && validityPattern.test(keyWord)) {
		return true;
	}
	return false;
};
function checkKeyWord(keyWord) {
	var error = null;

	if (!checkValidity(keyWord))
		error = "查询条件中含有非法字符，请查证！";
	if (!keyWord || keyWord.length < 2 || keyWord.length > 100
			|| keyWord == "请输入企业名称或完整的注册号")
		error = "查询条件不能少于2个字且不能多于100个字!";

	if (findkey(keyWord))
		error = "请尽量输入详细的查询条件,不能只输入关键字,如:'重庆','有限','公司'等!";

	return error;
}
function showError(error) {
	if ($("#wrap .alert-error").size() == 0)
		$("#wrap").prepend("<div class='alert alert-error'></div>");
	$("#wrap .alert-error").html(error);
}

$(function() {
	// fixed msie placeholder
	Placeholders.enable();

	$("#refresh-code").add("#auth-code-img").click(
			function() {
				var action = $("#auth-code-img").attr("src").split("?")[0]
						+ "?width=130&height=40&fs=23&t="
						+ new Date().getTime();
				$("#auth-code-img").attr("src", action);
			});

	$(".search-bar a").add("#research-button").click(
			function() {

				var error = checkKeyWord($("#search-keyword").val());
				if (error) {
					showError(error);
					return;
				}

				$.blockUI({
					message : $("#searchAC"),
					css : blockCss,
					overlayCSS : overlayCSS,
					onBlock : function() {
						$("#auth-code").val("");
						$("#auth-code-img").attr("src",
								"sc.action?width=130&height=40&fs=23");
					}
				});
			});

	$("#close-button").click(function() {
		$.unblockUI();
	});

	$("#close-button1").click(function() {

		$.unblockUI();
	});

	$("#search-button").click(
			function() {
				$.unblockUI();
				var error = checkKeyWord($("#search-keyword").val());
				if (error)
					showError(error);
				else {
					$(".alert-error").remove();
					// var url = "search.action?code="+$("#auth-code").val();
					$("#search-from").append(
							"<input type='hidden' name='code' value='"
									+ $("#auth-code").val() + "'/>");
					$("#search-from").submit();
				}
			});

	$("#search-from #search-keyword").keyup(function(event) {
		event.stopPropagation();
		if (event.keyCode == 13) {
			$("#auth-code").focus();
		}
	});

	$("#auth-code").keyup(function(event) {
		event.stopPropagation();
		if (event.keyCode == 13) {
			$("#search-button").click();
		}
	});

	// 设置搜索类型
	if (location.search == "?stype=belong") {
		$("#search-from [name='stype']").val("belong");
	}

	$("#result .name")
			.click(
					function() {
						var form = $("<form style='display:none'></form>");
						form.appendTo("body");
						form.attr("action", "search_ent")
								.attr("method", "POST");
						form.append("<input name='id' value="
								+ $(this).attr("data-id") + ">");
						form.append("<input name='type' value="
								+ $(this).attr("data-type") + ">");
						form.append("<input name='name' value="
								+ $(this).html() + ">");
						form.append("<input name='entId' value="
								+ $(this).attr("data-entId") + ">");
						form.submit();
					});
	var tip = $("#tip-img");
	var speed = 0;
	var showDelay = 1200;
	var hideDelay = 150;
	/*
	 * tip.fadeIn(speed).delay(showDelay).fadeOut(speed).delay(hideDelay)
	 * .fadeIn(speed).delay(showDelay).fadeOut(speed).delay(hideDelay)
	 * .fadeIn(speed).delay(showDelay).fadeOut(speed).delay(hideDelay)
	 * .fadeIn(speed).delay(showDelay).fadeOut(speed);
	 */
	setInterval(function() {
		tip.fadeIn(speed).delay(showDelay).fadeOut(speed);
	}, showDelay + hideDelay);
});
//search.js

$("#result .name").click(function(){
	var form = $("<form style='display:none'></form>");
	form.appendTo("body");
	form.attr("action","search_ent").attr("method","POST");
	form.append("<input name='id' value="+ $(this).attr("data-id") +">");
	form.append("<input name='type' value="+ $(this).attr("data-type") +">");
	form.append("<input name='name' value="+ $(this).html() +">");
	form.append("<input name='entId' value="+ $(this).attr("data-entId") +">");
	form.submit();
});
