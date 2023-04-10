package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "like_post", schema = "mydb", catalog = "")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LikePostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "like_id", nullable = false)
    private Long likeId;
    @Basic
    @Column(name = "user_user_id", nullable = false)
    private Long userUserId;
    @Basic
    @Column(name = "post_post_id", nullable = false)
    private Long postPostId;
    @ManyToOne
    @JoinColumn(name = "user_user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userByUserUserId;
    @ManyToOne
    @JoinColumn(name = "post_post_id", referencedColumnName = "post_id", nullable = false)
    private PostEntity postByPostPostId;


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
        int result = likeId.intValue();
        result = 31 * result + userUserId.intValue();
        result = 31 * result + postPostId.intValue();
        return result;
    }

}
