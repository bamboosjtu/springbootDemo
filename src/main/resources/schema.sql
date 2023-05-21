CREATE TABLE IF NOT EXISTS Taco_Order (
	id IDENTITY,
	delivery_name varchar(50) NOT NULL,
	delivery_street varchar(50) NOT NULL,
	delivery_city varchar(50) NOT NULL,
	delivery_state varchar(20) NOT NULL,
	delivery_zip varchar(10) NOT NULL,
	cc_number varchar(16) NOT NULL,
	cc_expiration varchar(5) NOT NULL,
	cc_cvv varchar(3) NOT NULL,
	placed_at timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco (
	id IDENTITY,
	name varchar(50) NOT NULL,
	taco_order bigint NOT NULL,
	taco_order_key bigint NOT NULL,
	created_at timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS Ingredient_Ref (
	ingredient varchar(4) NOT NULL,
	taco bigint NOT NULL,
	taco_key bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS Ingredient (
	id varchar(4) NOT NULL PRIMARY KEY,
	name varchar(25) NOT NULL,
	TYPE varchar(10) NOT NULL
);


ALTER TABLE Taco
ADD FOREIGN KEY (taco_order) REFERENCES Taco_Order(id);

ALTER TABLE Ingredient_Ref
ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);


delete from Ingredient_Ref;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;

insert into Ingredient (id, name, type)
	values ('FLTO', '小麦粉薄烙饼', 'WRAP');
insert into Ingredient (id, name, type)
	values ('COTO', '玉米粉薄烙饼', 'WRAP');
insert into Ingredient (id, name, type)
	values ('GRBF', '牛肉', 'PROTEIN');
insert into Ingredient (id, name, type)
	values ('CARN', '猪肉', 'PROTEIN');
insert into Ingredient (id, name, type)
	values ('TMTO', '番茄', 'VEGGIES');
insert into Ingredient (id, name, type)
	values ('LETC', '莴苣', 'VEGGIES');
insert into Ingredient (id, name, type)
	values ('CHED', '切达牌奶酪', 'CHEESE');
insert into Ingredient (id, name, type)
	values ('JACK', '杰克牌奶酪', 'CHEESE');
insert into Ingredient (id, name, type)
	values ('SLSA', '沙拉酱', 'SAUCE');
insert into Ingredient (id, name, type)
	values ('SRCR', '奶油酱', 'SAUCE');