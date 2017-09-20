package com.dayuanit.emall.controller;

import com.dayuanit.emall.dto.AjaxResultDTO;
import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.pojo.MallShoppingCart;
import com.dayuanit.emall.service.CartService;
import com.dayuanit.emall.service.GoodService;
import com.mysql.jdbc.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopCartController extends BaseController{

    Logger log = LoggerFactory.getLogger(ShopCartController.class);

    @Autowired
    private GoodService goodService;

    @Autowired
    private CartService cartService;

    /**
     * 将商品详情页的商品信息加到购物车
     */
    @RequestMapping("/shopCart/toOrder")
    @ResponseBody
    public AjaxResultDTO toOrder(String goodsId, String goodsCounts, HttpServletRequest req) {
        try {
            cartService.addCart(getUserId(req), Integer.parseInt(goodsId), Integer.parseInt(goodsCounts));
        } catch (EmallException ee) {
            log.info("购物车增加失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.info("购物车增加异常:{}", e);
            return AjaxResultDTO.failed("系统故障请联系客服");
        }

        return AjaxResultDTO.success();
    }

    /**
     * 去往购物车页面
     */
    @RequestMapping("/shopCart/toCart")
    public String toCart() {
        return "cart";
    }

    /**
     * 展示购物车中的商品(将该用户下的所有加入购物车的信息查出)
     */
    @RequestMapping("/shopCart/showGoods")
    @ResponseBody
    public AjaxResultDTO showGoods(HttpServletRequest req) {
        List<MallShoppingCart> list = null;

        try {
            list = cartService.listCart(getUserId(req));
        } catch (EmallException ee) {
            log.error("购物展示商品失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.info("购物车展示商品异常：{}", e);
            return AjaxResultDTO.failed("系统故障请联系客户");
        }

        return AjaxResultDTO.success(list);
    }

    /**
     * 根据购物车的ID查出具体商品信息
     */
    @RequestMapping("/shopCart/showGoodsDetail")
    @ResponseBody
    public AjaxResultDTO showGoodsDetail(int typeId) {
        MallShoppingCart mallShoppingCart = null;

        try {
            mallShoppingCart = cartService.getCartShoppingId(typeId);//这里传回来的typeId是购物车的ID，还要通过这个ID查出货物
        } catch (EmallException ee) {
            log.error("获取购物车信息失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.error("获取购物车信息异常:{}", e);
            return AjaxResultDTO.failed("系统异常请联系客服");
        }

        MallGoods mallGoods = null;

        try {
            mallGoods = goodService.getGoodsById(mallShoppingCart.getGoodsId());
        } catch (EmallException ee) {
            log.info("查询具体商品信息失败:{}", ee.getMessage());
            return AjaxResultDTO.failed(ee.getMessage());
        } catch (Exception e) {
            log.info("查询具体商品信息异常:{}", e);
            return AjaxResultDTO.failed("系统异常请联系客服");
        }

        return AjaxResultDTO.success(mallGoods);
    }

    //删除购物车中的东西
    @RequestMapping("/shopCart/deleteGoods")
    public String deleteGoods(int shoppingCartId) {
        try {
            cartService.deleteShoppingCartGoodsId(shoppingCartId);
        } catch (EmallException ee) {
            log.info("删除失败{}", ee.getMessage());
        }

        return "cart";
    }
}
