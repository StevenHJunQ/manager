package com.aoxing.it.service.impl;

import com.aoxing.it.bean.Form;
import com.aoxing.it.dto.request.AddFormRequest;
import com.aoxing.it.dto.request.PageRequest;
import com.aoxing.it.dto.response.BaseResponse;
import com.aoxing.it.dto.vo.FormVo;
import com.aoxing.it.mapper.FormMapper;
import com.aoxing.it.service.FormService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hejq9
 * @date 2019-10-26
 */
@Service("formService")
public class FormServiceImpl implements FormService {

    @Autowired
    private FormMapper formMapper;

    @Override
    public BaseResponse addForm(AddFormRequest request) {
        Form form = new Form();
        BeanUtils.copyProperties(request, form);
        StringBuilder typeSb = new StringBuilder();
        if (request.getType() != null) {
            for (String type : request.getType()) {
                typeSb.append(type).append("/");
            }
            String type = typeSb.toString();
            if (type.length() > 0) {
                type = type.substring(0, type.length() - 1);
            }
            form.setType(type);
        }
        if (request.getOptions() != null) {
            StringBuilder optionsSb = new StringBuilder();
            for (String option : request.getOptions()) {
                optionsSb.append(option).append("/");
            }
            String options = optionsSb.toString();
            if (options.length() > 0) {
                options = options.substring(0, options.length() - 1);
            }
            form.setOptions(options);
        }
        form.setDelivery(request.getDelivery() ? 1 : 0);
        form.setDescription(request.getDesc());
        int result = formMapper.insert(form);
        BaseResponse response = new BaseResponse();
        if (result == 1) {
            response.setCode(200);
            response.setDesc("addForm success");
        } else {
            response.setCode(500);
            response.setDesc("addForm error");
        }
        return response;
    }

    @Override
    public IPage<Form> getFormList(PageRequest<FormVo> request) {
        Page<Form> page = new Page<>(request.getPageIndex(), request.getPageSize());
        FormVo formVo = request.getVo();
        Form form = new Form();
        BeanUtils.copyProperties(formVo, form);
        QueryWrapper<Form> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(formVo.getName())){
            wrapper.like("name", formVo.getName());
        }
        IPage<Form> iPage = formMapper.selectPage(page, wrapper);
        return iPage;
    }

    @Override
    public BaseResponse updateForm(FormVo formVo) {
        Form form = new Form();
        BeanUtils.copyProperties(formVo, form);
        form.setFormId(formVo.getId());
        form.setDescription(formVo.getDesc());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        form.setDate1(format.format(new Date()));
        int result = formMapper.updateById(form);
        BaseResponse response = new BaseResponse();
        if (result == 1) {
            response.setCode(200);
            response.setDesc("updateForm success");
        } else {
            response.setCode(500);
            response.setDesc("updateForm error");
        }
        return response;
    }

    @Override
    public BaseResponse removeForm(FormVo formVo) {
        Form form = new Form();
        form.setFormId(formVo.getId());
        UpdateWrapper<Form> wrapper = new UpdateWrapper<>(form);
        int result = formMapper.delete(wrapper);
        BaseResponse response = new BaseResponse();
        if (result == 1) {
            response.setCode(200);
            response.setDesc("removeForm success");
        } else {
            response.setCode(500);
            response.setDesc("removeForm error");
        }
        return response;
    }

    @Transactional
    @Override
    public BaseResponse removeFormList(List<FormVo> formVoList) {
        List<Long> ids = new ArrayList<>();
        if (formVoList != null) {
            for (FormVo vo : formVoList) {
                ids.add(vo.getId());
            }
        }
        int result = formMapper.deleteBatchIds(ids);
        BaseResponse response = new BaseResponse();
        if (result >= 1) {
            response.setCode(200);
            response.setDesc("removeFormList success");
        } else {
            response.setCode(500);
            response.setDesc("removeFormList error");
        }
        return response;
    }
}
