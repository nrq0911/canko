package com.canko;

import com.alibaba.fastjson.JSON;
import com.canko.mapper.GoodsMapper;
import com.canko.model.ProductVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineApplicationTests {

	@Autowired
	private GoodsMapper goodsMapper;

	@Test
	public void contextLoads() {
		System.out.print(JSON.toJSONString(new ProductVO(goodsMapper.getGoodsById(1))));
	}

}
