package dsc.controller;

import java.util.List;

import dsc.model.entidades.Usuario;
import dsc.model.sessionBeans.UsuarioBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UsuarioMB {
    @EJB
    private UsuarioBean usuarioSessionBean;

    private Usuario usuario;
    private String id;
    
    public UsuarioMB() {
        this.usuario = new Usuario();  // Inicializa a propriedade usuario
    }

    public String criarUsuario() {
        usuarioSessionBean.criarUsuario(usuario);
        // Redirecionar para a página de login com mensagem de sucesso
        return "login?faces-redirect=true&msg=cadastroSucesso";
    }

    public String atualizarUsuario() {
        usuarioSessionBean.atualizarUsuario(usuario);
        // Redirecionar para a lista de usuários ou página inicial
        return "usuarios?faces-redirect=true";
    }

    public void removerUsuario() {
        usuarioSessionBean.removerUsuario(id);
        // Redirecionar para a lista de usuários
    }

    public Usuario buscarUsuario() {
        usuario = usuarioSessionBean.buscarUsuarioPorId(id);
        return usuario;
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
}
