package br.com.fiap.gs_ddd.model.repository;

import br.com.fiap.gs_ddd.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Exemplo de consulta personalizada (opcional)
    Usuario findByEmail(String email);
}
