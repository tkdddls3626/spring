package kopo.poly.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.RecordDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IRecordMapper;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("RecordMapper")
public class RecordMapper extends AbstractMongoDBComon implements IRecordMapper {


    @Override
    public int insertRecord(RecordDTO pDTO) throws Exception {
        log.info(this.getClass().getName()+".insertRecord Start!!");

        String currentDate = DateUtil.getDateTime("yyyyMM");
        log.info("생성할 컬렉션의 날짜정보 : "+currentDate);

        String colNm = "RECORD_"+ currentDate;

        int res = 0;

        super.createCollection(colNm);

        //3개월 전의 컬렉션을 찾아 삭제
        String past3colNm = DateUtil.getMonthPastTime(currentDate,3);
        super.dropCollection(past3colNm);

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        if(pDTO == null) {
            log.info("null값이 넘어와 메모리에 강제로 올림");
            pDTO = new RecordDTO();
        }

        collection.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info(this.getClass().getName()+".insertRecord End!!");
        return res;
    }

    //이용자 id와 조회희망 날짜를 컬렉션이름으로 받아 원하는 기간의 자신의 레코드 조회(colNm은 yyyyMM형식)
    @Override
    public List<RecordDTO> getRecordList(RecordDTO pDTO, String colNm) throws Exception {
        log.info(this.getClass().getName()+".getRecordList Start!!");

        List<RecordDTO> rList = new ArrayList<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        if(pDTO == null) {
            log.info("null값이 넘어와 메모리에 강제로 올림");
            pDTO = new RecordDTO();
        }

        Document query = new Document();
        query.append("user_id",pDTO.getUser_id());

        Document projection = new Document();
        projection.append("_id",0);

        FindIterable<Document> rs = collection.find(query).projection(projection);

        for(Document doc : rs) {
            if(doc==null){
                doc = new Document();
                log.info("null값이 넘어와 메모리에 강제로 올림");
            }

            String record_date = CmmUtil.nvl(doc.getString("record_date"));
            log.info("record_date : "+record_date);
            String start_time = CmmUtil.nvl(doc.getString("start_time"));
            String end_time = CmmUtil.nvl(doc.getString("end_time"));
            String streaming_time = CmmUtil.nvl(doc.getString("streaming_time"));
            String near_time = CmmUtil.nvl(doc.getString("near_time"));
            String blink_time = CmmUtil.nvl(doc.getString("blink_time"));
            String face_time = CmmUtil.nvl(doc.getString("face_time"));
            String distract_time = CmmUtil.nvl(doc.getString("distract_time"));
            String attention_time = CmmUtil.nvl(doc.getString("attention_time"));
            String daily_comment = CmmUtil.nvl(doc.getString("daily_comment"));
            String record_title = CmmUtil.nvl(doc.getString("record_title"));

            RecordDTO rDTO = new RecordDTO();
            rDTO.setRecord_date(record_date);
            rDTO.setStart_time(start_time);
            rDTO.setEnd_time(end_time);
            rDTO.setStreaming_time(streaming_time);
            rDTO.setNear_time(near_time);
            rDTO.setBlink_time(blink_time);
            rDTO.setFace_time(face_time);
            rDTO.setDistract_time(distract_time);
            rDTO.setAttention_time(attention_time);
            rDTO.setDaily_comment(daily_comment);
            rDTO.setRecord_title(record_title);

            rList.add(rDTO);

        }

        log.info(this.getClass().getName()+".getRecordList End!!");

        return rList;
    }

    @Override
    public RecordDTO getDailyRecord(RecordDTO pDTO, String colNm) throws Exception {
        log.info(this.getClass().getName()+".getDailyRecord Start!!");

        if(pDTO == null) {
            log.info("null값이 넘어와 메모리에 강제로 올림");
            pDTO = new RecordDTO();
        }

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("user_id",pDTO.getUser_id());
        query.append("record_date", pDTO.getRecord_date());

        Document projection = new Document();
        projection.append("_id",0);

        FindIterable<Document> rs = collection.find(query).projection(projection);

        RecordDTO rDTO = new RecordDTO();

        for(Document doc : rs) {
            if(doc==null){
                doc = new Document();
                log.info("null값이 넘어와 메모리에 강제로 올림");
            }

            String record_date = CmmUtil.nvl(doc.getString("record_date"));
            log.info("record_date : "+record_date);
            String start_time = CmmUtil.nvl(doc.getString("start_time"));
            String end_time = CmmUtil.nvl(doc.getString("end_time"));
            String streaming_time = CmmUtil.nvl(doc.getString("streaming_time"));
            String near_time = CmmUtil.nvl(doc.getString("near_time"));
            String blink_time = CmmUtil.nvl(doc.getString("blink_time"));
            String face_time = CmmUtil.nvl(doc.getString("face_time"));
            String distract_time = CmmUtil.nvl(doc.getString("distract_time"));
            String attention_time = CmmUtil.nvl(doc.getString("attention_time"));
            String daily_comment = CmmUtil.nvl(doc.getString("daily_comment"));
            String record_title = CmmUtil.nvl(doc.getString("record_title"));

            rDTO.setRecord_date(record_date);
            rDTO.setStart_time(start_time);
            rDTO.setEnd_time(end_time);
            rDTO.setStreaming_time(streaming_time);
            rDTO.setNear_time(near_time);
            rDTO.setBlink_time(blink_time);
            rDTO.setFace_time(face_time);
            rDTO.setDistract_time(distract_time);
            rDTO.setAttention_time(attention_time);
            rDTO.setDaily_comment(daily_comment);
            rDTO.setRecord_title(record_title);
        }

        log.info(this.getClass().getName()+".getDailyRecord End!!");
        return rDTO;
    }
}
