CREATE TABLE `temp_item`
(
    `id`    VARCHAR(36)  NOT NULL COMMENT '主键',
    `temp_key`   VARCHAR(100) NOT NULL COMMENT '键',
    `temp_value` VARCHAR(256) NOT NULL COMMENT '值',
        PRIMARY KEY (`id`)
) ENGINE = InnoDB;
