package com.example.aftas.Controllers;

import com.example.aftas.model.Dto.MemberDto;
import com.example.aftas.Services.Impl.MemberService;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.Dto.Requests.MemberRequest;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.mappers.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
@CrossOrigin("http://localhost:4200")
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
    public List<MemberDto> getMemberByName(@PathVariable("name") String name){
        return memberService.getMemberByName(name);
    }
    @GetMapping("/fname/{fname}")
    public List<MemberDto> getMemberByFamilyName(@PathVariable("fname") String fname){
        return memberService.getMemberByFamilyName(fname);
    }
    @GetMapping("/code/{code}")
    public List<RankingDto> findByCode(@PathVariable("code") String code){
        return memberService.getCompetitionMembers(code);
    }
    @PostMapping
    public MemberDto saveMember(@RequestBody MemberRequest memberRequest){
            return memberService.registerMember(memberRequest);
    }
    @PutMapping
    public MemberDto updateMember(@RequestBody MemberRequest memberRequest){
        return memberService.updateMember(memberRequest);
    }

    @DeleteMapping("/{identity}")
    public boolean deleteMember(@PathVariable("identity") String identityNumber){
        System.out.println("=========================================");
        System.out.println("identity : "+identityNumber);
        System.out.println("=========================================");
        MemberDto memberDto = memberService.getMemberByIdentityNumber(identityNumber);
        System.out.println("=========================================");
        System.out.println(memberDto);
        System.out.println("=========================================");
        if (memberDto != null){
            memberService.deleteMember(memberMapper.mapFrom(memberDto));
            return true;
        }else {
            return false;
        }
    }
}
