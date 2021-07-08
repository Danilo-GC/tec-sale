CREATE KEYSPACE Product
WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 3}
AND durable_writes = true;

use product;

CREATE TABLE product(
    id uuid,
    name text,
    price decimal,
    type text,
    description text,
    PRIMARY KEY (id)
);