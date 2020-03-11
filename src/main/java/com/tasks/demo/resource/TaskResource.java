package com.tasks.demo.resource;

import com.tasks.demo.domain.Task;
import com.tasks.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("aplicacao/task")
public class TaskResource {

    private final TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Task>> listarTodos(Pageable pageable) {
        System.out.println("get");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll(pageable));
    }

    @RequestMapping(path = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<Task> cadastrar(@Valid @RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.create(task));
    }

    @RequestMapping(value = "atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody Task task) {
        Task tarefa = taskService.update(id, task);

        if (Objects.nonNull(tarefa)) {
            return ResponseEntity.status(HttpStatus.OK).body(tarefa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }
    }

    @RequestMapping(value = "atualizar-status/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> atualizarStatus(@PathVariable("id") Long id, @RequestBody Task task) {
        Task tarefa = taskService.alterarStatus(id, task);

        if (Objects.nonNull(tarefa)) {
            return ResponseEntity.status(HttpStatus.OK).body(tarefa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }
    }

    @RequestMapping(value = "deletar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {

        Boolean deletou = taskService.deletar(id);

        if (deletou) {
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }
    }



}
