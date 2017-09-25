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
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/good")
public class GoodIntroduce extends BaseController{

    Logger log = LoggerFactory.getLogger(GoodIntroduce.class);

    @Autowired
    private GoodService goodService;

    /**
     * 第二种防止重复提交的方法在即将跳转到商品展示页面的时候在session中设置token
     */
    @RequestMapping("/toGoodsIntroduce")
    public String toGoodsIntroduce(String goodId, ModelMap mm, HttpSession session) {
        try {
            MallGoods mallGood = goodService.getGoodsById(Integer.parseInt(goodId));
            mm.addAttribute("mallGood", mallGood);
            String token = UUID.randomUUID().toString();
            session.setAttribute("cart_token", token);
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

