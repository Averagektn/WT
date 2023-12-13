package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FilmRepository;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmService {
    private static final Logger logger = LogManager.getLogger(FilmService.class);
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public FilmEntity save(FilmEntity film) {
        return filmRepository.save(film);
    }

    public List<FilmEntity> getFilms() throws ServiceException {
        return (List<FilmEntity>) filmRepository.findAll();
    }

    public Optional<FilmEntity> getFilmById(Long id) {
        return filmRepository.findById(id);
    }
}
