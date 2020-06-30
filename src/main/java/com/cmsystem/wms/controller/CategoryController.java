package com.cmsystem.wms.controller;


import com.cmsystem.wms.entity.Result;
import com.cmsystem.wms.entity.ValidClass;
import com.cmsystem.wms.form.DeleteIdsForm;
import com.cmsystem.wms.mapper.CategoryMapper;
import com.cmsystem.wms.model.Category;
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
@RequestMapping("/category")
@Api(description = "分类字典")
public class CategoryController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    PageService pageService;

    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping("/list")
    @ApiOperation(value = "分页列表")
    Result list(Category category) {
        pageService.setPaginate();
        List<Category> list = categoryMapper.selectWhere(category, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping("/get_by_id")
    @ApiOperation(value = "通过 id 查找")
    Result getById(Integer id) {
        return Result.success(categoryMapper.findById(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "添加分类字典")
    Result insert(@Validated @RequestBody Category category, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        category.setCreateUid(userInfoService.getLoginUser().getId());
        categoryMapper.insert(category);
        return Result.success("添加分类字典成功");
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新字典")
    Result update(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Category category, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        category.setUpdateUid(userInfoService.getLoginUser().getId());
        categoryMapper.update(category);
        return Result.success("更新字典成功");
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除字典")
    Result delete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = categoryMapper.delete(form.getIds());
        return Result.success("共删除" + num + "条数据");
    }

    @GetMapping("/select_by_type")
    @ApiOperation(value = "根据 type 获取字典")
    Result selectByType(Integer type) {
        return Result.success(categoryMapper.selectByType(type));
    }


}
