package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.UserFilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends CrudRepository<UserFilmEntity, UserFilmEntityPK> {
}
