CREATE TABLE employee (
                          id bigserial PRIMARY KEY,
                          state varchar(255),
                          passport_series_number varchar(255) NOT NULL,
                          name varchar(255) NOT NULL,
                          surname varchar(255) NOT NULL,
                          date_of_birth date NOT NULL
);

CREATE TABLE position (
                           id bigserial PRIMARY KEY ,
                           title varchar(255)  NOT NULL
);


CREATE TABLE position_employee (
                                   position_id bigserial REFERENCES position(id),
                                   employee_id bigserial REFERENCES employee(id),
                                   PRIMARY KEY (position_id, employee_id)

);
