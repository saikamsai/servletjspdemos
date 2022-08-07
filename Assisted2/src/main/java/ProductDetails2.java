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


@WebServlet("/list5")
public class ProductDetails2 extends HttpServlet {
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
                
        	try (Statement stmt = connection.createStatement();) {
                PrintWriter out = response.getWriter();
                         out.println("<html><body>");
                         CallableStatement stmt1 = connection.prepareCall("{call add_product(?, ?)}");
                         stmt1.setString(1, "new product");
                         stmt1.setBigDecimal(2, new BigDecimal(1900.50));
                         stmt1.executeUpdate();
                         
                         out.println("Stored procedure has been executed.<Br>");
                         stmt.close();

                        out.println("</body></html>");
                        connection.close();
                        
                }catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                doGet(request, response);
        }

}
