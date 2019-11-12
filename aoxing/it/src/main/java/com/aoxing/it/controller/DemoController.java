package com.aoxing.it.controller;

import com.aoxing.it.bean.Form;
import com.aoxing.it.dto.request.AddFormRequest;
import com.aoxing.it.dto.request.PageRequest;
import com.aoxing.it.dto.response.BaseResponse;
import com.aoxing.it.dto.response.CaptchasResponse;
import com.aoxing.it.dto.response.PageResponse;
import com.aoxing.it.dto.vo.FormVo;
import com.aoxing.it.service.FormService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hejq9
 * @date 2019-10-26
 */
@CrossOrigin
@RestController
public class DemoController {

    @Autowired
    private FormService formService;

    /**
     * 插入表单
     *
     * @return
     */
    @PostMapping("/addForm")
    public BaseResponse addForm(@Valid @RequestBody AddFormRequest request) {
        return formService.addForm(request);
    }

    @PostMapping("/getFormList")
    public PageResponse<FormVo> getFormList(@Valid @RequestBody PageRequest<FormVo> request) {
        PageResponse<FormVo> response = new PageResponse<>();
        IPage<Form> iPage = formService.getFormList(request);
        response.setPageTotal(((Long) iPage.getTotal()).intValue());
        List<FormVo> list = new ArrayList<>();
        for (Form form : iPage.getRecords()) {
            FormVo formVo = new FormVo();
            BeanUtils.copyProperties(form, formVo);
            formVo.setId(form.getFormId());
            formVo.setDesc(form.getDescription());
            list.add(formVo);
        }
        response.setList(list);
        response.setCode(200);
        response.setDesc("success");
        return response;
    }

    @PostMapping("/updateForm")
    public BaseResponse updateForm(@Valid @RequestBody FormVo formVo) {
        return formService.updateForm(formVo);
    }

    @PostMapping("/removeForm")
    public BaseResponse removeForm(@Valid @RequestBody FormVo formVo) {
        return formService.removeForm(formVo);
    }

    @PostMapping("/removeFormList")
    public BaseResponse removeFormList(@Valid @RequestBody List<FormVo> formVoList){
        return formService.removeFormList(formVoList);
    }
}
