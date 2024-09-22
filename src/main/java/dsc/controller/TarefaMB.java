package dsc.controller;

import java.io.Serializable;
import java.util.List;

import dsc.model.entidades.Tarefa;
import dsc.model.entidades.Usuario;
import dsc.model.sessionBeans.TarefaBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

//@RequestScoped
@Named
@SessionScoped
public class TarefaMB implements Serializable {
	private static final long serialVersionUID = -4272609180484517298L;

	@EJB
	private TarefaBean tarefaSessionBean;

	@Inject
	private LoginMB loginBean;
	
	private Tarefa tarefa;
	private Tarefa tarefaSelecionada = new Tarefa();
	private Long tarefaIdParaEdicao;

	public TarefaMB() {
		this.tarefa = new Tarefa();
	}


	public String criarTarefa() {
	    try {
	        tarefa.setResponsavel(loginBean.getUsuarioLogado());
	        tarefaSessionBean.adicionarTarefa(tarefa);
	        tarefa = new Tarefa();
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa criada com sucesso!", null));
	        return "home?faces-redirect=true";
	    } catch (IllegalArgumentException e) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
	        return null;
	    }
	}

	public List<Tarefa> listarTarefas() {
		return tarefaSessionBean.listarTarefas(loginBean.getUsuarioLogado());
	}



	public String carregarTarefaParaEdicao(String id) {
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
        try {
            tarefaSessionBean.atualizarTarefa(tarefaSelecionada);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa atualizada com sucesso!", null));
            tarefaSelecionada = new Tarefa(); // Reseta a tarefa selecionada
            return "home?faces-redirect=true";
        } catch (IllegalArgumentException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            return null;
        }
    }
    
    public void removerTarefa(String id) {
        tarefaSessionBean.removerTarefa(id);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa removida com sucesso!", null));
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

	public Long getTarefaIdParaEdicao() {
		return tarefaIdParaEdicao;
	}

	public void setTarefaIdParaEdicao(Long tarefaIdParaEdicao) {
		this.tarefaIdParaEdicao = tarefaIdParaEdicao;
	}
}
