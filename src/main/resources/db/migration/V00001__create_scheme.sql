CREATE TABLE employee (
                          id bigserial PRIMARY KEY,
                          disabled boolean default false,
                          passport_butch varchar(4)  NOT NULL,
                          passport_number varchar(6) NOT NULL,
                          name varchar(15) NOT NULL,
                          surname varchar(15) NOT NULL,
                          date_of_birth date NOT NULL
);

CREATE TABLE position (
                           id bigserial PRIMARY KEY ,
                           title varchar(50)  NOT NULL,
                           description text  NOT NULL
);


CREATE TABLE position_employee (
                                   position_id bigserial REFERENCES position(id),
                                   employee_id bigserial REFERENCES employee(id),
                                   PRIMARY KEY (position_id, employee_id)

);
