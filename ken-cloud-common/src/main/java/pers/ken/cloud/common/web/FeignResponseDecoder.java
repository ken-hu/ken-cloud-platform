package pers.ken.cloud.common.web;

import com.alibaba.fastjson.JSON;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * <code>FeignResultDecoder</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/3 22:26.
 *
 * @author _Ken.Hu
 */
@Configuration
@SuppressWarnings("rawtypes")
public class FeignResponseDecoder implements Decoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (Objects.isNull(response.body())) {
            throw new DecodeException(response.status(), "No valid data returned", response.request());
        }
        String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
        //对结果进行转换
        PlatformResult platformResult = JSON.parseObject(bodyStr, PlatformResult.class);
        //如果返回错误，且为内部错误，则直接抛出异常
        return platformResult.getData();
    }
}
