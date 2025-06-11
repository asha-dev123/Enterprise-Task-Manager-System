
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    role VARCHAR(255),
    email VARCHAR(255),
    department VARCHAR(255)
);

CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT
);

CREATE TABLE leave_request (
    id SERIAL PRIMARY KEY,
    employee_id INTEGER REFERENCES employee(id),
    start_date DATE,
    end_date DATE,
    status VARCHAR(50)
);
