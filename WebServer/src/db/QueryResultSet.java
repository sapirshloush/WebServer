package db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class QueryResultSet
{
	private SessionFactory sf;
	private Session session;
	private Query<PersPlayer> persPlayerQuery;
	private Query SolutionQuery;
	private Transaction trans;
	private List<PersPlayer> persPlayerResultSet;
	private List SolutionResultSet;

	
	public QueryResultSet()
	{
		persPlayerResultSet = new ArrayList<>();
	}

	public void addNewSolutionEntry(SolutionTable st)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			trans = session.beginTransaction();
			session.save(st);
			trans.commit();

			Alert message = new Alert(AlertType.INFORMATION);
			message.setHeaderText(null);
			message.setTitle("Solutions Table");
			message.setContentText("Solution was added successfully!");
			message.showAndWait();
		}
		catch (HibernateException e)
		{
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}


	public void getSolutionByLevelName(String name)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			SolutionQuery = session.createQuery("from Solutions where levelName = :lvlName");
			SolutionQuery.setParameter("lvlName", name);
			SolutionResultSet = SolutionQuery.list();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void getEntriesBySteps(int steps)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			persPlayerQuery = session.createQuery("from World_Records where steps = :steps");
			persPlayerQuery.setParameter("steps", steps);
			persPlayerResultSet = persPlayerQuery.list();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getEntriesBySeconds(int seconds)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			persPlayerQuery = session.createQuery("from World_Records where seconds = :sec");
			persPlayerQuery.setParameter("sec", seconds);
			persPlayerResultSet = persPlayerQuery.list();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	public void addNewEntry(PersPlayer player)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			trans = session.beginTransaction();
			session.save(player);
			trans.commit();

			Alert message = new Alert(AlertType.INFORMATION);
			message.setHeaderText(null);
			message.setTitle("World Record Database Log");
			message.setContentText("Entry was added successfully!");
			message.showAndWait();
		}
		catch (HibernateException e)
		{
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		} 
	}
	
	@SuppressWarnings("unchecked")
	public void getAllEntries()
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			persPlayerQuery = session.createQuery("from World_Records");
			persPlayerResultSet = persPlayerQuery.list();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	public void updateSteps(String username, int newStepsValue)
	{
		try
		{
			session = sf.openSession();
			trans = session.beginTransaction();
			
			PersPlayer p = session.get(PersPlayer.class, username);
			p.setSteps(newStepsValue);
			session.update(p);
			trans.commit();
		}
		catch (HibernateException e)
		{
			trans.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	public void updateSeconds(String username, int newSecondsValue)
	{
		try
		{
			session = sf.openSession();
			trans = session.beginTransaction();
			
			PersPlayer p = session.get(PersPlayer.class, username);
			p.setSeconds(newSecondsValue);
			session.update(p);
			trans.commit();
		}
		catch (HibernateException e)
		{
			trans.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getEntriesByLevel(String level)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			persPlayerQuery = session.createQuery("from World_Records where levelName = :level");
			persPlayerQuery.setParameter("level", level);
			persPlayerResultSet = persPlayerQuery.list();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getEntriesByUsername(String username)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			persPlayerQuery = session.createQuery("from World_Records where username = :username");
			persPlayerQuery.setParameter("username", username);
			persPlayerResultSet = persPlayerQuery.list();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	

	public List<PersPlayer> getPersPlayerResultSet()
	{
		return persPlayerResultSet;
	}

	public void setPersPlayerResultSet(List<PersPlayer> persPlayerResultSet)
	{
		this.persPlayerResultSet = persPlayerResultSet;
	}
	
	
}
