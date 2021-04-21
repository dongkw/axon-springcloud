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

#  Scenario: 取消saga
#    Given sagaId:
#      | id | i |
#    When 收到取消事件CancelEvent:
#      | id   | 1 |
#      | data | 2 |
#    Then 发送取消调用外部:
#      | id | 1 |
#    Then 风控成功CmplSuccEvt:
#      | id | 1 |
#    And  合规成功VerfSuccEvt:
#      | id | 1 |
#    Then 发送CancelledCmd:
#      | id   | 1 | 2 |
#      | data | 2 | 4 |


  Scenario: 创建saga
    Given sagaId:
      | id | 1 |
    When 收CreateEvent:
      | id   | 1 |
      | data | 2 |
    Then 发送取消调用外部:
      | id | 1 |

  Scenario: 验证创建saga成功
    Given 收到CreateEvent:
      | id   | 1 |
      | data | 2 |
    When 收到外部请求CmplSuccEvent:
      | id | 1 |
    Then 发送确认命令:
      | id | 1 |


  Scenario: 取消saga
    Given 取消sagaId:
      | id | 1 |
    When 收CancelEvent:
      | id   | 1 |
      | data | 2 |
    Then 发送取消调用外部命令:
      | id | 1 |