package com.tasks.demo.repository;

import com.tasks.demo.domain.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Page<Tarefa> findByDescricao(Pageable pageable, String descricao);
    Page<Tarefa> findByOrderByDataCriacaoAsc(Pageable pageable);

}
