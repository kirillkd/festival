package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.LinkedList;

import utils.Pair;

import beans.FestivalEventBean;
import beans.GenericListBean;
import beans.TicketBean;
import beans.TicketPriceBean;
import beans.TicketTypeBean;
import beans.VisitorBean;


public class PurchaseTicketDAO extends DAO {
	
	/**
	 * Lists id and name of all festival events present in the database.
	 * 
	 * @param festivalEventListBean the empty list to be filled
	 * @throws SQLException
	 */
	public void getFestivalEvents(GenericListBean<FestivalEventBean> festivalEventListBean) throws SQLException {
		String query = "select festival_event_id, name from festival_event;";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(query);
		
		festivalEventListBean.setItems(new LinkedList<FestivalEventBean>());
		
		while (resultSet.next()) {
			FestivalEventBean festivalEventBean = new FestivalEventBean();
			
			festivalEventBean.setFestival_event_id(resultSet.getInt("festival_event_id"));
			festivalEventBean.setName(resultSet.getString("name"));
			
			festivalEventListBean.getItems().add(festivalEventBean);
		}
		
		resultSet.close();
		statement.close();
	}
	
	/**
	 * Lists all available Pairs of ticket type and ticket price. The price
	 * for a ticket is calculated based on the current date.
	 * 
	 * @param ticketTypeListBean the empty list to be filled
	 * @throws SQLException
	 */
	public void getTicketTypes(GenericListBean<Pair<TicketTypeBean, TicketPriceBean>> ticketTypeListBean) throws SQLException {
		String query = "select tt.ticket_type_id, tt.type, tp.ticket_price_id, tp.price " +
				"from ticket_type tt, ticket_price tp " +
				"where tt.ticket_type_id = tp.ticket_type_id " +
				"and (tp.valid_to >= current_date or valid_to is null);";
				
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(query);
		
		ticketTypeListBean.setItems(new LinkedList<Pair<TicketTypeBean, TicketPriceBean>>());
		
		while (resultSet.next()) {
			TicketTypeBean ticketTypeBean = new TicketTypeBean();
			
			ticketTypeBean.setTicket_type_id(resultSet.getInt("ticket_type_id"));
			ticketTypeBean.setType(resultSet.getString("type"));
			
			TicketPriceBean ticketPriceBean = new TicketPriceBean();
			
			ticketPriceBean.setTicket_price_id(resultSet.getInt("ticket_price_id"));
			ticketPriceBean.setPrice(resultSet.getDouble("price"));
			
			ticketTypeListBean.getItems().add(new Pair<TicketTypeBean, TicketPriceBean>(ticketTypeBean, ticketPriceBean));
		}
		
		resultSet.close();
		statement.close();
	}
	
	/**
	 * Lists all currently in the database available sexes a visitor can have.
	 * 
	 * @param sexListBean the empty list to be filled
	 * @throws SQLException
	 */
	public void getVisitorSexDropdownContent(GenericListBean<String> sexListBean) throws SQLException {
		String query = "select unnest(enum_range(NULL::visitor_sex));";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(query);
		
		sexListBean.setItems(new LinkedList<String>());
		
		while (resultSet.next()) {
			sexListBean.getItems().add(resultSet.getString(1));
		}
		
		resultSet.close();
		statement.close();
	}
	
	/**
	 * Lists all currently available payment methods for a ticket.
	 * 
	 * @param paymentMethodListBean
	 * @throws SQLException
	 */
	public void getTicketPaymentMethodDropdownContent(GenericListBean<String> paymentMethodListBean) throws SQLException {
		String query = "select unnest(enum_range(NULL::ticket_payment_method));";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(query);
		
		paymentMethodListBean.setItems(new LinkedList<String>());
		
		while (resultSet.next()) {
			paymentMethodListBean.getItems().add(resultSet.getString(1));
		}
		
		resultSet.close();
		statement.close();
	}

	/**
	 * Validates the data entered by the user for completeness and correctness.
	 * It also prevents entering a space to avoid entering the data.
	 * 
	 * @param visitorBean the data for the visitor
	 * @param ticketBean the data for the ticket
	 */
	public void validateInput(VisitorBean visitorBean, TicketBean ticketBean) {
		if (visitorBean.getFirst_name() == null || visitorBean.getFirst_name().trim().equals("")) {
			throw new IllegalArgumentException("Please enter your first name!");
		}
		if (visitorBean.getLast_name() == null || visitorBean.getLast_name().trim().equals("")) {
			throw new IllegalArgumentException("Please enter your last name!");
		}
		if (visitorBean.getEmail() == null || visitorBean.getEmail().trim().equals("")) {
			throw new IllegalArgumentException("Please enter your E-Mail Address!");
		}
		if (!visitorBean.getEmail().contains("@")) {
			throw new IllegalArgumentException("Please enter a valid E-Mail Address!");
		}
		if (visitorBean.getAddress() == null || visitorBean.getAddress().trim().equals("")) {
			throw new IllegalArgumentException("Please enter your address!");
		}
		if (visitorBean.getCountry() == null || visitorBean.getCountry().trim().equals("")) {
			throw new IllegalArgumentException("Please enter your country!");
		}
		if (ticketBean.getPayment_method() == null || ticketBean.getPayment_method().trim().equals("")) {
			throw new IllegalArgumentException("Please select a valid payment method!");
		}
	}	

