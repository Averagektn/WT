package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.FilmMediaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<FilmMediaEntity, Long> {
}
