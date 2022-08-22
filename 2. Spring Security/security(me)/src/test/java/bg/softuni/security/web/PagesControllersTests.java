package bg.softuni.security.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class PagesControllersTests {


    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "user")
    void testShowAllPages() throws Exception {
        mockMvc.perform(get("/pages/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("all"));
    }

    @Test
    @WithMockUser(username = "user",roles = "MODERATOR")
    void testModeratorPage() throws Exception {

        mockMvc.perform(get("/pages/moderators"))
                .andExpect(status().isOk())
                .andExpect(view().name("moderators"));

    }
    @Test
    @WithMockUser(username = "user")
    void testIsNotModeratorUser_StatusForbidden() throws Exception {
        mockMvc.perform(get("/pages/moderators"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "user", roles = "MODERATOR")
    void adminPage_testWithUserRoleModerator_StatusForbidden() throws Exception {
        mockMvc.perform(get("/pages/admins"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user")
    void adminPage_testWithoutRoles_StatusForbidden() throws Exception {
        mockMvc.perform(get("/pages/admins"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = "ADMIN")
    void adminPage_testWithoutRole_StatusForbidden() throws Exception {
        mockMvc.perform(get("/pages/admins"))
                .andExpect(status().isOk())
                .andExpect(view().name("admins"));
    }


}