package com.example.component.impl;

import com.example.component.IHdfsExam;
import com.example.dto.HadoopDTO;
import com.example.service.impl.HdfsFileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

@Log4j
@RequiredArgsConstructor
@Component
public class Exam01 implements IHdfsExam {

    private final HdfsFileUploadService hdfsFileUploadService;

    @Override
    public void doExam() throws Exception {

        HadoopDTO pDTO = new HadoopDTO();

        // 하둡에 저장될 파일 정보

        pDTO.setHadoopUploadPath("/01/02");
        pDTO.setHadoopUploadFileName("access_log.gz");

        pDTO.setLocalUploadPath("c:/hadoop_data");
        pDTO.setLocalUploadFileName("access_log.gz");

        hdfsFileUploadService.uploadHdfsFile(pDTO);
    }
}
