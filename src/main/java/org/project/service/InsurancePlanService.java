package org.project.service;

import java.util.List;

import org.project.binding.request.SearchRequest;
import org.project.binding.response.PlanResponse;
import org.springframework.stereotype.Service;

@Service
public interface InsurancePlanService {

	public List<PlanResponse>  SearchPlans(SearchRequest request);
	public List<String> getUniquePlanNames();

	public List<String> getUniquePlanStatus();

}