	/**
	 * Get the name of the festival for a given festival_event_id.
	 * 
	 * @param festivalEventBean the bean with a festival_event_id
	 * @throws SQLException
	 */
	public void getFestivalEventInformation(FestivalEventBean festivalEventBean) throws SQLException {
		String query = "select name from festival_event where festival_event_id = ?;";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, festivalEventBean.getFestival_event_id());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		
		festivalEventBean.setName(resultSet.getString("name"));
		
		resultSet.close();
		preparedStatement.close();
	}

	/**
	 * Get the current price and the type name for a given ticket_type_id.
	 * 
	 * @param ticketPriceBean the ticketPriceBean to be filled with the price
	 * @param ticketTypeBean the ticketTypeBean containing the ticket_type_id and to be filed with the type name
	 * @throws SQLException
	 */
	public void getTicketPriceInformation(TicketPriceBean ticketPriceBean,
			TicketTypeBean ticketTypeBean) throws SQLException {
		
		String query = "select tp.price, tt.type " +
				"from ticket_type tt, ticket_price tp " +
				"where tt.ticket_type_id = ? " +
				"and tt.ticket_type_id = tp.ticket_type_id " +
				"and (tp.valid_to is null or tp.valid_to >= current_date);";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, ticketTypeBean.getTicket_type_id());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		
		ticketPriceBean.setPrice(resultSet.getDouble("price"));
		ticketTypeBean.setType(resultSet.getString("type"));
		
		resultSet.close();
		preparedStatement.close();
	}
	
	/**
	 * Writes all the data for buying a ticket to the database.
	 * 
	 * @param festivalEventBean bean containing the information about the festival event
	 * @param ticketTypeBean bean containing the selected ticket type
	 * @param visitorBean bean containing the visitor's data
	 * @param ticketBean bean containing the ticket data
	 * @throws SQLException
	 */
	public void createDatabaseEntries(FestivalEventBean festivalEventBean,
			TicketTypeBean ticketTypeBean, VisitorBean visitorBean,
			TicketBean ticketBean) throws SQLException {
		
		// Insert into table visitor
		String insertVisitor = "insert into visitor " +
				"(email, last_name, first_name, address, country, birthdate, phone, sex) values " +
				"(?, ?, ?, ?, ?, ?, ?, ?::visitor_sex);";
		
		PreparedStatement preparedStatement = connection.prepareStatement(insertVisitor, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setString(1, visitorBean.getEmail());
		preparedStatement.setString(2, visitorBean.getLast_name());
		preparedStatement.setString(3, visitorBean.getFirst_name());
		preparedStatement.setString(4, visitorBean.getAddress());
		preparedStatement.setString(5, visitorBean.getCountry());
		preparedStatement.setDate(6, visitorBean.getBirthdate());
		preparedStatement.setString(7, visitorBean.getPhone());
		preparedStatement.setString(8, visitorBean.getSex());
		
		preparedStatement.executeUpdate();
		
		ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		
		generatedKeys.next();
		
		// The visitor_id is generated by the database and returned, 
		// we need it for assigning the ticket to the visitor
		visitorBean.setVisitor_id(generatedKeys.getInt(1));
		
		generatedKeys.close();
		preparedStatement.close();
		
		// Insert into table ticket
		String insertTicket = "insert into ticket " +
				"(booking_date, payment_method, ticket_type_id, visitor_id, festival_event_id) values " +
				"(current_date, ?::ticket_payment_method, ?, ?, ?)";
		
		preparedStatement = connection.prepareStatement(insertTicket);
		
		preparedStatement.setString(1, ticketBean.getPayment_method());
		preparedStatement.setInt(2, ticketTypeBean.getTicket_type_id());
		preparedStatement.setInt(3, visitorBean.getVisitor_id());
		preparedStatement.setInt(4, festivalEventBean.getFestival_event_id());
		
		preparedStatement.executeUpdate();
		
		connection.commit();
		
		preparedStatement.close();	
	}
}
