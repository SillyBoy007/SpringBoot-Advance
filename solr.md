# solr学习笔记

# 一.需求分析

## 1.1 场景描述

​	在一些大型门户网站、电子商务网站等都需要站内搜索功能，使用传统的数据库查询方式实现搜索无法满足一些高级的搜索需求，比如：搜索速度要快、搜索结果按相关度排序、搜索内容格式不固定等，这里就需要使用全文检索技术实现搜索功能。 

**1.1.1 使用lucene实现**

​	单独使用Lucene实现站内搜索需要开发的工作量较大，主要表现在：索引维护、索引性能优化、搜索性能优化等，因此不建议采用。 

**1.1.2 使用solr实现**

​	基于Solr实现站内搜索扩展性较好并且可以减少程序员的工作量，因为Solr提供了较为完备的搜索引擎解决方案，因此在门户、论坛等系统中常用此方案。

 ## 1.2 什么是solr

​	Solr 是Apache下的一个顶级开源项目，采用Java开发，它是基于Lucene的全文搜索服务器。Solr提供了比Lucene更为丰富的查询语言，同时实现了可配置、可扩展，并对索引、搜索性能进行了优化。  

​	Solr可以独立运行，运行在Jetty、Tomcat等这些Servlet容器中，Solr 索引的实现方法很简单，用 POST 方法向 Solr 服务器发送一个描述 Field 及其内容的 XML 文档，Solr根据xml文档添加、删除、更新索引 。Solr 搜索只需要发送 HTTP GET 请求，然后对 Solr 返回Xml、json等格式的查询结果进行解析，组织页面布局。Solr不提供构建UI的功能，Solr提供了一个管理界面，通过管理界面可以查询Solr的配置和运行情况。 

**Solr与Lucene的区别：** 

​	Lucene是一个开放源代码的全文检索引擎工具包，它不是一个完整的全文检索引擎，Lucene提供了完整的查询引擎和索引引擎，目的是为软件开发人员提供一个简单易用的工具包，以方便的在目标系统中实现全文检索的功能，或者以Lucene为基础构建全文检索引擎。 

​	Solr的目标是打造一款企业级的搜索引擎系统，它是一个搜索引擎服务，可以独立运行，通过Solr可以非常快速的构建企业的搜索引擎，通过Solr也可以高效的完成站内搜索功能。 

![](image/17.png)

# 二.Solr安装及配置

## 2.1 Solr的下载

从Solr官方网站（http://lucene.apache.org/solr/ ）下载Solr4.10.3，根据Solr的运行环境，Linux下需要下载lucene-4.10.3.tgz，windows下需要下载lucene-4.10.3.zip。

Solr使用指南可参考：https://wiki.apache.org/solr/FrontPage。

## 2.2 Solr的文件夹结构

将solr-4.10.3.zip解压：

![1547132122667](image/18.png)

1. bin：solr的运行脚本

2. contrib：solr的一些贡献软件/插件，用于增强solr的功能。

3. dist：该目录包含build过程中产生的war和jar文件，以及相关的依赖文件。

4. docs：solr的API文档

5. example：solr工程的例子目录：

   * example/solr：该目录是一个包含了默认配置信息的Solr的Core目录。

   * example/multicore：该目录包含了在Solr的multicore中设置的多个Core目录。 

   * example/webapps：该目录中包括一个solr.war，该war可作为solr的运行实例工程。

     licenses：solr相关的一些许可信息

## 2.3 运行环境

solr 需要运行在一个Servlet容器中，Solr4.10.3要求jdk使用1.7以上，Solr默认提供Jetty（java写的Servlet容器），本教程使用Tocmat作为Servlet容器，环境如下： 

Solr：Solr4.10.3

Jdk：jdk1.7.0_72

Tomcat：apache-tomcat-7.0.53

## 2.4 Solr整合tomcat

**2.4.1 Solr Home与SolrCore**

创建一个Solr home目录，SolrHome是Solr运行的主目录，目录中包括了运行Solr实例所有的配置文件和数据文件，Solr实例就是SolrCore，一个SolrHome可以包括多个SolrCore（Solr实例），每个SolrCore提供单独的搜索和索引服务。

example\solr是一个solr home目录结构，如下： l

![1547132494930](image/19.png)

上图中“collection1”是一个SolrCore（Solr实例）目录 ，目录内容如下所示： 

![1547132518560](image/20.png)

**说明：**

* collection1：叫做一个Solr运行实例SolrCore，SolrCore名称不固定，一个solr运行实例对外单独提供索引和搜索接口。

* solrHome中可以创建多个solr运行实例SolrCore。
* 一个solr的运行实例对应一个索引目录。
* conf是SolrCore的配置文件目录 。
* data目录存放索引文件需要创建

**2.4.2 整合步骤**

1. 安装tomcat。D:\temp\apache-tomcat-7.0.53

2. 把solr的war包复制到tomcat 的webapp目录下。把\solr-4.10.3\dist\solr-4.10.3.war复制到D:\temp\apache-tomcat-7.0.53\webapps下。改名为solr.war

3. solr.war解压。使用压缩工具解压或者启动tomcat自动解压。解压之后删除solr.war

4. 把\solr-4.10.3\example\lib\ext目录下的所有的jar包添加到solr工程中

5. 配置solrHome和solrCore。

   * 创建一个solrhome（存放solr所有配置文件的一个文件夹）。\solr-4.10.3\example\solr目录就是一个标准的solrhome。

   * 把\solr-4.10.3\example\solr文件夹复制到D:\temp\0108路径下，改名为solrhome，改名不是必须的，是为了便于理解。 

   * 在solrhome下有一个文件夹叫做collection1这就是一个solrcore。就是一个solr的实例。一个solrcore相当于mysql中一个数据库。Solrcore之间是相互隔离。 

     * 在solrcore中有一个文件夹叫做conf，包含了索引solr实例的配置信息。 

     * 在conf文件夹下有一个solrconfig.xml。配置实例的相关信息。**如果使用默认配置可以不用做任何修改。**

       **Xml的配置信息：**

       **Lib**：solr服务依赖的扩展包，默认的路径是collection1\lib文件夹，如果没有就创建一个

       **dataDir**：配置了索引库的存放路径。默认路径是collection1\data文件夹，如果没有data文件夹，会自动创建。

       requestHandler：

       ![img](image/21.png) 

       ![img](image/22.png)

       



