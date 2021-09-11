package kopo.poly.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import kopo.poly.dto.MonitorDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IMonitorMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("MonitorMapper")
public class MonitorMapper extends AbstractMongoDBComon implements IMonitorMapper {

    final private String colNm = "monitor_info";

    @Override
    public int insertMonitorInfo(MonitorDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertMonitorInfo Start!!");

        int res = 0;

        if (pDTO == null) {
            log.info("널값이 넘어와 메모리에 강제로 올림");
            pDTO = new MonitorDTO();
        }
        super.createCollection(colNm);

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        collection.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info(this.getClass().getName() + ".insertMonitorInfo End!!");
        return res;
    }

    @Override
    public int updateMonitorInfo(MonitorDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".updateMonitorInfo Start!!");

        int res = 0;

        if (pDTO == null) {
            log.info("널값이 넘어와 메모리에 강제로 올림");
            pDTO = new MonitorDTO();
        }

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("user_id", pDTO.getUser_id());

        Document updateQuery = new Document();
        updateQuery.append("distance_check", pDTO.getDistance_check());
        updateQuery.append("blink_check", pDTO.getBlink_check());
        updateQuery.append("sleepCheckTime", pDTO.getSleepCheckTime());
        updateQuery.append("nearCheckTime", pDTO.getNearCheckTime());
        updateQuery.append("restTerm", pDTO.getRestTerm());
        updateQuery.append("restTime", pDTO.getRestTime());
        updateQuery.append("mSleep", pDTO.getMSleep());
        updateQuery.append("mNear", pDTO.getMNear());
        updateQuery.append("mRestStart", pDTO.getMRestStart());
        updateQuery.append("mRestEnd", pDTO.getMRestEnd());

        UpdateResult updateResults = collection.updateOne(query, new Document("$set", updateQuery));

        res = (int) updateResults.getMatchedCount();
        log.info("res : " + res);

        log.info(this.getClass().getName() + ".updateMonitorInfo End!!");
        return res;
    }

    @Override
    public MonitorDTO getMonitorInfo(MonitorDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getMonitorInfo Start!!");

        if (pDTO == null) {
            log.info("널값이 넘어와 메모리에 강제로 올림");
            pDTO = new MonitorDTO();
        }

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("user_id", pDTO.getUser_id());

        FindIterable<Document> rs = collection.find(query);

        MonitorDTO rDTO = new MonitorDTO();

        for(Document doc : rs){

            if (doc == null) {
                doc = new Document();
            }

            String distance_check = doc.getString("distance_check");
            String blink_check = doc.getString("blink_check");
            String sleepCheckTime = doc.getString("sleepCheckTime");
            String nearCheckTime = doc.getString("nearCheckTime");
            String restTerm = doc.getString("restTerm");
            String restTime = doc.getString("restTime");
            String mSleep = doc.getString("mSleep");
            String mNear = doc.getString("mNear");
            String mRestStart = doc.getString("mRestStart");
            String mRestEnd = doc.getString("mRestEnd");

            rDTO.setDistance_check(distance_check);
            rDTO.setBlink_check(blink_check);
            rDTO.setSleepCheckTime(sleepCheckTime);
            rDTO.setNearCheckTime(nearCheckTime);
            rDTO.setRestTerm(restTerm);
            rDTO.setRestTime(restTime);
            rDTO.setMSleep(mSleep);
            rDTO.setMNear(mNear);
            rDTO.setMRestStart(mRestStart);
            rDTO.setMRestEnd(mRestEnd);

        }

        log.info(this.getClass().getName() + ".getMonitorInfo End!!");

        return rDTO;
    }
}
