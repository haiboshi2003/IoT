package com.example.iotbankend.mapper;

import com.example.iotbankend.pojo.Data;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DataMapper {
    // 使用java.sql.Timestamp类型
    @Results({
            @Result(property = "time", column = "time", javaType = LocalDateTime.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM data")
    List<Data> getAllData();
    @Results({
            @Result(property = "time", column = "time", javaType = LocalDateTime.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM data WHERE time BETWEEN #{date} AND #{nextDate}")
    List<Data> getByDate(@Param("date") LocalDateTime date, @Param("nextDate") LocalDateTime nextDate);

    @Results({
            @Result(property = "time", column = "time", javaType = LocalDateTime.class, jdbcType = JdbcType.TIMESTAMP)
    })
    @Select("SELECT * FROM predictdata WHERE time BETWEEN #{startDate} AND #{nextDate}")
    List<Data> getPredate(LocalDateTime startDate, LocalDateTime nextDate);
}
