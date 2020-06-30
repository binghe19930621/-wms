package com.cmsystem.wms.controller;

import com.cmsystem.wms.controller.base.BaseController;
import com.cmsystem.wms.entity.Result;
import com.cmsystem.wms.mapper.LoginRecordMapper;
import com.cmsystem.wms.model.LoginRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/logger")
@Api(description = "日志接口")
public class LoggerController extends BaseController {

    @Autowired
    LoginRecordMapper loginRecordMapper;

    @GetMapping(value = "/login_record_list")
    @ApiOperation(value = "用户登录登出日志")
    Result list(LoginRecord form) {
        pageService.setPaginate();
        List<LoginRecord> list = loginRecordMapper.selectWhere(form, pageService.createSort());
        return Result.successPage(list);
    }


}
