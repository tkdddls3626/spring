package kopo.poly.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IUserRegMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Component("UserRegMapper")
public class UserRegMapper extends AbstractMongoDBComon implements IUserRegMapper {

    final private String colNm = "user_info";

    @Override
    public int userReg(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".userReg Start!");

        int res = 0;

        super.createCollection(colNm);

        // 저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        if(pDTO == null) {
            log.info("null값이 넘어와 메모리에 강제로 올림");
            pDTO = new UserInfoDTO();
        }

        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        collection.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info(this.getClass().getName() + ".userReg End!");

        return res;
    }

    @Override
    public Map<String, String> getIdExist(String pUser_id) throws Exception {
        log.info(this.getClass().getName() + ".getIdExist Start!");

        if(pUser_id == null){
            pUser_id="";
            log.info("null값이 넘어와 강제로 메모리에 올림");
        }

        Map<String, String> rMap = new HashMap<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Studio3T
        Document query = new Document();

        query.append("user_id", pUser_id);

        Consumer<Document> processBlock = document -> rMap.put("Exist_yn", "y");

        collection.find(query).forEach(processBlock);

        log.info(this.getClass().getName() + ".getIdExist End!");

        return rMap;
    }

    @Override
    public Map<String, String> getEmailExist(String pUser_email) throws Exception {
        log.info(this.getClass().getName() + ".getEmailExist Start!");

        if(pUser_email == null){
            pUser_email="";
            log.info("null값이 넘어와 강제로 메모리에 올림");
        }

        Map<String, String> rMap = new HashMap<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Studio3T
        Document query = new Document();

        query.append("user_email", pUser_email);

        Consumer<Document> processBlock = document -> rMap.put("Exist_yn", "y");

        collection.find(query).forEach(processBlock);

        log.info(this.getClass().getName() + ".getEmailExist End!");

        return rMap;
    }

    @Override
    public int userDel(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".userDel Start!");

        if(pDTO == null) {
            pDTO = new UserInfoDTO();
            log.info("널값이 넘어와 강제로 메모리에 올림");
        }

        int res =0;

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("user_id", pDTO.getUser_id());
        query.append("user_pw", pDTO.getUser_pw());

        FindIterable<Document> rs = col.find(query);


        for(Document doc : rs) {
            col.deleteOne(doc);
            res = 1;
        }

        log.info(this.getClass().getName() + ".userDel End!");

        return res;
    }

}
