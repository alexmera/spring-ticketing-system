CREATE TABLE app_user (
  id         SERIAL,
  user_name  VARCHAR(100) NOT NULL,
  user_email VARCHAR(100) NOT NULL,
  rol        VARCHAR(50)  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (user_name)
);