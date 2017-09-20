package com.dayuanit.emall.controller;

import com.dayuanit.emall.dto.AddressDTO;
import com.dayuanit.emall.dto.AjaxResultDTO;
import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.service.MallAddressService;
import com.dayuanit.emall.service.ProvinceAndCityService;
import com.dayuanit.emall.vo.AddressVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{

    Logger log = LoggerFactory.getLogger(AddressController.class);

    @Resource
    private ProvinceAndCityService provinceAndCityService;

    @Autowired
    private MallAddressService mallAddressService;

    @RequestMapping("/listProvince")
    @ResponseBody
    public AjaxResultDTO listProvince() {
        return AjaxResultDTO.success(provinceAndCityService.listProvince());
    }

//    @RequestMapping("/toAddress")
//    public String toAddress() {
//        return "address";
//    }

    @RequestMapping("/toAddress")//使用ModelAndView的效果和上面的一样
    public ModelAndView toAddress(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("address");

        return mv;
    }

    @RequestMapping("/listCity")
    @ResponseBody
    public AjaxResultDTO listCity(String provinceCode) {
        return AjaxResultDTO.success(provinceAndCityService.listCity(provinceCode));
    }

    @RequestMapping("/listArea")
    @ResponseBody
    public AjaxResultDTO listArea(String cityCode) {
        return AjaxResultDTO.success(provinceAndCityService.listArea(cityCode));
    }

    @RequestMapping("/addAddress")
    @ResponseBody
    public AjaxResultDTO addAddress(AddressVO addressVO, HttpServletRequest req) {
        addressVO.setProvinceWord(provinceAndCityService.getProvinceName(Integer.parseInt(addressVO.getProvinceCode())));
        addressVO.setCityWord(provinceAndCityService.getCityName(Integer.parseInt(addressVO.getCityCode())));
        addressVO.setAreaWord(provinceAndCityService.getAreaName(Integer.parseInt(addressVO.getAreaCode())));

        try {
            mallAddressService.addAddress(addressVO, getUserId(req));
        } catch (EmallException ee) {
            log.error("增加地址失败:{}", ee.getMessage());
            AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("其他异常:{}", e.getMessage());
            AjaxResultDTO.failed("系统故障请联系客服");
        }

        return AjaxResultDTO.success();
    }

    @RequestMapping("/listAddress")
    @ResponseBody
    public AjaxResultDTO listAddress(HttpServletRequest req) {
        List<AddressDTO> list = null;

        try {
            list = mallAddressService.listAddress(getUserId(req));
        } catch (EmallException ee) {
            log.error("遍历地址错误{}", ee.getMessage());
            return AjaxResultDTO.failed("展示地址错误");
        } catch (Exception e) {
            log.error("其他错误:{}", e.getMessage());
            return AjaxResultDTO.failed("系统故障请联系客服");
        }

        return AjaxResultDTO.success(list);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public AjaxResultDTO modify(AddressVO addressVO, HttpServletRequest req) {
        try {
            mallAddressService.modify(addressVO, getUserId(req));
        } catch (EmallException ee) {
            log.error("修改地址失败{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("其他异常:{}", e.getMessage());
            return  AjaxResultDTO.failed("联系客服");
        }

        return AjaxResultDTO.success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public AjaxResultDTO del(int addressId, HttpServletRequest req) {
        try {
            mallAddressService.delAddress(addressId, getUserId(req));
        } catch (EmallException ee) {
            log.error("删除失败:{}", ee.getMessage(), ee);
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("其他异常:{}", e.getMessage(), e);
            return AjaxResultDTO.failed("请联系客服");
        }

        return AjaxResultDTO.success();
    }
}
