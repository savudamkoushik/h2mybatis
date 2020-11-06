package com.example.h2mybatis.Controller;

import com.example.h2mybatis.Entity.StudReq;
import com.example.h2mybatis.Entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@RestController
public class MyBatisCntroller {
    private SqlSession getSqlSession() throws IOException{
        Reader reader= Resources.getResourceAsReader("dbConfig/SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory.openSession();
    }
    @PostMapping("/student")
    public ResponseEntity<Student> postStudent(@RequestBody StudReq studReq) throws IOException {
        SqlSession session =getSqlSession();
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
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) throws IOException {
        SqlSession session=getSqlSession();
        Student student=session.selectOne("Student.getById",id);
        return new ResponseEntity(student,HttpStatus.ACCEPTED);

    }
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() throws IOException {
        SqlSession session=getSqlSession();
        List<Student> list=session.selectList("Student.getAll");
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody StudReq studentRequest,@PathVariable int id) throws IOException{
        SqlSession session=getSqlSession();
        Student stud=session.selectOne("Student.getById",id);
        stud.setName(studentRequest.getName());
        stud.setBranch(studentRequest.getBranch());
        stud.setEmail(studentRequest.getEmail());
        session.update("Student.updateStudent",stud);
        session.commit();
        session.close();
        return new ResponseEntity<>(stud,HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") int theId) throws IOException{
        SqlSession session=getSqlSession();
        session.delete("Student.deleteStudent",theId);
        session.commit();
        session.close();
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
