package com.sparta.springsecurity.security;

import com.sparta.springsecurity.entity.User;
import com.sparta.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service    //어노테이션을 통해 빈으로 등록
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    // 데이터베이스에 접근을 해서 유저객체를 가져온 다음에 검증을 하는 부분

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("UserDetailsServiceImpl.loadUserByUsername : " + username);

        User user = userRepository.findByUsername(username) //userRepository와 Details를 연결해서 유저를 조회
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new UserDetailsImpl(user, user.getUsername(), user.getPassword());
    }

}