package com.aoxing.it.dto.request;

import lombok.Data;

import java.util.List;

/**
 * @author hejq9
 * @date 2019-10-26
 */
@Data
public class AddFormRequest {

    private String name;

    private String region;

    private String date1;

    private String date2;

    private Boolean delivery;

    private List<String> type;

    private String resource;

    private String desc;

    private List<String> options;
}
