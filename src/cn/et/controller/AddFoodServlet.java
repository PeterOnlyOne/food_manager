package cn.et.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import cn.et.model.MyFood;

/**
 * Servlet implementation class AddFoodServlet
 */
public class AddFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    String absPath = "E:/myImage/"; 
    MyFood myFood = new MyFood();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ServletFileUpload.isMultipartContent(request);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建一个新的文件上传处理程序
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		//解析请求，获取文件项
		List fileItems;
		String typeId=null;
		String foodName=null;
		String price=null;
		String introduce=null;
		ServletContext sc = this.getServletContext();
		
		String sPath = "/";
		try {
			fileItems = upload.parseRequest(request);
			//处理上传文件项
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				//处理上传的文件项
				FileItem fItem = (FileItem) iter.next();
				if (fItem.isFormField()) {
					if (fItem.getFieldName().equals("typeId")) {
						typeId = fItem.getString();
					}
					if (fItem.getFieldName().equals("foodName")) {
						foodName = fItem.getString("UTF-8");
					}
					if (fItem.getFieldName().equals("price")) {
						price = fItem.getString();
					}
					if (fItem.getFieldName().equals("introduce")) {
						introduce = fItem.getString("UTF-8");
					}
				}else {
					InputStream is = fItem.getInputStream();
					//获取文件名
					String name = fItem.getName();
					String destPath = absPath+"/"+name;
					sPath = sPath+name;
					FileOutputStream fos = new FileOutputStream(destPath);
					byte[] bs = new byte[1024];
					int n = 1;
					while ((n=is.read(bs)) != -1) {
						fos.write(bs, 0, n);
					}
					fos.close();
					is.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		try {
			myFood.saveFood(typeId, foodName, price, sPath, introduce);
			request.getRequestDispatcher("/foodList").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
