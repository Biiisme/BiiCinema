package Seat_Modal;

public class Seat {
	private int GheID;
	private String TenGhe;
	private int GiaGhe;
	private int TrangThai;
	public int getGheID() {
		return GheID;
	}
	public void setGheID(int gheID) {
		GheID = gheID;
	}
	public String getTenGhe() {
		return TenGhe;
	}
	public void setTenGhe(String tenGhe) {
		TenGhe = tenGhe;
	}
	public int getGiaGhe() {
		return GiaGhe;
	}
	public void setGiaGhe(int giaGhe) {
		GiaGhe = giaGhe;
	}
	public int getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
	public Seat(int gheID, String tenGhe, int giaGhe, int trangThai) {
		super();
		GheID = gheID;
		TenGhe = tenGhe;
		GiaGhe = giaGhe;
		TrangThai = trangThai;
	}
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
