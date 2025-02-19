package Controller;

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
import javax.servlet.http.HttpSession;

import ChiTietHD.ChiTietHdBO;

import HoaDon_Modal.HoaDon_Bo;

import Movie_Modal.Movie;
import Movie_Modal.Movie_Bo;
import Room_Modal.Room_Bo;
import Seat_Modal.Seat;
import Seat_Modal.Seat_Bo;
import User_Modal.User;


/**
 * Servlet implementation class BillController
 */
@WebServlet("/BillController")
public class BillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String phimIDParam = request.getParameter("PhimID");
	        String roomIDParam = request.getParameter("RoomID");
	        
	        int phimid = 0;
	        int phongID = 0;

	        try {
	            if (phimIDParam != null && !phimIDParam.isEmpty()) {
	                phimid = Integer.parseInt(phimIDParam);
	            }
	            if (roomIDParam != null && !roomIDParam.isEmpty()) {
	                phongID = Integer.parseInt(roomIDParam);
	            }
	        } catch (NumberFormatException e) {
	            request.setAttribute("error", "Lỗi: Giá trị PhimID hoặc RoomID không hợp lệ!");
	            e.printStackTrace();
	        }
		String startDate = "29-12-2024";
		startDate= (request.getParameter("startDate"));				
		request.setAttribute("startDate",startDate);
		String startTime = "17:45";				
		startTime=(request.getParameter("startTime"));
		request.setAttribute("startTime",startTime);
		String LichChieuID = "1";
		LichChieuID=(request.getParameter("LichChieuID"));
		request.setAttribute("LichChieuID",LichChieuID);
		String[] seatsParam = request.getParameterValues("seats");
		
				
		// Kiểm tra nếu danh sách không rỗng
		if (seatsParam != null) {
		    List<Integer> seats = new ArrayList<>();
		    for (String seat : seatsParam) {
		        seats.add(Integer.parseInt(seat)); // Convert String thành Integer
		    }

		    // Gắn danh sách ghế vào request
		    request.setAttribute("seats", seats);
		} else {
			String err = "Vui lòng chọn ghế ngồi!";
		    // Không có ghế được chọn
			request.setAttribute("err",err );
			System.out.println("err");
			RequestDispatcher rd=request.getRequestDispatcher("SeatController");
			rd.forward(request, response);
		}
		
		Movie_Bo mv = new Movie_Bo();
		Room_Bo roombo = new Room_Bo();
	
		
		
		try {
			Movie Movie =  mv.ChiTietphim(phimid);
			request.setAttribute("Movie",Movie);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {String TenPhong = "";
			TenPhong = roombo.getPhongtheoID(phongID);
			System.out.println(TenPhong);
			request.setAttribute("TenPhong",TenPhong);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("Bill.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
