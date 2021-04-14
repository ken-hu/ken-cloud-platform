package pers.ken.cloud.uc.account.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ken.cloud.uc.account.dto.ResourceUpdateDTO;
import pers.ken.cloud.uc.account.service.RoleService;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@RestController
@RequestMapping("/account/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public void create() {
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
    }

    @PutMapping
    public void update(@RequestParam ResourceUpdateDTO resourceUpdateDTO) {
    }

    @GetMapping
    public void search(@RequestParam String name) {
    }

}

