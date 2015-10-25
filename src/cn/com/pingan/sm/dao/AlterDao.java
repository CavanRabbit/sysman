package cn.com.pingan.sm.dao;

import java.sql.Date;
import java.util.List;
import cn.com.pingan.sm.pojo.AlterInfo;

public interface AlterDao {

	

    public AlterInfo get(String id,boolean hasNext);
	public String insert(AlterInfo alter,boolean hasNext);
	public String update(AlterInfo alter,boolean hasNext) ;
	public String delete(String id,boolean hasNext) ;
	public List<AlterInfo> findByDate(Date date,boolean hasNext);
	public List<AlterInfo> findByDate(Date sdate,Date edate,boolean hasNext);
}
