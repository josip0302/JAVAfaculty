package hr.fer.zemris.java.tecaj_13.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table (name="blog_user")
public class BlogUser {

    private Long id;
    private String firstName;
    private String lastName;
    private String nick;
    private String email;
    private String passwordHash;

   private Collection<BlogEntry> entries=new HashSet<>();
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @OneToMany(mappedBy = "creator",fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    public Collection<BlogEntry> getEntries() {
        return entries;
    }

    public void setEntries(Collection<BlogEntry> entries) {
        this.entries = entries;
    }

    @Column(length=100,nullable=false)
    public String getFirstName() {
        return firstName;
    }

    @Column(length=100,nullable=false)
    public String getLastName() {
        return lastName;
    }


    @Column(length=100,unique = true,nullable=false)
    public String getNick() {
        return nick;
    }

    @Column(length=100,nullable=false)
    public String getEmail() {
        return email;
    }

    @Column(length=4096,nullable=false)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id @GeneratedValue
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogUser blogUser = (BlogUser) o;
        return Objects.equals(id, blogUser.id) && Objects.equals(firstName, blogUser.firstName) && Objects.equals(lastName, blogUser.lastName) && Objects.equals(nick, blogUser.nick) && Objects.equals(email, blogUser.email) && Objects.equals(passwordHash, blogUser.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nick, email, passwordHash);
    }

    @Override
    public String toString() {
        return "BlogUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
