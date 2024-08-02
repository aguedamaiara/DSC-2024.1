package dsc.controller;

import java.io.Serializable;
import java.util.List;

import dsc.model.entidades.Tarefa;
import dsc.model.entidades.Usuario;
import dsc.model.sessionBeans.TarefaSessionBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class TarefaBean implements Serializable {
	private Tarefa tarefa = new Tarefa();
	private Tarefa tarefaSelecionada = new Tarefa();
	private Integer tarefaIdParaEdicao;

	@EJB
	private TarefaSessionBean tarefaSessionBean;

	@Inject
	private LoginBean loginBean;

	public String criarTarefa() {
		tarefa.setResponsavel(loginBean.getUsuarioLogado());
		tarefaSessionBean.adicionarTarefa(tarefa);
		tarefa = new Tarefa();
		return "home?faces-redirect=true";
	}

	public List<Tarefa> listarTarefas() {
		return tarefaSessionBean.listarTarefas(loginBean.getUsuarioLogado());
	}

	public String removerTarefa(Tarefa tarefa) {
		tarefaSessionBean.removerTarefa(tarefa);
		return "home?faces-redirect=true";
	}

	public String carregarTarefaParaEdicao(Integer id) {
		Usuario usuario = loginBean.getUsuarioLogado();
		List<Tarefa> tarefas = listarTarefas();

		for (Tarefa t : tarefas) {
			if (t.getId().equals(id)) {
				this.tarefaSelecionada = t;
				break;
			}
		}
		return null;
	}

	public String atualizarTarefa() {
		if (tarefaSelecionada != null && tarefaSelecionada.getId() != null) {
			tarefaSelecionada.setResponsavel(loginBean.getUsuarioLogado());
			tarefaSessionBean.atualizarTarefa(tarefaSelecionada);
			tarefaSelecionada = new Tarefa();
			  FacesContext.getCurrentInstance().addMessage(null,
			            new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa atualizada com sucesso!", null));

			        return "home?faces-redirect=true";
			    }

			    FacesContext.getCurrentInstance().addMessage(null,
			        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar tarefa. Tarefa selecionada inválida.", null));

			    return "home?faces-redirect=true";
			}

	// Getters e Setters
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}

	public Integer getTarefaIdParaEdicao() {
		return tarefaIdParaEdicao;
	}

	public void setTarefaIdParaEdicao(Integer tarefaIdParaEdicao) {
		this.tarefaIdParaEdicao = tarefaIdParaEdicao;
	}
}
