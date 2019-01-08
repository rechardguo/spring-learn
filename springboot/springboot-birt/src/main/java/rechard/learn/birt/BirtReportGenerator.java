package rechard.learn.birt;

import org.eclipse.birt.report.engine.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class BirtReportGenerator {
    @Autowired
    private IReportEngine birtEngine ;

    public ByteArrayOutputStream generate(ReportParameter rptParam) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IReportRunnable runnable = null;
        runnable = birtEngine
                .openReportDesign("E:\\code\\my_github_code\\spring-learn\\springboot\\springboot-schedual\\src\\main\\resources\\car.rptdesign");
        IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask(runnable);
        runAndRenderTask.setParameterValues(setParameters(runnable, rptParam.getParameter()));

        IRenderOption options =new RenderOption();
        if (rptParam.getFormat().equalsIgnoreCase("pdf")) {
            PDFRenderOption pdfOptions = new PDFRenderOption(options);
            pdfOptions.setOutputFormat("pdf");
            pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
            pdfOptions.setOutputStream(baos);
            runAndRenderTask.setRenderOption(pdfOptions);
        }
        runAndRenderTask.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY,
                this.getClass().getClassLoader());
        runAndRenderTask.run();
        runAndRenderTask.close();
        return baos;
    }


    protected HashMap<String, Object> setParameters(IReportRunnable report, Map<String,Object> m) throws Exception {

        HashMap<String, Object> parms = new HashMap<String, Object>();
        IGetParameterDefinitionTask task = birtEngine.createGetParameterDefinitionTask(report);

        Collection<IParameterDefnBase> params = task.getParameterDefns(true);
        Iterator<IParameterDefnBase> iter = params.iterator();
        while (iter.hasNext()) {
            IParameterDefnBase param = (IParameterDefnBase) iter.next();
            Object val=m.get(param.getName());
            if (val!=null) {
                parms.put(param.getName(),val);
            }
        }
        task.close();
        return parms;
    }

}
