package service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.TodoException;
import jakarta.validation.ConstraintViolationException;
import model.TodoDTO;
import repository.TodoRepo;

@Service
public class TodoServiceImp implements TodoService {
	
	@Autowired
	private TodoRepo repo;
	
	@Override
	public void createTodo(TodoDTO todo) throws TodoException, ConstraintViolationException {
			if(repo.findByTodo(todo.getTodo()).isPresent()){
				throw new TodoException(TodoException.TodoAlreadyExists());
			}
			else
				{
				todo.setCreatedAt(new Date(System.currentTimeMillis()));
				repo.save(todo);
				}
		} 
}
