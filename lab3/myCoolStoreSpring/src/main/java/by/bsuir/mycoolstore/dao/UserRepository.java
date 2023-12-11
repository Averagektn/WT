package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findByUsrBannedByIsNotNull();

    UserEntity findByUsrEmailAndUsrPassword(String usrEmail, String usrPassword);
}
