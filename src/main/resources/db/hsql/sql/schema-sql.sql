-- 通用的条件表达式
CREATE TABLE t_rule_criteria(
  id INTEGER IDENTITY PRIMARY KEY,
  criteria VARCHAR(120) NOT NULL,
  name VARCHAR(80),
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
  -- 支持appKey 查询记录，Unique Key
  appKey VARCHAR(120) NOT NULL,
  name VARCHAR(80),
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
  -- 支持moduleKey查询该条记录
  moduleKey VARCHAR(120) NOT NULL,
  -- 业务模块的名称描述
  name VARCHAR(80),
  sort INTEGER NOT NULL
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);

-- 模块的可配置项(RuleItem)
CREATE TABLE t_rule_application_module_item(
  id INTEGER IDENTITY PRIMARY KEY,
  moduleId INTEGER NOT NULL,
  -- 配置项对外显示的名称
  name VARCHAR(80) NOT NULL,
  -- 可配置项的唯一标识，用于查找
  itemKey VARCHAR(120),
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);

-- 模块可配置那些选项(CriteriaOption)
CREATE TABLE t_rule_application_module_option(
  id INTEGER IDENTITY PRIMARY KEY,
  -- 业务模块的ID
  moduleId INTEGER NOT NULL,
  -- 选项的唯一标识，用于查找
  optionKey VARCHAR(120) NOT NULL,
  -- 选项的类型，1=SimpleOption,2=PredifinedValuesOption
  optionType INT NOT NULL,
  -- 选项期望的数据类型
  className VARCHAR(150),
  -- 选项对外显示的名称;
  name VARCHAR(80),
  sort INTEGER NOT NULL
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);
--选项有可选值的元数据信息（只针对类型为PredifinedValuesOption）
CREATE TABLE t_rule_application_module_option_named_value(
  id INTEGER IDENTITY PRIMARY KEY,
  -- 可枚举的option对应的值对外显示的名称
  name VARCHAR(80) NOT NULL,
  -- 实际的值，保存为字符串
  value VARCHAR(200) NOT NULL,
  -- 选项ID
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
  --规则输出的类型，1=ScalarRuleOutput,2=PredifinedValuesRuleOutput，3=JavaBeanRuleOutput
  -- 类型为2和3的输出，可能有多个配置项
  outputType INT,
  --页面显示的名称
  name VARCHAR(80),
  --规则输出期望的类型
  className VARCHAR(150),
  --模块的ID
  moduleId INTEGER NOT NULL,
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);


--规则的输出为预定义的元数据信息（只针对类型为PredifinedValuesRuleOutput）
CREATE TABLE t_rule_application_module_output_predifined_value(
  id INTEGER IDENTITY PRIMARY KEY,
  -- 可枚举的output对应的值对外显示的名称
  name VARCHAR(80) NOT NULL,
  -- 实际的值，保存为字符串
  value VARCHAR(200) NOT NULL,
  --输出结果的ID
  outputId INTEGER NOT NULL,
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);


--规则输出为JavaBean的元数据信息（只针对类型为JavaBeanRuleOutput）
CREATE TABLE t_rule_application_module_output_javabean(
  id INTEGER IDENTITY PRIMARY KEY,
  --字段的名称
  fieldName VARCHAR(80) NOT NULL,
  --字段的数据类型
  fieldClassName VARCHAR(150) NOT NULL,
  --输出结果的ID
  outputId INTEGER NOT NULL,
 -- sync SMALLINT DEFAULT(0)
  -- status INTEGER default(1) not null,
  -- ctime DATETIME default GETDATE(),
  -- mtime DATETIME DEFAULT GETDATE(),
  --cuser INTEGER not null default (0),
  --muser INTEGER not null default (0)
);


