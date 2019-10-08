package xyz.diaodeyipi.orderfood.controller;

import org.springframework.web.bind.annotation.*;
import xyz.diaodeyipi.orderfood.entity.Menu;
import xyz.diaodeyipi.orderfood.service.MenuService;

import java.util.List;

/**
 * @author yangsijie
 */
@RestController("menuController")
@RequestMapping(path = "/menu")
public class MenuController {
    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(path = "/list")
    public List<Menu> selectAll() {
        return menuService.selectAll();
    }

    @GetMapping(path = "/list/{name}")
    public Menu selectOne(@PathVariable("name") String name) {
        return menuService.selectOne(name);
    }

    @PostMapping(path = "/")
    public String insert(Menu menu) {
        int result = menuService.insert(menu);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PutMapping(path = "/")
    public String update(Menu menu) {
        int result = menuService.update(menu);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @DeleteMapping(path = "/{name}")
    public String delete(@PathVariable("name") String name) {
        menuService.delete(name);
        return "success";
    }
}
