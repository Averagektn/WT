package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.MediaRepository;
import by.bsuir.mycoolstore.entity.FilmMediaEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class MediaService {
    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public Optional<FilmMediaEntity> getFIlmMedia(Long filmId) {
        return mediaRepository.findById(filmId);
    }

    public void save(FilmMediaEntity filmMedia) {
        mediaRepository.save(filmMedia);
    }
}
