package kopo.poly.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
//기록정보 객체
public class RecordDTO {

    private String user_id; // 기록정보를 사용할 사용자의 아이디
    private String record_date; //yyyy-mm-dd
    private String start_time; //웹캠시작시간
    private String end_time; //웹캠종료시간
    private String streaming_time; //웹캠작동시간
    private String face_time; //얼굴인식시간
    private String near_time; //근거리탐지시간
    private String blink_time; //졸음탐지시간
    private String distract_time; //집중못한시간(근거리+졸음)
    private String attention_time; //사용자집중시간
    private String daily_comment; //일일 사용자 코멘트
    private String record_title; //기록 제목
}