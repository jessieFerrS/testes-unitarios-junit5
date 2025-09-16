package barriga.infra;

import barriga.domain.Usuario;
import barriga.domain.exception.ValidationException;
import barriga.service.UsuarioService;
import org.junit.jupiter.api.*;

import static barriga.testes.builder.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {

    private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    @Test
    @Order(1)
    public void deveSalvarUsuarioValiado(){
        Usuario user = service.salvar(umUsuario().comId(null).agora());
        assertNotNull(user.id());
        //assertEquals(2L, user.id());
    }

    @Test
    @Order(2)
    public void deveRejeitarUsuarioExistente(){
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(umUsuario().comId(null).agora()));
        assertEquals("Usuario com e-mail user@email.com já cadastrado!", exception.getMessage());
    }
}
