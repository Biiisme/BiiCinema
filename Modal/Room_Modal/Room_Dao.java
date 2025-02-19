package Room_Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import KetNoi_Modal.KetNoi;

public class Room_Dao {
	public ArrayList<Room>getPhong() throws ClassNotFoundException, SQLException{
		ArrayList<Room> ds = new ArrayList<Room>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from Phong";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs =cmd.executeQuery();
		while(rs.next()) {
			
			ds.add(new Room( rs.getInt("Phong_ID"),rs.getString("TenPhong")));
		}
		rs.close();
		kn.cn.close();
		return ds;
		
	} 
}
