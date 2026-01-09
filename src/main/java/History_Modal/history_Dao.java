package History_Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import KetNoi_Modal.KetNoi;
import History_Modal.GroupedHistory;


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

	public ArrayList<GroupedHistory> getGroupedHistory(int makh) throws ClassNotFoundException, SQLException{
		ArrayList<GroupedHistory> ds = new ArrayList<GroupedHistory>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT hd.ID as HoaDonID, m.TenPhim, m.poster_url as Poster, lc.NgayChieu, lc.GioChieu, p.TenPhong, " +
				"STRING_AGG(g.TenGhe, ',') WITHIN GROUP (ORDER BY g.TenGhe) as DanhSachGhe, " +
				"hd.NgayMua, hd.SoLuongGhe, hd.TongTien " +
				"FROM HoaDon hd " +
				"INNER JOIN CTHD ct ON hd.ID = ct.HoaDon_ID " +
				"INNER JOIN Movie m ON ct.Phim_ID = m.movie_id " +
				"INNER JOIN LichChieu lc ON ct.LichChieu_ID = lc.LichChieu_ID " +
				"INNER JOIN Phong p ON ct.Phong_ID = p.Phong_ID " +
				"INNER JOIN Ghe g ON ct.Ghe_ID = g.ID " +
				"WHERE hd.KhachHang_ID = ? AND hd.TrangThai = 1 " +
				"GROUP BY hd.ID, m.TenPhim, m.Poster, lc.NgayChieu, lc.GioChieu, p.TenPhong, hd.NgayMua, hd.SoLuongGhe, hd.TongTien " +
				"ORDER BY hd.NgayMua DESC";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, makh);
		ResultSet rs = cmd.executeQuery();

		while(rs.next()) {
			int hoaDonId = rs.getInt("HoaDonID");
			String tenPhim = rs.getString("TenPhim");
			String posterPhim = rs.getString("Poster");
			String ngayChieu = rs.getString("NgayChieu");
			String gioChieu = rs.getString("GioChieu");
			String tenPhong = rs.getString("TenPhong");
			String danhSachGhe = rs.getString("DanhSachGhe");
			String ngayMua = rs.getString("NgayMua");
			int soLuongVe = rs.getInt("SoLuongGhe");
			int tongTien = rs.getInt("TongTien");

			// Tạo mã QR từ ID hóa đơn (có thể sử dụng thư viện QR code sau)
			String maQR = "HD" + String.format("%06d", hoaDonId);

			GroupedHistory groupedHistory = new GroupedHistory(hoaDonId, tenPhim, posterPhim, ngayChieu,
					gioChieu, tenPhong, danhSachGhe, ngayMua, maQR, soLuongVe, tongTien);
			ds.add(groupedHistory);
		}
		rs.close();
		cmd.close();
		kn.cn.close();
		return ds;
	}
}
