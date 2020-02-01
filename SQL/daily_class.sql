create view daily_class as (
   select f.student_id,
          f.class_id,
          f.grade_period_one,
          f.grade_period_two,
          f.grade_period_three,
          f.grade_period_four,
          fgr.recovery_grade_period_one,
          fgr.recovery_grade_period_two,
          fgr.recovery_grade_period_three,
          fgr.recovery_grade_period_four,
          COALESCE(sat.qt_absences, 0)                                            as qt_absences,
          COALESCE(sat.qt_justified_absences, 0)                                  as qt_justified_absences,
          COALESCE(sat.qt_absences, 0) + COALESCE(sat.qt_justified_absences, 0)   as total_absences
   from final_grade f
            left join final_grade_recovery fgr on f.class_id = fgr.class_id and f.student_id = fgr.student_id
            left join student_absences sat on f.class_id = sat.class_id and f.student_id = sat.student_id
            left join config_class cc on f.class_id = cc.class_id
   order by f.student_id, f.class_id
);