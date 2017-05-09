package com.my.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.RetailerDAO;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.TempObjects;

@Controller
@RequestMapping("/public/ajax/*")
public class AjaxController {
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@RequestMapping(value = "/public/ajax/products", method = RequestMethod.POST,headers="Accept=*/*", produces="application/json")
	public @ResponseBody List<TempObjects> getProducts(@RequestBody String val) throws Exception  {
		System.out.println("Value: " + val);
		String catid=val.substring(0,val.lastIndexOf(","));
		String search=val.substring(val.lastIndexOf(","));
		catid=extractData(catid);
		search=extractData(search);

		Long id=Long.parseLong(catid);
		
		if(id==0)
		{
			List<Product> productList=productDao.list(search);
			List<TempObjects> objectList=new ArrayList<TempObjects>();
			for(Product p:productList)
			{
				if(p.getStatus().equals("Active")){
				TempObjects to= new TempObjects();
				to.setTitle(p.getTitle());
				to.setDesc(p.getDescription());
				to.setPath(p.getFilename());
				to.setUserName(p.getRetailer().getUsername());
				to.setPrice(p.getPrice());
				to.setId(p.getId());
				objectList.add(to);
				}
			}	
			return objectList;
		}
		else if(id>0)
		{
			Category c= categoryDAO.get(id);
			List<Product> productList = new ArrayList(c.getProducts());
			List<TempObjects> objectList=new ArrayList<TempObjects>();
			for(Product p:productList)
				{
				if((org.apache.commons.lang3.StringUtils.containsIgnoreCase(p.getDescription(),search) || org.apache.commons.lang3.StringUtils.containsIgnoreCase(p.getTitle(),search)) && p.getStatus().equals("Active"))
				{
					TempObjects to= new TempObjects();
					to.setTitle(p.getTitle());
					to.setDesc(p.getDescription());
					to.setPath(p.getFilename());
					to.setUserName(p.getRetailer().getUsername());
					to.setPrice(p.getPrice());
					to.setId(p.getId());
					objectList.add(to);
				}	
				}
				return objectList;
			
		}
		return null;
		


	}

	public String extractData(String catid) {
		String t = catid.substring(0, catid.lastIndexOf("\""));
		String temp = t.substring(t.lastIndexOf("\"") + 1);
		return temp;
	}

}
