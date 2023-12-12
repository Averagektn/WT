package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.FilmEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<FilmEntity, Long> {
}
