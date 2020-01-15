-- 1) Modificar a estrutura da tabela student_absences
ALTER TABLE student_absences RENAME COLUMN quantity TO qt_absences;
ALTER TABLE student_absences ADD qt_justified_absences integer not null default 0;

-- 2) Alterar o comportamento das functions que calculam o total de faltas não justificadas e as que não são
drop trigger trg_calculate_student_absences_iu on call;
drop function fn_calculate_student_absences_iu;

create function fn_calculate_student_absences_iu() returns trigger
    language plpgsql
as
$$
BEGIN
    DELETE FROM student_absences WHERE student_id = NEW.student_id AND class_id = NEW.class_id;

    WITH sa AS (
        SELECT sc.student_id, sc.class_id,
            (SELECT COALESCE(count(*), 0) AS qt_absences FROM call c WHERE c.student_id = sc.student_id AND c.class_id = sc.class_id AND c.status in (1)),
            (SELECT COALESCE(count(*), 0) AS qt_justified_absences FROM call c WHERE c.student_id = sc.student_id AND c.class_id = sc.class_id AND c.status in (2))
        FROM student_class sc
        WHERE sc.student_id = NEW.student_id AND sc.class_id = NEW.class_id
        GROUP BY sc.student_id, sc.class_id
    )
    INSERT INTO student_absences (student_id, class_id, qt_absences, qt_justified_absences)
    SELECT *
    FROM sa;

    RETURN NEW;
END;
$$;

create trigger trg_calculate_student_absences_iu
    after insert or update
    on call
    for each row
execute procedure fn_calculate_student_absences_iu();

drop trigger trg_calculate_student_absences_d on call;
drop function fn_calculate_student_absences_d;

create function fn_calculate_student_absences_d() returns trigger
    language plpgsql
as
$$
BEGIN
    DELETE FROM student_absences WHERE student_id = OLD.student_id AND class_id = OLD.class_id;

    WITH sa AS (
        SELECT sc.student_id, sc.class_id,
               (SELECT COALESCE(count(*), 0) AS qt_absences FROM call c WHERE c.student_id = sc.student_id AND c.class_id = sc.class_id AND c.status in (1)),
               (SELECT COALESCE(count(*), 0) AS qt_justified_absences FROM call c WHERE c.student_id = sc.student_id AND c.class_id = sc.class_id AND c.status in (2))
        FROM student_class sc
        WHERE sc.student_id = OLD.student_id AND sc.class_id = OLD.class_id
        GROUP BY sc.student_id, sc.class_id
    )
    INSERT INTO student_absences (student_id, class_id, qt_absences, qt_justified_absences)
    SELECT *
    FROM sa;

    RETURN NEW;
END;
$$;

create trigger trg_calculate_student_absences_d
    after delete
    on call
    for each row
execute procedure fn_calculate_student_absences_d();