package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.LibraryRepository;
import by.bsuir.mycoolstore.entity.UserFilmEntityPK;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LibraryService {
    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository){
        this.libraryRepository = libraryRepository;
    }

    public Boolean isInLibrary(Long userId, Long filmId){
        var pk = new UserFilmEntityPK();
        pk.setUfUser(userId);
        pk.setUfFilm(filmId);

        return libraryRepository.findById(pk).isPresent();
    }
}
