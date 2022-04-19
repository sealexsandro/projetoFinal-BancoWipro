package com.wipro.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.projetofinal.entities.Moviment;

@Repository
public interface MovimentRepository extends JpaRepository<Moviment, Long>{

}
