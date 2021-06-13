package com.ibegu.dalaoadmin.service;

import com.ibegu.dalaoadmin.domain.Demo;
import com.ibegu.dalaoadmin.domain.DemoExample;
import com.ibegu.dalaoadmin.mapper.DemoMapper;
import com.ibegu.dalaoadmin.req.DemoReq;
import com.ibegu.dalaoadmin.resp.DemoResp;
import com.ibegu.dalaoadmin.utils.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Angus Lan
 * @Date 2021/4/10 3:45
 **/

@Service
public class DemoService {

    // @Autowired
    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list(){


        return demoMapper.selectByExample(null);
        // return demoMapper.selectByExample(new DemoExample());
    }

    public List<DemoResp> list(DemoReq demoReq){

        //创建查询条件 从数据库中返回
        DemoExample demoExample = new DemoExample();
        DemoExample.Criteria criteria = demoExample.createCriteria();
        //条件查询
        if (!ObjectUtils.isEmpty(demoReq.getName())) {
            criteria.andNameLike("%" + demoReq.getName() + "%");
        }
        List<Demo> demoList = demoMapper.selectByExample(demoExample);



        //将demo封装为demoresp

        //法一：循环加单体复制
        // List<DemoResp> demoRespList = new ArrayList<>();
        // for (Demo demo : demoList) {
        //     // DemoResp demoResp = new DemoResp();
        //     // BeanUtils.copyProperties(demo, demoResp);
        //     //单体对象复制
        //     DemoResp demoResp = CopyUtil.copy(demo, DemoResp.class);
        //     // demoResp.setId(123L);
        //     demoRespList.add(demoResp);
        // }

        //法二：列表复制
        List<DemoResp> demoRespList = CopyUtil.copyList(demoList, DemoResp.class);


        return demoRespList;


        // return demoMapper.selectByExample(new DemoExample());
    }





}