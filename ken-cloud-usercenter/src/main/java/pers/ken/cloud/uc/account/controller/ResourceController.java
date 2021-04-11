package pers.ken.cloud.uc.account.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ken.cloud.uc.account.dto.ResourceCreateDTO;
import pers.ken.cloud.uc.account.service.ResourceService;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@RestController
@RequestMapping("/account/resource")
public class ResourceController {
    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public void create(@RequestBody ResourceCreateDTO resourceCreateDTO) {
        resourceService.create(resourceCreateDTO);
    }

    @DeleteMapping
    public void delete() {
    }

    @PutMapping
    public void update() {
    }

    @GetMapping
    public void search() {
    }

}

