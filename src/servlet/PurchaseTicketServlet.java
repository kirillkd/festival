package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Pair;
import utils.DateConverter;

import dao.PurchaseTicketDAO;

import beans.FestivalEventBean;
import beans.GenericListBean;
import beans.TicketBean;
import beans.TicketPriceBean;
import beans.TicketTypeBean;
import beans.VisitorBean;


@WebServlet("/purchase-ticket")
public class PurchaseTicketServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		PurchaseTicketDAO purchaseTicketDao = new PurchaseTicketDAO();
		
		try {
			purchaseTicketDao.getConnection();
			
			// Get all festival events
			GenericListBean<FestivalEventBean> festivalEventListBean = new GenericListBean<FestivalEventBean>();
			purchaseTicketDao.getFestivalEvents(festivalEventListBean);
			req.setAttribute("festivalEventListBean", festivalEventListBean);
			
			// Get ticket types with current price
			GenericListBean<Pair<TicketTypeBean, TicketPriceBean>> ticketTypeListBean = new GenericListBean<>();
			purchaseTicketDao.getTicketTypes(ticketTypeListBean);
			req.setAttribute("ticketTypeListBean", ticketTypeListBean);
			
			// Get dropdown content for visitor's sex
			GenericListBean<String> sexListBean = new GenericListBean<>();
			purchaseTicketDao.getVisitorSexDropdownContent(sexListBean);
			req.setAttribute("sexListBean", sexListBean);
			
			// Get dropdown content for ticket payment method
			GenericListBean<String> paymentMethodListBean = new GenericListBean<>();
			purchaseTicketDao.getTicketPaymentMethodDropdownContent(paymentMethodListBean);
			req.setAttribute("paymentMethodListBean", paymentMethodListBean);
			
			req.setAttribute("action", "purchase");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		} finally {
			// Make sure to close the database connection
			try {
				purchaseTicketDao.closeConnection();
			} catch (SQLException e) {
				req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/purchaseTicket.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Create bean objects, fill them with request data
		// and pass them again to the request
		FestivalEventBean festivalEventBean = new FestivalEventBean();
		festivalEventBean.setFestival_event_id(Integer.parseInt(req.getParameter("inputFestival_Event")));
		req.setAttribute("festivalEventBean", festivalEventBean);
		
		TicketTypeBean ticketTypeBean = new TicketTypeBean();
		ticketTypeBean.setTicket_type_id(Integer.parseInt(req.getParameter("inputTicket_Type")));
		req.setAttribute("ticketTypeBean", ticketTypeBean);

		VisitorBean visitorBean = new VisitorBean();
		visitorBean.setFirst_name(req.getParameter("inputFirst_Name"));
		visitorBean.setLast_name(req.getParameter("inputLast_Name"));
		visitorBean.setEmail(req.getParameter("inputEmail"));
		visitorBean.setPhone(req.getParameter("inputPhone"));
		visitorBean.setSex(req.getParameter("inputSex"));
		visitorBean.setAdddress(req.getParameter("inputAddress"));
		visitorBean.setCountry(req.getParameter("inputCountry"));
		req.setAttribute("visitorBean", visitorBean);

		TicketBean ticketBean = new TicketBean();
		ticketBean.setPayment_method(req.getParameter("inputPayment_Method"));
		req.setAttribute("ticketBean", ticketBean);

		TicketPriceBean ticketPriceBean = new TicketPriceBean();
		req.setAttribute("ticketPriceBean", ticketPriceBean);

		PurchaseTicketDAO purchaseTicketDao = new PurchaseTicketDAO();
		
		try {
			purchaseTicketDao.getConnection();
			
			// We retrieve again all information which we already displayed the user, 
			// but in case there is an error we need to show the data again
			
			// Get all festival events
			GenericListBean<FestivalEventBean> festivalEventListBean = new GenericListBean<FestivalEventBean>();
			purchaseTicketDao.getFestivalEvents(festivalEventListBean);
			req.setAttribute("festivalEventListBean", festivalEventListBean);
			
			// Get ticket types with current price
			GenericListBean<Pair<TicketTypeBean, TicketPriceBean>> ticketTypeListBean = new GenericListBean<>();
			purchaseTicketDao.getTicketTypes(ticketTypeListBean);
			req.setAttribute("ticketTypeListBean", ticketTypeListBean);
			
			// Get dropdown content for visitor's sex
			GenericListBean<String> sexListBean = new GenericListBean<>();
			purchaseTicketDao.getVisitorSexDropdownContent(sexListBean);
			req.setAttribute("sexListBean", sexListBean);
			
			// Get dropdown content for ticket payment method
			GenericListBean<String> paymentMethodListBean = new GenericListBean<>();
			purchaseTicketDao.getTicketPaymentMethodDropdownContent(paymentMethodListBean);
			req.setAttribute("paymentMethodListBean", paymentMethodListBean);
			
			// Validate the input data
			purchaseTicketDao.validateInput(visitorBean, ticketBean);
			visitorBean.setBirthdate(DateConverter.UserInputDateToSQLDate(req.getParameter("inputBirthdate")));
			
			// Retrieve information about festival event and payment method again,
			// since the request data contains only ids for them
			purchaseTicketDao.getFestivalEventInformation(festivalEventBean);
			purchaseTicketDao.getTicketPriceInformation(ticketPriceBean, ticketTypeBean);
			
			req.setAttribute("action", "confirm");
						
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			req.setAttribute("action", "purchase");
		} finally {
			// Make sure to close the database connection
			try {
				purchaseTicketDao.closeConnection();
			} catch (SQLException e) {
				req.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/purchaseTicket.jsp");
		dispatcher.forward(req, resp);
	}
}
