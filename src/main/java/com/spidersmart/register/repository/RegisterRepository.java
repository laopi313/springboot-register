package com.spidersmart.register.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spidersmart.register.model.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long>{

	@Query("SELECT r FROM Register r WHERE (r.date) = (:date)")
	List<Register> findByDate(@Param("date") Date date);
	
	@Query("SELECT r FROM Register r WHERE (r.firstName) = (:firstName)")
	List<Register> findByFirstName(@Param("firstName") String firstName);	
	
	@Query("SELECT r FROM Register r WHERE (r.lastName) = (:lastName)")
	List<Register> findByLastName(@Param("lastName") String lastName);	
	
	@Query("SELECT r FROM Register r WHERE (r.paymentType) = (:paymentType)")
	List<Register> findByPaymentType(@Param("paymentType") String paymentType);	
	
	@Query("SELECT r FROM Register r WHERE (r.course) = (:course)")
	List<Register> findByCourse(@Param("course") String course);	
}
