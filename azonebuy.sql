/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.5.52-MariaDB : Database - azonebuy
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`azonebuy` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `azonebuy`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `display_id` varchar(20) DEFAULT NULL,
  `goods_url` varchar(200) NOT NULL,
  `prime_price` int(11) NOT NULL,
  `market_price` int(11) NOT NULL,
  `discount` double(2,1) DEFAULT NULL,
  `deadline_time` datetime DEFAULT NULL,
  `sales_volume` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `goods_information` varchar(1000) NOT NULL,
  `first_level_name` varchar(100) NOT NULL,
  `first_level` varchar(500) NOT NULL,
  `second_level_name` varchar(100) DEFAULT NULL,
  `second_level` varchar(500) DEFAULT NULL,
  `third_level_name` varchar(100) DEFAULT NULL,
  `third_level` varchar(500) DEFAULT NULL,
  `goods_images` varchar(4000) NOT NULL,
  `buy_information` varchar(1000) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`name`,`display_id`,`goods_url`,`prime_price`,`market_price`,`discount`,`deadline_time`,`sales_volume`,`stock`,`goods_information`,`first_level_name`,`first_level`,`second_level_name`,`second_level`,`third_level_name`,`third_level`,`goods_images`,`buy_information`,`remark`) values 
(1,'【心動抄底價 戶外旅行包 買一贈一】','1501681500511','https://img.alicdn.com/imgextra/i2/763591660/TB2x6F1Xh_fFuJjy0FgXXcaCFXa_!!763591660.jpg',3299,1499,3.2,'2017-08-02 22:34:34',4570,430,'超值 ，免運，必備，;心動，不如行動。;多功能雙肩背包！;買一贈一！;注意！！！第二件顏色隨機發放！！！','請選擇顏色','【心動抄底價 多功能雙肩背包！（綠色） 買一贈一】;\r\n【心動抄底價 多功能雙肩背包！（粉紅色） 買一贈一】;\r\n【心動抄底價 多功能雙肩背包！（黑色） 買一贈一】;\r\n【心動抄底價 多功能雙肩背包！（紅色） 買一贈一】;\r\n【心動抄底價 多功能雙肩背包！（黃色） 買一贈一】;\r\n【心動抄底價 多功能雙肩背包！（藍色） 買一贈一】;\r\n【心動抄底價 多功能雙肩背包！（橙色） 買一贈一】','請選擇尺碼','小號;大號;升級大號',NULL,NULL,'{1:\"https://img.alicdn.com/imgextra/i1/763591660/TB2FbZXd77myKJjSZFzXXXgDpXa_!!763591660.jpg\",2:\"https://img.alicdn.com/imgextra/i2/763591660/TB2NvAXd77myKJjSZFzXXXgDpXa_!!763591660.gif\",3:\"https://img.alicdn.com/imgextra/i2/763591660/TB2Z5fPd4olyKJjSZPfXXawNpXa_!!763591660.gif\",4:\"https://img.alicdn.com/imgextra/i2/763591660/TB2IVEbdYAlyKJjSZFBXXbtiFXa_!!763591660.jpg\",5:\"https://img.alicdn.com/imgextra/i4/763591660/TB2BlZcck7OyuJjSspbXXXZuXXa_!!763591660.jpg\",6:\"https://img.alicdn.com/imgextra/i2/763591660/TB2yW6scXIkyKJjy0FgXXX0mFXa_!!763591660.jpg\",7:\"https://img.alicdn.com/imgextra/i2/763591660/TB2p426ca3PyuJjy1zkXXcjRFXa_!!763591660.jpg\",8:\"https://img.alicdn.com/imgextra/i1/763591660/TB2zb3qckUOyuJjy1XdXXXlkXXa_!!763591660.jpg\",9:\"https://img.alicdn.com/imgextra/i1/763591660/TB2IW7eck7OyuJjSsplXXXqdpXa_!!763591660.jpg\",10:\"https://img.alicdn.com/imgextra/i4/763591660/TB28ukmclAOyuJjy0FlXXcaxFXa_!!763591660.jpg\",11:\"https://img.alicdn.com/imgextra/i2/763591660/TB20HYIco3iyKJjSspnXXXbIVXa_!!763591660.jpg\",12:\"https://img.alicdn.com/imgextra/i3/763591660/TB2LJzscXIkyKJjy0FgXXX0mFXa_!!763591660.jpg\",13:\"https://img.alicdn.com/imgextra/i3/763591660/TB25fnjcXokyKJjy1zbXXXZfVXa_!!763591660.jpg\",14:\"https://img.alicdn.com/imgextra/i3/763591660/TB209DlcgwjyKJjSsppXXaxUpXa_!!763591660.gif\",15:\"https://img.alicdn.com/imgextra/i4/763591660/TB2.MIqckUOyuJjy1XdXXXlkXXa_!!763591660.gif\",16:\"https://img.alicdn.com/imgextra/i1/763591660/TB21tbhco3iyKJjy1zeXXbxZFXa_!!763591660.jpg\",17:\"https://img.alicdn.com/imgextra/i1/763591660/TB2Bg_0d_MlyKJjSZFFXXalVFXa_!!763591660.jpg\",18:\"https://img.alicdn.com/imgextra/i4/763591660/TB2mT21d_IlyKJjSZFOXXaAhFXa_!!763591660.jpg\",19:\"https://img.alicdn.com/imgextra/i1/763591660/TB21jQXd63nyKJjSZFHXXaTCpXa_!!763591660.jpg\",20:\"https://img.alicdn.com/imgextra/i1/763591660/TB2e72Qd4olyKJjSZPfXXawNpXa_!!763591660.jpg\",21:\"https://img.alicdn.com/imgextra/i3/763591660/TB2_NY6d7onyKJjSZFtXXXNaVXa_!!763591660.jpg\",22:\"https://img.alicdn.com/imgextra/i2/763591660/TB2oQv_dYMlyKJjSZFlXXbMoFXa_!!763591660.jpg\",23:\"https://img.alicdn.com/imgextra/i3/763591660/TB2y4vncgojyKJjy0FaXXakspXa_!!763591660.jpg\",24:\"https://img.alicdn.com/imgextra/i2/763591660/TB27PfgcgwjyKJjy1zdXXbgZpXa_!!763591660.jpg\",25:\"https://img.alicdn.com/imgextra/i4/763591660/TB2Y9jIco3iyKJjSspnXXXbIVXa_!!763591660.jpg\",26:\"https://img.alicdn.com/imgextra/i1/763591660/TB2OEPscXIkyKJjy0FgXXX0mFXa_!!763591660.jpg\",27:\"https://img.alicdn.com/imgextra/i1/763591660/TB2JT2Ico3iyKJjSspnXXXbIVXa_!!763591660.jpg\",28:\"https://img.alicdn.com/imgextra/i4/763591660/TB2VL2ickUkyKJjy1zjXXX1wFXa_!!763591660.jpg\",29:\"https://img.alicdn.com/imgextra/i4/763591660/TB2JFDJco3iyKJjSspnXXXbIVXa_!!763591660.jpg\",30:\"https://img.alicdn.com/imgextra/i1/763591660/TB2pYrfcgAjyKJjSsziXXbyxXXa_!!763591660.jpg\",31:\"https://img.alicdn.com/imgextra/i1/763591660/TB2KwYqcXojyKJjy0FiXXbCrVXa_!!763591660.jpg\"}','今日下單立享全台免運！;本店支持貨到付款！;絕對的正品，絕對的優惠！;多功能雙肩背包！買一贈一！;注意！！！第二件顏色隨機發放！！！',''),
(2,'【新款輕山地車坐墊自行車坐墊鞍座包舒適】','1501727141316','https://img.alicdn.com/imgextra/i4/763591660/TB2IkG0XdrgFuJjSspaXXX2tFXa_!!763591660.jpg',2500,1250,5.0,'2017-08-03 21:06:49',1500,500,'鍛煉身體委屈了屁股？;為了完美體形委屈了它？;我們比你更愛你的屁股;創新設計，完美保護你的屁股;給你完全不一樣的的舒適體驗;知音難求，回饋更愛屁股的你;勁爆價格限時銷售;我們只做這一批;現在購買送防震內褲;雙重體驗，完美舒適;多重體位，左右搖擺中，呵護它。愛護它。','請選擇顏色','【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250','請選擇贈品防震內褲尺碼','S;M;L;XL;XXL;XXXL','','','{1:\"https://img.alicdn.com/imgextra/i2/763591660/TB2FBW1Xb_fFuJjSspeXXaLpVXa_!!763591660.jpg\",2:\"https://img.alicdn.com/imgextra/i1/763591660/TB2ynUXXariFuJjSZFKXXaSDXXa_!!763591660.jpg\",3:\"https://img.alicdn.com/imgextra/i4/763591660/TB2h3a0Xd_gFuJjy0FeXXapjFXa_!!763591660.jpg\",4:\"https://img.alicdn.com/imgextra/i2/763591660/TB2eTgaXajiFuJjSZFoXXaLuVXa_!!763591660.jpg\",5:\"https://img.alicdn.com/imgextra/i3/763591660/TB2SjobXhDhFuJjSZFMXXcAJXXa_!!763591660.jpg\",6:\"https://img.alicdn.com/imgextra/i3/763591660/TB2lnoaXajiFuJjSZFoXXaLuVXa_!!763591660.jpg\",7:\"https://img.alicdn.com/imgextra/i3/763591660/TB2qyIbXjLgFuJjSZJiXXbZLXXa_!!763591660.jpg\",8:\"https://img.alicdn.com/imgextra/i3/763591660/TB2mYUbXaHiFuJjSZFmXXaegFXa_!!763591660.jpg\",9:\"https://img.alicdn.com/imgextra/i2/763591660/TB2p4ZbXiDhFuJjSZFrXXcp4pXa_!!763591660.jpg\",10:\"https://img.alicdn.com/imgextra/i2/763591660/TB2W_AaXajiFuJjSZFoXXaLuVXa_!!763591660.jpg\",11:\"https://img.alicdn.com/imgextra/i3/763591660/TB2AuP.Xo6iFuJjSZFjXXajuFXa_!!763591660.jpg\",12:\"https://img.alicdn.com/imgextra/i3/763591660/TB2BR3cXmjgFuJjSZFlXXXGZpXa_!!763591660.jpg\",13:\"https://img.alicdn.com/imgextra/i4/763591660/TB2X4O0XdvgFuJjSszdXXccmFXa_!!763591660.jpg\",14:\"https://img.alicdn.com/imgextra/i1/763591660/TB24WG2XmzeFuJjy1zeXXa3RFXa_!!763591660.jpg\",15:\"https://img.alicdn.com/imgextra/i1/763591660/TB2Uza1XhDfFuJjy0FpXXbcqpXa_!!763591660.jpg\",16:\"https://img.alicdn.com/imgextra/i3/763591660/TB2E1q1XnTfFuJjSspjXXcGuFXa_!!763591660.jpg\",17:\"https://img.alicdn.com/imgextra/i2/763591660/TB2oEy2Xf_eFuJjy1zkXXXKVpXa_!!763591660.jpg\",18:\"https://img.alicdn.com/imgextra/i2/763591660/TB2oA12Xb6fFuJjy0FaXXc2uFXa_!!763591660.jpg\"}','鍛煉身體委屈了屁股？;為了完美體形委屈了它？;我們比你更愛你的屁股;創新設計，完美保護你的屁股;給你完全不一樣的的舒適體驗;知音難求，回饋更愛屁股的你;勁爆價格限時銷售;我們只做這一批;現在購買送防震內褲;雙重體驗，完美舒適;多重體位，左右搖擺中，呵護它。愛護它。',''),
(3,'【新款特惠！鋰電池45件套 變形款兩用電動螺絲刀電鑽】','1503237239459','https://img.alicdn.com/imgextra/i4/763591660/TB2pe0MXMYDK1JjSZPiXXbcHVXa_!!763591660.jpg',2398,1199,5.0,'2017-08-21 15:56:02',5675,360,'新款特惠;新品只送這一次;全新設計，別樣的手感;讓您家庭勞動不在費力;貨到付款！！包郵費！！;免去你後顧之憂;一套在手，工具全有;↓ ↓ ↓ ↓ ↓ ↓;速速下單，解放您的雙手','顏色','【新款特惠！工具箱（YGT牌子）灰色45件套】-NT$1199','','','','','{1:\"https://img.alicdn.com/imgextra/i1/763591660/TB2scxKXNDBK1JjSZFhXXXFFFXa_!!763591660.jpg\",2:\"https://img.alicdn.com/imgextra/i2/763591660/TB2bNtFXM6DK1JjSZJiXXalIVXa_!!763591660.jpg\",3:\"https://img.alicdn.com/imgextra/i3/763591660/TB2WV8MXNDBK1JjSZFhXXXFFFXa_!!763591660.jpg\",4:\"https://img.alicdn.com/imgextra/i2/763591660/TB26.pGXTPEK1JjSZFAXXbZuXXa_!!763591660.jpg\",5:\"https://img.alicdn.com/imgextra/i1/763591660/TB21jdLXNHBK1JjSZFkXXbg9VXa_!!763591660.jpg\",6:\"https://img.alicdn.com/imgextra/i2/763591660/TB2onBqXzS_LeJjSZFwXXbnnpXa_!!763591660.jpg\",7:\"https://img.alicdn.com/imgextra/i4/763591660/TB2GfqcXwvGK1Jjy0FcXXXXmVXa_!!763591660.jpg\",8:\"https://img.alicdn.com/imgextra/i1/763591660/TB2W99XXsrHK1JjSspdXXXNFpXa_!!763591660.jpg\",9:\"https://img.alicdn.com/imgextra/i1/763591660/TB2oKWaXv_HK1JjSszfXXc1pVXa_!!763591660.jpg\",10:\"https://img.alicdn.com/imgextra/i4/763591660/TB2wdFLXNHBK1JjSZFuXXXRSpXa_!!763591660.jpg\",11:\"https://img.alicdn.com/imgextra/i2/763591660/TB2AfubXwvGK1Jjy0FhXXcgiXXa_!!763591660.jpg\",12:\"https://img.alicdn.com/imgextra/i3/763591660/TB2omycXE6FK1Jjy0FpXXbFqVXa_!!763591660.jpg\",13:\"https://img.alicdn.com/imgextra/i2/763591660/TB2YtRKXMYDK1JjSZFCXXX.vXXa_!!763591660.jpg\",14:\"https://img.alicdn.com/imgextra/i4/763591660/TB22gNIXJHEK1JjSZFGXXcjVFXa_!!763591660.jpg\",15:\"https://img.alicdn.com/imgextra/i4/763591660/TB2o65bXDzGK1JjSsphXXXc6XXa_!!763591660.jpg\",16:\"https://img.alicdn.com/imgextra/i2/763591660/TB2IZGcXDzGK1JjSspnXXaVgXXa_!!763591660.jpg\",17:\"https://img.alicdn.com/imgextra/i3/763591660/TB2_bxPXNHBK1JjSZFvXXaKtXXa_!!763591660.jpg\"}','新款特惠;新品只送這一次;全新設計，別樣的手感;讓您家庭勞動不在費力;貨到付款！！包郵費！！;免去你後顧之憂;一套在手，工具全有;↓ ↓ ↓ ↓ ↓ ↓;速速下單，解放您的雙手',''),
(4,'【特惠！小米路由器3C智能無線家用WiFi穿墻王路由器】','1503542806502','https://img.alicdn.com/imgextra/i4/763591660/TB2ZEchXnZRMeJjSspkXXXGpXXa_!!763591660.jpg',2598,1299,5.0,'2017-08-24 10:54:41',1890,350,'最新小米路由器;小米公司原廠貨; 3G WiFi 4天線;  網速更快 寬頻 ;無線上網 不卡頓;現在下單 贈5米網線;包郵免運費，15天超長驗貨期！','顏色','【特惠！小米3C智能無線家用WiFi穿墻王路由器（贈5米網線）】-NT$1299','','','','','{1:\"https://img.alicdn.com/imgextra/i4/763591660/TB2yoFMXS7PL1JjSZFHXXcciXXa_!!763591660.jpg\",2:\"https://img.alicdn.com/imgextra/i1/763591660/TB2MA.uXgMPMeJjy1XcXXXpppXa_!!763591660.jpg\",3:\"https://img.alicdn.com/imgextra/i4/763591660/TB29bliXoFWMKJjSZFvXXaenFXa_!!763591660.jpg\",4:\"https://img.alicdn.com/imgextra/i2/763591660/TB25VlWXTwKL1JjSZFgXXb6aVXa_!!763591660.jpg\",5:\"https://img.alicdn.com/imgextra/i4/763591660/TB2UhkqXbsTMeJjSszgXXacpFXa_!!763591660.jpg\",6:\"https://img.alicdn.com/imgextra/i2/763591660/TB2bbh0XTAPL1JjSZFLXXXbWVXa_!!763591660.jpg\",7:\"https://img.alicdn.com/imgextra/i3/763591660/TB2DgV8XOAKL1JjSZFoXXagCFXa_!!763591660.jpg\",8:\"https://img.alicdn.com/imgextra/i1/763591660/TB2Yv5dXRcHL1JjSZFBXXaiGXXa_!!763591660.jpg\",9:\"https://img.alicdn.com/imgextra/i1/763591660/TB2y_ebXKkJL1JjSZFmXXcw0XXa_!!763591660.jpg\",10:\"https://img.alicdn.com/imgextra/i1/763591660/TB2p9aaXUEIL1JjSZFFXXc5kVXa_!!763591660.jpg\",11:\"https://img.alicdn.com/imgextra/i3/763591660/TB2ok.tXlUSMeJjSszeXXcKgpXa_!!763591660.jpg\",12:\"https://img.alicdn.com/imgextra/i1/763591660/TB2Q4GcXN3IL1JjSZPfXXcrUVXa_!!763591660.jpg\",13:\"https://img.alicdn.com/imgextra/i1/763591660/TB2pKVsXjJTMKJjSZFPXXbHUFXa_!!763591660.jpg\"}','最新小米路由器;小米公司原廠貨; 3G WiFi 4天線;  網速更快 寬頻 ;無線上網 不卡頓;現在下單 贈5米網線',''),
(5,'【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】','1503740188191','https://img.alicdn.com/imgextra/i2/763591660/TB2bTghXUgQMeJjy0FgXXc5dXXa_!!763591660.jpg',2598,1299,5.0,'2017-08-26 17:36:28',1890,580,'交通擁堵 心情煩躁;車內空氣渾濁 ;影響心情 破壞健康;炫酷車載氧吧;空氣淨化器;給您的愛車一個不一樣的空氣環境;給您和家人一個健康的空間;只為衝擊銷量 驚喜活動價;這個價格只敢做這一次;現在下單，買一贈一;全台免運','請選擇','【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299','','','','','{1:\"https://img.alicdn.com/imgextra/i2/763591660/TB2ees9XpdYMKJjSspbXXa2RVXa_!!763591660.jpg\",2:\"https://img.alicdn.com/imgextra/i2/763591660/TB2XmQgXTZRMeJjSspoXXcCOFXa_!!763591660.jpg\",3:\"https://img.alicdn.com/imgextra/i4/763591660/TB2nwwfXHsTMeJjSsziXXcdwXXa_!!763591660.jpg\",4:\"https://img.alicdn.com/imgextra/i3/763591660/TB2E9skXMoQMeJjy1XaXXcSsFXa_!!763591660.jpg\",5:\"https://img.alicdn.com/imgextra/i2/763591660/TB21dAlXMoQMeJjy0FpXXcTxpXa_!!763591660.jpg\",6:\"https://img.alicdn.com/imgextra/i1/763591660/TB2b.QmXMMPMeJjy1XbXXcwxVXa_!!763591660.jpg\"}','交通擁堵 心情煩躁;車內空氣渾濁 ;影響心情 破壞健康;炫酷車載氧吧;空氣淨化器;給您的愛車一個不一樣的空氣環境;給您和家人一個健康的空間;只為衝擊銷量 驚喜活動價;這個價格只敢做這一次;現在下單，買一贈一;全台免運',''),
(6,'【火爆！單晶硅折疊太陽能充電器 買一贈十】','1504239233309','https://img.alicdn.com/imgextra/i1/763591660/TB2.0eWa8USMeJjy1zjXXc0dXXa_!!763591660.jpg',2598,1299,5.0,'2017-09-01 12:36:53',5644,98,'單晶硅折疊太陽能充電器，;\r\n原價NT$2598，今日只為銷量，只要NT$1299！！;\r\n還有！！！;\r\n買一贈十，贈送登山扣十個！;\r\n優惠僅限今日！贈品贈完為止！;\r\n貨到付款！先驗貨，再付款！;\r\n不見貨不給錢！;\r\n不是正品不給錢！;\r\n今日下單！再享全台免運！！;\r\n15天超長鑒賞！如有任何不滿意，無憂退換！;\r\n選購產品軍用多功能鉗，只為與眾不同！;\r\n免運費！免運費！免運費！','請選擇產品','【火爆！單晶硅折疊太陽能充電器 買一贈十】-NT$1299;【超強震撼！軍用級別多功能鉗】-NT$1299','','','','','{1:\"https://img.alicdn.com/imgextra/i4/763591660/TB2NoOXcgkLL1JjSZFpXXa7nFXa_!!763591660.jpg\",2:\"https://img.alicdn.com/imgextra/i4/763591660/TB2HT1hce7JL1JjSZFKXXc4KXXa_!!763591660.jpg\",3:\"https://img.alicdn.com/imgextra/i2/763591660/TB2ErCZa3oQMeJjy0FpXXcTxpXa_!!763591660.jpg\",4:\"https://img.alicdn.com/imgextra/i1/763591660/TB2MWyacosIL1JjSZPiXXXKmpXa_!!763591660.jpg\",5:\"https://img.alicdn.com/imgextra/i3/763591660/TB2061Sa8USMeJjSszeXXcKgpXa_!!763591660.jpg\",6:\"https://img.alicdn.com/imgextra/i1/763591660/TB2LQ5Wa5ERMeJjSspiXXbZLFXa_!!763591660.jpg\",7:\"https://img.alicdn.com/imgextra/i3/763591660/TB2f9mTa_ZRMeJjSsplXXXeqXXa_!!763591660.jpg\",8:\"https://img.alicdn.com/imgextra/i3/763591660/TB2cg1pcoAKL1JjSZFkXXa8cFXa_!!763591660.jpg\",9:\"https://img.alicdn.com/imgextra/i3/763591660/TB2vO1Ra3sSMeJjSsphXXXuJFXa_!!763591660.jpg\",10:\"https://img.alicdn.com/imgextra/i2/763591660/TB2tiyTa3MPMeJjy1XdXXasrXXa_!!763591660.jpg\",11:\"https://img.alicdn.com/imgextra/i2/763591660/TB2CRGlcoQIL1JjSZFhXXaDZFXa_!!763591660.jpg\",12:\"https://img.alicdn.com/imgextra/i1/763591660/TB2UiOQaYsTMeJjSszgXXacpFXa_!!763591660.jpg\"}','單晶硅折疊太陽能充電器，;\r\n原價NT$2598，今日只為銷量，只要NT$1299！！;\r\n還有！！！;\r\n買一贈十，贈送登山扣十個！;\r\n優惠僅限今日！贈品贈完為止！;\r\n貨到付款！先驗貨，再付款！;\r\n不見貨不給錢！;\r\n不是正品不給錢！;\r\n今日下單！再享全台免運！！;\r\n15天超長鑒賞！如有任何不滿意，無憂退換！;\r\n選購產品軍用多功能鉗，只為與眾不同！;\r\n免運費！免運費！免運費！',''),
(7,'【新款特惠！360°隱蔽式燈泡攝像頭】','1504283907310','https://img.alicdn.com/imgextra/i4/807127278/TB25o._a.gQMeJjy0FiXXXhqXXa_!!807127278.jpg',3165,1899,6.0,'2017-09-02 01:00:23',71,65,'全新隱蔽360°燈泡攝像頭;\r\n不易發現！！完美隱藏！！;\r\n保護家人安全！！保護公司財產！！;\r\n防人之心不可無;\r\n超強設計！！神不知鬼不覺急速安裝！！;\r\n超多用處等待您的開發！！！;\r\n新品上市只為知名度;\r\n限時促銷折扣！！;\r\n活動期間下單！再贈4G內存卡！！享全台免運！！;\r\n免運費！！免運費！！免運費！！;\r\n貨到付款！！貨到付款！！貨到付款！！;','請選擇產品','【火爆！360°隱蔽式燈泡攝像頭】-NT$1899;','','','','','{1:\"https://img.alicdn.com/imgextra/i2/807127278/TB2NUI9a.gQMeJjy0FeXXXOEVXa_!!807127278.jpg\",2:\"https://img.alicdn.com/imgextra/i4/807127278/TB2EFM_aYsTMeJjSsziXXcdwXXa_!!807127278.jpg\",3:\"https://img.alicdn.com/imgextra/i1/807127278/TB2FQ0XbbsTMeJjSszhXXcGCFXa_!!807127278.jpg\",4:\"https://img.alicdn.com/imgextra/i3/807127278/TB24z8ycx3IL1JjSZPfXXcrUVXa_!!807127278.jpg\",5:\"https://img.alicdn.com/imgextra/i1/807127278/TB25DkRbPJTMKJjSZFPXXbHUFXa_!!807127278.jpg\",6:\"https://img.alicdn.com/imgextra/i3/807127278/TB2_YAGbUF7MKJjSZFLXXcMBVXa_!!807127278.jpg\",7:\"https://img.alicdn.com/imgextra/i1/807127278/TB25bNrcwkLL1JjSZFpXXa7nFXa_!!807127278.jpg\",8:\"https://img.alicdn.com/imgextra/i3/807127278/TB2YSXNcEcKL1JjSZFzXXcfJXXa_!!807127278.jpg\",9:\"https://img.alicdn.com/imgextra/i4/807127278/TB2nh7vbUl7MKJjSZFDXXaOEpXa_!!807127278.jpg\",10:\"https://img.alicdn.com/imgextra/i4/807127278/TB2SwBGczoIL1JjSZFyXXbFBpXa_!!807127278.jpg\",11:\"https://img.alicdn.com/imgextra/i1/807127278/TB2ci4bbgoQMeJjy0FpXXcTxpXa_!!807127278.jpg\",12:\"https://img.alicdn.com/imgextra/i3/807127278/TB2hvI.a3sSMeJjSspcXXXjFXXa_!!807127278.jpg\",13:\"https://img.alicdn.com/imgextra/i3/807127278/TB2Q50zcDAKL1JjSZFCXXXFspXa_!!807127278.jpg\",14:\"https://img.alicdn.com/imgextra/i3/807127278/TB2VsXycuEJL1JjSZFGXXa6OXXa_!!807127278.jpg\",15:\"https://img.alicdn.com/imgextra/i1/807127278/TB2n5U.a_ZRMeJjSsppXXXrEpXa_!!807127278.jpg\"}','全新隱蔽360°燈泡攝像頭;\r\n不易發現！！完美隱藏！！;\r\n保護家人安全！！保護公司財產！！;\r\n超強設計！！神不知鬼不覺急速安裝！！;\r\n超多用處等待您的開發！！！;\r\n限時促銷折扣！！;\r\n活動期間下單！再贈4G內存卡！！享全台免運！！;\r\n免運費！！免運費！！免運費！！;\r\n貨到付款！！貨到付款！！貨到付款！！;','');

