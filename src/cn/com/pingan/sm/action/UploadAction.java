package cn.com.pingan.sm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.com.pingan.sm.service.ImportExcel;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File caseExcel;
	private String caseExcelFileName;
    private String caseExcelFileContentType;
    private JSONObject result = new JSONObject();

	public File getCaseExcel() {
		return caseExcel;
	}


	public void setCaseExcel(File caseExcel) {
		this.caseExcel = caseExcel;
	}


	public String getCaseExcelFileName() {
		return caseExcelFileName;
	}


	public void setCaseExcelFileName(String caseExcelFileName) {
		this.caseExcelFileName = caseExcelFileName;
	}


	public String getCaseExcelFileContentType() {
		return caseExcelFileContentType;
	}


	public void setCaseExcelFileContentType(String caseExcelFileContentType) {
		this.caseExcelFileContentType = caseExcelFileContentType;
	}

	
	@SuppressWarnings("resource")
	public String upload(){
		
		ImportExcel ie = new ImportExcel();
		FileInputStream fis = null;

		try {
			 fis = new FileInputStream(caseExcel);
			 //ie.readExcel(fis);
			String status;

			status = ie.insertFromExcel(fis, caseExcelFileName);
			if(status=="success"){
				result.put("status", status);
				return "success";
			}else{
				result.put("status", "failed");
				return "success";
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		}
	}


	public JSONObject getResult() {
		return result;
	}


	public void setResult(JSONObject result) {
		this.result = result;
	}



}
