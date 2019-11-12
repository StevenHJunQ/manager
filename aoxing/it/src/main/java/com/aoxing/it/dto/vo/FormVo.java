package com.aoxing.it.dto.vo;

import lombok.Data;

/**
 * @author hejq9
 * @date 2019-10-27
 */
@Data
public class FormVo {

    private Long id;
    private String name;
    private String date1;
    private String desc;

    public FormVo(){}

    public FormVo(Long id, String name, String date1, String desc) {
        this.id = id;
        this.name = name;
        this.date1 = date1;
        this.desc = desc;
    }
}
