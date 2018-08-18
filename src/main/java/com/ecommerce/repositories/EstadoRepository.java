package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.domain.Estado;

@Repository	
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
