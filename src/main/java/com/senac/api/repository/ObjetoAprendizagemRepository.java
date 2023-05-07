package com.senac.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senac.api.entity.ObjetoAprendizagem;

@Repository
public interface ObjetoAprendizagemRepository extends JpaRepository<ObjetoAprendizagem, Long>{

	@Query(value = ""
			+ "SELECT objeto_aprendizagem.* FROM objeto_aprendizagem "
			+ "JOIN situacao_objeto_aprendizagem "
			+ "ON situacao_objeto_aprendizagem.objeto_aprendizagem_id  = objeto_aprendizagem.objeto_aprendizagem_id "
			+ "WHERE situacao_objeto_aprendizagem.situacao_aprendizagem_id = :id"
			+ "", nativeQuery =  true)
	List<ObjetoAprendizagem> findBySituacaoAprendizagem(Long id); 
	
	
	/*
	@Query(value = ""
			+ "SELECT * FROM objeto_aprendizagem "
			+ "JOIN situacao_objeto_aprendizagem "
			+ "ON situacao_objeto_aprendizagem.objeto_aprendizagem_id  = objeto_aprendizagem.id "
			+ "WHERE situacao_objeto_aprendizagem.situacao_aprendizagem_id = :id"
			+ "", nativeQuery =  true)
	List<ObjetoAprendizagem> findBySituacaoAprendizagem(Long id); 
	*/
}


