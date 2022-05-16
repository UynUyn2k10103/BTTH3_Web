create database btth_product;

-- drop database btth_product;
use btth_product;


CREATE TABLE product (
	p_id INT NOT NULL AUTO_INCREMENT,
    p_code VARCHAR(255) NOT NULL,
    p_description VARCHAR(255) NOT NULL,
    p_price FLOAT(10) NOT NULL,
    PRIMARY KEY (p_id)
) AUTO_INCREMENT=1;

insert into product(p_code, p_description, p_price) 
values 
	('8601', '86 (the band) - True Life Songs and Pictures', '14.95'),
    ('pf01', 'Paddlefoot - The first CD', '12.95'),
    ('pf02', 'Paddlefoot - The second CD', '14.95'),
    ('jr01', 'Joe Rut - Genuine Wood Grained Finish', '13.95');
    
SELECT * FROM btth_product.product;