package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "like_post", schema = "mydb", catalog = "")
public class LikePostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "like_id", nullable = false)
    private int likeId;
    @Basic
    @Column(name = "user_user_id", nullable = false)
    private int userUserId;
    @Basic
    @Column(name = "post_post_id", nullable = false)
    private int postPostId;
    @ManyToOne
    @JoinColumn(name = "user_user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByUserUserId;
    @ManyToOne
    @JoinColumn(name = "post_post_id", referencedColumnName = "post_id", nullable = false)
    private PostEntity postByPostPostId;

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }

    public int getPostPostId() {
        return postPostId;
    }

    public void setPostPostId(int postPostId) {
        this.postPostId = postPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikePostEntity that = (LikePostEntity) o;

        if (likeId != that.likeId) return false;
        if (userUserId != that.userUserId) return false;
        if (postPostId != that.postPostId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = likeId;
        result = 31 * result + userUserId;
        result = 31 * result + postPostId;
        return result;
    }

    public UserEntity getUserByUserUserId() {
        return userByUserUserId;
    }

    public void setUserByUserUserId(UserEntity userByUserUserId) {
        this.userByUserUserId = userByUserUserId;
    }

    public PostEntity getPostByPostPostId() {
        return postByPostPostId;
    }

    public void setPostByPostPostId(PostEntity postByPostPostId) {
        this.postByPostPostId = postByPostPostId;
    }
}
