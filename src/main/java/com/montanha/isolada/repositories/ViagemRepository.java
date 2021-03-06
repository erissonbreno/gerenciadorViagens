package com.montanha.isolada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.montanha.isolada.entities.Viagem;

import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

	Viagem findByLocalDeDestino(String localDeDestino);

	List<Viagem> findAllByRegiao(String regiao);
	

}
