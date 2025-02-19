package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import User_Modal.User;
import User_Modal.UserBo;
import nl.captcha.Captcha;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("login") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }

        if (request.getParameter("btLogin") != null) {
            String tendn = request.getParameter("TenDN");
            String mk = request.getParameter("MatKhau");
            System.out.println("Login");
            
            HttpSession session = request.getSession();
    		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
    		request.setCharacterEncoding("UTF-8");
    		String answer = request.getParameter("answer");
    		if(session.getAttribute("dem")!=null){
    			int d =(int)session.getAttribute("dem");
    			if(answer!=null)
    				if(d>=3 && !captcha.isCorrect(answer)) {
    					RequestDispatcher rd=request.getRequestDispatcher("LoginController");
    					rd.forward(request, response);
    					return;
    				}
    		}
            if (tendn != null && mk != null) {
            	System.out.println("thanhcong1");
                UserBo khbo = new UserBo();
                try {
                    User kh = khbo.CheckLogin(tendn, mk);

                    if (kh != null) { 
                    	System.out.println(kh.getRole());
                    	System.out.println("thanhcong2");// Kiểm tra tránh lỗi NullPointerException
                       
                        session.setAttribute("dn", kh);
                        session.removeAttribute("dem");
                        if (kh.getRole() != null && "customer".equalsIgnoreCase(kh.getRole().trim())) {
                            response.sendRedirect("HomePageController");
                            return;
                        } else if (kh.getRole() != null && "admin".equalsIgnoreCase(kh.getRole().trim())) {
                        	System.out.println("thanhcong");
                        	response.sendRedirect("AdminHomeController");
                        	return;
                        }
                        System.out.println("thanhcong3");
                    } else {
    					if(session.getAttribute("dem")==null) {
    						session.setAttribute("dem", (int)0);
    					}
    					int d = (int)session.getAttribute("dem");
    					d++;
    					session.setAttribute("dem", d);
    					request.setAttribute("tb", "Dang nhập sai");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect("error.jsp"); // Chuyển hướng nếu có lỗi
                }
            }
            
            	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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
