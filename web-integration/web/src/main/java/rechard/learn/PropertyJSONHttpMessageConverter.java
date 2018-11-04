package rechard.learn;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import rechard.learn.bean.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertyJSONHttpMessageConverter extends AbstractHttpMessageConverter<User> {

    public PropertyJSONHttpMessageConverter(){
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(User.class);
    }


    @Override
    protected User readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException { InputStream inStream = inputMessage.getBody();
       Properties properties = new Properties();
       properties.load(inStream);

       User user = new User();
       user.setAge(Integer.parseInt(properties.getProperty("user.age")));
       user.setName(properties.getProperty("user.name"));
        return user;
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        OutputStream outputStream = outputMessage.getBody();

        Properties properties = new Properties();

        properties.setProperty("user.name",user.getName());
        properties.setProperty("user.age",user.getAge()+"");
        properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"Written by web server");

    }
}
