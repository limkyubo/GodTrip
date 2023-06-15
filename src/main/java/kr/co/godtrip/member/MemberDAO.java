package kr.co.godtrip.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

		public MemberDAO() {
			System.out.println("----MemberDAO() end");
		}
		
		@Autowired
		SqlSession sqlSession;
		//회원가입
		public void insert(MemberDTO dto) {
			sqlSession.insert("member.insert",dto);
		}
		
		//중복체크 id, email
		public int duplicateId(String id) {
			return sqlSession.selectOne("member.duplicateId",id);
		}
		
		public int duplicateemail(String email) {
			return sqlSession.selectOne("member.duplicateEmail",email);
		}
		
		//로그인
		public MemberDTO loginProc(String id, String passwd) {
		    Map<String, String> paramMap = new HashMap<>();
		    paramMap.put("id", id);
		    paramMap.put("passwd", passwd);
		    return sqlSession.selectOne("member.loginProc", paramMap);
		}
		
		//회원 정보 불러오기
		public MemberDTO select(String s_id) {
			return sqlSession.selectOne("member.select",s_id);
		}
		
		//회원정보 업데이트
		public void update(Map<String, Object>map) {
			 sqlSession.update("member.update",map);
		}
		
		//회원 삭제 
		  public int delete(String s_id, String passwd) {
		        // 아이디와 비밀번호가 일치하는 회원을 삭제하는 로직 작성
		       //값을 2개 받아와야 하기때문에 map을 사용. 
			  // 일치하는 경우 1 아니면 0
			  
			  Map<String, String> paramMap = new HashMap<>();
			    paramMap.put("id", s_id);
			    paramMap.put("passwd", passwd);
			  int cnt= sqlSession.update("member.memberdelete", paramMap);
			return cnt;
		    }
		
		
}
