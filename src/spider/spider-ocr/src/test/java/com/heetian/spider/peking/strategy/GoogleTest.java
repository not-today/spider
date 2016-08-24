package com.heetian.spider.peking.strategy;

import java.io.File;

import com.heetian.spider.ocr.util.ImageUtils;

public class GoogleTest {

	public static void main(String[] args) {
		test();
		//ImageUtils.removeStencil("C:\\Users\\tst\\Desktop\\03");
	}
	private static void test() {
		File file = new File("C:\\Users\\tst\\Desktop\\03");
		for(File pic:file.listFiles()){
			if(!pic.isFile())
				continue;
			try {
				String msg = pic.getName();
				if(msg.contains("_")){
					msg = msg.split("_")[0];
				}else{
					msg = msg.split("\\.")[0];
				}
				msg = msg.replaceAll("\\s", "");
				renameFile(file.getAbsolutePath(), pic.getName(), msg+"_1"+".png");
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	 public static void renameFile(String path,String oldname,String newname){ 
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
            File oldfile=new File(path+"/"+oldname); 
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            File newfile=new File(path+"/"+newname); 
            int x = 2;
            while(newfile.exists()){//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
            	newfile=new File(path+"/"+newname.split("_")[0]+"_"+x+".png");
            	x++;
            }
            oldfile.renameTo(newfile);
           /* if(newfile.exists())
                System.out.println(newname+"已经存在！"); 
            else{ 
                oldfile.renameTo(newfile); 
            } */
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
	}
}
