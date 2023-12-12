package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.UserFilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends CrudRepository<UserFilmEntity, UserFilmEntityPK> {
    List<UserFilmEntity> getLibraryByUfUser(Long userId);
}
