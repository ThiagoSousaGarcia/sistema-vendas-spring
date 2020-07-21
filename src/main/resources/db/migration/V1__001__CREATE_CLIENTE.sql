CREATE TABLE CLIENTE (
   ID BIGINT IDENTITY(1, 1) NOT NULL,
   NOME VARCHAR(255) NOT NULL,
   DATA_NASCIMENTO DATE NOT NULL,
   CPF VARCHAR(255) NOT NULL,
   RUA VARCHAR (255) NOT NULL,
   BAIRRO VARCHAR(255) NOT NULL,
   CIDADE VARCHAR(255) NOT NULL,
   COMPLEMENTO VARCHAR(255) NULL,
   CEP VARCHAR(255) NOT NULL,
   TELEFONE VARCHAR(255) NOT NULL,
   EMAIL VARCHAR(255) NOT NULL,
   CONSTRAINT CLIENTE_PK PRIMARY KEY (ID) 
);