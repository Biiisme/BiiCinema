package Schedule_Modal;

import java.sql.Date;

public class Schedule {
	private int LichChieuID;
	private Date NgayChieu;
	private String GioChieu;
	
	public int getLichChieuID() {
		return LichChieuID;
	}
	public void setLichChieuID(int lichChieuID) {
		LichChieuID = lichChieuID;
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
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(int lichChieuID, Date ngayChieu, String gioChieu) {
		super();
		LichChieuID = lichChieuID;
		NgayChieu = ngayChieu;
		GioChieu = gioChieu;
	}
	
	
}
