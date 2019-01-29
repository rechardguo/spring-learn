package rechard.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rechard.learn.birt.BirtReportGenerator;
import rechard.learn.birt.ReportParameter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

@RestController
public class ReportController {
    @Autowired
    BirtReportGenerator birtReportGenerator;

    Logger logger = LoggerFactory.getLogger(ReportController.class);

    @PostMapping("/report/car/{searchBy}/{seachValue}")
    public String searchCarReport(@PathVariable("searchBy") String searchBy,@PathVariable("seachValue") String searchValue){
        ReportParameter rm=new ReportParameter("car","PDF");
        rm.setParameter(searchBy, searchValue);
        try {
            ByteArrayOutputStream baos=birtReportGenerator.generate(rm);
            FileOutputStream fops = new FileOutputStream("c:/test/carreport_"+System.currentTimeMillis()+".pdf");
            fops.write(baos.toByteArray());
            fops.close();
            baos.close();
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        return "Hello World";
    }


    @PostMapping("/report/user")
    public String searchUserReport(){
        ReportParameter rm=new ReportParameter("user","PDF");
        try {
            ByteArrayOutputStream baos=birtReportGenerator.generate(rm);
            FileOutputStream fops = new FileOutputStream("c:/test/user_report_"+System.currentTimeMillis()+".pdf");
            fops.write(baos.toByteArray());
            fops.close();
            baos.close();
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        return "Hello World";
    }
}
