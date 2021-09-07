package kopo.poly.persistance.mongodb;

import kopo.poly.dto.RecordDTO;

import java.util.List;

//기록 정보 처리하는 매퍼
public interface IRecordMapper {

    //저장. 달마다 다른 컬렉션에 저장, 3개월 이전의 컬렉션은 자동 삭제함
    int insertRecord(RecordDTO pDTO) throws Exception;

    //조회
    List<RecordDTO> getRecordList(RecordDTO pDTO, String colNm) throws Exception;

    //상세조회
    RecordDTO getDailyRecord(RecordDTO pDTO, String colNm) throws Exception;

}
