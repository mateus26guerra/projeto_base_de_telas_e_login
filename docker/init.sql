-- Cria o banco de dados se não existir
CREATE DATABASE login;

\c login

-- Habilita extensão para gerar UUID
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Cria a tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Insere o usuário padrão com senha '123'
INSERT INTO users (username, password, role)
VALUES ('suporte1', '$2a$12$elJkrpNdA7Md6u5RMZKbNeog2Gv.rv.39/naL4KB6lCVwPGSeCQAC', 'ADMIN')
ON CONFLICT (username) DO NOTHING;

-----------------------------------------------------------
-- 🟩 TABELA DE PRODUTOS (com base na sua classe Product)
-----------------------------------------------------------

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);

-- Exemplo de produto inicial (opcional)
INSERT INTO product (name, price)
VALUES
('Camiseta Azul', 49.90),
('Calça Jeans', 129.90),
('Tênis Branco', 199.90)
ON CONFLICT DO NOTHING;
