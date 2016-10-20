package br.com.vrosendo;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vrosendo.domain.Plan;
import br.com.vrosendo.repository.PlanRepository;

@Component
public class ConfigureDb {

	@Autowired
	PlanRepository planRepository;
	
	public void configure(){
		planRepository.save(new Plan("Plan1","", new BigDecimal(9)));
		planRepository.save(new Plan("Plan2","", new BigDecimal(8)));
		planRepository.save(new Plan("Plan3","", new BigDecimal(7)));
	}
}
