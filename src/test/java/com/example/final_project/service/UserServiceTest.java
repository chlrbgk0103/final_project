package com.example.final_project.service;

import com.example.final_project.dto.UserDto;
import com.example.final_project.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserMapper userMapper;
    @InjectMocks
    UserService userService;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("aaa");
        userDto.setUserPassword("1234");
        userDto.setUserGender("M");
        userDto.setUserAddress("서울시");
        userDto.setUserEmail("aaa@naver.com");
    }

    @Test @DisplayName("회원 등록 ")
    void register() {
        // GIVEN
        doNothing().when(userMapper).insert(any());
        // WHEN
        userService.register(userDto);
        // THEN
        verify(userMapper, times(1)).insert(any());
    }

    @Test @DisplayName(" 회원 번호 조회 ( 아이디 / 패스워드 ) ")
    void findUserNumber() {
        // GIVEN
            doReturn(1L).when(userMapper).selectUserNumber(any(), any());
        // WHEN
        Long foundNumber = userService.findUserNumber("test", "1234");
        // THEN
        assertThat(foundNumber).isEqualTo(1L);
    }
}