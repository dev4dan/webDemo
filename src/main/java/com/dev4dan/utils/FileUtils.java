package com.dev4dan.utils;

import java.io.*;
import java.util.*;

/**
 * Created by danielwood on 18/04/2017.
 */
public class FileUtils {
	private static final Map<String, String> FILE_HEADERS = new HashMap<String, String>(); // 文件头集合

	static {
		FILE_HEADERS.put("ffd8ffe000104a464946", "jpg"); //JPEG (jpg)
		FILE_HEADERS.put("89504e470d0a1a0a0000", "png"); //PNG (png)
		FILE_HEADERS.put("47494638396126026f01", "gif"); //GIF (gif)
		FILE_HEADERS.put("49492a00227105008037", "tif"); //TIFF (tif)
		FILE_HEADERS.put("424d228c010000000000", "bmp"); //16色位图(bmp)
		FILE_HEADERS.put("424d8240090000000000", "bmp"); //24位位图(bmp)
		FILE_HEADERS.put("424d8e1b030000000000", "bmp"); //256色位图(bmp)
		FILE_HEADERS.put("41433130313500000000", "dwg"); //CAD (dwg)
		FILE_HEADERS.put("3c21444f435459504520", "html"); //HTML (html)
		FILE_HEADERS.put("3c21646f637479706520", "htm"); //HTM (htm)
		FILE_HEADERS.put("48544d4c207b0d0a0942", "css"); //css
		FILE_HEADERS.put("696b2e71623d696b2e71", "js"); //js
		FILE_HEADERS.put("7b5c727466315c616e73", "rtf"); //Rich Text Format (rtf)
		FILE_HEADERS.put("38425053000100000000", "psd"); //Photoshop (psd)
		FILE_HEADERS.put("46726f6d3a203d3f6762", "eml"); //Email [Outlook Express 6] (eml)
		FILE_HEADERS.put("d0cf11e0a1b11ae10000", "doc"); //MS Excel 注意：word、msi 和 excel的文件头一样
		FILE_HEADERS.put("d0cf11e0a1b11ae10000", "vsd"); //Visio 绘图
		FILE_HEADERS.put("5374616E64617264204A", "mdb"); //MS Access (mdb)
		FILE_HEADERS.put("252150532D41646F6265", "ps");
		FILE_HEADERS.put("255044462d312e350d0a", "pdf"); //Adobe Acrobat (pdf)
		FILE_HEADERS.put("2e524d46000000120001", "rmvb"); //rmvb/rm相同
		FILE_HEADERS.put("464c5601050000000900", "flv"); //flv与f4v相同
		FILE_HEADERS.put("00000020667479706d70", "mp4");
		FILE_HEADERS.put("49443303000000002176", "mp3");
		FILE_HEADERS.put("000001ba210001000180", "mpg"); //
		FILE_HEADERS.put("3026b2758e66cf11a6d9", "wmv"); //wmv与asf相同
		FILE_HEADERS.put("52494646e27807005741", "wav"); //Wave (wav)
		FILE_HEADERS.put("52494646d07d60074156", "avi");
		FILE_HEADERS.put("4d546864000000060001", "mid"); //MIDI (mid)
		FILE_HEADERS.put("504b0304140000000800", "zip");
		FILE_HEADERS.put("526172211a0700cf9073", "rar");
		FILE_HEADERS.put("235468697320636f6e66", "ini");
		FILE_HEADERS.put("504b03040a0000000000", "jar");
		FILE_HEADERS.put("4d5a9000030000000400", "exe");//可执行文件
		FILE_HEADERS.put("3c25402070616765206c", "jsp");//jsp文件
		FILE_HEADERS.put("4d616e69666573742d56", "mf");//MF文件
		FILE_HEADERS.put("3c3f786d6c2076657273", "xml");//xml文件
		FILE_HEADERS.put("494e5345525420494e54", "sql");//xml文件
		FILE_HEADERS.put("7061636b616765207765", "java");//java文件
		FILE_HEADERS.put("406563686f206f66660d", "bat");//bat文件
		FILE_HEADERS.put("1f8b0800000000000000", "gz");//gz文件
		FILE_HEADERS.put("6c6f67346a2e726f6f74", "properties");//bat文件
		FILE_HEADERS.put("cafebabe0000002e0041", "class");//bat文件
		FILE_HEADERS.put("49545346030000006000", "chm");//bat文件
		FILE_HEADERS.put("04000000010000001300", "mxp");//bat文件
		FILE_HEADERS.put("504b0304140006000800", "docx");//docx文件
		FILE_HEADERS.put("d0cf11e0a1b11ae10000", "wps");//WPS文字wps、表格et、演示dps都是一样的
		FILE_HEADERS.put("6431303a637265617465", "torrent");
		FILE_HEADERS.put("6D6F6F76", "mov"); //Quicktime (mov)
		FILE_HEADERS.put("FF575043", "wpd"); //WordPerfect (wpd)
		FILE_HEADERS.put("CFAD12FEC5FD746F", "dbx"); //Outlook Express (dbx)
		FILE_HEADERS.put("2142444E", "pst"); //Outlook (pst)
		FILE_HEADERS.put("AC9EBD8F", "qdf"); //Quicken (qdf)
		FILE_HEADERS.put("E3828596", "pwl"); //Windows Password (pwl)
		FILE_HEADERS.put("2E7261FD", "ram"); //Real Audio (ram)
	}

