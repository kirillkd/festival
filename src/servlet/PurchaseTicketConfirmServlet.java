package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		visitorBean.setFirst_name(req.getParameter("inputFirst_Name").trim());
		visitorBean.setLast_name(req.getParameter("inputLast_Name").trim());
		visitorBean.setEmail(req.getParameter("inputEmail").trim());
		visitorBean.setPhone(req.getParameter("inputPhone").trim());
		visitorBean.setSex(req.getParameter("inputSex").trim());
		visitorBean.setAdddress(req.getParameter("inputAddress").trim());
		visitorBean.setCountry(req.getParameter("inputCountry").trim());
		
		TicketBean ticketBean = new TicketBean();
		ticketBean.setPayment_method(req.getParameter("inputPayment_Method"));
		
		PurchaseTicketDAO purchaseTicketDao = new PurchaseTicketDAO();
		
		try {
			purchaseTicketDao.getConnection();
			
			visitorBean.setBirthdate(Date.valueOf(req.getParameter("inputBirthdate")));
			
			purchaseTicketDao.createDatabaseEntries(festivalEventBean, ticketTypeBean, visitorBean, ticketBean);
			
			req.setAttribute("success", "A new ticket was successfully purchased!");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		} finally {
			try {
				purchaseTicketDao.closeConnection();
			} catch (SQLException e) {
				req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
		}
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);			
	}
}
