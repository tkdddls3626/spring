package kopo.poly.service;

import kopo.poly.dto.UserInfoDTO;

public interface IUserLoginService {
    UserInfoDTO userLogin(UserInfoDTO pDTO) throws Exception;
}
