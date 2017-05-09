package com.my.spring.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.RetailerDAO;
import com.my.spring.pojo.Person;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Rating;

public class MyXlsView extends AbstractExcelView
{

		
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Person p = (Person)session.getAttribute("user");
		HSSFSheet sheet = workbook.createSheet("Products for "+p.getFirstName()+" "+p.getLastName());
		sheet.setDefaultColumnWidth(12);
		HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, "Product Name");
        cell=getCell(sheet, 0, 1);
        setText(cell, "Description");
        cell=getCell(sheet, 0, 2);
        setText(cell, "Price");
        cell=getCell(sheet, 0, 3);
        setText(cell, "Quantity");
        cell=getCell(sheet, 0, 4);
        setText(cell, "Status");
        
		HSSFSheet sheet1 = workbook.createSheet("Ratings for Products ");
		sheet1.setDefaultColumnWidth(12);
		cell=getCell(sheet1, 0, 0);
        setText(cell, "Product Name");
        cell=getCell(sheet1, 0, 1);
        setText(cell, "Rating");
        cell=getCell(sheet1, 0, 2);
        setText(cell, "Title");
        cell=getCell(sheet1, 0, 3);
        setText(cell, "Description");
        cell=getCell(sheet1, 0, 4);
        setText(cell, "Posted by");
        
        
        System.out.println("Retailer Id"+p.getPersonID());
        ProductDAO productDao=new ProductDAO();
        List <Product> productList= productDao.list(p.getPersonID());
//        System.out.println("productList size>>"+productList.size());
        
        for(int i=0;i<productList.size();i++)
        {
        	Product prod=productList.get(i);
        	if(prod.getRatingList().size()>0){
        		ArrayList<Rating> ratingList= new ArrayList(prod.getRatingList());
        		for(int j=0;j<ratingList.size();j++)
        		{
        			Rating r=ratingList.get(j);
        			cell=getCell(sheet1,j+1,0);
                	setText(cell, prod.getTitle());
                	cell=getCell(sheet1,j+1,1);
                	setText(cell, String.valueOf(r.getScore()));
                	cell=getCell(sheet1,j+1,2);
                	setText(cell, r.getTitle());
                	cell=getCell(sheet1,j+1,3);
                	setText(cell, r.getDescription());
                	cell=getCell(sheet1,j+1,4);
                	setText(cell, r.getPostedBy());
        			
        		
        		}
        	}
        	cell=getCell(sheet,i+1,0);
        	setText(cell, prod.getTitle());
        	cell=getCell(sheet,i+1,1);
        	setText(cell, prod.getDescription());
        	cell=getCell(sheet,i+1,2);
        	setText(cell,String.valueOf(prod.getPrice()));
        	cell=getCell(sheet,i+1,3);
        	setText(cell, String.valueOf(prod.getQuantity()));
        	cell=getCell(sheet,i+1,4);
        	setText(cell, prod.getStatus());
        	
        	
        }
        


		
	}

}
