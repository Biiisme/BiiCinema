package Schedule_Modal;

import java.sql.SQLException;
import java.util.ArrayList;

public class Schedule_Bo {
	static Schedule_Dao scheDAO =new Schedule_Dao();
	
	public ArrayList<Schedule> getLichChieu_PhongID(String maPhong) throws NumberFormatException, ClassNotFoundException, SQLException{
		ArrayList<Schedule>ds =new ArrayList<Schedule>();
		ds = scheDAO.getSchedule_PhongID(Integer.parseInt(maPhong));
		
		return ds;
	}
}
