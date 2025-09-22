package barriga.infra;

import barriga.domain.Usuario;
import barriga.service.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Locale.filter;

public class UsuarioMemoryRepository implements UsuarioRepository {

    private List<Usuario> users;
    private Long currentId;

    public UsuarioMemoryRepository() {
        this.users = new ArrayList<>();
        this.currentId = 0L;
        salvar(new Usuario(null, "Usuario #1", "usuario1@email.com", "123456"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario newUser = new Usuario(nextId(), usuario.nome(), usuario.email(), usuario.senha());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.email().equalsIgnoreCase(email))
                .findFirst();
    }

    public void printUsers(){
        System.out.println(users);
    }


    private Long nextId() {
        return ++currentId;
    }

    public static void main(String[] args) {
        UsuarioMemoryRepository repo = new UsuarioMemoryRepository();
        repo.printUsers();
        repo.salvar(new Usuario(null, "Usuario #2", "user2@example.com", "654321"));
        repo.printUsers();
        repo.salvar(new Usuario(null, "Usuario #3", "user2@example.com", "9874563"));
        repo.printUsers();
    }
}
