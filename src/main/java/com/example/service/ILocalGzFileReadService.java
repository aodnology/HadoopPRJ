package com.example.service;

import com.example.dto.HadoopDTO;

import java.util.List;

public interface ILocalGzFileReadService {

    List<String> readLocalGzFileCnt(HadoopDTO pDTO) throws Exception;
    List<String> readLoocalGzFileIP(HadoopDTO pDTO) throws Exception;
}
