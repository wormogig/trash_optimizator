package dao;

import model.AbstractDbObj;

public interface AbstractDao {
    public boolean addObj(AbstractDbObj obj);
    public <T extends AbstractDbObj> T getById(long id, String className);
}
