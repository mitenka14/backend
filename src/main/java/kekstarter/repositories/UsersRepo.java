package kekstarter.repositories;

import kekstarter.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByActivationCode(String code);
    Boolean existsByUsernameAndEmail(String username, String email);
}
