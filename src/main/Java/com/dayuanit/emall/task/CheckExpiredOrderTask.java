package com.dayuanit.emall.task;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.mapper.MallOrderDetailMapper;
import com.dayuanit.emall.mapper.MallOrderMapper;
import com.dayuanit.emall.pojo.MallOrder;
import com.dayuanit.emall.pojo.MallOrderDetail;
import com.dayuanit.emall.service.GoodService;
import com.dayuanit.emall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("checkExpiredOrderTask")
public class CheckExpiredOrderTask {

    private static final Logger log = LoggerFactory.getLogger(CheckExpiredOrderTask.class);

    @Autowired
    private MallOrderMapper mallOrderMapper;

    @Autowired
    private MallOrderDetailMapper mallOrderDetailMapper;

    @Autowired
    private GoodService goodService;

    @Autowired
    private OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    public void doTask2() {
        log.info(">>>doTask");

        List<MallOrder> list = mallOrderMapper.listExpiredOrder();
        for (MallOrder mallOrder : list) {
            Date orderTime = mallOrder.getModifyTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(orderTime);
            calendar.add(Calendar.MINUTE, 30);
            Date expDate = calendar.getTime();
            if (new Date().after(expDate)) {
                List<MallOrderDetail> listMallOrderDetail = mallOrderDetailMapper.listMallOrderDetail(mallOrder.getId());
                for (MallOrderDetail mallOrderDetail : listMallOrderDetail) {
                    goodService.addGoodsNum(mallOrderDetail.getGoodId(), mallOrderDetail.getCounts());
                }

                int rows = mallOrderMapper.changeOrderStatus(3,mallOrder.getUserId(), mallOrder.getId());
                if (1 != rows) {
                    throw new EmallException("订单状态改为失效状态失败");
                }

            }
        }
    }

    public void  doTask() {
        int offSet = 0;
        List<MallOrder> list = null;
        int i = 0;
        while (true) {
            list = mallOrderMapper.listOrderByStatus(1, 0, 20);
            if (list.size() <= 0) {
                break;
            }
            process(list);
            offSet =  offSet + 20;
        }
    }

    public void process(List<MallOrder> list) {
        for (MallOrder mallOrder : list) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mallOrder.getModifyTime());
            calendar.add(Calendar.MINUTE, 30);

            if (calendar.getTime().after(new Date())) {
                continue;
            }

            log.info(">>>处理订单{}", mallOrder.getId());

            try {
                orderService.processExpiredOrder(mallOrder);
            } catch (Exception e) {
                log.error("处理订单失败", e);
            }
        }
    }
}
