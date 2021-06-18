-- Note definition

-- Drop table

-- DROP TABLE note;

CREATE TABLE note (
	id uuid NOT NULL,
	user_id uuid NOT NULL,
	title text NOT NULL,
	description text NOT NULL,
	created_at timestamp NOT NULL,
	modified_at timestamp NOT NULL,
	CONSTRAINT pk_note PRIMARY KEY (id)
);

-- Email definition

-- Drop table

-- DROP TABLE email;

CREATE TABLE email (
	id uuid NOT NULL,
	note_id uuid NOT NULL,
	action text NOT NULL,
	created_at timestamp NOT NULL,
	CONSTRAINT pk_email PRIMARY KEY (id)
);
CREATE INDEX ix_email_note_id ON Email USING btree (note_id);


-- Email foreign keys

ALTER TABLE email ADD CONSTRAINT fk_email_note_note_id FOREIGN KEY (note_id) REFERENCES Note(id) ON DELETE CASCADE;