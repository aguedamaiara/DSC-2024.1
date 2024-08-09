package dsc.controller;

import java.util.List;

import dsc.model.entidades.Usuario;
import dsc.model.sessionBeans.UsuarioBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UsuarioMB {
    @EJB
    private UsuarioBean usuarioSessionBean;

    private Usuario usuario;
    private String id;
    
    private Usuario usuarioSelecionado = new Usuario();
    
    public UsuarioMB() {
        this.usuario = new Usuario();  // Inicializa a propriedade usuario
    }

    public String criarUsuario() {
        usuarioSessionBean.criarUsuario(usuario);
        // Redirecionar para a página de login com mensagem de sucesso
        return "login?faces-redirect=true&msg=cadastroSucesso";
    }

	/*
	 * public String atualizarUsuario() {
	 * usuarioSessionBean.atualizarUsuario(usuario); // Redirecionar para a lista de
	 * usuários ou página inicial return "usuarios?faces-redirect=true"; }
	 */
    
	/*
	 * public String atualizarUsuario() { Usuario existingUser =
	 * buscarUsuarioPorId(usuario.getId()); if (existingUser != null) {
	 * existingUser.setNome(usuario.getNome());
	 * existingUser.setEmail(usuario.getEmail());
	 * existingUser.setSenha(usuario.getSenha());
	 * usuarioSessionBean.atualizarUsuario(existingUser); } return
	 * "usuarios?faces-redirect=true"; }
	 */
    
    // Método para carregar o usuário selecionado para edição
    public String carregarUsuarioParaEdicao(String id) {
        this.usuarioSelecionado = usuarioSessionBean.buscarUsuarioPorId(id);
        if (this.usuarioSelecionado != null) {
            return null; // Sucesso ao carregar
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar usuário para edição.", null));
            return "usuarios?faces-redirect=true"; // Erro ao carregar
        }
    }

    // Método para atualizar o usuário
    public String atualizarUsuario() {
        if (usuarioSelecionado != null && usuarioSelecionado.getId() != null) {
            usuarioSessionBean.atualizarUsuario(usuarioSelecionado);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário atualizado com sucesso!", null));
            return "usuarios?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar usuário. Usuário selecionado inválido.", null));
            return "usuarios?faces-redirect=true";
        }
    }

    
    
	/*
	 * public String atualizarUsuario() { usuario.setNome(usuario.getNome());
	 * usuario.setEmail(usuario.getEmail()); usuario.setSenha(usuario.getSenha());
	 * usuarioSessionBean.atualizarUsuario(usuario); return
	 * "usuarios?faces-redirect=true"; }
	 */
    
    public void removerUsuario(String id) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario != null) {
            usuarioSessionBean.removerUsuario(usuario);
        }
    }
    public Usuario buscarUsuario() {
        usuario = usuarioSessionBean.buscarUsuarioPorId(id);
        return usuario;
    }
    
    public Usuario buscarUsuarioPorId(String id) {
        return usuarioSessionBean.buscarUsuarioPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioSessionBean.listarUsuarios();
    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
}
