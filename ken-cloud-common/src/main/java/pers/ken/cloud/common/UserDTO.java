package pers.ken.cloud.common;

import lombok.Data;

import java.util.Map;

/**
 * <code>UserDTO</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/6 12:54.
 *
 * @author _Ken.Hu
 */
@Data
public class UserDTO {
    private Long id;
    private String username;
    private Map<String, String> extInfo;
}
