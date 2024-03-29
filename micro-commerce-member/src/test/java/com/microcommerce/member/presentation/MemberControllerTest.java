package com.microcommerce.member.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcommerce.member.application.MemberService;
import com.microcommerce.member.domain.dto.req.SignInReqDto;
import com.microcommerce.member.domain.dto.req.SignUpReqDto;
import com.microcommerce.member.domain.dto.res.SignInResDto;
import com.microcommerce.member.domain.dto.res.SignUpResDto;
import com.microcommerce.member.domain.enums.MemberType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService memberService;

    @Test
    public void signUp() throws Exception {
        final SignUpResDto res = SignUpResDto.getInstance("test@test.com");
        Mockito.when(memberService.signUp(Mockito.any(SignUpReqDto.class)))
                .thenReturn(res);

        final SignUpReqDto req = new SignUpReqDto("test@test.com", "test name", "password",
                "01000000000", MemberType.NORMAL);
        final String reqJson = new ObjectMapper().writeValueAsString(req);

        mvc.perform(
                        MockMvcRequestBuilders
                                .post("/public-api/v1/members/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(reqJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("data.email").value("test@test.com"));
    }

    @Test
    public void signIn() throws Exception {
        final SignInResDto res = SignInResDto.getInstance("TestAccessToken...");
        Mockito.when(memberService.signIn(Mockito.any(SignInReqDto.class)))
                .thenReturn(res);

        final SignInReqDto req = new SignInReqDto("test@test.com", "test name", MemberType.NORMAL);
        final String reqJson = new ObjectMapper().writeValueAsString(req);

        mvc.perform(
                        MockMvcRequestBuilders
                                .post("/public-api/v1/members/sign-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(reqJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("data.accessToken").value("TestAccessToken..."));
    }

    @Test
    public void getProfile() {
        // TODO
    }

}