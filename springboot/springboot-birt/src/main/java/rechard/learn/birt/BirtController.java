package rechard.learn.birt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.PipedInputStream;

@RestController
public class BirtController {
    @Autowired
    BirtReportGenerator birtReportGenerator;

    Logger logger = LoggerFactory.getLogger(BirtController.class);

    @PostMapping("/report/car/{searchBy}/{seachValue}")
    public void test(@PathVariable("searchBy") String searchBy,@PathVariable("seachValue") String searchValue){
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
    }
}
