package com.plate.baiyue.common;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.plate.baiyue.curriculum.knowledge.model.Knowledge;
import com.plate.baiyue.curriculum.knowledge.persistence.KnowledgeMapper;
import com.plate.common.spring.SpringContextHolder;
import com.plate.common.util.FileUtils;
import com.plate.frame.core.model.Catalog;
import com.plate.frame.core.persistence.CatalogMapper;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-2
 * @描述:
 */
public class BaiYueSQLiteUtil {
	
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	private static KnowledgeMapper knowMapper = SpringContextHolder.getBean(KnowledgeMapper.class);
	private static CatalogMapper catalogMapper = SpringContextHolder.getBean(CatalogMapper.class);
	
	private static String srcPath = SpringContextHolder.getRootRealPath()+"/"+BaiYueServerGlobalConfig.ZSD_DBFILE;
	
	private static String getTargetPath(String target){
		return SpringContextHolder.getRootRealPath()+"/"+target;
	}
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成知识点库
	 * @param catalogId
	 */
	public static void generateKnowledgeBank(Integer catalogId){
		String dbTargetPath = getTargetPath(BaiYueServerGlobalConfig.getDbDirNameByCatalog(catalogId));
		String imTargetPath = getTargetPath(BaiYueServerGlobalConfig.getImageDirNameByCatalog(catalogId));
		//复制数据文件
		FileUtils.copyFile(srcPath, dbTargetPath);
		//压缩
		File imageFile = new File(imTargetPath);
		File zipFile = new File(getTargetPath(BaiYueServerGlobalConfig.getImageDirNameByCatalog(catalogId)+"/"+BaiYueServerGlobalConfig.getImageFileByCatalog(catalogId)+".rar"));
		File[] fs = imageFile.listFiles();
		if(fs!=null && fs.length>0){
			FileUtils.zipFiles(fs, zipFile);
		}
		PreparedStatement catalogPstmt = null;
		PreparedStatement knowPstmt    = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbTargetPath);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			//目录信息
			Catalog catalog = catalogMapper.getCatalogById(catalogId);
			List<Catalog> catalogList = catalogMapper.getDescendantCatalogById(catalog.getId());
			catalogList.add(0, catalog);
			//更改版本号
			catalogMapper.updateCatalogVersion(catalogId);
			//知识点
			List<Knowledge> knowList = knowMapper.getDescendantKnowledgeByCatalog(catalogId);
			String catalogSql = "insert into C_DIR(_id,name,parent_id,dir_ids,leaf,serialno,sub_id,version,file_path) values(?,?,?,?,?,?,?,?,?)";
			catalogPstmt = conn.prepareStatement(catalogSql);
			for(int i=0;i<catalogList.size();i++){
				Catalog c = catalogList.get(i);
				catalogPstmt.setInt(1, c.getId());
				catalogPstmt.setString(2, c.getName());
				catalogPstmt.setInt(3, c.getParent_id());
				catalogPstmt.setString(4, c.getDir_ids());
				catalogPstmt.setInt(5, c.getLeaf());
				catalogPstmt.setInt(6, c.getSerialno());
				catalogPstmt.setInt(7, c.getSub_id());
				catalogPstmt.setInt(8, c.getVersion());
				catalogPstmt.setString(9, c.getFile_path());
				catalogPstmt.addBatch();
			}
			String knowSql = "insert into C_KNOWLEDGE(_id,search_key,content,dir_id,dir_ids,sub_id) values(?,?,?,?,?,?)";
			knowPstmt = conn.prepareStatement(knowSql);
			for(int i=0;i<knowList.size();i++){
				Knowledge know = knowList.get(i);
				knowPstmt.setInt(1, know.getId());
				knowPstmt.setString(2, know.getSearch_key());
				knowPstmt.setString(3, know.getContent());
				knowPstmt.setInt(4, know.getDir_id());
				knowPstmt.setString(5, know.getDir_ids());
				knowPstmt.setInt(6, know.getSub_id());
				knowPstmt.addBatch();
			}
			catalogPstmt.executeBatch();
			knowPstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
