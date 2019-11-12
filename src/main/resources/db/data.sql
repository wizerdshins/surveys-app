
create table if not exists survey
(
	id bigserial not null
		constraint survey_pkey
			primary key,
	title varchar(255),
	begin_date date,
	end_date date,
	activity boolean
)
;

create table if not exists question
(
	id bigserial not null
		constraint question_pkey
			primary key,
	text varchar(255),
	survey_id integer
		constraint survey_id
			references survey
)
;