package com.je.webapp.service;

import com.je.webapp.Domain.MemberInfo;
import com.je.webapp.repository.jt.GameJtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameService {
    
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GameJtRepository gameJtRepository;


    public Map<String, Object> loginMember(String name) throws Exception {

        Map<String, Object> result = new HashMap<>();

        try {
            List<MemberInfo> memberInfos = new ArrayList<>();
            // 1. 기존 생성된 사용자인지 찾는다.
            String groupName = gameJtRepository.searchMember(name);

            // 2-1. 신규 사용자이면...
            if("new".equals(groupName)) {
                // 2-2-1. 사용자를 추가할 그룹을 조회한다.
                groupName = gameJtRepository.searchMyGroup();
                // 2-2-2. 해당 그룹으로 사용자를 추가한다.
                gameJtRepository.addMember(name, groupName);
            } else {
                // 2-2. 기존 생성된 사용자이면 사용자 정보를 조회한다.
                memberInfos = gameJtRepository.searchMemberInfo(name);
            }

            result.put("gameInfo", memberInfos);
            result.put("group", groupName);

        } catch (Exception e) {
            logger.error("Error Insert member");
            throw e;
        }

        return result;

    }


    public List<MemberInfo> getMemberInfo(String name) throws Exception {

        List<MemberInfo> memberInfos = new ArrayList<>();

        memberInfos = gameJtRepository.searchMemberInfo(name);


        return memberInfos;
    }

    public String insertScore(String name, String group, String game, int score) throws Exception {

        gameJtRepository.addScore(name, group, game, score);

        return "success";
    }
}