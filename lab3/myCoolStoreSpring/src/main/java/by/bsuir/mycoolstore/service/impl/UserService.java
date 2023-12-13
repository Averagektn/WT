package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FeedbackRepository;
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
    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public UserService(UserRepository userRepository, FeedbackRepository feedbackRepository) {
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public Boolean isBanned(Long userId) {
        var user = userRepository.findById(userId);

        return user.map(userEntity -> userEntity.getUsrBannedBy() != null).orElse(Boolean.FALSE);
    }

    public Long registration(UserEntity user) throws ServiceException {
        UserEntity savedUser;

        if (user.getUsrPassword().length() < 8) {
            throw new ServiceException("Password length is less than 8");
        }

        if (user.getUsrBannedBy() != null) {
            throw new ServiceException("Registration of banned user");
        }

        try {
            savedUser = userRepository.save(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        return savedUser.getUsrId();
    }

    public List<UserEntity> getBannedUsers() {
        return userRepository.findByUsrBannedByIsNotNull();
    }

    public Optional<UserEntity> signIn(UserEntity user) {
        return Optional.ofNullable(userRepository.findByUsrEmailAndUsrPassword(user.getUsrEmail(), user.getUsrPassword()));
    }

    public void ban(Long userId, Long adminId) {
        var user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().setUsrBannedBy(adminId);
            feedbackRepository.deleteAllByFbkAuthor(user.get());
        }
    }

    public void unban(Long userId) {
        var user = userRepository.findById(userId);

        user.ifPresent(userEntity -> userEntity.setUsrBannedBy(null));
    }
}
