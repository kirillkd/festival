package dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import beans.VisitorAccountBean;
import beans.BandBean;
import beans.TimetableEntryBean;
import beans.ProviderBean;

public class CheckUsernameDAO extends DAO {
	
	public CheckUsernameDAO() throws ClassNotFoundException, SQLException {
		getConnection();
	}

	public void getCheckUsername(VisitorAccountBean visitor_account,
			ArrayList<BandBean> bands) throws RuntimeException, SQLException,
			ClassNotFoundException {

		String query1 = "SELECT * FROM visitor_account WHERE username = ?;";
		String query2 = "SELECT p.name FROM band b, timetable_entry te, provider p WHERE username = ? AND b.band_id = te.band_id AND p.provider_id = b.provider_id ORDER BY te.preference DESC;";

		
		PreparedStatement pstmt1 = connection.prepareStatement(query1);
		PreparedStatement pstmt2 = connection.prepareStatement(query2);

		pstmt1.setString(1, visitor_account.getUsername());
		pstmt2.setString(1, visitor_account.getUsername());

		ResultSet rs1 = pstmt1.executeQuery();
		ResultSet rs2 = pstmt2.executeQuery();

		if (rs1.next() == false) {
			throw new RuntimeException("Username "
					+ visitor_account.getUsername() + " does not exist!");
		} else {
			while (rs2.next()) {
				BandBean bandbean = new BandBean();
				bandbean.setName(rs2.getString("name"));
				bands.add(bandbean);
			}
			if (bands.size() == 0) {
				throw new RuntimeException("There is no band playing!");
			}

			rs1.close();
			rs2.close();
			pstmt1.close();
			pstmt2.close();
			connection.close();

		}
	}
}