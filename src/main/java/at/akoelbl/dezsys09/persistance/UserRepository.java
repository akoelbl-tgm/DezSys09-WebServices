package at.akoelbl.dezsys09.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The class UserRepository is used to access the User objects in the database.
 * @author Alexander Koelbl
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
}