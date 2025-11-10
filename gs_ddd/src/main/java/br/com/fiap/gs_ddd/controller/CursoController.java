package br.com.fiap.gs_ddd.controller;

import br.com.fiap.gs_ddd.model.dto.CursoDTO;
import br.com.fiap.gs_ddd.model.entity.Curso;
import br.com.fiap.gs_ddd.model.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/novo")
    public ModelAndView novo() {
        return new ModelAndView("formCurso");
    }

    @PostMapping
    public ModelAndView cadastrar(@Valid CursoDTO cursoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("formCurso");
        }

        Curso curso = new Curso(cursoDTO);
        cursoRepository.save(curso);

        return new ModelAndView("redirect:/cursos");
    }

    @GetMapping
    public ModelAndView listar() {
        List<Curso> cursos = cursoRepository.findAll();
        ModelAndView mv = new ModelAndView("cursos");
        mv.addObject("cursos", cursos);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        ModelAndView mv = new ModelAndView("formCurso");
        mv.addObject("curso", curso);
        mv.addObject("acao", "/cursos/atualizar/" + id);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        cursoRepository.deleteById(id);
        return new ModelAndView("redirect:/cursos");
    }

    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable Long id, @Valid CursoDTO cursoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("formCurso");
        }

        Curso curso = new Curso(cursoDTO);
        curso.setIdCurso(id);
        cursoRepository.save(curso);

        return new ModelAndView("redirect:/cursos");
    }
}
