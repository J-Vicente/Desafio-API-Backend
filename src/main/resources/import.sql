-- exemplos gerados pelo ChatGPT para testes

-- ===============================
-- Cursos
-- ===============================
INSERT INTO tb_cursos (nome, codigo) VALUES ('Ciência da Computação', 'CCOMP');
INSERT INTO tb_cursos (nome, codigo) VALUES ('Engenharia de Software', 'ESOFT');

-- ===============================
-- Professores
-- ===============================
INSERT INTO tb_professores (nome, matricula) VALUES ('Carlos Silva', 1001);
INSERT INTO tb_professores (nome, matricula) VALUES ('Ana Souza', 1002);
INSERT INTO tb_professores (nome, matricula) VALUES ('João Pereira', 1003);

-- ===============================
-- Disciplinas
-- ===============================

INSERT INTO tb_disciplinas (nome, codigo, curso_id) VALUES ('Programação Orientada a Objetos', 'POO101', 1);
INSERT INTO tb_disciplinas (nome, codigo, curso_id) VALUES ('Banco de Dados', 'BD202', 1);
INSERT INTO tb_disciplinas (nome, codigo, curso_id) VALUES ('Engenharia de Requisitos', 'ER303', 2);

-- ===============================
-- Alunos
-- ===============================

INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES ('Maria Oliveira', 2001, 1);
INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES ('Pedro Santos', 2002, 1);
INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES ('Julia Costa', 2003, 2);
INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES ('Antônio Junior', 2004, 2);

-- ===============================
-- Relações Many-to-Many
-- ===============================
-- Professores em disciplinas
INSERT INTO tb_professores_by_disciplina (disciplina_id, professor_id) VALUES (1, 1); -- Carlos -> POO
INSERT INTO tb_professores_by_disciplina (disciplina_id, professor_id) VALUES (2, 2); -- Ana -> BD
INSERT INTO tb_professores_by_disciplina (disciplina_id, professor_id) VALUES (3, 3); -- João -> ER

-- Alunos em disciplinas
INSERT INTO tb_alunos_by_disciplina (disciplina_id, aluno_id) VALUES (1, 1); -- Maria -> POO
INSERT INTO tb_alunos_by_disciplina (disciplina_id, aluno_id) VALUES (1, 2); -- Pedro -> POO
INSERT INTO tb_alunos_by_disciplina (disciplina_id, aluno_id) VALUES (2, 1); -- Maria -> BD
INSERT INTO tb_alunos_by_disciplina (disciplina_id, aluno_id) VALUES (3, 3); -- Julia -> ER
