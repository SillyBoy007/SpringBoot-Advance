package com.wang.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@EnableWebSecurity //开启基于WebSecurity的注解(已经开启了@Configuration)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll() //让所有人可以访问首页
        .antMatchers("/level1/**").hasRole("VIP1")
        .antMatchers("/level2/**").hasRole("VIP2")
        .antMatchers("/level3/**").hasRole("VIP3");
        //访问测试

        //开启自动配置的登陆功能,如果没有登录，则会来到登录页面
        //SpringSecurity自动处理的请求
        //默认规则
        //1、/login 来到登录页
        //2、重定向到 /login?error 表示登录失败
        //3、更多信息
        //4、默认post形式的/login代表处理登录
        //5、一旦定制loginPage,那么loginPage的post请求就是登录
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin")/*.loginProcessingUrl("/login")*/;



        //开启自动配置的注销功能
        //默认规则
        //1.访问/logout表示用户注销,清空session
        //2.注销成功会返回/login?logout页面

        http.logout().logoutSuccessUrl("/"); //注销以后来到首页


        //开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        //登陆成功后，将cookie发给浏览器保存,以后登陆带上这个cookie，只要通过检查就可以免登陆
        //点击注销会删除cookie
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //auth.jdbcAuthentication()
        //内存用户验证
    /*    auth.inMemoryAuthentication().withUser("wang").password("123456").roles("VIP1","VIP2").and()
                .withUser("xia").password("654321").roles("VIP2","VIP3");  //表单提交的时候密码是以密文匹配,会报错
*/
        auth.inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("wang").password("123456").roles("VIP1","VIP2").and()
                .withUser("yun").password("123456").roles("VIP3"); //表单提交的时候密码是以明文匹配

    }
}
