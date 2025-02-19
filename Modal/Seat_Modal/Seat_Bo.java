package Seat_Modal;

import java.sql.SQLException;
import java.util.ArrayList;





public class Seat_Bo {
	static Seat_Dao seatrDao = new Seat_Dao();
	static ArrayList<Seat> ds;
	public static ArrayList<Seat> getSeat(int PhongID,String HangGhe) throws ClassNotFoundException, SQLException{
		ds = seatrDao.getGhe(PhongID);
		ArrayList<Seat>  temp = new ArrayList<Seat>();
		for(Seat item : ds) {
		if(item.getTenGhe().toLowerCase().trim().contains(HangGhe.toLowerCase().trim())) {
			temp.add(item);
		}
		}		
	return temp;
	}
	
public boolean updateGhe(int maGhe) throws ClassNotFoundException, SQLException {		
		
		return seatrDao.updateGhe(maGhe);
		
	}
}

