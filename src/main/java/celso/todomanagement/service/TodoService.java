package celso.todomanagement.service;

import celso.todomanagement.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long todoId);

    List<TodoDto> getAllTodo();

    TodoDto updateTodo(TodoDto updatedTodoDto, Long todoId);
}
