package HoaDon_Modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import KetNoi_Modal.KetNoi;



public class HoaDon_Dao {
	public int Them(int makh ,int SoLuongVe,int TongTien) throws Exception{
		
	 	String query = "insert into HoaDon(KhachHang_ID,NgayMua,TrangThai,SoLuongGhe,TongTien)values(?,GETDATE(),1,?,?)";

        KetNoi kn = new KetNoi();
        
            kn.ketnoi();  // Kết nối đến cơ sở dữ liệu
            Connection conn = kn.cn;

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, makh);
            ps.setInt(2, SoLuongVe);
            ps.setInt(3, TongTien);
            int kq = ps.executeUpdate();        
            ps.close();
          
            conn.close();  // Đóng kết nối
       return kq;
}
public Integer GetmaxHd() throws Exception{
			KetNoi kn = new KetNoi();
            kn.ketnoi();  // Kết nối đến cơ sở dữ liệu
            Connection conn = kn.cn;
            String query = "select max(ID)as MaxHd from HoaDon";
            PreparedStatement ps = conn.prepareStatement(query);
           
            
            ResultSet rs = ps.executeQuery();
            int max = 0;
            if(rs.next()) max =rs.getInt("MaxHd");
          
            ps.close();
            rs.close();
            conn.close();  // Đóng kết nối
       return max;
}
}
