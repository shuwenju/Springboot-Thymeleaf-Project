package com.jac.thymeleaf.thymeleaf;

import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;
import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
import com.jac.thymeleaf.thymeleaf.repository.PostRepository;
import com.jac.thymeleaf.thymeleaf.service.MediaService;
import com.jac.thymeleaf.thymeleaf.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootApplication
public class ThymeleafApplication implements CommandLineRunner {

	private final MediaService mediaService;
	private final MapperHelper mapperHelper;
	private final PostRepository postRepository;
	private final UserService userService;
	@Autowired
	public ThymeleafApplication(MediaService mediaService, MapperHelper mapperHelper, PostRepository postRepository, UserService userService) {
		this.mediaService = mediaService;
		this.mapperHelper = mapperHelper;
		this.postRepository = postRepository;
		this.userService = userService;
	}


	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		//testing below service functions:

//		System.out.println(mediaService.getAllPosts()); //this worked fine pulling posts
//		System.out.println(mediaService.getPostByUserId(2L)); //this worked fine pulling posts by user id from database
//		mediaService.deletePost(5L); //this worked fine deleting post 5L in database
//		System.out.println(mapperHelper.convertUserEntitytoModel(userService.findById(1L).get())); //this can get a user by id


//		YOU MUST SAVE THE USER ENTITY FIRST BEFORE YOU CAN SAVE POST ENTITY, DO LIKE BELOW:
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		LocalDateTime localDateTime = timestamp.toLocalDateTime();
//		PostModel newPost = PostModel.builder()
//				.content("saving again")
//				.createdAt(localDateTime)
//				.user(mapperHelper.convertUserEntitytoModel(userService.findById(1L).get()))
//						.build();
////		userService.save(userService.findById(1L).get()); // added this line in service method
//		mediaService.savePost(newPost); //tested and worked!
//		System.out.println(newPost); //	format of created_at from java: createdAt=2023-04-13T20:14:40


		// this didn't work initially, the save method of post keeps clearing the post id to null, i had to add the lines to setPost in comment's save method first
		// SAME HERE YOU MUST SAVE USER ENTITY AND POST ENTITY FIRST BEFORE SAVING COMMENT
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		LocalDateTime localDateTime = timestamp.toLocalDateTime();
//		PostModel existingPost = mapperHelper.convertPostEntityToPostModel(postRepository.findById(6L).get());
//		CommentModel newComment = CommentModel.builder()
//				.content("third comment for kel")
//				.createdAt(localDateTime)
//				.post(existingPost)
//				.user(mapperHelper.convertUserEntitytoModel(userService.findById(1L).get())).build();
//
//		System.out.println(newComment);
//		mediaService.saveComment(newComment);


//		System.out.println(mediaService.getAllCommentByPostId(14L)); // this one also worked! when post has no comment it gives an empty list



	}
}
