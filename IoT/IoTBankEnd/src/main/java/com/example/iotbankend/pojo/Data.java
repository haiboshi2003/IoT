package com.example.iotbankend.pojo;

import java.time.LocalDateTime;

@lombok.Data
public class Data {
    private LocalDateTime time;
    private double temperature;
    private Integer pressure;
    private  Integer humidity;
}
