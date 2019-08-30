package dao;

import model.AbstractDbObj;

public interface AbstractDao {
    public boolean addObj(AbstractDbObj obj);
    public boolean deleteAll (String className);
    public <T extends AbstractDbObj> T getById(long id, String className);
}
