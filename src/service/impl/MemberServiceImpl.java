package service.impl;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.MemberDto;
import service.MemberService;

// <controller>와 <dao>의 중계
public class MemberServiceImpl implements MemberService {
	// 인터페이스 클래스 -> 일반클래스
	MemberDao dao = new MemberDaoImpl();

	@Override
	public boolean getId(String id) {
		return dao.getId(id);
	}

	@Override
	public boolean addMember(MemberDto dto) {
		return dao.addMember(dto);
	}

	@Override
	public MemberDto login(String id, String pwd) {
		return dao.login(id, pwd);
	}
	
}
