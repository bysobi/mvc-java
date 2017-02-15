DROP DATABASE IF EXISTS
  `test`;
CREATE DATABASE `test` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
use `test`;

--
-- Структура таблицы `answer`
--



CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `questionId` int(11) NOT NULL,
  `answerNumber` int(11) NOT NULL,
  `textOfAnswer` varchar(50) NOT NULL,
  `flag` tinyint(1) NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- СВЯЗИ ТАБЛИЦЫ `answer`:
--   `questionId`
--       `question` -> `id`
--


--
-- Структура таблицы `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `textOfQuestion` varchar(300) CHARACTER SET utf32 NOT NULL,
  `complexity` int(3) NOT NULL COMMENT 'простой «1», средней сложности «2» и сложный «3»',
  `testId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- СВЯЗИ ТАБЛИЦЫ `question`:
--   `testId`
--       `test` -> `id`
--



--
-- Структура таблицы `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `subjectName` varchar(50) NOT NULL,
  `testName` varchar(50) NOT NULL,
  `timeToPass` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- СВЯЗИ ТАБЛИЦЫ `test`:
--


--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstName` varchar(50) CHARACTER SET utf32 NOT NULL,
  `secondName` varchar(50) CHARACTER SET utf32 NOT NULL,
  `lastName` varchar(50) CHARACTER SET utf32 NOT NULL,
  `userLogin` varchar(50) CHARACTER SET utf32 NOT NULL,
  `userPassword` varchar(50) CHARACTER SET utf32 NOT NULL,
  `role` enum('ROLE_ADMIN','ROLE_STUDENT') DEFAULT NULL,
  `locale` varchar(5) NOT NULL,
  `isActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- СВЯЗИ ТАБЛИЦЫ `user`:
--


--
-- Структура таблицы `user_answer`
--

CREATE TABLE `user_answer` (
  `userId` int(11) NOT NULL,
  `answerId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- СВЯЗИ ТАБЛИЦЫ `user_answer`:
--   `answerId`
--       `answer` -> `id`
--   `userId`
--       `user` -> `id`
--

-- --------------------------------------------------------

--
-- Структура таблицы `user_test`
--

