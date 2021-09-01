package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserRegService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class UserRegController {

    @Resource(name="UserRegService")
    private IUserRegService userRegService;

    @RequestMapping(value = "/user/userReg")
    public String userReg(HttpServletRequest request, ModelMap model) throws Exception{
        log.info(this.getClass().getName()+".userReg Start!!");

        String msg = "";
        String url ="";

        try {

            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            String user_pw = EncryptUtil.encHashSHA256(CmmUtil.nvl(request.getParameter("user_pw")));
            String user_nm = CmmUtil.nvl(request.getParameter("user_nm"));
            String user_email = EncryptUtil.encAES128CBC(CmmUtil.nvl(request.getParameter("user_email"))); 

            log.info("user_id : "+ user_id);
            log.info("user_pw : " + user_pw);
            log.info("user_nm : " + user_nm);
            log.info("user_email : " + user_email);

            UserInfoDTO pDTO = new UserInfoDTO();
            pDTO.setUser_id(user_id);
            pDTO.setUser_pw(user_pw);
            pDTO.setUser_nm(user_nm);
            pDTO.setUser_email(user_email);

            userRegService.userReg(pDTO);

            msg = "회원가입 성공";
            url = "/userLoginForm";

        } catch (Exception e) {
            // 저장 실패 시
            msg = "회원가입 실패";
            url = "/userRegForm";
            log.info(e.toString());
            e.printStackTrace();
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName()+".userReg End!!");

        return "/redirect";
    }

    @RequestMapping(value = "/user/getIdExist")
    @ResponseBody
    public Map<String, String> getIdExist(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".getIdExist Start!!");

        Map<String, String> rMap = new HashMap<>();

        try {

            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            log.info("user_id : " + user_id);

            rMap = userRegService.getIdExist(user_id);
            log.info("입력받은 아이디의 존재 여부 : "+rMap.get("Exist_yn"));

        } catch (Exception e) {
            rMap.put("Exist_yn", "exception");
            log.info(e.toString());
            e.printStackTrace();
        }

        log.info(this.getClass().getName() + ".getIdExist End!!");

        return rMap;
    }

    @RequestMapping(value = "/user/getEmailExist")
    @ResponseBody
    public Map<String, String> getEmailExist(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".getEmailExist Start!!");

        Map<String, String> rMap = new HashMap<>();

        try {

            String input_email_encrypted = EncryptUtil.encAES128CBC(CmmUtil.nvl(request.getParameter("input_email")));
            log.info("input_email : " + input_email_encrypted);

            rMap = userRegService.getEmailExist(input_email_encrypted);
            log.info("입력받은 이메일의 존재 여부 : "+rMap.get("Exist_yn"));

        } catch (Exception e) {
            rMap.put("Exist_yn", "exception");
            log.info(e.toString());
            e.printStackTrace();
        }

        log.info(this.getClass().getName() + ".getEmailExist End!!");

        return rMap;
    }

    @RequestMapping(value = "/user/userDel")
    public String userDel(HttpServletRequest request, ModelMap model, HttpSession session) {

        log.info(this.getClass().getName()+".userDel Start!");

        String msg="";
        String url="";


        try {

            String user_id = CmmUtil.nvl((String)session.getAttribute("SS_USER_ID"));
            String input_pw = EncryptUtil.encHashSHA256(CmmUtil.nvl((String)request.getParameter("input_pw")));

            log.info("탈퇴할 사용자 아이디 : "+user_id);
            log.info("입력받은 사용자 비밀번호 : "+input_pw);

            UserInfoDTO pDTO = new UserInfoDTO();
            pDTO.setUser_id(user_id);
            pDTO.setUser_pw(input_pw);

            int res = userRegService.userDel(pDTO);

            if( res == 1 ){
                log.info("삭제로직 실행 성공");
                msg = "성공적으로 탈퇴되었습니다. 이용해주셔서 감사합니다.";
                url="/";
            } else {
                log.info("삭제로직 실행 실패 : 잘못된 비밀번호");
                msg = "올바른 비밀번호를 입력해주세요.";
                url="/";
            }

        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
            msg="시스템 오류입니다. 잠시후 다시 시도해주세요.";
            url="/";
        }

        model.addAttribute("msg",msg);
        model.addAttribute("url",url);

        log.info(this.getClass().getName()+".userDel End!");

        return "/redirect";
    }
}
