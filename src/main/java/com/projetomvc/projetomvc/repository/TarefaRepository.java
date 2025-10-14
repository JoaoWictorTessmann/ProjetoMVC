package com.projetomvc.projetomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetomvc.projetomvc.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository <Tarefa, Long> {
    
}