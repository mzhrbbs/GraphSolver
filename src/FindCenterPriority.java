import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

@WebServlet("/FindCenterPriority")
@MultipartConfig
public class FindCenterPriority extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> city_list = new ArrayList<String>();
	int cityValueToaAssigned = 0;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = session.getId();
		Part filePart = request.getPart("file");

		String stringLine = null;

		HashSet<String> city_list = new HashSet<String>();
		String[] lineArray = new String[3];

		int edge = 0;
		ObjectNode obNode = null;

		int size = 0;
		try {
			size = Integer.parseInt(request.getParameter("maxNodes"));

		} catch (NumberFormatException ne) {
			response.getWriter().print("Value must be an integer less than 100");
		} catch (Exception e) {
			response.getWriter().print(e.getMessage());
			return;
		}

		int[][] graph = new int[size + 1][size + 1];
		int[] priArray = new int[size + 1];

		preProcess(graph);
		BufferedReader bufferedReader;
		CsvMapper csvMapper = new CsvMapper();

		ArrayNode arrayNode = csvMapper.createArrayNode();
		ArrayNode sourceNode = csvMapper.createArrayNode();

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));
			bufferedReader.readLine();

			int priDest = 0;
			int priSrc = 0;
			String src = "";
			String dest = "";
			String weight = "";

			while ((stringLine = bufferedReader.readLine()) != null) {
				edge++;
				ObjectNode jNode = csvMapper.createObjectNode();
				lineArray = stringLine.split(",");

				if (lineArray[0].contains("(")) {
					src = lineArray[0].split("\\(")[0];
					String temp = lineArray[0].split("\\(")[1];

					String pri = temp.substring(0, temp.indexOf(')'));
					priSrc = Integer.parseInt(pri);
					priArray[Integer.parseInt(src)] = priSrc;
					weight = lineArray[2] = lineArray[2].trim();

				} else if (!lineArray[0].contains("(")) {
					src = lineArray[0] = lineArray[0].trim();
					weight = lineArray[2] = lineArray[2].trim();
				}

				if (lineArray[1].contains("(")) {
					dest = lineArray[1].split("\\(")[0];
					String temp = lineArray[1].split("\\(")[1];

					String pri = temp.substring(0, temp.indexOf(')'));
					priDest = Integer.parseInt(pri);
					priArray[Integer.parseInt(dest)] = priDest;
					weight = lineArray[2] = lineArray[2].trim();

				} else if (!lineArray[1].contains("(")) {

					dest = lineArray[1] = lineArray[1].trim();
					weight = lineArray[2] = lineArray[2].trim();
				}

				src = src.trim();
				dest = dest.trim();
				weight.trim();

				int x = Integer.parseInt(src);
				int y = Integer.parseInt(dest);
				int z = Integer.parseInt(weight);
				graph[x][y] = z;
				jNode.put("id", src + "-" + dest);

				jNode.put("source", src);
				jNode.put("target", dest);
				jNode.put("label", weight);
				jNode.put("type","arrow");

				if (!city_list.contains(src)) {

					city_list.add(src);

					ObjectNode xNode = csvMapper.createObjectNode();

					xNode.put("id", src);
					xNode.put("label", src);
					xNode.put("x", Math.random() * 4 + 1);
					xNode.put("y", Math.random() * 4 + 1);
					xNode.put("size", (int) Math.random() * 2 + 1);
					sourceNode.add(xNode);
				}

				if (!city_list.contains(dest)) {
					city_list.add(dest);

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

			 System.out.println(obNode.toString());
		} catch (IOException e) {
			System.out.println("Error while printing.");

		}
		session.setAttribute("graph", graph);
		session.setAttribute("json", obNode);
		session.setAttribute("priorityArray", priArray);
		request.getRequestDispatcher("/FindCP").forward(request, response);

	}

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