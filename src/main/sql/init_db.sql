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



CREATE TABLE "categories" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"department" varchar NOT NULL,
	"description" varchar NOT NULL,
	CONSTRAINT categories_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "currencies" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT currencies_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "suppliers" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"description" varchar NOT NULL,
	CONSTRAINT suppliers_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



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

