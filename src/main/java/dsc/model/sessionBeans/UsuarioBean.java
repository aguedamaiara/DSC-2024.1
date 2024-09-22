package dsc.model.sessionBeans;

import java.util.List;

import dsc.model.entidades.Usuario;
import dsc.model.repositorios.UsuarioRepositorio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UsuarioBean {
	
    @EJB
	private UsuarioRepositorio urep;

    public Usuario criarUsuario(Usuario usuario) {
        validarUsuario(usuario);
      return this.urep.adicionarUsuario(usuario);
    }
    
    public Usuario buscarUsuarioPorId(String id) {
        return urep.buscarUsuarioPorId(id);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return urep.buscarUsuarioPorEmail(email);
    }

    public List<Usuario> listarUsuarios() {
        return urep.listarUsuarios();
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

