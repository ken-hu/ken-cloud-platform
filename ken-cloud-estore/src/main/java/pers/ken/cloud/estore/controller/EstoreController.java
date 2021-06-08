package pers.ken.cloud.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.ken.cloud.uc.account.api.TestApi;

import java.util.Arrays;

/**
 * <code>EstoreController</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/6 15:28.
 *
 * @author _Ken.Hu
 */
@RestController
@RequestMapping("/estore")
public class EstoreController {

    private final TestApi testApi;

    @Autowired
    public EstoreController(TestApi testApi) {
        this.testApi = testApi;
    }

    @GetMapping("/test")
    public void test(@RequestParam String content) {
        System.out.println(content);
        String ok = testApi.okokok("ok", "1", "1", Arrays.asList("1", "2", "3"));
        System.out.println(ok);
    }
}
