CREATE TABLE IF NOT EXISTS analyse
(
    id character varying(255) NOT NULL,
    CONSTRAINT analyse_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rss_feed_item
(
    id character varying(255)  NOT NULL,
    title character varying(MAX ) NOT NULL,
    link character varying(MAX ) NOT NULL,
    analyse_id character varying(255) NOT NULL,
    CONSTRAINT rss_feed_item_pkey PRIMARY KEY (id),
    CONSTRAINT rss_feed_item_analyse_id_key FOREIGN KEY (analyse_id)
        REFERENCES analyse (id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS topic
(
    id character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    count integer NOT NULL,
    analyse_id character varying(255) NOT NULL,
    CONSTRAINT topic_pkey PRIMARY KEY (id),
    CONSTRAINT topic_analyse_id_fkey FOREIGN KEY (analyse_id)
        REFERENCES analyse (id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);