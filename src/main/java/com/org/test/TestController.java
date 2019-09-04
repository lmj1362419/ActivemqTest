package com.org.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author lmj
 * @Date 2019/9/3 14:27
 */
@RestController
public class TestController {
    @Autowired
    private MongoTemplate mongoTemplate;
    @RequestMapping(value = "test",method = RequestMethod.GET )
    public String test(){
        Test test = new Test();
        test.setId(1l);
        test.setName("张");
        test.setAge(11);
        mongoTemplate.save(test);
        return "成功";
    }
    @RequestMapping(value = "test2",method = RequestMethod.POST )
    public String test2(@RequestBody Test test){
        Query query = new Query();
        Update update = Update.update("name",test.getName()).setOnInsert("id",test.getId()).set("age", test.getAge());
        mongoTemplate.updateFirst(query,update,Test.class);
        return "成功";
    }
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(@RequestBody Test test){
        mongoTemplate.save(test);
        return "成功";
    }
    @RequestMapping(value = "list",method = RequestMethod.GET )
    public List<Test> list(){

        Query query = new Query();

        return  mongoTemplate.find(query, Test.class);
    }
    @RequestMapping(value = "list2",method = RequestMethod.POST )
    public List<Test> list2(@RequestBody Param param){

        Query query = new Query();
        Criteria min = Criteria.where("id").gte(param.getMin());
        Criteria max = Criteria.where("id").lte(param.getMax());
        query.addCriteria(new Criteria().andOperator(min,max));

        return  mongoTemplate.find(query, Test.class);
    }

}
