package com.dayuanit.emall.mapper;

import com.dayuanit.emall.pojo.MallOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallOrderDetailMapper {

    int save(MallOrderDetail mallOrderDetail);

    List<MallOrderDetail> listMallOrderDetail(@Param("mallOrderId") Integer mallOrderId);
}
