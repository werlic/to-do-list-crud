package com.app.todolist.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoDb implements TodoService {
    
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Todo> listTodo() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Todo", Todo.class).getResultList();
    }

    @Override
    public Todo saveOrUpdate(Todo todo) {
        // TODO Auto-generated method stub
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Todo saved = em.merge(todo);
        em.getTransaction().commit();
        return saved;
    }

    @Override
    public Todo getIdTodo(int id) {
        // TODO Auto-generated method stub
        EntityManager em = emf.createEntityManager();
        return em.find(Todo.class, id);
    }

    @Override
    public void hapus(int id) {
        // TODO Auto-generated method stub
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Todo.class, id));
        em.getTransaction().commit();
    }
}