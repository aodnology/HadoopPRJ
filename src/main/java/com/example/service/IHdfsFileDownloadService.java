package com.example.service;

import com.example.dto.HadoopDTO;

public interface IHdfsFileDownloadService {

    void downloadHdfsFile(HadoopDTO pDTO) throws Exception;
}
