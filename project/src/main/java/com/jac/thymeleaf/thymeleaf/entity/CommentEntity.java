package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;

import java.util.Collection;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "comment", schema = "mydb", catalog = "")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_id", nullable = false)
    private int commentId;
    @Basic
    @Column(name = "post_post_id", nullable = false)
    private int postPostId;
    @ManyToOne
    @JoinColumn(name = "post_post_id", referencedColumnName = "post_id", nullable = false)
    private PostEntity postByPostPostId;
    @OneToMany(mappedBy = "commentByCommentCommentId")
    private Collection<LikeCommentEntity> likeCommentsByCommentId;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

        CommentEntity that = (CommentEntity) o;

        if (commentId != that.commentId) return false;
        if (postPostId != that.postPostId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + postPostId;
        return result;
    }

    public PostEntity getPostByPostPostId() {
        return postByPostPostId;
    }

    public void setPostByPostPostId(PostEntity postByPostPostId) {
        this.postByPostPostId = postByPostPostId;
    }

    public Collection<LikeCommentEntity> getLikeCommentsByCommentId() {
        return likeCommentsByCommentId;
    }

    public void setLikeCommentsByCommentId(Collection<LikeCommentEntity> likeCommentsByCommentId) {
        this.likeCommentsByCommentId = likeCommentsByCommentId;
    }
}
