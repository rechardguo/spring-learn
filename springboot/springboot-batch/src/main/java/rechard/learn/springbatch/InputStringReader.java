package rechard.learn.springbatch;

public class InputStringReader {
    int i=0;
    public String inputString(){
        if(i>10)
            return null;
        return "hello,world"+i++;

    }
}
