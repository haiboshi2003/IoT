package com.example.iotbankend.Service.Impl;

import com.example.iotbankend.Service.DataService;
import com.example.iotbankend.mapper.DataMapper;
import com.example.iotbankend.pojo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataImpl implements DataService {
    @Autowired
    DataMapper dataMapper;

    @Override
    public List<Data> getAllData() {
        return dataMapper.getAllData();
    }

    @Override
    public List<Data> getByDate(
            LocalDateTime date, LocalDateTime nextDate) {
        return dataMapper.getByDate(date, nextDate);
    }


}
