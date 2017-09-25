package com.dayuanit.emall.controller;

import com.alibaba.fastjson.JSON;
import com.dayuanit.emall.dto.AjaxResultDTO;
import com.dayuanit.emall.dto.GoodsOrderInfoDTO;
import com.dayuanit.emall.dto.MyOrderDTO;
import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.pojo.MallOrder;
import com.dayuanit.emall.service.OrderService;
import com.dayuanit.emall.service.impl.OrderServiceImpl;
import com.dayuanit.emall.util.PageUtils;
import com.dayuanit.emall.vo.CartVO;
import com.dayuanit.pay.dto.PayOrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController extends BaseController{

    Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/toMyOrder")
    public ModelAndView toMyOrder() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("myorder");
        return mv;
    }

    @RequestMapping("/order/toOrder")
    public ModelAndView toOrder(String mallOrderId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("mallOrderId", mallOrderId);
        mv.setViewName("order");
        return mv;
    }

    @RequestMapping("/order/loadOrders")
    @ResponseBody
    public AjaxResultDTO loadOrders(@RequestParam(required = false, defaultValue = "1") int currentPage, int status, HttpServletRequest req) {
        log.info(">>>status:{}", status);
        PageUtils<MyOrderDTO> pageUtils = null;
        try {
            pageUtils = orderService.listEffectiveOrder(currentPage, getUserId(req), status);
        } catch (EmallException ee) {
            log.error(">>>遍历我的订单失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error(">>>遍历我的订单异常:{}", e);
            return AjaxResultDTO.failed("系统异常请联系客服");
        }
        return AjaxResultDTO.success(pageUtils);
    }

    @RequestMapping("/order/order")
    public String order() {
        return "order";
    }

    @RequestMapping("/order/createOrder")
    @ResponseBody
    public AjaxResultDTO createOrder(String buyMsg, HttpServletRequest req, @RequestBody CartVO cartVO) {
        log.info("========================================buyMsg信息{}", buyMsg);
        log.info("===========================================msg2信息{}", cartVO);

        if (StringUtils.isAllBlank(buyMsg)) {//如果传过来的信息为空
            return AjaxResultDTO.failed("信息不合法");
        }

        //返回的是一个静态内部类的List，根据业务需要返回货物信息，以及数量将该信息装在内部类中在装入List中
        List<OrderServiceImpl.BuyGoodDetail> dto = orderService.createOrderFromCart(buyMsg, getUserId(req));

        return AjaxResultDTO.success(dto);
    }

    @RequestMapping("/order/createOrder4Json")
    @ResponseBody
    public AjaxResultDTO createOrder4Json(String buyMsg) {
        log.info("购买信息:{}, buyMsg");

        List<CartVO> list = null;
        try {
            list = JSON.parseArray(buyMsg, CartVO.class);//解析前台传来的buyMsg的JSON字符串
        } catch (EmallException ee) {
            log.error("下单失败", ee);
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("下单异常,{}", e);
            return AjaxResultDTO.failed("系统异常请联系客服");
        }

        return AjaxResultDTO.success();
    }

    @RequestMapping("/order/createOrder4JsonBody")
    @ResponseBody
    public AjaxResultDTO createOrder4JsonBody(@RequestBody List<CartVO> vos, HttpServletRequest req) {
        for (CartVO vo : vos) {
            log.info(">>>>>>>>>>>>vo.getGoodId{}", vo.getGoodId());
            log.info(">>>>>>>>>>>>vo.getGoodCounte{}", vo.getGoodCounts());
        }

        try {
            MallOrder mallOrder = orderService.createOrderFromCart(vos, getUserId(req));
            return AjaxResultDTO.success(mallOrder);
        } catch (EmallException ee) {
            log.error("创建订单失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("创建订单异常", e);
            return AjaxResultDTO.failed("系统异常请联系客服");
        }
    }

    @RequestMapping("/order/loadOrderInfo")
    @ResponseBody
    public AjaxResultDTO loadOrderInfo(int mallOrderId, HttpServletRequest req) {

        GoodsOrderInfoDTO dto = null;
        try {
            dto = orderService.loadOrderInfo(mallOrderId, getUserId(req));
        } catch (EmallException ee) {
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("加载结算信息异常", e);
            return AjaxResultDTO.failed("加载信息失败");
        }

        return AjaxResultDTO.success(dto);
    }

    @RequestMapping("/order/pay")
    @ResponseBody
    public AjaxResultDTO pay(int mallOrderId, int checkedAddressId, int checkedPayChannel, HttpServletRequest req) {
        log.info("用户确认支付:>>>>>>>>>>>mallOrderId{},checkedAddressId{},checkedPayChannel{}", mallOrderId, checkedAddressId, checkedPayChannel);
        Map<String, Object> map = null;
//        PayOrderDTO payOrderDTO = null;

        try {
          map = orderService.pay(mallOrderId, checkedAddressId, checkedPayChannel, getUserId(req));
//            payOrderDTO = orderService.payByDTO(mallOrderId, checkedAddressId, checkedPayChannel, getUserId(req));
        } catch (EmallException ee) {
            log.error("订单生成失败{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("生成订单时候其他异常{}", e);
            return AjaxResultDTO.failed("订单生成异常，请联系客服");
        }

        return AjaxResultDTO.success(map);
    }

    @RequestMapping("/order/orderPay")
    @ResponseBody
    public AjaxResultDTO orderPay(int orderId, HttpServletRequest req) {

        try {
            Map<String, Object> map = orderService.payFromOrder(orderId, getUserId(req));
            return AjaxResultDTO.success(map);
        } catch(EmallException ee) {
            log.error(">>>订单支付失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch(Exception e) {
            log.error("支付失败", e);
            return AjaxResultDTO.failed("支付失败，请联系客服");
        }
    }
}
