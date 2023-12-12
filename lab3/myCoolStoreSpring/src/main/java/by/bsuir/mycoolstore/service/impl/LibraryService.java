package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FilmRepository;
import by.bsuir.mycoolstore.dao.LibraryRepository;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntityPK;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository, FilmRepository filmRepository) {
        this.libraryRepository = libraryRepository;
        this.filmRepository = filmRepository;
    }

    public List<FilmEntity> getUserFilms(Long userId) {
        var carts = libraryRepository.getLibraryByUfUser(userId);

        return (List<FilmEntity>) filmRepository.findAllById(carts.stream().map(UserFilmEntity::getUfFilm)
                .collect(Collectors.toList()));
    }

    public Boolean isInLibrary(Long userId, Long filmId) {
        var pk = new UserFilmEntityPK();
        pk.setUfUser(userId);
        pk.setUfFilm(filmId);

        return libraryRepository.findById(pk).isPresent();
    }
}
