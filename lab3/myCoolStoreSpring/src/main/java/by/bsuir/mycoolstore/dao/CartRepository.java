package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.CartEntity;
import by.bsuir.mycoolstore.entity.CartEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, CartEntityPK> {
    void deleteByCrtUser(Long crtUser);
    List<CartEntity> getCartByCrtUser(Long userId);
}
