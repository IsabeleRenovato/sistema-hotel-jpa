package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import model.entities.utils.TipoPagamento;

@Entity
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	private TipoPagamento tipoPagamento;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Reserva reserva;

	public Pagamento() {
		
	}

	public Pagamento(double valor, TipoPagamento tipoPagamento) {
	
		this.valor = valor;
		this.tipoPagamento = tipoPagamento;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	
	
	
}



