package dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.VisitorAccountBean;
import beans.BandBean;

public class CheckUsernameDAO extends DAO {

	/*
	 * 
	 */
	public void getCheckUsername(VisitorAccountBean visitor_account,
			ArrayList<BandBean> bands) throws RuntimeException, SQLException,
			ClassNotFoundException {

		// List all usernames of the visitor_accounts
		String query1 = "SELECT * FROM visitor_account WHERE username = ?;";
		// List all bands which a specific visitor can hear during his festival visit
		String query2 = "SELECT p.name FROM band b, timetable_entry te, provider p WHERE username = ? AND b.band_id = te.band_id AND p.provider_id = b.provider_id ORDER BY te.preference DESC;";
		// List a result if username and password match
		String query3 = "SELECT * FROM visitor_account WHERE username = ? AND password = ?;";

		PreparedStatement pstmt1 = connection.prepareStatement(query1);
		PreparedStatement pstmt2 = connection.prepareStatement(query2);
		PreparedStatement pstmt3 = connection.prepareStatement(query3);

		pstmt1.setString(1, visitor_account.getUsername());
		pstmt2.setString(1, visitor_account.getUsername());
		pstmt3.setString(1, visitor_account.getUsername());
		pstmt3.setString(2, visitor_account.getPassword());

		ResultSet rs1 = pstmt1.executeQuery();
		ResultSet rs2 = pstmt2.executeQuery();
		ResultSet rs3 = pstmt3.executeQuery();

		// Check, if username is already created
		if (rs1.next() == false) {
			throw new RuntimeException("Invalid credentials!");
		} else if (rs3.next() == false) {
			// Check, if username and password match
			throw new RuntimeException("Invalid credentials!");
		} else {
			// List all bands the visitor can hear on his festival visit
			while (rs2.next()) {
				BandBean bandbean = new BandBean();
				bandbean.setName(rs2.getString("name"));
				bands.add(bandbean);
			}
			// Message, if no bands are playing
			if (bands.size() == 0) {
				throw new RuntimeException("There is no band playing!");
			}

			rs1.close();
			rs2.close();
			rs3.close();
			pstmt1.close();
			pstmt2.close();
			pstmt3.close();
		}
	}
}
