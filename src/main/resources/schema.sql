-- -----------------------------------------------------
-- table  
-- -----------------------------------------------------
create table if not exists product (
  id int not null auto_increment,
  name varchar(150) not null,
  description varchar(300)not null,
  price double not null,
  primary key (id))
;