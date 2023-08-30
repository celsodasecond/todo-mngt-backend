package celso.todomanagement.controller;

import celso.todomanagement.dto.TodoDto;
import celso.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodoDto = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long todoId) {
        TodoDto foundTodoDto = todoService.getTodo(todoId);

        return ResponseEntity.ok(foundTodoDto);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo() {
        List<TodoDto> todoDtos = todoService.getAllTodo();

        return ResponseEntity.ok(todoDtos);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long todoId) {
        TodoDto updatedTodoDto = todoService.updateTodo(todoDto, todoId);

        return ResponseEntity.ok(updatedTodoDto);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);

        return ResponseEntity.ok("Todo has been successfully deleted.");
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoDto> triggerTodoComplete(@PathVariable Long todoId) {
        TodoDto todoDto = todoService.triggerCompleteTodo(todoId);

        return ResponseEntity.ok(todoDto);
    }
}
