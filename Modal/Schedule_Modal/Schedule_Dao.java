package Schedule_Modal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import KetNoi_Modal.KetNoi;



public class Schedule_Dao {
	public ArrayList<Schedule> getSchedule_PhongID(int maPhong) throws ClassNotFoundException, SQLException{
		ArrayList<Schedule> ds = new ArrayList<Schedule>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from LichChieu where Phong_ID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1,maPhong);
		ResultSet rs =cmd.executeQuery();
		while(rs.next()) {
			int maLichChieu =rs.getInt("LichChieu_ID");
			Date NgayChieu =rs.getDate("NgayChieu");
			String GioChieu =rs.getString("GioChieu");
			
			ds.add(new Schedule(maLichChieu, NgayChieu, GioChieu));
		}
		rs.close();
		kn.cn.close();
		return ds;
	}    
}
