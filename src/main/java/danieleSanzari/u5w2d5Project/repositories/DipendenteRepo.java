package danieleSanzari.u5w2d5Project.repositories;

import danieleSanzari.u5w2d5Project.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepo extends JpaRepository<Dipendente, Integer> {
    Optional<Dipendente> findByUsername(String username);

    boolean existsByUsername(String username);
}
