ALTER TABLE "Products" DROP CONSTRAINT IF EXISTS "Products_fk0";

ALTER TABLE "Products" DROP CONSTRAINT IF EXISTS "Products_fk1";

ALTER TABLE "Products" DROP CONSTRAINT IF EXISTS "Products_fk2";

ALTER TABLE "Orders" DROP CONSTRAINT IF EXISTS "Orders_fk0";

ALTER TABLE "LineItems" DROP CONSTRAINT IF EXISTS "LineItems_fk0";

ALTER TABLE "LineItems" DROP CONSTRAINT IF EXISTS "LineItems_fk1";

DROP TABLE IF EXISTS "Products";

DROP TABLE IF EXISTS "Categories";

DROP TABLE IF EXISTS "Currencies";

DROP TABLE IF EXISTS "Suppliers";

DROP TABLE IF EXISTS "Users";

DROP TABLE IF EXISTS "Orders";

DROP TABLE IF EXISTS "LineItems";

CREATE TABLE "Products" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"price" float NOT NULL,
	"description" varchar NOT NULL,
	"supplier_id" int NOT NULL,
	"category_id" int NOT NULL,
	"currency_id" int NOT NULL,
	CONSTRAINT Products_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Categories" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"department" varchar NOT NULL,
	"description" varchar NOT NULL,
	CONSTRAINT Categories_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Currencies" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT Currencies_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Suppliers" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"description" varchar NOT NULL,
	CONSTRAINT Suppliers_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Users" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"email" varchar NOT NULL,
	"password" varchar NOT NULL,
	"billing_address" varchar,
	"shipping_address" varchar,
	CONSTRAINT Users_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Orders" (
	"id" serial NOT NULL,
	"user_id" int NOT NULL,
	"total" float NOT NULL,
	"date" DATE NOT NULL,
	CONSTRAINT Orders_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "LineItems" (
	"order_id" int NOT NULL,
	"quantity" int NOT NULL,
	"product_id" int NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "Products" ADD CONSTRAINT "Products_fk0" FOREIGN KEY ("supplier_id") REFERENCES "Suppliers"("id");
ALTER TABLE "Products" ADD CONSTRAINT "Products_fk1" FOREIGN KEY ("category_id") REFERENCES "Categories"("id");
ALTER TABLE "Products" ADD CONSTRAINT "Products_fk2" FOREIGN KEY ("currency_id") REFERENCES "Currencies"("id");





ALTER TABLE "Orders" ADD CONSTRAINT "Orders_fk0" FOREIGN KEY ("user_id") REFERENCES "Users"("id");

ALTER TABLE "LineItems" ADD CONSTRAINT "LineItems_fk0" FOREIGN KEY ("order_id") REFERENCES "Orders"("id");
ALTER TABLE "LineItems" ADD CONSTRAINT "LineItems_fk1" FOREIGN KEY ("product_id") REFERENCES "Products"("id");

