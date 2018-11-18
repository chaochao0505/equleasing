/**
 * @company 西安一体物联网科技有限公司
 * @file ProductService.java
 * @author zhaochao
 * @date 2018年4月16日 
 */
package com.shx.web.service;

import com.shx.web.entity.ProductInfoEntity;
import com.shx.web.utils.page.PageList;
import com.shx.web.utils.page.PageQuery;

/**
 * @description :
 * @author : zhaochao
 * @version : V1.0.0
 * @date : 2018年4月16日
 */
public interface ProductService {

    
    /**
     * 查询所有商品列表
     * @param pageable
     * @return
     */
    PageList<ProductInfoEntity> findAll(PageQuery pageQuery);
    
   
}
