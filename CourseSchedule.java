import java.util.*;

public class CourseSchedule {
    //topological sort
    //Time complexity - o(v+e)
    // Space complexity - o(v+e)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0)
            return true;

        // indegrees array to represent number of courses - length.
        // each index describes the course number and the value at each index is the
        // number
        // of pre req courses that particular course is dependent on

        int[] indegrees = new int[numCourses];
        // Has map to depict key - course id. value list of courses that particular
        // key is dependent on.
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // queue for bfs
        Queue<Integer> q = new LinkedList<>();
        // variable to keep count of completed courses
        int count = 0;
        // Iterate over pre req array and populate indegree array.
        for (int[] prereq : prerequisites) {
            int to = prereq[0];
            int from = prereq[1];
            indegrees[to]++;
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<Integer>());
            }
            map.get(from).add(to);
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
                count++;
            }
        }
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (!map.containsKey(curr)) {
                continue;
            }
            List<Integer> edges = map.get(curr);
            for (int edge : edges) {
                indegrees[edge]--;
                if (indegrees[edge] == 0) {
                    q.add(edge);
                    count++;
                }
            }

        }
        return numCourses == count;

    }
}
