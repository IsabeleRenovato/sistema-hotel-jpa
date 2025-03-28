package model.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import model.entities.utils.Status;

@Entity
public class Reserva {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate checkIn;
	
	@Column(nullable = false)
	private LocalDate checkOut;
	
	@Column(nullable = false)
	private double valorTotal;
	
	@Column(nullable = false)
	private Status statusReserva;
	
	@OneToMany
	@JoinColumn(nullable = false)
	private List<Hospede> hospede;
	
	@OneToMany
	@JoinColumn(nullable = false)
	private List<Quarto> quarto;

	public Reserva() {
		
	}

	public Reserva(LocalDate checkIn, LocalDate checkOut, double valorTotal, Status statusReserva, List<Hospede> hospede, List<Quarto> quarto) {
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.valorTotal = valorTotal;
		this.statusReserva = statusReserva;
		this.hospede = hospede;
	    this.quarto = quarto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Status getStatusReserva() {
		return statusReserva;
	}

	public void setStatusReserva(Status statusReserva) {
		this.statusReserva = statusReserva;
	}
	
	
}



