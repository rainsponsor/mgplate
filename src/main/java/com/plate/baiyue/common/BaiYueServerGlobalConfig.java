package com.plate.baiyue.common;
/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-31
 * @描述:百阅文件(数据库、图片、视频......)服务器端存储路径配置
 */
public class BaiYueServerGlobalConfig {
	//session中存储的科目根目录标志
	public static final String CATALOG_ID = "catalog_id";
	//知识点模板数据文件
	public static final String ZSD_DBFILE = "/resources/file/db/template/baiyue.db";
	//服务端知识点数据文件生成（下载）路径
	public static final String GZ_SHUXUE_DBFILE = "/resources/file/db/gz_shuxue/gz_shuxue.db";//高中数学
	public static final String GZ_YUWEN_DBFILE = "/resources/file/db/gz_yuwen/gz_yuwen.db";//高中语文
	public static final String GZ_YINGYU_DBFILE = "/resources/file/db/gz_yingyu/gz_yingyu.db";//高中英语
	public static final String GZ_WULI_DBFILE = "/resources/file/db/gz_wuli/gz_wuli.db";//高中物理
	public static final String GZ_HUAXUE_DBFILE = "/resources/file/db/gz_huaxue/gz_huaxue.db";//高中化学
	public static final String GZ_SHENGWU_DBFILE = "/resources/file/db/gz_shengwu/gz_shengwu.db";//高中生物
	public static final String GZ_ZHENGZHI_DBFILE = "/resources/file/db/gz_zhengzhi/gz_zhengzhi.db";//高中政治
	public static final String GZ_DILI_DBFILE = "/resources/file/db/gz_dili/gz_dili.db";//高中地理
	public static final String GZ_LISHI_DBFILE = "/resources/file/db/gz_lishi/gz_lishi.db";//高中历史
	
	public static final String CZ_SHUXUE_DBFILE = "/resources/file/db/cz_shuxue/cz_shuxue.db";//初中数学
	public static final String CZ_YUWEN_DBFILE = "/resources/file/db/cz_yuwen/cz_yuwen.db";//初中语文
	public static final String CZ_YINGYU_DBFILE = "/resources/file/db/cz_yingyu/cz_yingyu.db";//初中英语
	public static final String CZ_WULI_DBFILE = "/resources/file/db/cz_wuli/cz_wuli.db";//初中物理
	public static final String CZ_HUAXUE_DBFILE = "/resources/file/db/cz_huaxue/cz_huaxue.db";//初中化学
	public static final String CZ_SHENGWU_DBFILE = "/resources/file/db/cz_shengwu/cz_shengwu.db";//初中生物
	public static final String CZ_ZHENGZHI_DBFILE = "/resources/file/db/cz_zhengzhi/cz_zhengzhi.db";//初中政治
	public static final String CZ_DILI_DBFILE = "/resources/file/db/cz_dili/cz_dili.db";//初中地理
	public static final String CZ_LISHI_DBFILE = "/resources/file/db/cz_lishi/cz_lishi.db";//初中历史
	
	//服务端知识点图片文件存放（下载）路径
	public static final String GZ_SHUXUE_IMGS = "/resources/file/images/gz_shuxue";//高中数学
	public static final String GZ_YUWEN_IMGS = "/resources/file/images/gz_yuwen";//高中语文
	public static final String GZ_YINGYU_IMGS = "/resources/file/images/gz_yingyu";//高中英语
	public static final String GZ_WULI_IMGS = "/resources/file/images/gz_wuli";//高中物理
	public static final String GZ_HUAXUE_IMGS = "/resources/file/images/gz_huaxue";//高中化学
	public static final String GZ_SHENGWU_IMGS = "/resources/file/images/gz_shengwu";//高中生物
	public static final String GZ_ZHENGZHI_IMGS = "/resources/file/images/gz_zhengzhi";//高中政治
	public static final String GZ_DILI_IMGS = "/resources/file/images/gz_dili";//高中地理
	public static final String GZ_LISHI_IMGS = "/resources/file/images/gz_lishi";//高中历史
	
	public static final String CZ_SHUXUE_IMGS = "/resources/file/images/cz_shuxue";//初中数学
	public static final String CZ_YUWEN_IMGS = "/resources/file/images/cz_yuwen";//初中语文
	public static final String CZ_YINGYU_IMGS = "/resources/file/images/cz_yingyu";//初中英语
	public static final String CZ_WULI_IMGS = "/resources/file/images/cz_wuli";//初中物理
	public static final String CZ_HUAXUE_IMGS = "/resources/file/images/cz_huaxue";//初中化学
	public static final String CZ_SHENGWU_IMGS = "/resources/file/images/cz_shengwu";//初中生物
	public static final String CZ_ZHENGZHI_IMGS = "/resources/file/images/cz_zhengzhi";//初中政治
	public static final String CZ_DILI_IMGS = "/resources/file/images/cz_dili";//初中地理
	public static final String CZ_LISHI_IMGS = "/resources/file/images/cz_lishi";//初中历史
	
