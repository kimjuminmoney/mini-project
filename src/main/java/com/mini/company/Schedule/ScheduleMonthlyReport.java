package com.mini.company.Schedule;

import com.mini.company.dto.overtime.response.OvertimeResponse;
import com.mini.company.service.overtime.OvertimeService;
import com.opencsv.CSVWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleMonthlyReport {

    private final OvertimeService overtimeService;
    private final JavaMailSender javaMailSender;

    public ScheduleMonthlyReport(OvertimeService overtimeService, JavaMailSender javaMailSender) {
        this.overtimeService = overtimeService;
        this.javaMailSender = javaMailSender;
    }

    /**
     *  매월 1일 오전 9시에 스케쥴 시작
     */
    @Scheduled(cron = " * 0 9 1 * * ")
    //@Scheduled(fixedDelay = 30000)
    public void monthlyReport() throws IOException {
        List<String[]> stringsList = staringListProcess();
        String fileName = "Monthly-Report.csv";
        String filePath = "C:/Users/dkrnl/Desktop/AllInOne/csv/";
        CSVWriter csvWriter = null;
        try{
            csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(filePath + fileName),"EUC-KR"));
            csvWriter.writeAll(stringsList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            assert csvWriter != null;
            csvWriter.close();
        }

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("dkrnlqkqn586@naver.com");
            helper.setSubject("월간 보고서");
            helper.setText("월간 보고서를 참고해주세요");
            helper.addAttachment(fileName,new File(filePath +fileName));
            javaMailSender.send(message);
        } catch (MessagingException e){
            e.printStackTrace();
        }

        
    }

    private List<String[]> staringListProcess() {
        List<String[]> stringsList = new ArrayList<>();
        List<OvertimeResponse> overtimeResponseList = overtimeService.getOvertimes(YearMonth.now());
        stringsList.add(new String[]{YearMonth.now().getYear() + " 년도",YearMonth.now().getMonthValue() + " 월" });
        stringsList.add(new String[]{"member_id","이름","초과 근무 시간(분)"});
        for(OvertimeResponse overtimeResponse : overtimeResponseList){
            String[] rowData = new String[3];
            rowData[0] = overtimeResponse.getMemberId().toString();
            rowData[1] = overtimeResponse.getName();
            rowData[2] = overtimeResponse.getOvertimeMinutes().toString();
            stringsList.add(rowData);
        }
        return stringsList;
    }


}
