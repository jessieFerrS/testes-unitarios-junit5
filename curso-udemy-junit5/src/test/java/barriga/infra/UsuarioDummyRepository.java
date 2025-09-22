package barriga.infra;

import barriga.domain.Usuario;
import barriga.service.repositories.UsuarioRepository;
import barriga.testes.builder.UsuarioBuilder;

import java.util.Optional;

import static barriga.testes.builder.UsuarioBuilder.umUsuario;

public class UsuarioDummyRepository implements UsuarioRepository {

    @Override
    public Usuario salvar(Usuario usuario) {
        return umUsuario()
                .comNome(usuario.nome())
                .comEmail(usuario.email())
                .comSenha(usuario.senha())
                .agora();
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        if ("user@email.com".equals(email))
            return Optional.of(umUsuario().comEmail(email).agora());
        return Optional.empty();
    }
}
