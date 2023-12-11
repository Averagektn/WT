package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.CartRepository;
import by.bsuir.mycoolstore.entity.CartEntityPK;
import jakarta.persistence.Access;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public boolean isInCart(Long userId, Long filmId){
        var pk = new CartEntityPK();
        pk.setCrtUser(userId);
        pk.setCrtFilm(filmId);

        return cartRepository.findById(pk).isPresent();
    }
}
