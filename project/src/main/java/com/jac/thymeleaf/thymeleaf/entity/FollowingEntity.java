package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "following", schema = "mydb", catalog = "")
public class FollowingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "relation_id", nullable = false)
    private int relationId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "follower_id", nullable = false)
    private int followerId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByFollowerId;

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowingEntity that = (FollowingEntity) o;

        if (relationId != that.relationId) return false;
        if (userId != that.userId) return false;
        if (followerId != that.followerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = relationId;
        result = 31 * result + userId;
        result = 31 * result + followerId;
        return result;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public UserEntity getUserByFollowerId() {
        return userByFollowerId;
    }

    public void setUserByFollowerId(UserEntity userByFollowerId) {
        this.userByFollowerId = userByFollowerId;
    }
}
