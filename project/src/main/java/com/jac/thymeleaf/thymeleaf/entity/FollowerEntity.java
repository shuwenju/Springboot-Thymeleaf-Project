package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "follower", schema = "mydb", catalog = "")
public class FollowerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "relation_id", nullable = false)
    private int relationId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "friend_id", nullable = false)
    private int friendId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByFriendId;

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

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowerEntity that = (FollowerEntity) o;

        if (relationId != that.relationId) return false;
        if (userId != that.userId) return false;
        if (friendId != that.friendId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = relationId;
        result = 31 * result + userId;
        result = 31 * result + friendId;
        return result;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public UserEntity getUserByFriendId() {
        return userByFriendId;
    }

    public void setUserByFriendId(UserEntity userByFriendId) {
        this.userByFriendId = userByFriendId;
    }
}
