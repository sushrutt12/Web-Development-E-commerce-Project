package com.my.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.CustomerDAO;
import com.my.spring.dao.OrderDAO;
import com.my.spring.dao.OrderItemDAO;
import com.my.spring.dao.PersonDAO;
import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.RatingDAO;
import com.my.spring.dao.RetailerDAO;
import com.my.spring.exception.CustomerException;
import com.my.spring.exception.OrderItemException;
import com.my.spring.exception.PersonException;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Rating;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.CartItems;
import com.my.spring.pojo.Customer;
import com.my.spring.pojo.Order;
import com.my.spring.pojo.OrderItem;
import com.my.spring.pojo.Person;
import com.my.spring.validator.CustomerValidator;

@Controller
@RequestMapping("/user/**")
public class UserController {

	@Autowired
	@Qualifier("orderDao")
	OrderDAO orderDao;
	
	@Autowired
	@Qualifier("ratingDao")
	RatingDAO ratingDao;
	
	@Autowired
	@Qualifier("orderItemDao")
	OrderItemDAO orderItemDao;
	
	
	@Autowired
	@Qualifier("retailerDao")
	RetailerDAO retailerDao;
	
	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;
	
	@Autowired
	@Qualifier("personDao")
	PersonDAO personDao;

	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@Autowired
	@Qualifier("customerValidator")
	CustomerValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	
	
	
	@RequestMapping(value = "/user/retailer.htm", method = RequestMethod.GET)
	protected String goToRetailerHome(HttpServletRequest request) throws Exception {
		return "retailer-home";
	}
	
	@RequestMapping(value = "/user/admin.htm", method = RequestMethod.GET)
	protected String goToAdminHome(HttpServletRequest request) throws Exception {
		List<Person> personList = personDao.getPersonList();
		request.setAttribute("userList", personList);
		return "admin-home";
	}
	
	@RequestMapping(value = "/user/customer.htm", method = RequestMethod.GET)
	protected String goToCustomerHome(HttpServletRequest request) throws Exception {
		return "customer-home";
	}
	
	@RequestMapping(value = "/user/customer/addrating.htm", method = RequestMethod.GET)
	protected String addProductRating(HttpServletRequest request) throws Exception {
		Rating r = new Rating();
		long pid = Long.parseLong(request.getParameter("pid"));
		Product p=productDao.get(pid);
		r.setProduct(p);
		r.setScore(Integer.parseInt(request.getParameter("rating")));
		r.setDescription(request.getParameter("reviewDesc"));
		r.setTitle(request.getParameter("reviewTitle"));
		HttpSession session = request.getSession();
		Person person =(Person) session.getAttribute("user");
		r.setPostedBy(person.getUsername());
		p.getRatingList().add(r);
		ratingDao.createRating(r);
		
		return "customer-home";
	}
	
	@RequestMapping(value = "/user/customer/orders.htm", method = RequestMethod.GET)
	protected String goToCustomerOrders(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		Customer u=(Customer)session.getAttribute("user");
		List<Order> orderList = orderDao.getOrderList(u.getPersonID());
		request.setAttribute("orderList", orderList);
		return "customer-orders";
	}
	
	
	@RequestMapping(value = "/user/customer/checkout.htm", method = RequestMethod.GET)
	protected String userCheckout(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		Customer u=(Customer)session.getAttribute("user");
		Cart cart=(Cart)session.getAttribute("cart");
		// Need to substract the quantity of cart items from product.
//		System.out.println("Cart size"+cart.getCartItems().size());
//		System.out.println("Customer "+u.getFirstName());
		Order o = new Order();
		o.setCustomer(u);
		for(CartItems ci:cart.getCartItems())
		{
			OrderItem oi = new OrderItem();
			oi.setProduct(ci.getProduct());
			oi.setQuantity(ci.getQuantity());
			
			try {
				orderItemDao.createOrderItem(oi);
			} catch (OrderItemException e) {
			
				System.out.println("Order Item Exception"+e);
			}
			o.getOrderItemList().add(oi);
		}
		orderDao.createOrder(o);
		List<Order> orderList = orderDao.getOrderList(u.getPersonID());
		request.setAttribute("orderList", orderList);
		return "customer-orders";
//		return "customer-checkout";
	}
	

	@RequestMapping(value = "/user/admin/updateusers", method = RequestMethod.POST)
	protected ModelAndView saveUsers(HttpServletRequest request) throws Exception {
		
		HashMap<String, String[]> hm = (HashMap<String, String[]>) request.getParameterMap();
		Set<String> set = hm.keySet();
		String[] temp = hm.get("id");
		for (int i = 0; i < temp.length; i++) {
			int id=0;
			String status=null;
			System.out.println("Length" + temp.length);
			for (String s : set) {
				
				temp = hm.get(s);
//				System.out.println("Temp value>>" + Arrays.toString(temp));
				String t = temp[i];
//				System.out.println("T value>>" + t);
			if(s.equals("id"))
			{
				id=Integer.parseInt(t);
			}
			else if(s.equals("status"))
			{
				status=t;
			}
			System.out.println("" + s);
			System.out.println("ID>>"+id);
			System.out.println("Status>>"+status);
			}
		if(id!=0 && status!=null)
		personDao.updateStatus(id, status);
		}
		
		request.setAttribute("userlist", personDao.getPersonList());
		return new ModelAndView("admin-home");

	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request,HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		try {

			System.out.print("loginUser");

			Person u = personDao.getPerson(request.getParameter("username"), request.getParameter("password"));

			if (u == null) {
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}

			session.setAttribute("user", u);

			// if (u.getRole().equals("admin")){
			if (u.getRole() != null && u.getRole().equals("Admin")) {
//				request.setAttribute("userlist", userDao.getUserList());
				return "redirect:/user/admin.htm";
			} else if(u.getRole() != null && u.getRole().equals("Retailer")){
////				request.set
				return "redirect:/user/retailer.htm";
			}
			else if(u.getRole() != null && u.getRole().equals("Customer")){
////			request.set
			return "redirect:/user/customer.htm";
		}
			else
				return "403";
		} catch (PersonException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	protected ModelAndView logoutUser(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
//----------------------------- Start of Card---------------------------------

	
	@RequestMapping(value = "/user/customer/addcard.htm", method = RequestMethod.GET)
	protected ModelAndView addCard(HttpServletRequest request) throws Exception {
		
		return new ModelAndView("card-add-form");

	}

	@RequestMapping(value = "/user/customer/addcard.htm", method = RequestMethod.POST)
	protected ModelAndView addCardSuccess(HttpServletRequest request) throws Exception {
		
		return new ModelAndView("card-add-success");

	}

	
	@RequestMapping(value = "/user/customer/removecard.htm", method = RequestMethod.GET)
	protected ModelAndView removeCard(HttpServletRequest request) throws Exception {
//		long ciid = Long.parseLong(request.getParameter("ciid"));
//		cartDao.deleteCartItems(request, ciid);
		return new ModelAndView("customer-checkout");

	}
	
	@RequestMapping(value = "/user/customer/viewcard.htm", method = RequestMethod.GET)
	protected ModelAndView viewCard(HttpServletRequest request) throws Exception {
		return new ModelAndView("card-view");
	}
	
//------------------------------End of Card ----------------------------------
	
}
