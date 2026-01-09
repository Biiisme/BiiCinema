package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import History_Modal.history;
import History_Modal.history_Bo;
import History_Modal.GroupedHistory;
import User_Modal.User;

/**
 * Servlet implementation class HistoryController
 */
@WebServlet("/HistoryController")
public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = new User();
 		u=(User)session.getAttribute("dn");
 		
 		if(u==null) {
 			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
 			rd.forward(request, response);
 			return;
 		}
		history_Bo hisBo=new history_Bo();
		ArrayList<GroupedHistory> groupedHistoryList = new ArrayList<GroupedHistory>();
		try {
			groupedHistoryList = hisBo.getGroupedHistory(u.getMaUser());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("lichsu");
		request.setAttribute("groupedHistoryList", groupedHistoryList);	
		RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
