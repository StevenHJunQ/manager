package com.aoxing.it.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author hejq9
 * @date 2019-10-27
 */
@Data
public class PageResponse<T> extends BaseResponse {

    private List<T> list;

    private Integer pageTotal;
}
