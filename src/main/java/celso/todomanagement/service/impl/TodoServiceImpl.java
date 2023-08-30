package celso.todomanagement.service.impl;

import celso.todomanagement.dto.TodoDto;
import celso.todomanagement.entity.Todo;
import celso.todomanagement.repository.TodoRepository;
import celso.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // This single line converts TodoDTO to TodoEntity Object.
        Todo todo = modelMapper.map(todoDto, Todo.class);

        Todo savedTodo = todoRepository.save(todo);

        todoDto = modelMapper.map(savedTodo, TodoDto.class);

        return todoDto;
    }
}
