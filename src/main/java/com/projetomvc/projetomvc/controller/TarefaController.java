package com.projetomvc.projetomvc.controller;


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
}
