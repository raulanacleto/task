package com.tasks.demo.resource;

import com.tasks.demo.domain.Tarefa;
import com.tasks.demo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("aplicacao/tarefa")
public class TarefaResource {

    private final TarefaService tarefaService;

    @Autowired
    public TarefaResource(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Tarefa>> listarTodos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.findAll(pageable));
    }

    @RequestMapping(path = "/{idTarefa}", method = RequestMethod.GET)
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable("idTarefa") Long idTarefa) {
        Tarefa tarefa = tarefaService.findById(idTarefa);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @RequestMapping(path = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<Tarefa> cadastrar(@Valid @RequestBody Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.create(tarefa));
    }

    @RequestMapping(value = "/atualizar", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@RequestBody Tarefa task) {
        Tarefa tarefa = tarefaService.update(task);

        if (Objects.nonNull(tarefa)) {
            return ResponseEntity.status(HttpStatus.OK).body(tarefa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }
    }

    @RequestMapping(value = "deletar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {

        Boolean deletou = tarefaService.deletar(id);

        if (deletou) {
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }
    }

}
