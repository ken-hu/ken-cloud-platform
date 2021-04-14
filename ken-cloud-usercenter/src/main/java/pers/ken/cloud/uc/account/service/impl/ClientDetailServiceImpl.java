package pers.ken.cloud.uc.account.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ken.cloud.uc.account.entity.ClientDetail;
import pers.ken.cloud.uc.account.mapper.ClientDetailMapper;
import pers.ken.cloud.uc.account.service.ClientDetailService;

/**
 * <p>
 * OauthClient信息表 服务实现类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
@Service
public class ClientDetailServiceImpl extends ServiceImpl<ClientDetailMapper, ClientDetail> implements ClientDetailService {

    private final ClientDetailMapper clientDetailMapper;

    @Autowired
    public ClientDetailServiceImpl(ClientDetailMapper clientDetailMapper) {
        this.clientDetailMapper = clientDetailMapper;
    }
    
    @Override
    public ClientDetail get(String clientId) {
        return clientDetailMapper.selectOne(
                Wrappers
                        .lambdaQuery(ClientDetail.class)
                        .eq(ClientDetail::getClientId, clientId)

        );
    }
}
