package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.CartRepository;
import by.bsuir.mycoolstore.entity.CartEntityPK;
import by.bsuir.mycoolstore.entity.FilmEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<FilmEntity> getCartFilms(Long userId) {
        var carts = cartRepository.getCartEntityByCrtUser(userId);

    }

    public boolean isInCart(Long userId, Long filmId) {
        var pk = new CartEntityPK();
        pk.setCrtUser(userId);
        pk.setCrtFilm(filmId);

        return cartRepository.findById(pk).isPresent();
    }
}
