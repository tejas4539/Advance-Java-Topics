package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.openConnection;

import pojo.Tutorial;

public class TutorialDaoImpl implements ITutorialDao {
	Connection con;
	PreparedStatement pst1, pst2, pst3;

	public TutorialDaoImpl() throws SQLException {
		con = openConnection();
		pst1 = con.prepareStatement("select name from tutorials where topic_id=? order by visits desc");
		pst2 = con.prepareStatement("select * from tutorials where name=?");
		pst3=con.prepareStatement("update tutorials set visits=visits+1 where id=?");
		System.out.println("tutorials doa created...");
	
	}

	@Override
	public List<String> getTutorialsByTopicId(int topic_id) throws SQLException {
		List<String> tutorial = new ArrayList<>();
		pst1.setInt(1, topic_id);
		try (ResultSet rs = pst1.executeQuery()) {
			while (rs.next()) {
				tutorial.add(rs.getString(1));
			}
			return tutorial;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tutorial getTutorialsDetailsByName(String name) throws SQLException {
		pst2.setString(1, name);
		try (ResultSet rs = pst2.executeQuery()) {
			if (rs.next()) {
				return new Tutorial(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7));
			}

		}
		return null;
	}

	@Override
	public String updateVisits(int tutorialId) throws SQLException {
		pst3.setInt(1, tutorialId);
		int res=pst3.executeUpdate();
		return res==1 ? "Visits updated ":"visits update failed";
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}
		if (pst2 != null) {
			pst2.close();
		}
		if (con != null) {
			con.close();
		}
		System.out.println("User Dao Cleaned Up....");
	}

}
