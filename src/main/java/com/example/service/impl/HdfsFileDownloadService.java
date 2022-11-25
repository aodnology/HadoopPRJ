package com.example.service.impl;

import com.example.dto.HadoopDTO;
import com.example.service.AbstractHadoopConf;
import com.example.service.IHdfsFileDownloadService;
import com.example.util.CmmUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class HdfsFileDownloadService extends AbstractHadoopConf
        implements IHdfsFileDownloadService {

    @Override
    public void downloadHdfsFile(HadoopDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".downloadFile start!");

        FileSystem hdfs = FileSystem.get(this.getHadoopConfiguration());

        String hadoopUploadFilePath = CmmUtil.nvl(pDTO.getHadoopUploadPath());

        String hadoopUploadFileName = CmmUtil.nvl(pDTO.getHadoopUploadFileName());

        String hadoopFile = hadoopUploadFilePath + "/" + hadoopUploadFileName;

        Path path = new Path(hadoopFile);

        log.info("HDFS Exists : " + hdfs.exists(path));

        if (hdfs.exists(path)) {
            Path localPath = new Path(
                    CmmUtil.nvl(pDTO.getLocalUploadPath())+
                            "/" + CmmUtil.nvl(pDTO.getLocalUploadFileName())
            );
            hdfs.close();

            log.info(this.getClass().getName() + ".downloadFile End!");
        }
    }
}
