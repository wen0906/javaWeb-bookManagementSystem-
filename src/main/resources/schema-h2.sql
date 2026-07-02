CREATE TABLE IF NOT EXISTS book_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200) DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publisher VARCHAR(100) DEFAULT NULL,
    publish_date DATE DEFAULT NULL,
    category_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    description TEXT DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES book_category(id) ON DELETE CASCADE
);

MERGE INTO book_category (id, name, description) KEY(id) VALUES
(1, '计算机', '计算机科学与技术相关书籍'),
(2, '文学小说', '各类文学作品和小说'),
(3, '历史传记', '历史书籍和人物传记'),
(4, '经济管理', '经济学和管理学书籍'),
(5, '艺术设计', '艺术、设计类书籍');

MERGE INTO book (id, isbn, title, author, publisher, publish_date, category_id, price, stock, description) KEY(id) VALUES
(1, '9787111544937', 'Java编程思想', 'Bruce Eckel', '机械工业出版社', '2017-06-01', 1, 109.00, 50, 'Java学习经典教材'),
(2, '9787111407010', '深入理解计算机系统', 'Randal E.Bryant', '机械工业出版社', '2016-01-01', 1, 139.00, 30, '计算机系统导论经典'),
(3, '9787532772460', '活着', '余华', '上海文艺出版社', '2012-08-01', 2, 39.00, 100, '当代文学经典'),
(4, '9787020142235', '百年孤独', '加西亚·马尔克斯', '人民文学出版社', '2011-06-01', 2, 55.00, 80, '魔幻现实主义代表作'),
(5, '9787508653883', '明朝那些事儿', '当年明月', '浙江人民出版社', '2017-05-01', 3, 188.00, 45, '通俗历史读物'),
(6, '9787111414346', '经济学原理', '曼昆', '机械工业出版社', '2015-07-01', 4, 99.00, 60, '经济学入门教材'),
(7, '9787115411370', '设计心理学', '唐纳德·诺曼', '中信出版社', '2015-10-01', 5, 89.00, 35, '设计领域经典著作');