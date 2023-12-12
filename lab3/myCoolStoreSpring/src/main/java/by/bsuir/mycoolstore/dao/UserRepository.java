package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findByUsrBannedByIsNotNull();

    UserEntity findByUsrEmailAndUsrPassword(String usrEmail, String usrPassword);
}
