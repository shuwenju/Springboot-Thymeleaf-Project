package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "user", schema = "mydb", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinedSince() {
        return joinedSince;
    }

    public void setJoinedSince(Date joinedSince) {
        this.joinedSince = joinedSince;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

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
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (joinedSince != null ? joinedSince.hashCode() : 0);
        result = 31 * result + (profilePic != null ? profilePic.hashCode() : 0);
        return result;
    }

    public Collection<FollowerEntity> getFollowersByUserId() {
        return followersByUserId;
    }

    public void setFollowersByUserId(Collection<FollowerEntity> followersByUserId) {
        this.followersByUserId = followersByUserId;
    }

    public Collection<FollowerEntity> getFollowersByUserId_0() {
        return followersByUserId_0;
    }

    public void setFollowersByUserId_0(Collection<FollowerEntity> followersByUserId_0) {
        this.followersByUserId_0 = followersByUserId_0;
    }

    public Collection<FollowingEntity> getFollowingsByUserId() {
        return followingsByUserId;
    }

    public void setFollowingsByUserId(Collection<FollowingEntity> followingsByUserId) {
        this.followingsByUserId = followingsByUserId;
    }

    public Collection<FollowingEntity> getFollowingsByUserId_0() {
        return followingsByUserId_0;
    }

    public void setFollowingsByUserId_0(Collection<FollowingEntity> followingsByUserId_0) {
        this.followingsByUserId_0 = followingsByUserId_0;
    }

    public Collection<LikeCommentEntity> getLikeCommentsByUserId() {
        return likeCommentsByUserId;
    }

    public void setLikeCommentsByUserId(Collection<LikeCommentEntity> likeCommentsByUserId) {
        this.likeCommentsByUserId = likeCommentsByUserId;
    }

    public Collection<LikePostEntity> getLikePostsByUserId() {
        return likePostsByUserId;
    }

    public void setLikePostsByUserId(Collection<LikePostEntity> likePostsByUserId) {
        this.likePostsByUserId = likePostsByUserId;
    }

    public Collection<PostEntity> getPostsByUserId() {
        return postsByUserId;
    }

    public void setPostsByUserId(Collection<PostEntity> postsByUserId) {
        this.postsByUserId = postsByUserId;
    }
}
