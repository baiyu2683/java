import java.util.*;

public class TestSubLength {


    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList();

        // 使用深度优先+回溯实现
        // 每个路径上节点是否被标记过
        boolean[] marked = new boolean[nums.length];
        // 路径
        List<Integer> path = new ArrayList();
        dfs(nums, marked, path, res);
        return res;
    }

    private void dfs(int[] nums, boolean[] marked, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList(path));
            return;
        }

        for(int i = 0 ; i < nums.length; i++) {
            if (!marked[i]) {

                // 标记当前节点为遍历过了
                marked[i] = true;
                path.add(nums[i]);
                System.out.println(path.size() + "标记前: " + Arrays.toString(path.toArray()));
                System.out.println(path.size() + "标记前: " + Arrays.toString(marked));
                // 深度优先一直到叶子节点
                dfs(nums, marked, path, res);

                // 标记完成之后,回溯,开始下一个分支
                marked[i] = false;
                path.remove(path.size() - 1);
                System.out.println(path.size() + "标记后: " + Arrays.toString(path.toArray()));
                System.out.println(path.size() + "标记后: " + Arrays.toString(marked));
            }
        }
    }

    public static void main(String[] args) {
        TestSubLength testSubLength = new TestSubLength();
        testSubLength.permute(new int[]{1,2,3});
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
