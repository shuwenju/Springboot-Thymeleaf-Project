package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "post", schema = "mydb", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id", nullable = false)
    private int postId;
    @Basic
    @Column(name = "content", nullable = false, length = -1)
    private String content;
    @Basic
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
    @Basic
    @Column(name = "user_user_id", nullable = false)
    private int userUserId;
    @OneToMany(mappedBy = "postByPostPostId")
    private Collection<CommentEntity> commentsByPostId;
    @OneToMany(mappedBy = "postByPostPostId")
    private Collection<LikePostEntity> likePostsByPostId;
    @ManyToOne
    @JoinColumn(name = "user_user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByUserUserId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (postId != that.postId) return false;
        if (userUserId != that.userUserId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + userUserId;
        return result;
    }

}