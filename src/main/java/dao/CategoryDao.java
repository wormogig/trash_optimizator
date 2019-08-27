package dao;

import model.Category;

import java.util.List;

public interface CategoryDao extends AbstractDao{
//    public boolean addCategory(Category category);
    public Category getCategoryByID (long id);
    public List<Category> getAllCategories();
}
