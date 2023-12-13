package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FeedbackRepository;
import by.bsuir.mycoolstore.entity.FeedbackEntity;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<FeedbackEntity> getFilmFeedbacks(Long filmId) {
        return feedbackRepository.findByFbkFilm(filmId);
    }

    public void save(FeedbackEntity feedback) throws ServiceException {
        if (feedback.getFbkRating() > 10 || feedback.getFbkRating() < 0) {
            throw new ServiceException("Invalid rating");
        }
        feedbackRepository.save(feedback);
    }
}
