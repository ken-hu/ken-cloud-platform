package pers.ken.cloud.common.web;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * <code>FeignErrorDecoder</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/3 22:51.
 *
 * @author _Ken.Hu
 */
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return null;
    }
}
