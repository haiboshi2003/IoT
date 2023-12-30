package com.example.iotbankend.Service;

import com.example.iotbankend.pojo.Data;

import java.time.LocalDateTime;
import java.util.List;

public interface DataService {


    List<Data> getAllData();


    List<Data> getByDate(LocalDateTime date, LocalDateTime nextDate);
}
