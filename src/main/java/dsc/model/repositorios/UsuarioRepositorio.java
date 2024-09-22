package dsc.model.repositorios;


import java.util.List;

import dsc.model.entidades.Usuario;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Singleton
public class UsuarioRepositorio {
	
	@PersistenceContext(name = "corporativo")
    private EntityManager entityManager;
	
	public Usuario adicionarUsuario(Usuario usuario) {
	    this.entityManager.persist(usuario);
	    this.entityManager.flush();
	    return usuario;
	}

    public Usuario buscarUsuarioPorId(String id) {
        return entityManager.find(Usuario.class, id);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<Usuario> listarUsuarios() {
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
    
    public List<Usuario> findAll(){
    	Query q = entityManager.createQuery("from Usuario U", Usuario.class);
    	return q.getResultList();
    }
    
//   public Usuario atualizarUsuario(Usuario usuario) {
//      return this.entityManager.merge(usuario);
//  }
//
//  public void removerUsuario(String id) {
//      Usuario usuario = entityManager.find(Usuario.class, id);
//      if (usuario != null) {
//          entityManager.remove(usuario);
//      }
//  }

}
