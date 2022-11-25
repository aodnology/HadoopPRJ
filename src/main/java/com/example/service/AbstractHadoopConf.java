package com.example.service;

import org.apache.hadoop.conf.Configuration;

/**
 * 하둡 접속을 위한 공통 설정을 추상한 객체로 정의
 * */

public abstract class AbstractHadoopConf {

    // 네임노드가 설치된 마스터 서버 IP
    String namenodeHost = "192.168.2.136";

    // 네임노드 포트
    String namenodePort = "9000";

    // 얀 포트
    String yarnPort = "8080";

    public Configuration getHadoopConfiguration() {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "hdfs://" + namenodeHost + ":" + namenodePort);
        conf.set("yarn.resourcemanager.address", namenodeHost + ":" + yarnPort);

        return conf;
    }
}
