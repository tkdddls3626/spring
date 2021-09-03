package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class PageController {

    @GetMapping(name = "/")
    public String index() throws Exception {
        log.info(this.getClass().getName()+".index Start!");
        return "/index";
    }
    @RequestMapping(value="/user/userRegForm")
    public String userRegForm() throws Exception {
        log.info(this.getClass().getName()+".userRegForm Start!");
        return "/user/userRegForm";
    }

    @RequestMapping(value="/user/userLoginForm")
    public String userLoginForm() throws Exception {
        log.info(this.getClass().getName()+".userLoginForm Start!");
        return "/user/userLoginForm";
    }

    @RequestMapping(value="/user/userDelForm")
    public String userDelForm() throws Exception {
        log.info(this.getClass().getName()+".userDelForm Start!");
        return "/user/userDelForm";
    }



    //아이디 찾기를 위해 이메일을 입력받는페이지 -> 여기서 아이디 인증번호를 발송하게 됌
    @RequestMapping(value="/user/inputforfindId")
    public String inputforfindIdPage() throws Exception {
        log.info(this.getClass().getName()+".inputforfindIdPage Start!");
        return "/user/inputforfindId";
    }
    //입력받은 인증번호가 발송된 것과 일치하면 아이디를 alert로 띄워주는 페이지 -> a태그로 로그인페이지로 가게 해야함
    @RequestMapping(value="/user/findId")
    public String findIdPage() throws Exception {
        log.info(this.getClass().getName()+".findIdPage Start!");
        return "/user/findId";
    }


    //비밀번호를 찾기 위해 아이디를 입력받는 페이지
    @RequestMapping(value="/user/inputforfindPw")
    public String inputforfindPwPage() throws Exception {
        log.info(this.getClass().getName()+".inputforfindPwPage Start!");
        return "/user/inputforfindPw";
    }
    //인증번호, 비밀번호, 비밀번호 확인 입력받는 페이지 (수정을 위해 히든으로 아이디값이 있어야함)
    @RequestMapping(value="/user/findPW")
    public String findPWPage() throws Exception {
        log.info(this.getClass().getName()+".findPWPage Start!");
        return "/user/findPW";
    }

    //인증번호, 비밀번호, 비밀번호 확인 입력받는 페이지 (수정을 위해 히든으로 아이디값이 있어야함)
    @RequestMapping(value="/user/updatePW")
    public String updatePW() throws Exception {
        log.info(this.getClass().getName()+".findPWPage Start!");
        return "/user/updatePW";
    }


    //로그인한 사용자가 비밀번호를 수정하기 위해 들어가는 페이지
    @RequestMapping(value="/userChangePW")
    public String userChangePWPage() throws Exception {
        log.info(this.getClass().getName()+".userChangePWPage Start!");
        return "/user/updatePwForm";
    }



    //모니터링 결과페이지. 결과값 보여주고 사용자의 코멘트를 받는 페이지 -> insertRecord를 실행시키는 페이지
    @RequestMapping(value = "/monitor/monitorResult")
    public String monitorResult(HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + ".monitorResult Start!!");
        return "/monitor/monitorResult";
    }

    //기록리스트 띄우는 페이지 컨트롤러는 RecordController에 있음

    //일일기록 띄우는 페이지 컨트롤러는 RecordController에 있음

    //인증번호, 비밀번호, 비밀번호 확인 입력받는 페이지 (수정을 위해 히든으로 아이디값이 있어야함)
    @RequestMapping(value="/user/mypage")
    public String mypage() throws Exception {
        log.info(this.getClass().getName()+".mypage Start!");
        return "/user/mypage";
    }
    @RequestMapping(value="/study/study")
    public String study() throws Exception {
        log.info(this.getClass().getName()+".study Start!");
        return "/study/study";
    }
    @RequestMapping(value="/study/studyRecode")
    public String studyRecode() throws Exception {
        log.info(this.getClass().getName()+".studyRecode Start!");
        return "/study/studyRecode";
    }

}
