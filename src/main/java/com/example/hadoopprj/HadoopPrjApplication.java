package com.example.hadoopprj;

import com.example.component.impl.Exam01;
import com.example.component.impl.Exam02;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j
@RequiredArgsConstructor
@SpringBootApplication
public class HadoopPrjApplication implements CommandLineRunner {

    private final Exam01 exam01;
    private final Exam02 exam02;

    @Override
    public void run(String... args) throws Exception {

        log.info("하둡 프로그래밍 실습");

        log.info("첫번재 실습");
        exam01.doExam();

        log.info("두번재 실습");
        exam02.doExam();

        log.info("하둡 프로그래밍 실습 끝");
    }

    public static void main(String[] args) {
        SpringApplication.run(HadoopPrjApplication.class, args);
    }
}
