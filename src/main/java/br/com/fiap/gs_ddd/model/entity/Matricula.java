package br.com.fiap.gs_ddd.model.entity;

import br.com.fiap.gs_ddd.model.dto.MatriculaDTO;
import jakarta.persistence.*;

@Entity(name = "Matricula")
@Table(name = "DDD_MATRICULA")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatricula;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO")
    private Curso curso;

    private String status;
    private Double nota;

    // Construtor vazio
    public Matricula() {
    }

    // Construtor com DTO
    public Matricula(MatriculaDTO matriculaDTO) {
        this.status = matriculaDTO.status();
        this.nota = matriculaDTO.nota();
    }

    // Getters e Setters
    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}