/*Table structure for table `goods_order` */

DROP TABLE IF EXISTS `goods_order`;

CREATE TABLE `goods_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_id` varchar(50) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `goods_spec` varchar(500) NOT NULL,
  `num` int(11) NOT NULL,
  `order_price` double(10,2) NOT NULL,
  `order_currency` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `order_state` tinyint(4) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `order_information` varchar(500) DEFAULT NULL,
  `order_remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

/*Data for the table `goods_order` */

insert  into `goods_order`(`id`,`display_id`,`goods_id`,`goods_spec`,`num`,`order_price`,`order_currency`,`create_time`,`order_state`,`update_time`,`member_id`,`order_information`,`order_remark`) values 
(62,'1502715119344449178',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（橙色） 買一贈一】,請選擇尺碼 升級大號',1,1499.00,1,'2017-08-14 20:51:59',11,NULL,NULL,NULL,NULL),
(63,'1502715246980556056',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（橙色） 買一贈一】,請選擇尺碼 小號',1,1499.00,1,'2017-08-14 20:54:06',11,NULL,NULL,NULL,NULL),
(64,'1502715250142146673',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（橙色） 買一贈一】,請選擇尺碼 小號,請選擇尺碼 小號',1,1499.00,1,'2017-08-14 20:54:10',11,NULL,NULL,NULL,NULL),
(65,'1502716650680135760',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（藍色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-14 21:17:30',11,NULL,NULL,NULL,NULL),
(66,'1502717004996823629',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（黃色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-14 21:23:24',11,NULL,NULL,NULL,NULL),
(67,'1502717058627992981',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（紅色） 買一贈一】,請選擇尺碼 升級大號',1,1499.00,1,'2017-08-14 21:24:18',11,NULL,NULL,NULL,NULL),
(69,'1502806219390060415',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（粉紅色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-15 22:10:19',11,NULL,NULL,NULL,NULL),
(70,'1502806539454893739',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（橙色） 買一贈一】,請選擇尺碼 小號',1,1499.00,1,'2017-08-15 22:15:39',11,NULL,NULL,NULL,NULL),
(71,'1502806695822579015',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（粉紅色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-15 22:18:15',11,NULL,NULL,NULL,NULL),
(72,'1502806700267565708',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（粉紅色） 買一贈一】,請選擇尺碼 大號,請選擇尺碼 大號',1,1499.00,1,'2017-08-15 22:18:20',11,NULL,NULL,NULL,NULL),
(73,'1502806742682588985',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（藍色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-15 22:19:02',11,NULL,NULL,NULL,NULL),
(74,'1502808583813370121',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XXL',1,1250.00,1,'2017-08-15 22:49:43',11,NULL,NULL,NULL,NULL),
(75,'1502809442623115623',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（粉紅色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-15 23:04:02',11,NULL,NULL,NULL,NULL),
(76,'1502846354235036807',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（黑色） 買一贈一】,請選擇尺碼 升級大號',1,1499.00,1,'2017-08-16 09:19:14',11,NULL,NULL,NULL,NULL),
(77,'1502846394338020233',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（橙色） 買一贈一】,請選擇尺碼 升級大號',1,1499.00,1,'2017-08-16 09:19:54',11,NULL,NULL,NULL,NULL),
(78,'1502846423496340460',1,'請選擇顏色 【心動抄底價 多功能雙肩背包！（綠色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-16 09:20:23',11,NULL,NULL,NULL,NULL),
(79,'1502846430655270882',1,'請選擇顏色 【心動抄底價 多功能雙肩背包！（綠色） 買一贈一】,請選擇尺碼 大號,請選擇尺碼 大號',1,1499.00,1,'2017-08-16 09:20:30',11,NULL,NULL,NULL,NULL),
(80,'1502846453344536170',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XXL',1,1250.00,1,'2017-08-16 09:20:53',11,NULL,NULL,NULL,NULL),
(82,'1502864734445693057',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 M',1,1250.00,1,'2017-08-16 14:25:34',31,'2017-10-14 21:42:04',11,NULL,NULL),
(83,'1502886303783404626',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（紅色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-16 20:25:03',11,NULL,NULL,NULL,NULL),
(84,'1502976804705545838',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XXL',1,1250.00,1,'2017-08-17 21:33:24',-1,'2017-08-17 21:33:33',NULL,NULL,NULL),
(85,'1503022164109473546',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（紅色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-08-18 10:09:24',11,NULL,NULL,NULL,NULL),
(86,'1503302189797194176',3,'顏色 【新款特惠！工具箱（YGT牌子）灰色45件套】-NT$1199',1,1199.00,1,'2017-08-21 15:56:29',11,NULL,NULL,NULL,NULL),
(87,'1503302196817193058',3,'顏色 【新款特惠！工具箱（YGT牌子）灰色45件套】-NT$1199',1,1199.00,1,'2017-08-21 15:56:36',11,'2017-08-21 15:56:49',NULL,NULL,NULL),
(88,'1503385129539852523',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XXL',1,1250.00,1,'2017-08-22 14:58:49',12,'2017-08-22 15:01:29',12,NULL,NULL),
(89,'1503393842591121245',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XL',1,1250.00,1,'2017-08-22 17:24:02',11,NULL,NULL,NULL,NULL),
(90,'1503457868372445373',3,'顏色 【新款特惠！工具箱（YGT牌子）灰色45件套】-NT$1199',1,1199.00,1,'2017-08-23 11:11:08',11,NULL,NULL,NULL,NULL),
(91,'1503468185460846733',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 L',1,1250.00,1,'2017-08-23 14:03:05',22,'2017-08-27 08:46:15',14,NULL,'座墊可改藍色？'),
(92,'1503486913165912665',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XXXL',1,1250.00,1,'2017-08-23 19:15:13',12,'2017-08-23 19:18:14',15,NULL,NULL),
(93,'1503496598792496934',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 L',1,1250.00,1,'2017-08-23 21:56:38',12,'2017-08-23 21:59:05',16,NULL,NULL),
(94,'1503543058732982749',4,'顏色 【特惠！小米路由器3C智能無線家用WiFi穿墻王路由器】-NT$1299',1,1299.00,1,'2017-08-24 10:50:58',11,NULL,NULL,NULL,NULL),
(95,'1503543308565215456',4,'顏色 【特惠！小米3C智能無線家用WiFi穿墻王路由器（贈5米網線）】-NT$1299',1,1299.00,1,'2017-08-24 10:55:08',11,NULL,NULL,NULL,NULL),
(96,'1503590045883938123',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XL',1,1250.00,1,'2017-08-24 23:54:05',11,NULL,NULL,NULL,NULL),
(97,'1503626150991147234',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 L',1,1250.00,1,'2017-08-25 09:55:50',12,'2017-08-25 09:57:29',17,NULL,NULL),
(98,'1503658274418732338',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 L',2,1250.00,1,'2017-08-25 18:51:14',11,NULL,NULL,NULL,NULL),
(99,'1503672451305396916',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 S',1,1250.00,1,'2017-08-25 22:47:31',11,NULL,NULL,NULL,NULL),
(100,'1503682231345232006',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 L',1,1250.00,1,'2017-08-26 01:30:31',12,'2017-08-26 01:31:56',18,NULL,NULL),
(101,'1503719374543740816',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XL',1,1250.00,1,'2017-08-26 11:49:34',12,'2017-08-26 11:53:25',19,NULL,NULL),
(102,'1503722237881340278',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 L',1,1250.00,1,'2017-08-26 12:37:17',11,NULL,NULL,NULL,NULL),
(103,'1503740253178546081',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-08-26 17:37:33',11,NULL,NULL,NULL,NULL),
(104,'1503752147756816936',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 S',1,1250.00,1,'2017-08-26 20:55:47',11,NULL,NULL,NULL,NULL),
(105,'1503876385576597984',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XL',1,1250.00,1,'2017-08-28 07:26:25',11,'2017-08-28 07:26:41',NULL,NULL,NULL),
(106,'1503893246725274786',2,'請選擇顏色 【新款輕山地車坐墊自行車坐墊鞍座包舒適（黑色）贈防震內褲】-NT$1250,請選擇贈品防震內褲尺碼 XL',1,1250.00,1,'2017-08-28 12:07:26',11,NULL,NULL,NULL,NULL),
(108,'1504240220696490055',6,'請選擇產品 【單晶硅折疊太陽能充電器 買一贈十+軍用級別多功能鉗】-NT$2400',1,1299.00,1,'2017-09-01 12:30:20',11,NULL,NULL,NULL,NULL),
(109,'1504240644661153882',6,'請選擇產品 【超強震撼！軍用級別多功能鉗】-NT$1299',1,1299.00,1,'2017-09-01 12:37:24',11,NULL,NULL,NULL,NULL),
(110,'1504275503640785120',6,'請選擇產品 【火爆！單晶硅折疊太陽能充電器 買一贈十】-NT$1299',1,1299.00,1,'2017-09-01 22:18:23',12,'2017-09-01 22:20:34',21,NULL,'出貨先通知'),
(111,'1504287645186738510',7,'請選擇產品 【火爆！360°隱蔽式燈泡攝像頭】-NT$1899',1,1899.00,1,'2017-09-02 01:40:45',11,NULL,NULL,NULL,NULL),
(112,'1507973280798019175',1,'請選擇顏色 \r\n【心動抄底價 多功能雙肩背包！（橙色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-10-14 17:28:00',11,NULL,NULL,NULL,NULL),
(113,'1508217232133233833',3,'顏色 【新款特惠！工具箱（YGT牌子）灰色45件套】-NT$1199',1,1199.00,1,'2017-10-17 13:13:52',11,NULL,NULL,NULL,NULL),
(114,'1508218462387072188',3,'顏色 【新款特惠！工具箱（YGT牌子）灰色45件套】-NT$1199',1,1199.00,1,'2017-10-17 13:34:22',11,NULL,NULL,NULL,NULL),
(115,'1508369551849134088',1,'請選擇顏色 【心動抄底價 多功能雙肩背包！（綠色） 買一贈一】,請選擇尺碼 大號',1,1499.00,1,'2017-10-19 07:32:31',11,NULL,NULL,NULL,NULL),
(116,'1508505008616132871',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-20 21:10:08',11,NULL,NULL,NULL,NULL),
(117,'1508593543555663543',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-21 21:45:43',11,NULL,NULL,NULL,NULL),
(118,'1508593603907133225',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-21 21:46:43',11,NULL,NULL,NULL,NULL),
(119,'1508593762470055504',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-21 21:49:22',11,'2017-10-22 01:20:15',NULL,NULL,NULL),
(120,'1508593801498580319',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-21 21:50:01',11,NULL,NULL,NULL,NULL),
(121,'1508595734908640933',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-21 22:22:14',11,NULL,NULL,NULL,NULL),
(122,'1508595884024134033',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-21 22:24:44',11,NULL,NULL,NULL,NULL),
(123,'1508604257565009132',5,'Ո�x�� ���@����܇�d�����܇�d�՚�Q����pm2.5��܇���� �Iһٛһ��-1260',1,1299.00,1,'2017-10-22 00:44:17',11,NULL,NULL,NULL,NULL),
(124,'1508634569928693970',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-22 09:09:29',11,NULL,NULL,NULL,NULL),
(125,'1508634748156853197',1,'請選擇顏色 【心動抄底價 多功能雙肩背包！（綠色） 買一贈一】,請選擇尺碼 升級大號',1,1499.00,1,'2017-10-22 09:12:28',11,NULL,NULL,NULL,NULL),
(126,'1508647436450796872',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-22 12:43:56',11,NULL,NULL,NULL,NULL),
(127,'1508647577032066137',5,'請選擇 【驚爆！車載充電器車載空氣淨化器pm2.5汽車氧吧 買一贈一】-NT$1299',1,1299.00,1,'2017-10-22 12:46:17',11,NULL,NULL,NULL,NULL),
(128,'1508825317312128831',1,'請選擇顏色 【心動抄底價 多功能雙肩背包！（綠色） 買一贈一】,請選擇尺碼 小號',1,1499.00,1,'2017-10-24 14:08:37',11,NULL,NULL,NULL,NULL);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `mobilephone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `region` varchar(100) NOT NULL,
  `address` varchar(500) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `member` */

