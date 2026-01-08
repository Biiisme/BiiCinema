package User_Modal;

import java.sql.Date;
import java.util.Objects;

public class User {
	private int MaUser;
	private String Hoten;
	private String SDT;
	private String Email;
	private String TenDN;
	private String Role;
	private String MatKhau;
	public String getHoten() {
		return Hoten;
	}
	public void setHoten(String hoten) {
		Hoten = hoten;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTenDN() {
		return TenDN;
	}
	public void setTenDN(String tenDN) {
		TenDN = tenDN;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}

	public int getMaUser() {
		return MaUser;
	}
	public void setMaUser(int maUser) {
		MaUser = maUser;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public User(int maUser, String hoten, String sDT, String email, String tenDN, String role, String matKhau) {
		super();
		MaUser = maUser;
		Hoten = hoten;
		SDT = sDT;
		Email = email;
		TenDN = tenDN;
		Role = role;
		MatKhau = matKhau;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
