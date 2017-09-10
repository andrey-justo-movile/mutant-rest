CREATE SCHEMA IF NOT EXISTS mutant;

CREATE TABLE mutant.dnas (
	id bigserial primary key,
	dna text not null,
	mutant boolean not null default false
)