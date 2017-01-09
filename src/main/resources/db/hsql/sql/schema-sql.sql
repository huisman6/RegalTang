-- 通用的条件表达式
CREATE TABLE t_conditional_config_criteria(
  id INTEGER IDENTITY PRIMARY KEY,
  criteria VARCHAR(50) NOT NULL,
  name VARCHAR(30),
  sort INTEGER NOT NULL
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);
