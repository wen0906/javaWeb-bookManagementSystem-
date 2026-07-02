CREATE DATABASE IF NOT EXISTS book_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE book_management;

DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS book_category;

CREATE TABLE book_category (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
    name VARCHAR(50) NOT NULL UNIQUE COMMENT '分类名称',
    description VARCHAR(200) DEFAULT NULL COMMENT '分类描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书分类表';

CREATE TABLE book (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '图书ID',
    isbn VARCHAR(20) NOT NULL UNIQUE COMMENT 'ISBN编号',
    title VARCHAR(100) NOT NULL COMMENT '书名',
    author VARCHAR(50) NOT NULL COMMENT '作者',
    publisher VARCHAR(100) DEFAULT NULL COMMENT '出版社',
    publish_date DATE DEFAULT NULL COMMENT '出版日期',
    category_id INT NOT NULL COMMENT '分类ID',
    price DECIMAL(10,2) NOT NULL COMMENT '价格',
    stock INT DEFAULT 0 COMMENT '库存数量',
    description TEXT DEFAULT NULL COMMENT '图书描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (category_id) REFERENCES book_category(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书表';

INSERT INTO book_category (name, description) VALUES
('计算机', '计算机科学与技术相关书籍'),
('文学小说', '各类文学作品和小说'),
('历史传记', '历史书籍和人物传记'),
('经济管理', '经济学和管理学书籍'),
('艺术设计', '艺术、设计类书籍');

INSERT INTO book (isbn, title, author, publisher, publish_date, category_id, price, stock, description) VALUES
('9787111544937', 'Java编程思想', 'Bruce Eckel', '机械工业出版社', '2017-06-01', 1, 109.00, 50, 'Java学习经典教材'),
('9787111407010', '深入理解计算机系统', 'Randal E.Bryant', '机械工业出版社', '2016-01-01', 1, 139.00, 30, '计算机系统导论经典'),
('9787532772460', '活着', '余华', '上海文艺出版社', '2012-08-01', 2, 39.00, 100, '当代文学经典'),
('9787020142235', '百年孤独', '加西亚·马尔克斯', '人民文学出版社', '2011-06-01', 2, 55.00, 80, '魔幻现实主义代表作'),
('9787508653883', '明朝那些事儿', '当年明月', '浙江人民出版社', '2017-05-01', 3, 188.00, 45, '通俗历史读物'),
('9787111414346', '经济学原理', '曼昆', '机械工业出版社', '2015-07-01', 4, 99.00, 60, '经济学入门教材'),
('9787115411370', '设计心理学', '唐纳德·诺曼', '中信出版社', '2015-10-01', 5, 89.00, 35, '设计领域经典著作');