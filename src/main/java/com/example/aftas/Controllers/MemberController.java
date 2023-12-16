package com.example.aftas.Controllers;

import com.example.aftas.model.Dto.MemberDto;
import com.example.aftas.Services.Impl.MemberService;
import com.example.aftas.model.Dto.Requests.MemberRequest;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.mappers.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    private final MemberService memberService;
    private final Mapper<Member, MemberDto> memberMapper;

    public MemberController(MemberService memberService, Mapper<Member, MemberDto> memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }


    @GetMapping
    public List<MemberDto> getAllMembers(){
        return memberService.getAllMembers();
    }
    @GetMapping("/identity/{identity}")
    public MemberDto getMemberByIdentityNumber(@PathVariable("identity") String identity){
        return memberService.getMemberByIdentityNumber(identity);
    }
    @GetMapping("/name/{name}")
    public MemberDto getMemberByName(@PathVariable("name") String name){
        return memberService.getMemberByName(name);
    }
    @GetMapping("/fname/{fname}")
    public MemberDto getMemberByFamilyName(@PathVariable("fname") String fname){
        return memberService.getMemberByFamilyName(fname);
    }
    @PostMapping
    public MemberDto saveMember(@RequestBody MemberRequest memberRequest){
        MemberDto memberDto = memberService.getMemberByIdentityNumber(memberRequest.getIdentityNumber());
        if (memberDto == null){
            return memberService.registerMember(memberRequest);
        }
        else{
            System.out.println("member Already existing");
            return null;
        }
    }
    @PutMapping
    public MemberDto updateMember(@RequestBody MemberRequest memberRequest){
        return memberService.updateMember(memberRequest);
    }

    @DeleteMapping("/{identity}")
    public boolean deleteMember(@PathVariable("identity") String identityNumber){
        MemberDto memberDto = memberService.getMemberByIdentityNumber(identityNumber);
        if (memberDto != null){
            memberService.deleteMember(memberMapper.mapFrom(memberDto));
            return true;
        }else {
            return false;
        }
    }
}
