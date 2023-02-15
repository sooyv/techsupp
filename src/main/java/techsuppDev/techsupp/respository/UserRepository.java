package techsuppDev.techsupp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
