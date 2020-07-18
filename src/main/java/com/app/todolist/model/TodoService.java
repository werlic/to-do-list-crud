package com.app.todolist.model;

import java.util.List;

public interface TodoService {
    List<Todo> listTodo();

    Todo saveOrUpdate(Todo todo);

    Todo getIdTodo(int id);

    void hapus(int id);
}