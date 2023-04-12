package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String profilePicture;

//    @OneToMany(mappedBy = "user")
//    private List<PostEntity> posts;
//
//    @OneToMany(mappedBy = "user")
//    private List<CommentEntity> comments;


//    @ManyToMany
//    @JoinTable(
//            name = "user_following",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "following_id"))
//    private Set<UserEntity> following;
//
//    @ManyToMany(mappedBy = "following")
//    private Set<UserEntity> followers;

}
