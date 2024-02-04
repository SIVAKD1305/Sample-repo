package controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import exception.TodoException;
import jakarta.validation.ConstraintViolationException;
import model.TodoDTO;
import repository.TodoRepo;
import service.TodoService;

@RestController
public class TodoController {
	
	@Autowired
	private TodoRepo repo;
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getTodo(){
		List<TodoDTO> todos = repo.findAll();
		if(todos.size()>0)
		return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
		else
			return new ResponseEntity<>("Nothing Todo...",HttpStatus.NOT_FOUND);
	}
	//Create 
	@PostMapping("/createTodos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todos) throws TodoException, ConstraintViolationException {
		try {
			todoService.createTodo(todos);
			return new ResponseEntity<>("Successfully Created",HttpStatus.OK);
		}
		catch(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
	//Read
	@GetMapping("/Gettodo/{id}")
	public ResponseEntity<?> getTodo(@PathVariable("id") String id){
		Optional<TodoDTO> todo = repo.findById(id);
		if(todo.isPresent())
			return new ResponseEntity<>(todo.get(),HttpStatus.OK);
		else
			return new ResponseEntity<>("Todo not found",HttpStatus.NOT_FOUND);
		
	}
	    //Update
		@PutMapping("/Updatetodo/{id}")
		public ResponseEntity<?> updateTodo(@PathVariable("id") String id,@RequestBody TodoDTO todo_n){
			Optional<TodoDTO> todo = repo.findById(id);
			if(todo.isPresent()) {
				TodoDTO update = todo.get();
				update.setCompleted(todo_n.getCompleted());
				update.setTodo(todo_n.getTodo() != null ? todo_n.getTodo() : update.getTodo());
				update.setDescription(todo_n.getDescription() != null ? todo_n.getDescription() : update.getDescription());
				update.setCreatedAt(todo_n.getCreatedAt() != null ? todo_n.getCreatedAt() : update.getCreatedAt());
				update.setCompletedAt(todo_n.getCompletedAt() != null ? todo_n.getCompletedAt() : update.getCompletedAt());
				repo.save(update);
				return new ResponseEntity<>("Todo with ID:-"+id+" Updated",HttpStatus.OK);
			}
			else
				return new ResponseEntity<>("Id not Found",HttpStatus.NOT_FOUND);
		}
		//Delete
		@DeleteMapping("/DeleteTodo/{id}")
		public ResponseEntity<?> deleteTodo(@PathVariable("id") String id){
			if(repo.findById(id).isPresent()) {
				repo.deleteById(id);
				return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(id+": Id Not Found",HttpStatus.NOT_FOUND);
		}
}
