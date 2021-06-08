package pers.ken.cloud.uc.app.service.impl;

import pers.ken.cloud.uc.app.entity.App;
import pers.ken.cloud.uc.app.mapper.AppMapper;
import pers.ken.cloud.uc.app.service.AppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用信息 服务实现类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-06-05
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements AppService {

}