	public static String getDbDirNameByCatalog(Integer catalog){
		String dir = null;
		switch(catalog){
		case 1:
			dir = GZ_SHUXUE_DBFILE;
			break;
		case 2:
			dir = GZ_YUWEN_DBFILE;
			break;
		case 3:
			dir = GZ_YINGYU_DBFILE;
			break;
		case 4:
			dir = GZ_WULI_DBFILE;
			break;
		case 5:
			dir = GZ_HUAXUE_DBFILE;
			break;
		case 6:
			dir = GZ_SHENGWU_DBFILE;
			break;
		case 7:
			dir = GZ_ZHENGZHI_DBFILE;
			break;
		case 8:
			dir = GZ_DILI_DBFILE;
			break;
		case 9:
			dir = GZ_LISHI_DBFILE;
			break;
		case 10:
			dir = CZ_SHUXUE_DBFILE;
			break;
		case 11:
			dir = CZ_YUWEN_DBFILE;
			break;
		case 12:
			dir = CZ_YINGYU_DBFILE;
			break;
		case 13:
			dir = CZ_WULI_DBFILE;
			break;
		case 14:
			dir = CZ_HUAXUE_DBFILE;
			break;
		case 15:
			dir = CZ_SHENGWU_DBFILE;
			break;
		case 16:
			dir = CZ_ZHENGZHI_DBFILE;
			break;
		case 17:
			dir = CZ_DILI_DBFILE;
			break;
		case 18:
			dir = CZ_LISHI_DBFILE;
			break;
		}
		return dir;
	}
	
	public static String getImageDirNameByCatalog(Integer catalog){
		String dir = null;
		switch(catalog){
		case 1:
			dir = GZ_SHUXUE_IMGS;
			break;
		case 2:
			dir = GZ_YUWEN_IMGS;
			break;
		case 3:
			dir = GZ_YINGYU_IMGS;
			break;
		case 4:
			dir = GZ_WULI_IMGS;
			break;
		case 5:
			dir = GZ_HUAXUE_IMGS;
			break;
		case 6:
			dir = GZ_SHENGWU_IMGS;
			break;
		case 7:
			dir = GZ_ZHENGZHI_IMGS;
			break;
		case 8:
			dir = GZ_DILI_IMGS;
			break;
		case 9:
			dir = GZ_LISHI_IMGS;
			break;
		case 10:
			dir = CZ_SHUXUE_IMGS;
			break;
		case 11:
			dir = CZ_YUWEN_IMGS;
			break;
		case 12:
			dir = CZ_YINGYU_IMGS;
			break;
		case 13:
			dir = CZ_WULI_IMGS;
			break;
		case 14:
			dir = CZ_HUAXUE_IMGS;
			break;
		case 15:
			dir = CZ_SHENGWU_IMGS;
			break;
		case 16:
			dir = CZ_ZHENGZHI_IMGS;
			break;
		case 17:
			dir = CZ_DILI_IMGS;
			break;
		case 18:
			dir = CZ_LISHI_IMGS;
			break;
		}
		return dir;
	}
	
	public static String getImageFileByCatalog(Integer catalog){
		String dir = null;
		switch(catalog){
		case 1:
			dir = "gz_shuxue";
			break;
		case 2:
			dir = "gz_yuwen";
			break;
		case 3:
			dir = "gz_yingyu";
			break;
		case 4:
			dir = "gz_wuli";
			break;
		case 5:
			dir = "gz_huaxue";
			break;
		case 6:
			dir = "gz_shengwu";
			break;
		case 7:
			dir = "gz_zhengzhi";
			break;
		case 8:
			dir = "gz_dili";
			break;
		case 9:
			dir = "gz_lishi";
			break;
		case 10:
			dir = "cz_shuxue";
			break;
		case 11:
			dir = "cz_yuwen";
			break;
		case 12:
			dir = "cz_yingyu";
			break;
		case 13:
			dir = "cz_wuli";
			break;
		case 14:
			dir = "cz_huaxue";
			break;
		case 15:
			dir = "cz_shengwu";
			break;
		case 16:
			dir = "cz_zhengzhi";
			break;
		case 17:
			dir = "cz_dili";
			break;
		case 18:
			dir = "cz_lishi";
			break;
		}
		return dir;
	}
	
}
