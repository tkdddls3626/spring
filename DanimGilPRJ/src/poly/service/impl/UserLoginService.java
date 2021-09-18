package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mongodb.IUserLoginMapper;
import kopo.poly.service.IUserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("UserLoginService")
public class UserLoginService implements IUserLoginService {
    @Resource(name = "UserLoginMapper")
    private IUserLoginMapper userLoginMapper;

    @Override
    public UserInfoDTO userLogin(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName()+".userLogin Start!!");

        if(pDTO == null){
            pDTO = new UserInfoDTO();
            log.info("널값이 넘어와 메모리에 강제로 올림");
        }

        UserInfoDTO rDTO = userLoginMapper.userLogin(pDTO);

        log.info(this.getClass().getName()+".userLogin End!!");

        return rDTO;
    }
}
