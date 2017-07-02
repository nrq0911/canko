package com.canko.mapper;

import com.canko.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by nrq on 2017/6/17.
 */
@Mapper
public interface MemberMapper {

    Member getMemberById(@Param("id") int id);

    void addMember(Member member);

}
