package com.mirea.schedule.repositories;

import com.mirea.schedule.models.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWeekDayRepository extends JpaRepository<WeekDay, Integer> {
}
