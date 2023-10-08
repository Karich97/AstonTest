--liquibase formatted sql

--changeset KartashovSM:1
--comment creating of BankAccount & Transaction tables
create table if not exists bank_account (
                              id bigserial not null,
                              current_balance float(53),
                              owner_name varchar(255),
                              pin_code varchar(255),
                              primary key (id)
);

create table bank_account_transactions (
                                           bank_account_id bigint not null,
                                           transactions_id bigint not null
);

create table transaction (
                             id bigserial not null,
                             amount float(53),
                             status varchar(255) check (status in ('APPROVED','IN_PROGRESS','DECLINE')),
                             target_id bigint not null,
                             bank_account_id bigint,
                             primary key (id)
);

alter table if exists bank_account_transactions
    drop constraint if exists UK_20hunm35120hjj25nns687xgn;

alter table if exists bank_account_transactions
    add constraint UK_20hunm35120hjj25nns687xgn unique (transactions_id);

alter table if exists bank_account_transactions
    add constraint FKdcneoueo4ur5eu85ej4fotbq3
        foreign key (transactions_id)
            references transaction;

alter table if exists bank_account_transactions
    add constraint FK8qvwepp6n6jaovhr0j3mx8ecy
        foreign key (bank_account_id)
            references bank_account;

INSERT INTO bank_account (current_balance, owner_name, pin_code) VALUES
                                                                     (1000, 'Иванов Иван', '1111'),
                                                                     (2000, 'Петров Петр', '2222'),
                                                                     (3000, 'Сидоров Сидор', '3333'),
                                                                     (4000, 'Кузнецова Анна', '4444'),
                                                                     (5000, 'Смирнов Сергей', '5555'),
                                                                     (6000, 'Михайлов Михаил', '6666'),
                                                                     (7000, 'Козлова Елена', '7777'),
                                                                     (8000, 'Васильева Ольга', '8888'),
                                                                     (9000, 'Никитина Александра', '9999'),
                                                                     (10000, 'Иванова Мария', '0000');

INSERT INTO transaction (amount, status, target_id, bank_account_id) VALUES
                                                                         (100, 'APPROVED', 1, 1),
                                                                         (200, 'DECLINE', 2, 1),
                                                                         (300, 'APPROVED', 2, 1),
                                                                         (400, 'DECLINE', 9, 2),
                                                                         (500, 'APPROVED', 10, 2),
                                                                         (600, 'DECLINE', 3, 3),
                                                                         (700, 'APPROVED', 4, 3),
                                                                         (800, 'APPROVED', 5, 3),
                                                                         (900, 'APPROVED', 6, 4),
                                                                         (1000, 'APPROVED', 7, 4),
                                                                         (1100, 'DECLINE', 8, 4),
                                                                         (1200, 'IN_PROGRESS', 5, 5),
                                                                         (1300, 'IN_PROGRESS', 5, 5),
                                                                         (1400, 'IN_PROGRESS', 6, 6),
                                                                         (1500, 'IN_PROGRESS', 6, 6),
                                                                         (1600, 'DECLINE', 7, 7),
                                                                         (1700, 'APPROVED', 7, 7),
                                                                         (1800, 'APPROVED', 7, 7),
                                                                         (1900, 'APPROVED', 8, 8),
                                                                         (2000, 'DECLINE', 8, 8),
                                                                         (2100, 'IN_PROGRESS', 9, 9),
                                                                         (2200, 'IN_PROGRESS', 9, 9),
                                                                         (2300, 'IN_PROGRESS', 10, 10),
                                                                         (2400, 'IN_PROGRESS', 10, 10);

INSERT INTO bank_account_transactions (bank_account_id, transactions_id) VALUES
                                                                             (1, 1),
                                                                             (1, 2),
                                                                             (1, 3),
                                                                             (2, 4),
                                                                             (2, 5),
                                                                             (3, 6),
                                                                             (3, 7),
                                                                             (3, 8),
                                                                             (4, 9),
                                                                             (4, 10),
                                                                             (4, 11),
                                                                             (5, 12),
                                                                             (5, 13),
                                                                             (6, 14),
                                                                             (6, 15),
                                                                             (7, 16),
                                                                             (7, 17),
                                                                             (7, 18),
                                                                             (8, 19),
                                                                             (8, 20),
                                                                             (9, 21),
                                                                             (9, 22),
                                                                             (10,23),
                                                                             (10,24);