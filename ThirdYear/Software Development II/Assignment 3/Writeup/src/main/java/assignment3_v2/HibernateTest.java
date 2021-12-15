package assignment3_v2;

import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;  

public class HibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		
		SessionFactory factory =  meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Student s = new Student();
		s.setId(3);
		s.setFirstName("John");
		s.setLastName("Sdmple");
		s.setEmailAddress("john.sdmple@nuigalway.ie");
		session.save(s);
		t.commit();  
	    System.out.println("successfully saved");    
	    factory.close();  
	    session.close();    
	}

}
