insert into users(email, password, fio, role_id)
VALUES ('admin@mail.ru',
        '0d5dc8d2e7d827523c132d36dc45bde9cb70f9e72d15950ab095054b9202e5ad9511e19f438389716083f488fcebb70d83a8229e12aabdeb8c132be351ee176f93be83b2f0edcde6',
        'Иванов Иван Иванович',
        (select id from user_role where name = 'ADMIN'));
