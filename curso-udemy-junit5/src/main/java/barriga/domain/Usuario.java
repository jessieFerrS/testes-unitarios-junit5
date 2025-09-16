package barriga.domain;

import barriga.domain.exception.ValidationException;

import java.util.Objects;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(Long id, String nome, String email, String senha) {
        if(nome == null || nome.isEmpty()) {
            throw new ValidationException("Nome é obrigatório!");
        }
        if(email == null || email.isEmpty()) {
            throw new ValidationException("E-mail é obrigatório!");
        }
        if(senha == null || senha.isEmpty()) {
            throw new ValidationException("Senha é obrigatória!");
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public String email() {
        return email;
    }

    public String senha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
