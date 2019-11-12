package com.aoxing.it.dto.request;

import lombok.Data;

/**
 * @author hejq9
 * @date 2019-10-27
 */
@Data
public class PageRequest<T> {

    private Integer pageIndex;

    private Integer pageSize;

    private T vo;
}
