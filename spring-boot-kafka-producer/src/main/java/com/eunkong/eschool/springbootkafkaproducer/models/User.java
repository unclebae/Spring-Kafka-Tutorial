package com.eunkong.eschool.springbootkafkaproducer.models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class User implements Serializable {

    private String name;
    private String dept;
    private Long salary;

}
