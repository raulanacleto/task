package com.tasks.demo.service;

import com.tasks.demo.domain.Tarefa;
import com.tasks.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Page<Tarefa> findAll(Pageable pageable) {
        return tarefaRepository.findByOrderByDataCriacaoAsc(pageable);
    }

    public Tarefa findById(Long idTarefa) {
        return tarefaRepository.findById(idTarefa).orElse(null);
    }

    public Tarefa create(Tarefa tarefa) {
        tarefa.setDataCriacao(new Date());
        return tarefaRepository.save(tarefa);
    }

    public Tarefa update(Tarefa tarefa) {
        if (Objects.nonNull(tarefaRepository.findById(tarefa.getId()))) {
            tarefa.setDataAtualizacao(new Date());
            return tarefaRepository.save(tarefa);
        } else {
            return null;
        }
    }

    public Boolean deletar(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (Objects.nonNull(tarefa)) {
            tarefa.setDataExclusao(new Date());
            tarefaRepository.save(tarefa);
            return true;
        } else {
            return false;
        }
    }

}
