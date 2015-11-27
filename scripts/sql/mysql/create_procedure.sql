DROP PROCEDURE IF EXISTS `demo_out_parameter`;
DELIMITER ;;
CREATE  PROCEDURE `demo_out_parameter`(IN user_id int)
BEGIN 
SET @z = 0;
SET @arrive = 0;
SET @th = 0;
SET @o = 0;
SET @click = 0;
SET @td = 0;

SELECT count(msa.id) INTO @z FROM mail_send_address msa, mail_task mt where msa.status='Y' and mt.user_id=user_id;
SELECT count(msa.id) INTO @arrive FROM mail_send_address msa, mail_task mt where msa.status='Y' and  msa.is_arrive=1 and mt.user_id=user_id;
SELECT count(msa.id) INTO @th FROM mail_send_address msa, mail_task mt  where msa.status='Y' and msa.is_th=1 and mt.user_id=user_id;
SELECT count(msa.id) INTO @o FROM mail_send_address msa, mail_task mt  where msa.status='Y' and msa.is_open=1 and mt.user_id=user_id;
SELECT sum(msa.click) INTO @click FROM mail_send_address msa, mail_task mt  where msa.status='Y' and msa.is_open=1 and mt.user_id=user_id;
SELECT count(msa.id) INTO @td FROM mail_send_address msa, mail_task mt  where msa.status='Y' and msa.is_td=1 and mt.user_id=user_id;

SELECT @z z,@arrive arrive, @th th, @o o, @click c , @td td;
END
;;
DELIMITER ;