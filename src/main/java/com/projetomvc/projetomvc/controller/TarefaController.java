package com.projetomvc.projetomvc.controller;

import java.util.Optional;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.projetomvc.projetomvc.model.Tarefa;
import com.projetomvc.projetomvc.repository.TarefaRepository;
import org.springframework.ui.Model;

@Controller
public class TarefaController {

    @Autowired // Injeção de dependência do repositório
    private TarefaRepository tarefaRepository;

    @GetMapping("/")
    public String listarTarefas(Model model, @RequestParam(defaultValue = "0") int page) {
        int tamanhoPagina = 5;
        Pageable configuracaoPagina = PageRequest.of(page, tamanhoPagina);
        Page<Tarefa> paginaTarefas = tarefaRepository.findAll(configuracaoPagina);
        model.addAttribute("paginaTarefas", paginaTarefas);
        model.addAttribute("novaTarefa", new Tarefa());
        return "index";
    }

    @PostMapping("/Adicionar")
    public String adicionarTarefa(@ModelAttribute Tarefa novaTarefa) {
        tarefaRepository.save(novaTarefa);
        return "redirect:/";
    }

    @GetMapping("/Excluir")
    public String excluirTarefa(@RequestParam Long id) {
        tarefaRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/AtualizarStatus")
    public String atualizarStatusTarefa(@RequestParam Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa != null) {
            tarefa.setConcluida(!tarefa.isConcluida());
            tarefaRepository.save(tarefa);
        }
        return "redirect:/";
    }

    @GetMapping("/Editar")
    public String editarTarefasPorId(@RequestParam("id") Long id, @RequestParam(defaultValue = "0") int page,
            Model model) {

        int tamanhoPagina = 5;
        Pageable configuracaoPagina = PageRequest.of(page, tamanhoPagina);
        Page<Tarefa> paginaTarefas = tarefaRepository.findAll(configuracaoPagina);
        model.addAttribute("paginaTarefas", paginaTarefas);
        Tarefa tarefaEditar = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        model.addAttribute("tarefaEditar", tarefaEditar);
        model.addAttribute("novaTarefa", new Tarefa());
            return "editar";
        }
    
        @PostMapping("/AtualizarTarefa")
        public String atualizarTarefa(@ModelAttribute Tarefa tarefaAtualizada) {
            tarefaRepository.save(tarefaAtualizada);
            return "redirect:/";
        }
    }

