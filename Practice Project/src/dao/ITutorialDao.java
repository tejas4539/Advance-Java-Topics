package dao;

import java.sql.SQLException;
import java.util.List;

import pojo.Tutorial;

public interface ITutorialDao {
      
	List<String> getTutorialsByTopicId(int topic_id)throws SQLException; 
	Tutorial getTutorialsDetailsByName(String name) throws SQLException;
	String updateVisits(int tutorialId) throws SQLException;
}
