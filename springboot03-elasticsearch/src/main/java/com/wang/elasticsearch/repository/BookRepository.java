package com.wang.elasticsearch.repository;

import com.wang.elasticsearch.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 泛型的类型为<实体类,索引类型>
 */
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {
    /**
     * 这边的方法名要注意和属性名对应,Book的属性名为bookname,对应的方法名中的属性为Bookname而不是BookName
     * @param bookname
     * @return
     */
    public List<Book> findByBooknameLike(String bookname);
}
