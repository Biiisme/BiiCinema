package KetNoi_Modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetNoi {
	public Connection cn;
	public void ketnoi() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url="jdbc:sqlserver://DESKTOP-3OB742L\\\\HYY:1433;databaseName=bi_cinema;user=sa; password=123";
		cn=DriverManager.getConnection(url);
		System.out.print("Connectted!!");
	}
}
