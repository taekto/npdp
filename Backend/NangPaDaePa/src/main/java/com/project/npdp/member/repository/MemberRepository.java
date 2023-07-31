package com.project.npdp.member.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    // 회원 가입
    public void save(Member member){
        em.persist(member);
    }

//    // 회원 조회
//    public Member findOne(Long id){
//        Member member = em.find(Member.class, id);
//        return member;
//    }

    // 이메일로 회원 조회
    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }
}
