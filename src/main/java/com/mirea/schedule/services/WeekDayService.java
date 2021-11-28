package com.mirea.schedule.services;

import com.mirea.schedule.models.WeekDay;
import com.mirea.schedule.repositories.IWeekDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekDayService {
    private IWeekDayRepository iWeekDayRepository;
    @Autowired
    public WeekDayService(IWeekDayRepository iWeekDayRepository){
        this.iWeekDayRepository=iWeekDayRepository;
    }
    public List<WeekDay> getAllWeekDays(){
        return iWeekDayRepository.findAll();
    }

}
