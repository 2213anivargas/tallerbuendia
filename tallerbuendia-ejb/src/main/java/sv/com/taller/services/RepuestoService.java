package sv.com.taller.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import sv.com.taller.JPAUtils.JPAUtil;
import sv.com.taller.entities.Repuesto;
import sv.com.taller.repositories.RepuestoRepository;

@Stateless
public class RepuestoService implements RepuestoRepository{

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	@Override
	public void agregar(Repuesto repuesto) {
		try {
			entity.getTransaction().begin();
			Repuesto repuestos = new Repuesto(repuesto.getIdRepuesto(),repuesto.getCantidad(),repuesto.getCosto(),repuesto.getFecha_adquisicion(),
					repuesto.getMarca(),repuesto.getNombre(),repuesto.getPrecio_venta());
			entity.persist(repuestos);
			entity.getTransaction().commit();
			System.out.println("Repuesto Agregado");
		}catch(Exception e) {
			entity.close();
			e.printStackTrace();
		}
		
	}

	@Override
	public void modificar(Repuesto repuesto) {
		try {
			entity.getTransaction().begin();
			entity.merge(repuesto);
			entity.getTransaction().commit();
			System.out.println("Repuesto Modificado");
		}catch(Exception e) {
			entity.close();
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminar(Repuesto repuesto) {
		try {
			entity.getTransaction().begin();
			entity.remove(repuesto);
			entity.getTransaction().commit();
			System.out.println("Repuesto Elimado");
		}catch(Exception e) {
			entity.close();
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void restarCantidad(Repuesto repuesto, int cantidad) {
		try {
			Repuesto cantidad1 = new Repuesto(repuesto.getCantidad()-cantidad);
			entity.getTransaction().begin();
			entity.merge(cantidad1.getCantidad());
			entity.getTransaction().commit();
			System.out.println("Repuesto Elimado");
		}catch(Exception e) {
			entity.close();
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Repuesto> mostrar() {
		List<Repuesto> repuestos = null;
		Query query = entity.createQuery("FROM Repuesto r");
		repuestos = query.getResultList();
		return repuestos;
	}

	@Override
	public List<Repuesto> buscar(Repuesto repuesto) {
		// TODO Auto-generated method stub
		return null;
	}

}
