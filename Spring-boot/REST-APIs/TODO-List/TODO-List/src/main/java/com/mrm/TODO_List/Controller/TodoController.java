package com.mrm.TODO_List.Controller;

import com.mrm.TODO_List.Model.Todo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TodoController{

    Map<Integer, Todo> todoData = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Todo>> viewAllTasks(){
        return ResponseEntity.ok(new ArrayList<>(todoData.values()));
    }

    @PostMapping
    public ResponseEntity<Todo> createTask(@RequestBody Todo todo){
        todoData.put(todo.getId(), todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Todo> updateStatus(@PathVariable int id, @RequestBody String status){
        Todo existing = todoData.get(id);
        if(existing==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existing.setStatus(status);
        todoData.put(id, existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteTask(@PathVariable int id){
        Todo existing = todoData.get(id);
        if(existing==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        todoData.remove(id, existing);
        return ResponseEntity.ok(existing);
    }
}
