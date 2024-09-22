package dsc.model.sessionBeans;

import java.util.List;

import dsc.model.entidades.StatusTarefa;
import dsc.model.entidades.Tarefa;
import dsc.model.entidades.Usuario;
import dsc.model.repositorios.TarefaRepositorio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class TarefaBean {

	@EJB
	private TarefaRepositorio tarefaRepositorio;

	public Tarefa adicionarTarefa(Tarefa tarefa) {
		validarTarefa(tarefa);
		return this.tarefaRepositorio.adicionarTarefa(tarefa);
	}

	public List<Tarefa> listarTarefas(Usuario usuario) {
		return tarefaRepositorio.listarTarefasPorUsuario(usuario);
	}

	public Tarefa atualizarTarefa(Tarefa tarefa) {
		validarTarefa(tarefa);
		return tarefaRepositorio.atualizarTarefa(tarefa);
	}

	public void removerTarefa(String id) {
		tarefaRepositorio.removerTarefa(id);
	}

	private void validarTarefa(Tarefa tarefa) throws IllegalArgumentException {
		if (tarefa.getTitulo() == null || tarefa.getTitulo().trim().isEmpty()) {
			throw new IllegalArgumentException("O título da tarefa não pode estar em branco.");
		}
		if (tarefa.getDescricao() == null || tarefa.getDescricao().trim().isEmpty()) {
			throw new IllegalArgumentException("A descrição da tarefa não pode estar em branco.");
		}
		if (tarefa.getStatus() == null || tarefa.getStatus().equals(StatusTarefa.SELECIONE)) {
			throw new IllegalArgumentException("Você deve selecionar um status válido para a tarefa.");
		}
		if (!isValidStatus(tarefa.getStatus())) {
			throw new IllegalArgumentException("Status da tarefa inválido.");
		}
	}

	private boolean isValidStatus(StatusTarefa status) {
		for (StatusTarefa s : StatusTarefa.values()) {
			if (s == status) {
				return true;
			}
		}
		return false;
	}
}