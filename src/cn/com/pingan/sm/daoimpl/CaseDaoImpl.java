package cn.com.pingan.sm.daoimpl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.com.pingan.sm.dao.CaseDao;

import cn.com.pingan.sm.pojo.CaseInfo;
import cn.com.pingan.sm.util.HibernateSessionFactory;

public class CaseDaoImpl implements CaseDao {

	private  Session session = null;
	public CaseDaoImpl(){
		
		session = getSession();
	}
	private Session getSession(){
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		return session;
	}
	
	@Override
	public List<CaseInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(CaseInfo ca,boolean hasNext) {
		// TODO Auto-generated method stub

		Transaction tx = null;
		try {
			
			if( get(ca.getId(),true)==null){
				tx  = session.beginTransaction();
				session.save(ca);
				tx.commit();
				return "success";
			}else{
				return "The primary key is already exist";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (session==null){
				System.out.println("获取session失败");
			}
			System.out.println("事务失败");
			tx.rollback();
			return "transaction failed";
		}finally{
			if(! hasNext)
				session.close();
		}
		

	}
	@Override
	public String insert(List<CaseInfo> caseList,boolean hasNext) {
		// TODO Auto-generated method stub

		Transaction tx = null;
		try {
			tx  = session.beginTransaction();
			for(CaseInfo ca : caseList){
				if( get(ca.getId(),true)==null){
					
					session.save(ca);
					
					
				}else{
					return "The primary key is already exist"+"----"+ca.getId();
				}
			}
			tx.commit();
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (session==null){
				System.out.println("获取session失败");
			}
			System.out.println("事务失败");
			tx.rollback();
			return "transaction failed";
		}finally{
			if(! hasNext)
				session.close();
		}
		
	}
	
	
	@Override
	public String update(CaseInfo ca,boolean hasNext) {
		// TODO Auto-generated method stub
	   return null;
	}

	@Override
	public String delete(CaseInfo ca,boolean hasNext) {
		// TODO Auto-generated method stub

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseInfo> findByDate(String date,boolean hasNext) {
		// TODO Auto-generated method stub

		List<CaseInfo> caseList = new ArrayList<CaseInfo>();

		//日期格式化函数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		if(date==null){
			
			//date = sdf.format(new java.util.Date());
			//日历对象
			Calendar cal = Calendar.getInstance();
			cal.setTime(new java.util.Date());
			//前一天的日期
			cal.add(Calendar.DAY_OF_MONTH, -1);
			//将java.util.Date转换成java.sql.Date
			date = sdf.format(cal.getTime());

		}
		//oracle
		String sql = "SELECT * FROM CASE_INFO WHERE TRUNC(STARTTIME)=DATE "+"'"+date+"'";
        //mysql
		//String sql = "SELECT * FROM CASE_INFO WHERE DATE_FORMAT(STARTTIME, '%Y-%m-%d')=DATE(?)";
			try {
				SQLQuery query = session.createSQLQuery(sql);
				query.setString(0, date);
				caseList = (List<CaseInfo>) query.addEntity(CaseInfo.class).list();
				return caseList;
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if (session==null){
					System.out.println("获取session失败");
				}
				System.out.println("查询失败");
				return null;
			}finally{
				if( ! hasNext ){
					session.close();
				}
					
			}
			

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseInfo> findByDate(String sdate, String edate,boolean hasNext) {
		// TODO Auto-generated method stub
		List<CaseInfo> caseList = new ArrayList<CaseInfo>();
	
		//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		//oracle
		String sql = "SELECT * FROM CASE_INFO WHERE TRUNC(STARTTIME)>=DATE "+"'"+sdate+"'"+
			"AND TRUNC(STARTTIME)<=DATE "+"'"+edate+"'";
		
		//mysql
		//String sql = "SELECT * FROM CASE_INFO WHERE DATE_FORMAT(STARTTIME, '%Y-%m-%d')>=DATE( ?) AND DATE_FORMAT(STARTTIME), '%Y-%m-%d')<=DATE(?)";

		try {
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, sdate);
			query.setString(1, edate);
			caseList = (List<CaseInfo>) query.addEntity(CaseInfo.class).list();
			return caseList;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (session==null){
				System.out.println("获取session失败");
			}
			System.out.println("查询失败");
			return null;
		}finally{
			if( ! hasNext ){
				session.close();
			}
		}
	}



	@Override
	public CaseInfo get(String id,boolean hasNext) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM CASE_INFO WHERE ID=:id";
		try {
			SQLQuery query = session.createSQLQuery(sql);
			query.setString("id", id);
			@SuppressWarnings("unchecked")
			List<CaseInfo> caseList = (List<CaseInfo>) query.addEntity(CaseInfo.class).list();
			if(caseList.size()==0){
				return null;
			}else{
				return caseList.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(session==null)
				System.out.println("获取session失败");
			System.out.println("查询失败");
			return null;
		}finally{
			if( ! hasNext ){
				session.close();
			}
		}
		
	}
	
}
