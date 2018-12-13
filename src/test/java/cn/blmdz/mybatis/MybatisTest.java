package cn.blmdz.mybatis;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class MybatisTest {

    public static void main(String[] args) throws IOException {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        DataSource dataSource = new PooledDataSource(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/db_test?autoReconnect=true&amp;useUnicode=true&amp;charaterEncoding=utf-8&amp;useSSL=false",
                "root",
                "root");
        Environment environment = new Environment("ddd", transactionFactory, dataSource);
        Configuration config = new Configuration(environment);
        config.addMapper(BlogMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);

//        InputStream inputStream = Resources.getResourceAsStream("cn/blmdz/mybatis/mybatis-config.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        

//        SqlSession session = sqlSessionFactory.openSession();
//        try {
//            BlogMapper mapper = session.getMapper(BlogMapper.class);
//            Blog blog = mapper.selectBlog(1L);
//            System.out.println(blog);
//        } finally {
//            session.close();
//        }

        A.doit(sqlSessionFactory, new CallBack(){
            @Override
            public void doit(SqlSession session) {
                BlogMapper mapper = session.getMapper(BlogMapper.class);
                Blog blog = mapper.selectBlog(1L);
                System.out.println(blog);
            }
        });
        

         SqlSessionManager m = SqlSessionManager.newInstance(Resources.getResourceAsStream("cn/blmdz/mybatis/mybatis-config.xml"));
         Blog blog = m.getMapper(BlogMapper.class).selectBlog(1L);
         System.out.println(blog);
        
    }
    
    static class A {
        static void doit(SqlSessionFactory sqlSessionFactory, CallBack callBack) {

            SqlSession session = sqlSessionFactory.openSession();
            try {
                callBack.doit(session);
            } finally {
                session.close();
            }
        }
    }
    
}
interface CallBack {
    void doit(SqlSession session);
}
