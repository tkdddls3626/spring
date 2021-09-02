package kopo.poly.controller;

import kopo.poly.dto.RecordDTO;
import kopo.poly.service.IRecordService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class RecordController {

    @Resource(name="RecordService")
    private IRecordService recordService;

    @RequestMapping(value = "/insertRecord")
    public String insertRecord(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".insertRecord Start!!");

        String url ="";
        String msg= "";

        String user_id = (String)session.getAttribute("SS_USER_ID");
        String record_date = DateUtil.getDateTime("yyyy.MM.dd");
        String start_time = (String)request.getParameter("start_time");
        String end_time = (String)request.getParameter("end_time");
        String streaming_time = (String)request.getParameter("streaming_time");
        String face_time = (String)request.getParameter("face_time");
        String near_time = (String)request.getParameter("near_time");
        String blink_time = (String)request.getParameter("blink_time");
        String distract_time = (String)request.getParameter("distract_time");
        String attention_time = (String)request.getParameter("attention_time");
        String daily_comment = (String)request.getParameter("daily_comment");

        log.info("user_id : "+user_id);
        log.info("streaming_time : "+streaming_time);
        log.info("attention_time : "+attention_time);
        log.info("daily_comment : "+daily_comment);

        RecordDTO pDTO = new RecordDTO();
        pDTO.setUser_id(user_id);
        pDTO.setRecord_date(record_date);
        pDTO.setStart_time(start_time);
        pDTO.setEnd_time(end_time);
        pDTO.setStreaming_time(streaming_time);
        pDTO.setNear_time(near_time);
        pDTO.setBlink_time(blink_time);
        pDTO.setFace_time(face_time);
        pDTO.setDistract_time(distract_time);
        pDTO.setAttention_time(attention_time);
        pDTO.setDaily_comment(daily_comment);

        int res = recordService.insertRecord(pDTO);

        if(res ==1 ) {
            log.info("기록값 저장 성공");
            msg="성공적으로 저장되었습니다. 수고하셨습니다.";
            url="/record/recordList";

        } else  {
            log.info("기록값 저장 실패");
             msg="저장에 실패하였습니다. let_hykim@naver.com으로 문의주세요";
             url="/index";
        }

        model.addAttribute("url",url);
        model.addAttribute("msg",msg);

        log.info(this.getClass().getName() + ".insertRecord End!!");

        return"/redirect";
    }

    @RequestMapping(value = "/getRecordList")
    public String getRecordList(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".getRecordList Start!!");

        // 이용자아이디
        String user_id = (String)session.getAttribute("SS_USER_ID");
        RecordDTO pDTO = new RecordDTO();
        pDTO.setUser_id(user_id);

        // 조회를 위해 사용자가 원하는 달의 컬렉션 명 생성
        String monthNum = CmmUtil.nvl((String)request.getParameter("monthNum"));

        if(monthNum.length()==0){
            monthNum ="0";
        }

        log.info("monthNum : "+monthNum);

        String colNm = DateUtil.getMonthPastTime(DateUtil.getDateTime("yyyyMM"),Integer.parseInt(monthNum));
        log.info("조회할 컬렉션명 : "+colNm);

        List<RecordDTO> rList = recordService.getRecordList(pDTO,colNm);

        if(rList==null) {
            log.info("널값이 넘어와 메모리에 강제로 올림");
        }

        model.addAttribute("rList", rList);
        model.addAttribute("monthNum", monthNum);
        log.info(this.getClass().getName() + ".getRecordList End!!");

        return "/record/recordList";
    }

    @RequestMapping(value = "/getDailyRecord")
    public String getDailyRecord(HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + ".getDailyRecord Start!!");







        log.info(this.getClass().getName() + ".getDailyRecord End!!");

        return "/record/recordDaily";
    }

}
