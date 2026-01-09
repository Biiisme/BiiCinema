package Schedule_Modal;

import java.sql.Date;

public class Schedule {
	private int LichChieuID;
	private int PhongID;
	private int MovieID;
	private Date NgayChieu;
	private String GioChieu;

	// Additional info for display
	private String TenPhong;
	private String TenPhim;

	public int getLichChieuID() {
		return LichChieuID;
	}
	public void setLichChieuID(int lichChieuID) {
		LichChieuID = lichChieuID;
	}

	public int getPhongID() {
		return PhongID;
	}
	public void setPhongID(int phongID) {
		PhongID = phongID;
	}

	public int getMovieID() {
		return MovieID;
	}
	public void setMovieID(int movieID) {
		MovieID = movieID;
	}

	public Date getNgayChieu() {
		return NgayChieu;
	}
	public void setNgayChieu(Date ngayChieu) {
		NgayChieu = ngayChieu;
	}

	public String getGioChieu() {
		return GioChieu;
	}
	public void setGioChieu(String gioChieu) {
		GioChieu = gioChieu;
	}

	public String getTenPhong() {
		return TenPhong;
	}
	public void setTenPhong(String tenPhong) {
		TenPhong = tenPhong;
	}

	public String getTenPhim() {
		return TenPhim;
	}
	public void setTenPhim(String tenPhim) {
		TenPhim = tenPhim;
	}

	public Schedule() {
		super();
	}

	public Schedule(int lichChieuID, int phongID, int movieID, Date ngayChieu, String gioChieu) {
		super();
		LichChieuID = lichChieuID;
		PhongID = phongID;
		MovieID = movieID;
		NgayChieu = ngayChieu;
		GioChieu = gioChieu;
	}

	public Schedule(int lichChieuID, int phongID, int movieID, Date ngayChieu, String gioChieu, String tenPhong, String tenPhim) {
		super();
		LichChieuID = lichChieuID;
		PhongID = phongID;
		MovieID = movieID;
		NgayChieu = ngayChieu;
		GioChieu = gioChieu;
		TenPhong = tenPhong;
		TenPhim = tenPhim;
	}

	@Override
	public String toString() {
		return "Schedule [LichChieuID=" + LichChieuID + ", PhongID=" + PhongID + ", MovieID=" + MovieID
				+ ", NgayChieu=" + NgayChieu + ", GioChieu=" + GioChieu + ", TenPhong=" + TenPhong
				+ ", TenPhim=" + TenPhim + "]";
	}
}
