package com.example.pathfinderproject.web;

import com.example.pathfinderproject.model.binding.CommentCreateBM;
import com.example.pathfinderproject.model.service.CommentServiceModel;
import com.example.pathfinderproject.model.view.CommentVM;
import com.example.pathfinderproject.service.CommentService;
import com.example.pathfinderproject.util.exception.ApiError;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<List<CommentVM>> getComments(@PathVariable Long routeId,
                                                       Principal principal){
        return ResponseEntity.ok(commentService.getComments(routeId));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe -> apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}