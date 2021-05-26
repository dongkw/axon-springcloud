package com.example.domain.aggregate.bean.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2021/1/25、1:28 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEvent {
    private String id;
    private String data;
}
