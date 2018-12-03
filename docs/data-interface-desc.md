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

### 4. getPersonalDevAssessTable
参数：学号  
返回：用户的发展测评表

### 5.getPage
参数：网页名
返回：网页文件

## 用例确定接口
### 用例1
#### 1. logInCheck
参数：账号与密码
返回：T/F

#### 2.latestActivities
参数：无
返回：各活动最新的几项

### 用例2
#### 3.待定

### 用例3
#### 4. updatePersonalInfo
参数：新的个人信息
返回：T/F

#### 5. applyForUpgrade
参数：升级申请
返回：T/F

### 用例4
#### 6.applyForNewActvt
参数：新的活动信息
返回：T/F

#### 7.handleNewActvtApply
参数：T/F
返回：T/F

### 用例5
#### 8.