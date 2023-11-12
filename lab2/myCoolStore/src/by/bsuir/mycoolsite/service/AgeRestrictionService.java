package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface AgeRestrictionService {
    List<AgeRestriction> getAgeRestrictions() throws ServiceException;
}
