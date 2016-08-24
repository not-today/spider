package com.heetian.spider.ocr.util;

import com.heetian.spider.ocr.exception.ImageConverte;

public class ResultProcess {
	private ValidateType type;
	private String[] contents;
	private ResultProcess() {
		super();
	}
	public static ResultProcess newInstance(ValidateType type,String[] contents) throws ImageConverte{
		if(contents==null||contents.length<=0)
			throw new ImageConverte("ResultProcess的contents参数不为为空");
		for(int x=0;x<contents.length;x++){
			if(contents[x]==null||"".equals(contents[x].trim())){
				throw new ImageConverte("ResultProcess的contents数组中有null值");
			}
		}
		return new ResultProcess(type,contents);
	}
	public ResultProcess(ValidateType type,String[] contents) {
		this();
		this.type = type;
		this.contents = contents;
	}
	public String process() throws Exception{
		String result = null;
		switch (this.type) {
		case count:
			result = String.valueOf(countTwoNumbSum(strToint(this.contents[0]), this.contents[1].toCharArray()[0], strToint(this.contents[2])));
			break;
		case noCount:
			StringBuilder sb = new StringBuilder();
			for(String ctmp :contents)
				sb.append(ctmp);
			result = sb.toString();
			break;
		default:
			break;
		}
		if(result==null||"".equals(result.trim()))
			return null;
		return result;
	}
	/**
	 * 
	 * @param str  一二三四五六七八九,零壹贰叁肆伍陆柒捌玖转化成数字123456789
	 * @return
	 */
	protected static int strToint(char str){
		if(str=='零'||str=='0'){
			return 0;
		}else if(str=='一'||str=='壹'||str=='1'){
			return 1;
		}else if(str=='二'||str=='贰'||str=='2'){
			return 2;
		}else if(str=='三'||str=='叁'||str=='3'){
			return 3;
		}else if(str=='四'||str=='肆'||str=='4'){
			return 4;
		}else if(str=='五'||str=='伍'||str=='5'){
			return 5;
		}else if(str=='六'||str=='陆'||str=='6'){
			return 6;
		}else if(str=='七'||str=='柒'||str=='7'){
			return 7;
		}else if(str=='八'||str=='捌'||str=='8'){
			return 8;
		}else if(str=='九'||str=='玖'||str=='9'){
			return 9;
		}else{
			throw new NumberFormatException("数字转换错误:"+str);
		}
	}
	/**
	 * 
	 * @param str  一二三四五六七八九,零壹贰叁肆伍陆柒捌玖转化成数字123456789
	 * @return
	 */
	protected static int strToint(String str){
		if(str==null||"".equals(str))
			throw new NumberFormatException("数字转换错误:被转化字符不能为null");
		if(!str.matches("[一二三四五六七八九零壹贰叁肆伍陆柒捌玖0123456789]{1,}"))
			throw new NumberFormatException("数字转换错误:含错误字符:"+str);
		if(str.length()==1){
			return strToint(str.charAt(0));
		}else if(str.length()>1){
			char[] tmps = str.toCharArray();
			String result = "";
			for(char ch:tmps){
				int x = strToint(ch);
				result = result + x;
			}
			if(!"".equals(result))
				return Integer.parseInt(result);
		}
		throw new NumberFormatException("数字转换错误:"+str);
	}
	public static int countTwoNumbSum(int x,char oper,int y) throws Exception{
		int result = Integer.MIN_VALUE;
		switch (oper) {
		case '+':
			result = x+y;
			break;
		case '-':
			result = x-y;
			break;
		case '*':
			result = x*y;
			break;
		case 'x':
			result = x*y;
			break;
		case '/':
			result = x/y;
		case '%':
			result = x%y;
			break;
		default:
			throw new Exception("操作字符出错:("+x+""+oper+""+y+")");
		}
		return result;
	}
}
