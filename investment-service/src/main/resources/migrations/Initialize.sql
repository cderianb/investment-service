CREATE TABLE "investors"(
    id BIGSERIAL PRIMARY KEY,
    created_date BIGINT not null,
    created_by varchar(50),
    last_modified_date BIGINT not null,
    last_modified_by varchar(50),
    version int,
    username varchar(50) not null,
    name varchar(50) not null,
    risk_profile varchar(20),
    source_of_fund varchar(20),
    CONSTRAINT UQ_investors_username UNIQUE (username)
);

CREATE TABLE "borrowers"(
    id BIGSERIAL PRIMARY KEY,
    created_date BIGINT not null,
    created_by varchar(50),
    last_modified_date BIGINT not null,
    last_modified_by varchar(50),
    version int,
    company_name varchar(50) not null,
    pic_name varchar(50) not null,
    phone varchar(20),
    business varchar(20),
    address varchar(255)
);

CREATE TABLE "tranches"(
   id BIGSERIAL PRIMARY KEY,
   created_date BIGINT not null,
   created_by varchar(50),
   last_modified_date BIGINT not null,
   last_modified_by varchar(50),
   version int,
   name varchar(50) not null,
   borrower_id bigint not null,
   annual_interest_rate float not null,
   amount_available_for_investment bigint not null ,
   duration int not null,
   maximum_investment_amount bigint not null,
   minimum_investment_amount_per_investor bigint not null,
   maximum_investment_amount_per_investor bigint not null,
   CONSTRAINT fk_tranches_borrower foreign key(borrower_id) references borrowers(id)
);

CREATE TABLE "platforms"(
   id BIGSERIAL PRIMARY KEY,
   created_date BIGINT not null,
   created_by varchar(50),
   last_modified_date BIGINT not null,
   last_modified_by varchar(50),
   version int,
   investor_id bigint not null,
   tranche_id bigint not null,
   start_date bigint not null,
   maturity_date bigint not null,
   end_date bigint null,
   status varchar(10) not null ,
   monthly_interest double precision not null,
   amount_invested double precision not null,
   platform_fee double precision not null,
   borrower_receive double precision not null,
   CONSTRAINT fk_platform_investor foreign key(investor_id) references investors(id),
   CONSTRAINT fk_platform_tranche foreign key(tranche_id) references tranches(id)
);

CREATE TABLE "repayments"(
    id BIGSERIAL PRIMARY KEY,
    created_date BIGINT not null,
    created_by varchar(50),
    last_modified_date BIGINT not null,
    last_modified_by varchar(50),
    version int,
    platform_id bigint not null,
    repayment_date bigint not null,
    "period" int not null ,
    interest_paid double precision not null,
    platform_fee double precision not null,
    CONSTRAINT fk_repayment_platform foreign key(platform_id) references platforms(id)
);