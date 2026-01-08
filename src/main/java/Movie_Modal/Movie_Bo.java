package Movie_Modal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class Movie_Bo {
	static Movie_Dao PhimDao = new Movie_Dao();
	ArrayList<Movie> ds;
	public ArrayList<Movie> getPhim() throws ClassNotFoundException, SQLException{
		ds = PhimDao.getPhim();
	return ds;
	}
	public static ArrayList<Movie> Tim(String keyword) throws ClassNotFoundException, SQLException{
		
		ArrayList<Movie> temp1 = new ArrayList<Movie>();
		ArrayList<Movie> ds = PhimDao.getPhim();
		for(Movie phim :ds) {
			if(phim.getTenPhim().toLowerCase().trim().contains(keyword.toLowerCase().trim())) {
				temp1.add(phim);
			}
		}
		return temp1;
	}
	
	public List<String> getPhimMoi() throws ClassNotFoundException, SQLException{
		List<String> ds1 = new ArrayList<String>();
		ds1 = PhimDao.getPhimMoi();
	return ds1;
	}
	
	public Movie ChiTietphim(int movie_id) throws ClassNotFoundException, SQLException{
		return PhimDao.ChiTietphim(movie_id);
	}
	
	public boolean addMovie(Movie mv) throws ClassNotFoundException, SQLException {		
		
		return PhimDao.addMovie(mv);
		
	}
	public boolean update(Movie mv) throws ClassNotFoundException, SQLException {		
		
		return PhimDao.updateMovie(mv);
		
	}
	public boolean delete_Movie(int Movie_ID) throws ClassNotFoundException, SQLException {		
		
		return PhimDao.deleteMovie(Movie_ID);
		
	}
}
