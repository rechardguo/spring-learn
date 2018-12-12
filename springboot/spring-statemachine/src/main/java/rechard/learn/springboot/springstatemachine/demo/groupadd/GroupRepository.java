package rechard.learn.springboot.springstatemachine.demo.groupadd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GroupRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Group group){
        jdbcTemplate.update("insert into pagroup(groupId,groupName,status) values (?,?,?)",
                group.getGroupId(),
                group.getGroupName(),
                Status.PENDING_APPROVAL.getStatusCode());
    }

    public List listAll() {
        List list  = jdbcTemplate.query("select groupId,groupName,status,isAdvance from pagroup",rowMapper());
        return list;
    }

    public Group findGroupById(int groupId) {
        Group group = jdbcTemplate.queryForObject("select groupId, groupName,status,isAdvance from pagroup where groupId = ?", new Object[]{groupId},rowMapper());
        return group;
    }
    private RowMapper<Group> rowMapper(){
        return  new RowMapper<Group>() {
            public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
                Group group = new Group(rs.getInt("groupId"), rs.getString("groupName"));
                group.setState(rs.getInt("status"));
                group.setAdvance(rs.getBoolean("isAdvance"));
                return group;
            }
        };
    }
}
