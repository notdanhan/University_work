package assignment3_v2;

import java.io.IOException;
import java.util.HashMap;

import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@WebServlet("/deleteStudentServlet")
public class deleteStudent extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5827735726611401114L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,String> updateParams = ResTools.parseQueryString(request.getQueryString());
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory =  meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			int val = Integer.parseInt(updateParams.get("id"));
			Query query = session.createQuery("DELETE Student WHERE id=:idv");
			query.setParameter("idv", val);
			query.executeUpdate();
			t.commit();
			response.sendRedirect("viewStudents");
		} catch(Exception e) {
			e.printStackTrace();
			try {
				response.sendRedirect("error.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
