package dsc.model.sessionBeans;

import java.util.List;

import dsc.model.entidades.Tarefa;
import dsc.model.entidades.Usuario;
import dsc.model.repositorios.TarefaRepositorio;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class TarefaBean {

	@Inject
	private TarefaRepositorio tarefaRepositorio;

	public void adicionarTarefa(Tarefa tarefa) {
		tarefaRepositorio.adicionarTarefa(tarefa);
	}

	public List<Tarefa> listarTarefas(Usuario usuario) {
		return tarefaRepositorio.listarTarefas(usuario);
	}

	public void removerTarefa(Tarefa tarefa) {
		tarefaRepositorio.removerTarefa(tarefa);
	}
	
	public void atualizarTarefa(Tarefa tarefa) {
		tarefaRepositorio.atualizarTarefa(tarefa);
	}

}