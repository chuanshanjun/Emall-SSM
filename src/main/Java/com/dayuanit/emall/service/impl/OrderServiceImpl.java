package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.dto.GoodsOrderInfoDTO;
import com.dayuanit.emall.dto.MyOrderDTO;
import com.dayuanit.emall.enums.GoodStatusEnum;
import com.dayuanit.emall.enums.OrderFromEnum;
import com.dayuanit.emall.enums.OrderStatusEnum;
import com.dayuanit.emall.enums.PayChannelEnum;
import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.mapper.MallAddressMapper;
import com.dayuanit.emall.mapper.MallGoodsMapper;
import com.dayuanit.emall.mapper.MallOrderDetailMapper;
import com.dayuanit.emall.mapper.MallOrderMapper;
import com.dayuanit.emall.pojo.MallAddress;
import com.dayuanit.emall.pojo.MallGoods;
import com.dayuanit.emall.pojo.MallOrder;
import com.dayuanit.emall.pojo.MallOrderDetail;
import com.dayuanit.emall.service.GoodService;
import com.dayuanit.emall.service.OrderService;
import com.dayuanit.emall.util.DateUtils;
import com.dayuanit.emall.util.MoneyUtils;
import com.dayuanit.emall.util.PageUtils;
import com.dayuanit.emall.vo.CartVO;
import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.dto.PayOrderDTO;
import com.dayuanit.pay.service.PayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.ExemptionMechanismException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService{

    public static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Autowired
    private MallOrderDetailMapper mallOrderDetailMapper;

    @Autowired
    private MallAddressMapper mallAddressMapper;

    @Autowired
    private PayService payService;

    @Autowired
    private GoodService goodService;

    @Override
    public List<BuyGoodDetail> createOrderFromCart(String buyMsg, int userId) {
        String[] goods = buyMsg.split("\\$");
        log.info("======================String[] goods信息:{}", goods);

        List<BuyGoodDetail> gds = new ArrayList<BuyGoodDetail>();
        for (String goodmsg : goods) {
            if (StringUtils.isAllBlank(goodmsg)) {
                continue;
            }

            String[] goodDetail = goodmsg.split("\\-");

            log.info("================goodDetail信息:{}", goodDetail);

            //校验商品
            MallGoods mallGoods = mallGoodsMapper.getGoodById(Integer.valueOf(goodDetail[0]));
            if (null == mallGoods) {
                throw new EmallException("商品不存在");
            }

            if (mallGoods.getStatus() == 2) {
                throw new EmallException("商品已经下架");
            }

            if (mallGoods.getInStock().intValue() < Integer.parseInt(goodDetail[1])) {
                throw new EmallException("商品库存不足");
            }

            //先生成订单再生成订单详情
            BuyGoodDetail gd = new BuyGoodDetail();
            gds.add(gd);

            gd.setGoods(mallGoods);
            gd.setCounts(Integer.parseInt(goodDetail[1]));
        }

        processOrderDetail(gds, userId);

        return gds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MallOrder createOrderFromCart(List<CartVO> vos, int userId, int orderFrom) {
        //定义好订单明细的List并定义好了List的长度节省空间
        List<MallOrderDetail> goodList = new ArrayList<MallOrderDetail>(vos.size());

        //定义好总金额
        String totalMoney = "0";

        for (CartVO vo : vos) {
            MallGoods good = mallGoodsMapper.getGoodById(vo.getGoodId());
            if (good.getStatus() == GoodStatusEnum.OFF_SHELVE.getK()) {
                throw new EmallException(String.format("商品s%下架", good.getName()));
            }

            if (good.getInStock() < vo.getGoodCounts()) {
                throw new EmallException(String.format("商品s%数量不够", good.getName()));
            }

            //生成详细订单
            MallOrderDetail mallOrderDetail = new MallOrderDetail();
            String goodTotalMoney = MoneyUtils.mul(good.getPrice(), String.valueOf(vo.getGoodCounts()));

            mallOrderDetail.setUnitPrice(good.getPrice());
            mallOrderDetail.setCounts(vo.getGoodCounts());
            mallOrderDetail.setGoodName(good.getName());
            mallOrderDetail.setAmount(goodTotalMoney);
            mallOrderDetail.setGoodId(good.getId());
            goodList.add(mallOrderDetail);

            totalMoney = MoneyUtils.plus(totalMoney, goodTotalMoney);
        }

        //生成总订单
        MallOrder mallOrder = new MallOrder();
        mallOrder.setAmount(totalMoney);
        mallOrder.setOrderFrom(OrderFromEnum.CART.getK());
        mallOrder.setStatus(OrderStatusEnum.WAIT_SETTLEMENT.getK());
        mallOrder.setUserId(userId);
        mallOrder.setPayChannel(0);
        mallOrder.setProvince("");
        mallOrder.setCity("");
        mallOrder.setArea("");
        mallOrder.setPhone(0);
        mallOrder.setRealName("");
        mallOrder.setDetail("");

        int rows = mallOrderMapper.createOrder(mallOrder);
        if (1 != rows) {
            throw new EmallException("订单生成失效");
        }

        //生成明细订单
        for (MallOrderDetail mod : goodList) {
            mod.setOrderId(mallOrder.getId());
            rows = mallOrderDetailMapper.save(mod);
            if (1 != rows) {
                throw new EmallException("详细订单生成失败呢");
            }
        }

        return mallOrder;
    }

    @Override
    public GoodsOrderInfoDTO loadOrderInfo(int mallOrderId, int userId) {
        MallOrder mallOrder = mallOrderMapper.getOrderById(mallOrderId);
        if (null == mallOrder) {
            throw new EmallException("订单不存在");
        }

        if (userId != mallOrder.getUserId()) {
            throw new EmallException("不是你的订单无权操作");
        }

        GoodsOrderInfoDTO dto = new GoodsOrderInfoDTO();
        dto.setAmount(mallOrder.getAmount());

        List<MallOrderDetail> list = mallOrderDetailMapper.listMallOrderDetail(mallOrderId);

        List<GoodsOrderInfoDTO.GoodsInfo> goods = new ArrayList<GoodsOrderInfoDTO.GoodsInfo>(list.size());

        for (MallOrderDetail mod : list) {
            GoodsOrderInfoDTO.GoodsInfo info = new GoodsOrderInfoDTO.GoodsInfo();
            goods.add(info);

            info.setAmount(mod.getAmount());
            info.setGoodName(mod.getGoodName());
            info.setCounts(mod.getCounts());
        }

        dto.setGoods(goods);

        return dto;
    }

    private void processOrderDetail(List<BuyGoodDetail> gds, int userId) {
        //先生成订单
        MallOrder mallOrder = new MallOrder();
        mallOrder.setOrderFrom(2);
        mallOrder.setStatus(0);
        mallOrder.setUserId(userId);

        String totalAmount = "0";

        //通过货物的数量和单价计算出订单的总价
        for (BuyGoodDetail gd : gds) {
            String amount = MoneyUtils.mul(String.valueOf(gd.getCounts()), String.valueOf(gd.getGoods().getPrice()));
            totalAmount = MoneyUtils.plus(amount, totalAmount);
            log.info("===============totalAmount:{}", totalAmount);
        }

        mallOrder.setAmount(totalAmount);

        //在数据库中生成订单
        int rows = mallOrderMapper.createOrder(mallOrder);

        if (1 != rows) {
            throw new EmallException("创建订单失败");
        }

        for (BuyGoodDetail gd : gds) {
            MallOrderDetail mallOrderDetail = new MallOrderDetail();
            String amount = MoneyUtils.mul(String.valueOf(gd.getCounts()), String.valueOf(gd.getGoods().getPrice()));
            mallOrderDetail.setAmount(amount);
            mallOrderDetail.setGoodId(gd.getGoods().getId());
            mallOrderDetail.setGoodName(gd.getGoods().getName());
            mallOrderDetail.setOrderId(mallOrder.getId());
            mallOrderDetail.setCounts(gd.getCounts());
            mallOrderDetail.setUnitPrice(String.valueOf(gd.getGoods().getPrice()));
            rows = mallOrderDetailMapper.save(mallOrderDetail);
            if (1 != rows) {
                throw new EmallException("创建订单失败");
            }

        }
    }

    //静态内部类，用于装载需要返回给前台的信息
    public static class BuyGoodDetail {
        private MallGoods goods;
        private int counts;
        public MallGoods getGoods() {
            return goods;
        }
        public void setGoods(MallGoods goods) {
            this.goods = goods;
        }
        public int getCounts() {
            return counts;
        }
        public void setCounts(int counts) {
            this.counts = counts;
        }
    }

    /**
     * 开启事物防止支付完后的减库存操作失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> pay(int mallOrderId, int checkedAddressId, int checkedPayChannel, int userId) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>checkedAddressId:{}", checkedAddressId);
        MallOrder mallOrder = mallOrderMapper.getOrderById(mallOrderId);
        if (null == mallOrder) {
            throw new EmallException("订单不存在");
        }

        if (mallOrder.getUserId() != userId) {
            throw new EmallException("订单不属于你");
        }

        if (mallOrder.getStatus() != OrderStatusEnum.WAIT_SETTLEMENT.getK() &&
                mallOrder.getStatus() != OrderStatusEnum.WAIT_PAY.getK()) {
            throw new EmallException("订单生成失败");
        }

        //此处以订单修改的时间为订单失效时间的起点
        Date orderTime = mallOrder.getModifyTime();

        //Calendar是工具类 单例模式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderTime);
        calendar.add(Calendar.MINUTE, 30);//增加三十分钟，如果减少时间-30

        Date expDate = calendar.getTime();

        if (new Date().after(expDate)) {//现在的时间在订单失效之后
            throw new EmallException("订单失效");
        }

        if (mallOrder.getStatus() == OrderStatusEnum.WAIT_SETTLEMENT.getK()) {
            int rows = mallOrderMapper.changeOrderStatus(OrderStatusEnum.WAIT_PAY.getK(), userId, mallOrderId);
            if (rows != 1) {
                throw new EmallException("订单更新失败");
            }
        }

        mallOrder.setPayChannel(checkedPayChannel);//设置支付方式

        MallAddress mallAddress =mallAddressMapper.getAddressById(checkedAddressId);

        mallOrder.setProvince(mallAddress.getProvinceWord());
        mallOrder.setCity(mallAddress.getCityWord());
        mallOrder.setArea(mallAddress.getAreaWord());
        mallOrder.setPhone(mallAddress.getCellphoneNum());
        mallOrder.setRealName(mallAddress.getRealName());
        mallOrder.setDetail(mallAddress.getAccurateAddress());
        log.info(mallAddress.getAccurateAddress());
        int rows = mallOrderMapper.updateOrderInfo(mallOrder);
        if (rows != 1) {
            throw new EmallException("订单生成失败");
        }

        /**
         * 请求支付系统 获取支付地址，本需要相应支付系统的domain和service接口，后将相应的文件打包成jar包上传到私服
         */

        PayOrder payOrder = new PayOrder();
        payOrder.setAmount(mallOrder.getAmount());
        payOrder.setBankId(null);
        payOrder.setBizId(String.valueOf(mallOrder.getId()));//订单ID
        payOrder.setDetailMsg("大猿商城");
        payOrder.setPayChannel(mallOrder.getPayChannel());
        payOrder.setUserId(mallOrder.getUserId());
        log.info(">>>payOrder{}", payOrder.getAmount());
        /**
         * 调用支付系统的生成支付订单方法
         */
        Map<String, Object> map = payService.addPayOrder(payOrder);

        List<MallOrderDetail> listMallOrderDetail = mallOrderDetailMapper.listMallOrderDetail(mallOrderId);
        for (MallOrderDetail mallOrderDetail : listMallOrderDetail) {
            goodService.subGoodsNum(mallOrderDetail.getGoodId(), mallOrderDetail.getCounts());
        }

        return map;
    }

    @Override
    public PageUtils<MyOrderDTO> listEffectiveOrder(int currentPage,Integer userId, Integer status) {
        if (status < 0) {
            status = null;
        }

        int totalDataNum = mallOrderMapper.countListOrderByUserIdAndStatus(userId, status);
        PageUtils<MyOrderDTO> pageUtils = new PageUtils<MyOrderDTO>(currentPage, totalDataNum);

        List<MallOrder> listMallOrder = mallOrderMapper.listOrderByUserIdAndStatus(userId, status, pageUtils.getOffset(), PageUtils.PRE_PAGE_NUM);

        List<MyOrderDTO> listMyOrderDTO = new ArrayList<MyOrderDTO>(listMallOrder.size());

        for (MallOrder mo : listMallOrder) {
            MyOrderDTO myOrderDTO = new MyOrderDTO();
            myOrderDTO.setAmount(mo.getAmount());
            myOrderDTO.setId(mo.getId());
            myOrderDTO.setPayChannel(PayChannelEnum.getEnum(mo.getPayChannel()).getDesc());
            myOrderDTO.setUserRealName(mo.getRealName());
            myOrderDTO.setStatus(OrderStatusEnum.getEnum(mo.getStatus()).getV());
            myOrderDTO.setCreateTime(DateUtils.dateToString(mo.getCreateTime()));
            List<MallOrderDetail> listMallOrderDetail = mallOrderDetailMapper.listMallOrderDetail(mo.getId());
            List<MyOrderDTO.OrderGoods> listOrderGoods = new ArrayList<>(listMallOrderDetail.size());

            for (MallOrderDetail mod : listMallOrderDetail) {
                MyOrderDTO.OrderGoods og = new MyOrderDTO.OrderGoods();
                og.setGoodName(mod.getGoodName());
                og.setNum(mod.getCounts());
                og.setPrice(mod.getUnitPrice());
                listOrderGoods.add(og);
            }

            myOrderDTO.setGoods(listOrderGoods);

            listMyOrderDTO.add(myOrderDTO);
        }

        pageUtils.setData(listMyOrderDTO);

        return pageUtils;
    }

    /**
     * 在对订单进行处理前加锁
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processPayResult(int orderId, String payId) {
        MallOrder mallOrder = mallOrderMapper.getOrderById4Lock(orderId);
        if (null == mallOrder) {
            return;
        }

        //如果订单不是待付款的都退回去
        if (mallOrder.getStatus() != OrderStatusEnum.WAIT_PAY.getK()) {
            return;
        }

        int rows = mallOrderMapper.changeOrderStatus(OrderStatusEnum.PAID.getK(), mallOrder.getUserId(), orderId);
        if (1 != rows) {
            throw new EmallException("订单更新失败");
        }

        log.info(">>>订单{}支付成功，请及时发货", orderId);
    }

    @Override
    public PayOrderDTO payByDTO(int mallOrderId, int checkedAddressId, int checkedPayChannel, int userId) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>checkedAddressId:{}", checkedAddressId);
        MallOrder mallOrder = mallOrderMapper.getOrderById(mallOrderId);
        if (null == mallOrder) {
            throw new EmallException("订单不存在");
        }

        if (mallOrder.getUserId() != userId) {
            throw new EmallException("订单不属于你");
        }

        if (mallOrder.getStatus() != OrderStatusEnum.WAIT_SETTLEMENT.getK() &&
                mallOrder.getStatus() != OrderStatusEnum.WAIT_PAY.getK()) {
            throw new EmallException("订单生成失败");
        }

        //此处以订单修改的时间为订单失效时间的起点
        Date orderTime = mallOrder.getModifyTime();

        //Calendar是工具类 单例模式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderTime);
        calendar.add(Calendar.MINUTE, 30);//增加三十分钟，如果减少时间-30

        Date expDate = calendar.getTime();

        if (new Date().after(expDate)) {//现在的时间在订单失效之后
            throw new EmallException("订单失效");
        }

        if (mallOrder.getStatus() == OrderStatusEnum.WAIT_SETTLEMENT.getK()) {
            int rows = mallOrderMapper.changeOrderStatus(OrderStatusEnum.WAIT_PAY.getK(), userId, mallOrderId);
            if (rows != 1) {
                throw new EmallException("订单更新失败");
            }
        }

        mallOrder.setPayChannel(checkedPayChannel);//设置支付方式

        MallAddress mallAddress =mallAddressMapper.getAddressById(checkedAddressId);

        mallOrder.setProvince(mallAddress.getProvinceWord());
        mallOrder.setCity(mallAddress.getCityWord());
        mallOrder.setArea(mallAddress.getAreaWord());
        mallOrder.setPhone(mallAddress.getCellphoneNum());
        mallOrder.setRealName(mallAddress.getRealName());
        mallOrder.setDetail(mallAddress.getAccurateAddress());
        log.info(mallAddress.getAccurateAddress());
        int rows = mallOrderMapper.updateOrderInfo(mallOrder);
        if (rows != 1) {
            throw new EmallException("订单生成失败");
        }

        /**
         * 请求支付系统 获取支付地址，本需要相应支付系统的domain和service接口，后将相应的文件打包成jar包上传到私服
         */

        PayOrder payOrder = new PayOrder();
        payOrder.setAmount(mallOrder.getAmount());
        payOrder.setBankId(null);
        payOrder.setBizId(String.valueOf(mallOrder.getId()));//订单ID
        payOrder.setDetailMsg("大猿商城");
        payOrder.setPayChannel(mallOrder.getPayChannel());
        payOrder.setUserId(mallOrder.getUserId());

        /**
         * 调用支付系统的生成支付订单方法
         */

        log.info(">>>payOrder:{}", payOrder.getAmount());
        PayOrderDTO payOrderDTO = payService.addPayOrderByDTO(payOrder);
        return payOrderDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> payFromOrder(int mallOrderId, int userId) {
        MallOrder mallOrder = mallOrderMapper.getOrderById4Lock(mallOrderId);
        if (null == mallOrder) {
            throw new EmallException("订单不存在");
        }

        if (mallOrder.getUserId() != userId) {
            throw new EmallException("订单不属于你");
        }

        if (mallOrder.getStatus() != OrderStatusEnum.WAIT_PAY.getK()) {
            throw new EmallException("订单不能付款");
        }

        ////此处以订单修改的时间为订单失效时间的起点
        Date orderTime = mallOrder.getModifyTime();
        //Calendar是工具类 单例模式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderTime);
        //增加三十分钟，如果减少时间-30
        calendar.add(Calendar.MINUTE, 30);

        Date expDate = calendar.getTime();
        //现在的时间在订单失效之后
        if (new Date().after(expDate)) {
            throw new EmallException("订单失效");
        }

        PayOrder payOrder = new PayOrder();
        payOrder.setAmount(mallOrder.getAmount());
        payOrder.setBankId(null);
        payOrder.setBizId(String.valueOf(mallOrder.getId()));//订单ID
        payOrder.setDetailMsg("大猿商城");
        payOrder.setPayChannel(mallOrder.getPayChannel());
        payOrder.setUserId(mallOrder.getUserId());

        Map<String, Object> map = payService.addPayOrder(payOrder);

        //按照约定1001指该笔订单已经支付成功过了，那么我们将修改他的商户订单
        if (null != map.get("code") && map.get("code").toString().equals("1001")) {
            processPayResult(mallOrderId, map.get("payId").toString());
        }

        //其他状况如支付订单失效，或者支付中则将其信息返回
        if (null != map.get("code") && !map.get("code").toString().equals("1000")) {
            throw new EmallException(map.get("message").toString());
        }

        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processExpiredOrder(MallOrder mallOrder) {
        //开启事务后再使用悲观锁再查询下
        MallOrder mallOrder1 = mallOrderMapper.getOrderById4Lock(mallOrder.getId());
        if (null == mallOrder1) {
            throw new EmallException("订单不存在");
        }

        //如果订单状态不是待付款的就挡回去
        if (mallOrder1.getStatus() != OrderStatusEnum.WAIT_PAY.getK()) {
            return;
        }

        //再次判断下失效时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mallOrder1.getModifyTime());
        calendar.add(Calendar.MINUTE, 30);
        if (calendar.getTime().after(new Date())) {
            return;
        }

        //修改订单状态
        int rows = mallOrderMapper.changeOrderStatus(OrderStatusEnum.OUT_OF_VALUE.getK(), mallOrder1.getUserId(), mallOrder1.getId());
        if (1 != rows) {
            throw new EmallException("更改订单状态失败");
        }

        //增加库存
        List<MallOrderDetail> list =  mallOrderDetailMapper.listMallOrderDetail(mallOrder1.getId());

        //使用悲观锁再查询下
        for (MallOrderDetail mallOrderDetail : list) {
            MallGoods mallGoods = mallGoodsMapper.getGoodById4Update(mallOrderDetail.getGoodId());
            if (null == mallGoods) {
                throw new EmallException("需要增加库存的商品信息不存在");
            }
            rows = mallGoodsMapper.changeGoodsNum(mallGoods.getId(), mallOrderDetail.getCounts());
            if (1 != rows) {
                throw new EmallException("增加库存失败");
            }

            log.info(">>>>增加商品的ID{},数量{}", mallGoods.getId(), mallOrderDetail.getCounts());
        }
    }

}
