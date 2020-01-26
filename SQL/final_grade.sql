create view final_grade as (
   with gp as (
       select e.period_year,
              e.class_id, eg.student_id,
              sum(eg.grade * e.weight) / sum(e.weight) as grade
       from exam e
                inner join exam_grade eg on e.id = eg.exam_id
       where e.rec_exam = false
       group by e.period_year, eg.student_id, e.class_id
       order by e.period_year asc
   ),
    f as (
        select gp.student_id,
            gp.class_id,
            (select sum(gpt.grade) from gp gpt where gpt.period_year = 1 and gpt.class_id = gp.class_id and gpt.student_id = gp.student_id) as grade_period_one,
            (select sum(gpt.grade) from gp gpt where gpt.period_year = 2 and gpt.class_id = gp.class_id and gpt.student_id = gp.student_id) as grade_period_two,
            (select sum(gpt.grade) from gp gpt where gpt.period_year = 3 and gpt.class_id = gp.class_id and gpt.student_id = gp.student_id) as grade_period_three,
            (select sum(gpt.grade) from gp gpt where gpt.period_year = 4 and gpt.class_id = gp.class_id and gpt.student_id = gp.student_id) as grade_period_four
        from gp
        group by gp.student_id, gp.class_id
    )
   select *
   from f
   order by f.student_id, f.class_id
);