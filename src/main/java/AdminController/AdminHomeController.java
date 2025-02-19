package AdminController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Movie_Modal.Movie;
import Movie_Modal.Movie_Bo;

/**
 * Servlet implementation class AdminHomeControll
 */
@WebServlet("/AdminHomeController")
public class AdminHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Movie>listSearch = new ArrayList<Movie>();
		List<String> dsm = new ArrayList<String>();
		Movie_Bo PhimBo = new Movie_Bo();
		String keysearch = request.getParameter("keysearch");
		String Movie_id = request.getParameter("movieId");
		if(Movie_id!= null) {
			int Movie_ID_int = Integer.parseInt(Movie_id);
			try {
				Movie temp = PhimBo.ChiTietphim(Movie_ID_int);
				request.setAttribute("ChiTietPhim", temp);	
				RequestDispatcher dispatcher = request.getRequestDispatcher("movie-detail.jsp");
		         dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		if(keysearch !=null) {
			try {
			listSearch = Movie_Bo.Tim(keysearch);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
	try {
			listSearch = PhimBo.getPhim();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dsm = PhimBo.getPhimMoi();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("dsphimmoi", dsm);
		request.setAttribute("listSearch", listSearch);	
		RequestDispatcher rd = request.getRequestDispatcher("adminHome.jsp");
		rd.forward(request, response);
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
