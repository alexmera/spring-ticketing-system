CREATE TABLE app_user (
  id         SERIAL,
  user_name  VARCHAR(100) NOT NULL,
  user_email VARCHAR(100) NOT NULL,
  rol        VARCHAR(50)  NOT NULL,
  password   VARCHAR(50)  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (user_name)
);

CREATE TABLE client (
  id          SERIAL,
  app_user_id INTEGER      NOT NULL,
  client_name VARCHAR(250) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (app_user_id) REFERENCES app_user (id)
);