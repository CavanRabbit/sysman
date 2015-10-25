package cn.com.pingan.sm.dao;


import java.util.List;

import cn.com.pingan.sm.pojo.CaseInfo;

public interface CaseDao {

	public List<CaseInfo> findAll() ;
	;
		public String insert(CaseInfo ca, boolean hasNext);
		public String insert(List<CaseInfo> caseList,boolean hasNext);
		public String update(CaseInfo ca, boolean hasNext) ;
		public String delete(CaseInfo ca, boolean hasNext) ;
		public List<CaseInfo> findByDate(String date, boolean hasNext );
		public List<CaseInfo> findByDate(String sdate,String edate, boolean hasNext);
	    public CaseInfo get(String id, boolean hasNext);
}
