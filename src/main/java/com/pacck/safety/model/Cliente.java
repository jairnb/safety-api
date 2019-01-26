package com.pacck.safety.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cliente_tbl")
public class Cliente {

	@Id
	private int idCliente;
	private String nome;
	private String sobrenome;
	private String email;
	private int telefone;
	private int nif;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="id_funcionario")
//	@JsonIgnore
//	private Funcionario funcionario;
	
	@OneToMany(mappedBy="cliente")
	@JsonIgnore
	private List<Servico> servicos;
	
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public int getNif() {
		return nif;
	}
	public void setNif(int nif) {
		this.nif = nif;
	}
//	public Funcionario getFuncionario() {
//		return funcionario;
//	}
//	public void setFuncionario(Funcionario funcionario) {
//		this.funcionario = funcionario;
//	}
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	
	
	
}
