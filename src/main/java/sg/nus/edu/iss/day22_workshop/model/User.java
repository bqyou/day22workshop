package sg.nus.edu.iss.day22_workshop.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String fullName;

    private String email;

    private Integer phone;

    private Date confirmationDate;

    private String comments;

}
