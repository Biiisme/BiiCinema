package User_Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import KetNoi_Modal.KetNoi;
import Movie_Modal.Movie;


public class UserDao {
	public ArrayList<User>getUser() throws ClassNotFoundException, SQLException{
		ArrayList<User> ds = new ArrayList<User>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from KhachHang";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs =cmd.executeQuery();
		while(rs.next()) {
			int MaUser = rs.getInt("MAKH");
			String Hoten =rs.getString("Hoten");
			String SDT =rs.getString("SDT");
			String Email =rs.getString("Email");
			String TenDN =rs.getString("TenDN");
			String Role =rs.getString("Role");
			String MatKhau =rs.getString("MatKhau");
			ds.add(new User(MaUser,Hoten,SDT,Email,TenDN,Role,MatKhau));
		}
		rs.close();
		kn.cn.close();
		return ds;
		
	} 
	
public Boolean addUser(User mv) throws SQLException, ClassNotFoundException{
		
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO KhachHang (HoTen,SDT,Email,TenDN,Role,MatKhau) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		cmd.setString(1, mv.getHoten());
        cmd.setString(2, mv.getSDT());
        cmd.setString(3, mv.getEmail());
        cmd.setString(4, mv.getTenDN());
        cmd.setString(5, mv.getRole());
        cmd.setString(6, mv.getMatKhau());
     
       
        int kq = cmd.executeUpdate();
        
		cmd.close();
		kn.cn.close();
		return kq >0 ;
	}
public Boolean updateUser(User mv) throws SQLException, ClassNotFoundException{
	
	KetNoi kn = new KetNoi();
	kn.ketnoi();
	String sql = "Update KhachHang set HoTen =?, SDT=?, Email=?, TenDN=?, Role=?, MatKhau=? where MAKH =?";
	PreparedStatement cmd = kn.cn.prepareStatement(sql);
	
	
	cmd.setString(1, mv.getHoten());
    cmd.setString(2, mv.getSDT());
    cmd.setString(3, mv.getEmail());
    cmd.setString(4, mv.getTenDN());
    cmd.setString(5, mv.getRole());
    cmd.setString(6, mv.getMatKhau());
    cmd.setInt(7, mv.getMaUser());

    int kq = cmd.executeUpdate();
    
	cmd.close();
	kn.cn.close();
	return kq >0 ;
}
public Boolean deleteUser(int UserID) throws SQLException, ClassNotFoundException{
	
	KetNoi kn = new KetNoi();
	kn.ketnoi();
	String sql = "delete from KhachHang where MAKH  = ? ";
	PreparedStatement cmd = kn.cn.prepareStatement(sql);
	
	cmd.setInt(1, UserID);
    
    int kq = cmd.executeUpdate();
    
	cmd.close();
	kn.cn.close();
	return kq >0 ;
}
public User CheckLogin(String username, String password) throws SQLException, ClassNotFoundException {
	KetNoi kn = new KetNoi();
	kn.ketnoi();
    String sql = "SELECT * FROM KhachHang WHERE TenDN=? AND MatKhau=?";
    PreparedStatement ps = kn.cn.prepareStatement(sql);
    ps.setString(1, username);
    ps.setString(2, password);

    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
    	int MaUser = rs.getInt("MAKH");
		String Hoten =rs.getString("Hoten");
		String SDT =rs.getString("SDT");
		String Email =rs.getString("Email");
		String TenDN =rs.getString("TenDN");
		String Role =rs.getString("Role");
		String MatKhau =rs.getString("MatKhau");
        return new User(MaUser,Hoten,SDT,Email,TenDN,Role,MatKhau);
    }
    return null;
}

}
