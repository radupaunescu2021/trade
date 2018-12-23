SET sql_mode = '';

DELIMITER $$
CREATE PROCEDURE prepare_data_r18()
BEGIN
  DECLARE i INT DEFAULT 6;
  DECLARE pid int;
  WHILE i < 100 DO
    SET pid =rand()*(i-1)+1;
    set @tec='node'+'-'+cast(i as char(10));
    SELECT @height:= height from tree where ID=pid;
    INSERT INTO tree (name,PID,height) VALUES (concat('node-',concat(i, '')),pid,@height+1);
    SET i = i + 1;
  END WHILE;
END$$
DELIMITER ;


insert into tree (name,PID,height) values ('root-0',0,0);
insert into tree (name,PID,height) values ('node-1',1,1);
insert into tree (name,PID,height) values ('node-2',1,1);
insert into tree (name,PID,height) values ('node-3',2,2);
insert into tree (name,PID,height) values ('node-4',2,2);
insert into tree (name,PID,height) values ('node-5',3,2);


CALL prepare_data_r18();