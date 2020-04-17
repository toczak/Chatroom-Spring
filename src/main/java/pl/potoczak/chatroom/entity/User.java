package pl.potoczak.chatroom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 25)
    @NotBlank(message = "Username is required.")
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    @NotBlank(message = "E-mail is required.")
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    @NotBlank(message = "Password is required.")
    private String password;

    @Transient
    @NotBlank(message = "Confirm password is required.")
    private String matchingPassword;

    @OneToMany(mappedBy = "user")
    private Collection<Post> allPosts;

    public User() {
    }

    public User(@NotBlank(message = "Username is required.") String username) {
        this.username = username;
    }

    public User(@NotBlank(message = "Username is required.") String username, @NotBlank(message = "Password is required.") String password) {
        this.username = username;
        this.password = password;
    }

    public User(@NotBlank(message = "Username is required.") String username, @NotBlank(message = "Password is required.") String password, @NotBlank(message = "Confirm password is required.") String matchingPassword) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
    public Collection<Post> getAllPosts() {
        return allPosts;
    }

    public void setAllPosts(Collection<Post> postsById) {
        this.allPosts = postsById;
    }
}
