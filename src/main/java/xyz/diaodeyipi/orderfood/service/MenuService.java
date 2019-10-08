package xyz.diaodeyipi.orderfood.service;

import xyz.diaodeyipi.orderfood.entity.Menu;

import java.util.List;

/**
 * @author yangsijie
 */
public interface MenuService {
    List<Menu> selectAll();
    Menu selectOne(String name);
    int insert(Menu menu);
    int update(Menu menu);
    int delete(String name);
}
