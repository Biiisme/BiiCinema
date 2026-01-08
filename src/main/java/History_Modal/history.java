package History_Modal;

public class history {
	private String TenPhim ;
	private String NgayChieu ;
	private String GioChieu ;
	private String TenPhong ;
	private String TenGhe ;
	private String NgayMua ;
	public String getTenPhim() {
		return TenPhim;
	}
	public void setTenPhim(String tenPhim) {
		TenPhim = tenPhim;
	}
	public String getNgayChieu() {
		return NgayChieu;
	}
	public void setNgayChieu(String ngayChieu) {
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
	public String getTenGhe() {
		return TenGhe;
	}
	public void setTenGhe(String tenGhe) {
		TenGhe = tenGhe;
	}
	public String getNgayMua() {
		return NgayMua;
	}
	public void setNgayMua(String ngayMua) {
		NgayMua = ngayMua;
	}
	public history(String tenPhim, String ngayChieu, String gioChieu, String tenPhong, String tenGhe, String ngayMua) {
		super();
		TenPhim = tenPhim;
		NgayChieu = ngayChieu;
		GioChieu = gioChieu;
		TenPhong = tenPhong;
		TenGhe = tenGhe;
		NgayMua = ngayMua;
	}
	public history() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
