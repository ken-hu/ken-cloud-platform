package pers.ken.cloud.uc.auth;

import lombok.Data;

import java.util.Map;

/**
 * <code>OauthUser</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/20 23:43.
 *
 * @author _Ken.Hu
 */
@Data
public class OauthUser {
    private Long id;
    private String username;
    private Map<String,String> extInfo;
}
