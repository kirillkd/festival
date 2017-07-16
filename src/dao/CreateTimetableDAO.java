package dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import beans.VisitorAccountBean;
import beans.BandBean;
import beans.TimetableEntryBean;
import beans.ProviderBean;

public class CreateTimetableDAO extends DAO {
	
	public CreateTimetableDAO() throws ClassNotFoundException, SQLException {
		getConnection();
	}

	public void getPreferences(VisitorAccountBean visitor_account, ArrayList<TimetableEntryBean> timetable_entry,
			ArrayList<BandBean> bands) throws RuntimeException, SQLException,
			ClassNotFoundException {

		String query1 = "UPDATE timetable_entry AS te SET preference = ? FROM band b, provider p WHERE te.username = ? AND p.name = ? AND b.band_id = te.band_id AND p.provider_id = b.provider_id;";
		
		PreparedStatement pstmt1 = connection.prepareStatement(query1);
		
		pstmt1.setString(2, visitor_account.getUsername());
		
		
		for (int i = 0; i < bands.size(); i++){
		
			pstmt1.setInt(1, timetable_entry.get(i).getPreference());
			pstmt1.setString(3, bands.get(i).getName());
			

			pstmt1.executeUpdate();
			
		}
		
		connection.commit();
					
		pstmt1.close();
		connection.close();

		
	}
	
	public void getCreateTimetable(VisitorAccountBean visitor_account, ArrayList<TimetableEntryBean> timetable_entry,
			ArrayList<BandBean> bands) throws RuntimeException, SQLException,
			ClassNotFoundException {

		String query2 = "SELECT b.timeslot_date, b.timeslot_start, b.timeslot_end, p.name FROM band b, provider p, timetable_entry te WHERE b.provider_id = p.provider_id  AND te.username=? AND te.band_id = b.band_id;";

		
		PreparedStatement pstmt2 = connection.prepareStatement(query2);

		pstmt2.setString(1, visitor_account.getUsername());
		
		ResultSet rs2 = pstmt2.executeQuery();
		
		while (rs2.next()) {
				BandBean bandbean = new BandBean();
				bandbean.setTimeslot_date(rs2.getDate("timeslot_date"));
				bandbean.setTimeslot_start(rs2.getDate("timeslot_start"));
				bandbean.setTimeslot_end(rs2.getDate("timeslot_end"));
				bandbean.setName(rs2.getString("name"));
				bands.add(bandbean);
		}
		
		
		rs2.close();
		pstmt2.close();
		connection.close();

		
	}
	
}
