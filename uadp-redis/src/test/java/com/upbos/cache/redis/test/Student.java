package com.upbos.cache.redis.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * <b>Application name：</b> Student.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年10月22日 02:25 <br>
 * <b>author：</b> <a href="mailto:wangjz@miyzh.com"> wangjz </a>
 * <b>version：</b>V2.0.0
 */
public class Student implements Serializable{
    private String id;
    private String name;
    private String sex;
    private String age;
    private String idNo;
    private String addr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        Student student = new Student();
        student.setId("100110");
        student.setAddr("多大点事多发达手动阀手动阀手动阀大法师大厦发送东方四大发生的发生的阿斯蒂芬");
        student.setAge("101");
        student.setIdNo("199292992929292929222");
        student.setName("真时尚");
        //JSON.toJSONString(student);

        //ObjectMapper mapper = new ObjectMapper();
        //try {
            //String s1 = mapper.writeValueAsString(student);
            JSON.toJSONString(student);
            //System.out.println(s1);
//        } catch (JsonProcessingException e1) {
//            e1.printStackTrace();
//        }
        long e = System.currentTimeMillis();
        System.out.println(e-s); //291

    }
}
