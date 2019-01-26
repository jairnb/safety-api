package com.pacck.safety.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="servico_tbl")
public class Servico {

	@Id
	private int id_servico;
	private LocalDate data_inicio;
	private LocalDate data_termino;
	private double custo;
	private String descricao;
	private String periodo;
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	//@JsonIgnore
	@ManyToMany//(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="prestar_tbl", joinColumns=@JoinColumn(name="id_servico"), inverseJoinColumns=@JoinColumn(name="id_funcionario"))
	private List<Funcionario> funcionario;
	@OneToMany(mappedBy="servico")
	//@JsonIgnore
	private List<Endereco> endereco;
	
	
	public int getId_servico() {
		return id_servico;
	}
	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}
	public LocalDate getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(LocalDate data_inicio) {
		this.data_inicio = data_inicio;
	}
	public LocalDate getData_termino() {
		return data_termino;
	}
	public void setData_termino(LocalDate data_termino) {
		this.data_termino = data_termino;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Funcionario> getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}
	public List<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	
	
	
	
}
