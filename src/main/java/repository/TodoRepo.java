package repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import model.TodoDTO;
@Repository
public interface TodoRepo extends MongoRepository<TodoDTO, String>{

	@Query("{'todo': ?0}")
	Optional<?> findByTodo(String todo);	
}
