package dao;
import pojo.Topic;

import java.sql.SQLException;
import java.util.List;

public interface ITopicDao {

	
	List<Topic> getAllAvailable()throws SQLException;
}
