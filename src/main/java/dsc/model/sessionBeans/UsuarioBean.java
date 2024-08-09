package dsc.model.sessionBeans;

import java.util.List;

import dsc.model.entidades.Usuario;
import dsc.model.repositorios.UsuarioRepositorio;
import jakarta.ejb.Stateless;

@Stateless
public class UsuarioBean {
    private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public void criarUsuario(Usuario usuario) {
        usuarioRepositorio.adicionarUsuario(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioRepositorio.atualizarUsuario(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarioRepositorio.removerUsuario(usuario.getId());
    }
    
    public Usuario buscarUsuarioPorId(String id) {
        return usuarioRepositorio.buscarUsuarioPorId(id);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepositorio.buscarUsuarioPorEmail(email);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.listarUsuarios();
    }
}
