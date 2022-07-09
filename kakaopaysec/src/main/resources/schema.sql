create table if not exists user(
	userId varchar(10) PRIMARY KEY,
	userName varchar(10),
	userAge varchar(10),
	regDate varchar(14)
);

create table if not exists account(
	userId varchar(10),
	accountNo varchar(12)
);

create table if not exists accountInfo(
	accountNo varchar(10),
	status varchar(2),
	date varchar(10),
	amount varchar(16)
);