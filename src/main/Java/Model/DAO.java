package main.Java.Model;

import java.util.List;
public interface DAO {
    void save(BaseModel m) throws Exception;
    void delete(int id) throws Exception;
    void update(BaseModel m) throws Exception;
    List<BaseModel> findAll() throws Exception;
    BaseModel findById(int id) throws Exception;
    List<BaseModel> findAllWithPagination(int index, int count) throws Exception;

}
