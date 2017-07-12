package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import utils.Pair;

import beans.FestivalEventBean;
import beans.GenericListBean;
import beans.TicketPriceBean;
import beans.TicketTypeBean;


public class PurchaseTicketDAO extends DAO {
	
	private Connection connection;
	
	
	public PurchaseTicketDAO() throws ClassNotFoundException, SQLException {
		this.connection = getConnection();
	}
	
	
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
	
	
	public void getTicketTypes(GenericListBean<Pair<TicketTypeBean, TicketPriceBean>> ticketTypeListBean) throws SQLException {
		String query = "select tt.ticket_type_id, tt.type, tp.ticket_price_id, tp.price " +
				"from ticket_type tt, ticket_price tp " +
				"where tt.ticket_type_id = tp.ticket_type_id " +
				"and tp.valid_from <= current_date " +
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
	
	
	public void getSexDropdownContent(GenericListBean<String> sexListBean) throws SQLException {
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
	
	public void getPaymentMethodDropdownContent(GenericListBean<String> paymentMethodListBean) throws SQLException {
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
	
		
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
