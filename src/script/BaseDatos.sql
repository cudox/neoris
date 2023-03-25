-- public.cliente definition

-- Drop table

-- DROP TABLE public.cliente;

CREATE TABLE public.cliente (
    cliente_id int8 NOT NULL,
	identificacion varchar NOT NULL,
	nombre varchar NOT NULL,
	genero varchar NOT NULL,
	edad numeric NOT NULL,
	direccion varchar NULL,
	telefono varchar NULL,
	contrasena varchar NOT NULL,
	estado bool NOT NULL,
	CONSTRAINT cliente_pk PRIMARY KEY (cliente_id)
);


-- public.cuenta definition

-- Drop table

-- DROP TABLE public.cuenta;

CREATE TABLE public.cuenta (
	numero_cuenta int8 NOT NULL,
	tipo_cuenta varchar NULL,
	saldo_inicial varchar NOT NULL,
	estado bool NOT NULL,
	cliente_id varchar NULL,
	CONSTRAINT cuenta_pk PRIMARY KEY (numero_cuenta),
	CONSTRAINT cliente_id FOREIGN KEY (cliente_id) REFERENCES public.cliente(cliente_id)
);


-- public.movimiento definition

-- Drop table

-- DROP TABLE public.movimiento;

CREATE TABLE public.movimiento (
	id varchar NOT NULL,
	fecha timestamp NOT NULL,
	tipo_movimiento varchar NOT NULL,
	valor numeric NULL,
	saldo numeric NULL,
	cuenta_id varchar NULL,
	CONSTRAINT movimiento_pk PRIMARY KEY (id),
	CONSTRAINT cuenta_id FOREIGN KEY (id) REFERENCES public.cuenta(numero_cuenta)
);