package pers.ken.cloud.uc.account.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ken.cloud.common.web.ServiceCode;
import pers.ken.cloud.uc.oauth.DataPermission;

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

    @DataPermission(name = "just...")
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(String test) {
        return test;
    }

    @GetMapping(value = "/okokok", produces = MediaType.APPLICATION_JSON_VALUE)
    public String okokok(String ok) {
        ServiceCode success = ServiceCode.SUCCESS;
        System.out.println("XXXXXXXXXXXXX" + success.getCode());
        System.out.println("XXXXXXXXXXXXX" + success.getDesc());
        return ok;
    }
}
