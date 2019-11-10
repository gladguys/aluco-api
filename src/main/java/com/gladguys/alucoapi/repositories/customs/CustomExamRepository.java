package com.gladguys.alucoapi.repositories.customs;

import java.util.Set;

public interface CustomExamRepository {
    Set<Long> getAllByClassId(Long classId);
}
