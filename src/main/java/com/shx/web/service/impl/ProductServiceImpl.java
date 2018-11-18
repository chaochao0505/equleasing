/**
 * @company 西安一体物联网科技有限公司
 * @file ProductServiceImpl.java
 * @author zhaochao
 * @date 2018年4月16日 
 */
package com.shx.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shx.web.dao.ProductInfoDao;
import com.shx.web.entity.ProductInfoEntity;
import com.shx.web.service.ProductService;
import com.shx.web.utils.page.PageList;
import com.shx.web.utils.page.PageQuery;

/**
 * @description :
 * @author : zhaochao
 * @version : V1.0.0
 * @date : 2018年4月16日
 */
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductInfoDao productInfoDao;


	@Override
	public PageList<ProductInfoEntity> findAll(PageQuery pageQuery) {
		PageList<ProductInfoEntity> list=productInfoDao.findAll(pageQuery);
		return list;
	}
	
}
