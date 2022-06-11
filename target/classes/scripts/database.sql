CREATE TABLE IF NOT EXISTS medicamentos (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    data_fabricacao DATE NOT NULL,
    data_vencimento DATE NOT NULL,
    quantidade INT
)

CREATE TABLE IF NOT EXISTS pessoa (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    tipo_pessoa VARCHAR(50) NOT NULL,
    email VARCHAR(250) NOT NULL,
    numero VARCHAR(250) NOT NULL,
    endereco VARCHAR(250) NOT NULL
)

CREATE TABLE IF NOT EXISTS procedimento (
	id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(400) NOT NULL,
    medico INT NOT NULL,
    enfermeiro INT NOT NULL,
    paciente INT NOT NULL,
    data DATE NOT NULL,
    preco INT NOT NULL,
    CONSTRAINT fk_medico FOREIGN KEY (medico) REFERENCES pessoa(id),
    CONSTRAINT fk_enfermeiro FOREIGN KEY (enfermeiro) REFERENCES pessoa(id),
    CONSTRAINT fk_paciente FOREIGN KEY (paciente) REFERENCES pessoa(id)
)