package com.pacck.safety.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="utilizador_tbl")
public class Utilizador {
	
	@Id
	private int idUtilizador;
	@Column(name="nomeutilizador")
	private String nome;
	private String senha;
	@OneToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	
	public int getIdUtilizador() {
		return idUtilizador;
	}
	public void setIdUtilizador(int idUtilizador) {
		this.idUtilizador = idUtilizador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	

}
