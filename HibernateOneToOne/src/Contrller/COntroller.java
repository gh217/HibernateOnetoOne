/*
 * in this case employee may have pc or no
 */


package Contrller;

import javax.persistence.Query;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

import hibernate.model.Employee;
import hibernate.model.Pc;

public class COntroller {

	static SessionFactory factory=new Configuration()
			.configure("Config.xml")
			.addAnnotatedClass(Employee.class)
			.addAnnotatedClass(Pc.class)
			.buildSessionFactory();
	
	static Session session=factory.getCurrentSession();
	public static void main(String[] args) {
		
		
		
		try {
		
			session.beginTransaction();
			
		
//			System.out.println(addEmployeePc());
			System.out.println(deleteEmployee());
			
			session.getTransaction().commit();
		} catch (Exception e) {
		
			System.out.println(e.getMessage());
		}
		
		
}
	
	// if have employee Pc or not will handle all
	// to remove employee without remove pc 
	static boolean deleteEmployee() {
		
		
		Employee emp=new Employee();
		emp.setId(9);
		
		Employee resEm=session.get(Employee.class, emp.getId());
		if(resEm==null)return false;
	
		
		if(resEm.getPc()!=null) {
			Pc pc = new Pc();
			pc.setId(20);
			Pc resPc=session.get(Pc.class, pc.getId());
			resPc.setEmployee(null); 
			resEm.setPc(null);
		}
	
		session.delete(resEm);
		
		return true;
		
	}
	
	static boolean deletePc(int id) {
		String hql = "DELETE FROM Pc "  + 
	             "WHERE id = :pcId";
		Query query = session.createQuery(hql);
		query.setParameter("pcId", id);
		
		int result = query.executeUpdate();
		
		if(result==0)return false;
		
		System.out.println("Rows affected: " + result);
		return true;
	}

	
	static boolean addEmployeePc() {
		try {
			
			Employee emp=new Employee();
			Pc pc = new Pc();
			
			emp.setAge(21);
			emp.setName("mohamed eid");
			session.save(emp);
			
			pc.setModelName("Hp");
	
			pc.setEmployee(emp); 
			session.save(pc);
			
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	static boolean addPc() {
		try {
		
			Pc pc = new Pc();
			pc.setModelName("Lenovo");
			session.save(pc);
			
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	static boolean addEmployee() {
		try {
			
			Employee emp=new Employee();
			Pc pc = new Pc();
		
			emp.setAge(20);
			emp.setName("mo mohamed");
			session.save(emp);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	static boolean updatepcIdEmployee() {
		try {
		
			Employee emp=new Employee();
			emp.setId(3);
			
			Pc pc = new Pc();
			pc.setId(11);
			
			
			Employee resEm=session.get(Employee.class, emp.getId());
			Pc resPc=session.get(Pc.class, pc.getId());
			
			if(resEm==null||pc==null||resPc.getEmployee()!=null) 
				 return false;
			
			resPc.setEmployee(resEm);
			session.update(resPc);
			
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
}

