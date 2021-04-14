package pers.ken.cloud.uc.account.dto;

import lombok.Data;

/**
 * <code>ResourceCreateDTO</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/11 13:30.
 *
 * @author _Ken.Hu
 */
@Data
public class ResourceUpdateDTO {
    private String name;
    private String desc;
    private Integer resourceType;
    private String resourceKey;
    private String extInfo;
}
