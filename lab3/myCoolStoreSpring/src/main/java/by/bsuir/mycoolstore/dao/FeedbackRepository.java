package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.FeedbackEntity;
import by.bsuir.mycoolstore.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<FeedbackEntity, Long> {
    List<FeedbackEntity> findByFbkFilm(Long fbkFilm);

    void deleteAllByFbkAuthor(UserEntity user);
}
