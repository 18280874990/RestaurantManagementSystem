#drop table feedback;
#drop table revenue;
#drop table rms_order;
#drop table food;
#drop table account;
#drop table employee;


#员工表,分成管理员 收银员   厨师  服务员
create table employee(
    employeeId        int           not null auto_increment,
    name      varchar(20)   not null,#姓名
    type      char(10)      not null,#种类为admin cashier cook waiter
    num       int           default 0,#cook和waiter使用 表示目前已有的任务数
    sex       boolean       not null, #性别 true 男 false 女
    birth     date          not null,
    salary    double        not null, #薪水
    phone     char(11)      not null, #电话
    workContent  varchar(100)  not null,#工作内容
    primary key(employeeId)
);

#账号表
#其中type为employee或者customer
create table account(
    accountId       int   not null auto_increment,
    accountNum      char(11)    not null unique,
    password      varchar(20)   not null,
    type        char(10)      not null,
    employeeId        int,
    primary key(accountId)
);

#菜品表
#其中image为图片的存储地址
#employee_id为厨师
#menu_type为菜品类型  包括凉菜cold 热菜hot
create table food(
    foodId            int           not null auto_increment,
    name          char(20)      not null unique ,#菜品名称
    sizePrice    varchar(50)   not null,#规格&价格 小small 中medium 大big 无default
    sales         int           not null default 0, #销量
    image         varchar(100)   not null,#图片URL
    description   varchar(100)  not null,#菜品描述
    type          char(10)      not null,#菜品类型
    primary key(foodId)
);

#订单表
create table rms_order(
                      orderId     int           not null, #订单ID 不自增 自己增
                      foodId      int           not null,#菜品
                      accountNum      varchar(20)   not null,#下订单的账号
                      foodNum     int           not null,#数量
                      size        varchar(10)    not null,#尺寸
                      price        double        not null,#价格
                      time         datetime      not null,#时间
                      type         boolean       not null,#订单类型  为true则是堂食  为false则是外送
                      state        int           not null default 0, #订单状态  0已下订单 1已做好 2已送达 3已完成  若为外送则没有2和3 4表示已送出
                      cookId       int           not null,
                      waiterId     int           not null,
                      mealCode    int, #取餐码 堂食需要
                      position     int, #就餐位置 堂食需要
                      phone        char(11),#电话 外送需要
                      addr         varchar(20),#地址 外送需要
                      primary key(orderId, foodId)
);


#营收表 暂时不改
create table revenue(
    revenueId         int           not null auto_increment,
    orderId           int           not null,
    money      double        not null,
    time       datetime      not null,
    primary key(revenueId)

);

#反馈信息表
create table feedback(
    feedbackId   int      not null auto_increment,
    account        char(11)      not null, #顾客电话
    content   varchar(500)  not null, #反馈内容
    time      datetime      not null, #反馈时间
    reply      varchar(500)  ,#回复内容
    replyTime   datetime   ,#回复时间
    primary key(feedbackId)
);



