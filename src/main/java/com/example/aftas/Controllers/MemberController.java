package com.example.aftas.Controllers;

import com.example.aftas.model.dto.MemberDto;
import com.example.aftas.Services.Impl.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberDto> getAllMembers(){
        return memberService.getAllMembers();
    }
    @GetMapping("{num}")
    public ResponseEntity<Object> getMemberByNum(@PathVariable("num") Integer num){
        return memberService.getMemberbyNum(num);
    }
}
