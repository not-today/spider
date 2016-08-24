package com.heetian.create.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtils {
	
	private static final int[] ORGAN_WEIGHT = {3,7,9,10,5,8,4,2};
	private static final String ORGAN_CONTAIN_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private static final int[] CREDIT_WEIGHT = {1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28};
	private static final String CREDIT_CONTAIN_CHARS = "0123456789ABCDEFGHJKLMNPQRTUWXY";
	
	
	public static boolean checkOrganCode(String organCode){
		
		// ����У��
		if(organCode.length() != 10 && organCode.length() != 9){
			return false;
		}
		
		if(organCode.length() == 10 && organCode.charAt(8) != '-'){
			return false;
		}
		
		// ͳһ��ʽ
		if(organCode.length() == 10 && organCode.charAt(8) == '-'){
			organCode = organCode.substring(0,8) + organCode.substring(9);
		}
		
		// ��ʽУ��
		String regex = "^([0-9ABCDEFGHJKLMNPQRTUWXY]{8})([0-9X])$";
		Pattern pat = Pattern.compile(regex);
		Matcher matcher = pat.matcher(organCode);
		if(!matcher.matches()){
			return false;
		}
			
		// ѭ������У��λ
		int sum = 0;
		for(int i = 0; i < organCode.length() - 1; i++ ){
			char c = organCode.charAt(i);
			sum += ORGAN_CONTAIN_CHARS.indexOf(c) * ORGAN_WEIGHT[i];
		}
		int c9 =  (11 - sum % 11) == 11 ? 0 : 11 - sum % 11;
		String checkCode = c9 == 10 ? "X" : Integer.toString(c9);
		
		
		if(checkCode.equals(organCode.substring(8))){
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	/**
	 * ͳһ������ô���У�����
	 * @param creditCode
	 * @return
	 */
	public static boolean checkCreditCode(String creditCode){
		
		// ����У��
		if(creditCode.length() != 18){
			return false;
		}
		
		
		// ��ʽУ��
		String regex = "^([0-9ABCDEFGHJKLMNPQRTUWXY]{2})([0-9]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{10})$";
		Pattern pat = Pattern.compile(regex);
		Matcher matcher = pat.matcher(creditCode);
		if(!matcher.matches()){
			return false;
		}
		
		
		// ѭ������У��λ
		int sum = 0;
		for(int i = 0; i < creditCode.length() - 1; i++ ){
			char c = creditCode.charAt(i);
			sum += CREDIT_CONTAIN_CHARS.indexOf(c) * CREDIT_WEIGHT[i];
		}
		
		int c18 = (31 - sum % 31) == 31 ? 0 : 31 - sum % 31;
		if(CREDIT_CONTAIN_CHARS.charAt(c18) == creditCode.charAt(17)){
			return true;
		}
		
		return false;
	}
	
	
	
	
	public static void main(String[] args) {
		
		boolean bool1 = checkCreditCode("911304041056497030");
		
		System.out.println(bool1);
		
		boolean bool2 = checkOrganCode("X1425457-0");
		
		System.out.println(bool2);
		
	}
	

}
