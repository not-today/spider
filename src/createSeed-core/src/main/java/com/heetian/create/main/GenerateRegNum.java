package com.heetian.create.main;

import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.dbcp.dao.inter.RegisterDaoInter;
import com.heetian.spider.dbcp.utils.DaoFactory;

public class GenerateRegNum {
	private  static RegisterDaoInter inter = DaoFactory.instanceReg("com.heetian.spider.dbcp.dao.imple.RegisterDaoImple");
	public static void main(String[] args) {
		List<String> organList = inter.getAllRegionCode();
		for(String organCode : organList){
			List<String> regList = inter.getRegListByRegionCode(organCode);
			List<String> newRegList = getNewRegList(organCode,regList);
			System.out.println("组织结构编号:       " + organCode);
			System.out.println("已存在注册号数:       " + (regList == null ? 0 : regList.size()));
			System.out.println("新生成注册号数:       " + (newRegList == null ? 0 : newRegList.size()));
			inter.batchInsertSeedRg(newRegList);
			System.out.println("======================================================================");
		}
	}
	/**
	 * 创建新注册号
	 * @param organCode
	 * @param regList
	 * @return
	 */
	private static List<String> getNewRegList(String organCode , List<String> regList){
		if(regList == null || regList.size() == 0) return null;
		List<String> newRegList = new ArrayList<String>();
		// �����ע���
		String type = null;		// 0,1,2,3,4,5,6,7,8,9,NA,NB
		int last = 1;
		for(String regNumber : regList){
			String index7 = regNumber.substring(6,7);		// ��7λ
			String index78 = regNumber.substring(6,8);		// ��7-8λ
			if(type == null || (!index7.equals(type) && !index78.equals(type))){
				type = index7.equalsIgnoreCase("N") ? index78 : index7;
				String lastStr = type.equals(index78) ? regNumber.substring(8,14) : regNumber.substring(7,14);
				boolean isNum = lastStr.matches("[0-9]+");
				if(!isNum){
					continue;
				}
				last = Integer.parseInt(lastStr);
				int start = last <= 10 ? 0 :  last - 10;
				newRegList.addAll(getNewRegListByArea(organCode,type,start,last));
				continue;
			}
			String nowStr = type.equals(index78) ? regNumber.substring(8,14) : regNumber.substring(7,14);
			boolean isNum = nowStr.matches("[0-9]+");
			if(!isNum){
				continue;
			}
			int now = Integer.parseInt(nowStr);
			if(now - last <= 20){
				newRegList.addAll(getNewRegListByArea(organCode,type,last,now));
			}
			else{
				newRegList.addAll(getNewRegListByArea(organCode, type, last, last+10));	 	// ǰ�����50��ע����
				newRegList.addAll(getNewRegListByArea(organCode, type, now - 10, now));
			}
			last = now;
		}
		newRegList.addAll(getNewRegListByArea(organCode, type, last, last + 10));
		return newRegList;
	}
	/**
	 * �����������ע���
	 * @param organCode
	 * @param type
	 * @param start
	 * @param end
	 * @return
	 */
	private static List<String> getNewRegListByArea(String organCode, String type, Integer start, Integer end){
		List<String> newRegList = new ArrayList<String>();
		while(++start < end){
			String sequenceCode = "";											// ˳����
			int length = 14 - organCode.length() - type.length();				// ˳���볤��
			for(int i = 0 ; i < length - start.toString().length()  ; i++){		// ����˳����ǰ��0
				sequenceCode += "0";
			}
			sequenceCode += start.toString();
			String regNumber = organCode + type + sequenceCode;					// ��֯�ṹ��� + ����  + ˳���� 
			String checkCode =  getCheckCode(type, regNumber);					// У����
			newRegList.add(regNumber + checkCode);
		}
		return newRegList;
	}
	/**
	 * ���У��λ
	 * @param 0��1��2��3��4��5��6��7��8��9��NA��NB
	 * @param registerCode
	 * @return
	 */
	private static String getCheckCode(String type , String registerCode){
		if(type.equalsIgnoreCase("NA") || type.equalsIgnoreCase("NB")){
			return "X";
		}
		int remainder = 10;
		for(int i = 0; i < registerCode.length(); i++){
            int temp = ( remainder + Integer.parseInt(registerCode.charAt(i)+"")) % 10;
            temp = temp == 0 ?  10 : temp;
            remainder = temp * 2 % 11;
        }
		Integer rst =  ( 11 - remainder ) % 10;
		return rst.toString();
	}
}
