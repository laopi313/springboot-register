package com.spidersmart.register.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spidersmart.register.model.RegisterReturn;
import com.spidersmart.register.model.Register;
import com.spidersmart.register.repository.RegisterRepository;
import com.spidersmart.register.utils.RegisterUtilities;


@Controller
public class RegisterController {

	@Autowired 
	RegisterRepository repository;
	//
	
    @GetMapping("/register")
    public String start() {
    	return "register";
    }

    @GetMapping("/registers")
    @ResponseBody
    public String goAjaxReturn(HttpServletRequest request) throws JsonProcessingException {
    	
		final DataTableRequestParam param = DataTablesParamUtility.getParam(request);

        ObjectMapper mapper = new ObjectMapper();
        List<Register> registerList = repository.findAll();
		int iTotalRecords = registerList.size();

		List<Register> registers = new LinkedList<Register>();
		for(Register r : registerList){
			if(	
				param.bSearchable[1] &&
				RegisterUtilities.convertDateToString(r.getDate()).contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[2] &&
				r.getFirstName().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[3] &&
				r.getLastName().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[4] &&
				Integer.toString(r.getHours()).contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[5] &&
				Integer.toString(r.getPrice()).contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[6] &&
				Integer.toString(r.getCredit()).contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[8] &&
				r.getCourse().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[9] &&
				r.getPaymentType().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[10] &&
				r.getMemo().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
			)
				{
					registers.add(r); // Add a company that matches search criterion
				}
		}
		int iTotalDisplayRecords = registers.size();//Number of companies that matches search criterion should be returned

		Collections.sort(registers, new Comparator<Register>(){
			@Override
			public int compare(Register c1, Register c2) {
				int result = 0;
				for(int i=0; i<param.iSortingCols; i++){
					int sortBy = param.iSortCol[i];
					if(param.bSortable[sortBy]){
						switch(sortBy){
							case 0:
								result = c1.getId().compareTo(c2.getId()) * 
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 1:
								result = c1.getDate().compareTo(c2.getDate()) * 
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 2:
								result = c1.getFirstName().compareToIgnoreCase(c2.getFirstName()) * 
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 3:
								result = c1.getLastName().compareToIgnoreCase(c2.getLastName()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 4:
								result = c1.getHours().compareTo(c2.getHours()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 5:
								result = c1.getPrice().compareTo(c2.getPrice()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 6:
								result = c1.getCredit().compareTo(c2.getCredit()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 7:
								result = c1.getCourse().compareToIgnoreCase(c2.getCourse()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 8:
								result = c1.getPaymentType().compareToIgnoreCase(c2.getPaymentType()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 9:
								result = c1.getMemo().compareToIgnoreCase(c2.getMemo()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
						}
					}
					if(result!=0)
						return result;
					else
						continue;
				}
				return result;
			}
		});
		
		if(registers.size()< param.iDisplayStart + param.iDisplayLength)
			registers = registers.subList(param.iDisplayStart, registers.size());
		else
			registers = registers.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
	
        RegisterReturn ret = new RegisterReturn();
 
        String[][] arr = new String[registers.size()][param.iColumns];
        int firstIndex = 0;
        for(Register c: registers) {
        	int index = 0;
			arr[firstIndex][index++] = c.getId().toString();
			arr[firstIndex][index++] = RegisterUtilities.convertDateToString(c.getDate());
			arr[firstIndex][index++] = c.getFirstName();
			arr[firstIndex][index++] = c.getLastName();
			arr[firstIndex][index++] = Integer.toString(c.getHours());
			arr[firstIndex][index++] = Integer.toString(c.getPrice());
			arr[firstIndex][index++] = Integer.toString(c.getCredit());
			arr[firstIndex][index++] = c.getCourse();
			arr[firstIndex][index++] = c.getPaymentType();
			arr[firstIndex][index++] = c.getMemo();
			firstIndex++;
        }
        ret.setAaData(arr);
        ret.setiTotalDisplayRecords(iTotalDisplayRecords);
        ret.setiTotalRecords(iTotalRecords);
        ret.setsEcho(param.sEcho);
        String str = mapper.writeValueAsString(ret);
        return str;
    }

	@PostMapping("/AddData")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addCompany(HttpServletRequest request){
		Date date = null;
		try {
			date = RegisterUtilities.convertStringToDate(request.getParameter("date"));
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date();
		}
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Integer hours = Integer.valueOf(request.getParameter("hours"));
		Integer price = Integer.valueOf(request.getParameter("price"));
		Integer credit = Integer.valueOf(request.getParameter("credit"));
		//Integer amount = Integer.valueOf(request.getParameter("amount"));
		String course = request.getParameter("course");
		String memo = request.getParameter("memo");
		String paymentType = request.getParameter("paymentType");
		switch (paymentType) {
			case "1":
				paymentType = "email";
				break;
			case "2":
				paymentType = "cash";
				break;
			case "3":
				paymentType = "cheque";
				break;
			case "4":
				paymentType = "card";
				break;
			case "5":
				paymentType = "other";
				break;
		}
		Register c = new Register(date, firstName ,lastName, hours, price, credit, course, paymentType, memo);
		repository.save(c);
		return;
	}
	
	@PostMapping("/register")
	public Register newRegister(@RequestBody Register register) {
		return repository.save(register);
	}
	
	@GetMapping("/PrintData/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void printReceipt(@PathVariable Long id) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		Optional<Register> register = repository.findById(id);
	    Workbook workbook = WorkbookFactory.create(new File("c://tmp//spider.xls"));

	    Sheet sheet = workbook.getSheetAt(0);
	    for(int i = 0; i < 14; i+=13) {
		    sheet.getRow(2+i).getCell(5).setCellValue(RegisterUtilities.convertDateToString(register.get().getDate()));
		    sheet.getRow(3+i).getCell(5).setCellValue(register.get().getId());
		    sheet.getRow(4+i).getCell(3).setCellValue(register.get().getFirstName()+" "+register.get().getLastName());
		    sheet.getRow(6+i).getCell(0).setCellValue(register.get().getCourse());
		    sheet.getRow(6+i).getCell(1).setCellValue(register.get().getHours());
		    sheet.getRow(6+i).getCell(2).setCellValue(register.get().getPrice());
		    sheet.getRow(6+i).getCell(3).setCellValue(register.get().getCredit());
		    sheet.getRow(6+i).getCell(4).setCellValue(register.get().getHours() * register.get().getPrice() - register.get().getCredit());
		    sheet.getRow(10+i).getCell(4).setCellValue(register.get().getHours() * register.get().getPrice() - register.get().getCredit());
		    sheet.getRow(6+i).getCell(5).setCellValue(register.get().getMemo());		    
		    sheet.getRow(10+i).getCell(1).setCellValue(register.get().getPaymentType());
	    }
	    
	    FileOutputStream fileOut = new FileOutputStream("c://tmp//"+register.get().getFirstName()+"_"+register.get().getLastName()+".xls");
	    workbook.write(fileOut);
	    fileOut.close();

	    // Closing the workbook
	    workbook.close();	    
		return;
	}
	
	@PostMapping("/UpdateData")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	protected void doPost(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		//int columnId = Integer.parseInt(request.getParameter("columnId"));
		int columnPosition = Integer.parseInt(request.getParameter("columnPosition"));
		//int rowId = Integer.parseInt(request.getParameter("rowId"));
		String value = request.getParameter("value");
		//String columnName = request.getParameter("columnName");
		
		for(Register register: repository.findAll())
		{
			if(register.getId()==id)
			{
				switch (columnPosition)
	            {
	                case 1:
					try {
						register.setDate(RegisterUtilities.convertStringToDate(value));
						} catch (ParseException e) {
							e.printStackTrace();
						}
	                    break;
	                case 2:
	                    register.setFirstName(value);
	                    break;
	                case 3:
	                    register.setLastName(value);
	                    break;
	                case 4:
	                	register.setHours(Integer.valueOf(value));
	                	break;
	                case 5:
	                	register.setPrice(Integer.valueOf(value));
	                	break;
	                case 6:
	                	register.setCredit(Integer.valueOf(value));
	                	break;
	                case 7:
	                    register.setCourse(value);
	                    break;
	                case 8:
	                    register.setPaymentType(value);
	                    break;
	                case 0:
	                	register.setMemo(value);
	                	break;	                	
	                default:
	                    break;
	            }
				repository.save(register);
			}
		}
		return;
	}
	
	@PostMapping("/DeleteData")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		for(Register c: repository.findAll())
		{
			if(c.getId()==id)
			{
				repository.deleteById((long) id);
				break;
			}
		}
		return;
	}	
}
