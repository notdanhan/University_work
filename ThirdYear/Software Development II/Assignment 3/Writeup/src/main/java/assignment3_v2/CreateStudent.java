package assignment3_v2;

import java.io.IOException;
import java.util.HashMap;

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
import org.hibernate.query.Query;


@WebServlet("/createStudent")
public class CreateStudent extends HttpServlet {
	private static final long serialVersionUID = 123123123L;
	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			/*First we do this to save having to write some horrible splicing code later*/
			HashMap<String,String> paramMap = ResTools.parseQueryString(request.getQueryString());
			if(paramMap.size() == 0) {
				throw new NoDataException("All Fields Were Empty!");
			}
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
			SessionFactory factory =  meta.getSessionFactoryBuilder().build();
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("SELECT MAX(id) FROM Student");
			int Id = (int) query.uniqueResult() + 1;
			Student s = new Student();
			s.setId(Id);
			String fName = paramMap.get("firstName");
			if(fName == null || fName.length() == 0) {
				s.setFirstName(" ");
				fName="";
			} else {
				s.setFirstName(fName);
				//force lowercase on names
				fName = fName.replace(" ", "_").toLowerCase();
			}
			String lName = paramMap.get("lastName");
			if(lName==null || lName.length()==0) {
				s.setLastName(" ");
				lName="";
			} else {
				s.setLastName(lName);
				//Force lowercase on names
				lName = lName.replace(" ", "_").toLowerCase();
			}
			String email = fName+"."+lName+"@nuigalway.ie";
			Query query2 = session.createQuery("SELECT COUNT(*) FROM Student where emailAddress=:email");
			query2.setParameter("email", email);
			if((long)query2.uniqueResult() > 0) {
				int i = 1;
				while(true) {
					email = fName+"."+lName+i+"@nuigalway.ie";
					query2.setParameter("email", email);
					if((long)query2.uniqueResult() == 0) {
						break;
					}
					i++;
				}
			}
			s.setEmailAddress(email);
			session.save(s);
			t.commit();
			session.close();
			factory.close();
			response.sendRedirect("viewStudents?id="+Id);

		} catch(Exception e) {
			try {
				response.sendRedirect("error.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		
	}
}
