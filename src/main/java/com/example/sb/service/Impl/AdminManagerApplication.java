package com.example.sb.service.Impl;

import com.example.sb.model.Entities.Admin;
import com.example.sb.model.dto.AdminDto;
import com.example.sb.model.mappers.Mapper;
import com.example.sb.repo.AdminRepository;
import com.example.sb.repo.CenterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminManagerApplication {
    private final AdminRepository adminRepository;
    private final CenterRepository centerRepository;

    private final Mapper<Admin, AdminDto> adminMapper;

    @Autowired
    public AdminManagerApplication(
            AdminRepository adminRepository,
            CenterRepository centerRepository,
            Mapper<Admin, AdminDto> adminMapper) {
        this.adminRepository = adminRepository;
        this.centerRepository= centerRepository;
        this.adminMapper = adminMapper;
    }
    public List<AdminDto> getAdmins(){
        return adminRepository.findAll().stream().map(adminMapper::mapTo).collect(Collectors.toList());
    }

    public void addNewAdmin(Admin admin) {
        Optional<Admin> adminOptional=adminRepository
                .findByEmail(admin.getEmail());
        if (adminOptional.isPresent())
            throw  new IllegalStateException("email taken");
        adminRepository.save(admin);
    }

    public void deleteAdmin(Long adminid) {
       boolean exists= adminRepository.existsById(adminid);

       if (!exists){
           throw new IllegalStateException("admin with id "+adminid +"does not exists");
       }
       adminRepository.deleteById(adminid);
    }
@Transactional
    public void updateAdmin(Long adminId,
                            String email,
                            String password
                            ) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new IllegalStateException("admin with id "+ adminId +"does not exists"));
        if (email !=null
                && email.length() > 0
                && !Objects.equals(admin.getEmail(),email)){
            Optional<Admin> adminOptional = adminRepository.findByEmail(email);
            if (adminOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            admin.setEmail(email);
        }
        if (password !=null
                && password.length() > 0
                && !Objects.equals(admin.getPassword(),password)){
            admin.setPassword(password);
        }


    }

    public AdminDto getAdminByEmail(String email) {
        Optional<Admin> adminOptional  = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            Admin adminEntity = adminOptional.get();
            return adminMapper.mapTo(adminEntity);
        } else {
            return null;
        }
    }
}
