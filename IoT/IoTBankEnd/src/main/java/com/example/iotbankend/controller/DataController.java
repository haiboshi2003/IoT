package com.example.iotbankend.controller;

import com.example.iotbankend.Service.DataService;
import com.example.iotbankend.pojo.Data;
import com.example.iotbankend.pojo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;
    
    @GetMapping("/getAll")
    public Result getAllData() {
        return Result.success(dataService.getAllData());
    }

    @GetMapping("/getByDate")
    public Result getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        System.out.print("\n date:");
        System.out.print(date);
        LocalDateTime startDate = date.atStartOfDay().withHour(0).withMinute(0).withSecond(0);
        System.out.print("\n date:");
        System.out.print(startDate);
        LocalDateTime nextDate = startDate.plusDays(1).withSecond(0);
        System.out.print("\n date:");
        System.out.print(nextDate);
        return Result.success(dataService.getByDate(startDate, nextDate));

    }


}
