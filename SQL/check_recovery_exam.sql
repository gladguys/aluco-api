create trigger trg_recovery_exam_iu
    before insert or update on exam
    for each row
execute procedure check_recovery_exam_iu();

create function check_recovery_exam_iu() returns trigger
    language plpgsql
as
$$
BEGIN
    IF NEW.rec_exam = true AND not exists(select 1 from exam e where e.class_id = NEW.class_id and e.period_year = NEW.period_year and e.rec_exam = true limit 1) THEN
        RETURN NEW;
    ELSIF NEW.rec_exam = false THEN
        RETURN NEW;
    ELSE
        RETURN OLD;
    END IF;
END;
$$;