package com.pacck.safety.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="endereco_tbl")
public class Endereco {

	@Id
	private int id_endereco;
	private String ilha;
	private String cidade;
	private String zona;
	private double latitude;
	private double longitude;
	@ManyToOne
	@JoinColumn(name="id_servico")
	@JsonIgnore
	private Servico servico;
	
	
	
	public int getId() {
		return id_endereco;
	}
	public void setId(int id) {
		this.id_endereco = id;
	}
	public String getIlha() {
		return ilha;
	}
	public void setIlha(String ilha) {
		this.ilha = ilha;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	
	
		
}
