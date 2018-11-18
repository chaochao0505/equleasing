/**
 * @company 西安一体物联网科技有限公司
 * @file ProductInfoDao.java
 * @author zhaochao
 * @date 2018年4月16日 
 */
package com.shx.web.dao;

import java.util.List;

import com.shx.web.entity.ProductInfoEntity;
import com.shx.web.utils.page.PageList;
import com.shx.web.utils.page.PageQuery;

/**
 * @description :
 * @author : zhaochao
 * @version : V1.0.0
 * @date : 2018年4月16日
 */
public interface ProductInfoDao {

    int deleteByPrimaryKey(String productId);

    int insertSelective(ProductInfoEntity record);

    ProductInfoEntity selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(ProductInfoEntity record);
    
    /**
     * 查询所有上架的商品
     * @return
     */
    List<ProductInfoEntity> findUpAll(Integer code);
    
    /**
     * 查询所有商品列表
     * @param pageable
     * @return
     */
    PageList<ProductInfoEntity> findAll(PageQuery pageQuery);
}
