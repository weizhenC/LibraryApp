package JAC.FSD09.libraryapp.service;

import JAC.FSD09.libraryapp.domain.Category;
import JAC.FSD09.libraryapp.exception.IdNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategorys();

    Long saveCategory(Category category);

    Category findCategoryById(Long categoryId) throws IdNotFoundException;

    void deleteCategoryById(Long categoryId);
}
