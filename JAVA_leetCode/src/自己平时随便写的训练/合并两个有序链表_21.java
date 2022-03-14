package 自己平时随便写的训练;

public class 合并两个有序链表_21 {

    static class ListNode {
        int val;
        ListNode next;
        public ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args){
        合并两个有序链表_21 list = new 合并两个有序链表_21();
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(2, new ListNode(3,new ListNode(6))));
        list.print(list.mergeTwoLists(l1,l2));

        int a = 8;
        int b = a;
        b = 6;
        System.out.println("    "+ a + b);
    }

    //使用递归的方法，每次判断当前两个链表最前的val，然后把较小的一个val拿开，剩下的next再次进行方法，直到有一个链表为空！
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if(l1 == null){
//            return l2;
//        }else if(l2 == null){
//            return l1;
//        }else if(l1.val <= l2.val){
//            l1.next = mergeTwoLists(l1.next,l2);
//            return l1;
//        }else {
//            l2.next = mergeTwoLists(l1,l2.next);
//            return l2;
//        }
//    }

    /*
        使用迭代的方法，新建两个链表，temp指向fin的地址，首先判断l1和l2此时val大小，若l1更小将temp.next接到此时l1的头部，然后把l1截断到l1.next，
        让l1变短，然后一直持续这个操作，直到l1或者l2有一个为空。重要点：temp要不断往下移动，不然之前temp.next是l1的头部，之后又被改成了l2的头部，
        并么有往下继续链。 最后只需要判断l1与l2哪一个是空，直接把另一个剩下的所有接到temp后面即可！

        重点：因为temp是指向fin，后面我们所有的操作其实都是操作到fin上的，temp因为不断后移，所以最后temp自己本身的值是指向最后一个val，但是本身temp
        是从一个链表一步一步下移的，所以temp的目的是确定fin这个链表的顺序，而fin才是这个链表完整的表达！
    */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode fin = new ListNode();
        ListNode temp = fin;

        while (l1 != null && l2 != null){
            if(l1.val <= l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else{
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if(l1 == null){
            temp.next = l2;
        }else {
            temp.next = l1;
        }

        return fin.next;
    }

    public void print(ListNode listNode){
        if(listNode != null){
            System.out.print(listNode.val);
            print(listNode.next);
        }
    }
}


