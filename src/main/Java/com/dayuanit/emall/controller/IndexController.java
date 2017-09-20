package com.dayuanit.emall.controller;

import com.dayuanit.emall.dto.AjaxResultDTO;
import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.pojo.MallType;
import com.dayuanit.emall.service.ShowService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/show")
public class IndexController extends BaseController{

    Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ShowService showService;

    @RequestMapping("/type")
    @ResponseBody
    public AjaxResultDTO type() {
        List<MallType> list = null;

        try {
            list = showService.showType();
        } catch (EmallException ee) {
            log.error(">>>遍历商品失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error(">>>遍历商品异常:{}", e);
            return AjaxResultDTO.failed("系统故障请联系客服");
        }

        return AjaxResultDTO.success(list);
    }

    @RequestMapping("/goods")
    @ResponseBody
    public AjaxResultDTO goods(int typeId) {
        List<MallGoods> list = null;
        try {
            list = showService.showGoods(typeId);
        } catch (EmallException ee) {
            log.error("遍历商品详情失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("遍历商品详情异常:{}", e.getMessage());
            return AjaxResultDTO.failed("系统故障请联系客服");
        }

        return AjaxResultDTO.success(list);
    }
}
