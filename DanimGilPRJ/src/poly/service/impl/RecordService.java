package kopo.poly.service.impl;

import kopo.poly.dto.RecordDTO;
import kopo.poly.persistance.mongodb.IRecordMapper;
import kopo.poly.service.IRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("RecordService")
public class RecordService implements IRecordService {

    @Resource(name="RecordMapper")
    private IRecordMapper recordMapper;

    @Override
    public int insertRecord(RecordDTO pDTO) throws Exception {
        return recordMapper.insertRecord(pDTO);
    }

    @Override
    public List<RecordDTO> getRecordList(RecordDTO pDTO, String colNm) throws Exception {
        return recordMapper.getRecordList(pDTO,colNm);
    }

    @Override
    public RecordDTO getDailyRecord(RecordDTO pDTO, String colNm) throws Exception {
        return recordMapper.getDailyRecord(pDTO,colNm);
    }
}
