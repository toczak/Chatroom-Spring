package pl.potoczak.chatroom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    private int id;
    @NotBlank(message = "Username is required.")
    private String username;
    @NotBlank(message = "E-mail is required.")
    private String email;
    @NotBlank(message = "Password is required.")
    private String password;
    @NotBlank(message = "Confirm password is required.")
    private String matchingPassword;
    private Collection<Post> allPosts;

    public User() {
    }

    public User(@NotBlank(message = "Username is required.") String username) {
        this.username = username;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 25)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    @JsonIgnore
    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public Collection<Post> getAllPosts() {
        return allPosts;
    }

    public void setAllPosts(Collection<Post> postsById) {
        this.allPosts = postsById;
    }
}
