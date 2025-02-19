package Movie_Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import KetNoi_Modal.KetNoi;





public class Movie_Dao {
	public ArrayList<Movie>getPhim() throws ClassNotFoundException, SQLException{
		ArrayList<Movie> ds = new ArrayList<Movie>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from Movie";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs =cmd.executeQuery();
		while(rs.next()) {
			ds.add(new Movie(rs.getInt("movie_id"),rs.getString("TenPhim"),rs.getString("poster_url"), 
					rs.getString("MoTaNgan"), rs.getString("MoTaDai"), rs.getString("DaoDien"), rs.getString("DienVien"),
					rs.getString("trailer_url") , rs.getString("Loai"),rs.getString("DoiTuong"),rs.getString("NgayKhoiChieu"),rs.getInt("ThoiLuong")));
		}
		rs.close();
		kn.cn.close();
		return ds;
		
	} 
	public List<String>getPhimMoi() throws ClassNotFoundException, SQLException{
		List<String> ds = new ArrayList<String>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from MovieNew";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs =cmd.executeQuery();
		while(rs.next()) {
			ds.add(rs.getString("banner_url"));
		}
		rs.close();
		kn.cn.close();
		return ds;
		
	} 
	
	public Boolean addMovie(Movie mv) throws SQLException, ClassNotFoundException{
		
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO Movie ( TenPhim, poster_url, MoTaNgan, MoTaDai, DaoDien, DienVien,trailer_url,Loai,NgayKhoiChieu,DoiTuong,ThoiLuong) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		cmd.setString(1, mv.getTenPhim());
        cmd.setString(2, mv.getPoster_url());
        cmd.setString(3, mv.getMoTaNgan());
        cmd.setString(4, mv.getMoTaDai());
        cmd.setString(5, mv.getDaoDien());
        cmd.setString(6, mv.getDienVien());
        cmd.setString(7, mv.getTrailer_url());
        cmd.setString(8, mv.getLoai());
        cmd.setString(9, mv.getNgayKhoiChieu());
        cmd.setString(10, mv.getDoiTuong());
        cmd.setInt(11, mv.getThoiLuong());
        int kq = cmd.executeUpdate();
        
		cmd.close();
		kn.cn.close();
		return kq >0 ;
	}
	public Boolean updateMovie(Movie mv) throws SQLException, ClassNotFoundException{
		
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "Update Movie set TenPhim =?, poster_url=?, MoTaNgan=?, MoTaDai=?, DaoDien=?, DienVien=?,trailer_url=?,Loai=?,trailer_url=?,NgayKhoiChieu=?,DoiTuong=?,ThoiLuong=?) where movie_id =?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		
		cmd.setString(1, mv.getTenPhim());
        cmd.setString(2, mv.getPoster_url());
        cmd.setString(3, mv.getMoTaNgan());
        cmd.setString(4, mv.getMoTaDai());
        cmd.setString(5, mv.getDaoDien());
        cmd.setString(6, mv.getDienVien());
        cmd.setString(7, mv.getTrailer_url());
        cmd.setString(8, mv.getLoai());
        cmd.setString(9, mv.getNgayKhoiChieu());
        cmd.setString(10, mv.getDoiTuong());
        cmd.setInt(11, mv.getThoiLuong());
        cmd.setInt(11, mv.getPhim_ID());
        int kq = cmd.executeUpdate();
        
		cmd.close();
		kn.cn.close();
		return kq >0 ;
	}
	public Boolean deleteMovie(int MovieID) throws SQLException, ClassNotFoundException{
		
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "delete from Movie where movie_id  = ? ";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		
		cmd.setInt(1, MovieID);
        
        int kq = cmd.executeUpdate();
        
		cmd.close();
		kn.cn.close();
		return kq >0 ;
	}
	
	
}
