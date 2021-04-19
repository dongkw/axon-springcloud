@test
Feature: 指令相关

  description: 指令指令指令指令指令指令指令指令指令指令指令指令指令指令指令指令

  Background:


  Scenario: 创建
    When 收到命令:
      | id   | 1 |
      | data | 2 |
    Then 发布事件:
      | id   | 1 |
      | data | 2 |

  Scenario: 取消
    Given 创建以后:
      | id   | 1 | 2 |
      | data | 2 | 3 |
    When 取消
      | id   | 2 | 1 |
      | data | 4 | 2 |
    Then 取消后
      | id   | 1 | 2 |
      | data | 2 | 4 |