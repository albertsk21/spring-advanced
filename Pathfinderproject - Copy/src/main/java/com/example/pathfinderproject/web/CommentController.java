package com.example.pathfinderproject.web;

import com.example.pathfinderproject.model.binding.CommentCreateBM;
import com.example.pathfinderproject.model.service.CommentServiceModel;
import com.example.pathfinderproject.model.view.CommentVM;
import com.example.pathfinderproject.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/comments/{routeId}")
    public ResponseEntity<CommentVM> postComment(@AuthenticationPrincipal UserDetails principal,
                                                 @PathVariable Long routeId,
                                                 @RequestBody @Valid CommentCreateBM commentCreateBM){
        CommentServiceModel commentSM = modelMapper.map(commentCreateBM, CommentServiceModel.class);
        commentSM.setId(routeId);
        CommentVM commentVM = commentService.CreateComment(commentSM);
        URI locationOfComment = URI.create(String.format("/api/comments/%s", routeId, commentVM.getId()));

        return ResponseEntity.created(locationOfComment).body(commentVM);
    }
}
