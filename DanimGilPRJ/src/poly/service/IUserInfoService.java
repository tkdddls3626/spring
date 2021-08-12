package poly.service;


import poly.dto.UserInfoDTO;

public interface IUserInfoService {

	int insertUserInfo(UserInfoDTO pDTO) throws Exception;
	//회원가입하기(회원 정보 등록하기)
	
	//로그인을 위해 아이디와 비빌먼호가 일치하는지 확인하기
	int getUserLoginCheck(UserInfoDTO pDTO) throws Exception;
	
}
