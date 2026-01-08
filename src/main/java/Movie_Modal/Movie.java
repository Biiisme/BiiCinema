package Movie_Modal;

public class Movie {
	private int Phim_ID;
	private String TenPhim ;
	private String poster_url ;
	private String MoTaNgan ;
	private String MoTaDai ;
	private String DaoDien ;
	private String DienVien ;
	private String trailer_url ;
	private String Loai ;
	private String DoiTuong ;
	private String NgayKhoiChieu ;
	private int ThoiLuong;
	
	
	public int getThoiLuong() {
		return ThoiLuong;
	}


	public void setThoiLuong(int thoiLuong) {
		ThoiLuong = thoiLuong;
	}


	public int getPhim_ID() {
		return Phim_ID;
	}


	public void setPhim_ID(int phim_ID) {
		Phim_ID = phim_ID;
	}


	public String getTenPhim() {
		return TenPhim;
	}


	public void setTenPhim(String tenPhim) {
		TenPhim = tenPhim;
	}


	public String getPoster_url() {
		return poster_url;
	}


	public void setPoster_url(String poster_url) {
		this.poster_url = poster_url;
	}


	public String getMoTaNgan() {
		return MoTaNgan;
	}


	public void setMoTaNgan(String moTaNgan) {
		MoTaNgan = moTaNgan;
	}


	public String getMoTaDai() {
		return MoTaDai;
	}


	public void setMoTaDai(String moTaDai) {
		MoTaDai = moTaDai;
	}


	public String getDaoDien() {
		return DaoDien;
	}


	public void setDaoDien(String daoDien) {
		DaoDien = daoDien;
	}


	public String getDienVien() {
		return DienVien;
	}


	public void setDienVien(String dienVien) {
		DienVien = dienVien;
	}


	public String getTrailer_url() {
		return trailer_url;
	}


	public void setTrailer_url(String trailer_url) {
		this.trailer_url = trailer_url;
	}


	public String getLoai() {
		return Loai;
	}


	public void setLoai(String loai) {
		Loai = loai;
	}


	public String getDoiTuong() {
		return DoiTuong;
	}


	public void setDoiTuong(String doiTuong) {
		DoiTuong = doiTuong;
	}


	public String getNgayKhoiChieu() {
		return NgayKhoiChieu;
	}


	public void setNgayKhoiChieu(String ngayKhoiChieu) {
		NgayKhoiChieu = ngayKhoiChieu;
	}


	public Movie(int phim_ID, String tenPhim, String poster_url, String moTaNgan, String moTaDai, String daoDien,
			String dienVien, String trailer_url, String loai, String doiTuong, String ngayKhoiChieu ,int thoiluong) {
		super();
		Phim_ID = phim_ID;
		TenPhim = tenPhim;
		this.poster_url = poster_url;
		MoTaNgan = moTaNgan;
		MoTaDai = moTaDai;
		DaoDien = daoDien;
		DienVien = dienVien;
		this.trailer_url = trailer_url;
		Loai = loai;
		DoiTuong = doiTuong;
		NgayKhoiChieu = ngayKhoiChieu;
		ThoiLuong = thoiluong;
	}


	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
