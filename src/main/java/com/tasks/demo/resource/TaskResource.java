package com.tasks.demo.resource;

import com.tasks.demo.domain.Task;
import com.tasks.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aplicacao/task")
//@Api(value = "Produto Web", tags = "Api Web")
public class TaskResource {

    private final TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    //@ApiOperation(value = "Listar todos os produtos que estão marcados para exportar", nickname = "listar", notes = "Traz todos os produtos paginados que não estão marcados como excluido e estão marcados como site e ativo")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Task>> listarTodos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll(pageable));
    }

}
