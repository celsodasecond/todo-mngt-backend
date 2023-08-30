package celso.todomanagement.service.impl;

import celso.todomanagement.dto.TodoDto;
import celso.todomanagement.entity.Todo;
import celso.todomanagement.exception.ResourceNotFoundException;
import celso.todomanagement.repository.TodoRepository;
import celso.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public TodoDto getTodo(Long todoId) {
        Todo todo = todoRepository
                .findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();

        List<TodoDto> todoDtos = todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).toList();

        return todoDtos;
    }

    @Override
    public TodoDto updateTodo(TodoDto updatedTodoDto, Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not Found in id: " + todoId));

        if (updatedTodoDto.getTitle() != null) savedTodo.setTitle(updatedTodoDto.getTitle());
        if (updatedTodoDto.getDescription() != null) savedTodo.setDescription(updatedTodoDto.getDescription());
        if (updatedTodoDto.getCompleted() != null) savedTodo.setCompleted(updatedTodoDto.getCompleted());

        Todo updatedTodo = todoRepository.save(savedTodo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
