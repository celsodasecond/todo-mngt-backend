package celso.todomanagement.service;

import celso.todomanagement.dto.TodoDto;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long todoId);
}
