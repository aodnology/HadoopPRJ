package com.example.service.impl;

import com.example.dto.HadoopDTO;
import com.example.service.ILocalGzFileReadService;
import com.example.util.CmmUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

@Log4j
@Service
public class LocalGzFileReadService implements ILocalGzFileReadService {
    @Override
    public List<String> readLocalGzFileCnt(HadoopDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".readLocalGzFile Start");

        long readCnt = pDTO.getLineCnt();
        log.info("readCnt : " + readCnt);

        List<String> logList = new ArrayList<>();

        String localFile = CmmUtil.nvl(pDTO.getLocalUploadPath()) +
                "/" + CmmUtil.nvl(pDTO.getLocalUploadFileName());

        log.info("localFile : " + localFile);

        // Gz 파일 가져오기
        File localGzFile = new File(localFile);

        // 파일 처리를 위해 파일을 스트림 형태로 변환
        FileInputStream fis = new FileInputStream(localGzFile);

        // Gz 파일로 압축된 파일스트림을 압축해제한 스트림으로 변경
        GZIPInputStream gzis = new GZIPInputStream(fis);

        // Gz 스트림 내용을 읽기
        InputStreamReader streamReader = new InputStreamReader(gzis);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            long idx = 0;

            String line;

            while ((line = lineReader.readLine()) != null) {

                log.info("line : "+ line);
                logList.add(line);

                idx++;

                if (readCnt == idx) {
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Gzip 파일 읽기 실패", e);
        }
        gzis.close();
        gzis = null;

        fis.close();
        fis = null;

        streamReader.close();
        streamReader = null;

        log.info(this.getClass(). getName() + ".readLocalGzFile End");
        return logList;
    }

    @Override
    public List<String> readLoocalGzFileIP(HadoopDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".readLocalGzFile Start");

        String localFile = CmmUtil.nvl(pDTO.getLocalUploadPath()) +
                "/" + CmmUtil.nvl(pDTO.getLocalUploadFileName());

        log.info("localFIle : " + localFile);

        List<String> logList = new ArrayList<>();

        File localGzFile = new File(localFile);

        FileInputStream fis = new FileInputStream(localGzFile);

        GZIPInputStream gzis = new GZIPInputStream(fis);

        InputStreamReader streamReader = new InputStreamReader(gzis);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {

            String line;

            String exp = CmmUtil.nvl(pDTO.getRegExp());
            log.info("exp : " + exp);

            while ((line = lineReader.readLine()) != null) {
                String ip = line.substring(0, line.indexOf(" "));

                if (Pattern.matches(exp, ip)) {
                    logList.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Gzip 파일 읽기 실패", e);
        }
        gzis.close();
        gzis = null;

        fis.close();
        fis = null;

        streamReader.close();
        streamReader = null;

        log.info(this.getClass(). getName() + ".readLocalGzFile End");
        return logList;
    }
}
