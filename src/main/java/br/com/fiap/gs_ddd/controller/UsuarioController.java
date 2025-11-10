package br.com.fiap.gs_ddd.controller;

import br.com.fiap.gs_ddd.model.dto.UsuarioDTO;
import br.com.fiap.gs_ddd.model.entity.Usuario;
import br.com.fiap.gs_ddd.model.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/novo")
    public ModelAndView novo() {
        return new ModelAndView("formUsuario");
    }

    @PostMapping
    public ModelAndView cadastrar(@Valid UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("formUsuario");
        }

        Usuario usuario = new Usuario(usuarioDTO);
        usuarioRepository.save(usuario);

        return new ModelAndView("redirect:/usuarios");
    }

    @GetMapping
    public ModelAndView listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        ModelAndView mv = new ModelAndView("usuarios");
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        ModelAndView mv = new ModelAndView("formUsuario");
        mv.addObject("usuario", usuario);
        mv.addObject("acao", "/usuarios/atualizar/" + id);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return new ModelAndView("redirect:/usuarios");
    }

    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable Long id, @Valid UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("formUsuario");
        }

        Usuario usuario = new Usuario(usuarioDTO);
        usuario.setIdUsuario(id);
        usuarioRepository.save(usuario);

        return new ModelAndView("redirect:/usuarios");
    }
}
