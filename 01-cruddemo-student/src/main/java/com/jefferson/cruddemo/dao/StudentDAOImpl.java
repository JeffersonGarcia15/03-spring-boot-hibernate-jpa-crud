package com.jefferson.cruddemo.dao;

import com.jefferson.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // gives supports for component scanning and translates JDBC exceptions
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection

    @Autowired // optional if only have one constructor
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional // since we are performing an update on the db
    public void save(Student student) {
        entityManager.persist(student); // save or update the student to the database.
    }

    @Override
    public Student findById(int id) {
        // Since this is READ ONLY, we don't need to use @Transactional
        return entityManager.find(Student.class, id); // find the student by id. Notice how we pass the entity class and the id.
    }

    @Override
    public List<Student> findAll() {
        // create query
//        TypedQuery<Student> students = entityManager.createQuery("FROM Student", Student.class); // Student is NOT the table name, it's the entity name.
        // default asc but can specify desc
        TypedQuery<Student> students = entityManager.createQuery("FROM Student", Student.class); // lastName because that's the JPA field, not the db column.


        // return query results
        return students.getResultList(); // method from the Query interface
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // create query
        TypedQuery<Student> students = entityManager.createQuery("FROM Student where lastName = :lastName", Student.class);
        // set query parameters
        students.setParameter("lastName", lastName);
        // return query parameters
        return students.getResultList();
    }

    @Override
    @Transactional // since we are performing an update on the db
    public void update(Student student) {
        entityManager.merge(student); // update the student to the database.
    }

    @Override
    @Transactional // since we are performing an update on the db
    public void delete(int id) {
        // retrieve the student by id
        Student student = entityManager.find(Student.class, id);
        // delete the student
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        // create query
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