	private static File getAvailableFile(String filePath) {
		if (filePath != null && !filePath.trim().equals("")) {
			File file = new File(filePath);
			if (!file.exists() || file.length() < 11) {
				return null;
			}
			return file;
		}
		return null;
	}

	private static String getFileHeaderBy10Bytes(FileInputStream is) {
		String value = null;
		try {
			byte[] b = new byte[10];
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
			System.out.println("getFileHeaderBy10Bytes.value : " + value);
		} catch (Exception e) {
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return value;
	}

	public static String getFileHeaderBy3Bytes(FileInputStream is) {
		String value = null;
		try {
			byte[] b = new byte[3];
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		} catch (Exception e) {
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return value;
	}

	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		return builder.toString().toLowerCase();
	}

	public static String getFileType(String filePath) {
		File file = getAvailableFile(filePath);

		FileInputStream is = null;
		String fileSuffix = null;
		try {
			is = new FileInputStream(file);
			String keySearch = getFileHeaderBy10Bytes(is);
			fileSuffix = FILE_HEADERS.get(keySearch);
			//补充 这里并不是所有的文件格式前10 byte（jpg）都一致，前五个byte一致即可
			if (isEmpty(fileSuffix)) {
				Iterator<String> keyList = FILE_HEADERS.keySet().iterator();
				String key, keySearchPrefix = keySearch.substring(0, 5);
				while (keyList.hasNext()) {
					key = keyList.next();
					if (key.contains(keySearchPrefix)) {
						fileSuffix = FILE_HEADERS.get(key);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileSuffix;
	}

	public static boolean isEmpty(final CharSequence s) {
		if (s == null) {
			return true;
		}
		return s.length() == 0;
	}

	public static List<String> readFileLine(FileInputStream fis){
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		List<String> lines = null;
		try {
			String str = "";
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
			lines = new ArrayList<>();
			// InputStreamReader的对象
			while ((str = br.readLine()) != null) {
				lines.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("读取文件失败");
		} finally {
			try {
				// 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
				if(br != null){
					br.close();
				}
				if(isr != null){
					isr.close();
				}
				if(fis != null){
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lines;
	}

	public static void readFile(){
		String path = "/Users/danielwood/IdeaProjects/git/web-pro/newAreaCode.txt";
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			List<String> lines = readFileLine(fis);
			for(int i=0 ; lines != null && i<lines.size() ; i++){
				String line = lines.get(i);
				String[] content = line.trim().split("=");
				System.out.println(content[1] + "=" + content[0]);
			}

			/*
			for(int i=0 ; lines != null && i<lines.size() ; i++){
				String line = lines.get(i);
				String[] tmps = line.split("：");
				if(tmps.length == 2){
					String header = tmps[1];
					String headerDiscr = tmps[0].replace("，文件头", "");
					String item = "FILE_HEADERS.put(\""+header+"\",\""+headerDiscr+"\");";
					System.out.println(item);

				}
			}*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取项目所在的根路径，用于获取普通配置文件，一般为properties
	 * String pkgPath 包路径
	 * String splitor 切分符号
	 * return
	 * */
	public static String getRootDir(String pkgPath, String splitor){
		if(pkgPath == null && pkgPath.trim().equals("")){
			return null;
		}
		String[] pkg = pkgPath.split(splitor);
		String[] projectPath = getProjectPath().split("/");

		if(pkg.length >= projectPath.length){
			return null;
		}

		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0 ; i<(projectPath.length-(pkg.length-2)) ; i++){
			stringBuilder.append("/"+projectPath[i]);
		}
		return stringBuilder.toString();
	}

	/**
	 * @return path返回当前文件的绝对路径；
	 * 描述：该方法用于自动判断项目文件所在的物理地址，并直接返回指定文件的物理地址；
	 * */
	public static String getProjectPath(){
		String realPath = FileUtils.class.getResource("").toString();
		//System.out.println("getProjectPath . realPath : "+realPath);
		String[] tmp = realPath.split("/");
		String path = "";
		String specToken = "bin";

		for(int i = 1 ; i<tmp.length ; i++){
			path += tmp[i]+"/";
			if(tmp[i].equals(specToken)){
				//showInfo("getFileRealPath . tmp["+i+"] : " + tmp[i]);
				break;
			}
		}
		//showInfo("getFileRealPath . path : "+path);
		return path;
	}

	/**
	 * @return path返回当前文件的绝对路径；
	 * 描述：该方法用于自动判断项目文件所在的物理地址，并直接返回指定文件的物理地址；
	 * */
	public static String getProjectPath4Bat(){
		String realPath = FileUtils.class.getResource("").toString();
//		System.out.println("getProjectPath . realPath : "+realPath);
		String[] tmp = realPath.split("/");
		String path = "";
		String specToken = "bin";

		for(int i = 1 ; i<tmp.length ; i++){
			path += tmp[i]+"\\";
			if(tmp[i].equals(specToken)){
				//showInfo("getFileRealPath . tmp["+i+"] : " + tmp[i]);
				break;
			}
		}
		//showInfo("getFileRealPath . path : "+path);
		return path;
	}

	/**
	 * @Description：该方法用于创建一个指定路径和名称的文件；
	 * @param folderPath
	 * 文件所在的路径；
	 * @param fileName
	 * 文件名称；
	 * @return boolean
	 */
	public static boolean createFile(String folderPath,String fileName){
		File file = new File(folderPath,fileName);
		boolean isCreated = false;
		try {
			if(!file.exists()){
				file.getParentFile().mkdirs();
				isCreated = file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isCreated;
	}

	/**
	 * @Description：该方法用于判断指定名称的文件是否存在；
	 * @param fileName
	 * 文件名称；
	 * @return boolean
	 */
	public static boolean isFileExist(String fileName){
		File file = new File(fileName);
		boolean flag = file.exists();
		return flag;
	}

	public static void main(String[] args) {
//		System.out.println(getFileType("/Users/danielwood/Documents/kirua.jpeg"));
//		readFile();
//		readFile();
		System.out.println("getProjectPath : "+getProjectPath());
		System.out.println("getRootDir : "+getRootDir(Constants.pkgPath, "/"));
	}
}
