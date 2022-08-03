package jdbctest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
	private static boolean validate(String EmailId,String password) {
		boolean check=false;
		try {Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "23@Swetha");
				PreparedStatement ps = connection.prepareStatement(
						"select * from Email where name=? and pass=?");
				ps.setString(1, EmailId);
				ps.setString(2, password);
				ResultSet rs=ps.executeQuery();
				check=rs.next();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return check;
		
	}

}

