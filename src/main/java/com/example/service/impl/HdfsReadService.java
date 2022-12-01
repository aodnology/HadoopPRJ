package com.example.service.impl;

import com.example.dto.HadoopDTO;
import com.example.service.AbstractHadoopConf;
import com.example.service.IHdfsFileReadService;
import com.example.util.CmmUtil;
import lombok.extern.log4j.Log4j;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class HdfsReadService extends AbstractHadoopConf
        implements IHdfsFileReadService {

    @Override
    public String readHdfsFile(HadoopDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".readHdfsFile Start");

        // 하둡 분산 파일 시스템 객체
        // 예 : hadoop fs -put access_log.gz /01/02/access_log.gz
        FileSystem hdfs = FileSystem.get(this.getHadoopConfiguration());

        // 하둡 분산파일 시스템에 저장될 파일 경로 및 폴더명
        String hadoopFile = CmmUtil.nvl(pDTO.getHadoopUploadPath()) + "/" + CmmUtil.nvl((pDTO.getHadoopUploadPath()));

        // 하둡분산파일시스템에 저장가능한 객체로 변환
        Path path = new Path(hadoopFile);

        // 기존 하둡에 존재하는지 로그 찍어보기
        log.info("HDFS Exists : " + hdfs.exists(path));

        String readLog = "";

        if (hdfs.exists(path)) { // 하둡 분산파일 시스템에 파일이 존재하면
            //하둡분산파일시스템의 파일 읽기
            // FSDataOutputStream 객체는 ByteBufferReadable를 상속하여 구현함
            FSDataInputStream inputStream = hdfs.open(path);
            readLog = inputStream.readUTF();
            inputStream.close();
        }

        log.info(this.getClass().getName() + ".readHdfsFile End");

        return  readLog;
    }
}