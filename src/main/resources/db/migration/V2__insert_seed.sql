
INSERT INTO tb_cursos (nome, codigo) VALUES
('Ciência da Computação', 'CC01'),
('Sistemas de Informação', 'SI01'),
('Engenharia de Software', 'ES01');


INSERT INTO tb_professores (nome, matricula) VALUES
('Carlos Silva', 1001),
('Fernanda Castro', 1002),
('João Pereira', 1003);


INSERT INTO tb_disciplinas (nome, codigo, curso_id) VALUES
('Programação I', 'P01', 1),
('Estruturas de Dados', 'ED01', 1),
('Banco de Dados', 'BD01', 2);


INSERT INTO tb_alunos (nome, matricula, curso_id) VALUES
('Ana Souza', 2001, 1),
('Bruno Lima', 2002, 2),
('Marina Costa', 2003, 1);


INSERT INTO tb_alunos_by_disciplina (aluno_id, disciplina_id) VALUES
(1, 1),
(1, 2),
(2, 3);


INSERT INTO tb_professores_by_disciplina (professor_id, disciplina_id) VALUES
(1, 1),
(1, 2),
(2, 3);
