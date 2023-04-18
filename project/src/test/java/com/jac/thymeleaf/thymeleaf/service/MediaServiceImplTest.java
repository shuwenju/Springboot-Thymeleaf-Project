package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import com.jac.thymeleaf.thymeleaf.exceptions.PostNotFoundException;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;
import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
import com.jac.thymeleaf.thymeleaf.repository.CommentRepository;
import com.jac.thymeleaf.thymeleaf.repository.PostRepository;
import com.jac.thymeleaf.thymeleaf.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Shuwen Ju
 */
@ExtendWith(MockitoExtension.class)
public class MediaServiceImplTest {

    @InjectMocks
    private MediaServiceImpl target;

    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MapperHelper mapperHelper;

    @Test
    void test_getAllPosts_returnAList(){
        //initialization: assumption

        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("user@mail.com")
                .firstName("usertest")
                .lastName("userLname")
                .password("1234512345")
                .username("usertestname")
                .sex("/images/default-profile.jpeg").build();
        UserModel userModel = UserModel.builder()
                .id(1L)
                .email("user@mail.com")
                .firstName("usertest")
                .lastName("userLname")
                .password("1234512345")
                .username("usertestname")
                .sex("/images/default-profile.jpeg").build();

        List<PostEntity> postEntityList = new ArrayList<>();
        postEntityList.add(PostEntity.builder()
                .id(1L)
                .content("1st post")
                .createdAt(LocalDateTime.now())
                .user(user).build());
        postEntityList.add(PostEntity.builder()
                .id(2L)
                .content("2nd post")
                .createdAt(LocalDateTime.now())
                .user(user).build());
        postEntityList.add(PostEntity.builder()
                .id(3L)
                .content("3rd post")
                .createdAt(LocalDateTime.now())
                .user(user).build());
        List<PostEntity> sortedEntityListDesc = postEntityList.stream()
                .sorted(Comparator.comparingLong(PostEntity::getId).reversed())
                .collect(Collectors.toList());
        List<PostModel> postModelList = new ArrayList<>();
        PostModel post3 = PostModel.builder()
                .id(3L)
                .content("3rd post")
                .createdAt(LocalDateTime.now())
                .user(userModel).build();
        postModelList.add(post3);
        postModelList.add(PostModel.builder()
                .id(2L)
                .content("2nd post")
                .createdAt(LocalDateTime.now())
                .user(userModel).build());
        postModelList.add(PostModel.builder()
                .id(1L)
                .content("1st post")
                .createdAt(LocalDateTime.now())
                .user(userModel).build());
        when(postRepository.findAllByOrderByIdDesc()).thenReturn(sortedEntityListDesc);
        when(mapperHelper.convertPostEntityListToPostModelList(sortedEntityListDesc)).thenReturn(postModelList);

        //call method:
        List<PostModel> actual = target.getAllPosts();

        //assertion:
        assertNotNull(actual);
        assertEquals(3, actual.size());
        assertEquals(post3, actual.get(0));
    }

    @Test
    public void testGetAllCommentByPostId_returnAList() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("user@mail.com")
                .firstName("usertest")
                .lastName("userLname")
                .password("1234512345")
                .username("usertestname")
                .sex("/images/default-profile.jpeg").build();
        PostEntity post = PostEntity.builder()
                .id(1L)
                .content("1st post")
                .createdAt(LocalDateTime.now())
                .user(user).build();
        UserModel userModel = UserModel.builder()
                .id(1L)
                .email("user@mail.com")
                .firstName("usertest")
                .lastName("userLname")
                .password("1234512345")
                .username("usertestname")
                .sex("/images/default-profile.jpeg").build();
        PostModel postModel = PostModel.builder()
                .id(1L)
                .content("1st post")
                .createdAt(LocalDateTime.now())
                .user(userModel).build();

        // Arrange
        Long postId = 1L;
        CommentEntity comment1 = CommentEntity.builder()
                .id(1L)
                .content("1st comment")
                .createdAt(LocalDateTime.now())
                .post(post)
                .user(user).build();
        CommentEntity comment2 = CommentEntity.builder()
                .id(2L)
                .content("2nd comment")
                .createdAt(LocalDateTime.now())
                .post(post)
                .user(user).build();
        List<CommentEntity> commentList = Arrays.asList(comment1, comment2);

