package kekstarter.repositories;

import kekstarter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
    Boolean existsByUsernameAndEmail(String username, String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findById (long id);
    User deleteById(long id);
}
