package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.CartRepository;
import by.bsuir.mycoolstore.dao.FilmRepository;
import by.bsuir.mycoolstore.dao.LibraryRepository;
import by.bsuir.mycoolstore.entity.CartEntity;
import by.bsuir.mycoolstore.entity.CartEntityPK;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final FilmRepository filmRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public CartService(CartRepository cartRepository, FilmRepository filmRepository, LibraryRepository libraryRepository) {
        this.cartRepository = cartRepository;
        this.filmRepository = filmRepository;
        this.libraryRepository = libraryRepository;
    }

    public List<FilmEntity> getCartFilms(Long userId) {
        var carts = cartRepository.getCartByCrtUser(userId);

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

    public void buy(Long userId) {
        var library = new ArrayList<UserFilmEntity>();
        var cart = cartRepository.getCartByCrtUser(userId);

        for (var c: cart){
            var ufe = new UserFilmEntity();
            ufe.setUfFilm(c.getCrtFilm());
            ufe.setUfUser(c.getCrtUser());
            library.add(ufe);
        }

        libraryRepository.saveAll(library);

        cartRepository.deleteByCrtUser(userId);
    }

    public void remove(Long userId){
        cartRepository.deleteByCrtUser(userId);
    }
}
