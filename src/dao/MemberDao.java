package dao;

import dto.MemberDto;

public interface MemberDao {	// prototype
	
	public boolean getId(String id);
	
	public boolean addMember(MemberDto dto);
	
	public MemberDto login(String id, String pwd);
}
