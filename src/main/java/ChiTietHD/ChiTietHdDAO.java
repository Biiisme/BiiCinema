package ChiTietHD;

import java.sql.Connection;
import java.sql.PreparedStatement;

import KetNoi_Modal.KetNoi;



public class ChiTietHdDAO {
	public int ThemCTHD(int phongID,int PhimID,int GheID,int LichChieuID,int HoaDonID) throws Exception{
		
	 	String query = "insert into CTHD(Phong_ID,Phim_ID,Ghe_ID,LichChieu_ID,HoaDon_ID)values(?,?,?,?,?);";

	    KetNoi kn = new KetNoi();
	    
	        kn.ketnoi();  // Kết nối đến cơ sở dữ liệu
	        Connection conn = kn.cn;

	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, phongID);
	        ps.setInt(2, PhimID);
	        ps.setInt(3, GheID);
	        ps.setInt(4, LichChieuID);
	        ps.setInt(5, HoaDonID);
	                
	        int kq = ps.executeUpdate();
	      
	        ps.close();
	      
	        conn.close();  // Đóng kết nối
	   return kq;
	}
}
