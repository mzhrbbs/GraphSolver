
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FindCenter
 */
@WebServlet("/FindCenter")
public class FindCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int[][] graph;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindCenter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		graph = (int[][]) session.getAttribute("graph");
		Object json = session.getAttribute("json");

		int[] priArray = (int[]) session.getAttribute("priorityArray");

		List<Integer> result = getPaths(graph);

		session.setAttribute("dist", result);
		request.setAttribute("dist", result);
		request.setAttribute("json", json);
		request.getRequestDispatcher("/display.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

	public static void print(int[][] dist) {

		int V = dist.length;
		for (int i = 0; i < V; ++i) {

			for (int j = 0; j < V; ++j) {
				if (dist[i][j] == 9999)
					;
				// System.out.print("INF ");
				else
					;

				// System.out.print(dist[i][j] + " ");
			}
			// System.out.println();
		}
	}

	List<Integer> getPaths(int[][] graph) {
		int V = graph.length;

		int dist[][] = new int[V][V];

		// Base case
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				dist[i][j] = graph[i][j];
			}
		}

		for (int k = 0; k < V; k++) {

			for (int i = 0; i < V; i++) {

				for (int j = 0; j < V; j++) {

					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}

			}
		}
		return getCenters(dist);
	}

	List<Integer> getCenters(int dist[][]) {
		int V = dist.length;

		int[] len = new int[V];
		for (int i = 0; i < V; i++) {
			len[i] = 0;
			for (int j = 0; j < V; j++) {
				if (i != j && dist[i][j] > len[i])
					len[i] = dist[i][j];
			}
		}

		int center = len[0];
		for (int i = 1; i < len.length; i++) {
			if (len[i] <= center)
				center = len[i];
		}

		// Get the centers in array
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < len.length; i++) {
			if (len[i] == center)
				res.add(i);
		}

		return res;
	}

}