CREATE TABLE `user_test` (
  `testId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `passageOfTime` time NOT NULL,
  `result` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- СВЯЗИ ТАБЛИЦЫ `user_test`:
--   `testId`
--       `test` -> `id`
--   `userId`
--       `user` -> `id`
--


--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
--
-- AUTO_INCREMENT для таблицы `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT для таблицы `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
  
  --
-- Set references
--
 ALTER TABLE answer ADD FOREIGN KEY (questionId) REFERENCES question(id); 
 
 ALTER TABLE user_test ADD FOREIGN KEY (testId) REFERENCES test(id);
 
 ALTER TABLE user_test ADD FOREIGN KEY (userId) REFERENCES user(id); 
 
 ALTER TABLE user_answer ADD FOREIGN KEY (userId) REFERENCES user(id);  



INSERT INTO `test` (`id`, `subjectName`, `testName`, `timeToPass`) VALUES
(1, 'Администрирование LINUX', 'Администрирование в Linux', '00:30:00'),
(2, 'Разработка Web приложений на java', 'Программирование Web на java', '00:40:00'),
(3, 'Тестирование ПО', 'Основы тестирования', '00:25:00');


INSERT INTO `question` (`id`, `textOfQuestion`, `complexity`, `testId`) VALUES
(1, 'Набор программ, как обычных, так и микропрограмм, которые обеспечивают интерфейс между приложениями пользователя и  аппаратурой компьютера, это:', 1, 1),
(2, 'Набор программного обеспечения, включающий все 4 основные составные части ОС, т. е. ядро, файловую систему, оболочку и совокупность утилит, а также некоторую совокупность прикладных программ, это:', 2, 1),
(3, 'В операционной системе UNIX основными средствами взаимодействия пользователя с системой являются клавиатура и экран монитора, работающий в текстовом режиме, это:', 2, 1),
(4, 'Объект системы, при помощи которого Linux ведет учет работы пользователя в системе, содержит данные о пользователе, необходимые для регистрации в системе и дальнейшей работы с ней:', 1, 1),
(5, 'Способ хранения и организации доступа к данным на информационном носителе или его разделе, это:', 2, 1),
(6, 'Файлы этого типа служат в основном для того, чтобы организовать обмен данными между разными приложениями ', 1, 1),
(7, 'Соединения между процессами, которые позволяют им взаимодействовать, не подвергаясь влиянию других процессов', 2, 1),
(8, 'Путь к объекту файловой системы, не начинающийся в корневом каталоге:', 2, 1),
(9, 'В файловых системах Linux вся информация, необходимая для работы с файлом, хранится в:', 1, 1),
(10, 'Параметры команды называются модификаторами выполнения или…:', 2, 1),
(19, 'Можно ли динамически менять размер массива?', 1, 2),
(20, 'Из какой структуры данных <сборщик мусора> удалит все элементы у которых исчезла последняя ссылка на их ключ в этой структуре?', 3, 2),
(33, 'aaaaaaa', 0, 7),
(37, 'asfvsdvb', 0, 7);



INSERT INTO `answer` (`id`, `questionId`, `answerNumber`, `textOfAnswer`, `flag`) VALUES
(1, 1, 1, 'драйверы', 0),


(2, 1, 2, 'утилиты', 0),
(3, 1, 3, 'операционная система', 1),
(4, 1, 4, 'дистрибутив', 0),
(5, 2, 1, 'драйверы', 0),
(6, 2, 2, 'дистрибутив', 1),
(7, 2, 3, 'утилиты', 0),
(8, 2, 4, 'операционная система', 0),
(9, 3, 1, 'терминал', 1),
(10, 3, 2, 'консоль', 0),
(11, 3, 3, 'текстовый редактор', 0),
(12, 3, 4, 'рабочий стол', 0),
(13, 4, 1, 'account', 1),
(14, 4, 2, 'UID', 0),
(15, 4, 3, 'login', 0),
(16, 4, 4, 'password', 0),
(17, 5, 1, 'операционная система', 0),
(18, 5, 2, 'RAID', 0),
(19, 5, 3, 'файловая система', 1),
(20, 5, 4, 'Cloud Computing', 0),
(21, 6, 1, 'каналы', 0),
(22, 6, 2, 'гнезда', 0),
(23, 6, 3, 'sockets', 0),
(24, 6, 4, 'pipes', 1),
(25, 7, 1, 'sockets', 1),
(26, 7, 2, 'pipes', 0),
(27, 7, 3, 'каналы', 0),
(28, 7, 4, 'порты', 0),
(29, 8, 1, 'корневой каталог', 0),
(30, 8, 2, 'относительный путь ', 1);

INSERT INTO `user` (`id`, `firstName`, `secondName`, `lastName`, `userLogin`, `userPassword`, `role`, `locale`, `isActive`) VALUES
(1, 'Chuikov', 'Yaroslav', 'Anatolievich', 'admin', 'admin', 'ROLE_ADMIN', 'ru', 1),
(2, 'Yaroslav', 'Anatolievich', 'Chuikov', 'yarik', '89', 'ROLE_STUDENT', 'ru', 1),
(21, 'user', 'user', 'user', 'user', '777', 'ROLE_STUDENT', 'ru', 0),
(28, 'ddddd', 'dfffff', 'vsdvsd', 'ako', '11', 'ROLE_STUDENT', 'ru', 1),
(29, 'qwwww', 'eeee', 'qqqq', 'login', '1', 'ROLE_STUDENT', 'ru', 1),
(30, 'user', 'user', 'user', 'myUser', '111', 'ROLE_STUDENT', 'ru', 1);




INSERT INTO `user_test` (`testId`, `userId`, `passageOfTime`, `result`) VALUES
(1, 1, '00:00:00', 100),
(1, 2, '00:00:00', 90),
(1, 21, '00:00:00', 70),
(1, 29, '00:00:00', 30),
(1, 30, '00:00:00', 55),
(2, 1, '00:00:00', 77),
(2, 2, '00:00:00', 20),
(2, 21, '00:00:00', 88),
(2, 28, '00:00:00', 67),
(2, 29, '00:00:00', 56),
(3, 1, '00:00:00', 99),
(3, 2, '00:00:00', 720),
(3, 21, '00:00:00', 45),
(3, 28, '00:00:00', 76),
(3, 29, '00:00:00', 80);

