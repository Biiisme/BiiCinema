package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

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
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
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

		// Hiển thị trang đăng ký
		if (request.getParameter("register") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		}

		// Xử lý đăng ký
		if (request.getParameter("btRegister") != null) {
			String hoTen = request.getParameter("HoTen");
			String sdt = request.getParameter("SDT");
			String email = request.getParameter("Email");
			String tenDN = request.getParameter("TenDN");
			String matKhau = request.getParameter("MatKhau");
			String confirmMatKhau = request.getParameter("ConfirmMatKhau");

			// Validate input
			String errorMessage = validateRegistration(hoTen, sdt, email, tenDN, matKhau, confirmMatKhau);
			if (errorMessage != null) {
				request.setAttribute("error", errorMessage);
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
				return;
			}

			// Kiểm tra captcha nếu cần
			HttpSession session = request.getSession();
			Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
			String answer = request.getParameter("answer");

			if (captcha != null && answer != null && !captcha.isCorrect(answer)) {
				request.setAttribute("error", "Mã captcha không đúng!");
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
				return;
			}

			// Tạo user mới
			UserBo userBo = new UserBo();
			User newUser = new User(1, hoTen, sdt, email, tenDN, "customer", matKhau);

			try {
				if (userBo.addUser(newUser)) {
					request.setAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("error", "Đăng ký thất bại! Vui lòng thử lại.");
					RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Lỗi hệ thống! Vui lòng thử lại sau.");
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
			}
		}
	}

	private String validateRegistration(String hoTen, String sdt, String email, String tenDN, String matKhau, String confirmMatKhau) {
		// Kiểm tra họ tên
		if (hoTen == null || hoTen.trim().isEmpty()) {
			return "Họ tên không được để trống!";
		}
		if (hoTen.length() < 2 || hoTen.length() > 100) {
			return "Họ tên phải từ 2-100 ký tự!";
		}

		// Kiểm tra số điện thoại
		if (sdt == null || sdt.trim().isEmpty()) {
			return "Số điện thoại không được để trống!";
		}
		if (!Pattern.matches("^[0-9]{10,11}$", sdt)) {
			return "Số điện thoại phải là 10-11 chữ số!";
		}

		// Kiểm tra email
		if (email == null || email.trim().isEmpty()) {
			return "Email không được để trống!";
		}
		if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
			return "Email không hợp lệ!";
		}

		// Kiểm tra tên đăng nhập
		if (tenDN == null || tenDN.trim().isEmpty()) {
			return "Tên đăng nhập không được để trống!";
		}
		if (tenDN.length() < 3 || tenDN.length() > 50) {
			return "Tên đăng nhập phải từ 3-50 ký tự!";
		}
		if (!Pattern.matches("^[a-zA-Z0-9_]+$", tenDN)) {
			return "Tên đăng nhập chỉ chứa chữ cái, số và dấu gạch dưới!";
		}

		// Kiểm tra mật khẩu
		if (matKhau == null || matKhau.trim().isEmpty()) {
			return "Mật khẩu không được để trống!";
		}
		if (matKhau.length() < 6) {
			return "Mật khẩu phải ít nhất 6 ký tự!";
		}

		// Kiểm tra xác nhận mật khẩu
		if (confirmMatKhau == null || !matKhau.equals(confirmMatKhau)) {
			return "Mật khẩu xác nhận không khớp!";
		}

		return null; // Không có lỗi
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
