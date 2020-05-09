package com.CloudTech.springmongodb;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserController controller;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllTest() throws Exception {
        List<User> result = Arrays.asList(new User("firstName0", "lastName0", "email0", "password0", Arrays.asList(new Course("CourseName0", 123l), new Course("CourseName1", 234l), new Course("CourseName2", 345l))), new User("firstName1", "lastName1", "email1", "password1", Arrays.asList(new Course("CourseName1", 123l), new Course("CourseName3", 234l))), new User("firstName2", "lastName2", "email2", "password2", Arrays.asList(new Course("CourseName1", 123l))), new User("firstName3", "lastName3", "email3", "password3", Arrays.asList()));
        when(repository.findAll()).thenReturn(result);
        mockMvc.perform(get("/user/all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.*", Matchers.hasSize(4))).andExpect(jsonPath("$.[0].*", Matchers.hasSize(6))).andExpect(jsonPath("$.[0].id", Matchers.emptyOrNullString())).andExpect(jsonPath("$.[0].firstName", Matchers.is("firstName0"))).andExpect(jsonPath("$.[0].lastName", Matchers.is("lastName0"))).andExpect(jsonPath("$.[0].email", Matchers.is("email0"))).andExpect(jsonPath("$.[0].password", Matchers.is("password0"))).andExpect(jsonPath("$.[0].courseList.*", Matchers.hasSize(3))).andExpect(jsonPath("$.[0].courseList.[0].*", Matchers.hasSize(3))).andExpect(jsonPath("$.[0].courseList.[0].id", Matchers.emptyOrNullString())).andExpect(jsonPath("$.[0].courseList.[0].name", Matchers.is("CourseName0"))).andExpect(jsonPath("$.[0].courseList.[0].price", Matchers.is(123))).andExpect(jsonPath("$.[1].courseList.*", Matchers.hasSize(2))).andExpect(jsonPath("$.[2].courseList.*", Matchers.hasSize(1))).andExpect(jsonPath("$.[3].courseList.*", Matchers.hasSize(0)));
    }

    @Test
    public void getByIdTest() throws Exception {
        User user = new User("5eb69c37af26a064b69ad0d0", "firstName0", "lastName0", "email0", "password0", Arrays.asList(new Course("CourseName0", 123l), new Course("CourseName1", 234l), new Course("CourseName2", 345l)));
        Optional<User> result = Optional.of(user);
        when(repository.findById("5eb69c37af26a064b69ad0d0")).thenReturn(result);
        mockMvc.perform(get("/user/5eb69c37af26a064b69ad0d0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(6)))
                .andExpect(jsonPath("$.id", Matchers.is("5eb69c37af26a064b69ad0d0")))
                .andExpect(jsonPath("$.firstName", Matchers.is("firstName0")))
                .andExpect(jsonPath("$.lastName", Matchers.is("lastName0")))
                .andExpect(jsonPath("$.email", Matchers.is("email0")))
                .andExpect(jsonPath("$.password", Matchers.is("password0")))
                .andExpect(jsonPath("$.courseList.*", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.courseList.[0].*", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.courseList.[0].id", Matchers.emptyOrNullString()))
                .andExpect(jsonPath("$.courseList.[0].name", Matchers.is("CourseName0")))
                .andExpect(jsonPath("$.courseList.[0].price", Matchers.is(123)))
        ;
    }
}