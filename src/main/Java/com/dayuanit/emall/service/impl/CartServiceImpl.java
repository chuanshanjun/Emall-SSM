/*购物车实现类*/

package com.dayuanit.emall.service.impl;

import com.dayuanit.emall.exception.EmallException;
import com.dayuanit.emall.mapper.MallShoppingCartMapper;
import com.dayuanit.emall.pojo.MallShoppingCart;
import com.dayuanit.emall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private MallShoppingCartMapper mallShoppingCartMapper;

    //增加购物车数据
    @Override
    public void addCart(int userId, int goodId, int counts) {
        MallShoppingCart mallShoppingCart = new MallShoppingCart();
        mallShoppingCart.setUserId(userId);
        mallShoppingCart.setGoodsId(goodId);
        mallShoppingCart.setCounts(counts);
        mallShoppingCart.setStatus((byte)1);

        int rows = mallShoppingCartMapper.addCart(mallShoppingCart);
        if (1 != rows) {
            throw new EmallException("购物车增加失败");
        }
    }

    @Override
    public List<MallShoppingCart> listCart(int userId) {
        List<MallShoppingCart> list = mallShoppingCartMapper.listCart(userId);
        if (null == list) {
            throw new EmallException("购物车内无商品");
        }

        return list;
    }

    @Override
    public void deleteShoppingCartGoodsId(int shoppingCartGoodId) {
        int rows = mallShoppingCartMapper.deleteShoppingCartGoods(shoppingCartGoodId);
        if (1 != rows) {
            throw new EmallException("删除失败");
        }
    }

    @Override
    public MallShoppingCart getCartShoppingId(int mallShoppingId) {
        MallShoppingCart mallShoppingCart = mallShoppingCartMapper.getMallShoppingCartById(mallShoppingId);
        if (null == mallShoppingCart) {
            throw new EmallException("获取购物车信息失败");
        }

        return mallShoppingCart;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.8.135", 6379);
        String msg = jedis.get("test:redis");
        System.out.println(msg);

        JedisPool jedisPool = new JedisPool("192.168.8.135", 6379);
        jedis = jedisPool.getResource();
        String msg2 = jedis.get("test:redis");
        System.out.println(msg2);
    }
}
