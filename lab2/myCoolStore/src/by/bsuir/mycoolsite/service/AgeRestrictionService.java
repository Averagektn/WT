package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface AgeRestrictionService {
    List<String> getAgeRestrictions() throws ServiceException;
}
