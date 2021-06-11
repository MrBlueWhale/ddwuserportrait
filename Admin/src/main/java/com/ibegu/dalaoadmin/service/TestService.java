package com.ibegu.dalaoadmin.service;

import com.ibegu.dalaoadmin.domain.Test;
import com.ibegu.dalaoadmin.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/4/10 3:45
 **/

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list(){

        return testMapper.list();
    }



}
