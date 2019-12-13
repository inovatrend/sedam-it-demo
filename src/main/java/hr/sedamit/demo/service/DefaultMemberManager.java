package hr.sedamit.demo.service;

import hr.sedamit.demo.dao.MemberRepository;
import hr.sedamit.demo.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DefaultMemberManager implements MemberManager {

    private MemberRepository repository;

    public DefaultMemberManager(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    @Override
    public Optional<Member> getMember(Long memberId) {
        return repository.findById(memberId);
    }

    @Override
    public Member save(Member member) {
        Member savedMember = repository.save(member);
        return savedMember;
    }

    @Override
    public void delete(Long memberId) {
        repository.deleteById(memberId);
    }

    @PostConstruct
    public void init(){
        log.info("Default member manager ready");
    }
}
