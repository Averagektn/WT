package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.CategoryRepository;
import by.bsuir.mycoolstore.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getCategories(){
        return (List<CategoryEntity>) categoryRepository.findAll();
    }
}
