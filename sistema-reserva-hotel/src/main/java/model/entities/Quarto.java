package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quarto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int numero;
	
	@Column(nullable = false)
	private TipoQuarto tipo;
	
	@Column(nullable = false)
	private double valor;

	public Quarto() {

	}

	public Quarto(int numero, TipoQuarto tipo, double valor) {
		this.numero = numero;
		this.tipo = tipo;
		this.valor = valor;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoQuarto getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuarto tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
}

enum TipoQuarto {
    SIMPLES, DUPLO, LUXO
}