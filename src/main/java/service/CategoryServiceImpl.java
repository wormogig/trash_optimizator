package service;

import dao.CategoryDao;
import dao.CategoryDaoImpl;
import model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{

    private static CategoryServiceImpl instance;
    private CategoryDao categoryDao;

    private CategoryServiceImpl() {
        categoryDao = new CategoryDaoImpl();
    }

    public static CategoryServiceImpl getInstance() {
        if (instance == null) {
            synchronized (PointServiceImpl.class) {
                if (instance==null){
                    instance = new CategoryServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean addCategory(Category category) {
        return categoryDao.addObj(category);
    }

    @Override
    public Category getCategoryByID(long id) {
        return categoryDao.getCategoryByID(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }
}
