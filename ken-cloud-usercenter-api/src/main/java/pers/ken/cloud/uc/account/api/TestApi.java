package pers.ken.cloud.uc.account.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.ken.cloud.uc.account.api.fallback.TestApiFallbackFactory;

import java.util.List;

/**
 * <code>TestApi</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/6 15:19.
 *
 * @author _Ken.Hu
 */
@FeignClient(
        value = "usercenter",
        contextId = "TestApi",
        fallbackFactory = TestApiFallbackFactory.class
)
public interface TestApi {
    @GetMapping(value = "/okokok", produces = MediaType.APPLICATION_JSON_VALUE)
    String okokok(@RequestParam(value = "ok") String ok,
                  @RequestParam(value = "channelId") String channelId,
                  @RequestParam(value = "userId") String userId,
                  @RequestParam(value = "dept") List<String> dept);
}
