package org.nju.sesta.sams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@Table(name = "tbl_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private String studentId;

    private String title;

    private String content;

    @Temporal(TemporalType.DATE)
    private Calendar releasedTime;

    private Boolean isRead;


    public Message() {
        releasedTime = Calendar.getInstance();
        isRead = false;
    }
}
