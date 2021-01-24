package com.example.projetoLoja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetoLoja.models.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
	
}