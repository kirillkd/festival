package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import beans.BandBean;
import beans.VisitorAccountBean;
import beans.TimetableEntryBean;
import dao.CreateTimetableDAO;

/**
 * Servlet implementation class CheckUsernameServlet
 */
@WebServlet("/createTimetable")
public class CreateTimetableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		try {
			CreateTimetableDAO dao = new CreateTimetableDAO();
	    	VisitorAccountBean visitor_account = new VisitorAccountBean();
	    	ArrayList<BandBean> bands = new ArrayList<>();
	    	ArrayList<TimetableEntryBean> timetable_entry = new ArrayList<>();
	    	visitor_account.setUsername(req.getParameter("username"));
	    	
	    	for (int i=0; i<Integer.parseInt(req.getParameter("bands")); i++){
	    		TimetableEntryBean timetable = new TimetableEntryBean();
	    		BandBean band = new BandBean();
	    		
	    		timetable.setPreference(Integer.parseInt(req.getParameter(i+".preferences")));
	    		
	    		band.setName(req.getParameter(i+".band"));
	    		
	    		timetable_entry.add(timetable);
	    		bands.add(band);
	    	}
	    	
	    	dao.getCreateTimetable(visitor_account, timetable_entry, bands);
	    	req.setAttribute("bands", bands);	
		}
		catch (SQLException | ClassNotFoundException | RuntimeException e) {
			req.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/timetable.jsp");
		dispatcher.forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/timetable.jsp");
		dispatcher.forward(request, response);
}
	
}
