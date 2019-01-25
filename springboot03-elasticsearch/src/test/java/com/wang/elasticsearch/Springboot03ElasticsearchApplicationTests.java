package com.wang.elasticsearch;


import com.wang.elasticsearch.bean.Book;
import com.wang.elasticsearch.bean.People;

import com.wang.elasticsearch.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ElasticsearchApplicationTests {
    @Autowired
    JestClient jestClient;
    @Autowired
    BookRepository bookRepository;
    @Test
    public void JestSave() {
        //1,给ES中的索引保存一个文档
        People people = new People();
        people.setId(1);
        people.setAge(22);
        people.setGender("男");
        people.setName("王鑫");

        //构建一个索引功能
        Index index = new Index.Builder(people).index("school").type("qinghua").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void JestSearch(){
        //查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"name\" : \"王\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("school").addType("qinghua").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void Booksave(){
        Book book = new Book();
        book.setId(1);
        book.setBookname("Java开发");
        book.setAuthor("Wang");
        bookRepository.index(book);
    }

    @Test
    public void Bookfind(){

        for (Book book: bookRepository.findByBooknameLike("开")){
            System.out.println(book);
        }

    }
}

