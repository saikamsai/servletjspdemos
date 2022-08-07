import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/list3")
public class ProductDetalis3 extends HttpServlet {
	Connection connection;

	@Override
	public void init(ServletConfig config) throws ServletException {

		try {
			ServletContext context = config.getServletContext();
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(context.getInitParameter("dburl"),
					context.getInitParameter("dbuser"), context.getInitParameter("dbpassword"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	response.setContentType("text/html");
        	try (Statement stmt = connection.createStatement();) {
                PrintWriter out = response.getWriter();    
    		stmt.executeUpdate("insert into eproduct values (16,'New Product8', 1500.00, now())");                        out.println("Executed an insert operation<br>");
                        
                        stmt.executeUpdate("update eproduct set price=12300 where name = 'New Product'");
                        out.println("Executed an update operation<br>");
                        
                        stmt.executeUpdate("delete from eproduct where name = 'New Product'");
                        out.println("Executed a delete operation<br>");
                        
                        stmt.close();
                        
                        connection.close();
                        
                        
                        out.println("</body></html>");
                        
                }  catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                doGet(request, response);
        }

}
