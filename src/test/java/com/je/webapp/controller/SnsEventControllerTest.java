package com.je.webapp.controller;

import com.je.webapp.WebAppApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) // Spring Junit 모듈
@ActiveProfiles("local")    // profile 정보 셋팅(spring.profiles.active)
@SpringApplicationConfiguration(classes = {WebAppApplication.class})    // configuration 설정
@WebAppConfiguration
//@IntegrationTest("server.port=8080")
public class SnsEventControllerTest {

/*    private Logger logger = Logger.getLogger(getClass());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    SnsEventService snsEventService;

    @Mock
    PointJobService pointJobService;

    @Mock
    IntroInfoService introInfoService;

    @InjectMocks
    SnsEventController snsEventController;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(snsEventController).build();

        System.setProperty("spring.profiles.active", "local");  // 개발환경

    }

    @Test
    public void testSnsEvent() throws Exception {

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/attend/sns_event")
                        .contentType("application/json;charset=UTF-8")
                        .param("gameId", "2048")
                        .param("snsCh", "cyworld")
                        .param("sessionId", "11234234232323111111")
                        .param("eventSeq", "126")
                    );

        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        }*/
}



