package com.cmsystem.wms.controller;

import com.cmsystem.wms.entity.Result;
import com.cmsystem.wms.entity.ValidClass;
import com.cmsystem.wms.form.DeleteIdsForm;
import com.cmsystem.wms.mapper.DepartMapper;
import com.cmsystem.wms.model.Depart;
import com.cmsystem.wms.service.DepartService;
import com.cmsystem.wms.service.PageService;
import com.cmsystem.wms.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/depart")
@Api(description = "部门管理")
public class DepartController {

    @Autowired
    DepartMapper departMapper;

    @Autowired
    PageService pageService;

    @Autowired
    DepartService departService;

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/list")
    @ApiOperation(value = "部门列表")
    Result list(Depart depart) {
        pageService.setPaginate();
        List<Depart> list = departMapper.selectWhere(depart, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping("/select_all")
    @ApiOperation(value = "所有部门")
    Result selectAll() {
        return Result.success(departMapper.selectAll());
    }

    @GetMapping("/get_by_id")
    @ApiOperation(value = "根据id查找部门")
    Result getById(Integer id) {
        return Result.success(departMapper.findById(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增部门")
    Result insert(@Validated @RequestBody Depart depart, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        depart.setCreateUid(userInfoService.getLoginUser().getId());
        departMapper.insert(depart);
        return Result.success("添加部门成功!");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改部门信息")
    Result update(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Depart depart, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        depart.setUpdateUid(userInfoService.getLoginUser().getId());
        departMapper.update(depart);
        return Result.success("修改部门信息成功!");
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除部门")
    Result delete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = departMapper.deleteByIds(form.getIds());
        return Result.success("成功删除 " + num + "条数据!");
    }
}
