ALTER TABLE "products" DROP CONSTRAINT IF EXISTS "products_fk0";

ALTER TABLE "products" DROP CONSTRAINT IF EXISTS "products_fk1";

ALTER TABLE "products" DROP CONSTRAINT IF EXISTS "products_fk2";

ALTER TABLE "orders" DROP CONSTRAINT IF EXISTS "orders_fk0";

ALTER TABLE "lineitems" DROP CONSTRAINT IF EXISTS "lineitems_fk0";

ALTER TABLE "lineitems" DROP CONSTRAINT IF EXISTS "lineitems_fk1";

DROP TABLE IF EXISTS "products";

DROP TABLE IF EXISTS "categories";

DROP TABLE IF EXISTS "currencies";

DROP TABLE IF EXISTS "suppliers";

DROP TABLE IF EXISTS "users";

DROP TABLE IF EXISTS "orders";

DROP TABLE IF EXISTS "lineitems";

DROP SEQUENCE IF EXISTS public.products_id_seq;
CREATE TABLE "products" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"price" float NOT NULL,
	"description" varchar NOT NULL,
	"supplier_id" int NOT NULL,
	"category_id" int NOT NULL,
	"currency_id" int NOT NULL,
  "image_path" varchar NOT NULL,
	CONSTRAINT products_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


DROP SEQUENCE IF EXISTS public.categories_id_seq;
CREATE TABLE "categories" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"department" varchar NOT NULL,
	"description" varchar NOT NULL,
	CONSTRAINT categories_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


DROP SEQUENCE IF EXISTS public.currencies_id_seq;
CREATE TABLE "currencies" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT currencies_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


DROP SEQUENCE IF EXISTS public.suppliers_id_seq;
CREATE TABLE "suppliers" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"description" varchar NOT NULL,
	CONSTRAINT suppliers_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


DROP SEQUENCE IF EXISTS public.users_id_seq;
CREATE TABLE "users" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"email" varchar NOT NULL,
	"password" varchar NOT NULL,
	"billing_address" varchar,
	"shipping_address" varchar,
	CONSTRAINT users_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


DROP SEQUENCE IF EXISTS public.orders_id_seq;
CREATE TABLE "orders" (
	"id" serial NOT NULL,
	"user_id" int NOT NULL,
	"total" float NOT NULL,
	"date" DATE NOT NULL,
	CONSTRAINT orders_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "lineitems" (
	"order_id" int NOT NULL,
	"quantity" int NOT NULL,
	"product_id" int NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "products" ADD CONSTRAINT "products_fk0" FOREIGN KEY ("supplier_id") REFERENCES "suppliers"("id");
ALTER TABLE "products" ADD CONSTRAINT "products_fk1" FOREIGN KEY ("category_id") REFERENCES "categories"("id");
ALTER TABLE "products" ADD CONSTRAINT "products_fk2" FOREIGN KEY ("currency_id") REFERENCES "currencies"("id");





ALTER TABLE "orders" ADD CONSTRAINT "orders_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "lineitems" ADD CONSTRAINT "lineitems_fk0" FOREIGN KEY ("order_id") REFERENCES "orders"("id");
ALTER TABLE "lineitems" ADD CONSTRAINT "lineitems_fk1" FOREIGN KEY ("product_id") REFERENCES "products" ("id");

INSERT INTO "currencies" (name) VALUES ('USD');
INSERT INTO "currencies" (name) VALUES ('HUF');
INSERT INTO "currencies" (name) VALUES ('EUR');
INSERT INTO "currencies" (name) VALUES ('CHF');
INSERT INTO "currencies" (name) VALUES ('GBP');

INSERT INTO "suppliers" (name, description) VALUES ('Amazon', 'Digital content and services');
INSERT INTO "suppliers" (name, description) VALUES ('Lenovo', 'Computers');
SELECT pg_catalog.setval('suppliers_id_seq', 2, true);

INSERT INTO "categories" (name, department, description) VALUES ('Tablet', 'Hardware', 'A tablet computer. Commonly shortend to tablet is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO "categories" (name, department, description) VALUES ('Test', 'Test', 'Test');
SELECT pg_catalog.setval('categories_id_seq', 2, true);

INSERT INTO "products" (name, price, description, supplier_id, category_id, currency_id, image_path) VALUES ('Amazon Fire', 49, 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 1, 1, '/static/img/product_1.jpg');
INSERT INTO "products" (name, price, description, supplier_id, category_id, currency_id, image_path) VALUES ('Lenovo IdeaPad Miix 700', 479, 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 2, 1, 1, '/static/img/product_2.jpg');
INSERT INTO "products" (name, price, description, supplier_id, category_id, currency_id, image_path) VALUES ('Amazon Fire HD 8', 89, 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.', 1, 1, 1, '/static/img/product_3.jpg');
SELECT pg_catalog.setval('products_id_seq', 3, true);

INSERT INTO "users" (name, email, password, billing_address, shipping_address) VALUES ('test', 'szabolcs.adam@webmillers.hu', 'password', 'test', 'test');





