package com.senac.api.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "situacao_aprendizagem")
public class SituacaoAprendizagem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "situacao_aprendizagem_id")
	private Long id;
	@Column(name = "situacao_aprendizagem_titulo")
	private String titulo;
	@Column(name = "situacao_aprendizagem_descricao")
	private String descricao;
	@Column(name = "situacao_aprendizagem_ordem")
	private Integer ordem;
	@Column(name = "situacao_aprendizagem_status")
	private Boolean status;
	@ManyToOne
	@JoinColumn(name = "planejamento_uc_id", referencedColumnName = "planejamento_uc_id")
	private PlanejamentoUc planejamentoUcId;
	@OneToOne
	@JoinColumn(name = "grau_dificuldade_id", referencedColumnName = "grau_dificuldade_id")
	private GrauDificuldade grauDificuldadeId;
	@OneToOne
	@JoinColumn(name = "badge_id", referencedColumnName = "badge_id")
	private Badge badgeId;
	@JsonIgnore
	@OneToMany(mappedBy = "situacaoAprendizagemId")
	private List<Atividade> atividades;
	@ManyToMany
	@JoinTable(
		name = "indicador_situacao_aprendizagens",
		joinColumns = { @JoinColumn(name = "situacao_aprendizagem_id", referencedColumnName = "situacao_aprendizagem_id")},
		inverseJoinColumns = { @JoinColumn(name = "copetencia_indicador_id", referencedColumnName = "copetencia_indicador_id")} 
	)
	@Column(name = "indicadores")
	private List<CopetenciaIndicador> indicadores;
	@JsonIgnore
	@ManyToMany(mappedBy = "aprendizagens")
	private List<ObjetoAprendizagem> objetos;
	
	public SituacaoAprendizagem() {
		super();
	}

	public SituacaoAprendizagem(Long id, String titulo, String descricao, Integer ordem, Boolean status,
			PlanejamentoUc planejamentoUcId, GrauDificuldade grauDificuldadeId, Badge badgeId, List<CopetenciaIndicador> indicadores) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.ordem = ordem;
		this.status = status;
		this.planejamentoUcId = planejamentoUcId;
		this.grauDificuldadeId = grauDificuldadeId;
		this.badgeId = badgeId;
		this.indicadores = indicadores;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public PlanejamentoUc getPlanejamentoUcId() {
		return planejamentoUcId;
	}
	public void setPlanejamentoUcId(PlanejamentoUc planejamentoUcId) {
		this.planejamentoUcId = planejamentoUcId;
	}
	public GrauDificuldade getGrauDificuldadeId() {
		return grauDificuldadeId;
	}
	public void setGrauDificuldadeId(GrauDificuldade grauDificuldadeId) {
		this.grauDificuldadeId = grauDificuldadeId;
	}
	public Badge getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(Badge badgeId) {
		this.badgeId = badgeId;
	}
	public List<CopetenciaIndicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<CopetenciaIndicador> indicadores) {
		this.indicadores = indicadores;
	}
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	public List<ObjetoAprendizagem> getObjetos() {
		return objetos;
	}
	public void setObjetos(List<ObjetoAprendizagem> objetos) {
		this.objetos = objetos;
	}
	

}
