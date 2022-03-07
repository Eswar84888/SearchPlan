package org.project.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.project.binding.request.SearchRequest;
import org.project.binding.response.PlanResponse;
import org.project.entities.InsurancePlanEntity;
import org.project.reports.ExcelReportGenerator;
import org.project.service.InsurancePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class InsuranceRestController {
	
	@Autowired
	private InsurancePlanService service;
	
	@GetMapping("/ex")
	public void generateExcel(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disspostion";
		String headerValue = "attachement; filename=plans.xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<PlanResponse> plans = service.SearchPlans(null);
		ExcelReportGenerator excelReport = new ExcelReportGenerator();
		excelReport.export(plans, response);
	
	}

@PostMapping("/plans")
	public ResponseEntity<List<String>> serachPlan(@RequestBody SearchRequest request){
		List<PlanResponse> searchPlans= service.SearchPlans(request);
		return new ResponseEntity(searchPlans,HttpStatus.OK);
		
	}

	@GetMapping("/plansNames")
	public ResponseEntity<List<String>>  getPlanNames(){
		
	List<String> planNames=service.getUniquePlanNames();
		
		return new ResponseEntity(planNames,HttpStatus.OK);
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanStatus() {
		List<String> planStatus = service.getUniquePlanStatus();
		return new ResponseEntity(planStatus, HttpStatus.OK);
	}
	
	
}
