package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

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
		
		GenericListBean<FestivalEventBean> festivalEventListBean = new GenericListBean<FestivalEventBean>();
		GenericListBean<Pair<TicketTypeBean, TicketPriceBean>> ticketTypeListBean = new GenericListBean<>();
		GenericListBean<String> sexListBean = new GenericListBean<>();
		GenericListBean<String> paymentMethodListBean = new GenericListBean<>();
		
		PurchaseTicketDAO purchaseTicketDao;
		
		try {
			purchaseTicketDao = new PurchaseTicketDAO();
						
			purchaseTicketDao.getFestivalEvents(festivalEventListBean);
			req.setAttribute("festivalEventListBean", festivalEventListBean);
			
			purchaseTicketDao.getTicketTypes(ticketTypeListBean);
			req.setAttribute("ticketTypeListBean", ticketTypeListBean);
			
			purchaseTicketDao.getVisitorSexDropdownContent(sexListBean);
			req.setAttribute("sexListBean", sexListBean);
			
			purchaseTicketDao.getTicketPaymentMethodDropdownContent(paymentMethodListBean);
			req.setAttribute("paymentMethodListBean", paymentMethodListBean);
			
			purchaseTicketDao.closeConnection();
						
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/purchaseTicket.jsp");
		dispatcher.forward(req, resp);
	}

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
		
		TicketPriceBean ticketPriceBean = new TicketPriceBean();
		
		try {
			PurchaseTicketDAO purchaseTicketDao = new PurchaseTicketDAO();
			
			purchaseTicketDao.getFestivalEventInformation(festivalEventBean);
			purchaseTicketDao.getTicketPriceInformation(ticketPriceBean, ticketTypeBean);
						
			visitorBean.setBirthdate(DateConverter.SQLDateToJavaDate(req.getParameter("inputBirthdate")));
			
			req.setAttribute("festivalEventBean", festivalEventBean);
			req.setAttribute("ticketTypeBean", ticketTypeBean);
			req.setAttribute("visitorBean", visitorBean);
			req.setAttribute("ticketBean", ticketBean);
			req.setAttribute("ticketPriceBean", ticketPriceBean);
			
			purchaseTicketDao.closeConnection();
			
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}	
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/purchaseTicketConfirm.jsp");
		dispatcher.forward(req, resp);		
	}
}
