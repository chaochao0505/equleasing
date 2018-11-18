/**
 * @company 西安一体物联网科技有限公司
 * @file BuyerProductContoller.java
 * @author zhaochao
 * @date 2018年4月16日 
 */
package com.shx.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductContoller {
	@Autowired
	private ProductService productService;
	@GetMapping("/list")
	public Map<String,Object> list(@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="size",defaultValue="10")Integer size){
		Map<String,Object> map=new HashMap<>();
		PageQuery pageQuery=new PageQuery();
		pageQuery.setiDisplayStart(page==1?0:(page-1)*size);
		pageQuery.setiDisplayLength(size);
		PageList<ProductInfoEntity> list=productService.findAll(pageQuery);
		map.put("list", list);
		map.put("totalCount",list.getPaginator().getTotalCount());
		map.put("currentPage",list.getPaginator().getCurPage());
		map.put("TotalPages",list.getPaginator().getTotalPages());
		return map;
	}
}
