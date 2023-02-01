package sg.nus.edu.iss.day22_workshop.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.day22_workshop.model.User;

@Repository
public class UserRepoImpl implements UserRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String countSQL = "select count(*) from users";

    private final String selectSQL = "select * from users";

    private final String selectByName = "select * from users where full_name like ?";

    private final String selectById = "select * from users where id = ?";

    private final String insertSQL = "insert into users (full_name, email, phone, " +
            "confirmation_date, comments ) values (?, ?, ?, ?, ?)";

    private final String updateSQL = "update users set full_name = ?, email = ?, " +
            "phone = ?, confirmation_date = ?, comments = ? where id = ?";

    @Override
    public int count() {
        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSQL, Integer.class);
        return result;
    }

    @Override
    public Boolean save(User user) {
        Boolean saved;
        saved = jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, user.getFullName());
                ps.setString(2, user.getEmail());
                ps.setInt(3, user.getPhone());
                ps.setDate(4, user.getConfirmationDate());
                ps.setString(5, user.getComments());
                Boolean rslt = ps.execute();
                return rslt;
            }

        });
        return saved;
    }

    // @Override
    // public Boolean save(User user) {
    // Integer saved;
    // saved = jdbcTemplate.update(insertSQL, user.getFullName(), user.getEmail(),
    // user.getPhone()
    // , user.getConfirmationDate(), user.getComments())
    // return saved> 0 ?true:false;
    // }

    @Override
    public List<User> listAll() {
        List<User> rsltList = new ArrayList<User>();
        rsltList = jdbcTemplate.query(selectSQL, BeanPropertyRowMapper.newInstance(User.class));
        return rsltList;
    }

    @Override
    public User findByName(String name) {
        User user = jdbcTemplate.queryForObject(selectByName, BeanPropertyRowMapper.newInstance(User.class), name);
        return user;
    }

    @Override
    public User findById(Integer id) {
        User user = jdbcTemplate.queryForObject(selectById, BeanPropertyRowMapper.newInstance(User.class), id);
        return user;
    }

    @Override
    public Integer update(User user) {
        Integer updated = 0;
        updated = jdbcTemplate.update(updateSQL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getFullName());
                ps.setString(2, user.getEmail());
                ps.setInt(3, user.getPhone());
                ps.setDate(4, user.getConfirmationDate());
                ps.setString(5, user.getComments());
                ps.setInt(6, user.getId());
            }
        });
        return updated;
    }

}
