package com.jac.thymeleaf.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Shuwen Ju
 */
@Entity
@Table(name = "following", schema = "mydb", catalog = "")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
