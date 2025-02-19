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
import User_Modal.User;
import User_Modal.UserBo;

/**
 * Servlet implementation class AddUserController
 */
@WebServlet("/AddUserController")
public class AddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		if(request.getParameter("btAddUser")!=null) {
			 RequestDispatcher rd = request.getRequestDispatcher("adminAddUser.jsp");
		        rd.forward(request, response);
		}
		
		if(request.getParameter("AddUser")!=null)
		 {
			 String hoten = request.getParameter("HoTen");
			 String sdt = request.getParameter("SDT");
			 String email = request.getParameter("Email");
			 String TenDN = request.getParameter("TenDN");
			 String matkhau = request.getParameter("MatKhau");
			 
			 UserBo u = new UserBo();
			 User user1= new User(1,hoten,sdt,email,TenDN,"customer",matkhau);
			 try {
				u.addUser(user1);
				response.sendRedirect("UserHomeController");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		if(request.getParameter("btDelete")!=null) {
			 UserBo u = new UserBo();
			 String userID = request.getParameter("userID");
			 try {
				u.deleteUser(Integer.parseInt(userID));
				response.sendRedirect("UserHomeController");
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
