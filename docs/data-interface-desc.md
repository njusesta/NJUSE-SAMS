# 接口描述

# V0.2 测试版

## 2018/12/02

## 更新历史

| 修改人员          | 日期         | 变更原因                                     | 版本号     |
| ------------- | ---------- | ---------------------------------------- | ------- |
| 黄国钊、周政、陈立 | 2018/12/02 | 初稿                            | V0.2 beta版 |
------

## 基础接口

### 1. getActivityInfo
参数：活动名称  
返回：活动具体信息

### 2. getPersonalInfo
参数：学号  
返回：用户信息

### 3. getActivityPaticipated
参数：学号  
返回：用户参加过的活动表

### 4. getPersonalDevAssessList
参数：学号  
返回：用户的发展测评表

### 5.getPersonalNotification
参数：学号
返回：用户的通知表

### 6.getPage
参数：网页名
返回：网页文件

## 用例确定接口
### 用例1 注册登录
#### 1. logInCheck
参数：账号与密码
返回：T/F

#### 2.latestActivities
参数：无
返回：各活动最新的几项

### 用例2 查看个人信息
#### 3.待定

### 用例3 完善个人信息，账号升级
#### 4. updatePersonalInfo
参数：新的个人信息
返回：T/F

#### 5. applyForUpgrade
参数：升级申请
返回：T/F 

### 用例4 发布活动
#### 6.applyForNewActvt
参数：新的活动信息
返回：T/F

#### 7.handleNewActvtApply
参数：T/F
返回：T/F

### 用例5 浏览查看活动
#### 8. getPage
参数：活动页名
返回：活动页

#### 9. getActivityItems
参数：活动类名(比赛、讲座等)
返回：活动项表

#### 10. getActivityInfo
参数：活动名称
返回：活动具体信息

### 用例6 报名参加活动
#### 11. applyForParticipate
参数：申请信息
返回：T/F

### 用例7 发布项目/比赛招募
同用例4

### 用例8 加入项目/比赛招募
同用例6

### 用例9 查看个人通知与已参加活动表
#### 12. getPersonalNotification
参数：学号
返回：用户通知表

#### 13. getActivityPaticipated
参数：学号
返回：用户参加过的活动表

### 用例10 编辑保存个人发展测评表
待定

### 用例11 审批发展测评表
#### 14. getClassmateList
参数：学号
返回：班长所管理的学生列表

#### 15. judgeDevAssessList
参数：所评学生学号和打分
返回：T/F

### 用例12 用户权限管理(查看与审核)
#### 16.getUpgradeApplicationList
参数：无
返回：升级申请表

#### 17. judgeUpgradeApplication
参数：申请条目信息和T/F
返回：T/F
