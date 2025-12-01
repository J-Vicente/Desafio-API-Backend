
CREATE TABLE tb_cursos (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    codigo VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE tb_alunos (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    matricula INT NOT NULL UNIQUE,
    curso_id BIGINT,
    CONSTRAINT fk_aluno_curso FOREIGN KEY (curso_id) REFERENCES tb_cursos(id)
);


CREATE TABLE tb_professores (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    matricula INT NOT NULL UNIQUE
);


CREATE TABLE tb_disciplinas (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    curso_id BIGINT,
    CONSTRAINT fk_disciplina_curso FOREIGN KEY (curso_id) REFERENCES tb_cursos(id)
);


CREATE TABLE tb_alunos_by_disciplina (
    aluno_id BIGINT NOT NULL,
    disciplina_id BIGINT NOT NULL,
    PRIMARY KEY (aluno_id, disciplina_id),
    CONSTRAINT fk_abd_aluno FOREIGN KEY (aluno_id) REFERENCES tb_alunos(id),
    CONSTRAINT fk_abd_disciplina FOREIGN KEY (disciplina_id) REFERENCES tb_disciplinas(id)
);


CREATE TABLE tb_professores_by_disciplina (
    professor_id BIGINT NOT NULL,
    disciplina_id BIGINT NOT NULL,
    PRIMARY KEY (professor_id, disciplina_id),
    CONSTRAINT fk_pbd_prof FOREIGN KEY (professor_id) REFERENCES tb_professores(id),
    CONSTRAINT fk_pbd_disc FOREIGN KEY (disciplina_id) REFERENCES tb_disciplinas(id)
);
