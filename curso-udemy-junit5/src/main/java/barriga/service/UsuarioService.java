package barriga.service;

import barriga.domain.Usuario;
import barriga.domain.exception.ValidationException;
import barriga.service.repositories.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        repository.getUserByEmail(usuario.email()).ifPresent(user -> {
            throw new ValidationException(String.format("Usuario com e-mail %s já cadastrado!", usuario.email()));
        });
        return repository.salvar(usuario);
    }
}
