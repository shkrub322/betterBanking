create table transaction (
                             id int not null auto_increment,
                             type varchar(25) not null,
                             accountNumber int not null,
                             currency varchar(10) not null,
                             merchantName varchar(50),
                             merchantLogo varchar(50),
                             amount int not null,
                             date timestamp not null default now(),
                             primary key (id)
);

create index accountNum_idx on transaction(accountNumber);

insert into transaction(type, accountNumber, currency, merchantName, merchantLogo, amount)
values ('withdraw', 1, 'usd', 'shkrub', 'shkrub', 100),
       ('withdraw', 1, 'usd', 'shkrub', 'shkrub', 200),
       ('deposit', 1, 'usd', 'shkrub', 'shkrub', 500);