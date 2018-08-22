package com.powerwin.entity;

import lombok.Data;

/**
 * Created by fengjinman Administrator on 2018/8/14.
 */
@Data
public class StateLog {

    private String adid;

    private Integer state;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer hour;

    private Integer create;

    private Integer change_count;


}
