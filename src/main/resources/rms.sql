drop table rms_feedback;
drop table rms_revenue;
drop table rms_order;
drop table rms_menu;
drop table rms_user;
drop table rms_customer;
drop table rms_employee;


#员工表,分成管理员 收银员   厨师  服务员
#type种类为admin cashier cook waiter
#sex true 男 false 女
create table rms_employee(
    employee_id        int           not null auto_increment,
    employee_name      varchar(20)   not null,
    employee_type      char(10)      not null,
    employee_sex       boolean,
    employee_salary    double,
    employee_phone     char(11),
    constraint  re_pk  primary key(employee_id)
);

#insert into rms_employee(employee_name, employee_type) values('IAmAdmin', 'admin');
#insert into rms_employee(employee_name, employee_type) values('IAmCashier', 'cashier');
#insert into rms_employee(employee_name, employee_type) values('IAmCook', 'cook');
#insert into rms_employee(employee_name, employee_type) values('IAmWaiter', 'waiter');

#顾客表
create table rms_customer(
    customer_id        int           not null auto_increment,
    customer_name      varchar(20)   not null,
    customer_sex       boolean,
    customer_phone     char(11),
    customer_addr      varchar(30),
    constraint  rc_pk  primary key(customer_id)
);

#insert into rms_customer(customer_name) values('Chiuchieh Hwang');

#账号表
#其中user_people为employee或者customer
create table rms_user(
    user_account       varchar(20)   not null,
    user_password      varchar(20)   not null,
    user_people        char(10)      not null,
    employee_id        int,
    customer_id        int,
    constraint  ru_pk  primary key(user_account),
    constraint  ru_re_fk  foreign key(employee_id) references rms_employee(employee_id),
    constraint  ru_rc_fk  foreign key(customer_id) references rms_customer(customer_id)
);

#insert into rms_user(user_account, user_password, user_people, employee_id) values('admin', '123456', 'employee',
    #(select employee_id from rms_employee where employee_type = 'admin'));
#insert into rms_user(user_account, user_password, user_people, employee_id) values('cashier', '123456', 'employee',
    #(select employee_id from rms_employee where employee_type = 'cashier'));
#insert into rms_user(user_account, user_password, user_people, employee_id) values('cook', '123456', 'employee',
    #(select employee_id from rms_employee where employee_type = 'cook'));
#insert into rms_user(user_account, user_password, user_people, employee_id) values('waiter', '123456', 'employee',
    #(select employee_id from rms_employee where employee_type = 'waiter'));
#insert into rms_user(user_account, user_password, user_people, customer_id) values('system', '123456', 'customer',
    #(select customer_id from rms_customer where customer_name = 'Chiuchieh Hwang'));

#菜品表
#其中image为图片的存储地址
#employee_id为厨师
#menu_type为菜品类型  包括凉菜cold 热菜hot
create table rms_menu(
    menu_id            int           not null auto_increment,
    menu_name          char(20)      not null,
    menu_price         double        not null,
    menu_sales         int           not null default 0,
    menu_image         varchar(30)   not null,
    menu_description   varchar(100)  not null,
    menu_type          char(10)      not null,
    employee_id        int           not null,
    constraint  rm_pk  primary key(menu_id),
    constraint  rm_re_fk  foreign key(employee_id) references rms_employee(employee_id)
);
insert into rms_menu(menu_name, menu_price, menu_image, menu_description, menu_type, employee_id)
    values('凉拌肉', 25, '凉拌肉.jpg', '这是凉拌肉，很好吃', 'cold', (select employee_id from rms_employee where employee_type = 'cook'));


#订单表
#account是下订单的账号
#type为订单类型  为true则是堂食  为false则是外送
#state为订单状态  0已下订单 1已做好 2已送达 3已完成  若为外送则没有2和3 4表示已送出
create table rms_order(
    order_id           int           not null,
    menu_id            int           not null,
    user_account       varchar(20)   not null,
    order_menu_num     int           not null default 1,
    order_time         datetime      not null,
    order_type         boolean       not null,
    order_state        int           not null default 0,
    order_meal_code    int,
    order_phone        char(11),
    order_addr         varchar(20),
    constraint  ro_pk  primary key(order_id, menu_id),
    constraint  ro_rm_fk  foreign key(menu_id) references rms_menu(menu_id),
    constraint  ro_ru_fk  foreign key(user_account) references rms_user(user_account)
);


#营收表
create table rms_revenue(
    revenue_id         int           not null auto_increment,
    order_id           int           not null,
    revenue_money      double        not null,
    revenue_time       datetime      not null,
    constraint  rr_pk  primary key(revenue_id),
    constraint  rr_ro_fk  foreign key(order_id) references rms_order(order_id)
);

#反馈信息表
create table rms_feedback(
    feedback_id        int           not null auto_increment,
    customer_id        int           not null,
    feedback_content   varchar(500)  not null,
    feedback_time      datetime      not null,
    constraint  rf_pk  primary key(feedback_id),
    constraint  rf_rc_fk  foreign key(customer_id) references rms_customer(customer_id)
);



