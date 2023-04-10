package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Collection;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "user", schema = "mydb", catalog = "")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "joined_since", nullable = true)
    private Date joinedSince;
    @Basic
    @Column(name = "profile_pic", nullable = true, length = 255)
    private String profilePic;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<FollowerEntity> followersByUserId;
    @OneToMany(mappedBy = "userByFriendId")
    private Collection<FollowerEntity> followersByUserId_0;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<FollowingEntity> followingsByUserId;
    @OneToMany(mappedBy = "userByFollowerId")
    private Collection<FollowingEntity> followingsByUserId_0;
    @OneToMany(mappedBy = "userByUserUserId")
    private Collection<LikeCommentEntity> likeCommentsByUserId;
    @OneToMany(mappedBy = "userByUserUserId")
    private Collection<LikePostEntity> likePostsByUserId;
    @OneToMany(mappedBy = "userByUserUserId")
    private Collection<PostEntity> postsByUserId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (joinedSince != null ? !joinedSince.equals(that.joinedSince) : that.joinedSince != null) return false;
        if (profilePic != null ? !profilePic.equals(that.profilePic) : that.profilePic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId.intValue();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (joinedSince != null ? joinedSince.hashCode() : 0);
        result = 31 * result + (profilePic != null ? profilePic.hashCode() : 0);
        return result;
    }

}
