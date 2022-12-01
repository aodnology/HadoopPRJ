package com.example.service;

import com.example.dto.HadoopDTO;

public interface IHdfsFileUploadService {
    /**
     * HDFS에 파일 업로드
     * 예 : hadoop fs -put access_log /access_log
     * */
    void uploadHdfsFile(HadoopDTO pDTO) throws Exception;
    /**
     * HDFS에 파일 업로드
     * 예 : zcat access_log.gz | head -n 10 | hadoop fs -put - /access_log.gz
     * */
    void uploadHdfsFileContents(HadoopDTO pDTO) throws Exception;
}
