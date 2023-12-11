package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FeedbackRepository;
import by.bsuir.mycoolstore.entity.FeedbackEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository){
        this.feedbackRepository = feedbackRepository;
    }
    public List<FeedbackEntity> getFilmFeedbacks(Long filmId){
        return feedbackRepository.findByFbkFilm(filmId);
    }
}
