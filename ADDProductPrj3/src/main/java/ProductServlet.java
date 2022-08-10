import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// reading form values
		String name = request.getParameter("txtName");
		int price = Integer.parseInt(request.getParameter("txtPrice"));
		
		// put values in Object
		ProductAddInfo prod = new ProductAddInfo();
		prod.setName(name);

		prod.setPrice(price);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(prod);
		session.getTransaction().commit();

		session.close();

		PrintWriter out = response.getWriter();
		if (i > 0)
			out.println("Record inserted");
		else
			out.println("Record not inserted");

	}

}
