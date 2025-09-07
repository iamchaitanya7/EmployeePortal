package com.ftpl.EmployeePortal.dao;

import com.ftpl.EmployeePortal.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int saveEmployee (Employee employee) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                Employee dbEmployee = session.get(Employee.class, employee.getId());
                if (dbEmployee == null) {
                    session.save(employee);
                    transaction.commit();
                    return 1; // Success: Student saved
                } else {
                    return 2; // Error: Student already exists
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 3; // Error: Exception occurred
            }
        }

    @Override
    public List<Employee> getAllEmployees () {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Employee", Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<> ();
        }
    }

    @Override
    public boolean updateEmployee (Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Fetch the existing Employee from the database
            Employee existingEmp = session.get(Employee.class, employee.getId());

            if (existingEmp != null) {
                session.evict(existingEmp);
                session.update(employee);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById (int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
