package com.nadic.desafiobackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name="tb_disciplinas",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"codigo"})
        })
public class Disciplina {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String codigo;

    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso curso;

    @ManyToMany
    @JoinTable( name = "tb_professores_by_disciplina",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> alunos;

    public Disciplina() {
    }

    public Disciplina(String nome, String codigo, Curso curso, List<Professor> professores, List<Aluno> alunos) {
        this.nome = nome;
        this.codigo = codigo;
        this.curso = curso;
        this.professores = professores;
        this.alunos = alunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
