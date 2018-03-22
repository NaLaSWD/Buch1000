-- Reader Role --
CREATE ROLE buchverzeichnis_reader_role;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO buchverzeichnis_reader_role;

-- Writer Role --
CREATE ROLE buchverzeichnis_writer_role;
GRANT buchverzeichnis_reader_role TO buchverzeichnis_writer_role;
GRANT INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO buchverzeichnis_writer_role;
GRANT SELECT,UPDATE ON ALL SEQUENCES IN SCHEMA public TO buchverzeichnis_writer_role;

-- Admin Role --
CREATE ROLE buchverzeichnis_admin_role;
GRANT buchverzeichnis_writer_role TO buchverzeichnis_admin_role;
GRANT CREATE ON SCHEMA public TO buchverzeichnis_admin_role;
GRANT DELETE ON ALL TABLES IN SCHEMA public TO buchverzeichnis_admin_role;

-- Users --
CREATE USER buchverzeichnis_user_read WITH PASSWORD 'power';
GRANT buchverzeichnis_reader_role TO buchverzeichnis_user_read;

CREATE USER buchverzeichnis_user_write WITH PASSWORD 'james';
GRANT buchverzeichnis_writer_role TO buchverzeichnis_user_write;

CREATE USER buchverzeichnis_user_admin WITH PASSWORD 'jones';
GRANT buchverzeichnis_admin_role TO buchverzeichnis_user_admin;