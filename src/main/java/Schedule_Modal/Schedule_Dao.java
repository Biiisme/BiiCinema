package Schedule_Modal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import KetNoi_Modal.KetNoi;
import Movie_Modal.Movie;
import Room_Modal.Room;



public class Schedule_Dao {

	// Lấy tất cả lịch chiếu với thông tin phim và phòng
	public ArrayList<Schedule> getAllSchedules() throws ClassNotFoundException, SQLException {
		ArrayList<Schedule> ds = new ArrayList<Schedule>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT lc.LichChieu_ID, lc.Phong_ID, lc.movie_id, lc.NgayChieu, lc.GioChieu, " +
					 "p.TenPhong, m.TenPhim " +
					 "FROM LichChieu lc " +
					 "INNER JOIN Phong p ON lc.Phong_ID = p.Phong_ID " +
					 "INNER JOIN Movie m ON lc.movie_id = m.movie_id " +
					 "ORDER BY lc.NgayChieu DESC, lc.GioChieu ASC";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int lichChieuID = rs.getInt("LichChieu_ID");
			int phongID = rs.getInt("Phong_ID");
			int movieID = rs.getInt("movie_id");
			Date ngayChieu = rs.getDate("NgayChieu");
			String gioChieu = rs.getString("GioChieu");
			String tenPhong = rs.getString("TenPhong");
			String tenPhim = rs.getString("TenPhim");

			ds.add(new Schedule(lichChieuID, phongID, movieID, ngayChieu, gioChieu, tenPhong, tenPhim));
		}
		rs.close();
		cmd.close();
		kn.cn.close();
		return ds;
	}

	// Lấy lịch chiếu theo ID
	public Schedule getScheduleById(int scheduleId) throws ClassNotFoundException, SQLException {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT lc.LichChieu_ID, lc.Phong_ID, lc.movie_id, lc.NgayChieu, lc.GioChieu, " +
					 "p.TenPhong, m.TenPhim " +
					 "FROM LichChieu lc " +
					 "INNER JOIN Phong p ON lc.Phong_ID = p.Phong_ID " +
					 "INNER JOIN Movie m ON lc.movie_id = m.movie_id " +
					 "WHERE lc.LichChieu_ID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, scheduleId);
		ResultSet rs = cmd.executeQuery();

		Schedule schedule = null;
		if(rs.next()) {
			int lichChieuID = rs.getInt("LichChieu_ID");
			int phongID = rs.getInt("Phong_ID");
			int movieID = rs.getInt("movie_id");
			Date ngayChieu = rs.getDate("NgayChieu");
			String gioChieu = rs.getString("GioChieu");
			String tenPhong = rs.getString("TenPhong");
			String tenPhim = rs.getString("TenPhim");

			schedule = new Schedule(lichChieuID, phongID, movieID, ngayChieu, gioChieu, tenPhong, tenPhim);
		}
		rs.close();
		cmd.close();
		kn.cn.close();
		return schedule;
	}

	// Thêm lịch chiếu mới
	public boolean addSchedule(int phongID, int movieID, Date ngayChieu, String gioChieu) throws ClassNotFoundException, SQLException {
		// Kiểm tra xung đột lịch chiếu
		if (hasScheduleConflict(phongID, ngayChieu, gioChieu)) {
			throw new SQLException("Phòng đã có lịch chiếu vào thời gian này!");
		}

		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO LichChieu (Phong_ID, movie_id, NgayChieu, GioChieu) VALUES (?, ?, ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, phongID);
		cmd.setInt(2, movieID);
		cmd.setDate(3, ngayChieu);
		cmd.setString(4, gioChieu);

		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result > 0;
	}

	// Cập nhật lịch chiếu
	public boolean updateSchedule(int scheduleId, int phongID, int movieID, Date ngayChieu, String gioChieu) throws ClassNotFoundException, SQLException {
		// Kiểm tra xung đột lịch chiếu (trừ schedule hiện tại)
		if (hasScheduleConflictExcludeId(phongID, ngayChieu, gioChieu, scheduleId)) {
			throw new SQLException("Phòng đã có lịch chiếu vào thời gian này!");
		}

		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "UPDATE LichChieu SET Phong_ID = ?, movie_id = ?, NgayChieu = ?, GioChieu = ? WHERE LichChieu_ID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, phongID);
		cmd.setInt(2, movieID);
		cmd.setDate(3, ngayChieu);
		cmd.setString(4, gioChieu);
		cmd.setInt(5, scheduleId);

		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result > 0;
	}

	// Xóa lịch chiếu
	public boolean deleteSchedule(int scheduleId) throws ClassNotFoundException, SQLException {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "DELETE FROM LichChieu WHERE LichChieu_ID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, scheduleId);

		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result > 0;
	}

	// Lấy danh sách phòng
	public ArrayList<Room> getAllRooms() throws ClassNotFoundException, SQLException {
		ArrayList<Room> ds = new ArrayList<Room>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT Phong_ID, TenPhong FROM Phong ORDER BY TenPhong";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int phongID = rs.getInt("Phong_ID");
			String tenPhong = rs.getString("TenPhong");

			Room room = new Room();
			room.setPhong_ID(phongID);
			room.setTenPhong(tenPhong);
			ds.add(room);
		}
		rs.close();
		cmd.close();
		kn.cn.close();
		return ds;
	}

	// Lấy danh sách phim
	public ArrayList<Movie> getAllMovies() throws ClassNotFoundException, SQLException {
		ArrayList<Movie> ds = new ArrayList<Movie>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT movie_id, TenPhim FROM Movie ORDER BY TenPhim";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int movieID = rs.getInt("movie_id");
			String tenPhim = rs.getString("TenPhim");

			Movie movie = new Movie();
			movie.setMovie_id(movieID);
			movie.setTenPhim(tenPhim);
			ds.add(movie);
		}
		rs.close();
		cmd.close();
		kn.cn.close();
		return ds;
	}

	// Kiểm tra xung đột lịch chiếu
	private boolean hasScheduleConflict(int phongID, Date ngayChieu, String gioChieu) throws ClassNotFoundException, SQLException {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT COUNT(*) as count FROM LichChieu WHERE Phong_ID = ? AND NgayChieu = ? AND GioChieu = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, phongID);
		cmd.setDate(2, ngayChieu);
		cmd.setString(3, gioChieu);
		ResultSet rs = cmd.executeQuery();

		boolean hasConflict = false;
		if(rs.next()) {
			hasConflict = rs.getInt("count") > 0;
		}
		rs.close();
		cmd.close();
		kn.cn.close();
		return hasConflict;
	}

	// Kiểm tra xung đột lịch chiếu (trừ schedule hiện tại)
	private boolean hasScheduleConflictExcludeId(int phongID, Date ngayChieu, String gioChieu, int excludeId) throws ClassNotFoundException, SQLException {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT COUNT(*) as count FROM LichChieu WHERE Phong_ID = ? AND NgayChieu = ? AND GioChieu = ? AND LichChieu_ID != ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, phongID);
		cmd.setDate(2, ngayChieu);
		cmd.setString(3, gioChieu);
		cmd.setInt(4, excludeId);
		ResultSet rs = cmd.executeQuery();

		boolean hasConflict = false;
		if(rs.next()) {
			hasConflict = rs.getInt("count") > 0;
		}
		rs.close();
		cmd.close();
		kn.cn.close();
		return hasConflict;
	}

	// Legacy method - giữ để tương thích
	public ArrayList<Schedule> getSchedule_PhongID(int maPhong) throws ClassNotFoundException, SQLException{
		ArrayList<Schedule> ds = new ArrayList<Schedule>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT lc.LichChieu_ID, lc.Phong_ID, lc.movie_id, lc.NgayChieu, lc.GioChieu, " +
					 "p.TenPhong, m.TenPhim " +
					 "FROM LichChieu lc " +
					 "INNER JOIN Phong p ON lc.Phong_ID = p.Phong_ID " +
					 "INNER JOIN Movie m ON lc.movie_id = m.movie_id " +
					 "WHERE lc.Phong_ID = ? " +
					 "ORDER BY lc.NgayChieu DESC, lc.GioChieu ASC";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, maPhong);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			int lichChieuID = rs.getInt("LichChieu_ID");
			int phongID = rs.getInt("Phong_ID");
			int movieID = rs.getInt("movie_id");
			Date ngayChieu = rs.getDate("NgayChieu");
			String gioChieu = rs.getString("GioChieu");
			String tenPhong = rs.getString("TenPhong");
			String tenPhim = rs.getString("TenPhim");

			ds.add(new Schedule(lichChieuID, phongID, movieID, ngayChieu, gioChieu, tenPhong, tenPhim));
		}
		rs.close();
		cmd.close();
		kn.cn.close();
		return ds;
	}
}
