package com.my.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.my.spring.file.MyXlsView;

@Controller
@RequestMapping("/user/retailer/file/*")
public class FileController {
	
	@RequestMapping(value = "/user/retailer/file/productsreport.xls", method = RequestMethod.GET)
	public ModelAndView showExcelReport()
	{
		View view = new MyXlsView( );
		return new ModelAndView(view);
	}
	

}
