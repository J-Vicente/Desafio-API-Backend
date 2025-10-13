-- Exemplos para testes


INSERT INTO tb_cursos (nome, codigo) VALUES ('Ciência da Computação', 'CCOMP');
INSERT INTO tb_cursos (nome, codigo) VALUES ('Engenharia de Software', 'ESOFT');


INSERT INTO tb_professores (nome, matricula) VALUES ('Carlos Silva', 1001);
INSERT INTO tb_professores (nome, matricula) VALUES ('Ana Souza', 1002);
INSERT INTO tb_professores (nome, matricula) VALUES ('João Pereira', 1003);


INSERT INTO tb_disciplinas (nome, codigo, curso_id) VALUES ('Programação Orientada a Objetos', 'POO101', 1);
INSERT INTO tb_disciplinas (nome, codigo, curso_id) VALUES ('Banco de Dados', 'BD202', 1);
INSERT INTO tb_disciplinas (nome, codigo, curso_id) VALUES ('Engenharia de Requisitos', 'ER303', 2);


INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES ('Maria Oliveira', 2001, 1);
INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES ('Pedro Santos', 2002, 1);
INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES ('Julia Costa', 2003, 2);
INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES ('Antônio Junior', 2004, 2);



INSERT INTO tb_professores_by_disciplina (disciplina_id, professor_id) VALUES (1, 1);
INSERT INTO tb_professores_by_disciplina (disciplina_id, professor_id) VALUES (2, 2);
INSERT INTO tb_professores_by_disciplina (disciplina_id, professor_id) VALUES (3, 3);


INSERT INTO tb_alunos_by_disciplina (disciplina_id, aluno_id) VALUES (1, 1);
INSERT INTO tb_alunos_by_disciplina (disciplina_id, aluno_id) VALUES (1, 2);
INSERT INTO tb_alunos_by_disciplina (disciplina_id, aluno_id) VALUES (2, 1);
INSERT INTO tb_alunos_by_disciplina (disciplina_id, aluno_id) VALUES (3, 3);
