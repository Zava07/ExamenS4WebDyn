package main.Java.Model;


import java.util.List;

public abstract class BaseObject {
    int id;

    public BaseObject(int id) {
        this.id = id;
    }

    public abstract void save() throws Exception;

    public abstract BaseObject findById() throws Exception;

    public abstract List<BaseObject> findAll() throws Exception;

    public abstract List<BaseObject> findAllWithPagination(int index, int count) throws Exception;

    public abstract void delete() throws Exception;

    public abstract void update() throws Exception;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
