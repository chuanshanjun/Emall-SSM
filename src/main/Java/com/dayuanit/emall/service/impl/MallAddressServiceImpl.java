package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.dto.AddressDTO;
import com.dayuanit.emall.enums.MallAddressDefault;
import com.dayuanit.emall.enums.MallAddressStatus;
import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.mapper.MallAddressMapper;
import com.dayuanit.emall.mapper.ProvinceAndCityMapper;
import com.dayuanit.emall.pojo.MallAddress;
import com.dayuanit.emall.service.MallAddressService;
import com.dayuanit.emall.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallAddressServiceImpl implements MallAddressService{

    @Autowired
    private MallAddressMapper mallAddressMapper;

    private MallAddress mallAddress;

    @Autowired
    private ProvinceAndCityMapper provinceAndCityMapper;

    @Override
    public void addAddress(AddressVO addressVO, int userId) {
        mallAddress = new MallAddress();
        mallAddress.setRealName(addressVO.getRealName());
        mallAddress.setProvinceWord(addressVO.getProvinceWord());
        mallAddress.setProvince(addressVO.getProvinceCode());
        mallAddress.setCityWord(addressVO.getCityWord());
        mallAddress.setCity(addressVO.getCityCode());
        mallAddress.setAreaWord(addressVO.getAreaWord());
        mallAddress.setArea(addressVO.getAreaCode());
        mallAddress.setAccurateAddress(addressVO.getAccurateAddress());
        mallAddress.setCellphoneNum(addressVO.getCellphoneNum());
        mallAddress.setDefaultAddress(addressVO.getDefaultAddress());
        mallAddress.setUserId(userId);
        mallAddress.setStatus(1);

        int rows = mallAddressMapper.addAddress(mallAddress);

        if (1 != rows) {
            throw new EmallException("增加地址失败");
        }
    }

    @Override
    public List<AddressDTO> listAddress(int userId) {
        List<AddressDTO> addressDtoList = new ArrayList<AddressDTO>();

        List<MallAddress> list = mallAddressMapper.listAddress(userId);

        AddressDTO addressDTO = null;

        for (MallAddress mallAddress : list) {
            addressDTO = new AddressDTO();
            addressDtoList.add(addressDTO);//为了防止防止遗漏放入list中，可在这先将对象放入List中
            addressDTO.setId(mallAddress.getId());

            addressDTO.setProvinceCode(mallAddress.getProvince());
            addressDTO.setProvinceWord(mallAddress.getProvinceWord());

            addressDTO.setCityCode(mallAddress.getCity());
            addressDTO.setCityWord(mallAddress.getCityWord());

            addressDTO.setAreaCode(mallAddress.getArea());
            addressDTO.setAreaWord(mallAddress.getAreaWord());

            addressDTO.setAccurateAddress(mallAddress.getAccurateAddress());

            addressDTO.setCellphoneNum(mallAddress.getCellphoneNum());

            addressDTO.setRealName(mallAddress.getRealName());

            addressDTO.setStatus(MallAddressStatus.getEnum(mallAddress.getStatus()).getV());
            /**
             * 此处本想将addressDTO中的默认地址使用使用"汉字"但考虑到需要传输到页面数字进行校验所以又改回来了
             */
//            addressDTO.setDefaultAddress(MallAddressDefault.getEnum(mallAddress.getDefaultAddress()).getV());
            addressDTO.setDefaultAddress(String.valueOf(mallAddress.getDefaultAddress()));
        }

        return addressDtoList;
    }

    @Override
    public void modify(AddressVO addressVO, int userId) {

        MallAddress mallAddress = new MallAddress();

        mallAddress.setRealName(addressVO.getRealName());
        mallAddress.setCellphoneNum(addressVO.getCellphoneNum());
        mallAddress.setProvince(addressVO.getProvinceCode());
        mallAddress.setProvinceWord(provinceAndCityMapper.getProvinceName(Integer.parseInt(addressVO.getProvinceCode())));
        mallAddress.setCity(addressVO.getCityCode());
        mallAddress.setCityWord(provinceAndCityMapper.getCityName(Integer.parseInt(addressVO.getCityCode())));
        mallAddress.setArea(addressVO.getAreaCode());
        mallAddress.setAreaWord(provinceAndCityMapper.getAreaName(Integer.parseInt(addressVO.getAreaCode())));
        mallAddress.setAccurateAddress(addressVO.getAccurateAddress());
        mallAddress.setDefaultAddress(addressVO.getDefaultAddress());
        mallAddress.setId(addressVO.getId());

        int rows = mallAddressMapper.modify(mallAddress);

        if (1 != rows) {
            throw new EmallException("修改失败");
        }
    }

    @Override
    public void delAddress(int addressId, int userId) {
        MallAddress mallAddress = mallAddressMapper.getAddressById(addressId);

        if (null == mallAddress) {
            throw new EmallException("此地址不存在");
        }

        if (mallAddress.getUserId().intValue() != userId) {
            throw new EmallException("无权操作");
        }

        int rows = mallAddressMapper.deleteAddress(addressId);

        if (1 != rows) {
            throw new EmallException("删除地址失败");
        }
    }

}
