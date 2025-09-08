package barriga.testes.domain;

import barriga.domain.Conta;
import barriga.domain.Usuario;
import barriga.domain.exception.ValidationException;
import barriga.testes.builder.ContaBuilder;
import barriga.testes.builder.UsuarioBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static barriga.testes.builder.ContaBuilder.umaConta;
import static barriga.testes.builder.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    @Test
    public void deveCriarContaValida() {

        //CRIAR UMA CONTA
        Conta conta = umaConta().agora();

        // ASSERTIVAS EM CIMA DA CONTA
        assertAll("Conta",
                () -> assertEquals(1L, conta.id()),
                () -> assertEquals("Conta valida", conta.nome()),
                () -> assertEquals(umUsuario().agora(), conta.usuario())
        );
    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void deveRejeitarContaInvalida(Long id, String nome, Usuario usuario, String mensagemEsperada) {
        String errorMessage = assertThrows(ValidationException.class, () ->
                umaConta().comId(id).comNome(nome).comUsuario(usuario).agora()).getMessage();
        assertEquals(mensagemEsperada, errorMessage);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, umUsuario().agora(), "Nome é obrigatório!"),
                Arguments.of(1L, "Conta valida", null, "Usuário é obrigatório!")
        );
    }

}
