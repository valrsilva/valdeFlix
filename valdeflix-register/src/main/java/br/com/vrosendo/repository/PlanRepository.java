package br.com.vrosendo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vrosendo.domain.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
