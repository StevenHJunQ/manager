package com.aoxing.it.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author hejq9
 * @date 2019-10-26
 */
@Data
public class Form {

    @TableId
    private Long formId;

    private String name;

    private String region;

    private String date1;

    private String date2;

    private Integer delivery;

    private String type;

    private String resource;

    private String description;

    private String options;
}
