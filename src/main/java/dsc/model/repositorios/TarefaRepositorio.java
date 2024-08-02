package dsc.model.repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import dsc.model.entidades.Tarefa;
import dsc.model.entidades.Usuario;
import jakarta.ejb.Stateless;

@Stateless
public class TarefaRepositorio {
	private Map<Usuario, List<Tarefa>> tarefasPorUsuario = new HashMap<>();
    private AtomicInteger idGenerator = new AtomicInteger(1);

	
	public void adicionarTarefa(Tarefa tarefa) {
		
		 if (tarefa.getId() == null) {
	            tarefa.setId(idGenerator.getAndIncrement());
	        }
		 
		Usuario responsavel = tarefa.getResponsavel();
		if (!tarefasPorUsuario.containsKey(responsavel)) {
			tarefasPorUsuario.put(responsavel, new ArrayList<>());
		}
		tarefasPorUsuario.get(responsavel).add(tarefa);
	}

	public List<Tarefa> listarTarefas(Usuario usuario) {
		return tarefasPorUsuario.getOrDefault(usuario, new ArrayList<>());
	}

	public void removerTarefa(Tarefa tarefa) {
		Usuario responsavel = tarefa.getResponsavel();
		if (tarefasPorUsuario.containsKey(responsavel)) {
			List<Tarefa> tarefasDoUsuario = tarefasPorUsuario.get(responsavel);
			tarefasDoUsuario.remove(tarefa);
		}
	}
	
	public void atualizarTarefa(Tarefa tarefa) {
        Usuario responsavel = tarefa.getResponsavel();
        if (tarefasPorUsuario.containsKey(responsavel)) {
            List<Tarefa> tarefasDoUsuario = tarefasPorUsuario.get(responsavel);
            for (int i = 0; i < tarefasDoUsuario.size(); i++) {
                if (tarefasDoUsuario.get(i).getId().equals(tarefa.getId())) {
                    tarefasDoUsuario.set(i, tarefa);
                    break;
                }
            }
        }
    }
}