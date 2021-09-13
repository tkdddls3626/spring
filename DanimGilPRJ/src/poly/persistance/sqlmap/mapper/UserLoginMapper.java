package kopo.poly.persistance.mongodb.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IUserLoginMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component("UserLoginMapper")
public class UserLoginMapper extends AbstractMongoDBComon implements IUserLoginMapper {

    final private String colNm = "user_info";

    @Override
    public UserInfoDTO userLogin(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".userLogin Start!!");

        if(pDTO == null){
            pDTO = new UserInfoDTO();
            log.info("널값이 넘어와 메모리에 강제로 올림");
        }

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Studio 3T
        Document query = new Document();
        query.append("user_id", pDTO.getUser_id());
        query.append("user_pw", pDTO.getUser_pw());

        FindIterable<Document> rs = collection.find(query);

        UserInfoDTO rDTO = new UserInfoDTO();

        for (Document doc : rs) {

            if (doc == null) {
                doc = new Document();
            }

            log.info("쿼리의 리턴 도큐먼트가 존재함");
            String user_id = doc.getString("user_id");
            String user_nm = doc.getString("user_nm");
            String user_email = doc.getString("user_email");

            rDTO.setUser_id(user_id);
            rDTO.setUser_nm(user_nm);
            rDTO.setUser_email(user_email);

        }

        log.info(this.getClass().getName() + ".userLogin End!!");

        return rDTO;
    }
}
