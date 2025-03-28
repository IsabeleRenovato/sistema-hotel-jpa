package infra;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.Pagamento;
import model.entities.Reserva;
import model.entities.utils.Status;

public class DAO<E> {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class <E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("sistema-reserva-hotel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DAO() {
		this(null);
	}

	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO<E> abrirT(){
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecharT(){
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> incluir (E entidade){
		em.persist(entidade);
		return this;
	}
	
	public DAO<E> incluirAtomico(E entidade){
		return this.abrirT().incluir(entidade).fecharT();
	}
	
	public List<E> obterTodos(int qtde, int deslocamento){
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula");
		}
		
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query= em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		return query.getResultList();
	}
	
	public List<E> consultar(String nomeConsulta, Object... params){
		TypedQuery<E> query = em.createNamedQuery(nomeConsulta, classe);
		
		for (int i = 0; i < params.length; i += 2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		}
		
		return query.getResultList();
	}
	
	public E consultarUm(String nomeConsulta, Object... params){
		List<E> lista = consultar(nomeConsulta, params);
		return lista.isEmpty() ? null : lista.get(0);
		
	}
	
	public List<E> verificarDisponibilidade(LocalDate inicio, LocalDate fim) {
        String jpql = """
            SELECT q FROM Quarto q 
            WHERE q.id NOT IN (
                SELECT r.quarto.id 
                FROM Reserva r
                WHERE (r.checkIn <= :fim AND r.checkOut >= :inicio)
            )
            """;
        return em.createQuery(jpql, classe)
                 .setParameter("inicio", inicio)
                 .setParameter("fim", fim)
                 .getResultList();
    }
	
	public boolean verificarPagamento(Long idReserva) {
	    String jpql = """
	            SELECT p FROM Pagamento p
	            WHERE p.reserva.id = :idReserva 
	            AND p.valor = p.reserva.valorTotal
	            AND p.reserva.statusReserva = :status
	            """;

	    try {
	        Pagamento pagamento = em.createQuery(jpql, Pagamento.class)
	                .setParameter("idReserva", idReserva)
	                .setParameter("status", Status.ATIVA) 
	                .getSingleResult();

	        return pagamento != null;
	    } catch (NoResultException e) {
	        return false; 
	    }
	}
	
	 public List<Object[]> gerarRelatorioOcupacao(LocalDate inicio, LocalDate fim) {
	        String jpql = """
	            SELECT q.numero, COUNT(r.id) AS totalReservas
	            FROM Quarto q
	            LEFT JOIN Reserva r ON q.id = r.quarto.id 
	                AND (r.checkIn <= :fim AND r.checkOut >= :inicio)
	            GROUP BY q.numero
	            ORDER BY totalReservas DESC
	            """;
	        return em.createQuery(jpql, Object[].class)
	                 .setParameter("inicio", inicio)
	                 .setParameter("fim", fim)
	                 .getResultList();
	    }
	
	

	public void fechar() {
		em.close();
	}
	
}
