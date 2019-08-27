package dao;

import model.Category;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class CategoryDaoImpl extends AbstractDaoImpl implements CategoryDao {

    @Override
    public Category getCategoryByID(long id) {
        return super.getById(id, Category.class.getName());
    }

    @Override
    public List<Category> getAllCategories() {
        Session session = sessionFactory.openSession();
        List<Category> categories = null;
        try {
            Query query = session.createQuery("FROM Category");
            categories = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;
    }


}
