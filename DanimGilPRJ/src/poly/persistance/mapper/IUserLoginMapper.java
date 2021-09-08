package kopo.poly.persistance.mongodb;

import kopo.poly.dto.UserInfoDTO;

public interface IUserLoginMapper {
    // 유저 로그인
    UserInfoDTO userLogin(UserInfoDTO pDTO) throws Exception;
}
