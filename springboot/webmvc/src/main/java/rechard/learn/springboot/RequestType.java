package rechard.learn.springboot;

import java.util.EnumSet;

public enum RequestType {
    CREATE_USER(0),EDIT_USER(1),DELETE_USER(2),
    CREATE_GROUP(3),EDIT_GROUP(4),DELETE_GROUP(5);
    private int code;
    RequestType(int code){
        this.code=code;
    }
    public int getCode() {
     return this.code;
    }
    static RequestType valueOf(int code){
        EnumSet<RequestType> set =EnumSet.allOf(RequestType.class);
        RequestType type = null;
       return  set.stream().filter(requestType->{
           return requestType.getCode()==code;
        }).findFirst().get();
    }

}
