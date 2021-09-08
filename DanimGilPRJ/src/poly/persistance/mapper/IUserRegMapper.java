package kopo.poly.persistance.mongodb;

import kopo.poly.dto.UserInfoDTO;
import java.util.Map;

public interface IUserRegMapper {

    //회원가입
    int userReg(UserInfoDTO pDTO) throws Exception;

    //회원 탈퇴
    int userDel(UserInfoDTO pDTO) throws Exception;

    //아이디 중복 검사
    Map<String, String> getIdExist(String pUser_id) throws Exception;

    //이메일 중복 검사
    Map<String, String> getEmailExist(String pUser_email) throws Exception;

}
