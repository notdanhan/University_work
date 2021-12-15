package assignment3_v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

@WebServlet("/viewStudents")
public class ViewStudents extends HttpServlet {
	private static final long serialVersionUID = 3331455088038033216L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> queryParams = ResTools.parseQueryString(request.getQueryString());
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory =  meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		if(queryParams.size() == 0) {
			/* Return Everything*/
			@SuppressWarnings("unchecked")
			List<Student> students = session.createQuery("FROM Student").list();
			request.setAttribute("Students", students);
		} else {
			//More Nuanced stuff goes here
			//Basic ID Query
			if(queryParams.get("id")!= null) {
				@SuppressWarnings("unchecked")
				Query<Student> query = session.createQuery("FROM Student WHERE id=:mid");
				List<Student> students = query.setParameter("mid", Integer.valueOf(queryParams.get("id"))).list();
				request.setAttribute("Students", students);
			}
		}
		session.close();
		factory.close();
		RequestDispatcher rd = request.getRequestDispatcher("viewStudentsTable.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
