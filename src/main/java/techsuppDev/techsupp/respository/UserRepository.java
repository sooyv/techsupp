package techsuppDev.techsupp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsuppDev.techsupp.domain.User;

import javax.persistence.EntityManager;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}
