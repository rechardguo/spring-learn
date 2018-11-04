package rechard.learn.springboot.springstatemachine.demo.groupadd;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public enum Status {
    PENDING_APPROVAL("status.pending_approval", 1),
    PARTIALLY_APPROVED("status.partially_approval", 2),
    APPROVED("status.approved", 3),
    REJECTED("status.rejected", 4),
    PENGDING_DOCUMENT_CHECK("status.pending_document_check", 5),
    PENDING_APPROVAL_CONFIRMATION("status.pending_approval_confirmation", 10),
    PENDING_REJECT_CONFIRMATION("status.pending_reject_confirmation", 11),
    CHOICE("spring.state.machine.choice",501);
    private String msgKey;
    private int statusCode;
    Status(String desc, int statusCode) {
        this.msgKey = desc;
        this.statusCode = statusCode;
    }
    public static Status valueOf(int statusCode) {
       Iterator<Status> iterator = EnumSet.allOf(Status.class).iterator();
       while (iterator.hasNext()){
           Status st =iterator.next();
           if(st.statusCode==statusCode)
               return st;
       }
        throw new IllegalArgumentException("invalid status code");
    }
    public String getMsgKey() {
        return msgKey;
    }
    public int getStatusCode() {
        return statusCode;
    }
}