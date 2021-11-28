package com.mirea.schedule.services;

import com.mirea.schedule.models.Record;
import com.mirea.schedule.repositories.IRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {
    private IRecordRepository iRecordRepository;
    @Autowired
    public RecordService(IRecordRepository iRecordRepository){
        this.iRecordRepository = iRecordRepository;
    }

    public List<Record> getAllRecordsByTypeId(int typeId){
        if (typeId != 0)
            return iRecordRepository.findAllByTypeId(typeId);
        else
            return getAllRecords();
    }
    public List<Record> getAllRecords(){
        return iRecordRepository.findAll();
    }
    public List<Record> getAllRecordsByUserId(int userId){
        return iRecordRepository.findAllByUserId(userId);
    }

    public Record getRecordById(int id){
        return iRecordRepository.findById(id);
    }

    public Record saveRecord(Record record){
        return iRecordRepository.save(record);
    }

    public void deleteById(int id){
        iRecordRepository.deleteById(id);
    }

    public List<Record> getAllByTypeIdAndUserId(int typeId, int userId ){
        return iRecordRepository.findAllByTypeIdAndUserId(typeId, userId);
    }


}
