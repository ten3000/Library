create table person
(
    person_id integer generated by default as identity
        primary key,
    name      varchar(100) not null
        unique,
    year      integer
        constraint person_year_check
            check (year > 1900)
);
create table book
(
    book_id    integer generated by default as identity
        primary key,
    name       varchar(100) not null,
    author     varchar(100) not null,
    year       integer,
    person_id  integer
        constraint book_person_null_fk
            references person
            on update cascade on delete set null,
    taken_date date
);
insert into person (person_id, name, year)
values  (1, 'Овсянников Юрий Михайлович', 1995),
        (8, 'Петр Василич', 1995),
        (10, 'Борис Бритва', 1999),
        (2, 'Глеб Владимирович Лягушка', 1948),
        (5, 'Джеймс Браун', 2002),
        (7, 'Вакуленко Василий', 2005),
        (4, 'Петров Антон', 1978),
        (3, 'Антонов Алексей', 1987);
insert into book (book_id, name, author, year, person_id, taken_date)
values  (2, 'День опричника', 'Владимир Сорокин', 2010, 1, '2023-01-11'),
        (1, 'Над пропастью во ржи', 'Джером Сэлинджер', 1951, 1, '2022-02-10'),
        (3, 'Тайные виды на гору Фудзи', 'Владимир Пелевин', 2018, null, null),
        (5, 'Философия Java', 'Брюс Эккель', 2018, 1, '2023-01-25'),
        (4, 'Гарри Поттер', 'Джуан Ролинг', 2000, 1, '2023-01-25');