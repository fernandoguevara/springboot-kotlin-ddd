-- Note definition

-- Drop table

-- DROP TABLE note;

CREATE TABLE note (
	id uniqueidentifier NOT NULL,
	user_id uniqueidentifier NOT NULL,
	title varchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	description varchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	created_at datetime2(7) NOT NULL,
	modified_at datetime2(7) NOT NULL,
	CONSTRAINT pk_note PRIMARY KEY (id)
);

-- Email definition

-- Drop table

-- DROP TABLE email;

CREATE TABLE email (
	id uniqueidentifier NOT NULL,
	note_id uniqueidentifier NOT NULL,
	action varchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	created_at datetime2(7) NOT NULL,
	CONSTRAINT pk_email PRIMARY KEY (id)
);
 CREATE NONCLUSTERED INDEX ix_email_note_id ON email (  note_id ASC  )
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;


-- notes.dbo.Email foreign keys

ALTER TABLE email ADD CONSTRAINT fk_email_note_note_id FOREIGN KEY (note_id) REFERENCES note(id) ON DELETE CASCADE;