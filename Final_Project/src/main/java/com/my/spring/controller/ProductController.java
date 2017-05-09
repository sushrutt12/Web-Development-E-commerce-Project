package com.my.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.RetailerDAO;
import com.my.spring.exception.ProductException;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Retailer;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.Customer;

@Controller
@RequestMapping("/user/retailer/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;

	@Autowired
	@Qualifier("retailerDao")
	RetailerDAO retailerDao;

	@RequestMapping(value = "/user/retailer/product/add.htm", method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("product") Product product, BindingResult result) throws Exception {
		boolean flag=true;
		try {

			File directory;

			String path = "C:\\Users\\sushr\\OneDrive\\Documents\\Spring Projects\\Final_Project\\media";
			directory = new File(path + "\\" + product.getTitle());
			
			boolean temp = directory.exists();
			System.out.println("Temp>>"+temp);
			
			if (!temp) {
				temp = directory.mkdir();
			}
			if (temp) {
				
				CommonsMultipartFile photoInMemory = product.getPhoto();
				String fileName = photoInMemory.getOriginalFilename().substring(0,
						photoInMemory.getOriginalFilename().lastIndexOf(".")) + new Date().getTime()
						+ photoInMemory.getOriginalFilename()
								.substring(photoInMemory.getOriginalFilename().lastIndexOf("."));
				String ext=photoInMemory.getOriginalFilename()
						.substring(photoInMemory.getOriginalFilename().lastIndexOf(".")+1);
//				if(ext.equals("mp4"))
//					advert.setType("video");
//				else if	(ext.equals("mp3"))
//					advert.setType("audio");
//				else if	(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("bmp") || ext.equals("png") )
//					advert.setType("image");
//				
				System.out.println(fileName);
				
				System.out.println(directory.getPath());
				String pathfinal="/media/";
				pathfinal+=directory.getPath().substring(directory.getPath().lastIndexOf("\\")+1);
				pathfinal+="/"+fileName;
				System.out.println("FINAL PATH IS>>>>>"+pathfinal);
				File localFile = new File(directory.getPath(), fileName);
				photoInMemory.transferTo(localFile);
				product.setFilename(pathfinal);
				System.out.println("File is stored at" + localFile.getPath());
//				System.out.print("registerNewUser");
				Retailer retailer = retailerDao.getRetailer(product.getPostedBy());
				product.setRetailer(retailer);
				product.setStatus("Active");
				product = productDao.create(product);

				for (Category c : product.getCategories()) {
					c = categoryDAO.get(c.getTitle());
					c.getProducts().add(product);
					categoryDAO.update(c);
				}

			} else {
				System.out.println("Failed to create directory!");
				flag=false;
			}

		} catch (

		IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
			flag=false;
		} catch (IOException e) {
			flag=false;
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} catch (ProductException e) {
			flag=false;
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		if(flag)
		return new ModelAndView("retailer-product-success", "product", product);
		else
			return new ModelAndView("error");

	}

	@RequestMapping(value = "/user/retailer/product/list.htm", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpServletRequest request) throws Exception {

		try {
			HttpSession session = request.getSession();
			Retailer r= (Retailer) session.getAttribute("user");
			
			List<Product> products = productDao.list(r.getPersonID());
			List<Category> categories;
			// How to get categories per product
			
//			request.setAttribute("categories", categories);
			return new ModelAndView("retailer-product-allitems", "products", products);

		} catch (ProductException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	@RequestMapping(value = "/user/retailer/product/add.htm", method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("product", new Product());
		mv.setViewName("retailer-product-form");
		return mv;
	}
	
	@RequestMapping(value = "/user/retailer/product/remove.htm", method = RequestMethod.GET)
	public ModelAndView deleteProduct(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("pid"));
		productDao.setOffline(id);
		
		return new ModelAndView("redirect:/user/retailer/product/list.htm");
	}
	
	@RequestMapping(value = "/user/retailer/product/activate.htm", method = RequestMethod.GET)
	public ModelAndView activateProduct(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("pid"));
		productDao.setOnline(id);
		
		return new ModelAndView("redirect:/user/retailer/product/list.htm");
//		return "retailer-product-allitems";
		
	}

}