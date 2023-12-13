package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FilmRepository;
import by.bsuir.mycoolstore.entity.FilmEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmService {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public FilmEntity save(FilmEntity film) {
        return filmRepository.save(film);
    }

    public List<FilmEntity> getFilms() {
        return (List<FilmEntity>) filmRepository.findAll();
    }

    public Optional<FilmEntity> getFilmById(Long id) {
        return filmRepository.findById(id);
    }
}
