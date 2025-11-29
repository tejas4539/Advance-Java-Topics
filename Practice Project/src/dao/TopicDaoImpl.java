package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.openConnection;

import pojo.Topic;

public class TopicDaoImpl implements ITopicDao {
    Connection con;
    PreparedStatement pst1;
    
	public TopicDaoImpl()throws SQLException {
		 con=openConnection();
		 pst1=con.prepareStatement("select * from topics");
	}

	@Override
	public List<Topic> getAllAvailable() throws SQLException {
        List<Topic> tp=new ArrayList<>();
        try(ResultSet rs=pst1.executeQuery()){
        	   while(rs.next()) {
        		   tp.add(new Topic(rs.getInt(1), rs.getString(2)));
        	   }
        	   return tp;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
		return null;
	}
	public void cleanUp()throws SQLException {
		if(pst1!=null) {
			pst1.close();
		}
		if(con!=null) {
			con.close();
		}
		System.out.println("User Dao Cleaned Up....");
	}

}
