package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
