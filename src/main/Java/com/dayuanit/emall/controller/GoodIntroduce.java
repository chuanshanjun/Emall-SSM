package com.dayuanit.emall.controller;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/good")
public class GoodIntroduce extends BaseController{

    Logger log = LoggerFactory.getLogger(GoodIntroduce.class);

    @Autowired
    private GoodService goodService;

    @RequestMapping("/toGoodsIntroduce")
    public String toGoodsIntroduce(String goodId, ModelMap mm) {
        try {
            MallGoods mallGood = goodService.getGoodsById(Integer.parseInt(goodId));
            mm.addAttribute("mallGood", mallGood);
        } catch (EmallException ee) {
            log.error("查询商品失败:{}", ee.getMessage());
        } catch (Exception e) {
            log.error("查询商品异常:{}", e);
        }

        return "goodsIntroduce";//去往商品详情页
    }

    /**
     * 两者写法都可
     */
//    @RequestMapping("/toGoodsIntroduce")
//    public String toGoodsIntroduce(String goodId, HttpServletRequest req) {
//        try {
//            MallGoods mallGood = goodService.getGoodsById(Integer.parseInt(goodId));
//            req.setAttribute("mallGood", mallGood);
//        } catch (EmallException ee) {
//            log.error("查询商品失败:{}", ee.getMessage());
//        } catch (Exception e) {
//            log.error("查询商品异常:{}", e);
//        }
//
//        return "goodsIntroduce";//去往商品详情页
//    }
}
