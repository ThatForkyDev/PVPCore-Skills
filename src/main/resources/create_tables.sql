CREATE TABLE IF NOT EXISTS `account_skills` (
    `uuid` BINARY(16) NOT NULL UNIQUE,
    `data`             LONGTEXT NOT NULL,
    PRIMARY KEY (`uuid`)
);