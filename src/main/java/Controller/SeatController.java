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
import Seat_Modal.Seat;
import Seat_Modal.Seat_Bo;

@WebServlet("/SeatController")
public class SeatController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public SeatController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String err = request.getParameter("err");
    	if(err!="") {
    		System.out.println("helloerr");
    		request.setAttribute("err", err);
    	}
    	  	
        // Lấy tham số từ request và kiểm tra null
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

        request.setAttribute("PhimID", phimid);
        request.setAttribute("RoomID", phongID);
        
        
        String startTime = request.getParameter("startTime");
        String startDate = request.getParameter("startDate");
        String lichChieuID = request.getParameter("LichChieuID");

        request.setAttribute("startTime", startTime);
        request.setAttribute("startDate", startDate);
        request.setAttribute("LichChieuID", lichChieuID);
        // Tạo danh sách ghế theo từng hàng
        String[] seatRows = {"A", "B", "C", "D", "E"};
        for (String row : seatRows) {
            try {
                ArrayList<Seat> seats = Seat_Bo.getSeat(phongID, row);
                request.setAttribute("list" + row, seats);
            } catch (ClassNotFoundException | SQLException e) {
                request.setAttribute("error", "Lỗi truy vấn dữ liệu ghế!");
                e.printStackTrace();
            }
        }
        
        // Chuyển hướng đến JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("Seats.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
