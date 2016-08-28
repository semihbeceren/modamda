DROP TABLE combined_items IF EXISTS;
DROP TABLE items IF EXISTS;
DROP TABLE categories IF EXISTS;
DROP TABLE combines IF EXISTS;
DROP TABLE users IF EXISTS;



CREATE TABLE users (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);

CREATE TABLE categories (
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(80)
);
CREATE INDEX categories_name ON categories (name);

CREATE TABLE items (
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(80),
	category_id INTEGER NOT NULL
);
CREATE INDEX items_name ON items (name);
ALTER TABLE items ADD CONSTRAINT fk_items_categories FOREIGN KEY (category_id) REFERENCES categories (id);

CREATE TABLE combines (
	id INTEGER IDENTITY PRIMARY KEY,
	add_date DATETIME,
	name VARCHAR(80),
	user_id INTEGER NOT NULL
);
CREATE INDEX combines_date ON combines (add_date);
ALTER TABLE combines ADD CONSTRAINT fk_combines_users FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE combined_items (
	combine_id       INTEGER NOT NULL,
  	item_id INTEGER NOT NULL
);
ALTER TABLE combined_items ADD CONSTRAINT fk_combined_items_combines FOREIGN KEY (combine_id) REFERENCES combines (id);
ALTER TABLE combined_items ADD CONSTRAINT fk_combined_items_items FOREIGN KEY (item_id) REFERENCES items (id);
