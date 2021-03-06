package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import eredua.HibernateUtil;
import eredua.domeinua.Event;
import eredua.domeinua.Question;
import eredua.domeinua.User;
import exceptions.QuestionAlreadyExist;

public class HibernateDataAccess implements DataAccessInterface {

	@Override
	public void emptyDatabase() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeDB() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Calendar today = Calendar.getInstance();

		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);
		if (month==12) { month=0; year+=1;}
		
		Event ev1=new Event("Atlético-Athletic", UtilDate.newDate(year,month,17));
		Event ev2=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,17));
		Event ev3=new Event("Getafe-Celta", UtilDate.newDate(year,month,17));
		Event ev4=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,17));
		Event ev5=new Event("Español-Villareal", UtilDate.newDate(year,month,17));
		Event ev6=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
		Event ev7=new Event("Malaga-Valencia", UtilDate.newDate(year,month,17));
		Event ev8=new Event("Girona-Leganés", UtilDate.newDate(year,month,17));
		Event ev9=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,17));
		Event ev10=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,17));

		Event ev11=new Event("Atletico-Athletic", UtilDate.newDate(year,month,1));
		Event ev12=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,1));
		Event ev13=new Event("Getafe-Celta", UtilDate.newDate(year,month,1));
		Event ev14=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,1));
		Event ev15=new Event("Español-Villareal", UtilDate.newDate(year,month,1));
		Event ev16=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
		
		Event ev17=new Event("Málaga-Valencia", UtilDate.newDate(year,month,28));
		Event ev18=new Event("Girona-Leganés", UtilDate.newDate(year,month,28));
		Event ev19=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,28));
		Event ev20=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,28));
		
		Question q1;
		Question q2;
		Question q3;
		Question q4;
		Question q5;
		Question q6;
		
		q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
		q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
		q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
		q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
		q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
		q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
		
		session.persist(ev1);
		session.persist(ev2);
		session.persist(ev3);
		session.persist(ev4);
		session.persist(ev5);
		session.persist(ev6);
		session.persist(ev7);
		session.persist(ev8);
		session.persist(ev9);
		session.persist(ev10);
		session.persist(ev11);
		session.persist(ev12);
		session.persist(ev13);
		session.persist(ev14);
		session.persist(ev15);
		session.persist(ev16);
		session.persist(ev17);
		session.persist(ev18);
		session.persist(ev19);
		session.persist(ev20);	
		
		session.persist(q1);
		session.persist(q2);
		session.persist(q3);
		session.persist(q4);
		session.persist(q5);
		session.persist(q6);

		session.getTransaction().commit();
	}

	@Override
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		int i = event.getEventNumber();
		Query q = session.createQuery("from Event e where e.eventNumber= :eventNumber");
		q.setParameter("eventNumber", i);
		List result = q.list();
		Event e = (Event) result.get(0);
		Question gal = e.addQuestion(question, betMinimum);
		session.persist(gal);
		session.getTransaction().commit();
		System.out.println("Galdera ondo sortu da");
		return gal;
	}

	@Override
	public List<Event> getEvents(Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Event e where e.eventDate= :date");
		q.setParameter("date", date);
		List result = q.list();
		session.getTransaction().commit();
		return result;
	}

	@Override
	public Vector<Date> getEventsMonth(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existQuestion(Event event, String question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void erregistratu(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.persist(user);
		session.getTransaction().commit();	
		System.out.println("Erabiltzailea ondo erregistratu da");
	}

	@Override
	public User logeatu(String eposta, String pasahitza) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from User u where u.eposta= :eposta");
		q.setParameter("eposta", eposta);
		List result = q.list();
		try {
			User u = (User)result.get(0);
			if(u.getPasahitza().equals(pasahitza)) {
				return u;
			}
		}catch(Exception e) {
			
		}
		session.getTransaction().commit();
		
		return null;
	}

	@Override
	public List<Question> getAllQuestions() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Question");
		List result = q.list();
		session.getTransaction().commit();
		return result;
	} 
	
}
