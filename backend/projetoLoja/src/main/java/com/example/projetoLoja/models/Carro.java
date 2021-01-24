package com.example.projetoLoja.models;

import javax.persistence.*;

@Entity
@Table(name = "carros")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "foto", length = 1000)
	private byte[] foto;
	
	@Column(name = "tipoVeiculo")
	private String tipoVeiculo;

	@Column(name = "marca")
	private String marca;

	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "ano")
	private int ano;

	@Column(name = "preco")
	private String preco;

	@Column(name = "descricao")
	private String descricao;

	public Carro() {

	}

	public Carro(byte[] foto , String tipoVeiculo, String marca, String modelo, int ano, String preco, String descricao) {
		this.foto = foto;
		this.tipoVeiculo = tipoVeiculo;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.preco = preco;
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", tipoVeiculo=" + tipoVeiculo + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + ano
				+ ", preco=" + preco + ", descricao=" + descricao + "]";
	}
	
}