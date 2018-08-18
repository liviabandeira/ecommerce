package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.domain.Cliente;

@Repository	
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