insert  into `member`(`id`,`name`,`mobilephone`,`email`,`region`,`address`,`remark`) values 
(11,'陳正益','0963194692','','南投縣埔里鎮','南投縣埔里鎮南安路133號',''),
(12,'蘇東城','0932620407','','台中市西區','台中市西區民生路33號6F之1',''),
(13,'王家俊','0933933353','','基隆市中正區','基隆市中正區基隆市新豐街472號6樓',''),
(14,'王家俊','0933933353','','基隆市仁愛區','基隆市仁愛區基隆市新豐街472號6樓','座墊可改藍色？'),
(15,'潘正吉','0921131883','isabella.sjp@gmail.com','宜蘭縣羅東鎮','宜蘭縣羅東鎮西安街258巷7號',''),
(16,'蘇朝海','0928636408','chao@honqchi.com.tw','桃園市楊梅區','桃園市楊梅區高獅路822巷1-1號',''),
(17,'錢冠仁','0933297012','a0933297012@gmail.com','台南市東區','台南市東區崇德二街十號',''),
(18,'劉禎隆','0935289199','ray@jypmold.com','新北市新莊區','新北市新莊區幸福東路76號',''),
(19,'張清林','0963368068','chinlin456@gmail.com','新北市永和區','新北市永和區永和區秀朗路一段215號',''),
(21,'朱冠渊','0981416648','a0981416648@gmail.com','新北市雙溪區','新北市雙溪區. 新北市双溪區長源里3鄰赤皮崙6号','出貨先通知');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
