package pers.ken.cloud.uc.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.ken.cloud.common.web.ServiceCode;
import pers.ken.cloud.uc.account.entity.Resource;
import pers.ken.cloud.uc.account.mapper.ResourceMapper;
import pers.ken.cloud.uc.data.DataPermission;

import java.util.List;

/**
 * <code>TestController</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/19 17:52.
 *
 * @author _Ken.Hu
 */
@RestController
public class TestController {

    private final ResourceMapper resourceMapper;

    @Autowired
    public TestController(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(String test) {
        return test;
    }

//    @DataPermission(ruleMetas = {"#ok","#channelId","#userId","#dept"}, name = "测试数据权限", desc = "描述信息")
    @GetMapping(value = "/okokok", produces = MediaType.APPLICATION_JSON_VALUE)
    public String okokok(@RequestParam String ok, @RequestParam String channelId, @RequestParam String userId, @RequestParam List<String> dept) {
        ServiceCode success = ServiceCode.SUCCESS;
        System.out.println("XXXXXXXXXXXXX" + success.getCode());
        System.out.println("XXXXXXXXXXXXX" + success.getDesc());
        List<Resource> resources = resourceMapper.selectList(new QueryWrapper<>());
        System.out.println(resources);
        return ok;
    }
}
