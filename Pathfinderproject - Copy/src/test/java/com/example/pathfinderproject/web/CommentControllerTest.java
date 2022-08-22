package com.example.pathfinderproject.web;

import com.example.pathfinderproject.model.entity.Comment;
import com.example.pathfinderproject.model.entity.Route;
import com.example.pathfinderproject.model.entity.User;
import com.example.pathfinderproject.repository.RouteRepository;
import com.example.pathfinderproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;
    private User testUser;
    private static final String COMMENT_1 = "Testing Comment 1";
    private static final String COMMENT_2 = "Testing Comment 2";

    @BeforeEach
    void setUp(){

       var testUserOp = userRepository.findByUsername("test");

        if(testUserOp.isEmpty()){
            testUser = new User();
            testUser.setUsername("test");
            testUser.setFullName("Test Tester");
            testUser.setPassword("12345");
            userRepository.save(testUser);
        }else{
            testUser = testUserOp.get();
        }

    }

//    @AfterEach
//    void tearDown(){
//        userRepository.deleteAll();
//        routeRepository.deleteAll();
//    }


    @Test
    void testGetComments() throws Exception{
        Route route = initComments(initRoute());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/comments/"+ route.getId()))
                .andExpect(status().isOk());
    }

    private Route initRoute(){
        var routeOp = routeRepository.findByName("Testing route");
        if(routeOp.isEmpty()){
            Route testRoute = new Route();
            testRoute.setName("Testing route");
            return routeRepository.save(testRoute);
        }
        return routeOp.get();

    }
    private Route initComments(Route route) {
            Comment commentTest1 = new Comment();
            commentTest1.setCreated(LocalDateTime.now());
            commentTest1.setAuthor(testUser);
            commentTest1.setTextContent(COMMENT_1);
            commentTest1.setApproved(true);
            commentTest1.setRoute(route);

            Comment commentTest2 = new Comment();
            commentTest2.setCreated(LocalDateTime.now());
            commentTest2.setAuthor(testUser);
            commentTest2.setTextContent(COMMENT_2);
            commentTest2.setApproved(true);
            commentTest2.setRoute(route);

        if(route.getComments() == null){
            route.setComments(Set.of(commentTest1, commentTest2));
        }
        return routeRepository.save(route);
    }
}
