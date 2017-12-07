package com.my.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.RetailerDAO;

import com.my.spring.dao.CartDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.CustomerDAO;
import com.my.spring.dao.PersonDAO;
import com.my.spring.exception.ProductException;
import com.my.spring.exception.RetailerException;
import com.my.spring.exception.CustomerException;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Retailer;
import com.my.spring.pojo.Customer;
import com.my.spring.pojo.Person;
import com.my.spring.validator.CustomerValidator;
import com.my.spring.validator.RetailerValidator;
import com.my.spring.pojo.CartItems;

import com.my.spring.pojo.Category;

@Controller
@RequestMapping("/public/**")
public class PublicViewController {

	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;

	@Autowired
	@Qualifier("retailerDao")
	RetailerDAO retailerDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;

	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;


	@Autowired
	@Qualifier("personDao")
	PersonDAO personDao;
	
	@Autowired
	@Qualifier("customerValidator")
	CustomerValidator customerValidator;

	@InitBinder("Customer")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(customerValidator);
	}

	@Autowired
	@Qualifier("retailerValidator")
	RetailerValidator retailerValidator;

	@InitBinder("Retailer")
	private void initBinder2(WebDataBinder binder) {
		binder.setValidator(retailerValidator);
	}

	
	//-----------------403---------------
		@RequestMapping(value = "public/401.htm", method = RequestMethod.GET)
		protected ModelAndView unAuthorized(HttpServletRequest request) throws Exception {
			return new ModelAndView("401");
			}
		
	//---------------------------------Retailer---------------------------------
	@RequestMapping(value = "public/retailerlogin.htm", method = RequestMethod.GET)
	protected ModelAndView goToRetailerLogin(HttpServletRequest request) throws Exception {
		return new ModelAndView("retailer-login");
		}
	
	
	@RequestMapping(value = "public/registerretailer.htm", method = RequestMethod.GET)
	protected ModelAndView registerRetailer() throws Exception {
		// System.out.print("registerUser");

		return new ModelAndView("retailer-register", "retailer", new Retailer());

	}

	
	@RequestMapping(value = "public/registerretailer.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewRetailer(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("retailer") Retailer retailer, BindingResult result) throws Exception {
		
		retailerValidator.validate(retailer, result);

		if (result.hasErrors()) {
			return new ModelAndView("retailer-register", "retailer", retailer);
		}

		try {

			// System.out.print("registerNewUser");
			retailer.setStatus("registered");
			Retailer r = retailerDao.register(retailer);
			sendEmail(r.getEmail().getEmailAddress(),r.getFirstName());
			// request.getSession().setAttribute("user", u);
			// System.out.println(request.getRequestURI());
			// response.set
			return new ModelAndView("redirect:/");

		} catch (RetailerException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}


//---------------------------------End of Retailer------------------------------
	
	
//----------------------------------Customer------------------------------------	
	@RequestMapping(value = "public/registercustomer.htm", method = RequestMethod.GET)
	protected ModelAndView registerCustomer() throws Exception {
		// System.out.print("registerUser");

		return new ModelAndView("customer-register", "customer", new Customer());

	}

	@RequestMapping(value = "public/registercustomer.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewCustomer(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("customer") Customer customer, BindingResult result) throws Exception {

		customerValidator.validate(customer, result);

		if (result.hasErrors()) {
			return new ModelAndView("customer-register", "customer", customer);
		}

		try {

			// System.out.print("registerNewUser");
			customer.setStatus("registered");
			Customer c=customerDao.register(customer);
			sendEmail(c.getEmail().getEmailAddress(),c.getFirstName());
            
		} catch (CustomerException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
		
		
	    return new ModelAndView("redirect:/");
	}
	
//-----------------------------End of Customer----------------------------------	

	
//-------------------------------Product--------------------------------------	
	
	@RequestMapping(value = "/public/viewproduct.htm", method = RequestMethod.GET)
	public ModelAndView viewProduct(HttpServletRequest request) throws ProductException {
		long id = Long.parseLong(request.getParameter("pid"));
		Product a = productDao.get(id);
		request.setAttribute("p", a);
		return new ModelAndView("public-product-view");

	}

	
	
		
	@RequestMapping(value = "/public/viewproducts.htm", method = RequestMethod.GET)
	public ModelAndView viewProducts(HttpServletRequest request) throws Exception {
		
		String search_cat = request.getParameter("search_cat");
		String search_query = request.getParameter("search_query");
//		System.out.println("searchCat>>>>"+search_cat);
//		System.out.println("searchquery>>>>"+search_query);
		List<Product> products=null;
		List<Category> categories=null;
		if (search_cat==null && search_query == null) {
			
			try {
				products= productDao.list();
		
			} catch (ProductException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while displaying all products");
			}
		}
		else if(search_cat.length()>0 && search_query.length()==0) 
		{
		
			long catId=Long.parseLong(search_cat);
			Category cat=null;
			
			
			try {
				if(catId==0)
				{
					products= productDao.list();
					
				}
				else{
				cat= categoryDao.get(catId);
				ArrayList<Product>products1 = new ArrayList(cat.getProducts());
				for(Product p:products1)
				{
					if(p.getStatus().equals("Offline")){
						products1.remove(p);
					}
				}
				products=products1;
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while displaying all products");
			}
		}
		else if(search_cat.length()>0 && search_query.length()>0) {
			
			long catId=Long.parseLong(search_cat);
			Category cat=null;
			try {
				if(catId==0)
				{
					products= productDao.list(search_query);
					
				}
				else{
				cat= categoryDao.get(catId);
				List<Product> unfilteredProducts = new ArrayList(cat.getProducts());
				System.out.println(unfilteredProducts.size());
				List<Product> filteredProducts = new ArrayList();
				for(Product p:unfilteredProducts){

					if((org.apache.commons.lang3.StringUtils.containsIgnoreCase(p.getDescription(),search_query) || org.apache.commons.lang3.StringUtils.containsIgnoreCase(p.getTitle(),search_query))&& p.getStatus().equals("Active")){
					filteredProducts.add(p);
					System.out.println("Added one product ID:"+p.getId());}
				}
				products=filteredProducts;
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while displaying all products");
			}
			

		}
		try{
		categories= categoryDao.list();}
		catch(Exception e){
			System.out.println(e);
			return new ModelAndView("error", "errorMessage", "error while displaying all products");
		}
		request.setAttribute("products", products);
		request.setAttribute("categories", categories);
		return new ModelAndView("public-product-allitems");

	}
	
//--------------------------------End of product-----------------------------
	
	
//------------------------------Cart--------------------------------
	@RequestMapping(value = "/public/addtocart.htm", method = RequestMethod.GET)
	public ModelAndView addToCart(HttpServletRequest request) throws ProductException {
		long id = Long.parseLong(request.getParameter("pid"));
		Product a = productDao.get(id);
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		// System.out.println(quantity);
		CartItems ci = new CartItems(quantity, a);
		cartDao.addCartItems(request, ci);
		return new ModelAndView("public-view-cart");

	}

	@RequestMapping(value = "/public/viewcart.htm", method = RequestMethod.GET)
	public ModelAndView viewCart(HttpServletRequest request) throws ProductException {

		return new ModelAndView("public-view-cart");

	}

	@RequestMapping(value = "/public/removefromcart.htm", method = RequestMethod.GET)
	public ModelAndView removeCartItem(HttpServletRequest request) throws ProductException {
		long ciid = Long.parseLong(request.getParameter("ciid"));
		cartDao.deleteCartItems(request, ciid);
		return new ModelAndView("public-view-cart");

	}
//------------------------------------End of Cart-------------------------------
	
//----------------------------------Retailer Profile--------------------------	
	@RequestMapping(value = "/public/profile", method = RequestMethod.GET)
	protected String userProfile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String userName=request.getParameter("user");
		long id=retailerDao.getRetailerId(userName);
		List <Product> list=productDao.list(id);
		
//		System.out.println(list.size());
		request.setAttribute("products", list);
		Person user=(Person) personDao.getPerson(id);
//		System.out.println(user);
		request.setAttribute("user",user);
		
		return "public-retailer-profile";
	}
//------------------------------------------------End of retailer profile
	
	
//-------------------------------EMAIL------------------
	public void sendEmail(String email,String name)
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	    
	    Session session = Session.getInstance(props,
	  		  new javax.mail.Authenticator() {
	  			protected PasswordAuthentication getPasswordAuthentication() {
	  				return new PasswordAuthentication("app-email@gmail.com", "sushrut123456");
	  			}
	  		  });
	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress("app-email@gmail.com"));
	        msg.setRecipients(Message.RecipientType.TO,
                    email);
	        msg.setSubject("Registration");
	        msg.setText("Hello"+name+",<br/> Thanks for registering");
	        Transport.send(msg);
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
		
	}
	//------------------------------End of email-----------------------
	
}
