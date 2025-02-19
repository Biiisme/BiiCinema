package User_Modal;

import java.sql.SQLException;
import java.util.ArrayList;





public class UserBo {
	static UserDao userDao = new UserDao();
	static ArrayList<User> ds;
	public ArrayList<User> getUser() throws ClassNotFoundException, SQLException{
		ds = userDao.getUser();
	return ds;
	}
	public static ArrayList<User> Tim(String keyword) throws ClassNotFoundException, SQLException{
		
		ArrayList<User> temp1 = new ArrayList<User>();
		 ds = userDao.getUser();
		for(User user :ds) {
			if(user.getHoten().toLowerCase().trim().contains(keyword.toLowerCase().trim())) {
				temp1.add(user);
			}
		}
		return temp1;
	}
	public static User CheckLogin(String Tendn ,String mk) throws ClassNotFoundException, SQLException{
		
		return userDao.CheckLogin(Tendn, mk);
	}
	public boolean addUser(User mv) throws ClassNotFoundException, SQLException {		
		
		return userDao.addUser(mv);
		
	}
	public boolean deleteUser(int userID) throws ClassNotFoundException, SQLException {		
		
		return userDao.deleteUser(userID);
		
	}
	public boolean updateUser(User mv) throws ClassNotFoundException, SQLException {		
		
		return userDao.updateUser(mv);
		
	}
}
