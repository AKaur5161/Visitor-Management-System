
package com.infodart.dao;

import java.util.Date;

import java.util.List;
import javax.persistence.EntityManager;
import com.infodart.Mail.SendMail;
import com.infodart.Mail.SendMailOTP;
import com.infodart.entity.Employee;
import com.infodart.entity.Login;
import com.infodart.entity.Visitor;
import com.infodart.util.Myfactory;

public class BaseDaoImpl implements BaseDao {

	EntityManager entity = Myfactory.getEntityManager();
	static String getrole = null;
	static String getname = null;
	static int id = 0;
	static int lastid = 0;
	static int otp = 0;

	public boolean login(String username, String password) {
		EntityManager entity = Myfactory.getEntityManager();

		if (entity != null) {
			try {

				String hql = "from com.infodart.entity.Login  a where a.Username =:Username AND a.Password =:Password ";
				@SuppressWarnings("unchecked")
				List<Login> result = entity.createQuery(hql).setParameter("Username", username)
						.setParameter("Password", password).getResultList();

				Login user = result.get(0);

				getrole = user.getRole();
				getname = user.getName();
				if (user != null && user.getUsername().equalsIgnoreCase(username)
						&& user.getPassword().equalsIgnoreCase(password)) {
					id = user.getID();
					System.out.println("Admin username and password is true");
					return true;
				}
			} catch (Exception exception) {
				System.out.println("Exception occurred while reading user data: " + exception.getMessage());
				return false;
			}

		}
		return false;
	}

	@Override
	public Login register(Login staff) {
		EntityManager entityManager = Myfactory.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(staff);
		entityManager.getTransaction().commit();
		entityManager.close();
		return staff;
	}

	@Override
	public Visitor registerVisitor(Visitor visitor) {
		EntityManager entityManager = Myfactory.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(visitor);
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
		lastid = visitor.getID();
		System.out.println(lastid);
		return visitor;

	}

	@Override
	public boolean ResetPassword(String Username, String newpass, String confirmpass) {
		EntityManager entity = Myfactory.getEntityManager();

		if (entity != null)
			try {

				String hql = "from com.infodart.entity.Login  s where s.Username =:Username AND s.Password =:Password";
				@SuppressWarnings({ "unchecked", "unused" })
				List<Login> result = entity.createQuery(hql).setParameter("Username", Username)
						.setParameter("Password", newpass).getResultList();

				if (Username != null && newpass.equalsIgnoreCase(confirmpass)) {
					entity.getTransaction().begin();
					entity.createQuery(
							"UPDATE Login SET Password = '" + newpass + "' where Username='" + Username + "'")
							.executeUpdate();

					entity.getTransaction().commit();
					System.out.println("Password changed");
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Password not changed due to" + e.getMessage());
				return false;
			}
		return false;
	}

	public String getRole() {

		return getrole;
	}

	public String getName() {

		return getname;
	}

	public int getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	public Visitor getVisitorDetails() {
		EntityManager entity = Myfactory.getEntityManager();
		int vid = lastid;
		System.out.println(vid);
		String hql = "from com.infodart.entity.Visitor v where v.ID = :lastid ";
		List<Visitor> result = entity.createQuery(hql).setParameter("lastid", lastid).getResultList();

		Visitor visit = result.get(0);
		String name = visit.getName();
		String purpose = visit.getPurpose();
		Date date = visit.getDate();

		System.out.println(name + "\t\t\t\t" + purpose + "\t\t\t\t" + date);
		return visit;
	}

	public int getlastId() {

		System.out.println("lastid of visitor is :" + lastid);
		return lastid;
	}

	@Override
	public void sendMail() {
		try {

			EntityManager entity = Myfactory.getEntityManager();
			BaseDao base = new BaseDaoImpl();
			int lastid = base.getlastId();
			String hql = "from com.infodart.entity.Visitor v where v.ID = :lastid ";
			@SuppressWarnings("unchecked")
			List<Visitor> result = entity.createQuery(hql).setParameter("lastid", lastid).getResultList();

			Visitor visit = result.get(0);

			String subject = "New Visitor ";
			int id = visit.getID();
			String name = visit.getName();
			String ContactPersonName = visit.getContactPersonName();
			String purpose = visit.getPurpose();

			Date date = visit.getDate();
			String text = "Visitor with  Visitor Id-" + '\t' + id + '\r' + '\n' + "Name-" + '\t' + name + "\r\n"
					+ "is  visiting the Organization for the pupose of" + '\t' + purpose + '\n'
					+ "has a meeting with Employee \n" + ContactPersonName + '\n' + "on \t  \n" + "date" + "\n" + date;

			String from = "methali590@gmail.com";
			String username = "methali590";
			String password = "AmanKaur2546";
			String mail = "amanpreet.k@infodartmail.com";

			SendMail.mail(from, username, password, mail, subject, text);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean VisitorCheckout(int id) {
		EntityManager entityManager = Myfactory.getEntityManager();

		if (entity != null)
			try {

				String hql = "from com.infodart.entity.Visitor v where v.ID =:id AND v.CheckOuttimestamp is null ";

				@SuppressWarnings("unchecked")
				List<Visitor> result = entityManager.createQuery(hql).setParameter("id", id).getResultList();
				if (id != 0) {
					entityManager.getTransaction().begin();

					Visitor visitor = result.get(result.size() - 1);
					visitor.setCheckOuttimestamp(new Date(System.currentTimeMillis()));
					entityManager.getTransaction().commit();
					entityManager.close();
					System.out.println("Checkout time added successfully");

					return true;
				}

			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				System.out.println("Checkout time not changed due to" + e.getMessage());
				System.out.println("Checkout time Unsuccessful!");

				return false;
			}
		return false;
	}

	@Override
	public boolean verifyEmployee(String Name, String Designation) {

		EntityManager entity = Myfactory.getEntityManager();

		if (entity != null) {
			try {

				String hql = "from com.infodart.entity.Employee  a where a.Name =:Name AND a.Designation =:Designation ";
				@SuppressWarnings("unchecked")
				List<Employee> result = entity.createQuery(hql).setParameter("Name", Name)
						.setParameter("Designation", Designation).getResultList();

				Employee emp = result.get(0);
				if (emp != null && emp.getName().equalsIgnoreCase(Name)
						&& emp.getDesignation().equalsIgnoreCase(Designation)) {
					System.out.println("Valid Employee Details");
					return true;
				}

			} catch (Exception e) {
				System.out.println("Employee not verify  " + e.getMessage());
				return false;
			}

		}
		return false;

	}

	@Override
	public void sendMailOTP(String mail) {
		try {

			
			String subject = "OTP ";
            otp = ((int) (Math.random() * 9000) + 1000);
			String text = "OTP :" + otp;
			String from = "methali590@gmail.com";
			String username = "methali590";
			String password = "AmanKaur2546";

			SendMailOTP.mail(from, username, password, mail, subject, text);
			System.out.println(otp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean verifyotp(int receiveotp) {
		
			
				if (otp==receiveotp) 
				{
					System.out.println("OTP is true");
					return true;
				}
			
		
	
		
		return false;		
		
}
}
