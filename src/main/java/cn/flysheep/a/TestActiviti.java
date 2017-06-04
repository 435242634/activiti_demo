package cn.flysheep.a;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * 模拟 Activiti 工作流框架执行
 * Created by yanglunyi on 2017/6/4.
 */
public class TestActiviti {

  /**
   * 取得流程引擎，且自动创建 Activiti 涉及的数据库和表
   */
  @Test
  public void createProcessEngineByCode() {
    // 通过代码形式创建
    // 1. 取得 ProcessEngineConfiguration 对象
    ProcessEngineConfiguration engineConfiguration =
        ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
    // 2. 设置数据库连接属性
    engineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
    engineConfiguration.setJdbcUrl(
        "jdbc:mysql://localhost:3306/activitiDB?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8");
    engineConfiguration.setJdbcUsername("root");
    engineConfiguration.setJdbcPassword("123456");
    // 3. 设置创建表的策略（当没有表时自动创建表）
    engineConfiguration
        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    // 4. 通过 ProcessEngineConfiguration 对象创建 ProcessEngine 对象
    ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
    System.out.println("流程引擎创建成功！");
  }

  /**
   * 通过加载 activiti.cfg.xml 获取流程引擎和自动创建数据库及表
   */
  @Test
  public void createProcessEngineByConfig() {
    // 从类路径中查找资源，activiti.cfg.xml 文件名可以自定义
    ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration
        .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
    System.out.println("流程引擎创建成功！");
  }

  /**
   * 通过 ProcessEngines 来获取默认的流程引擎
   */
  @Test
  public void createProcessEngineByEngine() {
    // 默认会加载类路径下的 activiti.cfg.xml
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    System.out.println("流程引擎创建成功！");
  }
}
