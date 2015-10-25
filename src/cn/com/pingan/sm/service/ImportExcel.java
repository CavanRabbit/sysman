package cn.com.pingan.sm.service;


import java.io.IOException;
import java.io.InputStream;

import java.sql.Timestamp;

import java.util.ArrayList;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.pingan.sm.pojo.CaseInfo;

public class ImportExcel {
	

	@SuppressWarnings("resource")
	public String insertFromExcel(InputStream is,String filename){
		
		 List<CaseInfo> lec = new ArrayList<CaseInfo>();  //每行的内容保存在列表中
		 
		if(filename.endsWith("xlsx")){
			
			 XSSFWorkbook wb = null;
			 XSSFSheet sheet = null;
			  try {
					wb = new XSSFWorkbook(is);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("打开表格失败！");
					return "failed";
				}
				 sheet = wb.getSheetAt(0);
				 int rowNum = sheet.getLastRowNum();
				 XSSFRow row = null;
				 XSSFCell  cell = null;
				 CaseInfo 	ec = null;
		
				 for(int i=1;i<=rowNum;i++){
				     ec = new CaseInfo();
					 row = sheet.getRow(i);
					 cell = row.getCell(0);
					 ec.setRelevance(cell.toString());
					// System.out.println(cell.toString());
					 cell = row.getCell(1);
					 ec.setDescription(cell.toString());
					// System.out.println(cell.toString());
					 cell = row.getCell(2);
					 ec.setStarttime(Timestamp.valueOf(cell.toString()));
					// System.out.println(cell.toString());				 
					 cell = row.getCell(3);
					 ec.setEndtime(Timestamp.valueOf(cell.toString()));
					// System.out.println(cell.toString());				 
					 cell = row.getCell(4);
					 ec.setMins(Float.parseFloat(cell.toString()));
					// System.out.println(cell.toString());				 
					 cell = row.getCell(5);
					 ec.setScope(cell.toString());
					 
					// System.out.println(cell.toString());				 
					 cell = row.getCell(6);
					 ec.setSolution(cell.toString());

					// System.out.println(cell.toString());
					 cell = row.getCell(7);
					 ec.setReason(cell.toString());
					
					 //System.out.println(cell.toString());
					 cell = row.getCell(8);
					 ec.setRemark(cell.toString());
					 //System.out.println(cell.toString());
					 
					 lec.add(ec);
				 }
		}else{
			 POIFSFileSystem pfs = null;
			 HSSFWorkbook wb = null;
			 HSSFSheet sheet = null;
			 try {
			  pfs = new POIFSFileSystem(is);
			  wb = new HSSFWorkbook(pfs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("打开表格失败！");
				return "failed";
			}
			 sheet = wb.getSheetAt(0);
			 int rowNum = sheet.getLastRowNum();
			 HSSFRow row = null;
			 HSSFCell  cell = null;
			 CaseInfo	ec = null;
			 
			 for(int i=1;i<=rowNum;i++){
				  ec = new CaseInfo();
					 row = sheet.getRow(i);
					 cell = row.getCell(0);
					 ec.setRelevance(cell.toString());
					// System.out.println(cell.toString());
					 cell = row.getCell(1);
					 ec.setDescription(cell.toString());
					// System.out.println(cell.toString());
					 cell = row.getCell(2);
					 ec.setStarttime(Timestamp.valueOf(cell.toString()));
					// System.out.println(cell.toString());				 
					 cell = row.getCell(3);
					 ec.setEndtime(Timestamp.valueOf(cell.toString()));
					// System.out.println(cell.toString());				 
					 cell = row.getCell(4);
					 ec.setMins(Float.parseFloat(cell.toString()));
					// System.out.println(cell.toString());				 
					 cell = row.getCell(5);
					 ec.setSolution(cell.toString());
					// System.out.println(cell.toString());				 
					 cell = row.getCell(6);
					 ec.setReason(cell.toString());
					// System.out.println(cell.toString());
					 cell = row.getCell(7);
					 ec.setRemark(cell.toString());
					 //System.out.println(cell.toString());
					 cell = row.getCell(8);
					 ec.setScope(cell.toString());
					 //System.out.println(cell.toString());
					 
					 lec.add(ec);
				 
			 }
		}
		return CaseAdder.insertCase(lec);
	}

	
	
}
