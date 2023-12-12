package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.UserRepository;
import by.bsuir.mycoolstore.entity.UserEntity;
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
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean isBanned(Long userId) {
        var user = userRepository.findById(userId);

        return user.map(userEntity -> userEntity.getUsrBannedBy() != null).orElse(Boolean.FALSE);
    }

    public Long registration(UserEntity user) throws ServiceException {
        var savedUser = userRepository.save(user);

        logger.info("Registered user: " + savedUser.getUsrId() + " " + savedUser.getUsrEmail());

        return savedUser.getUsrId();
    }

    public List<UserEntity> getBannedUsers() {
        return userRepository.findByUsrBannedByIsNotNull();
    }

    public Optional<UserEntity> signIn(UserEntity user) throws ServiceException {
        return Optional.ofNullable(userRepository.findByUsrEmailAndUsrPassword(user.getUsrEmail(), user.getUsrPassword()));
    }
}
