package rechard.learn.springboot.springstatemachine.demo.groupadd;

public class Group {
    private int groupId;
    private String groupName;
    private int state;
    private boolean isAdvance;
    public Group(){
    }

    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isAdvance() {
        return isAdvance;
    }

    public void setAdvance(boolean advance) {
        isAdvance = advance;
    }
}
