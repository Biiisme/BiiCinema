package History_Modal;

import java.util.List;

public class GroupedHistory {
    private int hoaDonId;
    private String tenPhim;
    private String posterPhim;
    private String ngayChieu;
    private String gioChieu;
    private String tenPhong;
    private String danhSachGhe; // Ví dụ: "A1,A2,A3"
    private String ngayMua;
    private String maQR;
    private int soLuongVe;
    private int tongTien;

    public GroupedHistory() {
        super();
    }

    public GroupedHistory(int hoaDonId, String tenPhim, String posterPhim, String ngayChieu,
                         String gioChieu, String tenPhong, String danhSachGhe, String ngayMua,
                         String maQR, int soLuongVe, int tongTien) {
        super();
        this.hoaDonId = hoaDonId;
        this.tenPhim = tenPhim;
        this.posterPhim = posterPhim;
        this.ngayChieu = ngayChieu;
        this.gioChieu = gioChieu;
        this.tenPhong = tenPhong;
        this.danhSachGhe = danhSachGhe;
        this.ngayMua = ngayMua;
        this.maQR = maQR;
        this.soLuongVe = soLuongVe;
        this.tongTien = tongTien;
    }

    // Getters and Setters
    public int getHoaDonId() {
        return hoaDonId;
    }

    public void setHoaDonId(int hoaDonId) {
        this.hoaDonId = hoaDonId;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getPosterPhim() {
        return posterPhim;
    }

    public void setPosterPhim(String posterPhim) {
        this.posterPhim = posterPhim;
    }

    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public String getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(String gioChieu) {
        this.gioChieu = gioChieu;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getDanhSachGhe() {
        return danhSachGhe;
    }

    public void setDanhSachGhe(String danhSachGhe) {
        this.danhSachGhe = danhSachGhe;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getMaQR() {
        return maQR;
    }

    public void setMaQR(String maQR) {
        this.maQR = maQR;
    }

    public int getSoLuongVe() {
        return soLuongVe;
    }

    public void setSoLuongVe(int soLuongVe) {
        this.soLuongVe = soLuongVe;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
