create table if not exists point_of_interest_entity
(
    id           bigserial
        constraint point_of_interest_entity_pk
            primary key,
    name         text,
    description  text,
    poi_type     text,
    address_name text,
    city         text,
    zip_code     text,
    latitude     double precision,
    longitude    double precision
);