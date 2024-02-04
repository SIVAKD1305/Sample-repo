package service;

import exception.TodoException;
import jakarta.validation.ConstraintViolationException;
import model.TodoDTO;

public interface TodoService {
	public void createTodo(TodoDTO todo) throws TodoException,ConstraintViolationException;
}
