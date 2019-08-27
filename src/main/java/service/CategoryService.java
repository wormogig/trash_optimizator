package service;

import model.Category;

import java.util.List;

public interface CategoryService {
    public boolean addCategory(Category category);
    public Category getCategoryByID(long id);
    public List<Category> getAllCategories();
}
