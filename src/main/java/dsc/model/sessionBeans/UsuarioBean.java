package dsc.model.sessionBeans;

import java.util.List;

import dsc.model.entidades.Usuario;
import dsc.model.repositorios.UsuarioRepositorio;
import jakarta.ejb.Stateless;

@Stateless
public class UsuarioBean {
    private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public void criarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        usuarioRepositorio.adicionarUsuario(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        validarUsuario(usuario);
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
    
    private void validarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode estar em branco.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail do usuário não pode estar em branco.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do usuário não pode estar em branco.");
        }
    }
}

