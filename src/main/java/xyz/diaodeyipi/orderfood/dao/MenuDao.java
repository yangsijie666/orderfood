package xyz.diaodeyipi.orderfood.dao;

import org.springframework.stereotype.Repository;
import xyz.diaodeyipi.orderfood.entity.Menu;

import java.util.List;

/**
 * @author yangsijie
 */
@Repository("menuDao")
public interface MenuDao {
    List<Menu> selectAll();
    Menu selectOne(String name);
    Menu selectById(int id);
    int insert(Menu menu);
    int update(Menu menu);
    int delete(String name);
}
