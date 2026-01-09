package Schedule_Modal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import Movie_Modal.Movie;
import Room_Modal.Room;

public class Schedule_Bo {
	static Schedule_Dao scheDAO = new Schedule_Dao();

	// Lấy tất cả lịch chiếu
	public ArrayList<Schedule> getAllSchedules() throws ClassNotFoundException, SQLException {
		return scheDAO.getAllSchedules();
	}

	// Lấy lịch chiếu theo ID
	public Schedule getScheduleById(int scheduleId) throws ClassNotFoundException, SQLException {
		return scheDAO.getScheduleById(scheduleId);
	}

	// Thêm lịch chiếu mới
	public boolean addSchedule(int phongID, int movieID, Date ngayChieu, String gioChieu) throws ClassNotFoundException, SQLException {
		return scheDAO.addSchedule(phongID, movieID, ngayChieu, gioChieu);
	}

	// Cập nhật lịch chiếu
	public boolean updateSchedule(int scheduleId, int phongID, int movieID, Date ngayChieu, String gioChieu) throws ClassNotFoundException, SQLException {
		return scheDAO.updateSchedule(scheduleId, phongID, movieID, ngayChieu, gioChieu);
	}

	// Xóa lịch chiếu
	public boolean deleteSchedule(int scheduleId) throws ClassNotFoundException, SQLException {
		return scheDAO.deleteSchedule(scheduleId);
	}

	// Lấy danh sách phòng
	public ArrayList<Room> getAllRooms() throws ClassNotFoundException, SQLException {
		return scheDAO.getAllRooms();
	}

	// Lấy danh sách phim
	public ArrayList<Movie> getAllMovies() throws ClassNotFoundException, SQLException {
		return scheDAO.getAllMovies();
	}

	// Legacy method - giữ để tương thích
	public ArrayList<Schedule> getLichChieu_PhongID(String maPhong) throws NumberFormatException, ClassNotFoundException, SQLException{
		ArrayList<Schedule>ds =new ArrayList<Schedule>();
		ds = scheDAO.getSchedule_PhongID(Integer.parseInt(maPhong));

		return ds;
	}
}
