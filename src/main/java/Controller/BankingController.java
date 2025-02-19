package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ChiTietHD.ChiTietHdBO;
import HoaDon_Modal.HoaDon_Bo;
import Seat_Modal.Seat_Bo;
import User_Modal.User;

/**
 * Servlet implementation class BankingController
 */
@WebServlet("/BankingController")
public class BankingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankingController() {
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
		
		String LichChieuID = "1";
		LichChieuID=(request.getParameter("LichChieuID"));
		int lichChieuID = Integer.parseInt(LichChieuID);
		 List<Integer> seats = new ArrayList<>();
		String seatsString = request.getParameter("seats");
		if (seatsString != null) {
			  // Supposons que vous obtenez la chaîne "[2, 10, 17]"
			if (seatsString != null && !seatsString.isEmpty()) {
			    seatsString = seatsString.replace("[", "").replace("]", "");  // Enlève les crochets
			    String[] seatsArray = seatsString.split(",");  // Divise la chaîne en un tableau de chaînes
			   
			    for (String seat : seatsArray) {
			        seats.add(Integer.parseInt(seat.trim()));  // Convertit chaque élément en entier
			    }
			}
		    HttpSession session = request.getSession();
        	User u = new User();
     		u=(User)session.getAttribute("dn");
        	
		
					HoaDon_Bo hdbo = new HoaDon_Bo();
					try {
						hdbo.Them(u.getMaUser(),seats.size(),seats.size()*45000);
					} catch (Exception e) {
						System.out.println("Lỗi thêm hóa đơn");
					}//Them 1 hoa don
					int maxhd;
					
					ChiTietHdBO cthdBO = new ChiTietHdBO();
					Seat_Bo sbo = new Seat_Bo();
					for(int item : seats)
						try {
							maxhd = hdbo.GetmaxHd();
							System.out.println("Mã hóa đơn mới nhất: " + maxhd);
							cthdBO.ThemCTHD(phongID,phimid,item, lichChieuID, maxhd);
							sbo.updateGhe(item);
						} catch (Exception e) {
							System.out.println("Lỗi lấy ra mahoadon va tạo cthd");
						}
					System.out.println("DaThem");
			
				
				response.sendRedirect("HomePageController");            
	            return;
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
