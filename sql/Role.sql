-- Reader Role --
CREATE ROLE autor;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO autor;

-- Writer Role --
CREATE ROLE verlag;
GRANT autor TO verlag;
GRANT INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO verlag;
GRANT SELECT,UPDATE ON ALL SEQUENCES IN SCHEMA public TO verlag;

-- Admin Role --
CREATE ROLE admin;
GRANT verlag TO admin;
GRANT CREATE ON SCHEMA public TO admin;
GRANT DELETE ON ALL TABLES IN SCHEMA public TO admin;

-- Users --
CREATE USER max WITH PASSWORD 'power';
GRANT autor TO max;
CREATE USER martha WITH PASSWORD 'stewart';
GRANT autor TO martha;
CREATE USER john WITH PASSWORD 'james';
GRANT verlag TO john;
CREATE USER jack WITH PASSWORD 'jones';
GRANT admin TO jack;