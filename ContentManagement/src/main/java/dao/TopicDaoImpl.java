package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.opeConnection;

import pojo.Topic;

public class TopicDaoImpl implements ITopicDao {
	Connection con;
	PreparedStatement pst1;

	public TopicDaoImpl()throws SQLException {
		con=opeConnection();
		pst1=con.prepareStatement("select * from topics");
		System.out.println("Topic dao created");
	}
	@Override
	public List<Topic> getAllTopics()  {
		List<Topic> topics=new ArrayList<Topic>();
		try(ResultSet rs=pst1.executeQuery()){
			while(rs.next()) {
				topics.add(new Topic(rs.getInt(1),rs.getString(2)));
				
			}
		return topics;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public void cleanUp()throws SQLException{
		if(pst1!=null) {
			pst1.close();
		}
		System.out.println("topic dao cleared");
	}

}
