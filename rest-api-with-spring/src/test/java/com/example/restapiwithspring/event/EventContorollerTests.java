package com.example.restapiwithspring.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventContorollerTests {

    /**
     * 참고로 MockMvc를 주입 받을 때 mvc에 빨간줄이 나올 수 있는데 신경 쓰지 않아도 된다.
     * <p>
     * 만약 시큐리티의 인증과 인가 설정이 안되어있는데 Security 또는 Oauth 라이브러리를 추가했다면 주석처리 해주자.
     */
    @Autowired
    MockMvc mockMvc;


//    @Test
//    public void test() throws Exception {
//        mockMvc.perform(post("/api/events/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaTypes.HAL_JSON)
//                )
//                .andExpect(status().isCreated());
//    }


    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @Test
    public void createEnent() throws Exception {
        Event event = Event.builder()
                .name("Spring")
                .description("REST API Development with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
                .closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
                .beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
                .endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타텁 팩토리")
                .build();
        event.setId(10);
        Mockito.when(eventRepository.save(event)).thenReturn(event);


        mockMvc.perform(post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,MediaTypes.HAL_JSON_VALUE))
        ;
    }
}
