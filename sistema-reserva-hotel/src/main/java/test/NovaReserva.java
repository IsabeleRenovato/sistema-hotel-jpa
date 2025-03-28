package test;

import infra.DAO;
import model.entities.Hospede;
import model.entities.Quarto;
import model.entities.utils.TipoQuarto;

public class NovaReserva {
	
	DAO<Object> dao = new DAO<>();
	
	Hospede hospede = new Hospede ("Isa", "123.456.789-00", "(19) 1234-5678");
	Quarto quarto = new Quarto (123, TipoQuarto.LUXO, 1200.00 );
	
	

}
