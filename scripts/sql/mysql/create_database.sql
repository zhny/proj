-- create database
create database st_ipop_emt default character set utf8;

-- add user
grant all privileges on *.* to st_ipop_emt@'%' identified by 'db10$ZTE';

-- log_bin_trust_function_creators
set global log_bin_trust_function_creators=1;

