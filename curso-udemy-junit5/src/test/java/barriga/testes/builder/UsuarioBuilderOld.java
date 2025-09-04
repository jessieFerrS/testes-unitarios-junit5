package barriga.testes.builder;

import barriga.domain.Usuario;

public class UsuarioBuilderOld {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    // Construtor estático para iniciar o builder, ninguem fora dessa classe pode instanciar diretamente o UsuarioBuilder.
    private UsuarioBuilderOld(){}

    public static barriga.testes.builders.UsuarioBuilderOld umUsuario() {
        barriga.testes.builders.UsuarioBuilderOld builder = new barriga.testes.builders.UsuarioBuilderOld();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(barriga.testes.builders.UsuarioBuilderOld builder) {
        builder.id = 1L;
        builder.nome = "Usuario valido";
        builder.email = "user@email.com";
        builder.senha = "1234567";
    }

    // esse metodo só podera ser acessado depois de ter instanciado o builder
    public barriga.testes.builders.UsuarioBuilderOld comId(Long param) {
        id = param;
        return this;
    }

    public barriga.testes.builders.UsuarioBuilderOld comNome(String param) {
        nome = param;
        return this;
    }


    public barriga.testes.builders.UsuarioBuilderOld comEmail(String param) {
        email = param;
        return this;
    }


    public barriga.testes.builders.UsuarioBuilderOld comSenha(String param) {
        senha = param;
        return this;
    }

    public Usuario agora(){
        return new Usuario(id, nome, email, senha);
    }
}
