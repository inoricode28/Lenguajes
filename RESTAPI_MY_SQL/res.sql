CREATE DATABASE restapi_nodejs_express;

USE restapi_nodejs_express;

CREATE TABLE language(
    id  TINYINT(2)  UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) COLLATE utf8_unicode_ci NOT NULL,
    programmers TINYINT(2) UNSIGNED NOT NULL
)