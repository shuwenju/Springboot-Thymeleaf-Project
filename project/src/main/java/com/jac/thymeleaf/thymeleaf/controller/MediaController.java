//package com.jac.thymeleaf.thymeleaf.controller;
//import com.jac.thymeleaf.thymeleaf.model.PostModel;
//
//import com.jac.thymeleaf.thymeleaf.service.MediaServiceImpl;
//import com.jac.thymeleaf.thymeleaf.view.CommentView;
//import com.jac.thymeleaf.thymeleaf.view.PostView;
//import com.jac.thymeleaf.thymeleaf.view.UserView;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.ui.Model;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Shuwen Ju
// */
//@Controller
//@RequestMapping("/")
//public class MediaController {
//
//    private final MediaServiceImpl postServiceImpl;
//
//    @Autowired
//    public MediaController(MediaServiceImpl postService) {
//        this.postServiceImpl = postService;
//    }
//
//    @GetMapping("/newsfeed")
//    public String profilePosts(Model theModel){
//        List<PostModel> thePosts = postServiceImpl.getAllPosts();
//
//        theModel.addAttribute("posts", thePosts);
//        return "newsfeed";
//    }
//
//
//    //shu testing newfeed page with below sketch data:
//    UserView user1 = UserView.builder()
//            .id(1L)
//            .username("user1")
//            .profilePicture("images/profile1.png")
//            .posts(new ArrayList<>())
//            .comments(new ArrayList<>()).build();
//    CommentView comment1 = CommentView.builder()
//            .id(2L)
//            .content("First comment")
//            .user(user1)
//            .build();
//
//    CommentView comment2 = CommentView.builder()
//            .id(2L)
//            .content("Second comment")
//            .user(user1)
//            .build();
//
//
//    PostView post1 =PostView.builder()
//            .id(1l)
//            .content("first post!")
//            .user(user1)
////            .comments(new ArrayList<>())
//            .build();
//    List<CommentView> commentList1 = new ArrayList<>();
//@GetMapping("view")
//public String viewAllPosts(Model theModel){
//    List<PostView> postViewList = new ArrayList<>();
//    LocalDateTime createdAt = LocalDateTime.now();
//    post1.formatCreatedAt(createdAt);
//    post1.setFormattedDateTime(post1.getFormattedDateTime());
//    post1.setComments(commentList1);
//
//    PostView post2 =PostView.builder()
//            .id(2l)
//            .content("second post!")
//            .user(user1)
////            .comments(new ArrayList<>())
//            .build();
//    List<CommentView> commentList2 = new ArrayList<>();
//    commentList2.add(comment1);
//    commentList2.add(comment2);
//    LocalDateTime createdAt2 = LocalDateTime.now();
//    post2.formatCreatedAt(createdAt2);
//    post2.setFormattedDateTime(post2.getFormattedDateTime());
//    postViewList.add(post1);
//    postViewList.add(post2);
//    post2.setComments(commentList2);
//
//    theModel.addAttribute("postList", postViewList);
//    return "newsfeed";
//}
//
//    @PostMapping("addcomments")
//    public String addComment( @RequestParam("content") String commentContent){
////                              @RequestParam("postId") Long postid) {
//        CommentView newComment = CommentView.builder()
//                .content(commentContent)
//                .createdAt(LocalDateTime.now())
//                .user(user1)
//                .post(post1)
//                .build();
//        post1.getComments().add(newComment);
//        return "redirect:view";
//    }
//}
