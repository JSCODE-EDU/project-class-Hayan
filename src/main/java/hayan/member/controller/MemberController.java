package hayan.member.controller;

import hayan.member.domain.Member;
import hayan.member.dto.RequestDto;
import hayan.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody RequestDto requestDto) {

        memberService.signUp(requestDto.getEmail(), requestDto.getPassword());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void logIn(@RequestBody RequestDto requestDto) {

        memberService.logIn(requestDto.getEmail(), requestDto.getPassword());
    }
}
