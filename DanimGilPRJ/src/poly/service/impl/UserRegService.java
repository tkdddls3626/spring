package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mongodb.IUserRegMapper;
import kopo.poly.service.IUserRegService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Service("UserRegService")
public class UserRegService implements IUserRegService {

    @Resource(name="UserRegMapper")
    private IUserRegMapper userRegMapper;

    @Override
    public int userReg(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName()+".userReg Start!!");

        if(pDTO == null) {
            log.info("null값이 넘어와 메모리에 강제로 올림");
            pDTO = new UserInfoDTO();
        }

        int res = userRegMapper.userReg(pDTO);

        log.info(this.getClass().getName()+".userReg End!!");

        return res;
    }

    @Override
    public Map<String, String> getIdExist(String pUser_id) throws Exception {

        log.info(this.getClass().getName()+".getIdExist Start!!");

        if(pUser_id==null){
            log.info("null값이 넘어와 메모리에 강제로 올림");
            pUser_id="";
        }

        Map<String, String> rMap = userRegMapper.getIdExist(pUser_id);

        if(!CmmUtil.nvl(rMap.get("Exist_yn")).equals("y")) {
            log.info("아이디 중복 여부 : n ");
            rMap.put("Exist_yn","n");
        }

        log.info(this.getClass().getName()+".getIdExist End!!");

        return rMap;
    }

    @Override
    public Map<String, String> getEmailExist(String pUser_email) throws Exception {

        log.info(this.getClass().getName()+".getEmailExist Start!!");

        if(pUser_email==null){
            log.info("null값이 넘어와 메모리에 강제로 올림");
            pUser_email="";
        }

        Map<String, String> rMap = userRegMapper.getEmailExist(pUser_email);

        if(!CmmUtil.nvl(rMap.get("Exist_yn")).equals("y")) {
            log.info("이메일 중복 여부 : n ");
            rMap.put("Exist_yn","n");
        }

        log.info(this.getClass().getName()+".getIdExist End!!");

        return rMap;
    }

    @Override
    public int userDel(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+".userDel Start!!");


        if(pDTO == null) {
            pDTO = new UserInfoDTO();
            log.info("널값이 넘어와 강제로 메모리에 올림");
        }

        int res = 0;

        res = userRegMapper.userDel(pDTO);

        log.info(this.getClass().getName()+".userDel End!!");

        return res;
    }
}
