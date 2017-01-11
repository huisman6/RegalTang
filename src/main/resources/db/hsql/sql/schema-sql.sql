-- 通用的条件表达式
CREATE TABLE t_rule_criteria(
  id INTEGER IDENTITY PRIMARY KEY,
  criteria VARCHAR(50) NOT NULL,
  name VARCHAR(30),
  multiValue INTEGER NOT NULL,
  sort INTEGER NOT NULL
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);

--启用配置的应用
CREATE TABLE t_rule_application(
  id INTEGER IDENTITY PRIMARY KEY,
  appKey VARCHAR(80) NOT NULL,
  appName VARCHAR(50),
  sort INTEGER NOT NULL
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);
-- 业务模块
CREATE TABLE t_rule_application_module(
  id INTEGER IDENTITY PRIMARY KEY,
  appId INTEGER NOT NULL,
  moduleKey VARCHAR(200) NOT NULL,
  moduleName VARCHAR(50),
  sort INTEGER NOT NULL
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);

-- 模块可配置那些选项
CREATE TABLE t_rule_application_module_option(
  id INTEGER IDENTITY PRIMARY KEY,
  moduleId INTEGER NOT NULL,
  optionKey VARCHAR(120) NOT NULL,
  optionName VARCHAR(50),
  sort INTEGER NOT NULL
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);
--每一个选项可选的值
CREATE TABLE t_rule_application_module_option_value(
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  content VARCHAR(500) NOT NULL,
  optionId INTEGER NOT NULL,
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);

CREATE TABLE t_rule_application_module_output(
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  content VARCHAR(500) NOT NULL,
  moduleId INTEGER NOT NULL,
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);


