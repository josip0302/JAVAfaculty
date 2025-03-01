package hr.fer.zemris.java.tecaj_13.model;


import javax.persistence.*;

@Entity
@Table(name="blog_message")
public class BlogMessage {
    private Long id;
    private String autorName;
    private String forUser;
    private String message;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length=4096,nullable=false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Column(length=100,nullable=false)
    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String name) {
        this.autorName = name;
    }
    @Column(length=100,nullable=false)
    public String getForUser() {
        return forUser;
    }

    public void setForUser(String name) {
        this.forUser = name;
    }
}
