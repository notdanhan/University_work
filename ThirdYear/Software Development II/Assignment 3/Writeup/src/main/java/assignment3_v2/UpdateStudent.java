package assignment3_v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

@WebServlet("/updateStudentServlet")
public class UpdateStudent extends HttpServlet {

	private static final long serialVersionUID = 2141106540645771934L;

	@SuppressWarnings({"unchecked","rawtypes"})
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,String> updateParams = ResTools.parseQueryString(request.getQueryString());
		SessionFactory factory = null;
		Session session = null;
		Transaction t = null;
		try {
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
			factory =  meta.getSessionFactoryBuilder().build();
			session = factory.openSession();
			t = session.beginTransaction();
			if(updateParams.size() != 0) {
				if(updateParams.get("on") != null) {
					//Update Mode 1
					String param = updateParams.get("on");
					String queryString = "FROM Student WHERE " + param + "=:val";
					if(param.equals("id")) {
						try {
							Integer.valueOf(updateParams.get("param"));
						} catch(Exception e) {
							throw new Exception("Failed Update");
						}
					}
					Query query = session.createQuery(queryString);
					List<Student> students = null;
					if(param.equals("id")) {
						students = query.setParameter("val",Integer.valueOf(updateParams.get("param"))).list();
					} else {
						students = query.setParameter("val", updateParams.get("param")).list();
					}
					for(Student student: students) {
						if(updateParams.get("updatefname")!=null && updateParams.get("fname")!=null) {
							student.setFirstName(updateParams.get("fname"));
						}
						if(updateParams.get("updatelname")!=null && updateParams.get("lname") != null) {
							student.setLastName(updateParams.get("lname"));
						}
						if(updateParams.get("updateemail")!=null && updateParams.get("email") != null) {
							Query query2 = session.createQuery("FROM Student WHERE emailAddress:email");
							if(query2.setParameter("email", updateParams.get("email")).list().size() == 0) {
								student.setEmailAddress(updateParams.get("email"));
							}
							
						}
						session.update(student);
					}
					t.commit();
				} else if (updateParams.get("newemaildomain") != null) {
					//Update Mode 2
					//This has some fun part to it
					String newDomain = updateParams.get("newemaildomain").replace("@", "");
					if(!newDomain.contains(".")) {
						//make it .com if no domain has been attached
						newDomain+=".com";
					}
					List<Student> students = session.createQuery("FROM Student").list();
					
					Query query = session.createQuery("FROM Student WHERE emailAddress=:email");
					for(Student student: students) {
						try {
							String emailHead = student.getEmailAddress().split("@")[0];
							if(query.setParameter("email",(emailHead+"@"+newDomain)).list().size() != 0) {
								int i = 1;
								while(true) {
									//basically I gotta check if another email already has the new name and prevent duplicates
									List<Student> temp = query.setParameter("email",(emailHead+i+"@"+newDomain)).list();
									if(temp.size() == 0) {
										emailHead = emailHead+i+"@"+newDomain;
										break;
									} else if(temp.get(0).getId() == student.getId()) {
										//throw an error to start checking the next one without committing this one :)))
										throw new Exception("Skip this one :)");
									}
									i++;
								}
							} else {
								emailHead = emailHead+"@"+newDomain;
							}
							student.setEmailAddress(emailHead);
							session.update(student);
						} catch(Exception e) {
							continue;
						}
					}
					t.commit();
				} else {
					throw new Exception("Meaningless Data");
				}
			} else {
				throw new Exception("No Arguements");
			}
		} catch (Exception err) {
			if(t != null) t.rollback();
		} finally {
			if(session!= null) session.close();
			if(factory!=null) factory.close();
			try {
				response.sendRedirect("viewStudents");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
