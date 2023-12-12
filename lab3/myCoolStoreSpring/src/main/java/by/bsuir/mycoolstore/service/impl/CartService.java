package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.CartRepository;
import by.bsuir.mycoolstore.dao.FilmRepository;
import by.bsuir.mycoolstore.entity.CartEntity;
import by.bsuir.mycoolstore.entity.CartEntityPK;
import by.bsuir.mycoolstore.entity.FilmEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public CartService(CartRepository cartRepository, FilmRepository filmRepository) {
        this.cartRepository = cartRepository;
        this.filmRepository = filmRepository;
    }

    public List<FilmEntity> getCartFilms(Long userId) {
        var carts = cartRepository.getFilmsByCrtUser(userId);

        return (List<FilmEntity>) filmRepository.findAllById(carts.stream().map(CartEntity::getCrtFilm)
                .collect(Collectors.toList()));
    }

    public boolean isInCart(Long userId, Long filmId) {
        var pk = new CartEntityPK();
        pk.setCrtUser(userId);
        pk.setCrtFilm(filmId);

        return cartRepository.findById(pk).isPresent();
    }

    public void save(CartEntity cart) {
        cartRepository.save(cart);
    }
}
