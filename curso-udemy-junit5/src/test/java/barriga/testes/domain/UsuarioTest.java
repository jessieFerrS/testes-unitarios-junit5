package barriga.testes.domain;

import barriga.domain.Usuario;
import barriga.domain.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static barriga.testes.builder.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dominio: Usuario")
public class UsuarioTest {

    @Test
    @DisplayName("Deve criar um usuário válido")
    public void deveCriarUsuarioValido() {
        Usuario usuario = umUsuario().agora();

        assertAll("Usuario",
                () -> assertEquals(1L, usuario.getId()),
                () -> assertEquals("Usuario valido", usuario.getNome()),
                () -> assertEquals("user@email.com", usuario.getEmail()),
                () -> assertEquals("1234567", usuario.getSenha())
        );
    }


    @Test
    @DisplayName("Deve rejeitar um usuário sem nome")
    public void deveRejeitarUsuarioSemNome() {
        ValidationException exception = assertThrows(ValidationException.class, ()->
            umUsuario().comNome(null).agora());
        assertEquals("Nome é obrigatório!", exception.getMessage());
    }


    @Test
    @DisplayName("Deve rejeitar um usuário sem email")
    public void deveRejeitarUsuarioSemEmail() {
        ValidationException exception = assertThrows(ValidationException.class, ()->
                umUsuario().comEmail(null).agora());
        assertEquals("E-mail é obrigatório!", exception.getMessage());
    }


    @Test
    @DisplayName("Deve rejeitar um usuário sem senha")
    public void deveRejeitarUsuarioSemSenha() {
        ValidationException exception = assertThrows(ValidationException.class, ()->
                umUsuario().comSenha(null).agora());
        assertEquals("Senha é obrigatória!", exception.getMessage());
    }

    //parametized tests -> serve para rodar o mesmo teste com varios parametros diferentes
//    @ParameterizedTest(name = "[{index}] - {4}")
//    @CsvFileSource(files = "src/test/resources/camposObrigatoriosUsuario.csv", nullValues = "NULL", numLinesToSkip = 1)
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/camposObrigatoriosUsuario.csv", nullValues = "NULL", useHeadersInDisplayName = true)
    @DisplayName("Deve validar os campos obrigatórios")
    public void deveValidarCamposObrigatorios(Long id, String nome, String email, String senha, String mensagemEsperada) {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora());
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
