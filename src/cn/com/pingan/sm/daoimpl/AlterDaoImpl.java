package cn.com.pingan.sm.daoimpl;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import cn.com.pingan.sm.dao.AlterDao;
import cn.com.pingan.sm.pojo.AlterInfo;
import cn.com.pingan.sm.util.HibernateSessionFactory;


public class AlterDaoImpl implements AlterDao{

	private  Session session = null;
	public AlterDaoImpl(){
		
		session = getSession();
	}
	private Session getSession(){
		/*Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		Session session = cfg.buildSessionFactory(serviceRegistry).openSession();*/
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		return session;
	}


	@Override
	public AlterInfo get(String id,boolean hasNext) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM ALTER_INFO WHERE ID=?";
		try{
			SQLQuery query = session.createSQLQuery(sql);
			query.setString(0, id);
			@SuppressWarnings("unchecked")
			List<AlterInfo> alterList = (List<AlterInfo>)query.addEntity(AlterInfo.class).list();
			if(alterList.size()==0){
				return null;
			}else{
				return alterList.get(0);
			}
		
		}catch(Exception e){
			if(session==null)
				System.out.println("获取session失败");
			System.out.println("查询失败");
			return null;
			//只是一个中间过程，不用关闭session
		}finally{
			if( ! hasNext ){
				session.close();
			}
		}
		
	}

	@Override
	public String insert(AlterInfo alter,boolean hasNext) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try{
			
			if( get( alter.getId(),true)==null){
				tx  = session.beginTransaction();
				session.save(alter);
				tx.commit();
				return "success";
			}else{
				return "The primary key is already exist";
			}
		}catch(Exception e){
			if (session==null){
				System.out.println("获取session失败");
			}
			System.out.println("事务失败");
			tx.rollback();
			return "transaction failed";
		}finally{
			if( ! hasNext ){
				session.close();
			}
		}

	}

	@Override
	public String update(AlterInfo alter,boolean hasNext) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try{
			AlterInfo newAlter = get(alter.getId(),true);
			if(  newAlter != null ){
				newAlter.setExecute(alter.getExecute());
				newAlter.setExecuter(alter.getExecuter());
				tx  = session.beginTransaction();
				session.update(newAlter);
				tx.commit();
				return "success";
			}else{
				return "The primary key is not exist";
			}
		}catch(Exception e){
			if (session==null){
				System.out.println("获取session失败");
			}
			System.out.println("事务失败");
			tx.rollback();
			return "transaction failed";
		}finally{
			if( ! hasNext ){
				session.close();
			}
		}
	}

	@Override
	public String delete(String id,boolean hasNext) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try{
			AlterInfo alter = get(id,true);
			if( alter!=null ){
				tx  = session.beginTransaction();
				session.delete(alter);
				tx.commit();
				return "success";
			}else{
				return "The primary key is not exist";
			}

		}catch(Exception e){
			if (session==null){
				System.out.println("获取session失败");
			}
			System.out.println("事务失败");
			tx.rollback();
			return "transaction failed";
		}finally{
			if( ! hasNext ){
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlterInfo> findByDate(Date date,boolean hasNext) {
		// TODO Auto-generated method stub
		List<AlterInfo> alterList= new ArrayList<AlterInfo>();
		String sql = "SELECT * FROM ALTERINFO WHERE ALTERDATE=? ";
		try{
			SQLQuery query = session.createSQLQuery(sql);
			query.setDate(0, date);
			alterList = query.addEntity(AlterInfo.class).list();
			return alterList;
		}catch(Exception e){
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
	public List<AlterInfo> findByDate(Date sdate, Date edate,boolean hasNext) {
		// TODO Auto-generated method stub
		List<AlterInfo> alterList = new ArrayList<AlterInfo>();
		String sql = " SELECT * FROM ALTERINFO WHERE ALTERDATE>=? AND ALTERDATE<=? ";
		try{
			SQLQuery query = session.createSQLQuery(sql);
			query.setDate(0, sdate);
			query.setDate(1, edate);
			alterList = query.addEntity(AlterInfo.class).list();
			return alterList;
		}catch(Exception e){
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

	
}


