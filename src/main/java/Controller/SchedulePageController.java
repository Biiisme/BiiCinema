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

import Schedule_Modal.Schedule;
import Schedule_Modal.Schedule_Bo;

/**
 * Servlet implementation class SchedulePageController
 */
@WebServlet("/SchedulePageController")
public class SchedulePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchedulePageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int phimid =Integer.parseInt(request.getParameter("movieId"));
		request.setAttribute("PhimID",phimid);
		int phongid =Integer.parseInt(request.getParameter("roomID"));
		request.setAttribute("RoomID",phongid);
		
		
		Schedule_Bo sche_Bo =new Schedule_Bo();
		ArrayList<Schedule>ds = new ArrayList<Schedule>();
		String roomID = request.getParameter("roomID");
		try {
			ds = sche_Bo.getLichChieu_PhongID(roomID);
			request.setAttribute("dsSchedule", ds);
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("schedule.jsp");
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
