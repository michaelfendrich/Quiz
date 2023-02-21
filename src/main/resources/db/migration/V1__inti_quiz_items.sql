create table question (
    id int primary key auto_increment,
    question_text varchar not null,
    answer_a varchar not null,
    answer_b varchar not null,
    answer_c varchar not null,
    correct_answer varchar not null
);