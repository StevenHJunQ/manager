package com.aoxing.it.service;

import com.aoxing.it.bean.Form;
import com.aoxing.it.dto.request.AddFormRequest;
import com.aoxing.it.dto.request.PageRequest;
import com.aoxing.it.dto.response.BaseResponse;
import com.aoxing.it.dto.vo.FormVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author hejq9
 * @date 2019-10-26
 */
public interface FormService {

    BaseResponse addForm(AddFormRequest request);

    IPage<Form> getFormList(PageRequest<FormVo> request);

    BaseResponse updateForm(FormVo formVo);

    BaseResponse removeForm(FormVo formVo);

    BaseResponse removeFormList(List<FormVo> formVoList);
}
