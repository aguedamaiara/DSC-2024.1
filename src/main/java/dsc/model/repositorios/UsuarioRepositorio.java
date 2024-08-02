package dsc.model.repositorios;


import dsc.model.entidades.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositorio {
    private List<Usuario> usuarios = new ArrayList<>();
    private int idCounter = 1;

    public void adicionarUsuario(Usuario usuario) {
        usuario.setId(String.valueOf(idCounter++));
        usuarios.add(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                usuarios.set(i, usuario);
                return;
            }
        }
    }

    public void removerUsuario(String id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public Usuario buscarUsuarioPorId(String id) {
        return usuarios.stream()
                       .filter(usuario -> usuario.getId().equals(id))
                       .findFirst()
                       .orElse(null);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarios.stream()
                       .filter(usuario -> usuario.getEmail().equals(email))
                       .findFirst()
                       .orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }
}
