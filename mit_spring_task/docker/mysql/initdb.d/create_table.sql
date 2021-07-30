CREATE TABLE IF NOT EXISTS `address` (
    `addressid` INTEGER(10) NOT NULL COMMENT '住所ID',
    `zipcode` INTEGER(7) NOT NULL COMMENT '郵便番号',
    `address` VARCHAR(100) NOT NULL COMMENT '住所',
    `createdate` DATETIME NULL,
    `updatedate` DATETIME NULL,
    PRIMARY KEY (`addressid`)
    )
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
    `id` INTEGER(10) NOT NULL COMMENT 'ユーザーID',
    `name` VARCHAR(100) NOT NULL COMMENT 'ユーザ名',
    `age` INTEGER(3) NOT NULL COMMENT 'ユーザー年齢',
    `tel` INTEGER(15) NOT NULL COMMENT 'ユーザー電話番号',
    `addressid` INTEGER(10) NOT NULL,
    `createdate` DATETIME NULL,
    `updatedate` DATETIME NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`addressid`) REFERENCES `address` (`addressid`)
    )
    ENGINE = InnoDB;

INSERT INTO `address` VALUES (1, 1000001 , "東京都新宿区西新宿１−１−１", now(), now());
INSERT INTO `address` VALUES (2, 2000002 , "山梨県甲府市相生２−２−２", now(), now());
INSERT INTO `address` VALUES (3, 3000003 , "沖縄県那覇市３−３−３", now(), now());

INSERT INTO `users` VALUES (1, "太郎", 16, 0900010002, 1, now(), now());
INSERT INTO `users` VALUES (2, "次郎", 20, 0550030004, 1, now(), now());
INSERT INTO `users` VALUES (3, "花子", 45, 0330060007, 2, now(), now());