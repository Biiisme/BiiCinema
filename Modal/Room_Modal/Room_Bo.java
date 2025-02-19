package Room_Modal;

import java.sql.SQLException;
import java.util.ArrayList;



public class Room_Bo {
	static Room_Dao RoomDao = new Room_Dao();
	ArrayList<Room> ds;
	public ArrayList<Room> getPhong() throws ClassNotFoundException, SQLException{
		ds = RoomDao.getPhong();
	return ds;
	}
	
	public String getPhongtheoID(int roomID) throws ClassNotFoundException, SQLException{
		ds = RoomDao.getPhong();
		String temp ="";
		for(Room item : ds) {
			if(item.getRoom_id() == roomID)
			{
				temp = item.getTenphong();
			}
		}
	return temp;
	}
}
