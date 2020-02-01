create view final_grade_recovery as (
    with gpr as (
        select e.period_year, e.class_id, eg.student_id, eg.grade as recovery_grade
        from exam e
                 inner join exam_grade eg on e.id = eg.exam_id
        where e.rec_exam = true
        order by e.period_year asc
    ),
     f as (
         select
             gpr.student_id,
             gpr.class_id,
             (select gprt.recovery_grade from gpr gprt where gprt.period_year = 1 and gprt.class_id = gpr.class_id and gprt.student_id = gpr.student_id) as recovery_grade_period_one,
             (select gprt.recovery_grade from gpr gprt where gprt.period_year = 2 and gprt.class_id = gpr.class_id and gprt.student_id = gpr.student_id) as recovery_grade_period_two,
             (select gprt.recovery_grade from gpr gprt where gprt.period_year = 3 and gprt.class_id = gpr.class_id and gprt.student_id = gpr.student_id) as recovery_grade_period_three,
             (select gprt.recovery_grade from gpr gprt where gprt.period_year = 4 and gprt.class_id = gpr.class_id and gprt.student_id = gpr.student_id) as recovery_grade_period_four
         from gpr
         group by gpr.student_id, gpr.class_id
     )
    select *
    from f
);