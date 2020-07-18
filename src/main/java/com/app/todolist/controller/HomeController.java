package com.app.todolist.controller;

import com.app.todolist.model.Todo;
import com.app.todolist.model.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private TodoService todoservice;

    @Autowired
    public void setTodoservice(TodoService todoservice) {
        this.todoservice = todoservice;
    }

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("todo", todoservice.listTodo());
        return "home";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("todo", new Todo());
        return "create";
    }

    @RequestMapping(value = "/saves", method = RequestMethod.POST)
    public String simpan(Model model, Todo todo) {
        model.addAttribute("todo", todoservice.saveOrUpdate(todo));
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("todo", todoservice.getIdTodo(id));
        return "edit";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        todoservice.hapus(id);
        return "redirect:/";
    }
}