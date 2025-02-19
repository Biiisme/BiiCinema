package History_Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import KetNoi_Modal.KetNoi;


public class history_Dao {
	public ArrayList<history>gethistory(int makh) throws ClassNotFoundException, SQLException{
		ArrayList<history> ds = new ArrayList<history>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select TenPhim,NgayChieu,GioChieu,TenPhong,TenGhe,NgayMua\r\n"
				+ "from Movie inner join CTHD on Movie.movie_id = CTHD.Phim_ID\r\n"
				+ "			inner join HoaDon on HoaDon.ID = CTHD.HoaDon_ID\r\n"
				+ "			inner join LichChieu on CTHD.LichChieu_ID = LichChieu.LichChieu_ID\r\n"
				+ "			inner join Phong on Phong.Phong_ID = CTHD.Phong_ID\r\n"
				+ "			inner join Ghe on Ghe.ID = CTHD.Ghe_ID\r\n"
				+ "\r\n"
				+ "Where KhachHang_ID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setInt(1,makh);
		ResultSet rs =cmd.executeQuery();
		while(rs.next()) {
			
			String TenPhim =rs.getString("TenPhim");
			String NgayChieu =rs.getString("NgayChieu");
			String GioChieu =rs.getString("GioChieu");
			String TenPhong =rs.getString("TenPhong");
			String TenGhe =rs.getString("TenGhe");
			String NgayMua =rs.getString("NgayMua");
			ds.add(new history(TenPhim,NgayChieu,GioChieu,TenPhong,TenGhe,NgayMua));
		}
		rs.close();
		kn.cn.close();
		return ds;
		
	} 
}
