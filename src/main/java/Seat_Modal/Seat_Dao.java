package Seat_Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import KetNoi_Modal.KetNoi;



public class Seat_Dao {
	public ArrayList<Seat>getGhe(int PhongID) throws ClassNotFoundException, SQLException{
		ArrayList<Seat> ds = new ArrayList<Seat>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from Ghe where Phong_ID = ? ";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1,PhongID);
		ResultSet rs =cmd.executeQuery();
		while(rs.next()) {
			int GheID = rs.getInt("ID");
			String TenGhe =rs.getString("TenGhe");
			int GiaGhe = rs.getInt("Gia");
			int TrangThai = rs.getInt("TrangThai");
			ds.add(new Seat(GheID,TenGhe,GiaGhe,TrangThai));
		}
		rs.close();
		kn.cn.close();
		return ds;		
	} 
	
	public boolean updateGhe(int GheID) throws ClassNotFoundException, SQLException {
	    boolean isUpdated = false;
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    
	    // SQL câu lệnh cập nhật ghế theo GheID
	    String sql = "UPDATE Ghe SET  TrangThai = 1 WHERE ID = ?";
	    
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    
	    // Set các giá trị cho PreparedStatement
	    
	    cmd.setInt(1, GheID);
	    
	    // Thực thi câu lệnh SQL
	    int rowsAffected = cmd.executeUpdate();
	    
	    if (rowsAffected > 0) {
	        isUpdated = true;  // Nếu có ít nhất 1 dòng bị ảnh hưởng, thì cập nhật thành công
	    }

	    // Đóng kết nối
	    cmd.close();
	    kn.cn.close();
	    
	    return isUpdated;  // Trả về kết quả cập nhật
	}
}
