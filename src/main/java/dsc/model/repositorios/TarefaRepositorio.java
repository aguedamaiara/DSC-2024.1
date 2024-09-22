package dsc.model.repositorios;

import java.util.List;
import dsc.model.entidades.Tarefa;
import dsc.model.entidades.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class TarefaRepositorio {

	@PersistenceContext(name = "corporativo")
	private EntityManager entityManager;

	public Tarefa adicionarTarefa(Tarefa tarefa) {
		entityManager.persist(tarefa); // Deixe o JPA persistir e gerar o ID automaticamente
		return tarefa;
	}

	public List<Tarefa> listarTarefasPorUsuario(Usuario usuario) {
		return entityManager.createQuery("SELECT t FROM Tarefa t WHERE t.responsavel = :responsavel", Tarefa.class)
				.setParameter("responsavel", usuario).getResultList();
	}

	// Buscar Tarefa por ID
	public Tarefa buscarTarefaPorId(String id) {
		return entityManager.find(Tarefa.class, id);
	}

	// Listar todas as Tarefas
	public List<Tarefa> listarTodasTarefas() {
		return entityManager.createQuery("SELECT t FROM Tarefa t", Tarefa.class).getResultList();
	}

	  public void removerTarefa(String id) {
	        Tarefa tarefa = buscarTarefaPorId(id);
	        if (tarefa != null) {
	            entityManager.remove(tarefa);
	        }
	    }

	// Atualizar uma tarefa existente
	public Tarefa atualizarTarefa(Tarefa tarefa) {
		return entityManager.merge(tarefa);
	}
}
