package com.example.h2mybatis.Controller;

import com.example.h2mybatis.Entity.StudReq;
import com.example.h2mybatis.Entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;

@RestController
public class MyBatisCntroller {
    @PostMapping("/student")
    public ResponseEntity<Student> postStudent(@RequestBody StudReq studReq) throws IOException {
        Reader reader= Resources.getResourceAsReader("dbConfig/SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        Student stud=Student.builder()
                .name(studReq.getName())
                .branch(studReq.getBranch())
                .email(studReq.getEmail())
                .build();
        session.insert("Student.insertStudent",stud);
        session.commit();
        session.close();
        return new ResponseEntity<>(stud, HttpStatus.ACCEPTED);
    }
}