        CommentModel commentModel1 = CommentModel.builder()
                .id(1L)
                .content("1st comment")
                .createdAt(LocalDateTime.now())
                .post(postModel)
                .user(userModel).build();
        CommentModel commentModel2 = CommentModel.builder()
                .id(2L)
                .content("2nd comment")
                .createdAt(LocalDateTime.now())
                .post(postModel)
                .user(userModel).build();
        List<CommentModel> commentModelList = Arrays.asList(commentModel1, commentModel2);
        when(commentRepository.findByPostId(postId)).thenReturn(Optional.of(commentList));
        when(mapperHelper.convertCommentEntityListtoCommentModelList(Optional.of(commentList).get())).thenReturn(commentModelList);

        // Act
        List<CommentModel> result = target.getAllCommentByPostId(postId);

        // Assert
        assertEquals(2, result.size());
        assertEquals("1st comment", result.get(0).getContent());
        assertEquals("2nd comment", result.get(1).getContent());
    }



    @Test
    public void testGetAllCommentByPostId_returnEmptyList() {
        // Arrange
        Long postId = 1L;
        when(commentRepository.findByPostId(postId)).thenReturn(Optional.empty());

        // Act
        List<CommentModel> result = target.getAllCommentByPostId(postId);

        // Assert
        assertTrue(result.isEmpty());
    }


    @Test
    public void testFindPostById_returnPostModel() {
        // Arrange
        Long id = 1L;
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("user@mail.com")
                .firstName("usertest")
                .lastName("userLname")
                .password("1234512345")
                .username("usertestname")
                .sex("/images/default-profile.jpeg").build();
        UserModel userModel = UserModel.builder()
                .id(1L)
                .email("user@mail.com")
                .firstName("usertest")
                .lastName("userLname")
                .password("1234512345")
                .username("usertestname")
                .sex("/images/default-profile.jpeg").build();
        PostEntity postEntity =PostEntity.builder()
                .id(1L)
                .content("1st post")
                .createdAt(LocalDateTime.now())
                .user(user).build();
        PostModel expectedPostModel = PostModel.builder()
                .id(1L)
                .content("1st post")
                .createdAt(LocalDateTime.now())
                .user(userModel).build();
        when(postRepository.findById(id)).thenReturn(Optional.of(postEntity));
        when(mapperHelper.convertPostEntityToPostModel(Optional.of(postEntity).get())).thenReturn(expectedPostModel);

        // Act
        PostModel actual = target.findPostById(id);

        // Assert
        assertEquals(actual, expectedPostModel);

    }

    @Test
    public void testFindPostById_whenPostDoesNotExist_shouldThrowPostNotFoundException() {
        // Arrange
        Long id = 1L;
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(PostNotFoundException.class, () -> {
            target.findPostById(id);
        });
        String expectedMsg = "Post not found with id " + id;
        String actualMsg = exception.getMessage();

        //assert
        assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    public void savePostTest(){
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("user@mail.com")
                .firstName("usertest")
                .lastName("userLname")
                .password("1234512345")
                .username("usertestname")
                .sex("/images/default-profile.jpeg").build();
        UserModel userModel = UserModel.builder()
                .id(1L)
                .email("user@mail.com")
                .firstName("usertest")
                .lastName("userLname")
                .password("1234512345")
                .username("usertestname")
                .sex("/images/default-profile.jpeg").build();
        PostEntity postEntity =PostEntity.builder()
                .id(1L)
                .content("1st post")
                .createdAt(LocalDateTime.now())
                .user(user).build();
        PostModel postModel = PostModel.builder()
                .id(1L)
                .content("1st post")
                .createdAt(LocalDateTime.now())
                .user(userModel).build();

        when(mapperHelper.convertPostModeltoPostEntity(postModel)).thenReturn(postEntity);
        when(userRepository.save(user)).thenReturn(user);
        when(postRepository.save(postEntity)).thenReturn(postEntity);

        //call
        target.savePost(postModel);

        //verify
        verify(userRepository, times(1)).save(user);
        verify(postRepository, times(1)).save(postEntity);
    }

}
