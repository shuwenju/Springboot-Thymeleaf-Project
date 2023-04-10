package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "like_comment", schema = "mydb", catalog = "")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeCommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "like_id", nullable = false)
    private int likeId;
    @Basic
    @Column(name = "comment_comment_id", nullable = false)
    private int commentCommentId;
    @Basic
    @Column(name = "user_user_id", nullable = false)
    private int userUserId;
    @ManyToOne
    @JoinColumn(name = "comment_comment_id", referencedColumnName = "comment_id", nullable = false)
    private CommentEntity commentByCommentCommentId;
    @ManyToOne
    @JoinColumn(name = "user_user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByUserUserId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikeCommentEntity that = (LikeCommentEntity) o;

        if (likeId != that.likeId) return false;
        if (commentCommentId != that.commentCommentId) return false;
        if (userUserId != that.userUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = likeId;
        result = 31 * result + commentCommentId;
        result = 31 * result + userUserId;
        return result;
    }

}