package com.dayuanit.emall.service;

import com.dayuanit.emall.dto.AddressDTO;
import com.dayuanit.emall.pojo.MallAddress;
import com.dayuanit.emall.vo.AddressVO;

import java.util.List;

public interface MallAddressService {

    void addAddress(AddressVO addressVO, int userId);

    List<AddressDTO> listAddress(int userId);

    void modify(AddressVO addressVO, int userId);

    void delAddress(int addressId, int userId);

}
