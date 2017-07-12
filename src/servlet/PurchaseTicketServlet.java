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

import dao.PurchaseTicketDAO;

import beans.FestivalEventBean;
import beans.GenericListBean;
import beans.TicketPriceBean;
import beans.TicketTypeBean;


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
	
	

}
