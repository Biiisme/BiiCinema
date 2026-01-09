package History_Modal;

import java.sql.SQLException;
import java.util.ArrayList;
import History_Modal.GroupedHistory;



public class history_Bo {
	static history_Dao PhimDao = new history_Dao();
	ArrayList<history> ds;
	public ArrayList<history> gethistory(int makh) throws ClassNotFoundException, SQLException{
		ds = PhimDao.gethistory(makh);
	return ds;
	}

	public ArrayList<GroupedHistory> getGroupedHistory(int makh) throws ClassNotFoundException, SQLException{
		return PhimDao.getGroupedHistory(makh);
	}
}
