import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

@WebServlet("/FileUploadHandler")
@MultipartConfig
public class FileUploadHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Part filePart = request.getPart("file");
		String stringLine = null;

		HashSet<String> set = new HashSet<String>();
		int size = 0;
		String[] arr = new String[3];
		ObjectNode obNode = null;
		
		try {
			size = Integer.parseInt(request.getParameter("maxNodes"));
		} catch (NumberFormatException ne) {
			response.getWriter().print("Value must be an integer");
		} catch (Exception e) {
			response.getWriter().print(e.getMessage());
			return;
		}

		// initialize a graph and preprocess it
		int[][] graph = new int[size + 1][size + 1];
		preProcess(graph);

		BufferedReader bufferedReader;
		CsvMapper csvMapper = new CsvMapper();
		ArrayNode arrayNode = csvMapper.createArrayNode();
		ArrayNode sourceNode = csvMapper.createArrayNode();

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));
			bufferedReader.readLine();
			
			while ((stringLine = bufferedReader.readLine()) != null) {
				
				arr = stringLine.split(",");
				String src = arr[0] = arr[0].trim();
				String dest = arr[1] = arr[1].trim();
				String weight = arr[2] = arr[2].trim();
				
				//Define the adjacency matrix
				int x = Integer.parseInt(src);
				int y = Integer.parseInt(dest);
				int z = Integer.parseInt(weight);
			
				graph[x][y] = z;
				
				//Define json object for displaying the graph
				ObjectNode jNode = csvMapper.createObjectNode();
				jNode.put("id", src + "-" + dest);
				jNode.put("source", src);
				jNode.put("target", dest);
				jNode.put("label", weight);
				jNode.put("type", "arrow");

				if (!set.contains(src)) {
					
					set.add(src);
					ObjectNode xNode = csvMapper.createObjectNode();
					xNode.put("id", src);
					xNode.put("label", src);
					xNode.put("x", Math.random() * 4 + 1);
					xNode.put("y", Math.random() * 4 + 1);
					xNode.put("size", (int) Math.random() * 2 + 1);
					sourceNode.add(xNode);
				}

				if (!set.contains(dest)) {
					
					set.add(dest);
					ObjectNode xNode = csvMapper.createObjectNode();
					xNode.put("id", dest);
					xNode.put("label", dest);
					xNode.put("x", Math.random() * 4 + 1);
					xNode.put("y", Math.random() * 4 + 1);
					xNode.put("size", (int) Math.random() * 2 + 1);
					sourceNode.add(xNode);
				}
				arrayNode.add(jNode);
			}
			
			obNode = (ObjectNode) csvMapper.createObjectNode().set("edges", arrayNode);
			obNode.set("nodes", sourceNode);

		} catch (IOException e) {
			System.out.println("Error while printing.");
		}
		
		//Send graph to back-end and json to front-end
		session.setAttribute("graph", graph);
		session.setAttribute("json", obNode);
		request.getRequestDispatcher("FindCenter").forward(request, response);

	}

	//To preprocess the adjacency matrix
	public static void preProcess(int[][] matrix) {
		
		int n = matrix.length;
		int inf = 9999;
		
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j < n; j++) {
				
				if (i == j) 
					matrix[i][j] = 0;
				else 
					matrix[i][j] = inf;
			}
		}
	}
}
