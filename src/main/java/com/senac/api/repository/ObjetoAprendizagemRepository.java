package com.senac.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senac.api.entity.ObjetoAprendizagem;

@Repository
public interface ObjetoAprendizagemRepository extends JpaRepository<ObjetoAprendizagem, Long>{

	//@Query(value = "SELECT * FROM objetoAprendizagem WHERE badge_nivel_id = :id", nativeQuery = true)
	//List<ObjetoAprendizagem> findBySituacaoAprendizagem(Long id); 
}
