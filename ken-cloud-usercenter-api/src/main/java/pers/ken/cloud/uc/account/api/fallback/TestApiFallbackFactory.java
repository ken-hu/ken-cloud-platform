package pers.ken.cloud.uc.account.api.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import pers.ken.cloud.uc.account.api.TestApi;

/**
 * <code>TestFallbackFactory</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/6 15:21.
 *
 * @author _Ken.Hu
 */
public class TestApiFallbackFactory implements FallbackFactory<TestApi> {
    @Override
    public TestApi create(Throwable cause) {
        return null;
    }
}
