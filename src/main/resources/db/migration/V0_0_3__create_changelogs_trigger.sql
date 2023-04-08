CREATE FUNCTION func_changelogs() RETURNS TRIGGER LANGUAGE PLPGSQL AS $$
BEGIN
    if TG_OP = 'DELETE' then
	    insert into changelogs (event_type, table_name, "data")
		values(TG_OP, TG_TABLE_NAME, row_to_json(OLD));
    ELSE
	    insert into changelogs (event_type, table_name, "data")
		values(TG_OP, TG_TABLE_NAME, row_to_json(NEW));
	end if;

	RETURN NEW;
END;
$$
;

CREATE TRIGGER tg_changelogs AFTER
INSERT
OR
UPDATE
OR
DELETE ON drivers
FOR EACH ROW EXECUTE PROCEDURE func_changelogs();
