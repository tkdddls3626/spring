package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserLoginService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserLoginController {

    @Resource(name="UserLoginService")
    private IUserLoginService userLoginService;

    @RequestMapping(value="/userLogin")
    public String userLogin(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        log.info(this.getClass().getName()+".userLogin Start!!");

        String msg;
        String url;

        try {
        String input_id = CmmUtil.nvl((String)request.getParameter("input_id"));
        String input_pw = EncryptUtil.encHashSHA256(CmmUtil.nvl((String)request.getParameter("input_pw")));

        log.info("input_id : "+input_id);
        log.info("input_pw : "+input_pw);

        UserInfoDTO pDTO = new UserInfoDTO();

        pDTO.setUser_id(input_id);
        pDTO.setUser_pw(input_pw);

        UserInfoDTO rDTO = userLoginService.userLogin(pDTO);

        if(rDTO.getUser_id()==null){

            log.info("로그인 실패");

            msg="아이디 혹은 비밀번호가 올바르지 않습니다.";
            url="/userLoginForm";

        } else {
            log.info("로그인 성공");

            String user_id = CmmUtil.nvl(rDTO.getUser_id());
            String user_nm = CmmUtil.nvl(rDTO.getUser_nm());
            String encrypted_user_email = CmmUtil.nvl(rDTO.getUser_email());
            String eye_blink = CmmUtil.nvl(rDTO.getEye_blink());
            String eye_distance = CmmUtil.nvl(rDTO.getEye_distance());

            log.info("user_id : "+user_id);
            log.info("user_nm : "+user_nm);
            log.info("encrypted_user_email : "+encrypted_user_email);
            log.info("eye_blink : "+eye_blink);
            log.info("eye_distance : "+eye_distance);

            msg=user_nm+"님, 반갑습니다.";
            url="/";

            session.setAttribute("SS_USER_ID",user_id);
            session.setAttribute("SS_USER_NM", user_nm);
            session.setAttribute("SS_ENCRYPTED_USER_EMAIL", encrypted_user_email);

        } } catch (Exception e) {
            msg="시스템 오류입니다. 잠시 후 다시 시도해주세요.";
            url="/userLoginForm";
            log.info(e.toString());
            e.printStackTrace();
        }

        model.addAttribute("msg",msg);
        model.addAttribute("url",url);

        log.info(this.getClass().getName()+".userLogin End!!");

        return "/redirect";
    }
    @RequestMapping(value = "/userLogout")
    public String userLogout(HttpSession session, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".userLogout Start!!");

        // session 비움
        session.invalidate();

        model.addAttribute("msg", "로그아웃 성공");
        model.addAttribute("url", "/");

        log.info(this.getClass().getName() + ".userLogout End!!");

        return "/redirect";
    }
}
