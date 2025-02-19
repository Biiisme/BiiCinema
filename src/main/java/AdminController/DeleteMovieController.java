package AdminController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Movie_Modal.Movie_Bo;

/**
 * Servlet implementation class DeleteMovieController
 */
@WebServlet("/DeleteMovieController")
public class DeleteMovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMovieController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieIDParam = request.getParameter("Movie_ID");
	    System.out.println("Movie_ID nhận được: " + movieIDParam);

	    if (movieIDParam == null || movieIDParam.isEmpty()) {
	        System.out.println("Lỗi: Movie_ID không có giá trị!");
	        response.sendRedirect("AdminHomeController");
	        return;
	    }

	    try {
	        int movieID = Integer.parseInt(movieIDParam);
	        System.out.println("Movie_ID sau khi parse: " + movieID);

	        Movie_Bo mvBo = new Movie_Bo();
	        mvBo.delete_Movie(movieID);
	        System.out.println("Đã gọi delete_Movie() thành công!");
	    } catch (NumberFormatException e) {
	        System.out.println("Lỗi: Movie_ID không phải số hợp lệ!");
	        e.printStackTrace();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }

	    response.sendRedirect("AdminHomeController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
