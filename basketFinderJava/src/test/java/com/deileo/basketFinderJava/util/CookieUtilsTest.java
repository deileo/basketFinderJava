package com.deileo.basketFinderJava.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CookieUtilsTest {

    @MockBean
    private HttpServletResponse response;

    @Autowired
    private CookieUtils cookieUtils;

    @Test
    public void testShouldReturnCookieIfNameMatches() {
        Cookie cookie = new Cookie("randomName", "10");
        Cookie[] cookies = {cookie};

        assertEquals(Optional.of(cookie), CookieUtils.getCookie(cookies, "randomName"));
    }

    @Test
    public void testShouldReturnEmptyIfCookieNameDoesNotMatch() {
        Cookie cookie = new Cookie("badName", "10");
        Cookie[] cookies = {cookie};

        assertEquals(Optional.empty(), CookieUtils.getCookie(cookies, "randomName"));
    }

    @Test
    public void testShouldAddCookieToResponse() {
        doNothing().when(response).addCookie(any());

        CookieUtils.addCookie(response, "randomName", "10", 3600);
    }

    @Test
    public void testShouldNotDeleteCookieIfCookiesNameIsNotFound() {
        Cookie cookie = new Cookie("badName", "10");
        Cookie[] cookies = {cookie};

        verify(response, never()).addCookie(any());

        CookieUtils.deleteCookie(cookies, response, "randomName");
    }

    @Test
    public void testShouldRestartCookie() {
        Cookie cookie = new Cookie("randomName", "10");
        Cookie[] cookies = {cookie};

        doNothing().when(response).addCookie(any());

        CookieUtils.deleteCookie(cookies, response, "randomName");
    }
}
