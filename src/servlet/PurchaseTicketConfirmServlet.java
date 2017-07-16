package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DateConverter;

import beans.FestivalEventBean;
import beans.TicketBean;
import beans.TicketTypeBean;
import beans.VisitorBean;
import dao.PurchaseTicketDAO;


@WebServlet("/purchase-ticket-confirm")
public class PurchaseTicketConfirmServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		FestivalEventBean festivalEventBean = new FestivalEventBean();
		festivalEventBean.setFestival_event_id(Integer.parseInt(req.getParameter("inputFestival_Event")));
		
		TicketTypeBean ticketTypeBean = new TicketTypeBean();
		ticketTypeBean.setTicket_type_id(Integer.parseInt(req.getParameter("inputTicket_Type")));
		
		VisitorBean visitorBean = new VisitorBean();
		visitorBean.setFirst_name(req.getParameter("inputFirst_Name"));
		visitorBean.setLast_name(req.getParameter("inputLast_Name"));
		visitorBean.setEmail(req.getParameter("inputEmail"));
		visitorBean.setPhone(req.getParameter("inputPhone"));
		visitorBean.setSex(req.getParameter("inputSex"));
		visitorBean.setAdddress(req.getParameter("inputAddress"));
		visitorBean.setCountry(req.getParameter("inputCountry"));
		
		TicketBean ticketBean = new TicketBean();
		ticketBean.setPayment_method(req.getParameter("inputPayment_Method"));
				
		try {
			PurchaseTicketDAO purchaseTicketDao = new PurchaseTicketDAO();
						
			visitorBean.setBirthdate(Date.valueOf(req.getParameter("inputBirthdate")));
			
			purchaseTicketDao.createDatabaseEntries(festivalEventBean, ticketTypeBean, visitorBean, ticketBean);
			
			purchaseTicketDao.closeConnection();
			
			req.setAttribute("success", "A new ticket was successfully purchased!");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}	
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);			
	}
}
