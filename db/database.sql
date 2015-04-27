CREATE DATABASE IF NOT EXISTS Challenge;

DROP TABLE IF EXISTS `edges`;
DROP TABLE IF EXISTS `nodes`;

CREATE TABLE `edges` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source_id` bigint(20) DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `weight` double NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9q61n86dslyr3w3x8vmvfrhdx` (`target_id`),
  KEY `FK_dr36qiy25bbos05udg5onbk5e` (`source_id`),
  CONSTRAINT `FK_dr36qiy25bbos05udg5onbk5e` FOREIGN KEY (`source_id`) REFERENCES `nodes` (`id`),
  CONSTRAINT `FK_9q61n86dslyr3w3x8vmvfrhdx` FOREIGN KEY (`target_id`) REFERENCES `nodes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `nodes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r87yhrftlwadnpiky8ld6353t` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;