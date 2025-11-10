package br.com.fiap.gs_ddd.controller;

import br.com.fiap.gs_ddd.model.dto.MatriculaDTO;
import br.com.fiap.gs_ddd.model.entity.Curso;
import br.com.fiap.gs_ddd.model.entity.Matricula;
import br.com.fiap.gs_ddd.model.entity.Usuario;
import br.com.fiap.gs_ddd.model.repository.CursoRepository;
import br.com.fiap.gs_ddd.model.repository.MatriculaRepository;
import br.com.fiap.gs_ddd.model.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("formMatricula");
        mv.addObject("usuarios", usuarioRepository.findAll());
        mv.addObject("cursos", cursoRepository.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView cadastrar(@Valid MatriculaDTO matriculaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("formMatricula");
        }

        Matricula matricula = new Matricula(matriculaDTO);
        Usuario usuario = usuarioRepository.findById(matriculaDTO.idUsuario()).orElse(null);
        Curso curso = cursoRepository.findById(matriculaDTO.idCurso()).orElse(null);
        matricula.setUsuario(usuario);
        matricula.setCurso(curso);

        matriculaRepository.save(matricula);

        return new ModelAndView("redirect:/matriculas");
    }

    @GetMapping
    public ModelAndView listar() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        ModelAndView mv = new ModelAndView("matriculas");
        mv.addObject("matriculas", matriculas);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        Matricula matricula = matriculaRepository.findById(id).orElse(null);
        ModelAndView mv = new ModelAndView("formMatricula");
        mv.addObject("matricula", matricula);
        mv.addObject("usuarios", usuarioRepository.findAll());
        mv.addObject("cursos", cursoRepository.findAll());
        mv.addObject("acao", "/matriculas/atualizar/" + id);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        matriculaRepository.deleteById(id);
        return new ModelAndView("redirect:/matriculas");
    }

    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable Long id, @Valid MatriculaDTO matriculaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("formMatricula");
        }

        Matricula matricula = new Matricula(matriculaDTO);
        Usuario usuario = usuarioRepository.findById(matriculaDTO.idUsuario()).orElse(null);
        Curso curso = cursoRepository.findById(matriculaDTO.idCurso()).orElse(null);
        matricula.setUsuario(usuario);
        matricula.setCurso(curso);
        matricula.setIdMatricula(id);

        matriculaRepository.save(matricula);

        return new ModelAndView("redirect:/matriculas");
    }
}
