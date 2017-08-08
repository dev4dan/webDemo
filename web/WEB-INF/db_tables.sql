CREATE TABLE `mypractise`.`user` (
  `idpk` INT NOT NULL AUTO_INCREMENT COMMENT '用户记录id',
  `userName` VARCHAR(64) NOT NULL DEFAULT 'userName' COMMENT '用户名称',
  `birthday` DATETIME NOT NULL COMMENT '用户出生日期',
  `userAddress` VARCHAR(64) NOT NULL DEFAULT 'userAddress' COMMENT '用户地址',
  PRIMARY KEY (`idpk`),
  UNIQUE INDEX `idpk_UNIQUE` (`idpk` ASC));
