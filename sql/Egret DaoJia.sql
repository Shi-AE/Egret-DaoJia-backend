-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 42.193.192.61    Database: edj-customer
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `edj-customer`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-customer` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-customer`;

--
-- Table structure for table `edj_address_book`
--

DROP TABLE IF EXISTS `edj_address_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_address_book` (
  `id` bigint unsigned NOT NULL COMMENT '地址簿id',
  `edj_user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `phone` varchar(16) NOT NULL COMMENT '电话',
  `province` varchar(256) NOT NULL COMMENT '省份',
  `city` varchar(256) NOT NULL COMMENT '市',
  `county` varchar(256) NOT NULL COMMENT '区 / 县',
  `address` varchar(256) NOT NULL COMMENT '详细地址',
  `lon` decimal(10,5) NOT NULL COMMENT '经度',
  `lat` decimal(10,5) NOT NULL COMMENT '维度',
  `is_default` tinyint NOT NULL DEFAULT '0' COMMENT '是否为默认地址（0否 1是）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_address_book_edj_user_id_index` (`edj_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='地址簿';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_address_book`
--

LOCK TABLES `edj_address_book` WRITE;
/*!40000 ALTER TABLE `edj_address_book` DISABLE KEYS */;
INSERT INTO `edj_address_book` VALUES (9360765299998720,1,'周先生','13555555555','浙江省','温州市','永嘉县','岭后村',120.67055,28.16628,0,1,1,'2024-11-07 18:54:57','2024-11-28 12:56:36',0),(9364255359385600,1,'施先生','13666666666','浙江省','温州市','永嘉县','上下线',120.68290,28.20310,0,1,1,'2024-11-07 19:08:49','2024-11-28 12:56:36',0),(9364658763350016,1,'周先生','13555555555','浙江省','温州市','永嘉县','上塘人民医院',120.69251,28.15066,0,1,1,'2024-11-07 19:10:25','2024-11-28 12:56:36',0),(9365356368379904,1,'周先生','13555555555','广西','桂林','七星','桂林航天工业学院',110.37350,25.27977,0,1,1,'2024-11-07 19:13:11','2024-11-28 12:56:36',0),(9365859793911808,1,'周先生','13555555555','广西','桂林','七星','彩叠',110.31758,25.25309,1,1,1,'2024-11-07 19:15:11','2024-11-28 12:56:36',0),(27005873121935360,8288489957834752,'石先生','14888888888','陕西省','铜川市','宜君县','五里镇128号',109.24509,35.45712,0,8288489957834752,8288489957834752,'2024-12-26 11:30:19','2024-12-26 11:56:20',0),(27012420166238208,8288489957834752,'林先生','13555555555','广西壮族自治区','来宾市','合山市','河里镇140乡道甘林村民委员会',108.95170,23.74045,0,8288489957834752,8288489957834752,'2024-12-26 11:56:20','2025-04-25 21:55:43',0),(27012987135475712,8288489957834752,'周先生','13666666666','浙江省','温州市','永嘉县','北城街道岭后村',120.67055,28.16628,0,8288489957834752,8288489957834752,'2024-12-26 11:58:35','2024-12-27 19:06:53',0),(70360441586991104,8288489957834752,'周翔','13888888888','江苏省','无锡市','江阴市','月城镇协和大桥',120.20228,31.78649,0,8288489957834752,8288489957834752,'2025-04-25 02:45:54','2025-04-25 02:49:09',1),(70360555458150400,8288489957834752,'谭涛','15000000000','江苏省','无锡市','江阴市','澄江街道花园路文明广场公园',120.28432,31.91919,0,8288489957834752,8288489957834752,'2025-04-25 02:46:21','2025-04-25 02:49:09',1),(70360722324340736,8288489957834752,'戴佳伟','13666666666','黑龙江省','伊春市','丰林县','红星镇',129.57212,48.06755,0,8288489957834752,8288489957834752,'2025-04-25 02:47:01','2025-04-25 02:49:09',1),(70360867602448384,8288489957834752,'陈结冰','17455555555','江苏省','无锡市','江阴市','澄江街道花园路文明广场公园',120.28432,31.91919,0,8288489957834752,8288489957834752,'2025-04-25 02:47:36','2025-04-25 02:49:09',1),(70649797996462080,8288489957834752,'戴佳伟','16888888888','广东省','东莞市','','东城街道东城中路475号东莞东城万达广场',113.78386,23.03379,1,8288489957834752,8288489957834752,'2025-04-25 21:55:43','2025-05-16 23:15:27',0),(70649871937847296,8288489957834752,'周翔','19888888888','江苏省','无锡市','江阴市','澄江街道花园路文明广场公园',120.28432,31.91919,0,8288489957834752,8288489957834752,'2025-04-25 21:56:01','2025-04-25 21:56:51',0),(70650082768732160,8288489957834752,'谭涛','17888888888','江苏省','苏州市','张家港市','金港街道上江路双山岛',120.42047,31.99718,0,8288489957834752,8288489957834752,'2025-04-25 21:56:51','2025-04-25 21:57:32',0),(70650253531430912,8288489957834752,'张雨晨','15888888888','江苏省','无锡市','江阴市','华士镇金塔路231号华西塔林住宅区',120.43047,31.83333,0,8288489957834752,8288489957834752,'2025-04-25 21:57:32','2025-04-25 21:58:08',0),(70650404538957824,8288489957834752,'陈结冰','14888888888','江苏省','无锡市','江阴市','夏港街道海岸城C区',120.18940,31.90204,0,8288489957834752,8288489957834752,'2025-04-25 21:58:08','2025-04-25 21:59:12',0),(70650672894722048,8288489957834752,'张鼎','13888888888','江苏省','无锡市','江阴市','澄江街道青果路104号名人国际',120.27429,31.90033,0,8288489957834752,8288489957834752,'2025-04-25 21:59:12','2025-04-25 22:01:10',0),(70651167013093376,8288489957834752,'陈超','13777777777','江苏省','常州市','钟楼区','永红街道苏珵智慧酒店(常州钟楼陈渡桥店)',119.91382,31.76626,0,8288489957834752,8288489957834752,'2025-04-25 22:01:10','2025-04-25 22:14:40',0),(70654564504190976,8288489957834752,'李琼','13455555555','江苏省','南通市','通州区','平潮镇通州区裕成织布厂',120.80480,32.13684,0,8288489957834752,8288489957834752,'2025-04-25 22:14:40','2025-04-25 22:15:10',0),(70654692929585152,8288489957834752,'陈娟','13888888888','上海市','上海市','崇明区','绿华镇新建公路',121.21917,31.75826,0,8288489957834752,8288489957834752,'2025-04-25 22:15:10','2025-04-25 22:18:11',0),(70655039035162624,8288489957834752,'杨画','13555555555','广东省','东莞市','','大朗镇松山湖风景区',113.88164,22.89413,0,8288489957834752,8288489957834752,'2025-04-25 22:16:33','2025-05-16 23:16:16',0);
/*!40000 ALTER TABLE `edj_address_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_agency_certification`
--

DROP TABLE IF EXISTS `edj_agency_certification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_agency_certification` (
  `id` bigint unsigned NOT NULL COMMENT '机构id',
  `name` varchar(32) DEFAULT NULL COMMENT '企业名称',
  `id_number` varchar(18) DEFAULT NULL COMMENT '统一社会信用代码',
  `legal_person_name` varchar(32) DEFAULT NULL COMMENT '法定代表人姓名',
  `legal_person_id_card_no` varchar(18) DEFAULT NULL COMMENT '法定代表人身份证号',
  `business_license` varchar(128) DEFAULT NULL COMMENT '营业执照',
  `certification_status` tinyint NOT NULL DEFAULT '0' COMMENT '认证状态（0初始态 1认证中 2认证成功 3认证失败）',
  `certification_time` datetime DEFAULT NULL COMMENT '认证时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构认证信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_agency_certification`
--

LOCK TABLES `edj_agency_certification` WRITE;
/*!40000 ALTER TABLE `edj_agency_certification` DISABLE KEYS */;
INSERT INTO `edj_agency_certification` VALUES (1,'xxx','xxx','xxx','xxx','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/a4924f8b-0a89-498e-bec4-e241f3ae835a.jpg',2,'2024-11-19 00:57:08',1,1,'2024-11-15 16:42:33','2025-05-02 04:21:47',0),(14078868029972480,'网易（杭州）网络有限公司','91330000788831167A','丁磊','330683200001018875','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/a4924f8b-0a89-498e-bec4-e241f3ae835a.jpg',2,'2024-11-21 12:46:27',14078868029972480,14078868029972480,'2024-11-21 12:37:55','2025-05-02 04:21:47',0),(73147336041439232,'桂林天算网络科技有限公司','91450305MA5QCH327B','王进之','510703199503137737','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/04103404-11d6-473c-84c7-7a4bb3ba6bbd.jpg',2,'2025-05-02 19:25:31',73147336041439232,73147336041439232,'2025-05-02 19:23:38','2025-05-02 19:25:30',0);
/*!40000 ALTER TABLE `edj_agency_certification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_agency_certification_audit`
--

DROP TABLE IF EXISTS `edj_agency_certification_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_agency_certification_audit` (
  `id` bigint unsigned NOT NULL COMMENT '机构id',
  `edj_serve_provider_id` bigint unsigned NOT NULL COMMENT '机构id',
  `name` varchar(32) NOT NULL COMMENT '企业名称',
  `id_number` varchar(18) NOT NULL COMMENT '统一社会信用代码',
  `legal_person_name` varchar(32) NOT NULL COMMENT '法定代表人姓名',
  `legal_person_id_card_no` varchar(18) NOT NULL COMMENT '法定代表人身份证号',
  `business_license` varchar(128) NOT NULL COMMENT '营业执照',
  `audit_status` tinyint NOT NULL DEFAULT '0' COMMENT '审核状态（0未审核 1已审核）',
  `audit_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '审核人id',
  `audit_name` varchar(32) NOT NULL DEFAULT '' COMMENT '审核人姓名',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `reject_reason` varchar(128) NOT NULL DEFAULT '' COMMENT '驳回原因',
  `certification_status` tinyint NOT NULL DEFAULT '1' COMMENT '认证状态（0初始态 1认证中 2认证成功 3认证失败）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_agency_certification_audit_edj_serve_provider_id_index` (`edj_serve_provider_id`),
  KEY `edj_agency_certification_audit_legal_person_name_index` (`legal_person_name`),
  KEY `edj_agency_certification_audit_name_index` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构认证审核表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_agency_certification_audit`
--

LOCK TABLES `edj_agency_certification_audit` WRITE;
/*!40000 ALTER TABLE `edj_agency_certification_audit` DISABLE KEYS */;
INSERT INTO `edj_agency_certification_audit` VALUES (14332931594534912,14078868029972480,'阿里巴巴（中国）有限公司','91330100799655058B','蒋芳','131003200001018639','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/a4924f8b-0a89-498e-bec4-e241f3ae835a.jpg',1,1,'admin','2024-11-21 12:14:15','未满足认证要求',3,14078868029972480,14078868029972480,'2024-11-21 12:12:35','2025-05-02 04:22:01',0),(14334394437746688,14078868029972480,'深圳市腾讯计算机系统有限公司','91440300708461136T','马化腾','341421200001016573','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/a4924f8b-0a89-498e-bec4-e241f3ae835a.jpg',1,1,'admin','2024-11-21 12:25:45','',2,14078868029972480,14078868029972480,'2024-11-21 12:18:24','2025-05-02 04:22:01',0),(14339306387161088,14078868029972480,'网易（杭州）网络有限公司','91330000788831167A','丁磊','330683200001018875','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/a4924f8b-0a89-498e-bec4-e241f3ae835a.jpg',1,1,'admin','2024-11-21 12:46:27','',2,14078868029972480,14078868029972480,'2024-11-21 12:37:55','2025-05-02 04:22:01',0),(73148247841456128,73147336041439232,'桂林天算网络科技有限公司','91450305MA5QCH327B','王进之','510703199503137737','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/04103404-11d6-473c-84c7-7a4bb3ba6bbd.jpg',1,1,'admin','2025-05-02 19:25:31','',2,73147336041439232,73147336041439232,'2025-05-02 19:23:38','2025-05-02 19:25:30',0);
/*!40000 ALTER TABLE `edj_agency_certification_audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_bank_account`
--

DROP TABLE IF EXISTS `edj_bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_bank_account` (
  `id` bigint unsigned NOT NULL COMMENT '服务人员/机构id',
  `name` varchar(32) NOT NULL COMMENT '户名',
  `bank_name` varchar(32) NOT NULL COMMENT '银行名称',
  `province` varchar(32) NOT NULL COMMENT '省',
  `city` varchar(32) NOT NULL COMMENT '市',
  `district` varchar(32) NOT NULL COMMENT '区',
  `branch` varchar(32) NOT NULL COMMENT '网点',
  `account` varchar(32) NOT NULL COMMENT '银行账号',
  `account_certification` varchar(128) NOT NULL COMMENT '开户证明',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='银行账户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_bank_account`
--

LOCK TABLES `edj_bank_account` WRITE;
/*!40000 ALTER TABLE `edj_bank_account` DISABLE KEYS */;
INSERT INTO `edj_bank_account` VALUES (11148624847446016,'谈全浩','中国建设银行','广西壮族自治区','桂林市','七星区','中国建设银行温州分行','6236 6814 2001 1111 333','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2024/11/27/c8145d91-3bc9-45f7-b8f2-91ea27cb5b36.png',11148624847446016,11148624847446016,'2024-11-27 12:52:24','2024-11-28 12:59:08',0),(14078868029972480,'网易（杭州）网络有限公司','中国工商银行','浙江省','杭州市','上城区','中国工商银行股份有限公司浙江省分行','6217 7130 0357 1111','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2024/11/27/751ccce7-84d5-48ab-92f8-851dd33d911e.png',14078868029972480,14078868029972480,'2024-11-27 13:22:12','2024-11-28 12:53:33',0);
/*!40000 ALTER TABLE `edj_bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_institution_staff`
--

DROP TABLE IF EXISTS `edj_institution_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_institution_staff` (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `institution_id` bigint unsigned NOT NULL COMMENT '服务机构id',
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `phone` varchar(16) NOT NULL COMMENT '电话',
  `id_card_no` char(18) NOT NULL COMMENT '身份证号',
  `certification_img_list` json DEFAULT NULL COMMENT '证明资料列表',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构下属服务人员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_institution_staff`
--

LOCK TABLES `edj_institution_staff` WRITE;
/*!40000 ALTER TABLE `edj_institution_staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `edj_institution_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_serve_provider`
--

DROP TABLE IF EXISTS `edj_serve_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_serve_provider` (
  `id` bigint unsigned NOT NULL COMMENT '服务人员/机构表（同用户id）',
  `code` varchar(32) NOT NULL COMMENT '编号',
  `settings_status` tinyint NOT NULL DEFAULT '0' COMMENT '认证设置状态（0未完成 1已完成）',
  `score` decimal(6,2) NOT NULL DEFAULT '50.00' COMMENT '综合评分，默认50分',
  `good_level_rate` decimal(6,4) NOT NULL DEFAULT '0.0000' COMMENT '好评率',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务人员/机构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_serve_provider`
--

LOCK TABLES `edj_serve_provider` WRITE;
/*!40000 ALTER TABLE `edj_serve_provider` DISABLE KEYS */;
INSERT INTO `edj_serve_provider` VALUES (1,'EDJ_00000000000000001_04040000',1,50.00,0.0000,0,1,'2024-11-12 21:54:25','2024-11-21 17:33:57',0),(11148624847446016,'EDJ_20241112171917225_04040000',1,50.00,0.0000,0,0,'2024-11-12 17:19:15','2024-11-19 16:18:03',0),(14078868029972480,'EDJ_20241120192302379_04040000',1,50.00,0.0000,0,0,'2024-11-20 19:23:02','2024-11-21 12:25:54',0),(70266500728233984,'EDJ_20250424203238136_04040000',1,50.00,0.0000,0,0,'2025-04-24 20:32:39','2025-04-25 21:43:10',0),(73147336041439232,'EDJ_20250502192002335_04040000',1,50.00,0.0000,0,0,'2025-05-02 19:20:01','2025-05-02 19:25:55',0),(73151942012182528,'EDJ_20250502193820498_04040000',1,50.00,0.0000,0,0,'2025-05-02 19:38:19','2025-05-02 19:48:58',0);
/*!40000 ALTER TABLE `edj_serve_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_serve_provider_settings`
--

DROP TABLE IF EXISTS `edj_serve_provider_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_serve_provider_settings` (
  `id` bigint unsigned NOT NULL COMMENT '服务人员/机构id（同用户id）',
  `city_code` varchar(8) NOT NULL DEFAULT '' COMMENT '城市编号',
  `city_name` varchar(16) NOT NULL DEFAULT '' COMMENT '城市名称',
  `lon` decimal(10,5) NOT NULL DEFAULT '0.00000' COMMENT '经度',
  `lat` decimal(10,5) NOT NULL DEFAULT '0.00000' COMMENT '纬度',
  `intention_scope` varchar(128) NOT NULL DEFAULT '' COMMENT '意向单范围',
  `have_skill` tinyint NOT NULL DEFAULT '0' COMMENT '是否有技能（1有 0无）',
  `can_pick_up` tinyint NOT NULL DEFAULT '-1' COMMENT '是否可以接单（-1未知 0关闭接单 1开启接单）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务人员/机构认证信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_serve_provider_settings`
--

LOCK TABLES `edj_serve_provider_settings` WRITE;
/*!40000 ALTER TABLE `edj_serve_provider_settings` DISABLE KEYS */;
INSERT INTO `edj_serve_provider_settings` VALUES (1,'0792','九江市',115.53102,29.46583,'江西省九江市德安县邹桥乡斗笠曾家',1,1,0,1,'2024-11-12 21:54:25','2024-11-15 18:26:00',0),(11148624847446016,'0769','东莞市',113.85658,22.89148,'大岭山镇华兴街39号上场大厦',1,1,0,11148624847446016,'2024-11-12 17:19:15','2025-05-16 23:24:19',0),(14078868029972480,'0769','东莞市',113.85658,22.89148,'大湾区大学(松山湖校区)',1,1,0,14078868029972480,'2024-11-20 19:23:02','2025-05-16 22:47:03',0),(70266500728233984,'0510','无锡市',120.20220,31.78734,'云外水荘东侧',1,1,0,70266500728233984,'2025-04-24 20:32:39','2025-04-25 21:41:59',0),(73147336041439232,'0510','无锡市',120.27270,31.67804,'江苏省无锡市惠山区风能路',1,1,0,73147336041439232,'2025-05-02 19:20:01','2025-05-02 20:25:11',0),(73151942012182528,'0510','无锡市',120.20220,31.78734,'云外水荘东侧',1,1,0,73151942012182528,'2025-05-02 19:38:19','2025-05-02 19:47:49',0);
/*!40000 ALTER TABLE `edj_serve_provider_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_serve_provider_sync`
--

DROP TABLE IF EXISTS `edj_serve_provider_sync`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_serve_provider_sync` (
  `id` bigint unsigned NOT NULL COMMENT '服务提供者id（同用户id）',
  `serve_item_id_list` json NOT NULL COMMENT '技能列表',
  `lon` decimal(10,5) NOT NULL DEFAULT '0.00000' COMMENT '经度',
  `lat` decimal(10,5) NOT NULL DEFAULT '0.00000' COMMENT '纬度',
  `city_code` varchar(8) NOT NULL DEFAULT '' COMMENT '城市编码',
  `pick_up` tinyint NOT NULL DEFAULT (-(1)) COMMENT '是否可以接单（-1未知 0关闭接单 1开启接单）',
  `score` decimal(6,2) NOT NULL DEFAULT '50.00' COMMENT '评分，默认50分',
  `setting_status` tinyint NOT NULL DEFAULT '0' COMMENT '认证设置状态（0未完成 1已完成）',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '账号状态（0正常 1冻结）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务提供者同步表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_serve_provider_sync`
--

LOCK TABLES `edj_serve_provider_sync` WRITE;
/*!40000 ALTER TABLE `edj_serve_provider_sync` DISABLE KEYS */;
INSERT INTO `edj_serve_provider_sync` VALUES (1,'[1328135745609721, 1328135745609722, 1328135745609729]',115.53102,29.46583,'0792',1,50.00,1,0,0,1,'2024-11-12 21:54:25','2024-11-15 18:26:27',0),(11148624847446016,'[72894530948837376, 72899828094742528, 72900776691118081, 72901580131020800, 72902646524424193, 72903255927435264, 72903775048052737, 72904292314787840, 72905250474176512, 72906123162038273, 72907113550458880, 72907779886952448, 72908498379616256]',120.20220,31.78734,'0769',1,50.00,1,0,0,11148624847446016,'2024-11-12 17:19:15','2025-05-16 22:50:27',0),(14078868029972480,'[72894530948837376, 72899828094742528, 72900776691118081, 72901580131020800, 72902646524424193, 72903255927435264, 72903775048052737, 72904292314787840, 72905250474176512, 72906123162038273, 72907113550458880, 72907779886952448, 72908498379616256]',113.85658,22.89148,'0769',1,50.00,1,0,0,14078868029972480,'2024-11-20 19:23:02','2025-05-16 22:47:03',0),(70266500728233984,'[1328010629521408, 1328080728924160, 1328135745609729, 1349339059625984, 1352560444481536, 1352644787740672, 1352717865099265, 1352814757715969, 1352913915256833, 69831192715079680, 69831405877997568, 69831505555632129, 69831619330322432, 69831791397449728, 69831919768317952]',120.20220,31.78734,'0510',1,50.00,1,0,0,70266500728233984,'2025-04-24 20:32:39','2025-04-25 21:43:10',0),(73147336041439232,'[72894530948837376, 72899828094742528, 72901580131020800, 72902646524424193, 72903255927435264, 72905250474176512, 72907113550458880, 72907779886952448]',120.27270,31.67804,'0510',1,50.00,1,0,0,73147336041439232,'2025-05-02 19:20:01','2025-05-02 20:25:11',0),(73151942012182528,'[72894530948837376, 72899828094742528, 72901580131020800, 72902646524424193, 72903255927435264, 72905250474176512, 72907113550458880]',120.20220,31.78734,'0510',1,50.00,1,0,0,73151942012182528,'2025-05-02 19:38:19','2025-05-02 19:48:58',0);
/*!40000 ALTER TABLE `edj_serve_provider_sync` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_serve_skill`
--

DROP TABLE IF EXISTS `edj_serve_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_serve_skill` (
  `id` bigint unsigned NOT NULL COMMENT '服务技能主键',
  `serve_provider_id` bigint unsigned NOT NULL COMMENT '服务人员/机构id',
  `edj_serve_type_id` bigint unsigned NOT NULL COMMENT '服务类型id',
  `serve_type_name` varchar(255) NOT NULL COMMENT '服务类型名称',
  `edj_serve_item_id` bigint unsigned NOT NULL COMMENT '服务项id',
  `serve_item_name` varchar(255) NOT NULL COMMENT '服务项名称',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_serve_skill_serve_provider_id_index` (`serve_provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务技能表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_serve_skill`
--

LOCK TABLES `edj_serve_skill` WRITE;
/*!40000 ALTER TABLE `edj_serve_skill` DISABLE KEYS */;
INSERT INTO `edj_serve_skill` VALUES (73148367483977728,73147336041439232,72873557365239808,'保洁服务',72894530948837376,'日常清洁',73147336041439232,73147336041439232,'2025-05-02 19:24:07','2025-05-02 19:24:07',0),(73148367509143552,73147336041439232,72873557365239808,'保洁服务',72899828094742528,'深度清洁',73147336041439232,73147336041439232,'2025-05-02 19:24:07','2025-05-02 19:24:07',0),(73148368075374592,73147336041439232,72874351573479425,'清洁服务',72901580131020800,'擦玻璃',73147336041439232,73147336041439232,'2025-05-02 19:24:07','2025-05-02 19:24:07',0),(73148368087957504,73147336041439232,72874351573479425,'清洁服务',72902646524424193,'空调清洗',73147336041439232,73147336041439232,'2025-05-02 19:24:07','2025-05-02 19:24:07',0),(73148368536748032,73147336041439232,72874351573479425,'清洁服务',72903255927435264,'冰箱清洗',73147336041439232,73147336041439232,'2025-05-02 19:24:07','2025-05-02 19:24:07',0),(73148368549330944,73147336041439232,72875199204569088,'家居养护',72905250474176512,'地板打蜡',73147336041439232,73147336041439232,'2025-05-02 19:24:07','2025-05-02 19:24:07',0),(73148369048453120,73147336041439232,72876349135925248,'母婴护理',72907113550458880,'月嫂',73147336041439232,73147336041439232,'2025-05-02 19:24:07','2025-05-02 19:24:07',0),(73148369065230336,73147336041439232,72876349135925248,'母婴护理',72907779886952448,'育儿嫂',73147336041439232,73147336041439232,'2025-05-02 19:24:07','2025-05-02 19:24:07',0),(73153665640710144,73151942012182528,72873557365239808,'保洁服务',72894530948837376,'日常清洁',73151942012182528,73151942012182528,'2025-05-02 19:45:10','2025-05-02 19:45:10',0),(73153665653293056,73151942012182528,72873557365239808,'保洁服务',72899828094742528,'深度清洁',73151942012182528,73151942012182528,'2025-05-02 19:45:10','2025-05-02 19:45:10',0),(73153666437627904,73151942012182528,72874351573479425,'清洁服务',72901580131020800,'擦玻璃',73151942012182528,73151942012182528,'2025-05-02 19:45:10','2025-05-02 19:45:10',0),(73153666462793728,73151942012182528,72874351573479425,'清洁服务',72902646524424193,'空调清洗',73151942012182528,73151942012182528,'2025-05-02 19:45:10','2025-05-02 19:45:10',0),(73153667519758336,73151942012182528,72874351573479425,'清洁服务',72903255927435264,'冰箱清洗',73151942012182528,73151942012182528,'2025-05-02 19:45:10','2025-05-02 19:45:10',0),(73153667536535552,73151942012182528,72875199204569088,'家居养护',72905250474176512,'地板打蜡',73151942012182528,73151942012182528,'2025-05-02 19:45:10','2025-05-02 19:45:10',0),(73153668794826752,73151942012182528,72876349135925248,'母婴护理',72907113550458880,'月嫂',73151942012182528,73151942012182528,'2025-05-02 19:45:11','2025-05-02 19:45:11',0),(78265563319123968,14078868029972480,72873557365239808,'保洁服务',72894530948837376,'日常清洁',14078868029972480,14078868029972480,'2025-05-16 22:18:07','2025-05-16 22:36:52',1),(78267975639515136,11148624847446016,72873557365239808,'保洁服务',72894530948837376,'日常清洁',11148624847446016,11148624847446016,'2025-05-16 22:27:42','2025-05-16 22:27:57',1),(78268041666248704,11148624847446016,72873557365239808,'保洁服务',72894530948837376,'日常清洁',11148624847446016,11148624847446016,'2025-05-16 22:27:58','2025-05-16 22:50:26',1),(78268041674637312,11148624847446016,72875199204569088,'家居养护',72906123162038273,'除尘除螨',11148624847446016,11148624847446016,'2025-05-16 22:27:58','2025-05-16 22:50:26',1),(78270281802072064,14078868029972480,72873557365239808,'保洁服务',72894530948837376,'日常清洁',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270281814654976,14078868029972480,72873557365239808,'保洁服务',72899828094742528,'深度清洁',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270282372497408,14078868029972480,72873557365239808,'保洁服务',72900776691118081,'开荒保洁',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270282380886016,14078868029972480,72874351573479425,'清洁服务',72901580131020800,'擦玻璃',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270282972282880,14078868029972480,72874351573479425,'清洁服务',72902646524424193,'空调清洗',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270282980671488,14078868029972480,72874351573479425,'清洁服务',72903255927435264,'冰箱清洗',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270283513348096,14078868029972480,72874351573479425,'清洁服务',72903775048052737,'油烟机清洗',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270283517542400,14078868029972480,72874351573479425,'清洁服务',72904292314787840,'洗衣机清洗',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270284188631040,14078868029972480,72875199204569088,'家居养护',72905250474176512,'地板打蜡',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270284192825344,14078868029972480,72875199204569088,'家居养护',72906123162038273,'除尘除螨',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270284897468416,14078868029972480,72876349135925248,'母婴护理',72907113550458880,'月嫂',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270284901662720,14078868029972480,72876349135925248,'母婴护理',72907779886952448,'育儿嫂',14078868029972480,14078868029972480,'2025-05-16 22:36:52','2025-05-16 22:36:52',0),(78270285522419712,14078868029972480,72876349135925248,'母婴护理',72908498379616256,'保姆',14078868029972480,14078868029972480,'2025-05-16 22:36:53','2025-05-16 22:36:53',0),(78273699107061760,11148624847446016,72873557365239808,'保洁服务',72894530948837376,'日常清洁',11148624847446016,11148624847446016,'2025-05-16 22:50:26','2025-05-16 22:50:26',0),(78273699115450368,11148624847446016,72873557365239808,'保洁服务',72899828094742528,'深度清洁',11148624847446016,11148624847446016,'2025-05-16 22:50:26','2025-05-16 22:50:26',0),(78273699648126976,11148624847446016,72873557365239808,'保洁服务',72900776691118081,'开荒保洁',11148624847446016,11148624847446016,'2025-05-16 22:50:26','2025-05-16 22:50:26',0),(78273699656515584,11148624847446016,72874351573479425,'清洁服务',72901580131020800,'擦玻璃',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273700159832064,11148624847446016,72874351573479425,'清洁服务',72902646524424193,'空调清洗',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273700168220672,11148624847446016,72874351573479425,'清洁服务',72903255927435264,'冰箱清洗',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273700658954240,11148624847446016,72874351573479425,'清洁服务',72903775048052737,'油烟机清洗',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273700663148544,11148624847446016,72874351573479425,'清洁服务',72904292314787840,'洗衣机清洗',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273701166465024,11148624847446016,72875199204569088,'家居养护',72905250474176512,'地板打蜡',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273701170659328,11148624847446016,72875199204569088,'家居养护',72906123162038273,'除尘除螨',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273701753667584,11148624847446016,72876349135925248,'母婴护理',72907113550458880,'月嫂',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273701757861888,11148624847446016,72876349135925248,'母婴护理',72907779886952448,'育儿嫂',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0),(78273702215041024,11148624847446016,72876349135925248,'母婴护理',72908498379616256,'保姆',11148624847446016,11148624847446016,'2025-05-16 22:50:27','2025-05-16 22:50:27',0);
/*!40000 ALTER TABLE `edj_serve_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_worker_certification`
--

DROP TABLE IF EXISTS `edj_worker_certification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_worker_certification` (
  `id` bigint unsigned NOT NULL COMMENT '服务人员认证信息id',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `id_card_no` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `front_img` varchar(128) DEFAULT NULL COMMENT '身份证正面',
  `back_img` varchar(128) DEFAULT NULL COMMENT '身份证背面',
  `certification_material` varchar(128) DEFAULT NULL COMMENT '证明资料',
  `certification_status` tinyint NOT NULL DEFAULT '0' COMMENT '认证状态（0初始态 1认证中 2认证成功 3认证失败）',
  `certification_time` datetime DEFAULT NULL COMMENT '认证时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务人员认证信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_worker_certification`
--

LOCK TABLES `edj_worker_certification` WRITE;
/*!40000 ALTER TABLE `edj_worker_certification` DISABLE KEYS */;
INSERT INTO `edj_worker_certification` VALUES (1,'施亦翔','33030220021017081X','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',2,'2024-11-19 00:57:08',1,1,'2024-11-15 16:42:33','2025-05-02 04:22:26',0),(11148624847446016,'谈全浩','360429197004024017','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',2,'2024-11-19 00:57:08',11148624847446016,11148624847446016,'2024-11-15 16:40:16','2025-05-02 04:22:26',0),(70266500728233984,'戴佳伟','330482198906070435','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',2,'2025-04-25 21:42:54',70266500728233984,70266500728233984,'2025-04-24 20:34:46','2025-05-02 04:22:26',0),(73151942012182528,'李琼','31010419940618962X','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/7a20ebf6-b4aa-4b7f-9002-6ae6b18b5d17.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4f472f59-cb8c-4e2b-9592-08dc31015d29.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/84c9bb29-fd5e-43cf-80b9-ac9f1bb502fd.jfif',2,'2025-05-02 19:48:18',73151942012182528,73151942012182528,'2025-05-02 19:44:33','2025-05-02 19:48:17',0);
/*!40000 ALTER TABLE `edj_worker_certification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_worker_certification_audit`
--

DROP TABLE IF EXISTS `edj_worker_certification_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_worker_certification_audit` (
  `id` bigint unsigned NOT NULL COMMENT '审核id',
  `edj_serve_provider_id` bigint unsigned NOT NULL COMMENT '服务人员id',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `id_card_no` varchar(18) NOT NULL COMMENT '身份证号',
  `front_img` varchar(255) NOT NULL COMMENT '身份证正面',
  `back_img` varchar(255) NOT NULL COMMENT '身份证背面',
  `certification_material` varchar(255) NOT NULL COMMENT '证明材料',
  `audit_status` tinyint NOT NULL DEFAULT '0' COMMENT '审核状态（0未审核 1已审核）',
  `audit_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '审核人id',
  `audit_name` varchar(32) NOT NULL DEFAULT '' COMMENT '审核人姓名',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `reject_reason` varchar(128) NOT NULL DEFAULT '' COMMENT '驳回原因',
  `certification_status` tinyint NOT NULL DEFAULT '1' COMMENT '认证状态（0初始态 1认证中 2认证成功 3认证失败）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_worker_certification_audit_id_card_no_index` (`id_card_no`),
  KEY `edj_worker_certification_audit_name_index` (`name`),
  KEY `reason_index` (`edj_serve_provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务人员认证审核表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_worker_certification_audit`
--

LOCK TABLES `edj_worker_certification_audit` WRITE;
/*!40000 ALTER TABLE `edj_worker_certification_audit` DISABLE KEYS */;
INSERT INTO `edj_worker_certification_audit` VALUES (12225969151033344,11148624847446016,'施亦翔','33030220021017081X','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2024-11-19 00:55:56','公司信息不完整或不准确',3,11148624847446016,11148624847446016,'2024-11-15 16:40:16','2025-05-02 04:16:33',0),(12226542155874304,1,'施亦翔','33030220021017081X','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2024-11-19 00:57:08','',2,1,1,'2024-11-15 16:42:32','2025-05-02 04:16:33',0),(12226542155874305,11148624847446017,'葛的测','341503195409224463','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',0,0,'',NULL,'',1,0,0,'2024-11-18 21:48:46','2025-05-02 04:16:33',0),(12226542155874306,11148624847446018,'仉国成','150781197312259953','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2024-11-19 00:55:31','',2,0,0,'2024-11-18 21:48:46','2025-05-02 04:16:33',0),(12226542155874307,11148624847446019,'赖码实','370983199205057460','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2024-11-19 00:54:29','',2,0,0,'2024-11-18 21:48:46','2025-05-02 04:16:33',0),(12226542155874308,11148624847446021,'孙虚豪','51030419581221830X','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',0,0,'',NULL,'',1,0,0,'2024-11-18 21:48:46','2025-05-02 04:16:33',0),(12226542155874309,11148624847446022,'干汝玥','532822199309097655','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',0,0,'',NULL,'',1,0,0,'2024-11-18 21:48:46','2025-05-02 04:16:33',0),(12226542155874311,11148624847446023,'东名润','431225197406186871','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',0,0,'',NULL,'',1,0,0,'2024-11-18 21:48:46','2025-05-02 04:16:33',0),(12226542155874312,11148624847446024,'马果润','320103197307241206','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2025-05-02 04:17:39','',2,0,0,'2024-11-18 21:48:46','2025-05-02 04:17:38',0),(12226542155874313,11148624847446025,'爱毅私','220422198106056596','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2025-05-02 04:07:42','未满足认证要求',3,0,0,'2024-11-18 21:48:46','2025-05-02 04:16:33',0),(13649669528240128,11148624847446016,'戴佳伟','370902200312153762','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2024-11-19 14:58:13','未满足认证要求',3,11148624847446016,11148624847446016,'2024-11-19 14:57:31','2025-05-02 04:16:33',0),(13662260904345600,11148624847446016,'樊翔易','430624200110034165','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2024-11-19 15:47:53','风险审核未通过',3,11148624847446016,11148624847446016,'2024-11-19 15:47:33','2025-05-02 04:16:33',0),(13669874883117056,11148624847446016,'谈全浩','360429197004024017','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2024-11-19 16:18:01','',2,11148624847446016,11148624847446016,'2024-11-19 16:17:49','2025-05-02 04:16:33',0),(70267038719295488,70266500728233984,'戴佳伟','330482198906070435','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c78d7ce1-9f3e-4109-bd2c-523a465dbef1.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbbbe96-7a96-47f9-a3e1-156f15795801.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/07478357-44cb-4d36-9f0b-b7c6866efc3d.jfif',1,1,'admin','2025-04-25 21:42:54','',2,70266500728233984,70266500728233984,'2025-04-24 20:34:46','2025-05-02 04:16:33',0),(73153508098457600,73151942012182528,'李琼','31010419940618962X','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/7a20ebf6-b4aa-4b7f-9002-6ae6b18b5d17.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4f472f59-cb8c-4e2b-9592-08dc31015d29.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/84c9bb29-fd5e-43cf-80b9-ac9f1bb502fd.jfif',1,1,'admin','2025-05-02 19:48:18','',2,73151942012182528,73151942012182528,'2025-05-02 19:44:32','2025-05-02 19:48:17',0);
/*!40000 ALTER TABLE `edj_worker_certification_audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT transaction mode undo table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `edj-foundation`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-foundation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-foundation`;

--
-- Table structure for table `edj_city`
--

DROP TABLE IF EXISTS `edj_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_city` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '城市ID',
  `parent_id` int unsigned NOT NULL DEFAULT '0' COMMENT '上级归属',
  `type` tinyint NOT NULL DEFAULT '1' COMMENT '类型（1省 2市）',
  `name` varchar(16) NOT NULL DEFAULT '' COMMENT '城市名称',
  `city_code` varchar(8) NOT NULL DEFAULT '' COMMENT '城市编号',
  `pinyin_initial` char(1) NOT NULL COMMENT '城市名称拼音首字母',
  `sort_num` int NOT NULL DEFAULT '0' COMMENT '排序',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `edj_city_pk` (`city_code`)
) ENGINE=InnoDB AUTO_INCREMENT=405 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='城市字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_city`
--

LOCK TABLES `edj_city` WRITE;
/*!40000 ALTER TABLE `edj_city` DISABLE KEYS */;
INSERT INTO `edj_city` VALUES (1,0,1,'内蒙古自治区','15','N',0,0),(2,1,2,'呼伦贝尔市','0470','H',0,0),(3,1,2,'呼和浩特市','0471','H',0,0),(4,1,2,'包头市','0472','B',0,0),(5,1,2,'乌海市','0473','W',0,0),(6,1,2,'乌兰察布市','0474','W',0,0),(7,1,2,'通辽市','0475','T',0,0),(8,1,2,'赤峰市','0476','C',0,0),(9,1,2,'鄂尔多斯市','0477','E',0,0),(10,1,2,'巴彦淖尔市','0478','B',0,0),(11,1,2,'锡林郭勒盟','0479','X',0,0),(12,1,2,'兴安盟','0482','X',0,0),(13,1,2,'阿拉善盟','0483','A',0,0),(14,0,1,'黑龙江省','23','H',0,0),(15,14,2,'哈尔滨市','0451','H',0,0),(16,14,2,'齐齐哈尔市','0452','Q',0,0),(17,14,2,'牡丹江市','0453','M',0,0),(18,14,2,'佳木斯市','0454','J',0,0),(19,14,2,'绥化市','0455','S',0,0),(20,14,2,'黑河市','0456','H',0,0),(21,14,2,'大兴安岭地区','0457','D',0,0),(22,14,2,'伊春市','0458','Y',0,0),(23,14,2,'大庆市','0459','D',0,0),(24,14,2,'七台河市','0464','Q',0,0),(25,14,2,'鸡西市','0467','J',0,0),(26,14,2,'鹤岗市','0468','H',0,0),(27,14,2,'双鸭山市','0469','S',0,0),(28,0,1,'江苏省','32','J',0,0),(29,28,2,'南京市','025','N',0,0),(30,28,2,'无锡市','0510','W',0,0),(31,28,2,'镇江市','0511','Z',0,0),(32,28,2,'苏州市','0512','S',0,0),(33,28,2,'南通市','0513','N',0,0),(34,28,2,'扬州市','0514','Y',0,0),(35,28,2,'盐城市','0515','Y',0,0),(36,28,2,'徐州市','0516','X',0,0),(37,28,2,'淮安市','0517','H',0,0),(38,28,2,'连云港市','0518','L',0,0),(39,28,2,'常州市','0519','C',0,0),(40,28,2,'泰州市','0523','T',0,0),(41,28,2,'宿迁市','0527','S',0,0),(42,0,1,'湖南省','43','H',0,0),(43,42,2,'岳阳市','0730','Y',0,0),(44,42,2,'长沙市','0731','C',0,0),(45,42,2,'湘潭市','0732','X',0,0),(46,42,2,'株洲市','0733','Z',0,0),(47,42,2,'衡阳市','0734','H',0,0),(48,42,2,'郴州市','0735','C',0,0),(49,42,2,'常德市','0736','C',0,0),(50,42,2,'益阳市','0737','Y',0,0),(51,42,2,'娄底市','0738','L',0,0),(52,42,2,'邵阳市','0739','S',0,0),(53,42,2,'湘西土家族苗族自治州','0743','X',0,0),(54,42,2,'张家界市','0744','Z',0,0),(55,42,2,'怀化市','0745','H',0,0),(56,42,2,'永州市','0746','Y',0,0),(57,0,1,'天津市','12','T',0,0),(58,57,2,'天津市','022','T',0,0),(59,0,1,'上海市','31','S',0,0),(60,59,2,'上海市','021','S',0,0),(61,0,1,'山西省','14','S',0,0),(62,61,2,'朔州市','0349','S',0,0),(63,61,2,'忻州市','0350','X',0,0),(64,61,2,'太原市','0351','T',0,0),(65,61,2,'大同市','0352','D',0,0),(66,61,2,'阳泉市','0353','Y',0,0),(67,61,2,'晋中市','0354','J',0,0),(68,61,2,'长治市','0355','C',0,0),(69,61,2,'晋城市','0356','J',0,0),(70,61,2,'临汾市','0357','L',0,0),(71,61,2,'吕梁市','0358','L',0,0),(72,61,2,'运城市','0359','Y',0,0),(73,0,1,'台湾省','71','T',0,0),(74,73,2,'台湾省','1886','T',0,0),(75,0,1,'澳门特别行政区','82','A',0,0),(76,75,2,'澳门特别行政区','1853','A',0,0),(77,0,1,'青海省','63','Q',0,0),(78,77,2,'海北藏族自治州','0970','H',0,0),(79,77,2,'西宁市','0971','X',0,0),(80,77,2,'海东市','0972','H',0,0),(81,77,2,'黄南藏族自治州','0973','H',0,0),(82,77,2,'海南藏族自治州','0974','H',0,0),(83,77,2,'果洛藏族自治州','0975','G',0,0),(84,77,2,'玉树藏族自治州','0976','Y',0,0),(85,77,2,'海西蒙古族藏族自治州','0977','H',0,0),(86,0,1,'江西省','36','J',0,0),(87,86,2,'鹰潭市','0701','Y',0,0),(88,86,2,'新余市','0790','X',0,0),(89,86,2,'南昌市','0791','N',0,0),(90,86,2,'九江市','0792','J',0,0),(91,86,2,'上饶市','0793','S',0,0),(92,86,2,'抚州市','0794','F',0,0),(93,86,2,'宜春市','0795','Y',0,0),(94,86,2,'吉安市','0796','J',0,0),(95,86,2,'赣州市','0797','G',0,0),(96,86,2,'景德镇市','0798','J',0,0),(97,86,2,'萍乡市','0799','P',0,0),(98,0,1,'山东省','37','S',0,0),(99,98,2,'菏泽市','0530','H',0,0),(100,98,2,'济南市','0531','J',0,0),(101,98,2,'青岛市','0532','Q',0,0),(102,98,2,'淄博市','0533','Z',0,0),(103,98,2,'德州市','0534','D',0,0),(104,98,2,'烟台市','0535','Y',0,0),(105,98,2,'潍坊市','0536','W',0,0),(106,98,2,'济宁市','0537','J',0,0),(107,98,2,'泰安市','0538','T',0,0),(108,98,2,'临沂市','0539','L',0,0),(109,98,2,'滨州市','0543','B',0,0),(110,98,2,'东营市','0546','D',0,0),(111,98,2,'威海市','0631','W',0,0),(112,98,2,'枣庄市','0632','Z',0,0),(113,98,2,'日照市','0633','R',0,0),(114,98,2,'聊城市','0635','L',0,0),(115,0,1,'贵州省','52','G',0,0),(116,115,2,'贵阳市','0851','G',0,0),(117,115,2,'遵义市','0852','Z',0,0),(118,115,2,'安顺市','0853','A',0,0),(119,115,2,'黔南布依族苗族自治州','0854','Q',0,0),(120,115,2,'黔东南苗族侗族自治州','0855','Q',0,0),(121,115,2,'铜仁市','0856','T',0,0),(122,115,2,'毕节市','0857','B',0,0),(123,115,2,'六盘水市','0858','L',0,0),(124,115,2,'黔西南布依族苗族自治州','0859','Q',0,0),(125,0,1,'河北省','13','H',0,0),(126,125,2,'邯郸市','0310','H',0,0),(127,125,2,'石家庄市','0311','S',0,0),(128,125,2,'保定市','0312','B',0,0),(129,125,2,'张家口市','0313','Z',0,0),(130,125,2,'承德市','0314','C',0,0),(131,125,2,'唐山市','0315','T',0,0),(132,125,2,'廊坊市','0316','L',0,0),(133,125,2,'沧州市','0317','C',0,0),(134,125,2,'衡水市','0318','H',0,0),(135,125,2,'邢台市','0319','X',0,0),(136,125,2,'秦皇岛市','0335','Q',0,0),(137,0,1,'安徽省','34','A',0,0),(138,137,2,'滁州市','0550','C',0,0),(139,137,2,'合肥市','0551','H',0,0),(140,137,2,'蚌埠市','0552','B',0,0),(141,137,2,'芜湖市','0553','W',0,0),(142,137,2,'淮南市','0554','H',0,0),(143,137,2,'马鞍山市','0555','M',0,0),(144,137,2,'安庆市','0556','A',0,0),(145,137,2,'宿州市','0557','S',0,0),(146,137,2,'亳州市','0558','B',0,0),(147,137,2,'黄山市','0559','H',0,0),(148,137,2,'淮北市','0561','H',0,0),(149,137,2,'铜陵市','0562','T',0,0),(150,137,2,'宣城市','0563','X',0,0),(151,137,2,'六安市','0564','L',0,0),(152,137,2,'池州市','0566','C',0,0),(153,137,2,'阜阳市','1558','F',0,0),(154,0,1,'新疆维吾尔自治区','65','X',0,0),(155,154,2,'塔城地区','0901','T',0,0),(156,154,2,'哈密市','0902','H',0,0),(157,154,2,'和田地区','0903','H',0,0),(158,154,2,'阿勒泰地区','0906','A',0,0),(159,154,2,'克孜勒苏柯尔克孜自治州','0908','K',0,0),(160,154,2,'博尔塔拉蒙古自治州','0909','B',0,0),(161,154,2,'克拉玛依市','0990','K',0,0),(162,154,2,'乌鲁木齐市','0991','W',0,0),(163,154,2,'胡杨河市','0992','H',0,0),(164,154,2,'石河子市','0993','S',0,0),(165,154,2,'昌吉回族自治州','0994','C',0,0),(166,154,2,'吐鲁番市','0995','T',0,0),(167,154,2,'巴音郭楞蒙古自治州','0996','B',0,0),(168,154,2,'阿克苏地区','0997','A',0,0),(169,154,2,'喀什地区','0998','K',0,0),(170,154,2,'伊犁哈萨克自治州','0999','Y',0,0),(171,154,2,'昆玉市','1903','K',0,0),(172,154,2,'北屯市','1906','B',0,0),(173,154,2,'双河市','1909','S',0,0),(174,154,2,'五家渠市','1994','W',0,0),(175,154,2,'铁门关市','1996','T',0,0),(176,154,2,'阿拉尔市','1997','A',0,0),(177,154,2,'图木舒克市','1998','T',0,0),(178,154,2,'可克达拉市','1999','K',0,0),(179,0,1,'北京市','11','B',0,0),(180,179,2,'北京市','010','B',0,0),(181,0,1,'吉林省','22','J',0,0),(182,181,2,'长春市','0431','C',0,0),(183,181,2,'吉林市','0432','J',0,0),(184,181,2,'四平市','0434','S',0,0),(185,181,2,'通化市','0435','T',0,0),(186,181,2,'白城市','0436','B',0,0),(187,181,2,'辽源市','0437','L',0,0),(188,181,2,'松原市','0438','S',0,0),(189,181,2,'白山市','0439','B',0,0),(190,181,2,'延边朝鲜族自治州','1433','Y',0,0),(191,0,1,'重庆市','50','C',0,0),(192,191,2,'重庆市','023','C',0,0),(193,0,1,'广东省','44','G',0,0),(194,193,2,'广州市','020','G',0,0),(195,193,2,'汕尾市','0660','S',0,0),(196,193,2,'阳江市','0662','Y',0,0),(197,193,2,'揭阳市','0663','J',0,0),(198,193,2,'茂名市','0668','M',0,0),(199,193,2,'江门市','0750','J',0,0),(200,193,2,'韶关市','0751','S',0,0),(201,193,2,'惠州市','0752','H',0,0),(202,193,2,'梅州市','0753','M',0,0),(203,193,2,'汕头市','0754','S',0,0),(204,193,2,'深圳市','0755','S',0,0),(205,193,2,'珠海市','0756','Z',0,0),(206,193,2,'佛山市','0757','F',0,0),(207,193,2,'肇庆市','0758','Z',0,0),(208,193,2,'湛江市','0759','Z',0,0),(209,193,2,'中山市','0760','Z',0,0),(210,193,2,'河源市','0762','H',0,0),(211,193,2,'清远市','0763','Q',0,0),(212,193,2,'云浮市','0766','Y',0,0),(213,193,2,'潮州市','0768','C',0,0),(214,193,2,'东莞市','0769','D',0,0),(215,0,1,'湖北省','42','H',0,0),(216,215,2,'武汉市','027','W',0,0),(217,215,2,'襄阳市','0710','X',0,0),(218,215,2,'鄂州市','0711','E',0,0),(219,215,2,'孝感市','0712','X',0,0),(220,215,2,'黄冈市','0713','H',0,0),(221,215,2,'黄石市','0714','H',0,0),(222,215,2,'咸宁市','0715','X',0,0),(223,215,2,'荆州市','0716','J',0,0),(224,215,2,'宜昌市','0717','Y',0,0),(225,215,2,'恩施土家族苗族自治州','0718','E',0,0),(226,215,2,'十堰市','0719','S',0,0),(227,215,2,'随州市','0722','S',0,0),(228,215,2,'荆门市','0724','J',0,0),(229,215,2,'仙桃市','0728','X',0,0),(230,215,2,'神农架林区','1719','S',0,0),(231,215,2,'天门市','1728','T',0,0),(232,215,2,'潜江市','2728','Q',0,0),(233,0,1,'广西壮族自治区','45','G',0,0),(234,233,2,'防城港市','0770','F',0,0),(235,233,2,'南宁市','0771','N',0,0),(236,233,2,'柳州市','0772','L',0,0),(237,233,2,'桂林市','0773','G',0,0),(238,233,2,'梧州市','0774','W',0,0),(239,233,2,'玉林市','0775','Y',0,0),(240,233,2,'百色市','0776','B',0,0),(241,233,2,'钦州市','0777','Q',0,0),(242,233,2,'河池市','0778','H',0,0),(243,233,2,'北海市','0779','B',0,0),(244,233,2,'贵港市','1755','G',0,0),(245,233,2,'崇左市','1771','C',0,0),(246,233,2,'来宾市','1772','L',0,0),(247,233,2,'贺州市','1774','H',0,0),(248,0,1,'辽宁省','21','L',0,0),(249,248,2,'沈阳市','024','S',0,0),(250,248,2,'铁岭市','0410','T',0,0),(251,248,2,'大连市','0411','D',0,0),(252,248,2,'鞍山市','0412','A',0,0),(253,248,2,'抚顺市','0413','F',0,0),(254,248,2,'本溪市','0414','B',0,0),(255,248,2,'丹东市','0415','D',0,0),(256,248,2,'锦州市','0416','J',0,0),(257,248,2,'营口市','0417','Y',0,0),(258,248,2,'阜新市','0418','F',0,0),(259,248,2,'辽阳市','0419','L',0,0),(260,248,2,'朝阳市','0421','C',0,0),(261,248,2,'盘锦市','0427','P',0,0),(262,248,2,'葫芦岛市','0429','H',0,0),(263,0,1,'宁夏回族自治区','64','N',0,0),(264,263,2,'银川市','0951','Y',0,0),(265,263,2,'石嘴山市','0952','S',0,0),(266,263,2,'吴忠市','0953','W',0,0),(267,263,2,'固原市','0954','G',0,0),(268,263,2,'中卫市','1953','Z',0,0),(269,0,1,'浙江省','33','Z',0,0),(270,269,2,'衢州市','0570','Q',0,0),(271,269,2,'杭州市','0571','H',0,0),(272,269,2,'湖州市','0572','H',0,0),(273,269,2,'嘉兴市','0573','J',0,0),(274,269,2,'宁波市','0574','N',0,0),(275,269,2,'绍兴市','0575','S',0,0),(276,269,2,'台州市','0576','T',0,0),(277,269,2,'温州市','0577','W',0,0),(278,269,2,'丽水市','0578','L',0,0),(279,269,2,'金华市','0579','J',0,0),(280,269,2,'舟山市','0580','Z',0,0),(281,0,1,'海南省','46','H',0,0),(282,281,2,'保亭黎族苗族自治县','0801','B',0,0),(283,281,2,'白沙黎族自治县','0802','B',0,0),(284,281,2,'昌江黎族自治县','0803','C',0,0),(285,281,2,'澄迈县','0804','C',0,0),(286,281,2,'儋州市','0805','D',0,0),(287,281,2,'定安县','0806','D',0,0),(288,281,2,'东方市','0807','D',0,0),(289,281,2,'陵水黎族自治县','0809','L',0,0),(290,281,2,'海口市','0898','H',0,0),(291,281,2,'三亚市','0899','S',0,0),(292,281,2,'屯昌县','1892','T',0,0),(293,281,2,'文昌市','1893','W',0,0),(294,281,2,'琼海市','1894','Q',0,0),(295,281,2,'临高县','1896','L',0,0),(296,281,2,'五指山市','1897','W',0,0),(297,281,2,'万宁市','1898','W',0,0),(298,281,2,'琼中黎族苗族自治县','1899','Q',0,0),(299,281,2,'乐东黎族自治县','2802','L',0,0),(300,281,2,'三沙市','2898','S',0,0),(301,0,1,'河南省','41','H',0,0),(302,301,2,'商丘市','0370','S',0,0),(303,301,2,'郑州市','0371','Z',0,0),(304,301,2,'安阳市','0372','A',0,0),(305,301,2,'新乡市','0373','X',0,0),(306,301,2,'许昌市','0374','X',0,0),(307,301,2,'平顶山市','0375','P',0,0),(308,301,2,'信阳市','0376','X',0,0),(309,301,2,'南阳市','0377','N',0,0),(310,301,2,'开封市','0378','K',0,0),(311,301,2,'洛阳市','0379','L',0,0),(312,301,2,'焦作市','0391','J',0,0),(313,301,2,'鹤壁市','0392','H',0,0),(314,301,2,'濮阳市','0393','P',0,0),(315,301,2,'周口市','0394','Z',0,0),(316,301,2,'漯河市','0395','L',0,0),(317,301,2,'驻马店市','0396','Z',0,0),(318,301,2,'三门峡市','0398','S',0,0),(319,301,2,'济源市','1391','J',0,0),(320,0,1,'四川省','51','S',0,0),(321,320,2,'成都市','028','C',0,0),(322,320,2,'攀枝花市','0812','P',0,0),(323,320,2,'自贡市','0813','Z',0,0),(324,320,2,'绵阳市','0816','M',0,0),(325,320,2,'南充市','0817','N',0,0),(326,320,2,'达州市','0818','D',0,0),(327,320,2,'遂宁市','0825','S',0,0),(328,320,2,'广安市','0826','G',0,0),(329,320,2,'巴中市','0827','B',0,0),(330,320,2,'泸州市','0830','L',0,0),(331,320,2,'宜宾市','0831','Y',0,0),(332,320,2,'资阳市','0832','Z',0,0),(333,320,2,'乐山市','0833','L',0,0),(334,320,2,'凉山彝族自治州','0834','L',0,0),(335,320,2,'雅安市','0835','Y',0,0),(336,320,2,'甘孜藏族自治州','0836','G',0,0),(337,320,2,'阿坝藏族羌族自治州','0837','A',0,0),(338,320,2,'德阳市','0838','D',0,0),(339,320,2,'广元市','0839','G',0,0),(340,320,2,'内江市','1832','N',0,0),(341,320,2,'眉山市','1833','M',0,0),(342,0,1,'云南省','53','Y',0,0),(343,342,2,'西双版纳傣族自治州','0691','X',0,0),(344,342,2,'德宏傣族景颇族自治州','0692','D',0,0),(345,342,2,'昭通市','0870','Z',0,0),(346,342,2,'昆明市','0871','K',0,0),(347,342,2,'大理白族自治州','0872','D',0,0),(348,342,2,'红河哈尼族彝族自治州','0873','H',0,0),(349,342,2,'曲靖市','0874','Q',0,0),(350,342,2,'保山市','0875','B',0,0),(351,342,2,'文山壮族苗族自治州','0876','W',0,0),(352,342,2,'玉溪市','0877','Y',0,0),(353,342,2,'楚雄彝族自治州','0878','C',0,0),(354,342,2,'普洱市','0879','P',0,0),(355,342,2,'临沧市','0883','L',0,0),(356,342,2,'怒江傈僳族自治州','0886','N',0,0),(357,342,2,'迪庆藏族自治州','0887','D',0,0),(358,342,2,'丽江市','0888','L',0,0),(359,0,1,'西藏自治区','54','X',0,0),(360,359,2,'拉萨市','0891','L',0,0),(361,359,2,'日喀则市','0892','R',0,0),(362,359,2,'山南市','0893','S',0,0),(363,359,2,'林芝市','0894','L',0,0),(364,359,2,'昌都市','0895','C',0,0),(365,359,2,'那曲市','0896','N',0,0),(366,359,2,'阿里地区','0897','A',0,0),(367,0,1,'陕西省','61','S',0,0),(368,367,2,'西安市','029','X',0,0),(369,367,2,'咸阳市','0910','X',0,0),(370,367,2,'延安市','0911','Y',0,0),(371,367,2,'榆林市','0912','Y',0,0),(372,367,2,'渭南市','0913','W',0,0),(373,367,2,'商洛市','0914','S',0,0),(374,367,2,'安康市','0915','A',0,0),(375,367,2,'汉中市','0916','H',0,0),(376,367,2,'宝鸡市','0917','B',0,0),(377,367,2,'铜川市','0919','T',0,0),(378,0,1,'甘肃省','62','G',0,0),(379,378,2,'临夏回族自治州','0930','L',0,0),(380,378,2,'兰州市','0931','L',0,0),(381,378,2,'定西市','0932','D',0,0),(382,378,2,'平凉市','0933','P',0,0),(383,378,2,'庆阳市','0934','Q',0,0),(384,378,2,'金昌市','0935','J',0,0),(385,378,2,'张掖市','0936','Z',0,0),(386,378,2,'酒泉市','0937','J',0,0),(387,378,2,'天水市','0938','T',0,0),(388,378,2,'甘南藏族自治州','0941','G',0,0),(389,378,2,'白银市','0943','B',0,0),(390,378,2,'武威市','1935','W',0,0),(391,378,2,'嘉峪关市','1937','J',0,0),(392,378,2,'陇南市','2935','L',0,0),(393,0,1,'香港特别行政区','81','X',0,0),(394,393,2,'香港特别行政区','1852','X',0,0),(395,0,1,'福建省','35','F',0,0),(396,395,2,'福州市','0591','F',0,0),(397,395,2,'厦门市','0592','X',0,0),(398,395,2,'宁德市','0593','N',0,0),(399,395,2,'莆田市','0594','P',0,0),(400,395,2,'泉州市','0595','Q',0,0),(401,395,2,'漳州市','0596','Z',0,0),(402,395,2,'龙岩市','0597','L',0,0),(403,395,2,'三明市','0598','S',0,0),(404,395,2,'南平市','0599','N',0,0);
/*!40000 ALTER TABLE `edj_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_config_region`
--

DROP TABLE IF EXISTS `edj_config_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_config_region` (
  `id` bigint unsigned NOT NULL COMMENT '区域id',
  `edj_city_id` int unsigned NOT NULL COMMENT '城市编码',
  `staff_receive_order_max` int unsigned NOT NULL DEFAULT '10' COMMENT '（个体）接单量限制',
  `institution_receive_order_max` int unsigned NOT NULL DEFAULT '100' COMMENT '（企业）接单量限制值',
  `staff_serve_radius` int unsigned NOT NULL DEFAULT '50' COMMENT '（个体）服务范围半径',
  `institution_serve_radius` int unsigned NOT NULL DEFAULT '200' COMMENT '（企业）服务范围半径',
  `diversion_interval` int unsigned NOT NULL DEFAULT '120' COMMENT '分流间隔（单位分钟），即下单时间与服务预计开始时间的间隔',
  `seize_timeout_interval` int unsigned NOT NULL DEFAULT '60' COMMENT '抢单超时时间间隔（单位分钟），从支付成功进入抢单后超过当前时间抢单派单同步进行',
  `dispatch_strategy` tinyint NOT NULL DEFAULT '1' COMMENT '派单策略（1距离优先策略 2评分优先策略 3接单量优先策略）',
  `dispatch_per_round_interval` int unsigned NOT NULL DEFAULT '180' COMMENT '派单每轮时间间隔',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='区域业务配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_config_region`
--

LOCK TABLES `edj_config_region` WRITE;
/*!40000 ALTER TABLE `edj_config_region` DISABLE KEYS */;
INSERT INTO `edj_config_region` VALUES (72910586081849344,237,10,100,50,200,120,60,1,180,'2025-05-02 03:39:15','2025-05-02 03:39:15',1,1,0),(72910793540513792,277,10,100,50,200,120,60,1,180,'2025-05-02 03:40:05','2025-05-02 03:40:05',1,1,0),(72910961048432640,271,10,100,50,200,120,60,1,180,'2025-05-02 03:40:45','2025-05-02 03:40:45',1,1,0),(72911584334589952,30,10,100,50,200,120,60,1,180,'2025-05-02 03:43:13','2025-05-02 03:43:13',1,1,0),(78270570814648320,214,10,100,50,200,120,60,1,180,'2025-05-16 22:38:01','2025-05-16 22:38:01',1,1,0);
/*!40000 ALTER TABLE `edj_config_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_region`
--

DROP TABLE IF EXISTS `edj_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_region` (
  `id` bigint unsigned NOT NULL COMMENT '区域id',
  `edj_city_id` int unsigned NOT NULL COMMENT '城市id',
  `name` varchar(255) NOT NULL COMMENT '区域名称',
  `manager_name` varchar(255) NOT NULL COMMENT '负责人名称',
  `manager_phone` varchar(255) NOT NULL COMMENT '负责人电话',
  `active_status` tinyint NOT NULL DEFAULT '0' COMMENT '是否启用（0草稿 1禁用 2启用）',
  `sort_num` int NOT NULL DEFAULT '0' COMMENT '排序字段',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_region_edj_city_id_index` (`edj_city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='区域表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_region`
--

LOCK TABLES `edj_region` WRITE;
/*!40000 ALTER TABLE `edj_region` DISABLE KEYS */;
INSERT INTO `edj_region` VALUES (72910586081849344,237,'桂林市','戴佳伟','13666666666',2,0,'2025-05-02 03:39:15','2025-05-02 03:49:17',1,1,0),(72910793540513792,277,'温州市','于善鹏','13555555555',2,0,'2025-05-02 03:40:05','2025-05-02 03:49:09',1,1,0),(72910961048432640,271,'杭州市','周翔','13888888888',2,0,'2025-05-02 03:40:45','2025-05-02 03:49:02',1,1,0),(72911584334589952,30,'无锡市','李琼','13999999999',2,0,'2025-05-02 03:43:13','2025-05-02 03:48:15',1,1,0),(78270570814648320,214,'东莞市','杨画','15333333333',2,0,'2025-05-16 22:38:01','2025-05-16 22:41:58',1,1,0);
/*!40000 ALTER TABLE `edj_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_serve`
--

DROP TABLE IF EXISTS `edj_serve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_serve` (
  `id` bigint unsigned NOT NULL COMMENT '服务id',
  `edj_serve_item_id` bigint unsigned NOT NULL COMMENT '服务项id',
  `edj_region_id` bigint unsigned NOT NULL COMMENT '区域id',
  `edj_city_id` int unsigned NOT NULL COMMENT '城市id',
  `sale_status` tinyint NOT NULL DEFAULT '0' COMMENT '售卖状态（0草稿 1下架 2上架）',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `is_hot` tinyint NOT NULL DEFAULT '0' COMMENT '是否为热门（0非热门 1热门）',
  `hot_time` datetime DEFAULT NULL COMMENT '更新为热门的时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_serve_edj_serve_item_id_index` (`edj_serve_item_id`),
  KEY `edj_serve_edj_region_id_id_index` (`edj_region_id`,`id` DESC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='服务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_serve`
--

LOCK TABLES `edj_serve` WRITE;
/*!40000 ALTER TABLE `edj_serve` DISABLE KEYS */;
INSERT INTO `edj_serve` VALUES (72912408217530368,72903255927435264,72911584334589952,30,2,140.00,1,'2025-05-02 03:51:47',0,1,'2025-05-02 03:46:30','2025-05-02 03:51:46',0),(72912408217530369,72894530948837376,72911584334589952,30,2,1.81,0,NULL,0,1,'2025-05-02 03:46:30','2025-05-02 03:48:03',0),(72912408313999360,72907113550458880,72911584334589952,30,2,326.00,0,NULL,0,1,'2025-05-02 03:46:30','2025-05-02 03:50:46',0),(72912408540491776,72901580131020800,72911584334589952,30,2,17.00,1,'2025-05-02 03:51:49',0,1,'2025-05-02 03:46:30','2025-05-02 03:51:48',0),(72912408540491777,72905250474176512,72911584334589952,30,2,21.00,0,NULL,0,1,'2025-05-02 03:46:30','2025-05-02 03:50:44',0),(72912408653737984,72908498379616256,72911584334589952,30,2,100.00,1,'2025-05-02 03:51:38',0,1,'2025-05-02 03:46:30','2025-05-02 03:51:37',0),(72912495266115584,72902646524424193,72910961048432640,271,2,115.00,0,NULL,0,1,'2025-05-02 03:46:50','2025-05-02 03:48:57',0),(72912495270309888,72894530948837376,72910961048432640,271,0,1.81,0,NULL,0,0,'2025-05-02 03:46:50','2025-05-02 03:46:50',0),(72912495400333312,72906123162038273,72910961048432640,271,0,598.00,0,NULL,0,0,'2025-05-02 03:46:50','2025-05-02 03:46:50',0),(72912495593271296,72903775048052737,72910961048432640,271,2,149.00,0,NULL,0,1,'2025-05-02 03:46:50','2025-05-02 03:48:52',0),(72912495593271297,72900776691118081,72910961048432640,271,0,2.55,0,NULL,0,0,'2025-05-02 03:46:50','2025-05-02 03:46:50',0),(72912495689740288,72907779886952448,72910961048432640,271,2,226.60,0,NULL,0,1,'2025-05-02 03:46:50','2025-05-02 03:48:49',0),(72912601306509312,72894530948837376,72910793540513792,277,0,1.81,0,NULL,0,0,'2025-05-02 03:47:16','2025-05-02 03:47:16',0),(72912601306509313,72905250474176512,72910793540513792,277,0,21.00,0,NULL,0,0,'2025-05-02 03:47:16','2025-05-02 03:47:16',0),(72912601327480832,72904292314787840,72910793540513792,277,0,129.00,0,NULL,0,0,'2025-05-02 03:47:16','2025-05-02 03:47:16',0),(72912601465892864,72908498379616256,72910793540513792,277,0,100.00,0,NULL,0,0,'2025-05-02 03:47:16','2025-05-02 03:47:16',0),(72912601591721984,72907779886952448,72910793540513792,277,0,226.60,0,NULL,0,0,'2025-05-02 03:47:16','2025-05-02 03:47:16',0),(72912601595916288,72900776691118081,72910793540513792,277,0,2.55,0,NULL,0,0,'2025-05-02 03:47:16','2025-05-02 03:47:16',0),(72912601679802368,72903775048052737,72910793540513792,277,2,149.00,0,NULL,0,1,'2025-05-02 03:47:16','2025-05-02 03:49:06',0),(72912710006091776,72901580131020800,72910586081849344,237,0,17.00,0,NULL,0,0,'2025-05-02 03:47:42','2025-05-02 03:47:42',0),(72912710006091777,72900776691118081,72910586081849344,237,0,2.55,0,NULL,0,0,'2025-05-02 03:47:42','2025-05-02 03:47:42',0),(72912710014480384,72905250474176512,72910586081849344,237,0,21.00,0,NULL,0,0,'2025-05-02 03:47:42','2025-05-02 03:47:42',0),(72912710014480385,72904292314787840,72910586081849344,237,0,129.00,0,NULL,0,0,'2025-05-02 03:47:42','2025-05-02 03:47:42',0),(72912710161281024,72907113550458880,72910586081849344,237,0,326.00,0,NULL,0,0,'2025-05-02 03:47:42','2025-05-02 03:47:42',0),(72912710295498752,72899828094742528,72910586081849344,237,0,2.08,0,NULL,0,0,'2025-05-02 03:47:42','2025-05-02 03:47:42',0),(72912710299693056,72903255927435264,72910586081849344,237,0,140.00,0,NULL,0,0,'2025-05-02 03:47:42','2025-05-02 03:47:42',0),(72912710303887360,72907779886952448,72910586081849344,237,0,226.60,0,NULL,0,0,'2025-05-02 03:47:42','2025-05-02 03:47:42',0),(72912710350024704,72906123162038273,72910586081849344,237,2,598.00,0,NULL,0,1,'2025-05-02 03:47:42','2025-05-02 03:49:13',0),(72913288606134272,72904292314787840,72911584334589952,30,2,129.00,1,'2025-05-02 03:51:16',0,1,'2025-05-02 03:50:00','2025-05-02 03:51:15',0),(72913566365528064,72899828094742528,72911584334589952,30,2,2.08,1,'2025-05-02 03:51:35',0,1,'2025-05-02 03:51:06','2025-05-02 03:51:34',0),(78270758564278272,72905250474176512,78270570814648320,214,2,21.00,1,'2025-05-16 22:41:47',0,1,'2025-05-16 22:38:45','2025-05-16 22:41:51',0),(78270758564278273,72900776691118081,78270570814648320,214,2,2.55,1,'2025-05-16 22:41:45',0,1,'2025-05-16 22:38:45','2025-05-16 22:41:50',0),(78270758564278274,72902646524424193,78270570814648320,214,2,115.00,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:40:23',0),(78270758568472576,72907779886952448,78270570814648320,214,2,226.60,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:40:20',0),(78270758568472577,72903775048052737,78270570814648320,214,2,149.00,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:40:18',0),(78270758568472578,72894530948837376,78270570814648320,214,2,1.81,1,'2025-05-16 22:41:42',0,1,'2025-05-16 22:38:45','2025-05-16 22:41:47',0),(78270758602027008,72908498379616256,78270570814648320,214,2,100.00,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:40:10',0),(78270758887239680,72901580131020800,78270570814648320,214,2,17.00,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:40:06',0),(78270758954348544,72904292314787840,78270570814648320,214,2,129.00,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:39:59',0),(78270758962737152,72899828094742528,78270570814648320,214,2,2.08,1,'2025-05-16 22:41:40',0,1,'2025-05-16 22:38:45','2025-05-16 22:41:45',0),(78270758962737153,72903255927435264,78270570814648320,214,2,140.00,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:39:56',0),(78270758975320064,72906123162038273,78270570814648320,214,2,598.00,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:39:54',0),(78270758987902976,72907113550458880,78270570814648320,214,2,326.00,0,NULL,0,1,'2025-05-16 22:38:45','2025-05-16 22:39:52',0);
/*!40000 ALTER TABLE `edj_serve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_serve_item`
--

DROP TABLE IF EXISTS `edj_serve_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_serve_item` (
  `id` bigint unsigned NOT NULL COMMENT '服务项id',
  `code` varchar(255) NOT NULL COMMENT '服务编码',
  `edj_serve_type_id` bigint unsigned NOT NULL COMMENT '服务类型id',
  `name` varchar(255) NOT NULL COMMENT '服务名称',
  `icon` varchar(255) NOT NULL COMMENT '服务图标',
  `img` varchar(255) NOT NULL COMMENT '服务图片',
  `unit` tinyint NOT NULL COMMENT '服务数量单位(1小时 2天 3次 4台 5个 6㎡ 7米)',
  `description` varchar(500) NOT NULL DEFAULT '' COMMENT '服务描述',
  `detail_img` varchar(255) NOT NULL DEFAULT '' COMMENT '服务详图',
  `reference_price` decimal(10,2) NOT NULL COMMENT '参考价格',
  `sort_num` int NOT NULL DEFAULT '0' COMMENT '排序字段',
  `active_status` tinyint NOT NULL DEFAULT '0' COMMENT '活动状态（0草稿 1禁用 2启用）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_serve_item_name_index` (`name`),
  KEY `edj_serve_item_is_delete_sort_num_id_active_status_index` (`is_delete`,`sort_num`,`id` DESC,`active_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='服务项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_serve_item`
--

LOCK TABLES `edj_serve_item` WRITE;
/*!40000 ALTER TABLE `edj_serve_item` DISABLE KEYS */;
INSERT INTO `edj_serve_item` VALUES (72894530948837376,'EDJ_20250502023528356_03030000',72873557365239808,'日常清洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/238f7419-b92a-43d6-be5e-83810b7c84d7.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',6,'注：① 如果要使用天鹅到家清洁剂，每小时多加收5元；\n② 因预约资源紧张，部分时段会收取调度费；调度费不能享受会员卡折扣，不能使用优惠券折扣；\n③ 房屋面积及对应的服务时长仅供参考，请您根据打扫面积及清洁程度合理预估服务时长；','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/cdb7627b-7af6-4685-adf0-093af1595f0d.png',1.81,1,2,'2025-05-02 02:35:27','2025-05-02 03:32:01',1,1,0),(72899828094742528,'EDJ_20250502025631305_03030000',72873557365239808,'深度清洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f89df78-2dbb-4439-b773-d14ec3f83ef2.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/16cc1050-c29a-436b-8844-610cde65d12c.jpg',6,'1客厅/卧室：地面、天花板、灯具无尘、窗帘吸尘。\n2厨房：天花板、墙面、油烟机、灶台无油污，橱柜内部清洁，高温消毒。\n3卫生间：天花板、墙面、面盆、浴缸、龙头花洒、马桶无水垢，高温消毒。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e154e6d6-3fc5-4c8c-bcea-0d3199a42546.jpg',2.08,2,2,'2025-05-02 02:56:30','2025-05-02 03:34:53',1,1,0),(72900776691118081,'EDJ_20250502030017477_03030000',72873557365239808,'开荒保洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/f72865e1-ecb4-46e0-8d34-c91fdc319616.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/253f5502-202d-4895-a7e9-d0f94d712f49.jpg',6,'涂料、水泥点、玻璃胶等装修痕迹清除\n墙面、顶棚除尘、地面清洁、内窗玻璃清洁\n厨卫家具内外清洁，其余家具表面除尘\n家电表面清洁、瓷砖、五金件等清洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbff2dc-5438-4c2c-8098-453e25be13cb.jpg',2.55,3,2,'2025-05-02 03:00:16','2025-05-02 03:34:55',1,1,0),(72901580131020800,'EDJ_20250502030329030_03030000',72874351573479425,'擦玻璃','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/16c9cf20-03c9-4dcf-b6a4-e321b8283859.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/608fb282-41d3-46d8-8261-c3cb86c663bb.jpg',6,'采用“倒U型”路径清洁，融合创新清洁器，\n明亮窗外色彩。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/9e9c38e7-67b3-4597-93ec-e9ff96d5dcbf.jpg',17.00,4,2,'2025-05-02 03:03:28','2025-05-02 03:32:04',1,1,0),(72902646524424193,'EDJ_20250502030743280_03030000',72874351573479425,'空调清洗','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/63661117-73eb-459c-88f8-819e1f835241.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/15e60408-e3cd-4a09-8838-9d7ba1fe768a.jpg',4,'双重清洁剂去污，高温消毒，延长使用寿命，\n和家人一起轻松呼吸。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/8baa25b0-4e96-441d-a4fd-d95b3f414657.jpg',115.00,5,2,'2025-05-02 03:07:42','2025-05-02 03:34:57',1,1,0),(72903255927435264,'EDJ_20250502031008572_03030000',72874351573479425,'冰箱清洗','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/38ec5ba5-5266-4ae6-be91-bf00834fef04.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/b9e5f827-021c-42b1-82ea-a9be56d66acb.jpg',4,'除冰、除味，高温消毒，\n还您健康储食环境。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/364f5bbf-f98c-4b90-ac9b-f498aadffb53.jpg',140.00,6,2,'2025-05-02 03:10:08','2025-05-02 03:32:30',1,1,0),(72903775048052737,'EDJ_20250502031212341_03030000',72874351573479425,'油烟机清洗','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4949726a-25f9-410d-b06d-c329dba7f17c.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/fecacad0-6a58-4ed7-aa78-fa2aa1c85b34.jpg',4,'除油消毒，让您告别油烟熏呛，\n消除厨房安全隐患。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/119bfe9c-03fb-4536-b9f1-118f6addfefa.jpg',149.00,7,2,'2025-05-02 03:12:11','2025-05-02 03:35:00',1,1,0),(72904292314787840,'EDJ_20250502031415666_03030000',72874351573479425,'洗衣机清洗','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/682d0205-1d97-4387-8f68-56edbe3f3868.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/d241afea-a704-4ea7-a2fb-257b41de86a6.jpg',4,'杀菌除污垢，衣服不再越洗越脏，\n让衣服与肌肤放心接触。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/fb7a40b4-764e-4911-88e0-d14c2ce89178.jpg',129.00,9,2,'2025-05-02 03:14:15','2025-05-02 03:35:04',1,1,0),(72905250474176512,'EDJ_20250502031804107_03030000',72875199204569088,'地板打蜡','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/5bf04c3b-efc5-4a33-98cc-706a3a339493.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/90a35c54-8eb5-40c0-8d57-f1408680a926.jpg',6,'清除地板顽固污渍隔绝空气、水汽、灰尘，\n提高地板使用寿命。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/495f30f7-9fc0-492a-9423-ea0fddee5870.png',21.00,8,2,'2025-05-02 03:18:03','2025-05-02 03:35:02',1,1,0),(72906123162038273,'EDJ_20250502032132175_03030000',72875199204569088,'除尘除螨','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/793a1269-474b-430c-9d61-844453a67843.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2d925ce0-e2c0-459b-abce-c6487cbf31b5.jpg',3,'消灭螨虫危害，高温蒸汽杀毒，\n健康您的居住环境。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/0633a3d5-d458-46d8-a258-960feeb2a690.jpg',598.00,10,2,'2025-05-02 03:21:31','2025-05-02 03:32:09',1,1,0),(72907113550458880,'EDJ_20250502032528301_03030000',72876349135925248,'月嫂','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/06754d73-ca5e-4cdf-b66c-6c2c4172a737.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/61bf5b7f-7146-455e-ad70-7e6925c15913.jpg',2,'新妈妈与宝宝的生活照顾与专业护理。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/cc7387a3-4abc-42a2-91bf-1d344dc50225.jpg',326.00,11,2,'2025-05-02 03:25:27','2025-05-02 03:35:12',1,1,0),(72907779886952448,'EDJ_20250502032807168_03030000',72876349135925248,'育儿嫂','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e635f8df-65bf-463a-b71d-9d09a8c46448.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e21e136d-9c85-4a52-8cea-a74574949c2c.jpg',2,'专业育儿嫂呵护宝宝的成长，培养宝宝的良好习惯。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/7bf6f2b9-60ba-4877-bd24-bb7f2d8dc845.jpg',226.60,12,2,'2025-05-02 03:28:06','2025-05-02 03:35:15',1,1,0),(72908498379616256,'EDJ_20250502033058470_03030000',72876349135925248,'保姆','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/0eb6f16e-52e5-4b0d-bb2a-adb98a980125.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4628da4b-a187-4a81-b476-9d0ae75ff25f.jpg',2,'负责家庭的日常清洁维护、做饭、协助照顾老人、小孩以及客户吩咐的其他事物等。','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/5b007d16-c7a9-45c0-abbd-426f818793ec.png',100.00,13,2,'2025-05-02 03:30:57','2025-05-02 03:35:18',1,1,0);
/*!40000 ALTER TABLE `edj_serve_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_serve_sync`
--

DROP TABLE IF EXISTS `edj_serve_sync`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_serve_sync` (
  `id` bigint unsigned NOT NULL COMMENT '服务id',
  `edj_serve_type_id` bigint unsigned NOT NULL COMMENT '服务类型id',
  `edj_serve_item_id` bigint unsigned NOT NULL COMMENT '服务项id',
  `edj_city_id` int unsigned NOT NULL COMMENT '城市编码',
  `serve_item_name` varchar(100) NOT NULL COMMENT '服务项名称',
  `serve_type_name` varchar(255) NOT NULL COMMENT '服务类型名称',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `is_hot` tinyint NOT NULL DEFAULT '0' COMMENT '是否为热门（0非热门 1热门）',
  `hot_time` datetime DEFAULT NULL COMMENT '更新为热门的时间戳',
  `serve_item_sort_num` int NOT NULL DEFAULT '0' COMMENT '服务项排序字段',
  `serve_type_sort_num` int NOT NULL DEFAULT '0' COMMENT '服务类型排序字段',
  `serve_type_img` varchar(255) NOT NULL COMMENT '服务类型图片',
  `serve_type_icon` varchar(255) NOT NULL COMMENT '服务类型icon',
  `unit` tinyint NOT NULL COMMENT '服务数量单位',
  `detail_img` varchar(255) NOT NULL COMMENT '服务项详情图片',
  `serve_item_img` varchar(255) NOT NULL COMMENT '服务项图片',
  `serve_item_icon` varchar(255) NOT NULL COMMENT '服务项图标',
  PRIMARY KEY (`id`),
  KEY `edj_serve_sync_edj_serve_item_id_index` (`edj_serve_item_id`),
  KEY `edj_serve_sync_edj_serve_type_id_index` (`edj_serve_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='服务同步表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_serve_sync`
--

LOCK TABLES `edj_serve_sync` WRITE;
/*!40000 ALTER TABLE `edj_serve_sync` DISABLE KEYS */;
INSERT INTO `edj_serve_sync` VALUES (72912408217530368,72874351573479425,72903255927435264,30,'冰箱清洗','清洁服务',140.00,1,'2025-05-02 03:51:47',6,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/364f5bbf-f98c-4b90-ac9b-f498aadffb53.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/b9e5f827-021c-42b1-82ea-a9be56d66acb.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/38ec5ba5-5266-4ae6-be91-bf00834fef04.png'),(72912408217530369,72873557365239808,72894530948837376,30,'日常清洁','保洁服务',1.81,0,NULL,1,1,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/41788505-9c64-49f0-a623-e7118305af28.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f8eb4e2-23f5-4a43-98b6-f2624c92bb30.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/cdb7627b-7af6-4685-adf0-093af1595f0d.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/238f7419-b92a-43d6-be5e-83810b7c84d7.png'),(72912408313999360,72876349135925248,72907113550458880,30,'月嫂','母婴护理',326.00,0,NULL,11,4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/50144ed0-1e17-496f-ac83-aa3d7c461ae0.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/776db5d6-ceda-431e-b8b3-e616e045c732.png',2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/cc7387a3-4abc-42a2-91bf-1d344dc50225.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/61bf5b7f-7146-455e-ad70-7e6925c15913.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/06754d73-ca5e-4cdf-b66c-6c2c4172a737.png'),(72912408540491776,72874351573479425,72901580131020800,30,'擦玻璃','清洁服务',17.00,1,'2025-05-02 03:51:49',4,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/9e9c38e7-67b3-4597-93ec-e9ff96d5dcbf.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/608fb282-41d3-46d8-8261-c3cb86c663bb.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/16c9cf20-03c9-4dcf-b6a4-e321b8283859.png'),(72912408540491777,72875199204569088,72905250474176512,30,'地板打蜡','家居养护',21.00,0,NULL,8,3,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/6aa078b9-66ab-46a3-9955-1c7c6b423b7c.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c02aa117-d531-42b6-ba20-494bef9a0162.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/495f30f7-9fc0-492a-9423-ea0fddee5870.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/90a35c54-8eb5-40c0-8d57-f1408680a926.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/5bf04c3b-efc5-4a33-98cc-706a3a339493.png'),(72912408653737984,72876349135925248,72908498379616256,30,'保姆','母婴护理',100.00,1,'2025-05-02 03:51:38',13,4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/50144ed0-1e17-496f-ac83-aa3d7c461ae0.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/776db5d6-ceda-431e-b8b3-e616e045c732.png',2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/5b007d16-c7a9-45c0-abbd-426f818793ec.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4628da4b-a187-4a81-b476-9d0ae75ff25f.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/0eb6f16e-52e5-4b0d-bb2a-adb98a980125.png'),(72912495266115584,72874351573479425,72902646524424193,271,'空调清洗','清洁服务',115.00,0,NULL,5,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/8baa25b0-4e96-441d-a4fd-d95b3f414657.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/15e60408-e3cd-4a09-8838-9d7ba1fe768a.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/63661117-73eb-459c-88f8-819e1f835241.png'),(72912495593271296,72874351573479425,72903775048052737,271,'油烟机清洗','清洁服务',149.00,0,NULL,7,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/119bfe9c-03fb-4536-b9f1-118f6addfefa.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/fecacad0-6a58-4ed7-aa78-fa2aa1c85b34.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4949726a-25f9-410d-b06d-c329dba7f17c.png'),(72912495689740288,72876349135925248,72907779886952448,271,'育儿嫂','母婴护理',226.60,0,NULL,12,4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/50144ed0-1e17-496f-ac83-aa3d7c461ae0.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/776db5d6-ceda-431e-b8b3-e616e045c732.png',2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/7bf6f2b9-60ba-4877-bd24-bb7f2d8dc845.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e21e136d-9c85-4a52-8cea-a74574949c2c.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e635f8df-65bf-463a-b71d-9d09a8c46448.png'),(72912601679802368,72874351573479425,72903775048052737,277,'油烟机清洗','清洁服务',149.00,0,NULL,7,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/119bfe9c-03fb-4536-b9f1-118f6addfefa.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/fecacad0-6a58-4ed7-aa78-fa2aa1c85b34.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4949726a-25f9-410d-b06d-c329dba7f17c.png'),(72912710350024704,72875199204569088,72906123162038273,237,'除尘除螨','家居养护',598.00,0,NULL,10,3,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/6aa078b9-66ab-46a3-9955-1c7c6b423b7c.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c02aa117-d531-42b6-ba20-494bef9a0162.png',3,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/0633a3d5-d458-46d8-a258-960feeb2a690.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2d925ce0-e2c0-459b-abce-c6487cbf31b5.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/793a1269-474b-430c-9d61-844453a67843.png'),(72913288606134272,72874351573479425,72904292314787840,30,'洗衣机清洗','清洁服务',129.00,1,'2025-05-02 03:51:16',9,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/fb7a40b4-764e-4911-88e0-d14c2ce89178.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/d241afea-a704-4ea7-a2fb-257b41de86a6.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/682d0205-1d97-4387-8f68-56edbe3f3868.png'),(72913566365528064,72873557365239808,72899828094742528,30,'深度清洁','保洁服务',2.08,1,'2025-05-02 03:51:35',2,1,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/41788505-9c64-49f0-a623-e7118305af28.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f8eb4e2-23f5-4a43-98b6-f2624c92bb30.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e154e6d6-3fc5-4c8c-bcea-0d3199a42546.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/16cc1050-c29a-436b-8844-610cde65d12c.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f89df78-2dbb-4439-b773-d14ec3f83ef2.png'),(78270758564278272,72875199204569088,72905250474176512,214,'地板打蜡','家居养护',21.00,1,'2025-05-16 22:41:47',8,3,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/6aa078b9-66ab-46a3-9955-1c7c6b423b7c.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c02aa117-d531-42b6-ba20-494bef9a0162.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/495f30f7-9fc0-492a-9423-ea0fddee5870.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/90a35c54-8eb5-40c0-8d57-f1408680a926.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/5bf04c3b-efc5-4a33-98cc-706a3a339493.png'),(78270758564278273,72873557365239808,72900776691118081,214,'开荒保洁','保洁服务',2.55,1,'2025-05-16 22:41:45',3,1,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/41788505-9c64-49f0-a623-e7118305af28.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f8eb4e2-23f5-4a43-98b6-f2624c92bb30.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/ffbff2dc-5438-4c2c-8098-453e25be13cb.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/253f5502-202d-4895-a7e9-d0f94d712f49.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/f72865e1-ecb4-46e0-8d34-c91fdc319616.png'),(78270758564278274,72874351573479425,72902646524424193,214,'空调清洗','清洁服务',115.00,0,NULL,5,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/8baa25b0-4e96-441d-a4fd-d95b3f414657.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/15e60408-e3cd-4a09-8838-9d7ba1fe768a.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/63661117-73eb-459c-88f8-819e1f835241.png'),(78270758568472576,72876349135925248,72907779886952448,214,'育儿嫂','母婴护理',226.60,0,NULL,12,4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/50144ed0-1e17-496f-ac83-aa3d7c461ae0.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/776db5d6-ceda-431e-b8b3-e616e045c732.png',2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/7bf6f2b9-60ba-4877-bd24-bb7f2d8dc845.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e21e136d-9c85-4a52-8cea-a74574949c2c.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e635f8df-65bf-463a-b71d-9d09a8c46448.png'),(78270758568472577,72874351573479425,72903775048052737,214,'油烟机清洗','清洁服务',149.00,0,NULL,7,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/119bfe9c-03fb-4536-b9f1-118f6addfefa.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/fecacad0-6a58-4ed7-aa78-fa2aa1c85b34.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4949726a-25f9-410d-b06d-c329dba7f17c.png'),(78270758568472578,72873557365239808,72894530948837376,214,'日常清洁','保洁服务',1.81,1,'2025-05-16 22:41:42',1,1,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/41788505-9c64-49f0-a623-e7118305af28.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f8eb4e2-23f5-4a43-98b6-f2624c92bb30.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/cdb7627b-7af6-4685-adf0-093af1595f0d.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/238f7419-b92a-43d6-be5e-83810b7c84d7.png'),(78270758602027008,72876349135925248,72908498379616256,214,'保姆','母婴护理',100.00,0,NULL,13,4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/50144ed0-1e17-496f-ac83-aa3d7c461ae0.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/776db5d6-ceda-431e-b8b3-e616e045c732.png',2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/5b007d16-c7a9-45c0-abbd-426f818793ec.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/4628da4b-a187-4a81-b476-9d0ae75ff25f.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/0eb6f16e-52e5-4b0d-bb2a-adb98a980125.png'),(78270758887239680,72874351573479425,72901580131020800,214,'擦玻璃','清洁服务',17.00,0,NULL,4,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/9e9c38e7-67b3-4597-93ec-e9ff96d5dcbf.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/608fb282-41d3-46d8-8261-c3cb86c663bb.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/16c9cf20-03c9-4dcf-b6a4-e321b8283859.png'),(78270758954348544,72874351573479425,72904292314787840,214,'洗衣机清洗','清洁服务',129.00,0,NULL,9,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/fb7a40b4-764e-4911-88e0-d14c2ce89178.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/d241afea-a704-4ea7-a2fb-257b41de86a6.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/682d0205-1d97-4387-8f68-56edbe3f3868.png'),(78270758962737152,72873557365239808,72899828094742528,214,'深度清洁','保洁服务',2.08,1,'2025-05-16 22:41:40',2,1,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/41788505-9c64-49f0-a623-e7118305af28.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f8eb4e2-23f5-4a43-98b6-f2624c92bb30.png',6,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e154e6d6-3fc5-4c8c-bcea-0d3199a42546.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/16cc1050-c29a-436b-8844-610cde65d12c.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f89df78-2dbb-4439-b773-d14ec3f83ef2.png'),(78270758962737153,72874351573479425,72903255927435264,214,'冰箱清洗','清洁服务',140.00,0,NULL,6,2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png',4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/364f5bbf-f98c-4b90-ac9b-f498aadffb53.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/b9e5f827-021c-42b1-82ea-a9be56d66acb.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/38ec5ba5-5266-4ae6-be91-bf00834fef04.png'),(78270758975320064,72875199204569088,72906123162038273,214,'除尘除螨','家居养护',598.00,0,NULL,10,3,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/6aa078b9-66ab-46a3-9955-1c7c6b423b7c.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c02aa117-d531-42b6-ba20-494bef9a0162.png',3,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/0633a3d5-d458-46d8-a258-960feeb2a690.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2d925ce0-e2c0-459b-abce-c6487cbf31b5.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/793a1269-474b-430c-9d61-844453a67843.png'),(78270758987902976,72876349135925248,72907113550458880,214,'月嫂','母婴护理',326.00,0,NULL,11,4,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/50144ed0-1e17-496f-ac83-aa3d7c461ae0.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/776db5d6-ceda-431e-b8b3-e616e045c732.png',2,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/cc7387a3-4abc-42a2-91bf-1d344dc50225.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/61bf5b7f-7146-455e-ad70-7e6925c15913.jpg','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/06754d73-ca5e-4cdf-b66c-6c2c4172a737.png');
/*!40000 ALTER TABLE `edj_serve_sync` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_serve_type`
--

DROP TABLE IF EXISTS `edj_serve_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_serve_type` (
  `id` bigint unsigned NOT NULL COMMENT '服务类型id',
  `code` varchar(255) NOT NULL COMMENT '服务类型编码',
  `name` varchar(255) NOT NULL COMMENT '服务类型名称',
  `icon` varchar(255) NOT NULL COMMENT '服务类型图标',
  `img` varchar(255) NOT NULL COMMENT '服务类型图片',
  `sort_num` int NOT NULL DEFAULT '0' COMMENT '排序字段',
  `active_status` tinyint NOT NULL DEFAULT '0' COMMENT '是否启用（0草稿 1禁用 2启用）',
  `create_by` bigint NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_serve_type_index` (`is_delete`,`sort_num`,`id` DESC),
  KEY `edj_serve_type_name_index` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='服务类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_serve_type`
--

LOCK TABLES `edj_serve_type` WRITE;
/*!40000 ALTER TABLE `edj_serve_type` DISABLE KEYS */;
INSERT INTO `edj_serve_type` VALUES (72873557365239808,'EDJ_20250502011207872_03030000','保洁服务','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/2f8eb4e2-23f5-4a43-98b6-f2624c92bb30.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/41788505-9c64-49f0-a623-e7118305af28.png',1,2,1,1,'2025-05-02 01:12:07','2025-05-02 02:26:42',0),(72874351573479425,'EDJ_20250502011517238_03030000','清洁服务','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/369136eb-d88a-4c44-a03d-bc816eae6f9e.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/20815dd0-06a1-409e-83bc-c41b20981a99.png',2,2,1,1,'2025-05-02 01:15:16','2025-05-02 02:27:09',0),(72875199204569088,'EDJ_20250502011839328_03030000','家居养护','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/c02aa117-d531-42b6-ba20-494bef9a0162.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/6aa078b9-66ab-46a3-9955-1c7c6b423b7c.png',3,2,1,1,'2025-05-02 01:18:38','2025-05-02 03:15:24',0),(72876349135925248,'EDJ_20250502012313493_03030000','母婴护理','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/776db5d6-ceda-431e-b8b3-e616e045c732.png','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/50144ed0-1e17-496f-ac83-aa3d7c461ae0.png',4,2,1,1,'2025-05-02 01:23:12','2025-05-02 02:27:13',0);
/*!40000 ALTER TABLE `edj_serve_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT transaction mode undo table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `edj-market`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-market` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-market`;

--
-- Table structure for table `edj_activity`
--

DROP TABLE IF EXISTS `edj_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_activity` (
  `id` bigint unsigned NOT NULL COMMENT '优惠券活动表id',
  `name` varchar(128) NOT NULL COMMENT '优惠券活动名称',
  `type` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '优惠券类型（1满减 2折扣）',
  `amount_condition` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '使用条件（最低消费金额）',
  `discount_rate` tinyint unsigned NOT NULL DEFAULT '100' COMMENT '折扣率（单位%，折扣类型优惠券）',
  `discount_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额（单位元，满减类型优惠券）',
  `validity_days` int unsigned NOT NULL DEFAULT '0' COMMENT '优惠券有效期天数（0：表示指定截止时间）',
  `distribute_start_time` datetime NOT NULL COMMENT '开始发放时间',
  `distribute_end_time` datetime NOT NULL COMMENT '发放结束时间',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '活动状态（1待生效 2进行中 3已失效 4已作废）',
  `total_num` int unsigned NOT NULL DEFAULT '0' COMMENT '发放数量（0：表示无限量）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='优惠券活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_activity`
--

LOCK TABLES `edj_activity` WRITE;
/*!40000 ALTER TABLE `edj_activity` DISABLE KEYS */;
INSERT INTO `edj_activity` VALUES (72922492612538368,'618大促',1,200.00,100,20.00,3,'2025-05-02 00:00:00','2025-05-04 00:00:00',3,100,1,1,'2025-05-02 04:26:34','2025-05-16 23:08:15',0),(72923166087737344,'平台促销活动',2,200.00,50,0.00,3,'2025-05-02 00:00:00','2025-05-04 00:00:00',3,50,1,1,'2025-05-02 04:29:15','2025-05-16 23:08:15',0),(73158466365976576,'平台66大促',2,60.00,60,0.00,6,'2025-05-04 00:00:00','2025-05-05 00:00:00',3,666,1,1,'2025-05-02 20:04:14','2025-05-16 23:08:15',0),(73161016272121856,'平台11大促',2,1.00,50,0.00,11,'2025-05-02 00:00:00','2025-05-04 00:00:00',3,11,1,1,'2025-05-02 20:14:22','2025-05-16 23:08:15',0),(78275787585646592,'平台15大促',1,115.00,100,114.00,15,'2025-05-16 00:00:00','2025-05-18 00:00:00',2,15,1,1,'2025-05-16 22:58:44','2025-05-16 23:13:15',0),(78276089034469376,'平台16活动',2,16.00,96,0.00,16,'2025-05-16 00:00:00','2025-05-18 00:00:00',2,16,1,1,'2025-05-16 22:59:56','2025-05-16 23:13:15',0),(78279541550641152,'88大促',2,88.00,88,0.00,88,'2025-05-18 00:00:00','2025-05-28 00:00:00',1,88,1,1,'2025-05-16 23:13:39','2025-05-16 23:13:39',0),(78279646416629760,'99大促',1,999.00,100,99.00,99,'2025-05-19 00:00:00','2025-05-29 00:00:00',1,99,1,1,'2025-05-16 23:14:04','2025-05-16 23:14:04',0),(78307343448567808,'66大促',1,666.00,100,66.00,66,'2025-05-16 00:00:00','2025-05-25 00:00:00',2,66,1,1,'2025-05-17 01:04:08','2025-05-17 01:05:15',0),(78307431742861312,'77大促',2,77.00,77,0.00,77,'2025-05-17 00:00:00','2025-05-25 00:00:00',2,77,1,1,'2025-05-17 01:04:29','2025-05-17 01:05:15',0);
/*!40000 ALTER TABLE `edj_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_coupon`
--

DROP TABLE IF EXISTS `edj_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_coupon` (
  `id` bigint unsigned NOT NULL COMMENT '优惠券id',
  `edj_user_id` bigint unsigned NOT NULL COMMENT '优惠券拥有者',
  `edj_activity_id` bigint unsigned NOT NULL COMMENT '优惠券活动id',
  `edj_orders_id` bigint unsigned DEFAULT NULL COMMENT '订单id',
  `name` varchar(255) NOT NULL COMMENT '优惠券名称',
  `username` varchar(32) NOT NULL COMMENT '用户账号',
  `nickname` varchar(32) NOT NULL COMMENT '用户名称',
  `user_phone` char(11) NOT NULL COMMENT '用户手机号',
  `type` tinyint unsigned NOT NULL COMMENT '优惠券类型（1满减 2折扣）',
  `discount_rate` tinyint unsigned NOT NULL COMMENT '折扣率（单位%，折扣类型优惠券）',
  `discount_amount` decimal(10,2) NOT NULL COMMENT '优惠金额（单位元，满减类型优惠券）',
  `amount_condition` decimal(10,2) NOT NULL COMMENT '使用条件（最低消费金额）',
  `validity_time` datetime NOT NULL COMMENT '有效期',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '优惠券状态（1未使用 2已使用 3已过期 4已作废）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `edj_coupon_pk` (`edj_activity_id`,`edj_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户优惠券表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_coupon`
--

LOCK TABLES `edj_coupon` WRITE;
/*!40000 ALTER TABLE `edj_coupon` DISABLE KEYS */;
INSERT INTO `edj_coupon` VALUES (72945550320627712,8288489957834752,72922492612538368,NULL,'618大促','EDJ_8288489542598656','EDJ_8288489542598656','',1,100,20.00,200.00,'2025-05-05 05:58:12',NULL,3,0,0,'2025-05-02 05:58:11','2025-05-16 23:12:24',0),(72945585204654080,8288489957834752,72923166087737344,NULL,'平台促销活动','EDJ_8288489542598656','EDJ_8288489542598656','',2,50,0.00,200.00,'2025-05-05 05:58:21',NULL,3,0,0,'2025-05-02 05:58:20','2025-05-16 23:12:23',0),(73161303498059776,8288489957834752,73161016272121856,NULL,'平台11大促','EDJ_8288489542598656','EDJ_8288489542598656','',2,50,0.00,1.00,'2025-05-13 20:15:32',NULL,3,0,8288489957834752,'2025-05-02 20:15:31','2025-05-16 23:12:23',0),(78279695536123904,8288489957834752,78275787585646592,NULL,'平台15大促','EDJ_8288489542598656','EDJ_8288489542598656','',1,100,114.00,115.00,'2025-05-31 23:14:12',NULL,1,0,8288489957834752,'2025-05-16 23:14:16','2025-05-17 09:57:16',0),(78279695536123905,8288489957834752,78276089034469376,NULL,'平台16活动','EDJ_8288489542598656','EDJ_8288489542598656','',2,96,0.00,16.00,'2025-06-01 23:14:12',NULL,1,0,8288489957834752,'2025-05-16 23:14:16','2025-05-17 09:06:47',0),(78427376917049344,8288489957834752,78307431742861312,NULL,'77大促','EDJ_8288489542598656','EDJ_8288489542598656','',2,77,0.00,77.00,'2025-08-02 09:01:02',NULL,1,0,0,'2025-05-17 09:01:06','2025-05-17 09:01:06',0),(78427376950603776,8288489957834752,78307343448567808,NULL,'66大促','EDJ_8288489542598656','EDJ_8288489542598656','',1,100,66.00,666.00,'2025-07-22 09:01:02',NULL,1,0,0,'2025-05-17 09:01:06','2025-05-17 09:01:06',0);
/*!40000 ALTER TABLE `edj_coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_coupon_use_back`
--

DROP TABLE IF EXISTS `edj_coupon_use_back`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_coupon_use_back` (
  `id` bigint unsigned NOT NULL COMMENT '优惠券退回',
  `edj_coupon_id` bigint unsigned NOT NULL COMMENT '优惠券id',
  `edj_user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `use_back_time` datetime NOT NULL COMMENT '回退时间',
  `write_off_time` datetime DEFAULT NULL COMMENT '核销时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='优惠券退回表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_coupon_use_back`
--

LOCK TABLES `edj_coupon_use_back` WRITE;
/*!40000 ALTER TABLE `edj_coupon_use_back` DISABLE KEYS */;
INSERT INTO `edj_coupon_use_back` VALUES (73162650339405824,73161303498059776,8288489957834752,'2025-05-02 20:20:53','2025-05-13 20:15:32',8288489957834752,8288489957834752,'2025-05-02 20:20:52','2025-05-02 20:20:52',0),(73164798217318400,73161303498059776,8288489957834752,'2025-05-02 20:29:25','2025-05-13 20:15:32',8288489957834752,8288489957834752,'2025-05-02 20:29:24','2025-05-02 20:29:24',0),(78428808604971008,78279695536123905,8288489957834752,'2025-05-17 09:06:42','2025-06-01 23:14:12',8288489957834752,8288489957834752,'2025-05-17 09:06:48','2025-05-17 09:06:48',0),(78441512069656576,78279695536123904,8288489957834752,'2025-05-17 09:57:10','2025-05-31 23:14:12',8288489957834752,8288489957834752,'2025-05-17 09:57:17','2025-05-17 09:57:17',0);
/*!40000 ALTER TABLE `edj_coupon_use_back` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_coupon_write_off`
--

DROP TABLE IF EXISTS `edj_coupon_write_off`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_coupon_write_off` (
  `id` bigint unsigned NOT NULL COMMENT '优惠券核销id',
  `edj_coupon_id` bigint unsigned NOT NULL COMMENT '优惠券id',
  `edj_user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `edj_orders_id` bigint unsigned NOT NULL COMMENT '核销时使用的订单号',
  `edj_activity_id` bigint unsigned NOT NULL COMMENT '优惠券活动id',
  `write_off_time` datetime NOT NULL COMMENT '核销时间',
  `write_off_man_phone` char(11) NOT NULL COMMENT '核销人手机号',
  `write_off_man_name` varchar(32) NOT NULL COMMENT '核销人姓名',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='优惠券核销表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_coupon_write_off`
--

LOCK TABLES `edj_coupon_write_off` WRITE;
/*!40000 ALTER TABLE `edj_coupon_write_off` DISABLE KEYS */;
INSERT INTO `edj_coupon_write_off` VALUES (73161524814704640,73161303498059776,8288489957834752,2505020000000000054,73161016272121856,'2025-05-02 20:16:24','','EDJ_8288489542598656',8288489957834752,8288489957834752,'2025-05-02 20:16:24','2025-05-02 20:20:52',1),(73163157657251840,73161303498059776,8288489957834752,2505020000000000055,73161016272121856,'2025-05-02 20:22:54','','EDJ_8288489542598656',8288489957834752,8288489957834752,'2025-05-02 20:22:53','2025-05-02 20:29:24',1),(78280365609742336,78279695536123904,8288489957834752,2505160000000000056,78275787585646592,'2025-05-16 23:16:51','','EDJ_8288489542598656',8288489957834752,8288489957834752,'2025-05-16 23:16:56','2025-05-17 09:57:16',1),(78427471276306432,78279695536123905,8288489957834752,2505170000000000060,78276089034469376,'2025-05-17 09:01:23','','EDJ_8288489542598656',8288489957834752,8288489957834752,'2025-05-17 09:01:29','2025-05-17 09:06:47',1);
/*!40000 ALTER TABLE `edj_coupon_write_off` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT transaction mode undo table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `edj-mq`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-mq` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-mq`;

--
-- Table structure for table `edj_fail_msg`
--

DROP TABLE IF EXISTS `edj_fail_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_fail_msg` (
  `id` bigint unsigned NOT NULL COMMENT '失败消息id',
  `exchange` varchar(256) NOT NULL COMMENT ' 交换机',
  `routing_key` varchar(256) NOT NULL COMMENT '路由key',
  `msg` varchar(1024) NOT NULL COMMENT '消息',
  `reason` varchar(256) NOT NULL COMMENT '原因',
  `delay_msg_execute_time` bigint unsigned NOT NULL COMMENT '消息延迟执行时间',
  `next_fetch_time` bigint unsigned NOT NULL COMMENT '下次拉取时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息队列失败消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_fail_msg`
--

LOCK TABLES `edj_fail_msg` WRITE;
/*!40000 ALTER TABLE `edj_fail_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `edj_fail_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `edj-nacos`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-nacos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-nacos`;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'group_id',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'configuration description',
  `c_use` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'configuration usage',
  `effect` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置生效的描述',
  `type` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置的类型',
  `c_schema` text COLLATE utf8mb3_bin COMMENT '配置的模式',
  `encrypted_data_key` varchar(1024) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (1,'shared-mysql.yaml','DEFAULT_GROUP','spring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://42.193.192.61:3306/${mysql.db-name}?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai&stringtype=unspecified\n    username: edj_dev\n    password: EDJ@Dev!2024\n    # 数据源\n    type: com.zaxxer.hikari.HikariDataSource\n    hikari:\n      maximum-pool-size: 20 # 设置最大连接数，影响最大事务资源数量\n      minimum-idle: 10       # 最小空闲连接数\n      idle-timeout: 30000   # 空闲连接的超时时间（毫秒）\n      max-lifetime: 1800000 # 连接的最大存活时间（毫秒）\n      connection-timeout: 30000 # 获取连接的超时时间（毫秒）\n  # 事务\n  transaction:\n    default-timeout: 30 # 默认事务超时时间，单位秒\n    rollback-on-commit-failure: true # 提交失败时是否回滚\n\nmybatis-plus:\n  configuration:\n    map-underscore-to-camel-case: true\n    default-enum-type-handler: com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler\n  page:\n    max-limit: 1000\n  global-config:\n    db-config:\n      logic-delete-field: isDelete\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n\nmysql:\n  explain:\n    enable: false','968115da2c334b469ffcbe45f1bec834','2024-09-23 22:33:39','2025-03-02 20:05:00','nacos','112.14.79.77','','3b14212b-be35-4b72-bc26-bc62ba8e94f7','','','','yaml','',''),(2,'shared-redis.yaml','DEFAULT_GROUP','spring:\n  data:\n    redis:\n      host: 42.193.192.61\n      port: 6379\n      password: \"GUATdev!\"\n      database: 3','bd3759e6962b7e64297007251d823860','2024-09-24 11:47:58','2025-02-06 02:23:19','nacos','112.14.79.35','','3b14212b-be35-4b72-bc26-bc62ba8e94f7','','','','yaml','',''),(3,'shared-swagger.yaml','DEFAULT_GROUP','swagger:\r\n  enable: true\r\n  contact-name: A.E.\r\n  contact-url: https://github.com/Shi-AE\r\n  contact-email: shiyx325@163.com\r\n  version: v1.0\r\n\r\n# knife4j的增强配置，不需要增强可以不配\r\nknife4j:\r\n  enable: true    #开启knife4j,无需添加@EnableKnife4j注解\r\n  setting:\r\n    language: zh-CN   #中文\r\n    swagger-model-name: 实体列表   #默认为：Swagger Models','023534aadbbeea597c1c553f908b0fb4','2024-09-24 22:45:29','2024-09-24 22:45:29','nacos','183.63.226.253','','3b14212b-be35-4b72-bc26-bc62ba8e94f7',NULL,NULL,NULL,'yaml',NULL,''),(4,'edj-publics.yaml','DEFAULT_GROUP','ali:\n  oss:\n    enable: true\n    endpoint: oss-cn-hangzhou.aliyuncs.com\n    accessKeyId: LTAI5tBbWGhRqo7ecQK2Jj1v\n    accessKeySecret: 7d3xtEemTy8Q3jG9ZaNN7BCy3FBsHn\n    bucketName: shi-egret-daojia\n    basePath: EgretDaoJia\n\ntencent:\n  wechat:\n    enable: true\n    appId: wx11af548351f6f0b3\n    secret: c6a3eb6a777aba7452cc3f3a6d3368b8\n\namap:\n    enable: true\n    key: 6a193d48dc1fb99f7deea1a80441a75d','124b74f5cef460f944df4e0cffaa87e1','2024-10-13 23:51:20','2025-03-06 18:11:28','nacos','112.14.79.77','','3b14212b-be35-4b72-bc26-bc62ba8e94f7','','','','yaml','',''),(5,'shared-xxl-job.yaml','DEFAULT_GROUP','xxl-job:\n  enable: true\n  access-token: SecretKeyGuatComputerScienceAndEngineeringSoftwareEngineeringEgretDaoJiaDevelop\n  admin:\n    address: http://localhost:9000/xxl-job-main\n  executor:\n    appName: ${spring.application.name}\n    port: ${xxl-job.port}\n    # 日志保存路径\n    logpath: ./logs/xxl-job/jobhandler\n    # 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能\n    log-retention-days: 30','7dde4d093fceca21556f6ed91efe5ed2','2024-11-28 23:41:00','2024-11-29 12:09:07','nacos','116.4.96.98','','3b14212b-be35-4b72-bc26-bc62ba8e94f7','','','','yaml','',''),(6,'shared-es.yaml','DEFAULT_GROUP','es:\n  # host: 42.193.192.61\n  host: 127.0.0.1\n  port: 9200\n  username: elastic\n  password: edj_elastic_dev','6fe14b83abffa00a1b143ea5e72d8606','2024-12-08 20:49:58','2024-12-19 02:04:36','nacos','116.4.96.63','','3b14212b-be35-4b72-bc26-bc62ba8e94f7','','','','yaml','',''),(7,'shared-rabbitmq.yaml','DEFAULT_GROUP','spring:\n  rabbitmq:\n    # host: 42.193.192.61\n    host: 127.0.0.1\n    username: edj_dev\n    password: EDJ@Dev!2024\n    port: 5672\n    virtual-host: /edj\n    publisher-confirm-type: correlated #发送消息的异步回调，记录消息是否发送成功\n    publisher-returns: true #开启publish-return功能，消息到达交换机，但是没有到达对列表\n    template:\n      #消息路由失败时的策略, true: 调用ReturnCallback, false：丢弃消息\n      mandatory: true\n    listener:\n      simple:\n        acknowledge-mode: auto #，出现异常时返回nack，消息回滚到mq；没有异常，返回ack\n        retry:\n          enabled: true # 开启消费者失败重试\n          initial-interval: 1000 # 初识的失败等待时长为1秒\n          multiplier: 10 # 失败的等待时长倍数，下次等待时长 = multiplier * last-interval\n          max-attempts: 90000 # 最大重试次数\n          stateless: true # true无状态；false有状态。如果业务中包含事务，这里改为false','43dee4193b81b5fd26135322d78bcf51','2024-12-09 00:51:04','2024-12-19 02:05:00','nacos','116.4.96.63','','3b14212b-be35-4b72-bc26-bc62ba8e94f7','','','','yaml','',''),(8,'shared-sentinel.yml','DEFAULT_GROUP','spring:\n  cloud:\n    sentinel:\n      transport:\n        # 供sentinel dashboard平台访问端口\n        port: 8719\n        client-ip: 192.168.216.1 \n        # sentinel控制台\n        # dashboard: 42.193.192.61:3730\n        dashboard: 127.0.0.1:3730\n      #服务启动直接建立心跳连接\n      eager: true\nfeign:\n  sentinel:\n    enabled: true','39a281153297bea9e640d34000409755','2024-12-27 02:43:21','2024-12-27 21:10:29','nacos','223.104.61.125','','3b14212b-be35-4b72-bc26-bc62ba8e94f7','','','','yaml','',''),(9,'edj-orders-manager-degrade-rules','SENTINEL_GROUP','[{\"app\":\"edj-orders-manager\",\"count\":5.0,\"gmtCreate\":1735305849282,\"gmtModified\":1735305849282,\"grade\":2,\"id\":1,\"ip\":\"192.168.216.1\",\"limitApp\":\"default\",\"minRequestAmount\":5,\"port\":8719,\"resource\":\"GET:http://edj-foundations/inner/foundations/serve{id}\",\"statIntervalMs\":1000,\"timeWindow\":10},{\"app\":\"edj-orders-manager\",\"count\":5.0,\"gmtCreate\":1735305887791,\"gmtModified\":1735305887791,\"grade\":2,\"id\":2,\"ip\":\"192.168.216.1\",\"limitApp\":\"default\",\"minRequestAmount\":5,\"port\":8719,\"resource\":\"GET:http://edj-customer/inner/address/book{id}\",\"statIntervalMs\":1000,\"timeWindow\":10}]','0dc4bf43152faec94e95d461255b90f7','2024-12-27 21:24:09','2024-12-27 21:24:48','nacos','223.104.61.125','','',NULL,NULL,NULL,'text',NULL,''),(10,'edj-orders-manager.yaml','DEFAULT_GROUP','edj:\r\n  trade:\r\n    aliEnterpriseId: 2088241317544335\r\n    wechatEnterpriseId: 1561414331','b7bb2f64389f7bdd4b3ebf453083d813','2025-01-16 23:59:13','2025-01-16 23:59:13','nacos','112.14.79.47','','3b14212b-be35-4b72-bc26-bc62ba8e94f7',NULL,NULL,NULL,'yaml',NULL,''),(11,'edj-seata-server.properties','DEFAULT_GROUP','#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n\n#Transaction routing rules configuration, only for the client\nservice.vgroupMapping.default_tx_group=default\n#If you use a registry, you can ignore it\nservice.default.grouplist=127.0.0.1:8091\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Transaction storage configuration, only for the server. The file, db, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\nstore.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://42.193.192.61:3306/edj-seata?useUnicode=true&rewriteBatchedStatements=true\nstore.db.user=edj_seata\nstore.db.password=EDJ@DevSeata!2024\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackFailedUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\nserver.enableParallelRequestHandle=false\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898','be7eb7377d8a51422b7e8368373d4837','2025-03-13 01:11:12','2025-03-13 03:06:51','nacos','112.14.79.232','','3b14212b-be35-4b72-bc26-bc62ba8e94f7','','','','properties','',''),(12,'shared-spring-seata.yaml','DEFAULT_GROUP','seata:\r\n  data-source-proxy-mode: AT\r\n  registry:\r\n    type: nacos\r\n    nacos:\r\n      server-addr: 42.193.192.61:8848\r\n      namespace: 3b14212b-be35-4b72-bc26-bc62ba8e94f7\r\n      group: DEFAULT_GROUP\r\n      application: edj-seata-server\r\n      username: nacos\r\n      password: Edj@NacosToDev\r\n  # 事务组名称\r\n  tx-service-group: edj-seata\r\n  service:\r\n    vgroup-mapping: # 事务组与cluster的映射关系\r\n      edj-seata: default','4fa288aba6a2afbaae8412ef130d81a2','2025-03-13 18:00:52','2025-03-13 18:00:52','nacos','112.14.79.232','','3b14212b-be35-4b72-bc26-bc62ba8e94f7',NULL,NULL,NULL,'yaml',NULL,'');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` varchar(1024) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增长标识',
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL COMMENT 'id',
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增标识',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `op_type` char(10) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'operation type',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` varchar(1024) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '密钥',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL COMMENT 'role',
  `resource` varchar(128) NOT NULL COMMENT 'resource',
  `action` varchar(8) NOT NULL COMMENT 'action',
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL COMMENT 'username',
  `role` varchar(50) NOT NULL COMMENT 'role',
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` VALUES (1,'1','3b14212b-be35-4b72-bc26-bc62ba8e94f7','白鹭到家','白鹭到家公共配置','nacos',1727073121788,1727073121788);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL COMMENT 'username',
  `password` varchar(500) NOT NULL COMMENT 'password',
  `enabled` tinyint(1) NOT NULL COMMENT 'enabled',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('9O8F4Q','$2a$10$WFZeS/.4TczrjorLWj.OAOf2Pjt.eAm8.sWX4Mc6GL201JgWeY7LG',1),('nacos','$2a$10$byj5fPBEthJjZi5Wkh.56Oij4.IvzP7oqLeDo5hldJWqQO4NK.CTu',1),('nacos9084','$2a$10$wbc3zzAzmpUNmME1SuLULOIbmVSKEqeWqO40qDRVwYX9vHZYvZZqi',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `edj-orders`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-orders` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-orders`;

--
-- Table structure for table `edj_orders`
--

DROP TABLE IF EXISTS `edj_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_orders` (
  `id` bigint unsigned NOT NULL COMMENT '订单id',
  `edj_user_id` bigint unsigned NOT NULL COMMENT '订单所属人id',
  `edj_serve_type_id` bigint unsigned NOT NULL COMMENT '服务类型id',
  `edj_serve_item_id` bigint unsigned NOT NULL COMMENT '服务项id',
  `edj_serve_id` bigint unsigned NOT NULL COMMENT '服务id',
  `serve_type_name` varchar(255) NOT NULL COMMENT '服务类型名称',
  `serve_item_name` varchar(255) NOT NULL COMMENT '服务项名称',
  `serve_item_img` varchar(255) NOT NULL COMMENT '服务项图片',
  `unit` tinyint NOT NULL COMMENT '服务单位',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `pur_num` int NOT NULL DEFAULT '1' COMMENT '购买数量',
  `total_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `real_pay_amount` decimal(10,2) NOT NULL COMMENT '实际支付金额',
  `discount_amount` decimal(10,2) NOT NULL COMMENT '优惠金额',
  `city_code` varchar(8) NOT NULL COMMENT '城市编号',
  `serve_address` varchar(255) NOT NULL COMMENT '服务详细地址',
  `contacts_phone` varchar(16) NOT NULL COMMENT '联系人手机号',
  `contacts_name` varchar(255) NOT NULL COMMENT '联系人姓名',
  `serve_start_time` datetime NOT NULL COMMENT '服务开始时间',
  `real_serve_end_time` datetime DEFAULT NULL COMMENT '实际服务完成时间',
  `lon` decimal(10,5) NOT NULL COMMENT '经度',
  `lat` decimal(10,5) NOT NULL COMMENT '纬度',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `orders_status` smallint NOT NULL DEFAULT '0' COMMENT '订单状态（0待支付 100派单中 200待服务 300服务中 400待评价 500订单完成 600已取消 700已关闭）',
  `pay_status` tinyint NOT NULL DEFAULT '2' COMMENT '支付状态（2待支付 4支付成功）',
  `refund_status` tinyint NOT NULL DEFAULT '0' COMMENT '退款状态（0无退款 1退款中 2退款成功 3退款失败）',
  `evaluation_time` datetime DEFAULT NULL COMMENT '评价时间',
  `evaluation_status` tinyint NOT NULL DEFAULT '0' COMMENT '评价状态（0未评价 1已评价）',
  `trading_order_no` bigint unsigned DEFAULT NULL COMMENT '支付服务交易单号',
  `transaction_id` varchar(64) DEFAULT NULL COMMENT '第三方支付的交易单号',
  `refund_no` bigint unsigned DEFAULT NULL COMMENT '支付服务退款单号',
  `refund_id` varchar(64) DEFAULT NULL COMMENT '第三方支付的退款单号',
  `trading_channel` varchar(64) DEFAULT NULL COMMENT '支付渠道',
  `display` tinyint NOT NULL DEFAULT '1' COMMENT '用户端是否展示（1展示 0隐藏）',
  `sort_by` bigint unsigned NOT NULL COMMENT '排序字段（serve_start_time秒级时间戳+订单id后六位）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_orders`
--

LOCK TABLES `edj_orders` WRITE;
/*!40000 ALTER TABLE `edj_orders` DISABLE KEYS */;
INSERT INTO `edj_orders` VALUES (2505020000000000054,8288489957834752,72873557365239808,72894530948837376,72912408217530369,'保洁服务','日常清洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',6,1.81,1,1.81,0.91,0.90,'0510','江苏省扬州市高邮市卸甲镇虎头拐','13555555555','杨画','2025-05-08 10:30:00',NULL,119.59000,32.74000,'2025-05-02 20:17:39',700,4,2,NULL,0,73161631257616384,'4200002662202505029717812315',73162665925304320,'50303503032025050273020131058','WECHAT_PAY',1,1746671400054,8288489957834752,8288489957834752,'2025-05-02 20:16:26','2025-05-16 23:10:08',0),(2505020000000000055,8288489957834752,72873557365239808,72894530948837376,72912408217530369,'保洁服务','日常清洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',6,1.81,1,1.81,0.91,0.90,'0510','江苏省无锡市江阴市月城镇云外水荘','16888888888','戴佳伟','2025-05-08 11:00:00',NULL,120.20000,31.78000,'2025-05-02 20:23:41',700,4,2,NULL,0,73163215387516928,'2025050222001416931435978710',73164803938213888,NULL,'ALI_PAY',1,1746673200055,8288489957834752,8288489957834752,'2025-05-02 20:22:54','2025-05-02 20:29:28',0),(2505160000000000056,8288489957834752,72874351573479425,72902646524424193,78270758564278274,'清洁服务','空调清洗','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/15e60408-e3cd-4a09-8838-9d7ba1fe768a.jpg',4,115.00,1,115.00,1.00,114.00,'0769','广东省东莞市东城街道东城中路475号东莞东城万达广场','16888888888','戴佳伟','2025-05-17 08:00:00',NULL,113.78000,23.03000,'2025-05-16 23:17:45',700,4,1,NULL,0,78280507549048832,'4200002717202505162974435050',NULL,NULL,'WECHAT_PAY',1,1747440000056,8288489957834752,8288489957834752,'2025-05-16 23:16:58','2025-05-17 09:57:13',0),(2505170000000000057,8288489957834752,72873557365239808,72894530948837376,78270758568472578,'保洁服务','日常清洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',6,1.81,1,1.81,1.81,0.00,'0769','广东省东莞市大朗镇松山湖风景区','13555555555','杨画','2025-05-20 01:00:00',NULL,113.88000,22.89000,'2025-05-17 00:48:26',700,4,1,NULL,0,78303359501361152,'4200002658202505178366420332',NULL,NULL,'WECHAT_PAY',1,1747674000057,8288489957834752,8288489957834752,'2025-05-17 00:48:15','2025-05-17 09:52:24',0),(2505170000000000058,8288489957834752,72873557365239808,72894530948837376,78270758568472578,'保洁服务','日常清洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',6,1.81,1,1.81,1.81,0.00,'0769','广东省东莞市东城街道东城中路475号东莞东城万达广场','16888888888','戴佳伟','2025-05-18 06:30:00',NULL,113.78000,23.03000,'2025-05-17 00:49:59',700,4,1,NULL,0,78303740092506112,'4200002644202505177265642346',NULL,NULL,'WECHAT_PAY',1,1747521000058,8288489957834752,8288489957834752,'2025-05-17 00:48:49','2025-05-17 09:51:58',0),(2505170000000000059,8288489957834752,72873557365239808,72899828094742528,78270758962737152,'保洁服务','深度清洁','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/16cc1050-c29a-436b-8844-610cde65d12c.jpg',6,2.08,1,2.08,2.08,0.00,'0769','广东省东莞市大朗镇松山湖风景区','13555555555','杨画','2025-05-19 09:30:00',NULL,113.88000,22.89000,'2025-05-17 00:57:21',700,4,2,NULL,0,78305600056614912,'4200002642202505173249045207',78437886672527360,'50300703282025051774105204929','WECHAT_PAY',1,1747618200059,8288489957834752,8288489957834752,'2025-05-17 00:57:10','2025-05-17 09:50:07',0),(2505170000000000060,8288489957834752,72874351573479425,72901580131020800,78270758887239680,'清洁服务','擦玻璃','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/608fb282-41d3-46d8-8261-c3cb86c663bb.jpg',6,17.00,1,17.00,16.32,0.68,'0769','广东省东莞市东城街道东城中路475号东莞东城万达广场','16888888888','戴佳伟','2025-05-19 05:30:00',NULL,113.78000,23.03000,'2025-05-17 09:01:46',700,4,2,NULL,0,78427508215406592,'4200002737202505173446444362',78428813210181632,'50300203402025051747592991760','WECHAT_PAY',1,1747603800060,8288489957834752,8288489957834752,'2025-05-17 09:01:31','2025-05-17 09:10:06',0);
/*!40000 ALTER TABLE `edj_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_orders_canceled`
--

DROP TABLE IF EXISTS `edj_orders_canceled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_orders_canceled` (
  `id` bigint NOT NULL COMMENT '订单取消id',
  `canceller_id` bigint NOT NULL COMMENT '取消人id',
  `canceller_name` varchar(64) NOT NULL COMMENT '取消人名称',
  `cancel_reason` varchar(64) NOT NULL COMMENT '取消原因',
  `cancel_time` datetime NOT NULL COMMENT '取消时间',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单取消表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_orders_canceled`
--

LOCK TABLES `edj_orders_canceled` WRITE;
/*!40000 ALTER TABLE `edj_orders_canceled` DISABLE KEYS */;
INSERT INTO `edj_orders_canceled` VALUES (2505020000000000054,8288489957834752,'EDJ_8288489542598656','下单时间错误','2025-05-02 20:20:50',8288489957834752,8288489957834752,'2025-05-02 20:20:49','2025-05-02 20:20:49',0),(2505020000000000055,8288489957834752,'EDJ_8288489542598656','商家联系不上','2025-05-02 20:29:22',8288489957834752,8288489957834752,'2025-05-02 20:29:21','2025-05-02 20:29:21',0),(2505160000000000056,8288489957834752,'EDJ_8288489542598656','下单地址有误','2025-05-17 09:57:08',8288489957834752,8288489957834752,'2025-05-17 09:57:13','2025-05-17 09:57:13',0),(2505170000000000057,8288489957834752,'EDJ_8288489542598656','问题已解决，不需要了','2025-05-17 09:52:19',8288489957834752,8288489957834752,'2025-05-17 09:52:23','2025-05-17 09:52:23',0),(2505170000000000058,8288489957834752,'EDJ_8288489542598656','下单时间错误','2025-05-17 09:51:52',8288489957834752,8288489957834752,'2025-05-17 09:51:57','2025-05-17 09:51:57',0),(2505170000000000059,8288489957834752,'EDJ_8288489542598656','商家联系不上','2025-05-17 09:42:41',8288489957834752,8288489957834752,'2025-05-17 09:42:47','2025-05-17 09:42:47',0),(2505170000000000060,8288489957834752,'EDJ_8288489542598656','下单地址有误','2025-05-17 09:06:40',8288489957834752,8288489957834752,'2025-05-17 09:06:45','2025-05-17 09:06:45',0);
/*!40000 ALTER TABLE `edj_orders_canceled` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_orders_dispatch`
--

DROP TABLE IF EXISTS `edj_orders_dispatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_orders_dispatch` (
  `id` bigint unsigned NOT NULL COMMENT '订单id',
  `edj_serve_type_id` bigint unsigned NOT NULL COMMENT '服务类型id',
  `edj_serve_item_id` bigint unsigned NOT NULL COMMENT '服务项id',
  `serve_type_name` varchar(255) NOT NULL COMMENT '服务类型名称',
  `serve_item_name` varchar(255) NOT NULL COMMENT '服务项名称',
  `city_code` varchar(8) NOT NULL COMMENT '城市编码',
  `serve_address` varchar(255) NOT NULL COMMENT '服务地址',
  `serve_item_img` varchar(255) NOT NULL COMMENT '服务项目图片',
  `orders_amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `serve_start_time` datetime NOT NULL COMMENT '服务开始时间',
  `pay_success_time` datetime NOT NULL COMMENT '支付成功时间，用于计算是否进入派单',
  `lon` decimal(10,5) NOT NULL COMMENT '经度',
  `lat` decimal(10,5) NOT NULL COMMENT '维度',
  `pur_num` int unsigned NOT NULL COMMENT '服务数量',
  `is_transfer_manual` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否专人工',
  `sort_by` int unsigned NOT NULL COMMENT '抢单列表排序字段',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='派单池';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_orders_dispatch`
--

LOCK TABLES `edj_orders_dispatch` WRITE;
/*!40000 ALTER TABLE `edj_orders_dispatch` DISABLE KEYS */;
/*!40000 ALTER TABLE `edj_orders_dispatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_orders_grab`
--

DROP TABLE IF EXISTS `edj_orders_grab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_orders_grab` (
  `id` bigint unsigned NOT NULL COMMENT '订单id',
  `edj_serve_type_id` bigint unsigned NOT NULL COMMENT '服务类型id',
  `edj_serve_item_id` bigint unsigned NOT NULL COMMENT '服务项id',
  `serve_type_name` varchar(255) NOT NULL COMMENT '服务类型名称',
  `serve_item_name` varchar(255) NOT NULL COMMENT '服务项名称',
  `city_code` varchar(8) NOT NULL COMMENT '城市编码',
  `serve_address` varchar(255) NOT NULL COMMENT '服务地址',
  `serve_item_img` varchar(255) NOT NULL COMMENT '服务项目图片',
  `orders_amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `serve_start_time` datetime NOT NULL COMMENT '服务开始时间',
  `pay_success_time` datetime NOT NULL COMMENT '支付成功时间，用于计算是否进入派单',
  `lon` decimal(10,5) NOT NULL COMMENT '经度',
  `lat` decimal(10,5) NOT NULL COMMENT '维度',
  `pur_num` int unsigned NOT NULL COMMENT '服务数量',
  `is_time_out` tinyint(1) NOT NULL DEFAULT '0' COMMENT '抢单是否超时',
  `sort_by` int unsigned NOT NULL COMMENT '抢单列表排序字段',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抢单池';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_orders_grab`
--

LOCK TABLES `edj_orders_grab` WRITE;
/*!40000 ALTER TABLE `edj_orders_grab` DISABLE KEYS */;
INSERT INTO `edj_orders_grab` VALUES (2505020000000000054,72873557365239808,72894530948837376,'保洁服务','日常清洁','0510','江苏省扬州市高邮市卸甲镇虎头拐','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',0.91,'2025-05-08 10:30:00','2025-05-02 20:17:39',119.59000,32.74000,1,0,54,'2025-05-02 20:17:38','2025-05-02 20:17:38'),(2505020000000000055,72873557365239808,72894530948837376,'保洁服务','日常清洁','0510','江苏省无锡市江阴市月城镇云外水荘','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',0.91,'2025-05-08 11:00:00','2025-05-02 20:23:41',120.20000,31.78000,1,0,55,'2025-05-02 20:23:40','2025-05-02 20:23:40'),(2505170000000000059,72873557365239808,72899828094742528,'保洁服务','深度清洁','0769','广东省东莞市大朗镇松山湖风景区','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/16cc1050-c29a-436b-8844-610cde65d12c.jpg',2.08,'2025-05-19 09:30:00','2025-05-17 00:57:21',113.88000,22.89000,1,0,59,'2025-05-17 00:57:26','2025-05-17 00:57:26'),(2505170000000000060,72874351573479425,72901580131020800,'清洁服务','擦玻璃','0769','广东省东莞市东城街道东城中路475号东莞东城万达广场','https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/608fb282-41d3-46d8-8261-c3cb86c663bb.jpg',16.32,'2025-05-19 05:30:00','2025-05-17 09:01:46',113.78000,23.03000,1,0,60,'2025-05-17 09:01:51','2025-05-17 09:01:51');
/*!40000 ALTER TABLE `edj_orders_grab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_orders_refund`
--

DROP TABLE IF EXISTS `edj_orders_refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_orders_refund` (
  `id` bigint NOT NULL COMMENT '订单id',
  `trading_order_no` bigint NOT NULL COMMENT '支付服务交易单号',
  `real_pay_amount` decimal(10,2) NOT NULL COMMENT '实际支付金额',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单退款表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_orders_refund`
--

LOCK TABLES `edj_orders_refund` WRITE;
/*!40000 ALTER TABLE `edj_orders_refund` DISABLE KEYS */;
INSERT INTO `edj_orders_refund` VALUES (2505020000000000054,73161631257616384,0.91,8288489957834752,8288489957834752,'2025-05-02 20:20:51','2025-05-16 23:10:09',1),(2505020000000000055,73163215387516928,0.91,8288489957834752,8288489957834752,'2025-05-02 20:29:23','2025-05-02 20:29:28',1),(2505160000000000056,78280507549048832,1.00,8288489957834752,8288489957834752,'2025-05-17 09:57:14','2025-05-17 09:57:14',0),(2505170000000000057,78303359501361152,1.81,8288489957834752,8288489957834752,'2025-05-17 09:52:25','2025-05-17 09:52:25',0),(2505170000000000058,78303740092506112,1.81,8288489957834752,8288489957834752,'2025-05-17 09:51:59','2025-05-17 09:51:59',0),(2505170000000000059,78305600056614912,2.08,8288489957834752,8288489957834752,'2025-05-17 09:42:48','2025-05-17 09:50:07',1),(2505170000000000060,78427508215406592,16.32,8288489957834752,8288489957834752,'2025-05-17 09:06:46','2025-05-17 09:10:07',1);
/*!40000 ALTER TABLE `edj_orders_refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_orders_serve`
--

DROP TABLE IF EXISTS `edj_orders_serve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_orders_serve` (
  `id` bigint unsigned NOT NULL COMMENT '订单服务id',
  `edj_user_id` bigint unsigned NOT NULL COMMENT '消费者用户id',
  `edj_serve_providers_id` bigint unsigned NOT NULL COMMENT '服务人员或服务机构id',
  `edj_institution_staff_id` bigint unsigned DEFAULT NULL COMMENT '机构服务人员id',
  `edj_orders_id` bigint unsigned NOT NULL COMMENT '订单id',
  `orders_origin_type` tinyint unsigned NOT NULL COMMENT '订单来源类型（1抢单 2派单）',
  `city_code` varchar(8) NOT NULL COMMENT '城市编码',
  `edj_serve_type_id` bigint unsigned NOT NULL COMMENT '服务类型id',
  `serve_start_time` datetime NOT NULL COMMENT '服务预约开始时间',
  `edj_serve_item_id` bigint unsigned NOT NULL COMMENT '服务项id',
  `serve_status` tinyint unsigned NOT NULL COMMENT '服务状态（0待分配 1待服务 2服务中 3服务完成 4取消）',
  `settlement_status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '结算状态（0不可结算 1待结算 2结算完成）',
  `real_serve_start_time` datetime DEFAULT NULL COMMENT '实际服务开始时间',
  `real_serve_end_time` datetime DEFAULT NULL COMMENT '实际服务完结时间',
  `serve_before_imgs` json DEFAULT NULL COMMENT '服务前照片',
  `serve_after_imgs` json DEFAULT NULL COMMENT '服务后照片',
  `serve_item_img` varchar(255) NOT NULL COMMENT '服务项图片',
  `serve_before_illustrate` varchar(255) DEFAULT NULL COMMENT '服务前说明',
  `serve_after_illustrate` varchar(255) DEFAULT NULL COMMENT '服务后说明',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消退单时间',
  `orders_amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `pur_num` int unsigned NOT NULL COMMENT '购买数量',
  `sort_by` bigint unsigned NOT NULL COMMENT '排序字段（serve_start_time（秒级时间戳）+订单id（后6位））',
  `display` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '服务端/机构端是否展示（0隐藏 1展示）',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单服务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_orders_serve`
--

LOCK TABLES `edj_orders_serve` WRITE;
/*!40000 ALTER TABLE `edj_orders_serve` DISABLE KEYS */;
INSERT INTO `edj_orders_serve` VALUES (78302426193494016,8288489957834752,14078868029972480,NULL,2505160000000000056,1,'0769',72874351573479425,'2025-05-17 08:00:00',72902646524424193,0,0,NULL,NULL,NULL,NULL,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/15e60408-e3cd-4a09-8838-9d7ba1fe768a.jpg',NULL,NULL,NULL,1.00,1,56,1,0,0,'2025-05-17 00:44:35','2025-05-17 00:44:35',0),(78304019164987392,8288489957834752,11148624847446016,NULL,2505170000000000057,1,'0769',72873557365239808,'2025-05-20 01:00:00',72894530948837376,1,0,NULL,NULL,NULL,NULL,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',NULL,NULL,NULL,1.81,1,57,1,0,0,'2025-05-17 00:50:55','2025-05-17 00:50:55',0),(78427708237840384,8288489957834752,14078868029972480,NULL,2505170000000000058,1,'0769',72873557365239808,'2025-05-18 06:30:00',72894530948837376,0,0,NULL,NULL,NULL,NULL,'https://shi-egret-daojia.oss-cn-hangzhou.aliyuncs.com/EgretDaoJia/2025/05/02/e22cbf44-1889-49c3-841a-68626bc1ec3a.jpg',NULL,NULL,NULL,1.81,1,58,1,0,0,'2025-05-17 09:02:25','2025-05-17 09:02:25',0);
/*!40000 ALTER TABLE `edj_orders_serve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT transaction mode undo table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `edj-seata`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-seata` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-seata`;

--
-- Table structure for table `branch_table`
--

DROP TABLE IF EXISTS `branch_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_table` (
  `branch_id` bigint NOT NULL,
  `xid` varchar(128) NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `resource_group_id` varchar(32) DEFAULT NULL,
  `resource_id` varchar(256) DEFAULT NULL,
  `branch_type` varchar(8) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `client_id` varchar(64) DEFAULT NULL,
  `application_data` varchar(2000) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`branch_id`),
  KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_table`
--

LOCK TABLES `branch_table` WRITE;
/*!40000 ALTER TABLE `branch_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributed_lock`
--

DROP TABLE IF EXISTS `distributed_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distributed_lock` (
  `lock_key` char(20) NOT NULL,
  `lock_value` varchar(255) NOT NULL,
  `expire` bigint DEFAULT NULL,
  PRIMARY KEY (`lock_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributed_lock`
--

LOCK TABLES `distributed_lock` WRITE;
/*!40000 ALTER TABLE `distributed_lock` DISABLE KEYS */;
INSERT INTO `distributed_lock` VALUES ('AsyncCommitting',' ',0),('Committing',' ',0),('RetryCommitting',' ',0),('RetryRollbacking',' ',0),('Rollbacking',' ',0),('TxTimeoutCheck',' ',0),('UndologDelete',' ',0);
/*!40000 ALTER TABLE `distributed_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_table`
--

DROP TABLE IF EXISTS `global_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_table` (
  `xid` varchar(128) NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `status` tinyint NOT NULL,
  `application_id` varchar(32) DEFAULT NULL,
  `transaction_service_group` varchar(32) DEFAULT NULL,
  `transaction_name` varchar(128) DEFAULT NULL,
  `timeout` int DEFAULT NULL,
  `begin_time` bigint DEFAULT NULL,
  `application_data` varchar(2000) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`xid`),
  KEY `idx_status_gmt_modified` (`status`,`gmt_modified`),
  KEY `idx_transaction_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_table`
--

LOCK TABLES `global_table` WRITE;
/*!40000 ALTER TABLE `global_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lock_table`
--

DROP TABLE IF EXISTS `lock_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lock_table` (
  `row_key` varchar(128) NOT NULL,
  `xid` varchar(128) DEFAULT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `branch_id` bigint NOT NULL,
  `resource_id` varchar(256) DEFAULT NULL,
  `table_name` varchar(32) DEFAULT NULL,
  `pk` varchar(36) DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '0:locked ,1:rollbacking',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`row_key`),
  KEY `idx_status` (`status`),
  KEY `idx_branch_id` (`branch_id`),
  KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lock_table`
--

LOCK TABLES `lock_table` WRITE;
/*!40000 ALTER TABLE `lock_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `lock_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `edj-trade`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-trade` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-trade`;

--
-- Table structure for table `edj_pay_channel`
--

DROP TABLE IF EXISTS `edj_pay_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_pay_channel` (
  `id` bigint unsigned NOT NULL COMMENT '交易渠道id',
  `channel_name` varchar(32) DEFAULT NULL COMMENT '通道名称',
  `channel_label` varchar(32) DEFAULT NULL COMMENT '通道唯一标记',
  `domain` varchar(255) DEFAULT NULL COMMENT '域名',
  `app_id` varchar(32) NOT NULL COMMENT '商户appid',
  `public_key` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '支付公钥',
  `merchant_private_key` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '商户私钥',
  `other_config` varchar(1024) DEFAULT NULL COMMENT '其他配置',
  `encrypt_key` varchar(255) DEFAULT NULL COMMENT 'AES混淆密钥',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `notify_url` varchar(255) DEFAULT NULL COMMENT '回调地址',
  `enable_flag` char(3) DEFAULT NULL COMMENT '是否有效',
  `enterprise_id` bigint unsigned DEFAULT NULL COMMENT '商户ID（系统内部识别使用）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `edj_pay_channel_enterprise_id_index` (`enterprise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='交易渠道表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_pay_channel`
--

LOCK TABLES `edj_pay_channel` WRITE;
/*!40000 ALTER TABLE `edj_pay_channel` DISABLE KEYS */;
INSERT INTO `edj_pay_channel` VALUES (1,'支付宝支付','ALI_PAY','openapi.alipay.com','2021003141676135','MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhUnjdAKwZApwZEcfq+5L0pa77Vg3mqcoXv+th8RR0SYotkPsH1f2JkbS48ySaSCM6YNWSMNfqp5qdOla2zUJOBnJ/yaBg7s7fVD6V3M2mEog8kCDYGKt/3P4VII3xYl8lFYMQ3IcFRELkxCBBCA8JDKmf5z2R4F/Z/jFFEuOwxaJvp+7Ke9OzZHYdWGNnU6QP8YYLYUeX7VNZLHEuly34ExAw6A+yJkNDsYEho2Lu31QjT2pLh9g+88MlRfiI92iN25O9NVdeM4f5RcpvBPrBQZQs9tlFmALYSFS3prIf3FAobWM+W7iwxT6J25nFIhst1DdJQfIBpaeRUJVTkn99QIDAQAB','MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQC12YM9mR+HFQYTx/fHKHZbgszVtDHDB0B/ysWl3MbcPpGtjcZlDr5aynRMRLaoduRHT++A98IaNVIVGj9RHdXrX2j9I/Uz6fYDH63cdu6FZ6Pk82yPwNZW7pebprbVHInR/7gzsKQWSWEST70BgjCRqlbfAE6xzUZFTeYxciCjptm0rUQ2MC24xRdkvZByIDIYFnQ/AdmSFqNtKDR2WpEV/M8aBjyuPPomRJZ1X8oudWuJIU4ySdas04fCbDxD10TY/wyQcDHXuG1IrQpXme4DOGQeJZ0/aOFphBkDFUyPGfYMmLshOPNdBKi2IqWHPPs4XsV4Rv6+tvTSnMF2uGqHAgMBAAECggEAPa1sifPpcZN74DGupGng2uDeQI1BY3iOM8m+h6b9+61tE4RGifgaMAkCsOuNWE4a1uURwphFyUXUdTvVxdlsuMw/e7w6akUsH5sbCO99rtmcCQdXBtrM1+dMnIpK8LUhOYyWGVIMFVMGDYPmAyD5AC7aEAC2sC+DafYl4RdoYpidq1YxeE7DVw1aQHCI2mKhYjZG+3RDDGDfNFvdyH61MgdYjoGkeXNvARzEXgfWvfiTrHZ3H1SYgvOEHofzKDTrWsQL2dvaEsc55Jiw0AgNUVcgby7al8PUekTJoK3ZvrE3pSWaUirBcqsqWISHjeR7Xx501CHIha8EnZwlnDoM4QKBgQDtnYzwQ5mHg7cRHD8Z6QdTpvBvYSEPesiUT/HeI+AKQKDCVJxKiLvJagc6zZkOzV9bZDS/WLgzXWMyxUb+OTjht0jLWAMcf7NfFp3tPKq9wkmQQ/vQSBQ1lFmO6A4Zq1eoGKeUCB4pKBG6cSM+t8+ruhm7s1ZUt+6EBwCVN/izrQKBgQDD62jIm+6NFErdidUaIrGiFUrzqdR13w6JOexfk+O6Aau3wRqsr7Wz4nqQvVVxGMRpXbOH06zpeiS+vMmjwgO973VoLAmH+hJ0GZz8qj3zA2GEOFWjD2V7tqeRvGkQvz0v46pl+8sBJkrRHLN7DWNYY5NDI+b7exwqcTc/LL19gwJ/a6r4MeZvqvgD+7zQ2uy8ZSs/xzg7wsfgG1QeRIn8+qhOL8AnEZ7jeGCS5hJDSHHGw6KkRA/vZ1bpnBfIE2naXGywj3NR9Zfnry6QYO8cbt+adcRYVghTH/QYoKiFuxvonEKPrIQBJqUBY3ngforLjwTEpEie1cSCT1Dc8sBp8QKBgQCaz8fqzRyBKknGKQXVMxj+JKknRUl3IpzP3o9jLu9BqdRQzSwQzH9d91Y2TQXY6mM5hys35xG5JCUo+vCyj7p5OWCiwjl90yMFzr93/+YXwtIpsoIo6R+d1EUxKZoz+4mT7+hT0dUlwWZZOr6wO3IHBBf3c8UvbqZg+zlWmDnblQKBgEs6jwMkb5zaG2fyBJ7PJUN/8nIz8V+X0SxQfcEqIX0J+EC+7MAgFjcdZFp+lca3Vd9z+8Ksd4rMzMa5y856ositL2NZ+K0fs8i8EBaQPny61OgCFUuXEuv5keB2YuGMSns5FYRWuByrtDXl4PxzKXvq05iKWLKCaCq9v4momvKZ','111','VCS4bdmoAgXRaOq/TQ4MwA==','支付宝支付','https://7769c5ef.r15.cpolar.top/trade/notify/alipay/2088241317544335','YES',2088241317544335,'2021-11-06 14:45:58','2022-01-12 02:31:24'),(2,'微信支付','WECHAT_PAY','api.mch.weixin.qq.com','wx6592a2db3f85ed25','内部封装','-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDBHGgIh80193Gh\ndpD1LtMZfTRpcWI0fImyuBCyrd3gYb3rrsARebGcHdJsQA3mVjVqVp5ybhEZDPa4\necoK4Ye1hTppNpI/lmLt4/uUV/zhF5ahli7hi+116Ty6svHSbuMQBuUZeTFOwGrx\njvofU/4pGIwh8ZvkcSnyOp9uX2177UVxDBkhgbZbJp9XF2b83vUa5eHo93CziPzn\n3hFdAlBCdTXB7DH+m0nN3Jou0szGukvq7cIgGpHku4ycKSTkIhhl9WRhN6OoSEJx\nq88MXzjkzTruc85PHN52aUTUifwg3T8Y4XqFQ61dTnEmgxeD2O6/pLdB9gLsp6yC\nGqN5Lqk7AgMBAAECggEBAL4X+WzUSbSjFS9NKNrCMjm4H1zgqTxjj6TnPkC1mGEl\ntjAHwLgzJBw62wWGdGhWWpSIGccpBBm1wjTMZpAZfF66fEpP1t1Ta6UjtGZNyvfF\nIZmE3jdWZ/WXGBnsxtFQKKKBNwrBW0Fbdqq9BQjLxLitmlxbmwrgPttcy855j6vZ\nqq4MBT1v8CtUT/gz4UWW2xWovVnmWOrRSScv7Nh0pMbRpPLkNHXrBwSSNz/keORz\nXB9JSm85wlkafa7n5/IJbdTml3A/uAgW3q3JZZQotHxQsYvD4Zb5Cnc9CPAXE5L2\nYk877kVXZMGt5QPIVcPMj/72AMtaJT67Y0fN0RYHEGkCgYEA38BIGDY6pePgPbxB\n7N/l6Df0/OKPP0u8mqR4Q0aQD3VxeGiZUN1uWXEFKsKwlOxLfIFIFk1/6zQeC0xe\ntNTKk0gTL8hpMUTNkE7vI9gFWws2LY6DE86Lm0bdFEIwh6d7Fr7zZtyQKPzMsesC\n3XV9sdSUExEi5o/VwAyf+xZlOXcCgYEA3PGZYlILjg3esPNkhDz2wxFw432i8l/B\nCPD8ZtqIV9eguu4fVtFYcUVfawBb0T11RamJkc4eiSOqayC+2ehgb+GyRLJNK4Fq\nbFcsIT+CK0HlscZw51jrMR0MxTc4RzuOIMoYDeZqeGB6/YnNyG4pw2sD8bIwHm84\n06gtJsX/v10CgYAo8g3/aEUZQHcztPS3fU2cTkkl0ev24Ew2XGypmwsX2R0XtMSB\nuNPNyFHyvkgEKK2zrhDcC/ihuRraZHJcUyhzBViFgP5HBtk7VEaM36YzP/z9Hzw7\nbqu7kZ85atdoq6xpwC3Yn/o9le17jY8rqamD1mv2hUdGvAGYsHbCQxnpBwKBgHTk\neaMUBzr7yZLS4p435tHje1dQVBJpaKaDYPZFrhbTZR0g+IGlNmaPLmFdCjbUjiPy\nA2+Znnwt227cHz0IfWUUAo3ny3419QkmwZlBkWuzbIO2mms7lwsf9G6uvV6qepKM\neVd5TWEsokVbT/03k27pQmfwPxcK/wS0GFdIL/udAoGAOYdDqY5/aadWCyhzTGI6\nqXPLvC+fsJBPhK2RXyc+jYV0KmrEv4ewxlK5NksuFsNkyB7wlI1oMCa/xB3T/2vT\nBALgGFPi8BJqceUjtnTYtI4R2JIVEl08RtEJwyU5JZ2rvWcilsotVZYwfuLZ9Kfd\nhkTrgNxlp/KKkr+UuKce4Vs=\n-----END PRIVATE KEY-----\n','{\"mchId\":\"1561414331\",\"mchSerialNo\":\"25FBDE3EFD31B03A4377EB9A4A47C517969E6620\",\"apiV3Key\":\"CZBK51236435wxpay435434323FFDuv3\"}','内部封装','微信支付','https://www.itcast.cn/','YES',1561414331,'2021-11-09 17:28:40','2021-11-10 16:27:43');
/*!40000 ALTER TABLE `edj_pay_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_refund_record`
--

DROP TABLE IF EXISTS `edj_refund_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_refund_record` (
  `id` bigint unsigned NOT NULL COMMENT '退款记录id',
  `trading_order_no` bigint unsigned NOT NULL COMMENT '交易系统订单号（对于三方来说：商户订单）',
  `product_app_id` varchar(64) NOT NULL COMMENT '业务系统应用标识',
  `product_order_no` bigint unsigned NOT NULL COMMENT '业务系统订单号',
  `refund_no` bigint unsigned NOT NULL COMMENT '本次退款订单号',
  `refund_id` varchar(64) DEFAULT NULL COMMENT '第三方支付的退款单号',
  `enterprise_id` bigint unsigned NOT NULL COMMENT '商户号',
  `trading_channel` varchar(32) NOT NULL COMMENT '退款渠道（支付宝、微信、现金）',
  `refund_status` tinyint NOT NULL COMMENT '退款状态（1退款中 2成功 3失败）',
  `refund_code` varchar(8) DEFAULT NULL COMMENT '返回编码',
  `refund_msg` varchar(2048) DEFAULT NULL COMMENT '返回信息',
  `memo` varchar(256) DEFAULT NULL COMMENT '备注（订单门店，桌台信息）',
  `refund_amount` decimal(10,2) NOT NULL COMMENT '本次退款金额',
  `total` decimal(10,2) NOT NULL COMMENT '原订单金额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `edj_refund_record_pk` (`refund_no`),
  KEY `edj_refund_record_trading_order_no_index` (`trading_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='退款记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_refund_record`
--

LOCK TABLES `edj_refund_record` WRITE;
/*!40000 ALTER TABLE `edj_refund_record` DISABLE KEYS */;
INSERT INTO `edj_refund_record` VALUES (52970745529917440,52968716820570112,'edj.orders',2503080000000000020,52970745467002880,'50302702672025030815816661323',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":462,\"payer_total\":462,\"refund\":462,\"refund_fee\":3,\"settlement_refund\":462,\"settlement_total\":462,\"total\":462},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-03-08T03:05:28+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"52970745467002880\",\"out_trade_no\":\"52968716820570112\",\"promotion_detail\":[],\"refund_id\":\"50302702672025030815816661323\",\"status\":\"SUCCESS\",\"success_time\":\"2025-03-08T03:05:42+08:00\",\"transaction_id\":\"4200002501202503083775320759\",\"user_received_account\":\"支付用户零钱通\"}',NULL,4.62,4.62,'2025-03-08 03:05:26','2025-03-08 03:05:29'),(52972965109456896,52971092101062656,'edj.orders',2503080000000000021,52972965033959424,NULL,2088241317544335,'ALI_PAY',2,NULL,'{\"httpBody\":\"{\\\"alipay_trade_refund_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_change\\\":\\\"Y\\\",\\\"gmt_refund_pay\\\":\\\"2025-03-08 03:14:17\\\",\\\"out_trade_no\\\":\\\"52971092101062656\\\",\\\"refund_detail_item_list\\\":[{\\\"amount\\\":\\\"2.46\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"refund_fee\\\":\\\"2.46\\\",\\\"send_back_fee\\\":\\\"2.46\\\",\\\"trade_no\\\":\\\"2025030822001416931411839769\\\"},\\\"sign\\\":\\\"hURh9OzsaJcvL9Z1wBij+YMEelIWiNGKuWVfM0ZORM5s6xE8txQ4i/+hanQGzRpD04PXyosh1j0BdUlTrpvEFCMXLoiXMfI9VeQ1t3LrRG6A/OIyaPNduSPDFG2EdAQ7tdlSnprI+UAJaMsrJFlXActTpJWiiN7VFxiYl6CU6DgFHMLDz8MvxRectuiiywHz+TtyqaQnlxWZlS/gYmJGSl2LqXlgAwSInNDUYdl5LkajGgH2nckwSeaMvOp3Y4z0/UxJJ6wfOHmqN0eLb1rKocxRzGHbZYi3njKVvSaCmnQwksXCHqImw0LlQ5SwuF39oG0UmjqqRQ4tleD1RGAhTg==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025030822001416931411839769\",\"outTradeNo\":\"52971092101062656\",\"buyerLogonId\":\"193******36\",\"fundChange\":\"Y\",\"refundFee\":\"2.46\",\"gmtRefundPay\":\"2025-03-08 03:14:17\",\"refundDetailItemList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"2.46\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,2.46,2.46,'2025-03-08 03:14:16','2025-03-08 03:14:18'),(53254600656707584,53254255406768128,'edj.orders',2503080000000000022,53254600644124672,'50303102702025030867846284254',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":624,\"payer_total\":624,\"refund\":624,\"refund_fee\":4,\"settlement_refund\":624,\"settlement_total\":624,\"total\":624},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-03-08T21:53:24+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"53254600644124672\",\"out_trade_no\":\"53254255406768128\",\"promotion_detail\":[],\"refund_id\":\"50303102702025030867846284254\",\"status\":\"SUCCESS\",\"success_time\":\"2025-03-08T21:53:37+08:00\",\"transaction_id\":\"4200002510202503083591149587\",\"user_received_account\":\"支付用户零钱通\"}',NULL,6.24,6.24,'2025-03-08 21:53:23','2025-03-08 21:53:25'),(55150123210792960,55145977128706048,'edj.orders',2503140000000000033,55150123072380928,'50301102632025031458016171500',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":193,\"payer_total\":193,\"refund\":193,\"refund_fee\":1,\"settlement_refund\":193,\"settlement_total\":193,\"total\":193},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-03-14T03:25:33+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"55150123072380928\",\"out_trade_no\":\"55145977128706048\",\"promotion_detail\":[],\"refund_id\":\"50301102632025031458016171500\",\"status\":\"SUCCESS\",\"success_time\":\"2025-03-14T03:25:46+08:00\",\"transaction_id\":\"4200002647202503144642252559\",\"user_received_account\":\"支付用户零钱通\"}',NULL,1.93,1.93,'2025-03-14 03:25:31','2025-03-14 03:25:33'),(69818607651807232,69818235868700672,'edj.orders',2504230000000000036,69818607622447104,'50302303062025042316929135010',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":12,\"payer_total\":12,\"refund\":12,\"refund_fee\":0,\"settlement_refund\":12,\"settlement_total\":12,\"total\":12},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-23T14:52:53+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"69818607622447104\",\"out_trade_no\":\"69818235868700672\",\"promotion_detail\":[],\"refund_id\":\"50302303062025042316929135010\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-23T14:53:05+08:00\",\"transaction_id\":\"4200002662202504233911385463\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.12,0.12,'2025-04-23 14:52:52','2025-04-23 14:52:53'),(69819559481991168,69818836383981568,'edj.orders',2504230000000000037,69819559423270912,'50301303052025042365521395413',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":31,\"payer_total\":31,\"refund\":31,\"refund_fee\":0,\"settlement_refund\":31,\"settlement_total\":31,\"total\":31},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-23T14:56:40+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"69819559423270912\",\"out_trade_no\":\"69818836383981568\",\"promotion_detail\":[],\"refund_id\":\"50301303052025042365521395413\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-23T14:56:52+08:00\",\"transaction_id\":\"4200002614202504235108478893\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.31,0.31,'2025-04-23 14:56:38','2025-04-23 14:56:40'),(69820100970831872,69819681590763520,'edj.orders',2504230000000000038,69820100949860352,'50302003022025042391444911635',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":12,\"payer_total\":12,\"refund\":12,\"refund_fee\":0,\"settlement_refund\":12,\"settlement_total\":12,\"total\":12},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-23T14:58:49+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"69820100949860352\",\"out_trade_no\":\"69819681590763520\",\"promotion_detail\":[],\"refund_id\":\"50302003022025042391444911635\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-23T14:59:01+08:00\",\"transaction_id\":\"4200002665202504235468319500\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.12,0.12,'2025-04-23 14:58:48','2025-04-23 14:58:49'),(69822792988385280,69822468407975936,'edj.orders',2504230000000000039,69822792967413760,'50300503002025042341656801693',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":31,\"payer_total\":31,\"refund\":31,\"refund_fee\":0,\"settlement_refund\":31,\"settlement_total\":31,\"total\":31},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-23T15:09:30+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"69822792967413760\",\"out_trade_no\":\"69822468407975936\",\"promotion_detail\":[],\"refund_id\":\"50300503002025042341656801693\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-23T15:09:43+08:00\",\"transaction_id\":\"4200002651202504239342187965\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.31,0.31,'2025-04-23 15:09:29','2025-04-23 15:09:31'),(69829582627037184,69823213739991040,'edj.orders',2504230000000000040,69829582366990336,'50302302942025042319733174244',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":31,\"payer_total\":31,\"refund\":31,\"refund_fee\":0,\"settlement_refund\":31,\"settlement_total\":31,\"total\":31},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-23T15:36:30+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"69829582366990336\",\"out_trade_no\":\"69823213739991040\",\"promotion_detail\":[],\"refund_id\":\"50302302942025042319733174244\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-23T15:36:42+08:00\",\"transaction_id\":\"4200002606202504235506111743\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.31,0.31,'2025-04-23 15:36:28','2025-04-23 15:36:30'),(69942381567041536,69941147363729408,'edj.orders',2504230000000000042,69942381470572544,'50300403042025042325531095608',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":12,\"payer_total\":12,\"refund\":12,\"refund_fee\":0,\"settlement_refund\":12,\"settlement_total\":12,\"total\":12},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-23T23:04:43+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"69942381470572544\",\"out_trade_no\":\"69941147363729408\",\"promotion_detail\":[],\"refund_id\":\"50300403042025042325531095608\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-23T23:04:55+08:00\",\"transaction_id\":\"4200002598202504236813047844\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.12,0.12,'2025-04-23 23:04:41','2025-04-23 23:04:44'),(69942488203026432,69940149098405888,'edj.orders',2504230000000000041,69942488198832128,'50302903062025042308384726040',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":23,\"payer_total\":23,\"refund\":23,\"refund_fee\":0,\"settlement_refund\":23,\"settlement_total\":23,\"total\":23},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-23T23:05:08+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"69942488198832128\",\"out_trade_no\":\"69940149098405888\",\"promotion_detail\":[],\"refund_id\":\"50302903062025042308384726040\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-23T23:05:22+08:00\",\"transaction_id\":\"4200002601202504230917660989\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.23,0.23,'2025-04-23 23:05:07','2025-04-23 23:05:09'),(70657035968733184,70655275095056384,'edj.orders',2504250000000000043,70657035951955968,'50303503152025042535295530014',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":23,\"payer_total\":23,\"refund\":23,\"refund_fee\":0,\"settlement_refund\":23,\"settlement_total\":23,\"total\":23},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-25T22:24:30+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"70657035951955968\",\"out_trade_no\":\"70655275095056384\",\"promotion_detail\":[],\"refund_id\":\"50303503152025042535295530014\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-25T22:24:43+08:00\",\"transaction_id\":\"4200002662202504251736422995\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.23,0.23,'2025-04-25 22:24:29','2025-04-25 22:24:30'),(70657094466691072,70655514069721088,'edj.orders',2504250000000000044,70657094462496768,'50303703032025042567459908501',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":31,\"payer_total\":31,\"refund\":31,\"refund_fee\":0,\"settlement_refund\":31,\"settlement_total\":31,\"total\":31},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-25T22:24:45+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"70657094462496768\",\"out_trade_no\":\"70655514069721088\",\"promotion_detail\":[],\"refund_id\":\"50303703032025042567459908501\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-25T22:24:57+08:00\",\"transaction_id\":\"4200002598202504259770523677\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.31,0.31,'2025-04-25 22:24:43','2025-04-25 22:24:45'),(70657144626372609,70656861460520960,'edj.orders',2504250000000000052,70657144626372608,'50303403152025042543818043240',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":23,\"payer_total\":23,\"refund\":23,\"refund_fee\":0,\"settlement_refund\":23,\"settlement_total\":23,\"total\":23},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-25T22:24:56+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"70657144626372608\",\"out_trade_no\":\"70656861460520960\",\"promotion_detail\":[],\"refund_id\":\"50303403152025042543818043240\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-25T22:25:10+08:00\",\"transaction_id\":\"4200002639202504250436606034\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.23,0.23,'2025-04-25 22:24:55','2025-04-25 22:24:56'),(70657180353454080,70656674801410048,'edj.orders',2504250000000000051,70657180349259776,'50300502922025042573847115530',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":12,\"payer_total\":12,\"refund\":12,\"refund_fee\":0,\"settlement_refund\":12,\"settlement_total\":12,\"total\":12},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-25T22:25:04+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"70657180349259776\",\"out_trade_no\":\"70656674801410048\",\"promotion_detail\":[],\"refund_id\":\"50300502922025042573847115530\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-25T22:25:17+08:00\",\"transaction_id\":\"4200002593202504254502993965\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.12,0.12,'2025-04-25 22:25:03','2025-04-25 22:25:05'),(70657221205975041,70656519188537344,'edj.orders',2504250000000000050,70657221205975040,'50303203032025042518672129084',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":12,\"payer_total\":12,\"refund\":12,\"refund_fee\":0,\"settlement_refund\":12,\"settlement_total\":12,\"total\":12},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-25T22:25:14+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"70657221205975040\",\"out_trade_no\":\"70656519188537344\",\"promotion_detail\":[],\"refund_id\":\"50303203032025042518672129084\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-25T22:25:27+08:00\",\"transaction_id\":\"4200002639202504255253988312\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.12,0.12,'2025-04-25 22:25:13','2025-04-25 22:25:15'),(70657263086100480,70656178694938624,'edj.orders',2504250000000000048,70657263081906176,NULL,2088241317544335,'ALI_PAY',2,NULL,'{\"httpBody\":\"{\\\"alipay_trade_refund_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_change\\\":\\\"Y\\\",\\\"gmt_refund_pay\\\":\\\"2025-04-25 22:25:24\\\",\\\"out_trade_no\\\":\\\"70656178694938624\\\",\\\"refund_detail_item_list\\\":[{\\\"amount\\\":\\\"0.31\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"refund_fee\\\":\\\"0.31\\\",\\\"send_back_fee\\\":\\\"0.31\\\",\\\"trade_no\\\":\\\"2025042522001416931400070766\\\"},\\\"sign\\\":\\\"Q28/qkXhlXqy/Oh1QeaM1HAbRfY+6wQLTR6e9XQBWguO36gqvn05jwxCUO+14po8d/+1y+mAL9iCE4FVYGAkSfOQxUWLMN8H/Cofg+B0A25xlitQbgIiMdBigMtGm/udY1Nki1iGmpfrDxajLwRH3sjQ9zJmPCwoQTR8O7zYaJnJRNsoRqwqKVtS0RlRQ+b6PIcd2DtyJn90nbPhA+RPFU+1REhuhpQwSxCmS81vZN2atz3tbfuL6aTm9nBJPIsq8LhxXoAPS7ANGJWs9rIirnG0LgyBsnU1ko0Wb+q1Mi1VnK8Q/bkP5fEem0M7s5MZ6oHZubep1fSG9X9aJRF/XA==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025042522001416931400070766\",\"outTradeNo\":\"70656178694938624\",\"buyerLogonId\":\"193******36\",\"fundChange\":\"Y\",\"refundFee\":\"0.31\",\"gmtRefundPay\":\"2025-04-25 22:25:24\",\"refundDetailItemList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"0.31\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,0.31,0.31,'2025-04-25 22:25:23','2025-04-25 22:25:25'),(70657314579570689,70656355933642752,'edj.orders',2504250000000000049,70657314579570688,'50302603182025042588802764381',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":23,\"payer_total\":23,\"refund\":23,\"refund_fee\":0,\"settlement_refund\":23,\"settlement_total\":23,\"total\":23},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-25T22:25:36+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"70657314579570688\",\"out_trade_no\":\"70656355933642752\",\"promotion_detail\":[],\"refund_id\":\"50302603182025042588802764381\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-25T22:25:50+08:00\",\"transaction_id\":\"4200002649202504255423075506\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.23,0.23,'2025-04-25 22:25:35','2025-04-25 22:25:37'),(70657383013834752,70656017797242880,'edj.orders',2504250000000000047,70657383009640448,NULL,2088241317544335,'ALI_PAY',2,NULL,'{\"httpBody\":\"{\\\"alipay_trade_refund_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_change\\\":\\\"Y\\\",\\\"gmt_refund_pay\\\":\\\"2025-04-25 22:25:53\\\",\\\"out_trade_no\\\":\\\"70656017797242880\\\",\\\"refund_detail_item_list\\\":[{\\\"amount\\\":\\\"0.12\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"refund_fee\\\":\\\"0.12\\\",\\\"send_back_fee\\\":\\\"0.12\\\",\\\"trade_no\\\":\\\"2025042522001416931459959743\\\"},\\\"sign\\\":\\\"MqIOxPciLZ68XurNYBO85vfL5Sn92A4EdWUHhcy3qSnLs1S3Low20B2ogSkJAPZpBjDL/X2kQpfUpskUipk1XztEjJ6h25/0TvLKM0JjDkilXc0tDx0AuqJWNklDlh3QBUoXd4qbcIIGZemievYaDkmPDP+S9ndbw949NTMCG8O/3AJrocLzu0KpXHXe0wHm9XMOEEpte3Afm1Ge5jYx6Y2lZs7tsSFel6vRdYgjwy2Q2GOsl7UKzUdUKOfxIKsio1BUvcLOmgH52A9En6YrbyxB3iwMQYU4ian51Dgcao0BQwTcJDfwhZOaV5g6J/QPznxya/ERaqJ8iQM0bs/IeA==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025042522001416931459959743\",\"outTradeNo\":\"70656017797242880\",\"buyerLogonId\":\"193******36\",\"fundChange\":\"Y\",\"refundFee\":\"0.12\",\"gmtRefundPay\":\"2025-04-25 22:25:53\",\"refundDetailItemList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"0.12\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,0.12,0.12,'2025-04-25 22:25:52','2025-04-25 22:25:53'),(70657427435708416,70655671943323648,'edj.orders',2504250000000000045,70657427431514112,'50300903002025042514086632501',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":12,\"payer_total\":12,\"refund\":12,\"refund_fee\":0,\"settlement_refund\":12,\"settlement_total\":12,\"total\":12},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-25T22:26:03+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"70657427431514112\",\"out_trade_no\":\"70655671943323648\",\"promotion_detail\":[],\"refund_id\":\"50300903002025042514086632501\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-25T22:26:17+08:00\",\"transaction_id\":\"4200002604202504250388944129\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.12,0.12,'2025-04-25 22:26:02','2025-04-25 22:26:04'),(70657505789501441,70655849597263872,'edj.orders',2504250000000000046,70657505789501440,NULL,2088241317544335,'ALI_PAY',2,NULL,'{\"httpBody\":\"{\\\"alipay_trade_refund_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_change\\\":\\\"Y\\\",\\\"gmt_refund_pay\\\":\\\"2025-04-25 22:26:22\\\",\\\"out_trade_no\\\":\\\"70655849597263872\\\",\\\"refund_detail_item_list\\\":[{\\\"amount\\\":\\\"0.23\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"refund_fee\\\":\\\"0.23\\\",\\\"send_back_fee\\\":\\\"0.23\\\",\\\"trade_no\\\":\\\"2025042522001416931400120518\\\"},\\\"sign\\\":\\\"J26EZT2DCIqH3P6xMh46C6Szx+CJfR30gUKulc1n2wMTUOUpgPkHh+NzWACDllu2gHzGEx3mDE7bddJtJBar8m+l0ZMEzj8+Wg8CdeqCJl75I4Viof3xnU41kWd1SA+nBDHrmJEWdBVBepg/iXuffMGsLjSojIajLOd+TCzHQgFrEbCT7J7w9k8zfHmpA2aivie+2qRUhqb6W+kHVA+xxVmjWngwH7GeZVn6DV5Fs4koKkB2binGRRZLJ6qhX3nl8JYTMw6poxybUbfx8+zee3kHY7MoO9t89SQjts6n8CCmKjSJu5AgjUtPkz62ECASEPYzB4Ohjes/V8/a2idXwA==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025042522001416931400120518\",\"outTradeNo\":\"70655849597263872\",\"buyerLogonId\":\"193******36\",\"fundChange\":\"Y\",\"refundFee\":\"0.23\",\"gmtRefundPay\":\"2025-04-25 22:26:22\",\"refundDetailItemList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"0.23\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,0.23,0.23,'2025-04-25 22:26:21','2025-04-25 22:26:22'),(70666419813769216,70666294265667584,'edj.orders',2504250000000000053,70666419801186304,'50303003112025042556915277638',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":23,\"payer_total\":23,\"refund\":23,\"refund_fee\":0,\"settlement_refund\":23,\"settlement_total\":23,\"total\":23},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-04-25T23:01:47+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"70666419801186304\",\"out_trade_no\":\"70666294265667584\",\"promotion_detail\":[],\"refund_id\":\"50303003112025042556915277638\",\"status\":\"SUCCESS\",\"success_time\":\"2025-04-25T23:02:00+08:00\",\"transaction_id\":\"4200002654202504253964830950\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.23,0.23,'2025-04-25 23:01:46','2025-04-25 23:01:48'),(73162666101465088,73161631257616384,'edj.orders',2505020000000000054,73162665925304320,'50303503032025050273020131058',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":91,\"payer_total\":91,\"refund\":91,\"refund_fee\":1,\"settlement_refund\":91,\"settlement_total\":91,\"total\":91},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-05-02T20:20:57+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"73162665925304320\",\"out_trade_no\":\"73161631257616384\",\"promotion_detail\":[],\"refund_id\":\"50303503032025050273020131058\",\"status\":\"SUCCESS\",\"success_time\":\"2025-05-02T20:21:11+08:00\",\"transaction_id\":\"4200002662202505029717812315\",\"user_received_account\":\"支付用户零钱通\"}',NULL,0.91,0.91,'2025-05-02 20:20:56','2025-05-02 20:20:59'),(73164804005322752,73163215387516928,'edj.orders',2505020000000000055,73164803938213888,NULL,2088241317544335,'ALI_PAY',2,NULL,'{\"httpBody\":\"{\\\"alipay_trade_refund_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_change\\\":\\\"Y\\\",\\\"gmt_refund_pay\\\":\\\"2025-05-02 20:29:27\\\",\\\"out_trade_no\\\":\\\"73163215387516928\\\",\\\"refund_detail_item_list\\\":[{\\\"amount\\\":\\\"0.91\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"refund_fee\\\":\\\"0.91\\\",\\\"send_back_fee\\\":\\\"0.91\\\",\\\"trade_no\\\":\\\"2025050222001416931435978710\\\"},\\\"sign\\\":\\\"gXoxNq12nhtKivWWQigeWbnGzj9IBhpuVaHs8z+gBhKyIz8xyHjMI0HYtbBOMJNXP+p3FfmtBkGsIkpyRhn93zJ6sIL/G7H7v9wG9M05QJGw+S7moX+098x9vkTT+M0bZga9LOrHLqcJbVT/meghKT2YJSgduvSKREN5k4/B/+L1i5ULDdn87ZdutJdJd8zjDS52bKpqJW8qHRoFDJRcq0AkDM0dnvSeXsQe2AT5gm2vBGg5cDmbgmkHlaJPrA6c2Fh52B3Ife58fbuNtJZOx+6bO5Yzpbd/EEqOuaIigBH70H6sOiL3rzkYsMwtlfqIp31Ea80HXrCXbLJxPRj2mw==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025050222001416931435978710\",\"outTradeNo\":\"73163215387516928\",\"buyerLogonId\":\"193******36\",\"fundChange\":\"Y\",\"refundFee\":\"0.91\",\"gmtRefundPay\":\"2025-05-02 20:29:27\",\"refundDetailItemList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"0.91\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,0.91,0.91,'2025-05-02 20:29:25','2025-05-02 20:29:27'),(78428813348593664,78427508215406592,'edj.orders',2505170000000000060,78428813210181632,'50300203402025051747592991760',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":1632,\"payer_total\":1632,\"refund\":1632,\"refund_fee\":10,\"settlement_refund\":1632,\"settlement_total\":1632,\"total\":1632},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-05-17T09:06:50+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"78428813210181632\",\"out_trade_no\":\"78427508215406592\",\"promotion_detail\":[],\"refund_id\":\"50300203402025051747592991760\",\"status\":\"SUCCESS\",\"success_time\":\"2025-05-17T09:07:03+08:00\",\"transaction_id\":\"4200002737202505173446444362\",\"user_received_account\":\"支付用户零钱\"}',NULL,16.32,16.32,'2025-05-17 09:06:49','2025-05-17 09:06:51'),(78437886777384960,78305600056614912,'edj.orders',2505170000000000059,78437886672527360,'50300703282025051774105204929',1561414331,'WECHAT_PAY',2,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":208,\"payer_total\":208,\"refund\":208,\"refund_fee\":1,\"settlement_refund\":208,\"settlement_total\":208,\"total\":208},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-05-17T09:42:54+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"78437886672527360\",\"out_trade_no\":\"78305600056614912\",\"promotion_detail\":[],\"refund_id\":\"50300703282025051774105204929\",\"status\":\"SUCCESS\",\"success_time\":\"2025-05-17T09:42:57+08:00\",\"transaction_id\":\"4200002642202505173249045207\",\"user_received_account\":\"支付用户零钱\"}',NULL,2.08,2.08,'2025-05-17 09:42:52','2025-05-17 09:42:54'),(78440186002563072,78303740092506112,'edj.orders',2505170000000000058,78440185998368768,'50302603302025051740922122321',1561414331,'WECHAT_PAY',1,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":181,\"payer_total\":181,\"refund\":181,\"refund_fee\":1,\"settlement_refund\":181,\"settlement_total\":181,\"total\":181},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-05-17T09:52:02+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"78440185998368768\",\"out_trade_no\":\"78303740092506112\",\"promotion_detail\":[],\"refund_id\":\"50302603302025051740922122321\",\"status\":\"PROCESSING\",\"transaction_id\":\"4200002644202505177265642346\",\"user_received_account\":\"支付用户零钱\"}',NULL,1.81,1.81,'2025-05-17 09:52:00','2025-05-17 09:52:02'),(78440291313147904,78303359501361152,'edj.orders',2505170000000000057,78440291308953600,'50301203372025051723122938693',1561414331,'WECHAT_PAY',1,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":181,\"payer_total\":181,\"refund\":181,\"refund_fee\":1,\"settlement_refund\":181,\"settlement_total\":181,\"total\":181},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-05-17T09:52:27+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"78440291308953600\",\"out_trade_no\":\"78303359501361152\",\"promotion_detail\":[],\"refund_id\":\"50301203372025051723122938693\",\"status\":\"PROCESSING\",\"transaction_id\":\"4200002658202505178366420332\",\"user_received_account\":\"支付用户零钱\"}',NULL,1.81,1.81,'2025-05-17 09:52:26','2025-05-17 09:52:27'),(78441519032066048,78280507549048832,'edj.orders',2505160000000000056,78441519006900224,'50301003372025051713774501515',1561414331,'WECHAT_PAY',1,'200','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":100,\"payer_total\":100,\"refund\":100,\"refund_fee\":1,\"settlement_refund\":100,\"settlement_total\":100,\"total\":100},\"channel\":\"ORIGINAL\",\"create_time\":\"2025-05-17T09:57:20+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"78441519006900224\",\"out_trade_no\":\"78280507549048832\",\"promotion_detail\":[],\"refund_id\":\"50301003372025051713774501515\",\"status\":\"PROCESSING\",\"transaction_id\":\"4200002717202505162974435050\",\"user_received_account\":\"支付用户零钱\"}',NULL,1.00,1.00,'2025-05-17 09:57:18','2025-05-17 09:57:20');
/*!40000 ALTER TABLE `edj_refund_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_trading`
--

DROP TABLE IF EXISTS `edj_trading`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_trading` (
  `id` bigint unsigned NOT NULL COMMENT '交易订单id',
  `product_app_id` varchar(64) NOT NULL COMMENT '业务系统应用标识',
  `product_order_no` bigint unsigned NOT NULL COMMENT '业务系统订单号',
  `trading_order_no` bigint unsigned NOT NULL COMMENT '交易系统订单号（对于三方来说：商户订单）',
  `transaction_id` varchar(64) DEFAULT NULL COMMENT '第三方支付交易号',
  `trading_channel` varchar(32) NOT NULL COMMENT '支付渠道（支付宝、微信、现金、免单挂账）',
  `trading_type` varchar(32) NOT NULL COMMENT '交易类型（付款、退款、免单、挂账）',
  `trading_state` tinyint NOT NULL COMMENT '交易单状态（1待付款 2付款中 3付款失败 4已结算 5取消订单 6免单 7挂账）',
  `payee_name` varchar(64) DEFAULT NULL COMMENT '收款人姓名',
  `payee_id` bigint unsigned DEFAULT NULL COMMENT '收款人账户id',
  `payer_name` varchar(64) DEFAULT NULL COMMENT '付款人姓名',
  `payer_id` bigint unsigned DEFAULT NULL COMMENT '付款人id',
  `trading_amount` decimal(10,2) NOT NULL COMMENT '交易金额，单位：元',
  `refund` decimal(10,2) DEFAULT NULL COMMENT '退款金额（付款后，单位：元）',
  `is_refund` char(3) DEFAULT NULL COMMENT '是否有退款（YES，NO）',
  `result_code` varchar(128) DEFAULT NULL COMMENT '第三方交易返回编码（最终确认交易结果）',
  `result_msg` varchar(256) DEFAULT NULL COMMENT '第三方交易返回提示消息（最终确认交易信息）',
  `result_json` varchar(2048) DEFAULT NULL COMMENT '第三方交易返回信息json（分析交易最终信息）',
  `place_order_code` varchar(8) DEFAULT NULL COMMENT '统一下单返回编码',
  `place_order_msg` varchar(256) DEFAULT NULL COMMENT '统一下单返回信息',
  `place_order_json` varchar(2048) DEFAULT NULL COMMENT '统一下单返回信息json（用于生产二维码、Android ios唤醒支付等）',
  `enterprise_id` bigint unsigned NOT NULL COMMENT '商户号',
  `memo` varchar(256) DEFAULT NULL COMMENT '备注（订单门店，桌台信息）',
  `qr_code` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin COMMENT '二维码base64数据',
  `open_id` varchar(36) DEFAULT NULL COMMENT 'open_id标识',
  `enable_flag` varchar(16) DEFAULT NULL COMMENT '是否有效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `edj_trading_pk` (`trading_order_no`),
  KEY `edj_trading_product_order_no_index` (`product_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='交易订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_trading`
--

LOCK TABLES `edj_trading` WRITE;
/*!40000 ALTER TABLE `edj_trading` DISABLE KEYS */;
INSERT INTO `edj_trading` VALUES (52962377763414016,'edj.orders',2503080000000000019,52962371828473856,NULL,'ALI_PAY','FK',5,NULL,NULL,NULL,NULL,6.93,NULL,NULL,NULL,NULL,NULL,NULL,'https://qr.alipay.com/bax00458rvcfocbkmqus3010','{\"httpBody\":\"{\\\"alipay_trade_precreate_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"out_trade_no\\\":\\\"52962371828473856\\\",\\\"qr_code\\\":\\\"https:\\\\/\\\\/qr.alipay.com\\\\/bax00458rvcfocbkmqus3010\\\"},\\\"sign\\\":\\\"U1INxGvbMopNM1V2zQkw63GTV7O6aFHlU+esziIVDFwlPU0dGXAOW6TDkqQ2uzTf4Wbuq+fN5a9ra4/0uvZQTNr8BGSMsZsB6zmGBlP9BX9vHuijILVL/bmA5vE1RL5C9YsA41arpvz2xo5z7Zyii3Z9l1QodiTUGOafN+//Ca2Uf08kUh1LGadUIS9FGyNQD6gvNa/ROMHWybXDJFUr9BVTupAz/ZL9IHmzF0nZF7hFOvdWgte/PcLEHazwFK1tAuK3Tcp7pvFKecZNGYlm4GtBJttRsEXh9gWUOnbIfjaHLnXQqSyUWJVFImqZGdwvk61Ntnd1HPMqwbSSLgSZmQ==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"outTradeNo\":\"52962371828473856\",\"qrCode\":\"https://qr.alipay.com/bax00458rvcfocbkmqus3010\"}',2088241317544335,'服务名11','',NULL,'YES','2025-03-08 02:32:11','2025-03-08 02:32:11'),(52968724722638848,'edj.orders',2503080000000000020,52968716820570112,'4200002501202503083775320759','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,4.62,4.62,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":462,\"total\":462},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"52968716820570112\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-03-08T02:57:57+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002501202503083775320759\"}','200','weixin://wxpay/bizpayurl?pr=dtVszshz3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=dtVszshz3\\\"}\"}',1561414331,'服务名11','',NULL,'YES','2025-03-08 02:57:25','2025-03-08 02:57:25'),(52971097851453440,'edj.orders',2503080000000000021,52971092101062656,'2025030822001416931411839769','ALI_PAY','FK',4,NULL,NULL,NULL,NULL,2.46,2.46,'YES',NULL,NULL,'{\"httpBody\":\"{\\\"alipay_trade_query_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_pay_amount\\\":\\\"2.46\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_bill_list\\\":[{\\\"amount\\\":\\\"2.46\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"invoice_amount\\\":\\\"2.46\\\",\\\"out_trade_no\\\":\\\"52971092101062656\\\",\\\"point_amount\\\":\\\"0.00\\\",\\\"receipt_amount\\\":\\\"2.46\\\",\\\"send_pay_date\\\":\\\"2025-03-08 03:08:30\\\",\\\"total_amount\\\":\\\"2.46\\\",\\\"trade_no\\\":\\\"2025030822001416931411839769\\\",\\\"trade_status\\\":\\\"TRADE_SUCCESS\\\"},\\\"sign\\\":\\\"PMFevAf6rRQlw5cjnMYdJ7ln3V5Wj8OUTAFMPx+wz4wMe44nZoyjZ4Qp6qXgY5Mpl9nD0gwfqi7K3VQ8bDK9UDJQaW8Oz9KInO6Bm6XmB2NFA5wZ+I82UjYnqk4Tw5thCaPoDkJifaUqcFiUsrc5hCxDc7N2sU+hRWJfM5CDw2QOTYnCtSqtJ6SDxX4xcsFrcx2q7CjbP+KhuUgf8z9wTn8A4E+70l40QrN+3CR86BpctRrVOnv5womyBS8dnMWEum5wWfj/sdlJsjnHZF2ROB5vanCAY1c6vl34xxbM4WdYoPUccEFY9ehQ8B39RMWIUl7hbf5yKVZcKlv3HlFUEQ==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025030822001416931411839769\",\"outTradeNo\":\"52971092101062656\",\"buyerLogonId\":\"193******36\",\"tradeStatus\":\"TRADE_SUCCESS\",\"totalAmount\":\"2.46\",\"buyerPayAmount\":\"2.46\",\"pointAmount\":\"0.00\",\"invoiceAmount\":\"2.46\",\"sendPayDate\":\"2025-03-08 03:08:30\",\"receiptAmount\":\"2.46\",\"fundBillList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"2.46\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,'https://qr.alipay.com/bax02012rb4ms48ge1um30df','{\"httpBody\":\"{\\\"alipay_trade_precreate_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"out_trade_no\\\":\\\"52971092101062656\\\",\\\"qr_code\\\":\\\"https:\\\\/\\\\/qr.alipay.com\\\\/bax02012rb4ms48ge1um30df\\\"},\\\"sign\\\":\\\"R6XTYWupv37DsW0W1Dt0LBsfzbKTpCw0gF3LIX1h88kKiNlF0NGDQR1YCNQEXlqf9ZGeNjY1rWeY++vcbwz+NAiNAkYTpGgZFIa82l5O9D9gDOYYGg6cxL9+w6ESOQTxwWMlJOUfaEGjMBXY1eWaXnLTyzU4Gbpkh1jhipuugbX8NDo1vD0Mf2yK5W4+gYXZ/eQ40QIRR1mc6bOp66i+KzRqMkxScqyweg0SV1TGpkamo4pBBK/rEBzB/NN9hR+F/9e+Oibh5VilT4+vbgAaW0v3UnkYB1WrQ4eV95FtTnc+lePK2SgelNBLO0WODw4OH8Jxx+7uunbfP4hOB1Ht/A==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"outTradeNo\":\"52971092101062656\",\"qrCode\":\"https://qr.alipay.com/bax02012rb4ms48ge1um30df\"}',2088241317544335,'服务名称9','',NULL,'YES','2025-03-08 03:06:50','2025-03-08 03:06:50'),(53254262302203904,'edj.orders',2503080000000000022,53254255406768128,'4200002510202503083591149587','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,6.24,6.24,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":624,\"total\":624},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"53254255406768128\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-03-08T21:52:13+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002510202503083591149587\"}','200','weixin://wxpay/bizpayurl?pr=EpBjLWoz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=EpBjLWoz1\\\"}\"}',1561414331,'服务名称5','',NULL,'YES','2025-03-08 21:52:02','2025-03-08 21:52:02'),(55145985324376064,'edj.orders',2503140000000000033,55145977128706048,'4200002647202503144642252559','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,1.93,1.93,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":193,\"total\":193},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"55145977128706048\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-03-14T03:09:14+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002647202503144642252559\"}','200','weixin://wxpay/bizpayurl?pr=B2dFSJhz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=B2dFSJhz1\\\"}\"}',1561414331,'服务名称9','',NULL,'YES','2025-03-14 03:09:05','2025-03-14 03:09:05'),(69818243464585216,'edj.orders',2504230000000000036,69818235868700672,'4200002662202504233911385463','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.12,0.12,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":12,\"total\":12},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"69818235868700672\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-23T14:51:42+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002662202504233911385463\"}','200','weixin://wxpay/bizpayurl?pr=v2FcnS6z3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=v2FcnS6z3\\\"}\"}',1561414331,'服务55','',NULL,'YES','2025-04-23 14:51:25','2025-04-23 14:51:25'),(69818840569896960,'edj.orders',2504230000000000037,69818836383981568,'4200002614202504235108478893','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.31,0.31,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":31,\"total\":31},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"69818836383981568\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-23T14:53:56+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002614202504235108478893\"}','200','weixin://wxpay/bizpayurl?pr=PQTb0iIz3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=PQTb0iIz3\\\"}\"}',1561414331,'22','',NULL,'YES','2025-04-23 14:53:47','2025-04-23 14:53:47'),(69819684874903552,'edj.orders',2504230000000000038,69819681590763520,'4200002665202504235468319500','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.12,0.12,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":12,\"total\":12},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"69819681590763520\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-23T14:57:18+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002665202504235468319500\"}','200','weixin://wxpay/bizpayurl?pr=Cnjuyw6z1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=Cnjuyw6z1\\\"}\"}',1561414331,'服务55','',NULL,'YES','2025-04-23 14:57:08','2025-04-23 14:57:08'),(69822474795900928,'edj.orders',2504230000000000039,69822468407975936,'4200002651202504239342187965','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.31,0.31,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":31,\"total\":31},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"69822468407975936\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-23T15:08:22+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002651202504239342187965\"}','200','weixin://wxpay/bizpayurl?pr=8Ct20Qiz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=8Ct20Qiz1\\\"}\"}',1561414331,'22','',NULL,'YES','2025-04-23 15:08:14','2025-04-23 15:08:14'),(69823216315293696,'edj.orders',2504230000000000040,69823213739991040,'4200002606202504235506111743','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.31,0.31,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":31,\"total\":31},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"69823213739991040\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-23T15:11:16+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002606202504235506111743\"}','200','weixin://wxpay/bizpayurl?pr=iQSEiQyz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=iQSEiQyz1\\\"}\"}',1561414331,'22','',NULL,'YES','2025-04-23 15:11:10','2025-04-23 15:11:10'),(69940158887911424,'edj.orders',2504230000000000041,69940149098405888,'4200002601202504230917660989','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.23,0.23,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":23,\"total\":23},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"69940149098405888\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-23T22:56:01+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002601202504230917660989\"}','200','weixin://wxpay/bizpayurl?pr=GtemVaBz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=GtemVaBz1\\\"}\"}',1561414331,'服务名称6','',NULL,'YES','2025-04-23 22:55:52','2025-04-23 22:55:52'),(69941151264432128,'edj.orders',2504230000000000042,69941147363729408,'4200002598202504236813047844','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.12,0.12,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":12,\"total\":12},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"69941147363729408\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-23T22:59:56+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002598202504236813047844\"}','200','weixin://wxpay/bizpayurl?pr=OVLUxlTz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=OVLUxlTz1\\\"}\"}',1561414331,'服务55','',NULL,'YES','2025-04-23 22:59:48','2025-04-23 22:59:48'),(70655282292482048,'edj.orders',2504250000000000043,70655275095056384,'4200002662202504251736422995','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.23,0.23,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":23,\"total\":23},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"70655275095056384\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-25T22:17:45+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002662202504251736422995\"}','200','weixin://wxpay/bizpayurl?pr=3jf47z2z3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=3jf47z2z3\\\"}\"}',1561414331,'服务名称6','',NULL,'YES','2025-04-25 22:17:31','2025-04-25 22:17:31'),(70655516649218048,'edj.orders',2504250000000000044,70655514069721088,'4200002598202504259770523677','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.31,0.31,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":31,\"total\":31},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"70655514069721088\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-25T22:18:33+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002598202504259770523677\"}','200','weixin://wxpay/bizpayurl?pr=F2E2C6Cz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=F2E2C6Cz1\\\"}\"}',1561414331,'22','',NULL,'YES','2025-04-25 22:18:27','2025-04-25 22:18:27'),(70655674577346560,'edj.orders',2504250000000000045,70655671943323648,'4200002604202504250388944129','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.12,0.12,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":12,\"total\":12},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"70655671943323648\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-25T22:19:10+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002604202504250388944129\"}','200','weixin://wxpay/bizpayurl?pr=looxhEJz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=looxhEJz1\\\"}\"}',1561414331,'服务55','',NULL,'YES','2025-04-25 22:19:04','2025-04-25 22:19:04'),(70655852399058944,'edj.orders',2504250000000000046,70655849597263872,'2025042522001416931400120518','ALI_PAY','FK',4,NULL,NULL,NULL,NULL,0.23,0.23,'YES',NULL,NULL,'{\"httpBody\":\"{\\\"alipay_trade_query_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_pay_amount\\\":\\\"0.23\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_bill_list\\\":[{\\\"amount\\\":\\\"0.23\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"invoice_amount\\\":\\\"0.23\\\",\\\"out_trade_no\\\":\\\"70655849597263872\\\",\\\"point_amount\\\":\\\"0.00\\\",\\\"receipt_amount\\\":\\\"0.23\\\",\\\"send_pay_date\\\":\\\"2025-04-25 22:19:52\\\",\\\"total_amount\\\":\\\"0.23\\\",\\\"trade_no\\\":\\\"2025042522001416931400120518\\\",\\\"trade_status\\\":\\\"TRADE_SUCCESS\\\"},\\\"sign\\\":\\\"RBlRLdNcoSy9EcgRgoBNCKkqpNodHxKBuILBxh8y08rD0PJxCMYP3EaUqBES74onEPYeuVWjgwlbadaAt9hj12ez1Udfj2Dmk8gvJhttZlcTBJmkXkXEBdzee2RsOOjFFwttBzTOHiJg7FZGW1BtKJoAX7vdziYmbsXU1eOsi0EGdbo6IkxA5rySgyMVOZPa0s1XXEqwIUJp6uQY2zp6n8iQW+f1FGv/Cz9yt2f2r9SQmmQXbQFmNxPEuJxsUuvaPeAqb+4GBnSpyMuTVmWFA7GEBe0WUwXLRNCgfPlufJNZMZzV6UhbQScjHY4RI44tjSnF4z4KE08nJNiTEo46mw==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025042522001416931400120518\",\"outTradeNo\":\"70655849597263872\",\"buyerLogonId\":\"193******36\",\"tradeStatus\":\"TRADE_SUCCESS\",\"totalAmount\":\"0.23\",\"buyerPayAmount\":\"0.23\",\"pointAmount\":\"0.00\",\"invoiceAmount\":\"0.23\",\"sendPayDate\":\"2025-04-25 22:19:52\",\"receiptAmount\":\"0.23\",\"fundBillList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"0.23\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,'https://qr.alipay.com/bax01960mjh6ljb4ctek2519','{\"httpBody\":\"{\\\"alipay_trade_precreate_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"out_trade_no\\\":\\\"70655849597263872\\\",\\\"qr_code\\\":\\\"https:\\\\/\\\\/qr.alipay.com\\\\/bax01960mjh6ljb4ctek2519\\\"},\\\"sign\\\":\\\"Cx1+Uo33nujz8ePnZxZZSJ+HYkwi9c13g2xTJRBbk3jUX2MntbzNGU4EX7Ek6+DrytAg9ZTzvr8BPqxBFOGohjV0Lp8Lc0/rVnu+a9P7n1WVL+ox5ye4u0+WDWLP96vKVqiFQO5cMEqdCw34XudNNyHTjh68RCNGa7nZz0Avye2pjmPCVqa5TVZNEfaM2HVJYRANnAK3VfZwCZbD2RXKYtd+L5MXXpLhsgHblBe/aesQ3G7ESN9mzbDtstKkYx28YMobMzc6ZW2KGIvPFl01Xg7Vtlzx37qS9VPtE2MJoCVKIo6C/M3KUDvXwbpgZPL5nydHWa0jM+ejm/nnwgKFPA==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"outTradeNo\":\"70655849597263872\",\"qrCode\":\"https://qr.alipay.com/bax01960mjh6ljb4ctek2519\"}',2088241317544335,'服务名称6','',NULL,'YES','2025-04-25 22:19:47','2025-04-25 22:19:47'),(70656019114254336,'edj.orders',2504250000000000047,70656017797242880,'2025042522001416931459959743','ALI_PAY','FK',4,NULL,NULL,NULL,NULL,0.12,0.12,'YES',NULL,NULL,'{\"httpBody\":\"{\\\"alipay_trade_query_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_pay_amount\\\":\\\"0.12\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_bill_list\\\":[{\\\"amount\\\":\\\"0.12\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"invoice_amount\\\":\\\"0.12\\\",\\\"out_trade_no\\\":\\\"70656017797242880\\\",\\\"point_amount\\\":\\\"0.00\\\",\\\"receipt_amount\\\":\\\"0.12\\\",\\\"send_pay_date\\\":\\\"2025-04-25 22:20:33\\\",\\\"total_amount\\\":\\\"0.12\\\",\\\"trade_no\\\":\\\"2025042522001416931459959743\\\",\\\"trade_status\\\":\\\"TRADE_SUCCESS\\\"},\\\"sign\\\":\\\"XTkqfRMG/0G8CwZwy9LQral/6rhAIBAc5mnMV8RWH69fwHdXwihpdo/SDr6kR87pv4BU0x+gUBCVbmy7lQFVSrL7uD9P/iYGFJMWtzQ3DWNw3ENlVBoC8PTvbk3dOD8Q60GpCc1WUki/jYYruJCYpkSxwEAk7fB8kqk/gvJfVx9DoU7h3sqV5gCmMsQsdTp7jGeShJR3vMI7ZwSvo/sWxto3ufNoEqcircpvBLX35vKP3bj3US3YUeKEs39P41BCWrrog3+jocmWNRx7jWwdcAGbZanUgyEp7t4Gec+TwaYnM+mqN5DFcTia3HyNScSY+zsroXl8mc0/UkBCDq8oFQ==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025042522001416931459959743\",\"outTradeNo\":\"70656017797242880\",\"buyerLogonId\":\"193******36\",\"tradeStatus\":\"TRADE_SUCCESS\",\"totalAmount\":\"0.12\",\"buyerPayAmount\":\"0.12\",\"pointAmount\":\"0.00\",\"invoiceAmount\":\"0.12\",\"sendPayDate\":\"2025-04-25 22:20:33\",\"receiptAmount\":\"0.12\",\"fundBillList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"0.12\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,'https://qr.alipay.com/bax05081oimvtuq1f67p256b','{\"httpBody\":\"{\\\"alipay_trade_precreate_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"out_trade_no\\\":\\\"70656017797242880\\\",\\\"qr_code\\\":\\\"https:\\\\/\\\\/qr.alipay.com\\\\/bax05081oimvtuq1f67p256b\\\"},\\\"sign\\\":\\\"bftY4xomo1uLIlNItRFqG+QMosoOFxJNRu+Tvarz7JwC9Ha1XruWX5mVcpA1Y/DwtHsNCorr3A0Pe57+CZqXzWLz5sWqVMRhpJQHBS7Y5N+ZIeEwNvJWdm3q1vGvAZD3MbzSBsRWhAZjx2jVaYDATqX6X2RSCqSd/ccg4n1ljexGuzOwxeNc7agcOje0wX88Bi4X2JRnAx0WCowO6gbMpyEqQCvxegDI1wOLXJeLanksaw6udij5wZy3RezMlYMU8JU/Fp0QO5laNqR6B9Avb5ZppfJHrRXtPfUui1QDWkTVFYVtgbiqZBTky+7FwmBUR1EY8M5o3z3ziYrdcKDnWA==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"outTradeNo\":\"70656017797242880\",\"qrCode\":\"https://qr.alipay.com/bax05081oimvtuq1f67p256b\"}',2088241317544335,'服务55','',NULL,'YES','2025-04-25 22:20:27','2025-04-25 22:20:27'),(70656179798040576,'edj.orders',2504250000000000048,70656178694938624,'2025042522001416931400070766','ALI_PAY','FK',4,NULL,NULL,NULL,NULL,0.31,0.31,'YES',NULL,NULL,'{\"httpBody\":\"{\\\"alipay_trade_query_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_pay_amount\\\":\\\"0.31\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_bill_list\\\":[{\\\"amount\\\":\\\"0.31\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"invoice_amount\\\":\\\"0.31\\\",\\\"out_trade_no\\\":\\\"70656178694938624\\\",\\\"point_amount\\\":\\\"0.00\\\",\\\"receipt_amount\\\":\\\"0.31\\\",\\\"send_pay_date\\\":\\\"2025-04-25 22:21:12\\\",\\\"total_amount\\\":\\\"0.31\\\",\\\"trade_no\\\":\\\"2025042522001416931400070766\\\",\\\"trade_status\\\":\\\"TRADE_SUCCESS\\\"},\\\"sign\\\":\\\"cxHesnaNbAspWLD0p4+3RqvsFRver2wHszRHPzZc+Uyp/rITRYMk7L1pStnfeCgL50f6E3YPmD+QNtdNZpFXGH6aWuoEBTMhq+Q48P0B4IEujoiGDEsKeXdhUj/GCBnkRv7d8KNUcCaojtX8FC52oTwqhLKsk4o509AjV8v1IbxUXLYAGztAYQz9kgMR7xXRxOblJDIahZ9ePefAwhNBbOdn2nHLSeISNolmoRKP5v8uXp6cg4aH0QvxucCiK5j3Uq+wYrv8vfszHI2U5yibpM1T6rG7SXoWWeiwA4gXeErpPO1fL1G5QDPnW9nWayGvFyR5OXS4Mv1+2qS6EEVyRQ==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025042522001416931400070766\",\"outTradeNo\":\"70656178694938624\",\"buyerLogonId\":\"193******36\",\"tradeStatus\":\"TRADE_SUCCESS\",\"totalAmount\":\"0.31\",\"buyerPayAmount\":\"0.31\",\"pointAmount\":\"0.00\",\"invoiceAmount\":\"0.31\",\"sendPayDate\":\"2025-04-25 22:21:12\",\"receiptAmount\":\"0.31\",\"fundBillList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"0.31\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,'https://qr.alipay.com/bax07803aqxfkvlww5ur2579','{\"httpBody\":\"{\\\"alipay_trade_precreate_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"out_trade_no\\\":\\\"70656178694938624\\\",\\\"qr_code\\\":\\\"https:\\\\/\\\\/qr.alipay.com\\\\/bax07803aqxfkvlww5ur2579\\\"},\\\"sign\\\":\\\"XAPHF6odwe6z9XaVZK9DwC9DB7Yqipvt+OAbcNmxMCjwliULKeKUAAF8n67mbp7ESBeP4lwXdmyWHaze4ukzx1l3pF9hb6LWeWeiImDDUiLIumcNL0Qlr7yiUBAxVJuE6hzGTXTQktd4q5pmsojeVoX+2QIDVASi1OQkZiE/IH6vi+31qtig/iEo15SnQWxxnTIovYnf8MchxDFWW+ln3keMsKZGUV21Xd/HEDty4ErFpC8mzwe5SMd8yRAtiR8DSiRHq5JHSgaax9Zv2zSd2v16dovDQvxqdMhK0k15ehAZl9wojjjnWfV6O6uydcYxRoT1SM9np9ILrmk1erLOlw==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"outTradeNo\":\"70656178694938624\",\"qrCode\":\"https://qr.alipay.com/bax07803aqxfkvlww5ur2579\"}',2088241317544335,'22','',NULL,'YES','2025-04-25 22:21:05','2025-04-25 22:21:05'),(70656359653990400,'edj.orders',2504250000000000049,70656355933642752,'4200002649202504255423075506','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.23,0.23,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":23,\"total\":23},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"70656355933642752\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-25T22:21:54+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002649202504255423075506\"}','200','weixin://wxpay/bizpayurl?pr=JUpuNB2z1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=JUpuNB2z1\\\"}\"}',1561414331,'服务名称6','',NULL,'YES','2025-04-25 22:21:48','2025-04-25 22:21:48'),(70656521931612160,'edj.orders',2504250000000000050,70656519188537344,'4200002639202504255253988312','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.12,0.12,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":12,\"total\":12},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"70656519188537344\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-25T22:22:32+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002639202504255253988312\"}','200','weixin://wxpay/bizpayurl?pr=QKoTbcmz3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=QKoTbcmz3\\\"}\"}',1561414331,'服务55','',NULL,'YES','2025-04-25 22:22:26','2025-04-25 22:22:26'),(70656677565456384,'edj.orders',2504250000000000051,70656674801410048,'4200002593202504254502993965','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.12,0.12,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":12,\"total\":12},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"70656674801410048\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-25T22:23:18+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002593202504254502993965\"}','200','weixin://wxpay/bizpayurl?pr=aBDO1wfz3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=aBDO1wfz3\\\"}\"}',1561414331,'服务55','',NULL,'YES','2025-04-25 22:23:04','2025-04-25 22:23:04'),(70656864027435008,'edj.orders',2504250000000000052,70656861460520960,'4200002639202504250436606034','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.23,0.23,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":23,\"total\":23},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"70656861460520960\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-25T22:23:53+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002639202504250436606034\"}','200','weixin://wxpay/bizpayurl?pr=1TJhlnjz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=1TJhlnjz1\\\"}\"}',1561414331,'服务名称6','',NULL,'YES','2025-04-25 22:23:48','2025-04-25 22:23:48'),(70666299818926080,'edj.orders',2504250000000000053,70666294265667584,'4200002654202504253964830950','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.23,0.23,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":23,\"total\":23},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"70666294265667584\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-04-25T23:01:27+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002654202504253964830950\"}','200','weixin://wxpay/bizpayurl?pr=6R8EM7tz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=6R8EM7tz1\\\"}\"}',1561414331,'服务名称6','',NULL,'YES','2025-04-25 23:01:18','2025-04-25 23:01:18'),(73161639151296512,'edj.orders',2505020000000000054,73161631257616384,'4200002662202505029717812315','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,0.91,0.91,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":91,\"total\":91},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"73161631257616384\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-05-02T20:17:33+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002662202505029717812315\"}','200','weixin://wxpay/bizpayurl?pr=Zgf2fbez1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=Zgf2fbez1\\\"}\"}',1561414331,'日常清洁','',NULL,'YES','2025-05-02 20:16:51','2025-05-02 20:16:51'),(73163179194867712,'edj.orders',2505020000000000055,73163174878928896,NULL,'WECHAT_PAY','FK',5,NULL,NULL,NULL,NULL,0.91,NULL,NULL,NULL,NULL,NULL,'200','weixin://wxpay/bizpayurl?pr=6fFeRu0z1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=6fFeRu0z1\\\"}\"}',1561414331,'日常清洁','',NULL,'YES','2025-05-02 20:22:58','2025-05-02 20:22:58'),(73163224262664192,'edj.orders',2505020000000000055,73163215387516928,'2025050222001416931435978710','ALI_PAY','FK',4,NULL,NULL,NULL,NULL,0.91,0.91,'YES',NULL,NULL,'{\"httpBody\":\"{\\\"alipay_trade_query_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"buyer_logon_id\\\":\\\"193******36\\\",\\\"buyer_pay_amount\\\":\\\"0.91\\\",\\\"buyer_user_id\\\":\\\"2088142204716939\\\",\\\"fund_bill_list\\\":[{\\\"amount\\\":\\\"0.91\\\",\\\"fund_channel\\\":\\\"ALIPAYACCOUNT\\\"}],\\\"invoice_amount\\\":\\\"0.91\\\",\\\"out_trade_no\\\":\\\"73163215387516928\\\",\\\"point_amount\\\":\\\"0.00\\\",\\\"receipt_amount\\\":\\\"0.91\\\",\\\"send_pay_date\\\":\\\"2025-05-02 20:23:37\\\",\\\"total_amount\\\":\\\"0.91\\\",\\\"trade_no\\\":\\\"2025050222001416931435978710\\\",\\\"trade_status\\\":\\\"TRADE_SUCCESS\\\"},\\\"sign\\\":\\\"cF0zrXQzVaPfaz5XRa3DNUljSz/bgdXJDrPv3riSZu1EDCwZ74mxmDsR9bsH5LPzdupNXNG+Z9FlOxtgy3YSrOQcK/6kyv3mqk9Czxt4iQm3ZO4C7qdk1oOPA65jmP6Gw8Y5kOhJGz2I1xamGZao5i2HTRNFKmO1PjiRh9qYZbvcjZdGmiMt4VBZ5B5oTMY3b6E1r+K9/jpkzP/yXRTxm5IkeXzSRiyBcvKqo5Mg9NsXGO2OCz5Ggv7nlKgfYwNNfiJhp5EwOCnm1VLfQjUg2EbqTyoSWYKOejeRMVHOt5N7iie+zPzrqPaTJui5Gwki6i56XPVNj3eQQJpw9PcsuA==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"tradeNo\":\"2025050222001416931435978710\",\"outTradeNo\":\"73163215387516928\",\"buyerLogonId\":\"193******36\",\"tradeStatus\":\"TRADE_SUCCESS\",\"totalAmount\":\"0.91\",\"buyerPayAmount\":\"0.91\",\"pointAmount\":\"0.00\",\"invoiceAmount\":\"0.91\",\"sendPayDate\":\"2025-05-02 20:23:37\",\"receiptAmount\":\"0.91\",\"fundBillList\":[{\"fundChannel\":\"ALIPAYACCOUNT\",\"amount\":\"0.91\"}],\"buyerUserId\":\"2088142204716939\"}',NULL,'https://qr.alipay.com/bax02085p1fsizop5hfv30b6','{\"httpBody\":\"{\\\"alipay_trade_precreate_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"out_trade_no\\\":\\\"73163215387516928\\\",\\\"qr_code\\\":\\\"https:\\\\/\\\\/qr.alipay.com\\\\/bax02085p1fsizop5hfv30b6\\\"},\\\"sign\\\":\\\"GQVtmn6GxRK8plw7nuzizVzBinrQGIdRlz4FNUkseKex6Zdzl2hbjfwgMxWBOe9hEm5e5WjqH/Rm/laGtpADogVhKywPMZcg4ypMv7sKXG/ukYHEoqTHcW347uTL4setaKRAq4R/YNStp4qtTYG0bGfr/tA1gHUEYQ8Hl3EdbM38CmgEETI5Jt89U8ybTYcPCRPtYad820z3gCXjuWbsdg46ckigm87XwRoYNgRepeaKk/mxS5Vf4e6Rv6ChtALvj4HzrWJMIUPm/H3Dtd3S1PUqQpbKXZcTcP/ssrvVDK7ggpqXPKzMNhxrdS44qUJQGkQEwYpxs75J7YGMkdROYA==\\\"}\",\"code\":\"10000\",\"msg\":\"Success\",\"outTradeNo\":\"73163215387516928\",\"qrCode\":\"https://qr.alipay.com/bax02085p1fsizop5hfv30b6\"}',2088241317544335,'日常清洁','',NULL,'YES','2025-05-02 20:23:09','2025-05-02 20:23:09'),(78280515501449216,'edj.orders',2505160000000000056,78280507549048832,'4200002717202505162974435050','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,1.00,1.00,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":100,\"total\":100},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"78280507549048832\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-05-16T23:17:39+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002717202505162974435050\"}','200','weixin://wxpay/bizpayurl?pr=HesZaLGz3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=HesZaLGz3\\\"}\"}',1561414331,'空调清洗','',NULL,'YES','2025-05-16 23:17:32','2025-05-16 23:17:32'),(78303365763457024,'edj.orders',2505170000000000057,78303359501361152,'4200002658202505178366420332','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,1.81,1.81,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":181,\"total\":181},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"78303359501361152\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-05-17T00:48:27+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002658202505178366420332\"}','200','weixin://wxpay/bizpayurl?pr=Iyl41Gaz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=Iyl41Gaz1\\\"}\"}',1561414331,'日常清洁','',NULL,'YES','2025-05-17 00:48:20','2025-05-17 00:48:20'),(78303744957898752,'edj.orders',2505170000000000058,78303740092506112,'4200002644202505177265642346','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,1.81,1.81,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":181,\"total\":181},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"78303740092506112\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-05-17T00:49:57+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002644202505177265642346\"}','200','weixin://wxpay/bizpayurl?pr=BtvTAFPz1','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=BtvTAFPz1\\\"}\"}',1561414331,'日常清洁','',NULL,'YES','2025-05-17 00:49:50','2025-05-17 00:49:50'),(78305606079635456,'edj.orders',2505170000000000059,78305600056614912,'4200002642202505173249045207','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,2.08,2.08,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":208,\"total\":208},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"78305600056614912\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-05-17T00:57:22+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002642202505173249045207\"}','200','weixin://wxpay/bizpayurl?pr=6CXwbIGz3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=6CXwbIGz3\\\"}\"}',1561414331,'深度清洁','',NULL,'YES','2025-05-17 00:57:14','2025-05-17 00:57:14'),(78427514624303104,'edj.orders',2505170000000000060,78427508215406592,'4200002737202505173446444362','WECHAT_PAY','FK',4,NULL,NULL,NULL,NULL,16.32,16.32,'YES','SUCCESS','支付成功','{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":1632,\"total\":1632},\"appid\":\"wx6592a2db3f85ed25\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1561414331\",\"out_trade_no\":\"78427508215406592\",\"payer\":{\"openid\":\"otdlR4xSMgm3atKdQJtCcaLsgf1w\"},\"promotion_detail\":[],\"success_time\":\"2025-05-17T09:01:48+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200002737202505173446444362\"}','200','weixin://wxpay/bizpayurl?pr=a6SHdCCz3','{\"status\":200,\"body\":\"{\\\"code_url\\\":\\\"weixin://wxpay/bizpayurl?pr=a6SHdCCz3\\\"}\"}',1561414331,'擦玻璃','',NULL,'YES','2025-05-17 09:01:39','2025-05-17 09:01:39');
/*!40000 ALTER TABLE `edj_trading` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_undo_log`
--

DROP TABLE IF EXISTS `edj_undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_undo_log` (
  `branch_id` bigint NOT NULL COMMENT '分支事务ID (branch transaction id)',
  `xid` varchar(128) NOT NULL COMMENT '全局事务ID (global transaction id)',
  `context` varchar(128) NOT NULL COMMENT 'undo_log上下文，如序列化信息 (undo_log context, such as serialization)',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息 (rollback info)',
  `log_status` int NOT NULL COMMENT '日志状态，0：正常状态，1：防御状态 (log status, 0: normal status, 1: defense status)',
  `log_created` datetime(6) NOT NULL COMMENT '创建时间 (create datetime)',
  `log_modified` datetime(6) NOT NULL COMMENT '修改时间 (modify datetime)',
  PRIMARY KEY (`branch_id`,`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT事务模式的undo表 (AT transaction mode undo table)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_undo_log`
--

LOCK TABLES `edj_undo_log` WRITE;
/*!40000 ALTER TABLE `edj_undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `edj_undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT transaction mode undo table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `edj-user`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `edj-user` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `edj-user`;

--
-- Table structure for table `edj_authority`
--

DROP TABLE IF EXISTS `edj_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_authority` (
  `id` bigint unsigned NOT NULL COMMENT '权限ID',
  `parent_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `name` varchar(64) NOT NULL COMMENT '权限名',
  `permission` varchar(128) NOT NULL COMMENT '权限标识',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '权限状态（0正常 1停用）',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `edj_authority_pk` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='权限信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_authority`
--

LOCK TABLES `edj_authority` WRITE;
/*!40000 ALTER TABLE `edj_authority` DISABLE KEYS */;
INSERT INTO `edj_authority` VALUES (337736198864896,0,'基础运营服务','foundations',0,'',0,0,'2024-10-13 21:20:40','2024-10-13 21:20:40',0),(338323032326144,337736198864896,'服务类型管理','foundations:serverType',0,'',0,0,'2024-10-13 21:23:00','2024-10-21 16:50:54',0),(338523956264960,338323032326144,'新增服务类型','foundations:serverType:add',0,'',0,0,'2024-10-13 21:24:03','2024-10-13 21:24:03',0),(621079163387904,338323032326144,'服务类型分页查询','foundations:serverType:page',0,'',0,0,'2024-10-14 16:06:33','2024-10-14 16:06:33',0),(724530186764288,338323032326144,'启用服务类型','foundations:serverType:activate',0,'',0,0,'2024-10-14 22:57:40','2024-10-14 22:57:40',0),(724616602009600,338323032326144,'禁用服务类型','foundations:serverType:deactivate',0,'',0,0,'2024-10-14 22:58:00','2024-10-14 22:58:00',0),(724750459027456,338323032326144,'服务类型修改','foundations:serverType:update',0,'',0,0,'2024-10-14 22:58:32','2024-10-14 22:58:32',0),(724935155204096,338323032326144,'服务类型删除','foundations:serverType:delete',0,'',0,0,'2024-10-14 22:59:16','2024-10-14 22:59:16',0),(1083983155249152,338323032326144,'根据活动状态查询服务类型','foundations:serverType:status',0,'',0,0,'2024-10-15 22:46:00','2024-10-15 22:46:00',0),(1084430179975168,337736198864896,'服务项管理','foundations:serveItem',0,'',0,0,'2024-10-15 22:47:47','2024-10-21 16:50:24',0),(1085036588253184,1084430179975168,'根据id查询服务项','foundations:serveItem:findById',0,'',0,0,'2024-10-15 22:50:11','2024-10-15 22:50:11',0),(1085036588253185,1084430179975168,'删除服务项','foundations:serveItem:delete',0,'',0,0,'2024-10-15 22:50:11','2024-10-15 22:50:11',0),(1085036588253186,1084430179975168,'启用服务项','foundations:serveItem:activate',0,'',0,0,'2024-10-15 22:50:11','2024-10-15 22:50:11',0),(1085036588253187,1084430179975168,'新增服务项','foundations:serveItem:add',0,'',0,0,'2024-10-15 22:50:11','2024-10-15 22:50:11',0),(1085036588253188,1084430179975168,'分页查询服务项','foundations:serveItem:page',0,'',0,0,'2024-10-15 22:50:11','2024-10-15 22:50:11',0),(1085036588253189,1084430179975168,'禁用服务项','foundations:serveItem:deactivate',0,'',0,0,'2024-10-15 22:50:11','2024-10-15 22:50:11',0),(1085036588253190,1084430179975168,'修改服务项','foundations:serveItem:update',0,'',0,0,'2024-10-15 22:50:11','2024-10-15 22:50:11',0),(3168590889566208,337736198864896,'区域服务管理','foundations:region',0,'',0,0,'2024-10-21 16:49:28','2024-10-21 16:50:54',0),(3169606045351936,3168590889566208,'新增区域','foundations:region:add',0,'',0,0,'2024-10-21 16:53:32','2024-10-21 16:53:32',0),(3169606045351937,3168590889566208,'启用区域','foundations:region:activate',0,'',0,0,'2024-10-21 16:53:32','2024-10-21 16:53:32',0),(3169606045351938,3168590889566208,'删除区域','foundations:region:delete',0,'',0,0,'2024-10-21 16:53:31','2024-10-21 16:53:31',0),(3169606045351939,3168590889566208,'根据id查询区域','foundations:region:findById',0,'',0,0,'2024-10-21 16:53:32','2024-10-21 16:53:32',0),(3169606045351940,3168590889566208,'禁用区域','foundations:region:deactivate',0,'',0,0,'2024-10-21 16:53:33','2024-10-21 16:53:33',0),(3169606045351941,3168590889566208,'修改区域','foundations:region:update',0,'',0,0,'2024-10-21 16:53:32','2024-10-21 16:53:32',0),(3169606045351942,3168590889566208,'区域分页查询','foundations:region:page',0,'',0,0,'2024-10-21 16:53:31','2024-10-21 16:53:31',0),(4265771314790400,337736198864896,'区域服务管理','foundations:serve',0,'',0,0,'2024-10-24 17:29:16','2024-10-24 17:29:16',0),(4266534841696256,4265771314790400,'批量新增区域服务','foundations:serve:add',0,'',0,0,'2024-10-24 17:32:18','2024-10-24 17:32:18',0),(4266534841696257,4265771314790400,'区域服务删除','foundations:serve:delete',0,'',0,0,'2024-10-24 17:32:18','2024-10-24 17:32:18',0),(4266534841696258,4265771314790400,'区域服务上架','foundations:serve:onSale',0,'',0,0,'2024-10-24 17:32:18','2024-10-24 17:32:18',0),(4266534841696259,4265771314790400,'区域服务设置热门','foundations:serve:onHot',0,'',0,0,'2024-10-24 17:32:18','2024-10-24 17:32:18',0),(4266534841696260,4265771314790400,'区域服务价格修改','foundations:serve:update',0,'',0,0,'2024-10-24 17:32:18','2024-10-24 17:32:18',0),(4266534841696261,4265771314790400,'区域服务分页查询','foundations:serve:page',0,'',0,0,'2024-10-24 17:32:18','2024-10-24 17:32:18',0),(4266534841696262,4265771314790400,'区域服务取消热门','foundations:serve:offHot',0,'',0,0,'2024-10-24 17:32:18','2024-10-24 17:32:18',0),(4266534845890560,4265771314790400,'区域服务下架','foundations:serve:offSale',0,'',0,0,'2024-10-24 17:32:18','2024-10-24 17:32:18',0),(10766644855185408,0,'客户中心服务','consumer',0,'',0,0,'2024-11-11 16:01:25','2024-11-11 16:01:25',0),(10767315721527296,10766644855185408,'区域服务管理','consumer:serve',0,'',0,0,'2024-11-11 16:04:05','2024-11-11 16:04:05',0),(10768125473857536,10767315721527296,'获取首页服务列表','consumer:serve:category',0,'',0,0,'2024-11-11 16:07:18','2024-11-11 16:07:18',0),(10768505351970816,10766644855185408,'区域服务管理','consumer:region',0,'',0,0,'2024-11-11 16:08:49','2024-11-11 16:08:49',0),(10769316840742912,10768505351970816,'已开通服务区域列表','consumer:region:active',0,'',0,0,'2024-11-11 16:12:02','2024-11-11 16:12:02',0),(10772346994696192,10766644855185408,'地址薄管理','consumer:addressBook',0,'',0,0,'2024-11-11 16:24:05','2024-11-11 16:24:05',0),(10774079527460864,10772346994696192,'地址薄分页查询','consumer:addressBook:page',0,'',0,0,'2024-11-11 16:30:58','2024-11-11 16:30:58',0),(10774079527460865,10772346994696192,'批量删除地址薄','consumer:addressBook:delete',0,'',0,0,'2024-11-11 16:30:58','2024-11-11 16:30:58',0),(10774079527460866,10772346994696192,'地址薄新增','consumer:addressBook:add',0,'',0,0,'2024-11-11 16:30:58','2024-11-11 16:30:58',0),(10774079527460867,10772346994696192,'地址薄设为默认/取消默认','consumer:addressBook:updateDefault',0,'',0,0,'2024-11-11 16:30:58','2024-11-11 16:30:58',0),(10774079527460868,10772346994696192,'获取默认地址','consumer:addressBook:default',0,'',0,0,'2024-11-11 16:30:58','2024-11-11 16:30:58',0),(10774079531655168,10772346994696192,'地址薄详情','consumer:addressBook:detail',0,'',0,0,'2024-11-11 16:30:58','2024-11-11 16:30:58',0),(10774079531655169,10772346994696192,'修改地址薄','consumer:addressBook:update',0,'',0,0,'2024-11-11 16:30:58','2024-11-11 16:30:58',0),(12378559381512192,10767315721527296,'首页热门服务列表','consumer:serve:hot',0,'',0,0,'2024-11-16 02:46:37','2024-11-16 02:46:37',0),(16863355092606976,337736198864896,'服务人员认证审核管理','foundations:workerCertification',0,'',0,0,'2024-11-28 11:47:35','2024-11-28 11:47:35',0),(16863632625508352,16863355092606976,'审核服务人员认证信息','foundations:workerCertification:audit',0,'',0,0,'2024-11-28 11:48:41','2024-11-28 11:48:41',0),(16863632625508353,16863355092606976,'服务人员认证审核信息分页查询','foundations:workerCertification:page',0,'',0,0,'2024-11-28 11:48:41','2024-11-28 11:48:41',0),(16864175603326976,337736198864896,'机构认证审核管理','foundations:agencyCertification',0,'',0,0,'2024-11-28 11:50:50','2024-11-28 11:50:50',0),(16864395883978752,16864175603326976,'机构认证审核信息分页查询','foundations:agencyCertification:page',0,'',0,0,'2024-11-28 11:51:43','2024-11-28 11:51:43',0),(16864395883978753,16864175603326976,'审核机构认证信息','foundations:agencyCertification:audit',0,'',0,0,'2024-11-28 11:51:43','2024-11-28 11:51:43',0),(16867212086484992,0,'服务端','worker',0,'',0,0,'2024-11-28 12:02:54','2024-11-28 12:02:54',0),(16867661116088320,16867212086484992,'银行账户信息相关接口','worker:bankAccount',0,'',0,0,'2024-11-28 12:04:41','2024-11-28 12:04:41',0),(16868186574299136,16867661116088320,'获取当前用银行账号信息','worker:bankAccount:get',0,'',0,0,'2024-11-28 12:06:47','2024-11-28 12:06:47',0),(16868186574299137,16867661116088320,'银行账户信息相关接口','worker:bankAccount:upsert',0,'',0,0,'2024-11-28 12:06:46','2024-11-28 12:06:46',0),(16869597085179904,16867212086484992,'服务人员认证审核相关接口','worker:certification',0,'',0,0,'2024-11-28 12:12:23','2024-11-28 12:12:23',0),(16870132534222848,16869597085179904,'查询最新的驳回原因','worker:certification:reason',0,'',0,0,'2024-11-28 12:14:30','2024-11-28 12:14:30',0),(16870132534222849,16869597085179904,'服务人员提交认证申请','worker:certification:apply',0,'',0,0,'2024-11-28 12:14:30','2024-11-28 12:14:30',0),(16871164697911296,16867212086484992,'服务设置相关接口','worker:serveSettings',0,'',0,0,'2024-11-28 12:18:37','2024-11-28 12:18:37',0),(16871657419579392,16871164697911296,'获取服务范围设置','worker:serveSettings:getServeScope',0,'',0,0,'2024-11-28 12:20:34','2024-11-28 12:20:34',0),(16871657419579393,16871164697911296,'设置服务范围','worker:serveSettings:setServeScope',0,'',0,0,'2024-11-28 12:20:34','2024-11-28 12:20:34',0),(16871657419579394,16871164697911296,'获取所有设置状态','worker:serveSettings:getStatus',0,'',0,0,'2024-11-28 12:20:34','2024-11-28 12:20:34',0),(16871657419579395,16871164697911296,'接单设置','worker:serveSettings:setPickUp',0,'',0,0,'2024-11-28 12:20:34','2024-11-28 12:20:34',0),(16873213883850752,16867212086484992,'服务技能相关接口','worker:serveSkill',0,'',0,0,'2024-11-28 12:26:45','2024-11-28 12:26:45',0),(16874004073947136,16873213883850752,'查询服务技能目录','worker:serveSkill:category',0,'',0,0,'2024-11-28 12:29:54','2024-11-28 12:29:54',0),(16874004073947137,16873213883850752,'批量新增或修改服务技能','worker:serveSkill:batchUpsert',0,'',0,0,'2024-11-28 12:29:53','2024-11-28 12:29:53',0),(16875259542380544,0,'机构端','agency',0,'',0,0,'2024-11-28 12:34:53','2024-11-28 12:34:53',0),(16875456733388800,16875259542380544,'银行账户信息相关接口','agency:bankAccount',0,'',0,0,'2024-11-28 12:35:40','2024-11-28 12:35:40',0),(16875785285804032,16875456733388800,'获取当前用银行账号信息','agency:bankAccount:get',0,'',0,0,'2024-11-28 12:36:58','2024-11-28 12:36:58',0),(16875785285804033,16875456733388800,'新增或更新银行账号信息','agency:bankAccount:upsert',0,'',0,0,'2024-11-28 12:36:58','2024-11-28 12:36:58',0),(16876936341233664,16875259542380544,'服务人员认证审核相关接口','agency:certification',0,'',0,0,'2024-11-28 12:41:33','2024-11-28 12:41:33',0),(16877212729090048,16876936341233664,'机构端查询最新的驳回原因','agency:certification:reason',0,'',0,0,'2024-11-28 12:42:38','2024-11-28 12:42:38',0),(16877212729090049,16876936341233664,'机构端提交认证申请','agency:certification:apply',0,'',0,0,'2024-11-28 12:42:38','2024-11-28 12:42:38',0),(16877566883536896,16875259542380544,'服务设置相关接口','agency:serveSettings',0,'',0,0,'2024-11-28 12:44:03','2024-11-28 12:44:03',0),(16878063547850752,16877566883536896,'获取所有设置状态','agency:serveSettings:getStatus',0,'',0,0,'2024-11-28 12:46:01','2024-11-28 12:46:01',0),(16878063547850753,16877566883536896,'接单设置','agency:serveSettings:setPickUp',0,'',0,0,'2024-11-28 12:46:01','2024-11-28 12:46:01',0),(16878063547850754,16877566883536896,'服务范围设置','agency:serveSettings:setServeScope',0,'',0,0,'2024-11-28 12:46:01','2024-11-28 12:46:01',0),(16878063547850755,16877566883536896,'获取服务范围设置','agency:serveSettings:getServeScope',0,'',0,0,'2024-11-28 12:46:01','2024-11-28 12:46:01',0),(16878711639121920,16875259542380544,'服务技能相关接口','agency:serveSkill',0,'',0,0,'2024-11-28 12:48:36','2024-11-28 12:48:36',0),(16879101742948352,16878711639121920,'查询服务技能目录','agency:serveSkill:category',0,'',0,0,'2024-11-28 12:50:09','2024-11-28 12:50:09',0),(16879101742948353,16878711639121920,'批量新增或修改服务技能','agency:serveSkill:batchUpsert',0,'',0,0,'2024-11-28 12:50:09','2024-11-28 12:50:09',0),(17787553004658688,10766644855185408,'服务类型相关接口','consumer:serveType',0,'',0,0,'2024-12-01 01:00:01','2024-12-01 01:00:01',0),(17787711343828992,17787553004658688,'查询已开通的服务类型','consumer:serveType:get',0,'',0,0,'2024-12-01 01:00:39','2024-12-01 01:00:39',0),(18040200005689344,10767315721527296,'查询服务详情','consumer:serve:detail',0,'',0,0,'2024-12-01 17:43:57','2024-12-01 17:43:57',0),(25663895880605696,10767315721527296,'服务搜索','consumer:serve:search',0,'',0,0,'2024-12-22 18:37:48','2024-12-22 18:38:14',0),(26292939730984960,10766644855185408,'订单相关接口','consumer:orders',0,'',0,0,'2024-12-24 12:17:23','2024-12-24 12:17:23',0),(26293232594067456,26292939730984960,'下单','consumer:orders:place',0,'',0,0,'2024-12-24 12:18:33','2024-12-24 12:18:33',0),(34774739465805824,26292939730984960,'订单支付','consumer:orders:pay',0,'',0,0,'2025-01-16 22:00:58','2025-01-16 22:00:58',0),(42797359314509824,26292939730984960,'查询订单支付结果','consumer:orders:payResult',0,'',0,0,'2025-02-08 01:20:04','2025-03-02 17:23:37',0),(51007447258898432,26292939730984960,'订单查询','consumer:orders:list',0,'',0,0,'2025-03-02 17:04:01','2025-03-02 17:04:01',0),(51113766368321536,26292939730984960,'根据id查询订单详细信息','consumer:orders:detail',0,'',0,0,'2025-03-03 00:06:30','2025-03-03 00:06:30',0),(51443850987970560,26292939730984960,'取消订单','consumer:orders:cancel',0,'',0,0,'2025-03-03 21:58:08','2025-03-03 21:58:08',0),(53324284332089344,337736198864896,'优惠券活动管理','foundations:activity',0,'',0,0,'2025-03-09 02:30:18','2025-03-09 02:30:18',0),(53324728169144320,53324284332089344,'保存优惠券活动','foundations:activity:save',0,'',0,0,'2025-03-09 02:32:03','2025-03-09 02:32:03',0),(53345529710780416,53324284332089344,'分页查询优惠券活动','foundations:activity:page',0,'',0,0,'2025-03-09 03:54:44','2025-04-27 18:38:54',0),(53350895425167360,53324284332089344,'查询优惠券活动详情','foundations:activity:detail',0,'',0,0,'2025-03-09 04:16:03','2025-03-09 04:16:03',0),(53486838413008896,53324284332089344,'撤销优惠券活动','foundations:activity:revoke',0,'',0,0,'2025-03-09 13:16:14','2025-03-09 13:16:14',0),(53520249987014656,337736198864896,'优惠券管理','foundations:coupon',0,'',0,0,'2025-03-09 15:28:59','2025-03-09 15:28:59',0),(53520521710804992,53520249987014656,'查询活动优惠券领取记录','foundations:coupon:page',0,'',0,0,'2025-03-09 15:30:04','2025-03-09 15:30:04',0),(53532426647117824,10766644855185408,'优惠券相管理','consumer:coupon',0,'',0,0,'2025-03-09 16:17:22','2025-03-09 16:17:22',0),(53532854214467584,53532426647117824,'我的优惠券列表','consumer:coupon:page',0,'',0,0,'2025-03-09 16:19:04','2025-03-09 16:19:04',0),(53891256954396672,10766644855185408,'优惠券活动','consumer:activity',0,'',0,0,'2025-03-10 16:03:14','2025-03-10 16:03:14',0),(53891486919696384,53891256954396672,'查询用户端抢券列表','consumer:activity:list',0,'',0,0,'2025-03-10 16:04:09','2025-03-10 16:04:09',0),(54035070406893568,53532426647117824,'抢券','consumer:coupon:grab',0,'',0,0,'2025-03-11 01:34:42','2025-03-11 01:34:42',0),(54659828911710208,26292939730984960,'获取可用优惠券','consumer:orders:availableCoupon',0,'',0,0,'2025-03-12 18:57:18','2025-03-12 18:57:18',0),(70231489505402880,0,'提供者端','provider',0,'',0,0,'2025-04-24 18:13:31','2025-04-24 18:13:31',0),(70231767117996032,70231489505402880,'抢单管理','provider:ordersGarb',0,'',0,0,'2025-04-24 18:14:39','2025-04-24 18:14:39',0),(70231988669521920,70231767117996032,'查询服务端抢单列表','provider:ordersGarb:list',0,'',0,0,'2025-04-24 18:15:30','2025-04-24 18:15:30',0),(70885212695044096,70231767117996032,'抢单','provider:ordersGarb:grab',0,'',0,0,'2025-04-26 13:31:11','2025-04-26 13:31:11',0);
/*!40000 ALTER TABLE `edj_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_role`
--

DROP TABLE IF EXISTS `edj_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_role` (
  `id` bigint unsigned NOT NULL COMMENT '角色ID',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_order` int NOT NULL DEFAULT '1' COMMENT '显示顺序',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_role`
--

LOCK TABLES `edj_role` WRITE;
/*!40000 ALTER TABLE `edj_role` DISABLE KEYS */;
INSERT INTO `edj_role` VALUES (1,'admin',0,0,'admin',0,0,'2024-10-09 10:57:39','2024-10-09 10:57:39',0),(2,'consumer',1,0,'consumer 客户端用户基础角色',0,0,'2024-10-30 21:37:00','2024-10-30 22:25:04',0),(3,'worker',2,0,'worker 服务端用户基础权限',0,0,'2024-11-01 15:23:44','2024-11-01 15:23:44',0),(4,'institution',4,0,'institution 机构端用户基础权限',0,0,'2024-11-01 15:23:44','2024-11-01 15:23:44',0);
/*!40000 ALTER TABLE `edj_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_role_authority`
--

DROP TABLE IF EXISTS `edj_role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_role_authority` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `edj_role_id` bigint unsigned NOT NULL COMMENT '角色ID',
  `edj_authority_id` bigint unsigned NOT NULL COMMENT '权限ID',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `edj_role_authority_edj_role_id_edj_authority_id_uindex` (`edj_role_id`,`edj_authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=620389972132027 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_role_authority`
--

LOCK TABLES `edj_role_authority` WRITE;
/*!40000 ALTER TABLE `edj_role_authority` DISABLE KEYS */;
INSERT INTO `edj_role_authority` VALUES (1,1,337736198864896,0),(2,1,338323032326144,0),(3,1,338523956264960,0),(4,1,621079163387904,0),(620389972131842,1,724530186764288,0),(620389972131843,1,724616602009600,0),(620389972131844,1,724750459027456,0),(620389972131845,1,724935155204096,0),(620389972131846,1,1083983155249152,0),(620389972131847,1,1084430179975168,0),(620389972131848,1,1085036588253186,0),(620389972131849,1,1085036588253184,0),(620389972131850,1,1085036588253189,0),(620389972131851,1,1085036588253185,0),(620389972131852,1,1085036588253190,0),(620389972131853,1,1085036588253187,0),(620389972131854,1,1085036588253188,0),(620389972131855,1,3168590889566208,0),(620389972131856,1,3169606045351938,0),(620389972131857,1,3169606045351942,0),(620389972131858,1,3169606045351937,0),(620389972131859,1,3169606045351936,0),(620389972131860,1,3169606045351941,0),(620389972131861,1,3169606045351939,0),(620389972131862,1,3169606045351940,0),(620389972131863,1,4265771314790400,0),(620389972131864,1,4266534841696260,0),(620389972131865,1,4266534841696257,0),(620389972131866,1,4266534841696256,0),(620389972131867,1,4266534841696262,0),(620389972131868,1,4266534841696259,0),(620389972131869,1,4266534841696258,0),(620389972131870,1,4266534845890560,0),(620389972131871,1,4266534841696261,0),(620389972131876,1,10766644855185408,0),(620389972131877,2,10766644855185408,0),(620389972131878,2,10767315721527296,0),(620389972131879,1,10767315721527296,0),(620389972131880,1,10767768421146624,0),(620389972131881,2,10767768421146624,0),(620389972131882,1,10768125473857536,0),(620389972131883,2,10768125473857536,0),(620389972131884,1,10768505351970816,0),(620389972131885,2,10768505351970816,0),(620389972131886,2,10769316840742912,0),(620389972131887,1,10769316840742912,0),(620389972131888,2,10772346994696192,0),(620389972131889,1,10772346994696192,0),(620389972131890,2,10774079527460866,0),(620389972131891,1,10774079527460866,0),(620389972131892,1,10774079531655169,0),(620389972131893,2,10774079531655169,0),(620389972131894,2,10774079531655168,0),(620389972131895,1,10774079531655168,0),(620389972131896,1,10774079527460868,0),(620389972131897,2,10774079527460868,0),(620389972131898,2,10774079527460864,0),(620389972131899,1,10774079527460864,0),(620389972131900,2,10774079527460865,0),(620389972131901,1,10774079527460865,0),(620389972131902,2,10774079527460867,0),(620389972131903,1,10774079527460867,0),(620389972131904,1,12378559381512192,0),(620389972131905,2,12378559381512192,0),(620389972131906,3,10769316840742912,0),(620389972131907,4,10769316840742912,0),(620389972131908,1,16863355092606976,0),(620389972131909,1,16863632625508353,0),(620389972131910,1,16863632625508352,0),(620389972131911,1,16864175603326976,0),(620389972131912,1,16864395883978752,0),(620389972131913,1,16864395883978753,0),(620389972131914,1,16867212086484992,0),(620389972131915,3,16867212086484992,0),(620389972131916,1,16867661116088320,0),(620389972131917,3,16867661116088320,0),(620389972131918,3,16868186574299137,0),(620389972131919,1,16868186574299137,0),(620389972131920,1,16868186574299136,0),(620389972131921,3,16868186574299136,0),(620389972131922,1,16869597085179904,0),(620389972131923,3,16869597085179904,0),(620389972131924,1,16870132534222849,0),(620389972131925,3,16870132534222849,0),(620389972131926,3,16870132534222848,0),(620389972131927,1,16870132534222848,0),(620389972131928,1,16871164697911296,0),(620389972131929,3,16871164697911296,0),(620389972131930,3,16871657419579393,0),(620389972131931,1,16871657419579393,0),(620389972131932,1,16871657419579392,0),(620389972131933,3,16871657419579392,0),(620389972131934,3,16871657419579394,0),(620389972131935,1,16871657419579394,0),(620389972131936,3,16871657419579395,0),(620389972131937,1,16871657419579395,0),(620389972131938,1,16873213883850752,0),(620389972131939,3,16873213883850752,0),(620389972131940,1,16874004073947137,0),(620389972131941,3,16874004073947137,0),(620389972131942,3,16874004073947136,0),(620389972131943,1,16874004073947136,0),(620389972131944,1,16875259542380544,0),(620389972131945,4,16875259542380544,0),(620389972131946,1,16875456733388800,0),(620389972131947,4,16875456733388800,0),(620389972131948,1,16875785285804033,0),(620389972131949,4,16875785285804033,0),(620389972131950,4,16875785285804032,0),(620389972131951,1,16875785285804032,0),(620389972131952,1,16876936341233664,0),(620389972131953,4,16876936341233664,0),(620389972131954,4,16877212729090049,0),(620389972131955,1,16877212729090049,0),(620389972131956,1,16877212729090048,0),(620389972131957,4,16877212729090048,0),(620389972131958,1,16877566883536896,0),(620389972131959,4,16877566883536896,0),(620389972131960,1,16878063547850755,0),(620389972131961,4,16878063547850755,0),(620389972131962,4,16878063547850752,0),(620389972131963,1,16878063547850752,0),(620389972131964,4,16878063547850753,0),(620389972131965,1,16878063547850753,0),(620389972131966,1,16878063547850754,0),(620389972131967,4,16878063547850754,0),(620389972131968,1,16878711639121920,0),(620389972131969,4,16878711639121920,0),(620389972131970,4,16879101742948353,0),(620389972131971,1,16879101742948353,0),(620389972131972,4,16879101742948352,0),(620389972131973,1,16879101742948352,0),(620389972131974,2,17787553004658688,0),(620389972131975,1,17787553004658688,0),(620389972131976,1,17787711343828992,0),(620389972131977,2,17787711343828992,0),(620389972131978,1,18040200005689344,0),(620389972131979,2,18040200005689344,0),(620389972131980,1,25663895880605696,0),(620389972131981,2,25663895880605696,0),(620389972131982,1,26292939730984960,0),(620389972131983,2,26292939730984960,0),(620389972131984,1,26293232594067456,0),(620389972131985,2,26293232594067456,0),(620389972131986,1,34774739465805824,0),(620389972131987,2,34774739465805824,0),(620389972131988,2,42797359314509824,0),(620389972131989,1,42797359314509824,0),(620389972131990,1,51007447258898432,0),(620389972131991,2,51007447258898432,0),(620389972131992,2,51113766368321536,0),(620389972131993,1,51113766368321536,0),(620389972131994,1,51443850987970560,0),(620389972131995,2,51443850987970560,0),(620389972131996,1,53324284332089344,0),(620389972131997,1,53324728169144320,0),(620389972131998,1,53345529710780416,0),(620389972131999,1,53350895425167360,0),(620389972132000,1,53486838413008896,0),(620389972132001,1,53520249987014656,0),(620389972132002,1,53520521710804992,0),(620389972132003,1,53532426647117824,0),(620389972132004,2,53532426647117824,0),(620389972132005,1,53532854214467584,0),(620389972132006,2,53532854214467584,0),(620389972132007,1,53891256954396672,0),(620389972132008,2,53891256954396672,0),(620389972132009,1,53891486919696384,0),(620389972132010,2,53891486919696384,0),(620389972132011,2,54035070406893568,0),(620389972132012,1,54035070406893568,0),(620389972132013,1,54659828911710208,0),(620389972132014,2,54659828911710208,0),(620389972132015,1,70231489505402880,0),(620389972132016,4,70231489505402880,0),(620389972132017,3,70231489505402880,0),(620389972132018,1,70231767117996032,0),(620389972132019,4,70231767117996032,0),(620389972132020,3,70231767117996032,0),(620389972132021,4,70231988669521920,0),(620389972132022,1,70231988669521920,0),(620389972132023,3,70231988669521920,0),(620389972132024,4,70885212695044096,0),(620389972132025,1,70885212695044096,0),(620389972132026,3,70885212695044096,0);
/*!40000 ALTER TABLE `edj_role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_user`
--

DROP TABLE IF EXISTS `edj_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_user` (
  `id` bigint unsigned NOT NULL COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户账号',
  `nickname` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名称',
  `open_id` varchar(128) NOT NULL DEFAULT '' COMMENT '微信用户凭证',
  `email` varchar(64) NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `phone_number` char(11) NOT NULL DEFAULT '' COMMENT '用户手机号码',
  `avatar` varchar(128) NOT NULL DEFAULT '' COMMENT '用户头像',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '密码',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '账号状态（0正常 1冻结）',
  `login_ip` varchar(128) NOT NULL DEFAULT '' COMMENT '最后登录IP',
  `login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `create_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `update_by` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_user_username_index` (`username`),
  KEY `edj_user_open_id_index` (`open_id`),
  KEY `edj_user_phone_number_index` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_user`
--

LOCK TABLES `edj_user` WRITE;
/*!40000 ALTER TABLE `edj_user` DISABLE KEYS */;
INSERT INTO `edj_user` VALUES (1,'admin','admin','','shiyx325@163.com','','https://yjy-oss-videos.oss-accelerate.aliyuncs.com/tx.png','$2a$10$WmkLbetTwcEHDjX.NRpZUu11slG2jKtrxMsLXhNvA8PUpoB/jryzW',0,'0:0:0:0:0:0:0:1','2025-05-16 22:10:40','',0,0,'2024-10-09 09:21:40','2025-05-16 22:10:44',0),(8288489957834752,'EDJ_8288489542598656','EDJ_8288489542598656','oLexy7ZRawjq2D4POGTIi31bGf_Y','','','https://yjy-oss-videos.oss-accelerate.aliyuncs.com/tx.png','$2a$10$UAfVS2eH.Kuq.7Pge.78X.j75/UZQG/a/zqXurnupaViSTND7iW82',0,'0:0:0:0:0:0:0:1','2025-05-16 22:40:45','',0,0,'2024-11-04 19:54:07','2025-05-16 22:40:50',0),(11148624847446016,'谈全浩','谈全浩','','','19330439536','','$2a$10$Ze.mWZuYue2fGWFziHK1su83o/0pTfMw0mDMqxBrTpyFAm/O4n3eu',0,'127.0.0.1','2025-05-16 22:27:15','',0,0,'2024-11-12 17:19:15','2025-05-16 22:27:20',0),(14078868029972480,'网易（杭州）网络有限公司','网易（杭州）网络有限公司','','','19875447806','','$2a$10$QTQ.3NOJWT1u5nK4BNpGGeqVL7dRP4KGBDrFzGaywiLas9VzkQBRu',0,'0:0:0:0:0:0:0:1','2025-05-16 22:10:27','',0,0,'2024-11-20 19:23:01','2025-05-16 22:10:32',0),(70266500728233984,'戴佳伟','戴佳伟','','','16888888888','','$2a$10$rDIwrJQpP0ALqqQciXMr3evgnur4K6zvFVnGfceuKaOZxwO8XgDiK',0,'127.0.0.1','2025-04-25 21:41:16','',0,0,'2025-04-24 20:32:38','2025-04-25 21:42:56',0),(73147336041439232,'桂林天算网络科技有限公司','桂林天算网络科技有限公司','','','13666666666','','$2a$10$s9KGY2wRpsr81OeTheNBm.tHkUOC7FLj7JHKa917p2/hEfD.35Swi',0,'127.0.0.1','2025-05-02 19:37:24','',0,0,'2025-05-02 19:20:01','2025-05-02 19:37:23',0),(73151942012182528,'李琼','李琼','','','13555555555','','$2a$10$JLDQJkLxnT/Jpne0HqGwmu9kyVa7xG34qvToklrn8ffBz.LwGAd5e',0,'127.0.0.1','2025-05-02 19:41:32','',0,0,'2025-05-02 19:38:19','2025-05-02 19:48:18',0);
/*!40000 ALTER TABLE `edj_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edj_user_role`
--

DROP TABLE IF EXISTS `edj_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edj_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `edj_user_id` bigint unsigned NOT NULL COMMENT '用户ID',
  `edj_role_id` bigint unsigned NOT NULL COMMENT '角色ID',
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  PRIMARY KEY (`id`),
  KEY `edj_user_role_edj_user_id_edj_role_id_is_delete_index` (`edj_user_id`,`edj_role_id`,`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edj_user_role`
--

LOCK TABLES `edj_user_role` WRITE;
/*!40000 ALTER TABLE `edj_user_role` DISABLE KEYS */;
INSERT INTO `edj_user_role` VALUES (1,1,1,0),(7,8288489957834752,2,0),(8,10809135629344768,3,0),(9,10809488684883968,3,0),(10,11130585892528128,3,0),(11,11133448618254336,3,0),(12,11134463702736896,3,0),(13,11147342074093568,3,0),(14,11148624847446016,3,0),(15,11217416013225984,3,0),(16,13676152107573248,4,0),(17,14008578109349888,4,0),(22,14026999601569792,3,0),(23,14078868029972480,4,0),(24,70266500728233984,3,0),(25,73147336041439232,4,0),(26,73151942012182528,3,0);
/*!40000 ALTER TABLE `edj_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT transaction mode undo table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `xxl_job`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `xxl_job` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `xxl_job`;

--
-- Table structure for table `xxl_job_group`
--

DROP TABLE IF EXISTS `xxl_job_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `address_type` tinyint NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_group`
--

LOCK TABLES `xxl_job_group` WRITE;
/*!40000 ALTER TABLE `xxl_job_group` DISABLE KEYS */;
INSERT INTO `xxl_job_group` VALUES (2,'edj-foundations','白鹭到家-运营基础服务',1,'http://172.30.224.1:33531/','2025-05-16 23:06:36'),(3,'edj-orders-manager','白鹭到家-订单管理服务',1,'http://172.30.224.1:33551/','2025-05-16 23:07:40'),(4,'edj-trade','白鹭到家-支付服务',1,'http://172.30.224.1:33561/','2025-05-16 23:07:49'),(5,'edj-market','白鹭到家-优惠券服务',1,'http://172.30.224.1:33571/','2025-05-16 23:07:17'),(6,'edj-orders-grab','白鹭到家-抢单服务',1,'http://172.30.224.1:33581/','2025-05-16 23:07:27');
/*!40000 ALTER TABLE `xxl_job_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_info`
--

DROP TABLE IF EXISTS `xxl_job_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_info`
--

LOCK TABLES `xxl_job_info` WRITE;
/*!40000 ALTER TABLE `xxl_job_info` DISABLE KEYS */;
INSERT INTO `xxl_job_info` VALUES (2,2,'定时更新已开通服务区域列表','2024-11-29 00:40:44','2024-12-01 03:07:55','A.E.','shiyx325@163.com','CRON','0 0 3 * * ?','FIRE_ONCE_NOW','ROUND','ActiveRegionCacheSync','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2024-11-29 00:40:44','',1,1747508400000,1750791600000),(3,2,'定时更新首页服务列表缓存','2024-12-01 00:18:50','2024-12-01 03:07:43','A.E.','shiyx325@163.com','CRON','0 0 3 * * ?','FIRE_ONCE_NOW','ROUND','HomeCategoryCacheSync','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2024-12-01 00:18:50','',1,1747508400000,1750791600000),(4,2,'定时更新首页服务类型列表缓存','2024-12-01 02:29:54','2024-12-01 03:07:37','A.E.','shiyx325@163.com','CRON','0 0 3 * * ?','FIRE_ONCE_NOW','ROUND','HomeServeTypeCache','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2024-12-01 02:29:54','',1,1747508400000,1750791600000),(5,2,'定时更新首页区域热门服务缓存','2024-12-01 03:01:17','2024-12-01 03:07:30','A.E.','shiyx325@163.com','CRON','0 0 3 * * ?','FIRE_ONCE_NOW','ROUND','HomeHotServeCache','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2024-12-01 03:01:17','',1,1747508400000,1750791600000),(6,3,'取消支付超时订单','2025-03-04 00:03:45','2025-04-22 21:26:55','A.E.','shiyx325@163.com','CRON','0 * * * * ?','DO_NOTHING','RANDOM','CancelOverTimePayOrder','','DISCARD_LATER',0,0,'BEAN','','GLUE代码初始化','2025-03-04 00:03:45','',1,1750770780000,1750770840000),(7,3,'处理订单退款','2025-03-05 04:01:39','2025-03-05 13:26:47','A.E.','shiyx325@163.com','CRON','0 0/10 * * * ?','DO_NOTHING','RANDOM','HandleRefundOrders','','COVER_EARLY',0,0,'BEAN','','GLUE代码初始化','2025-03-05 04:01:39','',1,1747447800000,1750771200000),(8,4,'处理支付状态','2025-03-07 16:21:00','2025-03-08 03:16:21','A.E.','shiyx325@163.com','CRON','0 * * * * ?','DO_NOTHING','SHARDING_BROADCAST','TradingStateHandle','','DISCARD_LATER',0,0,'BEAN','','GLUE代码初始化','2025-03-07 16:21:00','',1,1750770780000,1750770840000),(9,5,'优惠券活动状态自动更新','2025-03-09 14:34:47','2025-03-11 01:39:33','A.E.','shiyx325@163.com','CRON','10 * * * * ?','DO_NOTHING','RANDOM','UpdateActivityStatus','','DISCARD_LATER',0,0,'BEAN','','GLUE代码初始化','2025-03-09 14:34:47','',1,1750770790000,1750770850000),(10,5,'优惠券活动预热','2025-03-10 13:59:57','2025-03-11 01:39:30','A.E.','shiyx325@163.com','CRON','20 * * * * ?','DO_NOTHING','RANDOM','ActivityPerHeat','','DISCARD_LATER',0,0,'BEAN','','GLUE代码初始化','2025-03-10 13:59:57','',1,1750770800000,1750770860000),(11,5,'优惠券库存预热','2025-03-10 22:00:28','2025-03-11 01:39:27','A.E.','shiyx325@163.com','CRON','30 * * * * ?','DO_NOTHING','RANDOM','ActivityStockPerHeat','','DISCARD_LATER',0,0,'BEAN','','GLUE代码初始化','2025-03-10 22:00:28','',1,1750770810000,1750770870000),(12,5,'同步抢券结果','2025-03-11 20:18:22','2025-03-11 20:48:12','A.E.','shiyx325@163.com','CRON','0/10 * * * * ?','DO_NOTHING','RANDOM','GrabCouponSync','','DISCARD_LATER',0,0,'BEAN','','GLUE代码初始化','2025-03-11 20:18:22','',1,1750770820000,1750770830000),(13,5,'优惠券活动已结束清理缓存','2025-03-12 14:27:46','2025-03-12 14:28:19','A.E.','shiyx325@163.com','CRON','0 0 2 * * ?','FIRE_ONCE_NOW','RANDOM','CleanActivityCache','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2025-03-12 14:27:46','',1,1747504800000,1750788000000),(14,6,'优惠券活动已结束清理缓存','2025-04-26 19:28:39','2025-04-26 22:11:15','A.E.','shiyx325@163.com','CRON','0/10 * * * * ?','DO_NOTHING','RANDOM','GrabOrdersSync','','DISCARD_LATER',0,0,'BEAN','','GLUE代码初始化','2025-04-26 19:28:39','',1,1750770820000,1750770830000);
/*!40000 ALTER TABLE `xxl_job_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_lock`
--

DROP TABLE IF EXISTS `xxl_job_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_lock`
--

LOCK TABLES `xxl_job_lock` WRITE;
/*!40000 ALTER TABLE `xxl_job_lock` DISABLE KEYS */;
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');
/*!40000 ALTER TABLE `xxl_job_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_log`
--

DROP TABLE IF EXISTS `xxl_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB AUTO_INCREMENT=120499 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_log`
--

LOCK TABLES `xxl_job_log` WRITE;
/*!40000 ALTER TABLE `xxl_job_log` DISABLE KEYS */;
INSERT INTO `xxl_job_log` VALUES (120469,2,3,'http://172.30.224.1:33531/','HomeCategoryCacheSync','',NULL,0,'2025-06-24 21:12:19',500,'任务触发类型：调度过期补偿<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33531/]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33531/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33531/run',NULL,0,NULL,0),(120470,2,4,'http://172.30.224.1:33531/','HomeServeTypeCache','',NULL,0,'2025-06-24 21:12:19',500,'任务触发类型：调度过期补偿<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33531/]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33531/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33531/run',NULL,0,NULL,0),(120471,5,13,'http://172.30.224.1:33571/','CleanActivityCache','',NULL,0,'2025-06-24 21:12:20',500,'任务触发类型：调度过期补偿<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120472,2,2,'http://172.30.224.1:33531/','ActiveRegionCacheSync','',NULL,0,'2025-06-24 21:12:20',500,'任务触发类型：调度过期补偿<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33531/]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33531/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33531/run',NULL,0,NULL,0),(120473,2,5,'http://172.30.224.1:33531/','HomeHotServeCache','',NULL,0,'2025-06-24 21:12:20',500,'任务触发类型：调度过期补偿<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33531/]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33531/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33531/run',NULL,0,NULL,0),(120474,5,10,'http://172.30.224.1:33571/','ActivityPerHeat','',NULL,0,'2025-06-24 21:12:23',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120475,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:12:23',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120476,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:12:24',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120477,5,11,'http://172.30.224.1:33571/','ActivityStockPerHeat','',NULL,0,'2025-06-24 21:12:30',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120478,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:12:30',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120479,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:12:30',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120480,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:12:40',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120481,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:12:40',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120482,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:12:50',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120483,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:12:50',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120484,3,6,'http://172.30.224.1:33551/','CancelOverTimePayOrder','',NULL,0,'2025-06-24 21:13:00',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33551/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33551/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33551/run',NULL,0,NULL,0),(120485,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:13:00',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120486,4,8,'http://172.30.224.1:33561/','TradingStateHandle','','0/1',0,'2025-06-24 21:13:00',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33561/]<br>路由策略：分片广播(0/1)<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33561/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33561/run',NULL,0,NULL,0),(120487,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:13:00',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120488,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:13:10',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120489,5,9,'http://172.30.224.1:33571/','UpdateActivityStatus','',NULL,0,'2025-06-24 21:13:10',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120490,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:13:10',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120491,5,10,'http://172.30.224.1:33571/','ActivityPerHeat','',NULL,0,'2025-06-24 21:13:20',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120492,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:13:20',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120493,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:13:20',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120494,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:13:30',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120495,5,11,'http://172.30.224.1:33571/','ActivityStockPerHeat','',NULL,0,'2025-06-24 21:13:30',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120496,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:13:30',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0),(120497,6,14,'http://172.30.224.1:33581/','GrabOrdersSync','',NULL,0,'2025-06-24 21:13:40',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33581/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33581/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33581/run',NULL,0,NULL,0),(120498,5,12,'http://172.30.224.1:33571/','GrabCouponSync','',NULL,0,'2025-06-24 21:13:40',500,'任务触发类型：Cron触发<br>调度机器：172.18.0.2<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://172.30.224.1:33571/]<br>路由策略：随机<br>阻塞处理策略：丢弃后续调度<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://172.30.224.1:33571/<br>code：500<br>msg：xxl-job remoting error(Connection refused (Connection refused)), for url : http://172.30.224.1:33571/run',NULL,0,NULL,0);
/*!40000 ALTER TABLE `xxl_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_log_report`
--

DROP TABLE IF EXISTS `xxl_job_log_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_log_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_log_report`
--

LOCK TABLES `xxl_job_log_report` WRITE;
/*!40000 ALTER TABLE `xxl_job_log_report` DISABLE KEYS */;
INSERT INTO `xxl_job_log_report` VALUES (1,'2024-11-27 00:00:00',0,0,0,NULL),(2,'2024-11-26 00:00:00',0,0,0,NULL),(3,'2024-11-25 00:00:00',0,0,0,NULL),(4,'2024-11-28 00:00:00',0,0,0,NULL),(5,'2024-11-29 00:00:00',0,3,3,NULL),(6,'2024-11-30 00:00:00',0,0,1,NULL),(7,'2024-12-01 00:00:00',0,8,2,NULL),(8,'2024-12-02 00:00:00',0,0,4,NULL),(9,'2024-12-03 00:00:00',0,0,4,NULL),(10,'2024-12-04 00:00:00',0,0,4,NULL),(11,'2024-12-05 00:00:00',0,0,4,NULL),(12,'2024-12-06 00:00:00',0,0,4,NULL),(13,'2024-12-09 00:00:00',0,0,8,NULL),(14,'2024-12-08 00:00:00',0,0,0,NULL),(15,'2024-12-07 00:00:00',0,0,0,NULL),(16,'2024-12-10 00:00:00',0,0,4,NULL),(17,'2024-12-18 00:00:00',0,0,4,NULL),(18,'2024-12-17 00:00:00',0,0,0,NULL),(19,'2024-12-16 00:00:00',0,0,0,NULL),(20,'2024-12-19 00:00:00',0,0,4,NULL),(21,'2024-12-20 00:00:00',0,0,0,NULL),(22,'2024-12-22 00:00:00',0,4,4,NULL),(23,'2024-12-21 00:00:00',0,0,0,NULL),(24,'2024-12-23 00:00:00',0,0,4,NULL),(25,'2024-12-24 00:00:00',0,0,0,NULL),(26,'2024-12-26 00:00:00',0,0,4,NULL),(27,'2024-12-25 00:00:00',0,0,0,NULL),(28,'2024-12-27 00:00:00',0,0,4,NULL),(29,'2025-01-16 00:00:00',0,0,4,NULL),(30,'2025-01-15 00:00:00',0,0,0,NULL),(31,'2025-01-14 00:00:00',0,0,0,NULL),(32,'2025-01-17 00:00:00',0,0,4,NULL),(33,'2025-02-06 00:00:00',0,0,4,NULL),(34,'2025-02-05 00:00:00',0,0,4,NULL),(35,'2025-02-04 00:00:00',0,0,0,NULL),(36,'2025-02-07 00:00:00',0,0,4,NULL),(37,'2025-02-08 00:00:00',0,0,4,NULL),(38,'2025-02-13 00:00:00',0,0,4,NULL),(39,'2025-02-12 00:00:00',0,0,0,NULL),(40,'2025-02-11 00:00:00',0,0,0,NULL),(41,'2025-02-14 00:00:00',0,0,4,NULL),(42,'2025-02-25 00:00:00',0,0,4,NULL),(43,'2025-02-24 00:00:00',0,0,0,NULL),(44,'2025-02-23 00:00:00',0,0,0,NULL),(45,'2025-02-26 00:00:00',0,0,4,NULL),(46,'2025-02-27 00:00:00',0,0,4,NULL),(47,'2025-03-01 00:00:00',0,0,4,NULL),(48,'2025-02-28 00:00:00',0,0,0,NULL),(49,'2025-03-02 00:00:00',0,0,0,NULL),(50,'2025-03-03 00:00:00',0,0,0,NULL),(51,'2025-03-04 00:00:00',0,0,0,NULL),(52,'2025-03-05 00:00:00',0,56,57,NULL),(53,'2025-03-06 00:00:00',0,13,374,NULL),(54,'2025-03-07 00:00:00',0,300,767,NULL),(55,'2025-03-08 00:00:00',0,490,1270,NULL),(56,'2025-03-09 00:00:00',0,315,1477,NULL),(57,'2025-03-10 00:00:00',0,236,2064,NULL),(58,'2025-03-11 00:00:00',0,462,4386,NULL),(59,'2025-03-12 00:00:00',0,1947,7951,NULL),(60,'2025-03-13 00:00:00',0,586,8940,NULL),(61,'2025-03-14 00:00:00',0,660,1643,NULL),(62,'2025-04-22 00:00:00',0,885,2293,NULL),(63,'2025-04-21 00:00:00',0,0,0,NULL),(64,'2025-04-20 00:00:00',0,0,0,NULL),(65,'2025-04-24 00:00:00',2,2352,6069,NULL),(66,'2025-04-23 00:00:00',0,3894,4801,NULL),(67,'2025-04-25 00:00:00',0,2365,214,NULL),(68,'2025-04-26 00:00:00',0,1918,6482,NULL),(69,'2025-04-27 00:00:00',0,1071,9223,NULL),(70,'2025-04-28 00:00:00',0,0,5658,NULL),(71,'2025-04-30 00:00:00',0,0,6079,NULL),(72,'2025-04-29 00:00:00',0,0,0,NULL),(73,'2025-05-01 00:00:00',0,121,7970,NULL),(74,'2025-05-02 00:00:00',0,9702,7578,NULL),(75,'2025-05-03 00:00:00',0,0,2348,NULL),(76,'2025-05-16 00:00:00',0,895,1054,NULL),(77,'2025-05-15 00:00:00',0,0,0,NULL),(78,'2025-05-14 00:00:00',0,0,0,NULL),(79,'2025-05-17 00:00:00',0,2229,72,NULL),(80,'2025-06-24 00:00:00',0,0,30,NULL),(81,'2025-06-23 00:00:00',0,0,0,NULL),(82,'2025-06-22 00:00:00',0,0,0,NULL);
/*!40000 ALTER TABLE `xxl_job_log_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_logglue`
--

DROP TABLE IF EXISTS `xxl_job_logglue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_logglue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_logglue`
--

LOCK TABLES `xxl_job_logglue` WRITE;
/*!40000 ALTER TABLE `xxl_job_logglue` DISABLE KEYS */;
/*!40000 ALTER TABLE `xxl_job_logglue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_registry`
--

DROP TABLE IF EXISTS `xxl_job_registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_registry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB AUTO_INCREMENT=800 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_registry`
--

LOCK TABLES `xxl_job_registry` WRITE;
/*!40000 ALTER TABLE `xxl_job_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `xxl_job_registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_user`
--

DROP TABLE IF EXISTS `xxl_job_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_user`
--

LOCK TABLES `xxl_job_user` WRITE;
/*!40000 ALTER TABLE `xxl_job_user` DISABLE KEYS */;
INSERT INTO `xxl_job_user` VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e',1,NULL);
/*!40000 ALTER TABLE `xxl_job_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-24 21:14:51
