package com.mirea.schedule.repositories;

import com.mirea.schedule.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findAllByTypeId(int typeId);
    List<Record> findAllByTypeIdAndUserId(int typeId, int userId);
    List<Record> findAllByUserId(int userId);
    Record findById(int id);
    Long deleteById(int id);



}
